/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bhf;

import alerta.Alerta;
import com.jfoenix.controls.JFXComboBox;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import modelo.Venda;
import modelo.Vender;

/**
 * FXML Controller class
 *
 * @author Honwana
 */
public class GraficoController implements Initializable {

    int ano = 2018;
    int jan = 0;
    int fev = 0;
    int mar = 0;
    int abr = 0;
    int may = 0;
    int june = 0;
    int july = 0;
    int aug = 0;
    int sep = 0;
    int oct = 0;
    int nov = 0;
    int dec = 0;
    ArrayList<Integer> quantMes = new ArrayList<>();
    @FXML
    private BarChart<String, Integer> bar;
    @FXML
    private JFXComboBox<Integer> jcbAno;

    @FXML
    private CategoryAxis eixoX;

    @FXML
    private NumberAxis eixoY;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        ObservableList<Integer> opcoes = FXCollections.observableArrayList(2018, 2019, 2020);
        jcbAno.setItems(opcoes);
    }

    private void desenhar() {
        for (Venda v : Venda.buscar()) {
            if (v.getDataVenda().getYear() == ano - 1900) {
                if (v.getDataVenda().getMonth() == 0) {
                    jan++;
                } else {
                    if (v.getDataVenda().getMonth() == 1) {
                        fev++;
                    } else {
                        if (v.getDataVenda().getMonth() == 2) {
                            mar++;
                        } else {
                            if (v.getDataVenda().getMonth() == 3) {
                                abr++;
                            } else {
                                if (v.getDataVenda().getMonth() == 4) {
                                    may++;
                                } else {
                                    if (v.getDataVenda().getMonth() == 5) {
                                        june++;
                                    } else {
                                        if (v.getDataVenda().getMonth() == 6) {
                                            july++;
                                        } else {
                                            if (v.getDataVenda().getMonth() == 7) {
                                                aug++;
                                            } else {
                                                if (v.getDataVenda().getMonth() == 8) {
                                                    sep++;
                                                } else {
                                                    if (v.getDataVenda().getMonth() == 9) {
                                                        oct++;
                                                    } else {
                                                        if (v.getDataVenda().getMonth() == 10) {
                                                            nov++;
                                                        } else {
                                                            if (v.getDataVenda().getMonth() == 11) {
                                                                dec++;
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        ArrayList<String> meses = new ArrayList<>();
        meses.add("Janeiro");
        meses.add("Fevereiro");
        meses.add("Marco");
        meses.add("Abril");
        meses.add("Maio");
        meses.add("Junho");
        meses.add("Julho");
        meses.add("Agosto");
        meses.add("Setembro");
        meses.add("Outubro");
        meses.add("Novembro");
        meses.add("Dezembro");
        XYChart.Series set1 = new XYChart.Series<>();
        set1.getData().add(new XYChart.Data(meses.get(0), jan));
        set1.getData().add(new XYChart.Data(meses.get(1), fev));
        set1.getData().add(new XYChart.Data(meses.get(2), mar));
        set1.getData().add(new XYChart.Data(meses.get(3), abr));
        set1.getData().add(new XYChart.Data(meses.get(4), may));
        set1.getData().add(new XYChart.Data(meses.get(5), june));
        set1.getData().add(new XYChart.Data(meses.get(6), july));
        set1.getData().add(new XYChart.Data(meses.get(7), aug));
        set1.getData().add(new XYChart.Data(meses.get(8), sep));
        set1.getData().add(new XYChart.Data(meses.get(9), oct));
        set1.getData().add(new XYChart.Data(meses.get(10), nov));
        set1.getData().add(new XYChart.Data(meses.get(11), dec));
        bar.getData().addAll(set1);
    }

    @FXML
    void draw(ActionEvent event) {
        ano = jcbAno.getValue();
        if (ano != 2018) {
            Alerta.errar("Desenhar Grafico", "Sem registos no ano selecionado");
        } else {
            desenhar();
        }
    }
}
