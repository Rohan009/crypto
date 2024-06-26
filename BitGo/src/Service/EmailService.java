package Service;

import model.CryptoData;
import model.Notification;

public class EmailService {

    public static boolean sendEmail(String email, CryptoData cryptoData) {
//        String to = "email";//change accordingly
//        String from = "sonoojaiswal1987@gmail.com";
//        String host = "localhost";//or IP address
//
//        //Get the session object
//        Properties properties = System.getProperties();
//        properties.setProperty("mail.smtp.host", host);
//        Session session = Session.getDefaultInstance(properties);
//
//        //compose the message
//        try{
//            MimeMessage message = new MimeMessage(session);
//            message.setFrom(new InternetAddress(from));
//            message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));
//            message.setSubject("Crypto Details");
//            message.setText(cryptoData);
//
//            // Send message
//            Transport.send(message);
//            System.out.println("message sent successfully....");
//
//        }catch (MessagingException mex) {
//            mex.printStackTrace();
//        }
        return true;
    }
}
