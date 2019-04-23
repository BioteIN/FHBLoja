/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bhf;

import alerta.Alerta;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import controle.SendMail;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import modelo.Cliente;
import modelo.Funcionario;
import modelo.Pedido;
import modelo.Produto;
import modelo.TipoPagamento;
import modelo.Venda;
import modelo.Vender;

/**
 * FXML Controller class
 *
 * @author bONGANI
 */
public class PedidoFXMLController implements Initializable {
public static Funcionario func = new Funcionario();
    private double preco = 0;
    private double pr;
    Pedido pedido = new Pedido();
    private List<Vender> prods = new ArrayList<>();

    /**
     * Initializes the controller class.
     */
    private ObservableList<Produto> produtos;
    private ObservableList<Cliente> clientes;
    @FXML
    private JFXComboBox<String> jcFunc;

    @FXML
    private JFXComboBox<Cliente> jcCliente;

    @FXML
    private JFXComboBox<Integer> jcEstado;

    @FXML
    private JFXComboBox<String> jcTipo;

    @FXML
    private JFXComboBox<Produto> jcProduto;

    @FXML
    private TextField  txtQuant;

    @FXML
    private JFXTextField txtPrecoUnit;

    @FXML
    private JFXTextField txtPrecoTotal;

    @FXML
    private JFXButton btIncluir;

    @FXML
    private TextField  txttotal;

    @FXML
    private JFXTextArea txtObrs;
    @FXML
    private TableView<Vender> tabela;
    @FXML
    private TableColumn<Vender, Produto> colID;

    @FXML
    private TableColumn<Vender, Integer> colQuantidade;

    @FXML
    private TableColumn<Vender, Double> colPreco;

    @FXML
    private TableColumn<Vender, Double> colPrecTotal;

    @FXML
    void incluir(ActionEvent event) {
        if (txtQuant.getText().isEmpty()) {
            Alerta.MostrarNotificacao("Inclusao do Produto", "Mostre a Quantidade A incluir");
        } else {
            Vender vender = new Vender();
            vender.setProduto((Produto) jcProduto.getValue());
            vender.setQuantidade(Integer.parseInt(txtQuant.getText()));
            vender.setPreco(jcProduto.getValue().getPrecoDeVendaUnit());
            if (jcTipo.getValue().equalsIgnoreCase("Unitario")) {
                vender.setPrecoTotal(Integer.parseInt(txtQuant.getText()) * vender.getPreco());
                preco += Venda.calcularPreco(Integer.parseInt(txtQuant.getText()), prods, false, jcProduto.getValue(),pedido);
                pr = preco;
            } else {
                preco += Venda.calcularPreco(Integer.parseInt(txtQuant.getText()), prods, true, jcProduto.getValue(),pedido);
                pr = preco;
            }
            if (Venda.calcularPreco(Integer.parseInt(txtQuant.getText()), prods, true, jcProduto.getValue(),pedido) != 0) {
                tabela.setItems(add(vender));
            }
            txttotal.setText(String.valueOf(pr));

        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        colID.setCellValueFactory(new PropertyValueFactory<>("produto"));
        colQuantidade.setCellValueFactory(new PropertyValueFactory<>("quantidade"));
        colPreco.setCellValueFactory(new PropertyValueFactory<>("preco"));
        colPrecTotal.setCellValueFactory(new PropertyValueFactory<>("precoTotal"));
        ObservableList<String> opcoes = FXCollections.observableArrayList("Unitario", "Embalagem");
        ObservableList<String> opcs = FXCollections.observableArrayList("A PRAZO", "PARCELAS");
        ObservableList<Integer> opc = FXCollections.observableArrayList(2, 4, 6, 8);
        jcEstado.setItems(opc);
        jcFunc.setItems(opcs);
        jcTipo.setItems(opcoes);
        adicionarProduto();
        adicionarCliente();
    }

    @FXML
    void confirmar(ActionEvent event) {
        if (tabela.getItems() != null) {
            if (jcFunc.getValue().equalsIgnoreCase("a prazo")) {
                pedido.setCliente(jcCliente.getValue());
                pedido.setProdutos(prods);
                pedido.setStatus("Aberto");
                pedido.setDataDoPedido(new Date());
                pedido.setUsuario(func);
                Pedido.incluirAPrazoouParcelas(jcEstado.getValue(), pedido, prods, preco, TipoPagamento.APRAZO);
            }else{
                pedido.setCliente(jcCliente.getValue());
                pedido.setProdutos(prods);
                pedido.setStatus("Aberto");
                pedido.setDataDoPedido(new Date());
                pedido.setUsuario(func);
                Pedido.incluirAPrazoouParcelas(jcEstado.getValue(), pedido, prods, preco, TipoPagamento.PARCELA);
            }
            SendMail.enviar(pedido.getCliente().getEmail(),"Confirmacao do Pedido do Cliente:  "+pedido.getCliente());
        } else {
            Alerta.errar("Pedido", "Impossivel confirmar o pedido sem incluir os produtos");
        }
    }
    @FXML
    void excluir(ActionEvent event) {
        if(tabela.getSelectionModel().getSelectedItem()!=null){
            Vender vender = tabela.getSelectionModel().getSelectedItem();
//            Venda.excluirTabela(vender);
            ObservableList<Vender> vend = tabela.getItems();
            for(Vender ve: vend ){
                if(vender.equals(ve)){
                    vend.remove(ve);
                    tabela.setItems(vend);
                    break;
                }
            }
        }
    }
    public void adicionarProduto() {
        List<Produto> p = new ArrayList<>();
        for (Produto pr : Produto.buscar()) {
            p.add(pr);
        }
        produtos = FXCollections.observableArrayList(p);
        jcProduto.setItems(produtos);
    }

    public void adicionarCliente() {
        List<Cliente> p = new ArrayList<>();
        for (Cliente pr : Cliente.buscar()) {
            p.add(pr);
        }
        clientes = FXCollections.observableArrayList(p);
        jcCliente.setItems(clientes);
    }

    private ObservableList<Vender> add(Vender vender) {
        List<Vender> lista = new ArrayList<>();
        lista = tabela.getItems();
        lista.add(vender);
        return FXCollections.observableArrayList(lista);
    }
}
