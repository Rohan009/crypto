package Service;

import Constants.Status;
import Constants.Type;
import model.Notification;
import model.User;

import java.util.*;

public class NotificationService {

    static Map<String, Notification> notifications = new HashMap<>();
    static Map<Status, Set<Notification>> notificationStatusMap = new HashMap<>();

    private static UserService userService = new UserService();

    public static boolean sendNotification(Notification notification) {
        boolean result = false;
        try {
            String email = userService.getUserEmail(notification.getUser().getUserId());
            switch(notification.getType()) {
                case SMS:
                    result = true;
                    break;
                case EMAIL:
                    result = new EmailService().sendEmail(email);
                    break;
                default:
                    throw new IllegalArgumentException("Notification type not supported");
            }
        } catch(Exception ex) {
            System.out.println("Error while sending notificaiont");
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

    public Set<Notification> getNotifications(Status status) {
        return notificationStatusMap.get(status);
    }

    public static void deleteNotification(Notification notification) {
        Status status = notifications.get(notification.getNotificationId()).getStatus();
        notificationStatusMap.get(status).remove(notification);
    }

}
