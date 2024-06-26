package Service;

import Constants.Status;
import Constants.Type;
import model.CryptoCoins;
import model.CryptoData;
import model.Notification;
import model.User;

import java.util.*;

public class NotificationService {

    private static Map<UUID, Notification> notifications;
    private static Map<Status, Set<Notification>> notificationStatusMap;
    private CryptoService cryptoService;
    private static UserService userService;

    public NotificationService() {
        notifications = new HashMap<>();
        notificationStatusMap = new HashMap<>();
        cryptoService = new CryptoService();
        userService = new UserService();
    }

    // Send notification API (Details mentioned in NotificationApp file)
    public static boolean sendNotification(UUID notificationId) {
        Notification notification = notifications.get(notificationId);
        boolean result = false;
        try {
            String email = userService.getUserEmail(notification.getUser().getUserId());
            switch(notification.getType()) {
                case SMS:
                    result = true;
                    break;
                case EMAIL:
                    result = new EmailService().sendEmail(email, notification.getCryptoData());
                    break;
                default:
                    throw new IllegalArgumentException("Notification type not supported");
            }
        } catch(Exception ex) {
            System.out.println("Error while sending notificaion: "+ex.getMessage());
        }
        updateNotificationStatus(notification, result);
        return result;
    }

    private static void updateNotificationStatus(Notification notification, boolean result) {
        if(result) {
            notification.setStatus(Status.SENT);
            notificationStatusMap.computeIfAbsent(Status.SENT, data-> new HashSet<>()).add(notification);
        } else {
            notification.setStatus(Status.FAILED);
            notificationStatusMap.computeIfAbsent(Status.FAILED, data-> new HashSet<>()).add(notification);
        }
        notifications.put(notification.getNotificationId(), notification);
    }


    // List of notifications based on status (Details mentioned in NotificationApp file)
    public Set<Notification> getNotifications(Status status) {
        if( notificationStatusMap.get(status).size() == 0) {
            System.out.println("No notification found for status "+ status);
        }
        return notificationStatusMap.get(status);
    }

    // Get notification based on Id (Details mentioned in NotificationApp file)
    // Time complexity O(1)
    public Notification getNotificationBasedOnId(UUID notificationId) throws Exception {
        if(!notifications.containsKey(notificationId)) {
            System.out.println("NotificationId not found");
            throw new Exception("NotificationId not found");
        }
        return notifications.get(notificationId);
    }

    // Delete notification based on id (Details mentioned in NotificationApp file)
    // Time complexity O(1)
    public void deleteNotification(UUID notificationId) throws Exception {
        if(!notifications.containsKey(notificationId)) {
            throw new Exception("Notification not found");
        }
        Notification notification = notifications.get(notificationId);
        Status status = notifications.get(notification.getNotificationId()).getStatus();
        notificationStatusMap.get(status).remove(notification);
        notifications.remove(notificationId);
        System.out.println("Notification Deleted: "+ notification);
    }


    public Notification createNotification(User user, Type type, CryptoData cryptoData) {
        Notification notification = new Notification(UUID.randomUUID(), type, Status.OUTSTANDING, user, cryptoData);
        notifications.put(notification.getNotificationId(), notification);
        notificationStatusMap.computeIfAbsent(Status.OUTSTANDING, data-> new HashSet<>()).add(notification);
        System.out.println("Notification created: ");
        System.out.println(notification);
        return notification;
    }

}
