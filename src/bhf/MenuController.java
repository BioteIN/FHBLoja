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
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author bONGANI
 */
public class MenuController implements Initializable {
 @FXML
    private Button btnDashboard;
    @FXML
    private Button btClientes;
    @FXML
    private Button btFornecedores;
    @FXML
    private Button btEntradas;
    @FXML
    private Button btCaixa;
    @FXML
    private Button btVendas;
    @FXML
    private Button btPedidos;
    @FXML
    private Button btRelatorios;
    @FXML
    private Button btnClasses3;
    @FXML
    private Button btProdutos;
    @FXML
    private Button btEstatisticas;
    @FXML
    private Button btSaidas;
    @FXML
    private Button btDefinicoes;
    @FXML
    private Button btFuncionarios;

    private  void fecharJanela() {
        
        ((Stage) btFuncionarios.getScene().getWindow()).close();
        
    }
    

    
    
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
                fecharJanela();

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
        Scene scene = new Scene(root);
        stage.setResizable(false);
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
