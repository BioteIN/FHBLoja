/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bhf;

import alerta.Alerta;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javax.swing.JOptionPane;
import modelo.Entrada;
import modelo.Produto;

/**
 * FXML Controller class
 *
 * @author Faruque Braimo
 */
public class EntradaController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private TextField tfQuantidade;

    @FXML
    private ComboBox<Produto> jcbProduto;

    @FXML
    private ComboBox<String> jcbTipoEntrada;

    @FXML
    private DatePicker dpDataEntrega;

    @FXML
    private TableColumn<Entrada, Produto> colunaProduto;
    @FXML
    private Button btnSalvar;
    @FXML
    private TableView<Entrada> table;
  @FXML
    private TextArea taObs;
    @FXML
    private TableColumn<Entrada, Integer> colunaQuant;

    @FXML
    private TableColumn<Entrada, String> colunaTipo;

    @FXML
    private TableColumn<Entrada, Date> colunData;
    @FXML
    private Button cancel;
    private ObservableList<Produto> produtos;

    @FXML
    public void gravar(ActionEvent event) {
        if (tfQuantidade.getText().isEmpty()) {
           Alerta.errar("Entrada","Preencha o campo das quantidades");
        } else {
            Entrada entrada = new Entrada();
            entrada.setDataDeEntrada(new Date());
            entrada.setProduto(jcbProduto.getValue());
            entrada.setTipoDeEntrada(jcbTipoEntrada.getValue());
            entrada.setObs(taObs.getText());
            Entrada.incluir(entrada, Integer.parseInt(tfQuantidade.getText()));
            table.setItems(preencherTabela());

        }
    }

    @FXML
    public void fecharJanela(MouseEvent event) {
        if (event.getSource() == cancel) {
            System.exit(0);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        adicionarProduto();
        colunaProduto.setCellValueFactory(new PropertyValueFactory<>("produto"));
        colunaQuant.setCellValueFactory(new PropertyValueFactory<>("quantidade"));
        colunaTipo.setCellValueFactory(new PropertyValueFactory<>("tipoDeEntrada"));
        colunData.setCellValueFactory(new PropertyValueFactory<>("dataDeEntrada"));
        ObservableList<String> options = FXCollections.observableArrayList("Compra", "Devolucao","Ajuste de Estoque");
        jcbTipoEntrada.setItems(options);
        table.setItems(preencherTabela());
    }

    public void adicionarProduto() {
        List<Produto> p = new ArrayList<>();
        for (Produto pr : Produto.buscar()) {
            p.add(pr);
        }
        produtos = FXCollections.observableArrayList(p);
        jcbProduto.setItems(produtos);
    }

    public ObservableList<Entrada> preencherTabela() {

        return FXCollections.observableArrayList(Entrada.buscar());

    }
}
