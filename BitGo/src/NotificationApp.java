import Constants.Status;
import Constants.Type;
import Service.NotificationService;
import Service.UserService;
import model.CryptoCoins;
import model.CryptoData;
import model.Notification;
import model.User;

import java.util.Set;
import java.util.UUID;

public class NotificationApp {

    private static NotificationService notificationService = new NotificationService();
    private static UserService userService = new UserService();

    public static void main(String[] args) throws Exception {

        User user1 = new User("u1","abc@gmail.com");
        userService.addUser(user1);

        // creating dummy user data
        CryptoData cryptoData = new CryptoData(30000, 5000000, 32000, 600000000);

        /*
            API 1: Create Notification which will be post API having below Data
           POST: /app/crypto/notification
           Req: {
                cryptoData: {
                },
                type: "email",
                user: {
                    id: "1",
                    "email": ""
                }
           }
           Res: 201

         */
        System.out.println();
        notificationService.createNotification(user1, Type.EMAIL, cryptoData);

        /*
            API 2: get Status Notification which will be Get API which will give list of notifications in given status
           GET: /app/crypto/notification?status=outstanding
           Res: {
                "notificationId" : "1231",
                "type": "email",
                "status": "outstanding",
                user: {
                    id: "1",
                    "email": ""
                },
                body: {
                    cryptoData: {
                    },
                }
           }

         */
        System.out.println();
        Set<Notification> notifications =  notificationService.getNotifications(Status.OUTSTANDING);
        System.out.println("Outstanding Notifications: ");
        System.out.println(notifications);

        /*
            API 3: Send Notification which will get the notification from outstanding list and send to given user
            stored in that notification object
            In scalable project we can use here messaging queue where we will producing all notifications and notification
            service will be consuming it one by one from queue to send data to all users.

           Post: /app/crypto/notification/{id}/send
           Res: {
                "id": "12131",
                "status": "SENT",
                "message": "Notification sent successfully."
            }
         */

        System.out.println();
        UUID notificationId = notifications.iterator().next().getNotificationId();
        boolean isNotificationDelivered = notificationService.sendNotification(notificationId);
        System.out.println("Notification "+notificationId+" delivered: "+isNotificationDelivered);
        System.out.println();
        notifications =  notificationService.getNotifications(Status.SENT);
        System.out.println("Sent Notifications: ");
        System.out.println(notifications);
        /*
            API 4: Delete: it will delete ntoification from DB/In memory based on id passed
           DELETE: /app/crypto/notification/{id}
           Res: {
                "status": "SUCCESS",
                "message": "Notification deleted successfully."
            }
         */
        System.out.println();
        notificationService.deleteNotification(notificationId);

        System.out.println();
        System.out.println("Searching notification with id: "+ notificationId);
        notificationService.getNotificationBasedOnId(notificationId);

    }
}
