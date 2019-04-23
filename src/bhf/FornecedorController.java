/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bhf;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import modelo.Cliente;
import modelo.Fornecedor;
import modelo.Funcionario;
import modelo.Produto;

/**
 * FXML Controller class
 *
 * @author Faruque Braimo
 */
public class FornecedorController implements Initializable {
    Fornecedor fornecedor = new  Fornecedor();
    @FXML
    private TableView<Fornecedor> tabela;

    @FXML
    private TableColumn<Fornecedor, Integer> colID;

    @FXML
    private TableColumn<Fornecedor, String> colNome;

    @FXML
    private TableColumn<Fornecedor, String> colEmail;

    @FXML
    private TableColumn<Fornecedor, String> colEndereco;

    @FXML
    private TableColumn<Fornecedor, String> colRepresentante;

    @FXML
    private TableColumn<Fornecedor, String> colConta;

    @FXML
    private TableColumn<Fornecedor, String> colNuit;

    @FXML
    private TableColumn<Fornecedor, String> colSlogan;

    @FXML
    private TableColumn<Fornecedor, String> colTel;

    @FXML
    private JFXTextField txtNome;

    @FXML
    private JFXTextField txtEndereco;

    @FXML
    private JFXTextField txtRepresentante;

    @FXML
    private JFXTextField txtConta;

    @FXML
    private JFXTextField txtNuit;

    @FXML
    private JFXTextField txtSlogan;

    @FXML
    private JFXTextField txtTelefone;

    @FXML
    private JFXTextField txtFax;

    @FXML
    private JFXTextField txtEmail;

    @FXML
    private JFXTextArea taObrs;

    @FXML
    private JFXButton btSalvar;

    @FXML
    void actualizar(MouseEvent event) {
        fornecedor = tabela.getSelectionModel().getSelectedItem();
        txtConta.setText(fornecedor.getContaCorrente());
        txtRepresentante.setText(fornecedor.getRepresentante());
        txtFax.setText(fornecedor.getFax());
        txtNome.setText(fornecedor.getNome());
        txtTelefone.setText(fornecedor.getTelefone());
        txtNuit.setText(fornecedor.getNuit());
        txtEmail.setText(fornecedor.getTelefone2());
        txtSlogan.setText(fornecedor.getSlogan());
        taObrs.setText(fornecedor.getObservacoes());
        txtEndereco.setText(fornecedor.getEndereco());
    }
    @FXML
    void update(MouseEvent event) {
         if (txtNome.getText().length() > 0 || txtEndereco.getText().length() > 0) {
             Fornecedor forn = new Fornecedor();
            forn.setDataCadastro(new Date());

            forn.setContaCorrente(txtConta.getText());
            forn.setRepresentante(txtRepresentante.getText());
            forn.setEndereco(txtEndereco.getText());
            forn.setFax(txtFax.getText());
            forn.setNome(txtNome.getText());
            forn.setTelefone(txtTelefone.getText());
            forn.setNuit(txtNuit.getText());
            forn.setObservacoes(taObrs.getText());
            forn.setTelefone2(txtEmail.getText());
            forn.setSlogan(txtSlogan.getText());
            Fornecedor.alterar(forn);
            txtEmail.setText("");
            txtEndereco.setText("");
            txtNome.setText("");
            txtNuit.setText("");
            taObrs.setText("");
            txtTelefone.setText("");
            txtConta.setText("");
            txtRepresentante.setText("");
            txtEmail.setText("");

            tabela.setItems(listarFornecedor());
        } else {
            Alert alerta = new Alert(Alert.AlertType.WARNING);
            alerta.setTitle("Prencher");
            alerta.setContentText("Preencher os campos obrigatorios");
            alerta.show();
        }

    }

    @FXML
    void gravar(ActionEvent event) {
        if (txtNome.getText().length() > 0 || txtEndereco.getText().length() > 0) {
            Fornecedor forn = new Fornecedor();
            forn.setDataCadastro(new Date());

            forn.setContaCorrente(txtConta.getText());
            forn.setRepresentante(txtRepresentante.getText());
            forn.setEndereco(txtEndereco.getText());
            forn.setFax(txtFax.getText());
            forn.setNome(txtNome.getText());
            forn.setTelefone(txtTelefone.getText());
            forn.setNuit(txtNuit.getText());
            forn.setObservacoes(taObrs.getText());
            forn.setTelefone2(txtEmail.getText());
            forn.setSlogan(txtSlogan.getText());
            Fornecedor.incluir(forn);
            txtEmail.setText("");
            txtEndereco.setText("");
            txtNome.setText("");
            txtNuit.setText("");
            taObrs.setText("");
            txtTelefone.setText("");
            txtConta.setText("");
            txtRepresentante.setText("");
            txtEmail.setText("");

            tabela.setItems(listarFornecedor());
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
        colID.setCellValueFactory(new PropertyValueFactory<>("id"));
        colNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colConta.setCellValueFactory(new PropertyValueFactory<>("contaCorrente"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("telefone2"));
        colTel.setCellValueFactory(new PropertyValueFactory<>("telefone"));
        colEndereco.setCellValueFactory(new PropertyValueFactory<>("endereco"));
        colNuit.setCellValueFactory(new PropertyValueFactory<>("nuit"));
        colSlogan.setCellValueFactory(new PropertyValueFactory<>("slogan"));
        colRepresentante.setCellValueFactory(new PropertyValueFactory<>("representante"));
        tabela.setItems(listarFornecedor());
    }

    private ObservableList<Fornecedor> listarFornecedor() {
        return FXCollections.observableArrayList(Fornecedor.buscar());
    }
}
