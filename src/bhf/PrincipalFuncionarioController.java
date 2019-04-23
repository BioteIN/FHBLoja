/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bhf;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Faruque Braimo
 */
public class PrincipalFuncionarioController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
     @FXML
    void caixar(ActionEvent event) throws IOException{
 Parent root = FXMLLoader.load(getClass().getResource("CaixaFXML.fxml"));
                    Stage stage = new Stage();
                    Scene scene=new Scene(root);
                    stage.setScene(scene);
                    stage.show();
    }

    @FXML
    void clientar(ActionEvent event) throws IOException{
 Parent root = FXMLLoader.load(getClass().getResource("Cliente.fxml"));
                    Stage stage = new Stage();
                    Scene scene=new Scene(root);
                    stage.setScene(scene);
                    stage.show();
    }

    @FXML
    void vender(ActionEvent event) throws IOException{
 Parent root = FXMLLoader.load(getClass().getResource("EntradaFXML.fxml"));
                    Stage stage = new Stage();
                    Scene scene=new Scene(root);
                    stage.setScene(scene);
                    stage.show();
    }
  

   

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
