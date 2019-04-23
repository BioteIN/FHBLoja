/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alerta;

import java.io.File;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

/**
 *
 * @author Faruque Braimo
 */
public class Som {
    
    
    public static void main(String[] args) {
        File audio = new File("Notify.wav");
        tocarSom(audio);
    }
    
 private   static boolean tocarSom( File file) {
        try {
            Clip clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(file));
            clip.start();
            Thread.sleep(clip.getMicrosecondLength()/100);
            return true;
            
        } catch (Exception e) {
            return false;
                    
        }
    }
    
}
