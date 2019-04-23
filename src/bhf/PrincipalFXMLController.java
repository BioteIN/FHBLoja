/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bhf;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.swing.JOptionPane;


/**
 *
 * @author bONGANI
 */
public class PrincipalFXMLController implements Initializable {
    
   
    @FXML
    private JFXButton btSair;
  @FXML
    private JFXTextField usuario;

    @FXML
    private JFXPasswordField senha;

    @FXML
    private JFXButton btEntrar;


    @FXML
    void entrar(ActionEvent event) throws IOException{
 if (usuario.getText().equalsIgnoreCase("helio") ||  usuario.getText().equalsIgnoreCase("faruque") || usuario.getText().equalsIgnoreCase("biote") )    {
                if (senha.getText().equalsIgnoreCase("1234") || senha.getText().equalsIgnoreCase("2601") || senha.getText().equalsIgnoreCase("0000")  )
                { 
                    JOptionPane.showMessageDialog(null, "Seja Bem vindo");
                    
                    
                    Parent root = FXMLLoader.load(getClass().getResource("PrincipalFXML.fxml"));
                    Stage stage = new Stage();
                    Scene scene=new Scene(root);
                    stage.setScene(scene);
                    stage.show();
      
                    
                    
                }
                }
           
             
            else {
                JOptionPane.showMessageDialog(null, "Usuario ou senha Errada verifique isso: ");
                usuario.setText("");
                senha.setText("");
            }
           
        }
    

   

    @FXML
    private void sair(ActionEvent event) {
     System.exit(0);
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
