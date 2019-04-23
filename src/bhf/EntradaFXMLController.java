/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bhf;

import alerta.Alerta;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfWriter;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import modelo.Cliente;
import modelo.Funcionario;
import modelo.Produto;
import modelo.Venda;
import modelo.Vender;

/**
 * FXML Controller class
 *
 * @author bONGANI
 */
public class EntradaFXMLController implements Initializable {
    public static Funcionario func = new Funcionario();
    private double preco = 0;
    private double pr;
    private int cod;
    private List<Vender> prods = new ArrayList<>();
    /**
     * Initializes the controller class.
     */
    Venda venda = new Venda();
    @FXML
    private JFXComboBox<Cliente> jcbCliente;
    @FXML
    private JFXComboBox<String> jcbTipoVenda;
    @FXML
    private JFXComboBox<Produto> jcbProduto;
    private ObservableList<Produto> produtos;
    private ObservableList<Cliente> clientes;
    @FXML
    private JFXTextField tfQuantidade;
    @FXML
    private TableView<Vender> tabela;
    @FXML
    private Label tfTotal;
    @FXML
    private TableColumn<Vender, Produto> colId;

    @FXML
    private TableColumn<Vender, Integer> colQuantidade;

    @FXML
    private TableColumn<Vender, Double> colPreco;

    @FXML
    private TableColumn<Vender, Double> colPrecTotal;
    @FXML
    private JFXButton btConfirmar;

    @FXML
    public void confirmar(ActionEvent event) throws IOException {
        if (tabela.getItems() != null) {
            venda.setFuncionario(func);
            venda.setCliente(jcbCliente.getValue());
            tfQuantidade.setText("");
            jcbCliente.setSelectionModel(null);
            jcbTipoVenda.setSelectionModel(null);
            Venda.incluirABalcao(preco, venda, prods);
            Parent root = FXMLLoader.load(getClass().getResource("Pagamentos.fxml"));

            Scene scene = new Scene(root);
            Stage stage = new Stage();

            stage.setScene(scene);
            stage.setTitle(" Cadastro De Funcionarios");
            stage.setResizable(false);
            stage.show();
        }
        PagamentosController.venda =venda;
    }

    @FXML
    public void incluir(ActionEvent event) {
        if (tfQuantidade.getText().isEmpty()) {
            Alerta.MostrarNotificacao("Inclusao do Produto", "Mostre a Quantidade A incluir");
        } else {
            Vender vender = new Vender();
            vender.setProduto(jcbProduto.getValue());
            vender.setQuantidade(Integer.parseInt(tfQuantidade.getText()));
            vender.setPreco(jcbProduto.getValue().getPrecoDeVendaUnit());
            if (jcbTipoVenda.getValue().equalsIgnoreCase("Unitario")) {
                vender.setPrecoTotal(Integer.parseInt(tfQuantidade.getText()) * vender.getPreco());
                preco += Venda.calcularPreco(Integer.parseInt(tfQuantidade.getText()), prods, false, jcbProduto.getValue(),venda);
                pr = preco;
            } else {
                preco += Venda.calcularPreco(Integer.parseInt(tfQuantidade.getText()), prods, true, jcbProduto.getValue(),venda);
                pr = preco;
            }
            if (Venda.calcularPreco(Integer.parseInt(tfQuantidade.getText()), prods, true, jcbProduto.getValue(),venda) != 0) {
                tabela.setItems(add(vender));
            }
            tfTotal.setText(String.valueOf(pr));

        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        colId.setCellValueFactory(new PropertyValueFactory<>("produto"));
        colQuantidade.setCellValueFactory(new PropertyValueFactory<>("quantidade"));
        colPreco.setCellValueFactory(new PropertyValueFactory<>("preco"));
        colPrecTotal.setCellValueFactory(new PropertyValueFactory<>("precoTotal"));
        ObservableList<String> opcoes = FXCollections.observableArrayList("Unitario", "Embalagem");
        jcbTipoVenda.setItems(opcoes);
        adicionarProduto();
        adicionarCliente();
    }
    public void adicionarProduto() {
        List<Produto> p = new ArrayList<>();
        for (Produto pr : Produto.buscar()) {
            p.add(pr);
        }
        produtos = FXCollections.observableArrayList(p);
        jcbProduto.setItems(produtos);
    }

    public void adicionarCliente() {
        List<Cliente> p = new ArrayList<>();
        for (Cliente pr : Cliente.buscar()) {
            p.add(pr);
        }
        clientes = FXCollections.observableArrayList(p);
        jcbCliente.setItems(clientes);
    }


    private ObservableList<Vender> add(Vender vender) {
        List<Vender> lista = new ArrayList<>();
        lista = tabela.getItems();
        lista.add(vender);
        return FXCollections.observableArrayList(lista);
    }
}
