/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bhf;

import java.io.IOException;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author Faruque Braimo
 */
public class SaidaMain extends Application {
    
   @Override
    public void start(Stage stage) throws IOException {
      
        Parent root = FXMLLoader.load(getClass().getResource("Saida.fxml"));
 
        Scene scene = new Scene(root);
                         stage.initStyle(StageStyle.UNDECORATED);

        stage.setScene(scene);
        stage.setTitle(" Cadastro De Funcionarios");
        stage.setResizable(false);
        stage.show();
    }
    
    
      
    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}