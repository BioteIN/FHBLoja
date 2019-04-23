/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bhf;

import alerta.Alerta;
import com.jfoenix.controls.JFXTextArea;
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
import javafx.geometry.Point2D;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import modelo.Entrada;
import modelo.Produto;
import modelo.Saida;

/**
 * FXML Controller class
 *
 * @author Faruque Braimo
 */
public class SaidaController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private TextField txtQuant;

    @FXML
    private ComboBox<Produto> jcbProduto;
    @FXML
    private ComboBox<String> jcbMotivo;
    @FXML
    private JFXTextArea taObs;
    @FXML
    private Button btSalvar;

    @FXML
    private Button cancel;
    @FXML
    private TableView<Saida> table;

    @FXML
    private TableColumn<Saida, String> colMotivo;
    @FXML
    private TableColumn<Saida, Produto> colProd;

    @FXML
    private TableColumn<Saida, Integer> colQuant;

    @FXML
    private TableColumn<Saida, Date> colDataSaida;

    private ObservableList<Produto> produtos;

    @FXML
    public void fecharJanela(MouseEvent event) {
        if (event.getSource() == cancel) {
            System.exit(0);
        }
    }

    @FXML
    public void gravar(ActionEvent event) {
        if (txtQuant.getText().isEmpty()) {
            Alerta.errar("Saida","Preencha o campo das quantidades");
        } else {
            Saida saida = new Saida();
            saida.setObs(taObs.getText());
            saida.setMotivo(jcbMotivo.getValue());
            saida.setDataDeSaida(new Date());
            saida.setProduto((Produto) jcbProduto.getValue());
            saida.incluir(saida, Integer.parseInt(txtQuant.getText()));
            table.setItems(preencherTabela());

        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        adicionarProduto();
        colProd.setCellValueFactory(new PropertyValueFactory<>("produto"));
        colQuant.setCellValueFactory(new PropertyValueFactory<>("quantidade"));
        colDataSaida.setCellValueFactory(new PropertyValueFactory<>("dataDeSaida"));
        colMotivo.setCellValueFactory(new PropertyValueFactory<>("motivo"));
         ObservableList<String> opcoes = FXCollections.observableArrayList("Roubo", "Ajuste de Estoque","Quebra","Deterioracao");
         jcbMotivo.setItems(opcoes);
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

    public ObservableList<Saida> preencherTabela() {

        return FXCollections.observableArrayList(Saida.buscar());

    }
}
