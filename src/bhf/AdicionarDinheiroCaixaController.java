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
public class AdicionarDinheiroCaixaController implements Initializable {
    @FXML
    private TextField txtValorAtual;

    @FXML
    private TextField txtValorAdd;
    public static Caixa caixa = new Caixa();
    @FXML
    void adicionar(ActionEvent event) {
        Caixa.adicionar(Double.parseDouble(txtValorAdd.getText()),Double.parseDouble(txtValorAdd.getText()),caixa);
        Alerta.MostrarNotificacao("Adicionar dinheiro do Caixa", "O Dinheiro foi adicionado com sucesso!!!");
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        txtValorAtual.setText(String.valueOf(caixa.getSaldoCaixa()));
    }    
    
}
