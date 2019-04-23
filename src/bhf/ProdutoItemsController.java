/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bhf;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import modelo.Produto;
import modelo.Vender;

/**
 * FXML Controller class
 *
 * @author bONGANI
 */
public class ProdutoItemsController implements Initializable {

    public static List<Vender> produtos = null;
    /**
     * Initializes the controller class.
     */
    @FXML
    private JFXButton btX;
    @FXML
    private TableView<Vender> tabelinha;
@FXML
    private TableColumn<Vender, Integer> colunaQuant;

    @FXML
    private TableColumn<Vender, Produto> colunaProd;
    @FXML
    void fechar(ActionEvent event) {
        System.exit(0);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        System.out.println(produtos);
        colunaProd.setCellValueFactory(new PropertyValueFactory<>("produto"));
        colunaQuant.setCellValueFactory(new PropertyValueFactory<>("quantidade"));
        if(produtos!=null){
            tabelinha.setItems(buscar());
        }
    }

    private ObservableList<Vender> buscar() {
        return FXCollections.observableArrayList(produtos);
    }
}
