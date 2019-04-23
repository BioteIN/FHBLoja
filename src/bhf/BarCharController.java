/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bhf;

import controle.ProdutoDAO;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import modelo.Produto;

/**
 * FXML Controller class
 *
 * @author Faruque Braimo
 */
public class BarCharController implements Initializable {

    @FXML
    private BarChart<?, ?> GraficoVendas;
    @FXML
    private CategoryAxis x;
    @FXML
    private NumberAxis y;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        ProdutoDAO dao = new ProdutoDAO();
        
        
              
        
        for( Produto p : dao.consultar()) {
            
        XYChart.Series  set1 = new XYChart.Series< >();
     
        set1.getData().add(new XYChart.Data("Manga",100));
//        set1.getData().add(new XYChart.Data("Banana",200));
//        set1.getData().add(new XYChart.Data("Maca",50));
//        set1.getData().add(new XYChart.Data("Abacaxi",20));
//        set1.getData().add(new XYChart.Data("Ananas",8));
//        set1.getData().add(new XYChart.Data("Laranja",100));
        GraficoVendas.getData().addAll(set1);
            
        }
        
        
        
        
        
        
       
                
        
        
    }    
    
}
