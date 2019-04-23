/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bhf;

import alerta.Alerta;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

/**
 *
 * @author Faruque Braimo
 */
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import controle.ClienteDAO;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javax.swing.JOptionPane;
import modelo.Cliente;
import org.controlsfx.validation.Severity;
import org.controlsfx.validation.ValidationSupport;
import org.controlsfx.validation.Validator;

public class ClienteController implements Initializable {

    private static final String EMAIL_PATTERN
            = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    @FXML
    private AnchorPane anchor;

    @FXML
    private Pane pane;

    @FXML
    private GridPane grid;

    @FXML
    private JFXTextField txtNaturalidade;

    @FXML
    private JFXTextField txtNumeroBI;

    @FXML
    private JFXTextField txtTelefone;

    @FXML
    private JFXTextArea txaObs;
    @FXML
    private JFXComboBox<String> JcTipo;
    @FXML
    private JFXDatePicker DataCadastro;

    @FXML
    private JFXDatePicker DataNasc;

    @FXML
    private JFXTextField txtNome;

    @FXML
    private JFXTextField txtApelido;

    @FXML
    private JFXTextField txtEmail;

    @FXML
    private JFXRadioButton rdFemenino;

    @FXML
    private JFXRadioButton rdMasculino;

    @FXML
    private JFXTextField txtEndereco;

    @FXML
    private FontAwesomeIconView btIcon;
    @FXML
    private TableView<Cliente> tabela;

    @FXML
    private TableColumn<Cliente, Integer> colId;

    @FXML
    private TableColumn<Cliente, String> colNome;

    Cliente cliente = new Cliente();
    @FXML
    private TableColumn<Cliente, String> colEndereco;

    @FXML
    private TableColumn<Cliente, Date> colNasc;

    @FXML
    private TableColumn<Cliente, String> colEmail;
    @FXML
    private Button bot;
    @FXML
    private Button btFoto;
    @FXML
    private Button BtInfo;
    @FXML
    private JFXButton btActualizar;

    @FXML
    private void gravar(ActionEvent event) {
 
        
         if(validationcheck()) {
       
        
        if (txtNome.getText().length() > 0 || txtEndereco.getText().length() > 0) {
            Cliente cliente = new Cliente();
            cliente.setCadastroData(new Date());
     
            Date date1 = Date.from(DataNasc.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
            cliente.setNascimento(date1);
            cliente.setEndereco(txtEndereco.getText());
            cliente.setNumeroBI(txtNumeroBI.getText());
            cliente.setNaturalidade(txtNaturalidade.getText());
            cliente.setObservacoes(txaObs.getText());
            cliente.setTelefone1(txtTelefone.getText());
            cliente.setNome(txtNome.getText());
            cliente.setEmail(txtEmail.getText());
            Cliente.incluir(cliente);
            txtEmail.setText("");
            txtEndereco.setText("");
            txtNome.setText("");
            txtNumeroBI.setText("");
            txaObs.setText("");
            txtTelefone.setText("");
            DataNasc.setValue(null);
            txtNaturalidade.setText("");

            tabela.setItems(listarClientes());
        } }else {
          
             Alerta.errar("Campos Invalidos"," Por favor! \n Preencha os campos vazios ou Correctamente");
        }

    } 

    @FXML
    void remover(ActionEvent event) {
        if (tabela.getSelectionModel().getSelectedItem() != null) {
            // Date date1 = Date.from(DataNasc.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
            //cliente.setNascimento(date1);
            cliente.setEndereco(txtEndereco.getText());
            cliente.setNumeroBI(txtNumeroBI.getText());
            cliente.setNaturalidade(txtNaturalidade.getText());
            cliente.setObservacoes(txaObs.getText());
            cliente.setTelefone1(txtTelefone.getText());
            cliente.setNome(txtNome.getText());
            Cliente.excluir(cliente);
            txtEmail.setText("");
            txtEndereco.setText("");
            txtNome.setText("");
            txtNumeroBI.setText("");
            txaObs.setText("");
            txtTelefone.setText("");
            DataNasc.setValue(null);
            txtNaturalidade.setText("");

            tabela.setItems(listarClientes());

        }
    }

    @FXML
    void update(ActionEvent event) {
        if (tabela.getSelectionModel().getSelectedItem() != null) {
            Date date1 = Date.from(DataNasc.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
            cliente.setNascimento(date1);
            cliente.setEndereco(txtEndereco.getText());
            cliente.setNumeroBI(txtNumeroBI.getText());
            cliente.setNaturalidade(txtNaturalidade.getText());
            cliente.setObservacoes(txaObs.getText());
            cliente.setTelefone1(txtTelefone.getText());
            cliente.setNome(txtNome.getText());
            Cliente.alterar(cliente);
            txtEmail.setText("");
            txtEndereco.setText("");
            txtNome.setText("");
            txtNumeroBI.setText("");
            txaObs.setText("");
            txtTelefone.setText("");
            DataNasc.setValue(null);
            txtNaturalidade.setText("");

            tabela.setItems(listarClientes());

        }
    }

    @FXML
    void atualizar(MouseEvent event) {
        cliente = tabela.getSelectionModel().getSelectedItem();
        txtEndereco.setText(cliente.getEndereco());
        DataNasc.getEditor().setText(cliente.getNascimento().toString());
        txtNome.setText(cliente.getNome());
        txtNumeroBI.setText(cliente.getNumeroBI());
        txaObs.setText(cliente.getObservacoes());
        txtTelefone.setText(cliente.getTelefone1());
        txtNaturalidade.setText(cliente.getNaturalidade());
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colNasc.setCellValueFactory(new PropertyValueFactory<>("nascimento"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colEndereco.setCellValueFactory(new PropertyValueFactory<>("endereco"));
        ObservableList<String> opcoes = FXCollections.observableArrayList("B.I.", "Carta de Conducao", "Passaporte", "Cartao do Trabalho", "Outros");
        JcTipo.setItems(opcoes);
        tabela.setItems(listarClientes());
        validators();
    }

    private ObservableList<Cliente> listarClientes() {

        List<Cliente> cli = new ArrayList<Cliente>();
        ClienteDAO dao = new ClienteDAO();

        for (Cliente c : dao.consultar()) {
            if (c.isStatus()) {
                cli.add(c);
            }

        }

        return FXCollections.observableArrayList(cli);

    }

    public void validators() {
        ValidationSupport validation = new ValidationSupport();
        ValidationSupport validation3 = new ValidationSupport();
        validation3.registerValidator(txtTelefone, Validator.createRegexValidator("Number must contain 8(Start with 8)", "[8]{1}[0-9]{8}", Severity.ERROR));
        ValidationSupport validation4 = new ValidationSupport();
        validation4.registerValidator(txtEmail, Validator.createRegexValidator("Provide correct Email", EMAIL_PATTERN, Severity.ERROR));
    }

    private boolean validationcheck() {

        if (!Pattern.matches(EMAIL_PATTERN, txtEmail.getText())) {
            return false;
        }

        if (!Pattern.matches("[8]{1}[0-9]{8}", txtTelefone.getText())) {
            return false;
        }
        return true;
    }
    
    @FXML
    public void removerLogico(ActionEvent ev) {
    if (tabela.getSelectionModel().getSelectedItem() != null) {
        Cliente cli = new Cliente();
        cli=tabela.getSelectionModel().getSelectedItem();
        Cliente.excluir(cliente);
        tabela.setItems(listarClientes());
        
        

        
    }

}

}