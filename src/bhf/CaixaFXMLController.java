/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bhf;

import alerta.Alerta;
import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import modelo.Caixa;

/**
 * FXML Controller class
 *
 * @author bONGANI
 */
public class CaixaFXMLController implements Initializable {

    @FXML
    private TableView<Caixa> tabelinha;
    
    @FXML
    private TableColumn<Caixa, Date> colunaHoraFecho;
    @FXML
    private TableColumn<Caixa, Integer> colunaCod;

    @FXML
    private TableColumn<Caixa, Date> colunaAbertura;

    @FXML
    private TableColumn<Caixa, Double> colunaFecho;

    @FXML
    private TableColumn<Caixa, Double> colunaSc;

    @FXML
    private TableColumn<Caixa, Double> colunaEntra;

    @FXML
    private TableColumn<Caixa, Boolean> colunaAberto;

    @FXML
    private TableColumn<Caixa, Double> colunaSaida;

    /**
     * Initializes the controller class.
     */
    @FXML
    void abrirCaixa(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("AbrirCaixa.fxml"));

        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.initStyle(StageStyle.UNDECORATED);

        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void adicionarDinheiro(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("AdicionarDinheiroCaixa.fxml"));

        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.initStyle(StageStyle.UNDECORATED);

        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void fecharCaixa(ActionEvent event) throws IOException {
        if (tabelinha.getSelectionModel().getSelectedItem() != null) {
            FecharCaixaController.valor = tabelinha.getSelectionModel().getSelectedItem();
            Parent root = FXMLLoader.load(getClass().getResource("FecharCaixa.fxml"));

            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } else {
            Alerta.errar("Fechar Caixa", "Selecione o caixa");
        }
    }

    @FXML
    void menuPrincipal(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("PrincipalOhhh.fxml"));

        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void retirarDinheiro(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("RetirarDinheiroCaixa.fxml"));

        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void clicar(MouseEvent event) {
        AdicionarDinheiroCaixaController.caixa = tabelinha.getSelectionModel().getSelectedItem();
        RetirarDinheiroCaixaController.caixa = tabelinha.getSelectionModel().getSelectedItem();

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        colunaCod.setCellValueFactory(new PropertyValueFactory<>("id"));
        colunaAbertura.setCellValueFactory(new PropertyValueFactory<>("dataAbertura"));
        colunaHoraFecho.setCellValueFactory(new PropertyValueFactory<>("dataFecho"));
        colunaAberto.setCellValueFactory(new PropertyValueFactory<>("aberto"));
        colunaEntra.setCellValueFactory(new PropertyValueFactory<>("saldoEntrada"));
        colunaFecho.setCellValueFactory(new PropertyValueFactory<>("saldoFecho"));
        colunaSaida.setCellValueFactory(new PropertyValueFactory<>("valorSaiu"));
        colunaSc.setCellValueFactory(new PropertyValueFactory<>("saldoCaixa"));
        listar();
    }

    public void listar() {
        if (Caixa.buscar() != null) {
            tabelinha.setItems(FXCollections.observableArrayList(Caixa.buscar()));
        }
    }

    public void actualizar() {
        listar();
    }
}
