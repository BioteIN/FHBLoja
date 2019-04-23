/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bhf;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import modelo.Caixa;

/**
 * FXML Controller class
 *
 * @author bONGANI
 */
public class AbrirCaixaController implements Initializable {

    @FXML
    private JFXButton btnAbrir;
    @FXML
    private TextField txtSaldoInicial;

    @FXML
    void abrir(ActionEvent event) {
        if(txtSaldoInicial.getText().isEmpty()){
            
        }else{
            Caixa caixa = new Caixa();
            caixa.setDataAbertura(new Date());
            caixa.setSaldoCaixa(Double.parseDouble(txtSaldoInicial.getText()));
            Caixa.abrir(caixa, Double.parseDouble(txtSaldoInicial.getText()));
           
        }
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
