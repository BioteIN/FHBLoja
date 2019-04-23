/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alerta;

import javafx.scene.control.TextField;

/**
 *
 * @author Faruque Braimo
 */
public class Validacoes  extends TextField{
    
    
    public void soLetras( int i, int a, String texto) {
        if( texto.matches("[0-9]") || texto.isEmpty()) {
            super.replaceText(a, a, texto);
        }
        
    }
    
}
