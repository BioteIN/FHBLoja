/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bhf;

import alerta.Alerta;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfWriter;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import controle.EmpresaDAO;
import controle.FuncionarioDAO;
import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import modelo.Empresa;
import modelo.Funcionario;
import modelo.Venda;
import modelo.Vender;


/**
 *
 * @author bONGANI
 */
public class LoginFXMLController implements Initializable {
    Empresa cliente=new Empresa();
    Funcionario funcionario = new Funcionario();
    @FXML
    private JFXButton btSair;
  @FXML
    private JFXTextField usuario;

    @FXML
    private JFXPasswordField senha;

    @FXML
    private JFXButton btEntrar;
    
     @FXML
    private JFXButton btRegistar;

   

    @FXML
    void registar(ActionEvent event) throws IOException {
 Parent root = FXMLLoader.load(getClass().getResource("Empresa.fxml"));
                    Stage stage = new Stage();
                    Scene scene=new Scene(root);
                    stage.setScene(scene);
                    stage.show();
    }

    
    private  void fecharJanela() {
        
        ((Stage) senha.getScene().getWindow()).close();
        
    }

 @FXML
    void entrar01(ActionEvent event) throws IOException{
             EmpresaDAO dao = new EmpresaDAO();
             if(dao.consultar()!=null){
            cliente.setNomeAdmin(usuario.getText());
            cliente.setSenhaAdmin(senha.getText());
            funcionario.setLogin(usuario.getText());
            funcionario.setSenha(senha.getText());


             FuncionarioDAO dao1 = new FuncionarioDAO();
              int k=0; 
              int r=0;
             if(dao.consultar()!=null){
            for ( Empresa cli :  dao.consultar()) {
             if(( cliente.getSenhaAdmin().equals(cli.getSenhaAdmin()) && cliente.getNomeAdmin().equalsIgnoreCase(cli.getNomeAdmin()))) k++; 
            }
             if ( k==0) {

             if(dao1.consultar()!=null){
            for ( Funcionario func :  dao1.consultar()) {
             if(( funcionario.getLogin().equals(func.getLogin()) && funcionario.getSenha().equalsIgnoreCase(func.getSenha()))){ 
                 EntradaFXMLController.func =func;
                 PagamentosController.func =func;
                 PedidoFXMLController.func =func;
                r++; 
             }
            }
             if ( r==0) {

                 Alert alerta = new Alert(Alert.AlertType.ERROR);
                alerta.setTitle("Feito");
                alerta.setContentText("Senha ou Usuario Incorrecto!");
                alerta.show();


             }else{
                 fecharJanela01();
                fxml.es=true;
                Parent root = FXMLLoader.load(getClass().getResource("PrincipalOhhh.fxml"));

            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
               }



             }


    //             Alert alerta = new Alert(Alert.AlertType.ERROR);
    //            alerta.setTitle("Feito");
    //            alerta.setContentText("Senha ou Usuario Incorrecto!");
    //            alerta.show();


             }else{
                 fecharJanela01();
                Parent root = FXMLLoader.load(getClass().getResource("PrincipalOhhh.fxml"));

            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
               }



             }
    }else{
                 Alerta.errar("Empresa","Registe a sua empresa primeiro.");
             }
    }
     private  void fecharJanela01() {
        
        ((Stage) btEntrar.getScene().getWindow()).close();
        
    }
    
    @FXML
    private void sair(ActionEvent event) {
     System.exit(0);
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
       EmpresaDAO dao = new EmpresaDAO();
       
      
                if(dao.consultar()!=null){
                    btRegistar.setVisible(false);
                }

       
       
       
        
    }    
    
}
