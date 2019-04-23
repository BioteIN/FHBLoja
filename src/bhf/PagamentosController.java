/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bhf;

import alerta.Alerta;
import static bhf.EntradaFXMLController.func;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfWriter;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import static com.sun.javafx.tk.Toolkit.getToolkit;
import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
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
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import modelo.ContaAReceber;
import modelo.Contas;
import modelo.Funcionario;
import modelo.Pagamento;
import modelo.TipoPagamento;
import modelo.Venda;

/**
 * FXML Controller class
 *
 * @author Faruque Braimo
 */
public class PagamentosController implements Initializable {

    public static Funcionario func = new Funcionario();
    public static Venda venda = new Venda();
    public static ContaAReceber conta = null;
    public static Contas contas = null;
    @FXML
    private Label txtNumero;

    @FXML
    private ComboBox<String> cbxFormaPagamentos;

    double receber;
    @FXML
    private TextField txtValorRecebido;

    @FXML
    private Label txtTroco;

    @FXML
    private JFXButton btConfirmar;

    @FXML
    private JFXTextField txtDesconto;
    @FXML
    private Label lblDesconto;

    @FXML
    private Label lbTotal;

    private void fecharJanela() {

        ((Stage) btConfirmar.getScene().getWindow()).close();

    }

    @FXML
    void pressionar(KeyEvent event) {

        KeyCode k = event.getCode().ENTER;
        if (conta != null) {
            if (event.getCode() == k) {
                txtTroco.setText(String.valueOf(Pagamento.trocar(conta.getValorDevido(), Double.parseDouble(txtValorRecebido.getText()))));
            }
        } else {
            if (contas.getTipoDePagamento() == TipoPagamento.PARCELA) {
                if (cbxFormaPagamentos.getValue().equalsIgnoreCase("todas")) {
                    double c=contas.getNumeroDeParcelas() - contas.getParcela();
                    txtTroco.setText(String.valueOf(Pagamento.trocar(contas.getValorPorParcela()*c, Double.parseDouble(txtValorRecebido.getText()))));
                } else {
                    txtTroco.setText(String.valueOf(Pagamento.trocar(contas.getValorPorParcela() * Integer.parseInt(cbxFormaPagamentos.getValue()), Double.parseDouble(txtValorRecebido.getText()))));
                }
            } else {
                txtTroco.setText(String.valueOf(Pagamento.trocar(Double.parseDouble(txtDesconto.getText()), Double.parseDouble(txtValorRecebido.getText()))));
            }
        }
    }

    private void imprimirRecibo() {
        Rectangle rect = new Rectangle(400, 700);
        Document document = new Document();
        document.setPageSize(rect);
        try {
            PdfWriter.getInstance(document, new FileOutputStream("Recibo.pdf"));

            document.open();

            document.add(new Paragraph(" Recibo de Pagamento", FontFactory.getFont(FontFactory.TIMES_BOLD, 20, Font.BOLD, BaseColor.BLACK)));
            document.add(new Paragraph("_______________________________________________________________________", FontFactory.getFont(FontFactory.TIMES_BOLD, 12, Font.BOLD, BaseColor.BLUE)));

            com.itextpdf.text.List list = new com.itextpdf.text.List(false, 20);

            list.add("              FHBLoja!       ");
            list.add("                          ");
            list.add("Eu,  " + func.getNome() + "declaro que recibi do Sr.(a): ");
            list.add(" " + venda.getCliente().getNome() + " residente no endereco :");
            list.add("          Endereco:    " + venda.getCliente().getEndereco());
            list.add("  O valor de: " + venda.getPrecoTotal());
            list.add("                               ");
            list.add("        ______________________________________      ");
            list.add(" Data:" + new Date().toString());

            document.add(list);
            document.close();

        } catch (DocumentException ex) {
            Logger.getLogger(RelatoriosController.class.getName()).log(Level.SEVERE, null, ex);

        } catch (FileNotFoundException ex) {
            System.out.println("aquela falha");
            Logger.getLogger(RelatoriosController.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            Desktop.getDesktop().open(new File("Recibo.pdf"));
        } catch (IOException ex) {
            Logger.getLogger(RelatoriosController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    void confirmar(ActionEvent event) {
        if (conta != null) {
            if (txtValorRecebido.getText().isEmpty()) {
                Alerta.errar("Pagamento", "Preencha o campo do Valor a receber");
            } else {
                receber = Double.parseDouble(txtValorRecebido.getText());
                if(Pagamento.pagarBalcao(conta, receber)){
                    imprimirRecibo();
                }
            }
        } else {
            if (txtValorRecebido.getText().isEmpty()) {
                Alerta.errar("Pagamento", "Preencha o campo do Valor a receber");
            } else {
                if (contas.getTipoDePagamento() == TipoPagamento.PARCELA) {
                    if (cbxFormaPagamentos.getValue().equalsIgnoreCase("todas")) {
                        receber = Double.parseDouble(txtValorRecebido.getText());
                        if(Pagamento.pagarParcelaTotal(contas, receber)){
                            imprimirRecibo();
                        }
                    } else {
                        receber = Double.parseDouble(txtValorRecebido.getText());
                        if(Pagamento.pagarParcela(Integer.parseInt(cbxFormaPagamentos.getValue()), contas, receber)){
                            imprimirRecibo();
                        }
                    }
                } else {
                    receber = Double.parseDouble(txtValorRecebido.getText());
                    if (cbxFormaPagamentos.getValue().equalsIgnoreCase("todo")) {
                        txtDesconto.setText(String.valueOf(contas.getValorDevido()));
                        if(Pagamento.pagarPrazoTotal(contas, receber)){
                            imprimirRecibo();
                        }
                    } else {
                        if(Pagamento.pagarPrazo(contas, receber, Double.parseDouble(txtDesconto.getText()))){
                            imprimirRecibo();
                        }
                    }
                }
            }
        }
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        if (conta != null) {
            lbTotal.setText(String.valueOf(conta.getValorDevido()));
        } else {
            lbTotal.setText(String.valueOf(contas.getValorDevido()));
        }
        if (conta == null) {
            if (contas != null && contas.getTipoDePagamento() == TipoPagamento.PARCELA) {
                cbxFormaPagamentos.setVisible(true);
                ObservableList<String> options = FXCollections.observableArrayList("1", "2", "3", "4", "5", "6", "7", "8", "TODAS");
                cbxFormaPagamentos.setItems(options);
                txtNumero.setVisible(true);
            } else {
                txtNumero.setVisible(true);
                txtNumero.setText("Tipo De Pagamento");
                lblDesconto.setVisible(true);
                txtDesconto.setVisible(true);
                cbxFormaPagamentos.setVisible(true);
                ObservableList<String> options = FXCollections.observableArrayList("Parcial", "TODO");
                cbxFormaPagamentos.setItems(options);
            }
        }
    }
}
