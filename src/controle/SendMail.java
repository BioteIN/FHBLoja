/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

//import java.util.Properties;
//import javax.mail.Message;
//import javax.mail.MessagingException;
//import javax.mail.Session;
//import javax.mail.Transport;
//import javax.mail.internet.InternetAddress;
//import javax.mail.internet.MimeMessage;
//import javax.mail.PasswordAuthentication;

/**
 *
 * @author bONGANI
 */
public class SendMail {

    /**
     * @param args the command line arguments
     */
    public static void enviar(String email, String assunto){
//        final String username = "bioten11@gmail.com";
//        final String password = "11011999";
//        Properties props = new Properties();
//        props.put("mail.smtp.auth", "true");
//        props.put("mail.smtp.host", "smtp.gmail.com");
//        props.put("mail.smtp.port", "587");
//        props.put("mail.smtp.ssl.trust", "*");
//
//        props.put("mail.smtp.user", username);
//        props.put("mail.smtp.password", password);
//
//        props.put("mail.smtp.auth", "true");
//        props.put("mail.smtp.starttls.enable", "true");
//        props.put("mail.smtp.EnableSSL.enable", "true");
//
//        Session session = Session.getDefaultInstance(props,
//                new javax.mail.Authenticator() {
//            protected PasswordAuthentication getPasswordAuthentication() {
//                return new PasswordAuthentication(username, password);
//            }
//        }
//        );
//        try {
//            Message message = new MimeMessage(session);
//            message.setFrom(new InternetAddress("bioten11@gmail.com"));
//            message.setRecipients(Message.RecipientType.TO,
//                    InternetAddress.parse(email));
//            message.setSubject(assunto);
//            message.setContent("<h:body style=background-color:white;font-family:verdana;color:#0066CC;>"
//                    + "Se estas a ver iston escreveste o teu primeiro email com java!</b></b>"
//                    + "</body>", "test/html; charset=utf-8");
//            Transport.send(message);
//            System.out.println("Email enviadoooooooo");
//        } catch (MessagingException e) {
//            System.out.println(e + "Grannnda ERROR");
//            throw new RuntimeException(e);
//        } 
    }
}
