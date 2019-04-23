/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bhf;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import modelo.Empresa;

/**
 * FXML Controller class
 *
 * @author bONGANI
 */
public class EmpresaController implements Initializable {

    /**
     * Initializes the controller class.
     */
     @FXML
    private TextField nome;

    @FXML
    private TextField telefone;

    @FXML
    private TextField usuario;

    @FXML
    private TextField email;

    @FXML
    private TextField senha;

    @FXML
    private TextField senha1;

    @FXML
    private JFXButton btGuardar;

    @FXML
    void gravar(ActionEvent event) {
 if (nome.getText().length() > 0 || telefone.getText().length() > 0) {
            Empresa cliente = new Empresa();
           // cliente.setCadastroData(new Date());

            //Date date1 = Date.from(DataNasc.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
            cliente.setNome(nome.getText());
            cliente.setEmail(email.getText());
            cliente.setNomeAdmin(usuario.getText());
            cliente.setSenhaAdmin(senha.getText());
            //cliente.setObservacoes(txaObs.getText());
            cliente.setTelefone(telefone.getText());
            Empresa.incluir(cliente);
            email.setText("");
            usuario.setText("");
            nome.setText("");
            senha.setText("");
            senha1.setText("");
            telefone.setText("");
            
        } else {
            Alert alerta = new Alert(Alert.AlertType.WARNING);
            alerta.setTitle("Prencher");
            alerta.setContentText("Preencher os campos obrigatorios");
            alerta.show();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
