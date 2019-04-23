/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alerta;

import java.io.File;
import java.io.PrintWriter;
import java.io.StringWriter;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.util.Duration;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import org.controlsfx.control.Notifications;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 *
 * @author Faruque Braimo
 */
public class Alerta {
//    
    
//     Icon icon = new ImageIcon(getClass().getResource("Fotos/voo.jpg"));
    
//    public static final Image image_checked=new Image("/Imagens/checked.png");
//    public static final Image image_warning=new Image("/Imagens/warning.png");
//    public static final Image image_movie_forbidden=new Image("/Imagens/forbidden.png");
//    
    public static void AlertaSimples(File file) {
         try {
            Clip clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(file));
            clip.start();
           
            
        } catch (Exception e) {
          
                    
        }
    }
    
    public static void MostrarAviso(Exception ex) {
//        Image img=image_movie_forbidden;
        Notifications notify=Notifications.create()
                        .darkStyle()
                
                
////                        .graphic(new ImageView(img))
                        .title(ex.getClass().getName())
                        .text(ex.getMessage())
                        .position(Pos.CENTER)
                        .hideAfter(Duration.seconds(3));
                          notify.show();
    }
    public static void errar(String titulo,String message){
           
            File audio = new File("Music/Notify.wav");
            Alerta.AlertaSimples(audio);
            TrayNotification tray = new TrayNotification();
            tray.setNotificationType(NotificationType.ERROR);
            tray.setTitle(titulo);
            tray.setMessage(message);
            tray.setAnimationType(AnimationType.SLIDE);
            
            tray.showAndDismiss(Duration.millis(4000));
    }
    public static void exito(String title,String message){
        TrayNotification tray1 = new TrayNotification();
            tray1.setNotificationType(NotificationType.SUCCESS);
            tray1.setTitle(title);
            tray1.setMessage(message);
            tray1.setAnimationType(AnimationType.SLIDE);
            tray1.showAndDismiss(Duration.millis(3000));
    }
     public static void MostrarNotificacao(String Title,String Message) 
     {
//              img   =  image_checked;
         
          
         
                Notifications notify=Notifications.create()
                        .darkStyle()
//                        .graphic(new ImageView(img))
                        .title(Title)
                        .text(Message)
                        .position(Pos.CENTER)
                        .hideAfter(Duration.seconds(2));
                notify.show();
                
                
     }
     public static void main(String[] args) {

    }
}
     
     
    
