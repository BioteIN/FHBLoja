/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bhf;

import alerta.Alerta;
import com.jfoenix.controls.JFXButton;
import controle.PedidoDAO;
import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import modelo.Cliente;
import modelo.Funcionario;
import modelo.Pedido;

/**
 * FXML Controller class
 *
 * @author bONGANI
 */
public class TabelaPedidoController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private TableView<Pedido> tabelinha;

    @FXML
    private TableColumn<Pedido, Integer> colunaId;

    @FXML
    private TableColumn<Pedido, Funcionario> colunaFuncionario;

    @FXML
    private TableColumn<Pedido, Cliente> colunaCliente;

    @FXML
    private TableColumn<Pedido, Date> colunaData;

    @FXML
    private TableColumn<Pedido, String> colunaEstado;
    @FXML
    private JFXButton btAddPedido;

    @FXML
    private JFXButton btProdd;

    @FXML
    private void adic(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("PedidoFXML.fxml"));

        Stage stage = new Stage();
        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();
    }

 @FXML
    void verProdutos(ActionEvent event) throws IOException {
        if(tabelinha.getSelectionModel().getSelectedItem()!=null){
         ProdutoItemsController.produtos = tabelinha.getSelectionModel().getSelectedItem().getProdutos();
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
    public void bbb(ActionEvent event) throws IOException {
        if (tabelinha.getSelectionModel().getSelectedItem() != null) {
            if (tabelinha.getSelectionModel().getSelectedItem().getStatus().equalsIgnoreCase("finalizado")) {
                Alerta.errar("Pagamento", "Este pedido foi finalizado");
            } else {
                PagamentosController.contas = tabelinha.getSelectionModel().getSelectedItem().getContas();
                Parent root = FXMLLoader.load(getClass().getResource("Pagamentos.fxml"));

                Stage stage = new Stage();
                Scene scene = new Scene(root);

                stage.setScene(scene);
                stage.show();
            }
        } else {
            Alerta.errar("Pagamento", "Nenhum pedido selecionado");
        }
    }

    @FXML
    void refresh(ActionEvent event
    ) {
        listarPedido();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb
    ) {
        // TODO
        colunaCliente.setCellValueFactory(new PropertyValueFactory<>("cliente"));
        colunaId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colunaFuncionario.setCellValueFactory(new PropertyValueFactory<>("usuario"));
        colunaEstado.setCellValueFactory(new PropertyValueFactory<>("status"));
        colunaData.setCellValueFactory(new PropertyValueFactory<>("dataDoPedido"));
        listarPedido();
    }

    public void listarPedido() {
        PedidoDAO dao = new PedidoDAO();
        if (dao.consultar() != null) {
            tabelinha.setItems(FXCollections.observableArrayList(dao.consultar()));
        }
    }

    @FXML
    void pagamento(MouseEvent event) {
        PagamentosController.contas = tabelinha.getSelectionModel().getSelectedItem().getContas();
    }

}
