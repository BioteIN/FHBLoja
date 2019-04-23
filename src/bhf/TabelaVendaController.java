/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bhf;

import alerta.Alerta;
import com.jfoenix.controls.JFXButton;
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
import javafx.stage.Stage;
import modelo.Cliente;
import modelo.Funcionario;
import modelo.Venda;

/**
 * FXML Controller class
 *
 * @author bONGANI
 */
public class TabelaVendaController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private JFXButton btAddVenda;
    @FXML
    private TableView<Venda> tabela;

    @FXML
    private TableColumn<Venda, Integer> colCod;

    @FXML
    private TableColumn<Venda, Funcionario> colFunc;

    @FXML
    private TableColumn<Venda, Cliente> colCliente;

    @FXML
    private TableColumn<Venda, Date> colData;

    @FXML
    private TableColumn<Venda, Boolean> colEstado;

    @FXML
    void adicionar(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("EntradaFXML.fxml"));

        Stage stage = new Stage();
        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        colCliente.setCellValueFactory(new PropertyValueFactory<>("cliente"));
        colCod.setCellValueFactory(new PropertyValueFactory<>("id"));
        colData.setCellValueFactory(new PropertyValueFactory<>("dataVenda"));
        colFunc.setCellValueFactory(new PropertyValueFactory<>("funcionario"));
        colEstado.setCellValueFactory(new PropertyValueFactory<>("status"));
        buscarVendas();
    }

    @FXML
    void verProdutos(ActionEvent event) throws IOException {
        if(tabela.getSelectionModel().getSelectedItem()!=null){
         ProdutoItemsController.produtos = tabela.getSelectionModel().getSelectedItem().getProdutos();
        Parent root = FXMLLoader.load(getClass().getResource("ProdutoItems.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        } else{
            Alerta.errar("Vendas","Selecione uma venda na tabela.");
        }
    }

    @FXML
    void atualizar(ActionEvent event) {
     buscarVendas();
    }

    private void buscarVendas() {
        if(Venda.buscar()!=null){
            tabela.setItems(FXCollections.observableArrayList(Venda.buscar()));
        }
    }
}
