/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bhf;

import alerta.Alerta;
import com.jfoenix.controls.JFXDatePicker;
import java.applet.AudioClip;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.Duration;
import modelo.Produto;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author Faruque Braimo
 */
public class AddProdutosController implements Initializable {

    public static Produto produto = null;
    @FXML
    private TextField txtNome;

    @FXML
    private TextField txtPeso;
    @FXML
    private TextField txtEstoqueFisico;
    @FXML
    private TextField txtEstoqueMinimo;
    @FXML
    private JFXDatePicker txtMarca;
    @FXML
    private TextField txtPorUnidade;
    @FXML
    private TextField txtTotal;
    @FXML
    private TextField txtQuantidade;
    @FXML
    private TextArea txtArea;
    @FXML
    private Button btAdd;
    @FXML
    private Button btCancel;

    /**
     * Initializes the controller class.
     */
    private void fecharJanela() {

        ((Stage) btAdd.getScene().getWindow()).close();

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if (produto != null) {
            txtEstoqueFisico.setText(String.valueOf(produto.getEstoqueFisico()));
            txtEstoqueMinimo.setText(String.valueOf(produto.getEstoqueMinimo()));
            txtMarca.getEditor().setText(produto.getMarca().toString());
            txtNome.setText(produto.getNome());
            txtPeso.setText(String.valueOf(produto.getPeso()));
            txtPorUnidade.setText(String.valueOf(produto.getPrecoDeVendaUnit()));
            txtTotal.setText(String.valueOf(produto.getPrecoDeVendaTotal()));
            txtQuantidade.setText(String.valueOf(produto.getQuantidadeEmbalagem()));
        }
    }

    public void cancelar(ActionEvent e) throws IOException {

        fecharJanela();

    }

    @FXML
    private void Adiconar(ActionEvent event) {
        Produto produto = new Produto();
        if (txtNome.getText().isEmpty() || txtTotal.getText().isEmpty() || txtEstoqueMinimo.getText().isEmpty() || txtEstoqueFisico.getText().isEmpty() || txtMarca.getValue() == null || txtPeso.getText().isEmpty() || txtQuantidade.getText().isEmpty()) {
            File audio = new File("Music/Notify.wav");
            Alerta.AlertaSimples(audio);
            TrayNotification tray = new TrayNotification();
            tray.setNotificationType(NotificationType.ERROR);
            tray.setTitle(" CAMPOS NAO PREENCHIDOS ");
            tray.setMessage(" Preencha os camps e tente novamente!");
            tray.setAnimationType(AnimationType.SLIDE);
            tray.showAndDismiss(Duration.millis(4000));
        } else {
            produto.setQuantidadeEmbalagem(Integer.parseInt(txtQuantidade.getText()));
            produto.setPrecoDeVendaUnit(Double.parseDouble(txtPorUnidade.getText()));
            double dd = Integer.parseInt(txtQuantidade.getText()) * produto.getPrecoDeVendaUnit();
            if (Double.parseDouble(txtTotal.getText()) <= dd) {
                produto.setNome(txtNome.getText());
                produto.setPrecoDeVendaTotal(Double.parseDouble(txtTotal.getText()));
                produto.setEstoqueFisico(Integer.parseInt(txtEstoqueFisico.getText()));
                produto.setEstoqueMinimo(Integer.parseInt(txtEstoqueMinimo.getText()));
                produto.setDataCompra(new Date());
                produto.setPeso(Double.parseDouble(txtPeso.getText()));
                Date date1 = Date.from(txtMarca.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
                produto.setMarca(date1);
                produto.incluir(produto);

                TrayNotification tray1 = new TrayNotification();
                tray1.setNotificationType(NotificationType.SUCCESS);
                tray1.setTitle(" EXITO ");
                tray1.setMessage(" Produto Cadastrado com Sucesso");
                tray1.setAnimationType(AnimationType.SLIDE);
                tray1.showAndDismiss(Duration.millis(3000));

                fecharJanela();
            } else {
                Alerta.errar("Adicionar Produto", "Preco de venda por embalagem nao pode ser maior do que " + dd);
            }
        }
    }

    @FXML

    public void editar() {

        Produto produto = new Produto();
        produto.setQuantidadeEmbalagem(Integer.parseInt(txtQuantidade.getText()));
        produto.setPrecoDeVendaUnit(Double.parseDouble(txtPorUnidade.getText()));
        if (txtNome.getText().isEmpty() || txtTotal.getText().isEmpty() || txtEstoqueMinimo.getText().isEmpty() || txtEstoqueFisico.getText().isEmpty() || txtMarca.getValue() == null || txtPeso.getText().isEmpty() || txtQuantidade.getText().isEmpty()) {
            File audio = new File("Music/Notify.wav");
            Alerta.AlertaSimples(audio);
            TrayNotification tray = new TrayNotification();
            tray.setNotificationType(NotificationType.ERROR);
            tray.setTitle(" CAMPOS NAO PREENCHIDOS ");
            tray.setMessage(" Preencha os camps e tente novamente!");
            tray.setAnimationType(AnimationType.SLIDE);
            tray.showAndDismiss(Duration.millis(4000));
        } else {
            double dd = Integer.parseInt(txtQuantidade.getText()) * produto.getPrecoDeVendaUnit();
            if (Double.parseDouble(txtTotal.getText()) <= dd) {
                produto.setNome(txtNome.getText());
                produto.setPrecoDeVendaTotal(Double.parseDouble(txtTotal.getText()));
                produto.setEstoqueFisico(Integer.parseInt(txtEstoqueFisico.getText()));
                produto.setEstoqueMinimo(Integer.parseInt(txtEstoqueMinimo.getText()));
                produto.setDataCompra(new Date());
                produto.setPeso(Double.parseDouble(txtPeso.getText()));
                Date date1 = Date.from(txtMarca.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
                produto.setMarca(date1);
                produto.actualizar(produto);

                TrayNotification tray1 = new TrayNotification();
                tray1.setNotificationType(NotificationType.SUCCESS);
                tray1.setTitle(" EXITO ");
                tray1.setMessage(" Produto Cadastrado com Sucesso");
                tray1.setAnimationType(AnimationType.SLIDE);
                tray1.showAndDismiss(Duration.millis(3000));

                fecharJanela();
            } else {
                Alerta.errar("Adicionar Produto", "Preco de venda por embalagem nao pode ser maior do que " + dd);
            }
        }

    }

}
