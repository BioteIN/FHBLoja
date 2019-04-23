/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bhf;

import java.net.URL;
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
public class FecharCaixaController implements Initializable {
    public static Caixa valor=null ;
        @FXML
    private TextField txtValorTotal;

    @FXML
    void fechar(ActionEvent event) {
        valor.setSaldoFecho(valor.getSaldoCaixa());
        Caixa.fechar(valor);
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        txtValorTotal.setText(String.valueOf(valor.getSaldoCaixa()));
    }    
    
}
