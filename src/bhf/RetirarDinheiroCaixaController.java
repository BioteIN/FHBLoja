/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bhf;

import alerta.Alerta;
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
public class RetirarDinheiroCaixaController implements Initializable {
     public static Caixa caixa = new Caixa();
         @FXML
    private TextField valorActual;

    @FXML
    private TextField txtValorRem;

    @FXML
    void retirar(ActionEvent event) {
        Caixa.retirar(Double.parseDouble(txtValorRem.getText()), caixa);
        Alerta.MostrarNotificacao("Retirar Valor", "O valor foi retirado do caixa");
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        valorActual.setText(String.valueOf(caixa.getSaldoCaixa()));
    }    
    
}
