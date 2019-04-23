/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bhf;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import controle.ProdutoDAO;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;
import javax.swing.table.TableRowSorter;
import modelo.Cliente;
import modelo.Produto;

/**
 * FXML Controller class
 *
 * @author bONGANI
 */
public class ProdController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private JFXButton btActualizar;

    @FXML
    private Pane pane;

    private JFXTextField txtNome;

    @FXML
    private AnchorPane anchor;

    @FXML
    private TableView<Produto> table;

    @FXML
    private TableColumn<Produto, String> colDescricao;

    @FXML
    private Button btEditar;

    @FXML
    private Button btRemover;

    @FXML
    private Button btAdicionar;
    @FXML
    private TableColumn<Produto, Integer> colId;
    @FXML
    private TableColumn<Produto, String> colNome;
    @FXML
    private TableColumn<Produto, String> colMarca;
//    @FXML
//    private TableColumn<Produto, String> ColFornecedor;
    @FXML
    private TableColumn<Produto, Double> colPrecoUnitario;
    @FXML
    private TableColumn<Produto, Double> colPrecoTotal;
    @FXML
    private TableColumn<Produto, Date> colDataCompra;
    @FXML
    private TableColumn<Produto, Integer> colEstoqueFisico;
    @FXML
    private TableColumn<Produto, Integer> colEstoqiueMinimo;
      @FXML
    private TextField txtPesquisa;


    
    final ObservableList<Produto> obsPro = FXCollections.observableArrayList();
    
    
    

     private  void fecharJanela() {
        
        ((Stage) btActualizar.getScene().getWindow()).close();
        
    }
    
     
     Tooltip tip = new Tooltip(" Adicionar  um Produto");
     Tooltip tip2 = new Tooltip(" Actualizar a tabela de produtos");
     Tooltip tip3 = new Tooltip(" Editar o Produto");
     Tooltip tip4 = new Tooltip(" Remover o  Produto");
    
    
    @FXML

    public void gravar() throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("AddProdutos.fxml"));

        Stage stage = new Stage();
        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();

    }

    @FXML

    public void editar() throws IOException {

        AddProdutosController.produto = new Produto();
//      AddProdutosController.produto = colId.getCellObservableValue(table);

//     Parent root = FXMLLoader.load(getClass().getResource("AddProdutos.fxml"));
//        
//     
//     Stage stage = new Stage();
//        Scene scene = new Scene(root);
//
//        stage.setScene(scene);
//        stage.show();           
    }

    public void fechar(ActionEvent event) {
        animacao();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
        procurarProduto();
        filtro = new FilteredList<>(obsPro, e->  true);
        
        btAdicionar.setTooltip(tip);
        btActualizar.setTooltip(tip2);
        btEditar.setTooltip(tip3);
        btRemover.setTooltip(tip4);

        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colMarca.setCellValueFactory(new PropertyValueFactory<>("marca"));
        colPrecoTotal.setCellValueFactory(new PropertyValueFactory<>("precoDeVendaTotal"));
        colPrecoUnitario.setCellValueFactory(new PropertyValueFactory<>("precoDeVendaUnit"));
        colEstoqiueMinimo.setCellValueFactory(new PropertyValueFactory<>("estoqueMinimo"));
        colEstoqueFisico.setCellValueFactory(new PropertyValueFactory<>("estoqueFisico"));
        colDataCompra.setCellValueFactory(new PropertyValueFactory<>("DataCompra"));
        colDescricao.setCellValueFactory(new PropertyValueFactory<>("descricao"));

        table.setItems(ListarProduto());

    }

    private ObservableList<Produto> ListarProduto() {
        
        List<Produto> list = new ArrayList<Produto>();
        ProdutoDAO dao = new ProdutoDAO();
       for(Produto d : dao.consultar()){
           if(d.isStatus()){
               
              list.add(d);
           }
           
       }
        
        return FXCollections.observableArrayList(list);
    }

    public void actuualizar(ActionEvent evente) {

        table.setItems(ListarProduto());

    }

    @FXML
    void atualizar(ActionEvent event) throws IOException {
        if (table.getSelectionModel().getSelectedItem() != null) {
            AddProdutosController.produto = table.getSelectionModel().getSelectedItem();
            Parent root = FXMLLoader.load(getClass().getResource("AddProdutos.fxml"));

            Stage stage = new Stage();
            Scene scene = new Scene(root);

            stage.setScene(scene);
            stage.show();

        }
    }

    public void animacao() {
        FadeTransition ft = new FadeTransition();
        ft.setDuration(Duration.millis(1000));
        ft.setFromValue(1);
        ft.setToValue(0);
        ft.setOnFinished((ActionEvent e) -> {
            try {
                gravar();
            } catch (IOException ex) {
                Logger.getLogger(ProdController.class.getName()).log(Level.SEVERE, null, ex);
            }

        });
        ft.play();

    }

    public TableView<Produto> getTable() {
        return table;
    }

    public void setTable(TableView<Produto> table) {
        this.table = table;
    }

     @FXML
    public void removerLogico(ActionEvent ev) {
        Produto p = new Produto();
        if (table.getSelectionModel().getSelectedItem() != null) {
        p=table.getSelectionModel().getSelectedItem();
        p.excluir(p);
        table.setItems(ListarProduto());
        

    }
}
    Produto prod;
   public void pesquisar(){
       FilteredList<Produto> filtro = new FilteredList<>( obsPro, e -> true);
       txtPesquisa.setOnKeyReleased(e -> {
     txtPesquisa.textProperty().addListener((observable, oldValue, newValue) -> {
         filtro.setPredicate((Predicate<? super Produto>) prod ->{
             
             if ( newValue == null || newValue.isEmpty()) {
                 return true;

                 
             }
             String LowerCaseFilter = newValue.toLowerCase();
             if ( prod.getNome().contains(newValue)) {
                 System.out.println("aaaa");
             return true; }
             
                          return false;
                          

         }  );
     });
           SortedList<Produto> sorteio = new SortedList<>(filtro);
           sorteio.comparatorProperty().bind(table.comparatorProperty());
           table.setItems(sorteio);
           
           
       });
               
       
   }
   
   
   FilteredList<Produto> filtro; 
   @FXML
   public void procurarProduto() {
        txtPesquisa.textProperty().addListener((o,ov,nv) ->{
        filtro.setPredicate((Produto p)->{
            
            String novoValor = nv.toLowerCase();
            return p.getNome().toLowerCase().contains(novoValor);
            
            
        });
             
        });
       table.setItems(filtro);
   }
    }


