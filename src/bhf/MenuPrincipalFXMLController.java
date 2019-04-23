/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bhf;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author bONGANI
 */
public class MenuPrincipalFXMLController implements Initializable {

    /**
     * Initializes the controller class.
     */
     @FXML
    private JFXButton btVenda;

    @FXML
    private JFXButton btPedidos;

    @FXML
    private JFXButton btCliente;

    @FXML
    private JFXButton btFornecedor;

    @FXML
    private JFXButton btProd;

    @FXML
    private JFXButton btEntrar;

    @FXML
    private JFXButton btCaixa;
    @FXML
    private Label dataActual;
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
    void entrar(ActionEvent event) throws IOException{
 Parent root = FXMLLoader.load(getClass().getResource("Entrad.fxml"));
                    Stage stage = new Stage();
                    Scene scene=new Scene(root);
                    stage.setScene(scene);
                    stage.show();
    }

    @FXML
    void fornecer(ActionEvent event) throws IOException{
 Parent root = FXMLLoader.load(getClass().getResource("Fornecedor.fxml"));
                    Stage stage = new Stage();
                    Scene scene=new Scene(root);
                    stage.setScene(scene);
                    stage.show();
    }

    @FXML
    void produtar(ActionEvent event) throws IOException{
 Parent root = FXMLLoader.load(getClass().getResource("prod.fxml"));
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
@FXML
    void relatar(ActionEvent event) throws IOException{
 Parent root = FXMLLoader.load(getClass().getResource("Relatorios.fxml"));
                    Stage stage = new Stage();
                    Scene scene=new Scene(root);
                    stage.setScene(scene);
                    stage.show();
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        Calendar calendario = Calendar.getInstance();
        Date data = new Date();
        dataActual.setText(calendario.getTime().toString());
    }    
    
}
