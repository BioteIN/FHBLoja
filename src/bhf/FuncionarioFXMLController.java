/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bhf;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import modelo.Cliente;
import modelo.Funcionario;

/**
 * FXML Controller class
 *
 * @author bONGANI
 */
public class FuncionarioFXMLController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
     @FXML
    private TableColumn<Funcionario, Integer> colID;

    @FXML
    private TableColumn<Funcionario, String> colNome;

    @FXML
    private TableColumn<Funcionario, String> colEndereco;

    @FXML
    private TableColumn<Funcionario, String> colNuit;

    @FXML
    private TableColumn<Funcionario, Date> colData;

    @FXML
    private TableColumn<Funcionario, String> colTipo;

    @FXML
    private TableColumn<Funcionario, String> colEmail;

    @FXML
    private TableColumn<Funcionario, String> colTelefone;

    @FXML
    private TableColumn<Funcionario, String> colUsuario;

    @FXML
    private TableColumn<Funcionario, String> colSenha;

    @FXML
    private JFXTextField txtNome;

    @FXML
    private JFXDatePicker dtNascimento;

    @FXML
    private JFXTextField txtEndereco;

    @FXML
    private JFXTextField txtNuit;

//    @FXML
//    private JFXComboBox<?> jcTipo;

    @FXML
    private JFXTextField txtEmail;

    @FXML
    private JFXTextField txttelefone;

    @FXML
    private JFXTextField txtUsuario;

    @FXML
    private JFXTextField txtSenha;

    @FXML
    private JFXTextArea txaObrs;
    Funcionario cliente= new Funcionario();
    @FXML
    private Button bot;
    
      @FXML
    private TableView<Funcionario> tabela;


    @FXML
   private void gravar(ActionEvent event) {
   
        
            if(txtNome.getText().length()>0 || txtEndereco.getText().length()>0){    
            Funcionario func = new Funcionario();
            func.setDataContratacao(new Date());
            
            Date date1 = Date.from(dtNascimento.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
            func.setDataNascimento(date1);
            func.setEndereco(txtEndereco.getText());
            func.setEmail(txtEmail.getText());
            func.setNuit(txtNuit.getText());
            func.setObservacoes(txaObrs.getText());
            func.setTelefone(txttelefone.getText());
            func.setNome(txtNome.getText());
            func.setLogin(txtUsuario.getText());
            func.setSenha(txtSenha.getText());
            func.incluir(func);
            txtEmail.setText("");
            txtEndereco.setText("");
            txtNome.setText("");
            txtNuit.setText("");
            txaObrs.setText("");
            txttelefone.setText("");
            dtNascimento.setValue(null);
            txtSenha.setText("");
            txtUsuario.setText("");
            tabela.setItems(listarFuncionarios());
            }else{
                Alert alerta = new Alert(Alert.AlertType.WARNING);
                alerta.setTitle("Prencher");
                alerta.setContentText("Preencher os campos obrigatorios");
                alerta.show();
            }

    }

      @FXML
    void actualizar(ActionEvent event) {
 if(tabela.getSelectionModel().getSelectedItem()!=null){
                Date date1 = Date.from(dtNascimento.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
            cliente.setDataNascimento(date1);
            cliente.setEndereco(txtEndereco.getText());
            cliente.setNuit(txtNuit.getText());
            cliente.setEmail(txtEmail.getText());
            cliente.setObservacoes(txaObrs.getText());
            cliente.setTelefone(txttelefone.getText());
            cliente.setNome(txtNome.getText());
            Funcionario.alterar(cliente);
            txtEmail.setText("");
            txtEndereco.setText("");
            txtNome.setText("");
            txtNuit.setText("");
            txaObrs.setText("");
            txttelefone.setText("");
            dtNascimento.setValue(null);
           txtUsuario.setText("");
           txtSenha.setText("");

            tabela.setItems(listarFuncionarios());
        
        }
    }
 @FXML
    private void actualizar01(MouseEvent event) {
  cliente = tabela.getSelectionModel().getSelectedItem();
        txtEndereco.setText(cliente.getEndereco());
        txtNome.setText(cliente.getNome());
        txtEmail.setText(cliente.getEmail());
        txaObrs.setText(cliente.getObservacoes());
        txttelefone.setText(cliente.getTelefone());
        dtNascimento.setValue(null);
        txtUsuario.setText(cliente.getLogin());
        txtSenha.setText(cliente.getSenha());
    }
 
    
    
       @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        colID.setCellValueFactory(new PropertyValueFactory<>("id"));
        colNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colData.setCellValueFactory(new PropertyValueFactory<>("dataNascimento"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colEndereco.setCellValueFactory(new PropertyValueFactory<>("endereco"));
        colNuit.setCellValueFactory(new PropertyValueFactory<>("nuit"));
        colSenha.setCellValueFactory(new PropertyValueFactory<>("senha"));
        colUsuario.setCellValueFactory(new PropertyValueFactory<>("login"));
        colTipo.setCellValueFactory(new PropertyValueFactory<>("tipo"));
        colTelefone.setCellValueFactory(new PropertyValueFactory<>("telefone"));
//        tabela.setItems(listarFuncionarios());
    }    
    private ObservableList<Funcionario> listarFuncionarios(){
        return FXCollections.observableArrayList(Funcionario.buscar());
    }
}

