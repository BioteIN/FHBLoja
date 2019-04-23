/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bhf;

import alerta.Alerta;
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
import javafx.scene.control.Button;
import javafx.stage.Stage;
import modelo.Produto;

/**
 * FXML Controller class
 *
 * @author Faruque Braimo
 */
public class fxml implements Initializable {
    public static boolean es=false;
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

 Parent root = FXMLLoader.load(getClass().getResource("Cliente.fxml"));
                    Stage stage = new Stage();
                    Scene scene=new Scene(root);
                    stage.setScene(scene);
                    stage.show();
    }
    @FXML
    void desenhar(ActionEvent event) throws IOException{

 Parent root = FXMLLoader.load(getClass().getResource("Grafico.fxml"));
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
    void saida(ActionEvent event) throws IOException{

 Parent root = FXMLLoader.load(getClass().getResource("Saida.fxml"));
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

       Parent root = FXMLLoader.load(getClass().getResource("TabelaVenda.fxml"));
                    Stage stage = new Stage();
                    Scene scene=new Scene(root);
                    stage.setScene(scene);
                    stage.show();
    }
    @FXML
    void funcionar(ActionEvent event) throws IOException{

       Parent root = FXMLLoader.load(getClass().getResource("FuncionarioFXML.fxml"));
                    Stage stage = new Stage();
                    Scene scene=new Scene(root);
                    stage.setScene(scene);
                    stage.show();
    }
    @FXML
    void pedir (ActionEvent event) throws IOException{

       Parent root = FXMLLoader.load(getClass().getResource("TabelaPedido.fxml"));
                    Stage stage = new Stage();
                    Scene scene=new Scene(root);
                    stage.setScene(scene);
                    stage.show();
    }  
    @FXML
    void definir(ActionEvent event) throws IOException{

 Parent root = FXMLLoader.load(getClass().getResource("Empresa.fxml"));
         Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }
    
     @FXML
    void relatar (ActionEvent event) throws IOException{

       Parent root = FXMLLoader.load(getClass().getResource("Relatorios.fxml"));
                    Stage stage = new Stage();
                    Scene scene=new Scene(root);
                    stage.setScene(scene);
                    stage.show();
    }
    private void ver(){
        int k=0;
        Date data = new Date();
        for(Produto  pr : Produto.buscar()){
            if(pr.getMarca().compareTo(data)==-1){
                k++;
            }
        }
        if(k>0){
            Alerta.errar("Produtos", "Ha Produtos expirados!");
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
//        ver();
        if(es==true){
        btEstatisticas.setDisable(true);
        btEntradas.setDisable(true);
        btSaidas.setDisable(true);
        btProdutos.setDisable(true);
        btRelatorios.setDisable(true);
        btFornecedores.setDisable(true);
        btFuncionarios.setDisable(true);
        }
    }    

   
}
