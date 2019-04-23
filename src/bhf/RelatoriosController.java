/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bhf;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.jfoenix.controls.JFXButton;
import controle.CaixaDAO;
import controle.ClienteDAO;
import controle.EmpresaDAO;
import controle.FornecedorDAO;
import controle.PedidoDAO;
import controle.ProdutoDAO;
import controle.VendaDAO;
import java.awt.Desktop;
import java.awt.Font;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javax.swing.JOptionPane;
import modelo.Caixa;
import modelo.Cliente;
import modelo.Empresa;
import modelo.Pedido;
import modelo.Produto;
import modelo.Venda;

/**
 * FXML Controller class
 *
 * @author bONGANI
 */
public class RelatoriosController implements Initializable {

    Empresa emp = new Empresa();
    String nomeEmp;

    /**
     * Initializes the controller class.
     */
    @FXML
    private JFXButton btCaixaPDF;

    @FXML
    private JFXButton btPedidoPDF;

    @FXML
    private JFXButton btVendaPDF;

    @FXML
    private JFXButton btProdutoPDF;

    @FXML
    private JFXButton btClientePDF;

    @FXML
    private JFXButton btFornecedorPDF;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        EmpresaDAO dao1 = new EmpresaDAO();
        for (Empresa c : dao1.consultar()) {
            nomeEmp = c.getNome();
        }

        btCaixaPDF.setOnMouseClicked((MouseEvent e) -> {
            gerarCaixaPDF();
        });
        btClientePDF.setOnMouseClicked((MouseEvent e) -> {
            gerarClientePDF();
        });
        btFornecedorPDF.setOnMouseClicked((MouseEvent e) -> {
            gerarFornecedorPDF();
        });
        btPedidoPDF.setOnMouseClicked((MouseEvent e) -> {
            gerarPedidoPDF();
        });
        btProdutoPDF.setOnMouseClicked((MouseEvent e) -> {
            gerarProdutoPDF();
        });
        btVendaPDF.setOnMouseClicked((MouseEvent e) -> {
            gerarVendaPDF();
        });
        // TODO
    }

    private void gerarCaixaPDF() {
        Document document = new Document();
        try {
            PdfWriter.getInstance(document, new FileOutputStream("Caixa.pdf"));
            document.open();
            document.add(new Paragraph(" " + nomeEmp, FontFactory.getFont(FontFactory.TIMES_BOLD, 20, Font.BOLD, BaseColor.BLACK)));
            document.add(new Paragraph(" Relatório de Caixa!", FontFactory.getFont(FontFactory.TIMES_BOLD, 18, Font.BOLD, BaseColor.BLACK)));
            document.add(new Paragraph("_______________________________________________________________________"));
            document.add(new Paragraph("Data:  " + new Date().toString()));
            com.itextpdf.text.List list = new com.itextpdf.text.List(true, 20);
            CaixaDAO dao = new CaixaDAO();
            for (Caixa c : dao.consultar()) {
                list.add("Data de Abertura:   " + c.getDataAbertura());
                list.add("Data de Fechamento: " + c.getDataFecho());
                list.add("Valor Inicial:      " + c.getValorEntrou());
                list.add("Data de Final:      " + c.getDataAbertura());
                list.add("Saldo:              " + c.getSaldoCaixa());
                list.add("Entrada:            " + c.getSaldoEntrada());
            }
            document.add(list);
            document.add(new Paragraph("_______________________________________________________________________"));
            document.close();

        } catch (DocumentException ex) {
            Logger.getLogger(RelatoriosController.class.getName()).log(Level.SEVERE, null, ex);

        } catch (FileNotFoundException ex) {
            System.out.println("aquela falha");
            Logger.getLogger(RelatoriosController.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            Desktop.getDesktop().open(new File("Caixa.pdf"));
        } catch (IOException ex) {
            Logger.getLogger(RelatoriosController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void gerarPedidoPDF() {
        double valorT = 0;
        Document document = new Document();
        try {
            PdfWriter.getInstance(document, new FileOutputStream("Pedido.pdf"));

            document.open();
            document.add(new Paragraph(" " + nomeEmp, FontFactory.getFont(FontFactory.TIMES_BOLD, 20, Font.BOLD, BaseColor.BLACK)));

            document.add(new Paragraph(" Relatório de Vendas!", FontFactory.getFont(FontFactory.TIMES_BOLD, 18, Font.BOLD, BaseColor.BLACK)));
            document.add(new Paragraph("_________________________________________________________________________________________________", FontFactory.getFont(FontFactory.TIMES_BOLD, 10, Font.BOLD, BaseColor.BLUE)));

            document.add(new Paragraph("Data:  " + new Date().toString()));
            document.add(new Paragraph("                                                                                        ", FontFactory.getFont(FontFactory.TIMES_BOLD, 10, Font.BOLD, BaseColor.BLUE)));

            PedidoDAO dao = new PedidoDAO();
            for (Pedido c : dao.consultar()) {
                PdfPTable tabela = new PdfPTable(4);
                PdfPCell celula = new PdfPCell(new Paragraph("Codigo    " + "Nome do Funcionario  " + "Nome do Funcionario " + "Preco Total    "));
                celula.setHorizontalAlignment(Element.ALIGN_LEFT);
                celula.setBorder(PdfPCell.NO_BORDER);

                celula.setBackgroundColor(new BaseColor(100, 150, 200));
                celula.setColspan(4);

                tabela.addCell(celula);
                tabela.addCell("" + c.getId());
                tabela.addCell(" ");
                tabela.addCell("" + c.getCliente().getNome());
                tabela.addCell(" ");
                document.add(tabela);
                document.add(new Paragraph("                                                                                        ", FontFactory.getFont(FontFactory.TIMES_BOLD, 10, Font.BOLD, BaseColor.BLUE)));
                document.add(new Paragraph("                                                                                        ", FontFactory.getFont(FontFactory.TIMES_BOLD, 10, Font.BOLD, BaseColor.BLUE)));

            }
            com.itextpdf.text.List list = new com.itextpdf.text.List(true, 0);
            list.add("Valor Total:        " + valorT);
            document.add(list);
            document.close();

        } catch (DocumentException ex) {
            Logger.getLogger(RelatoriosController.class.getName()).log(Level.SEVERE, null, ex);

        } catch (FileNotFoundException ex) {
            System.out.println("aquela falha");
            Logger.getLogger(RelatoriosController.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            Desktop.getDesktop().open(new File("Pedido.pdf"));
        } catch (IOException ex) {
            Logger.getLogger(RelatoriosController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void gerarVendaPDF() {
        double valorT = 0;
        Document document = new Document();
        try {
            int d = 0,h=0;
            double f=0;

            VendaDAO dao01 = new VendaDAO();
            for (Venda c : dao01.consultar()) {
                d++;
                f=f+c.getPrecoTotal();
                if(!c.isStatus()){
                    h++;
                }
            }
            PdfWriter.getInstance(document, new FileOutputStream("Venda.pdf"));

            document.open();
            document.add(new Paragraph(" " + nomeEmp, FontFactory.getFont(FontFactory.TIMES_BOLD, 20, Font.BOLD, BaseColor.BLACK)));
            document.add(new Paragraph("_________________________________________________________________________________________________"));
            document.add(new Paragraph(" Relatório de Vendas!", FontFactory.getFont(FontFactory.TIMES_BOLD, 18, Font.BOLD, BaseColor.BLACK)));
            document.add(new Paragraph(" Vendas Cadastradas: "+d+".      Valor Total: "+h));

            document.add(new Paragraph("_________________________________________________________________________________________________", FontFactory.getFont(FontFactory.TIMES_BOLD, 10, Font.BOLD, BaseColor.BLUE)));

            document.add(new Paragraph("Data:  " + new Date().toString()));
            document.add(new Paragraph("                                                                                        ", FontFactory.getFont(FontFactory.TIMES_BOLD, 10, Font.BOLD, BaseColor.BLUE)));

            VendaDAO dao = new VendaDAO();
            for (Venda c : dao.consultar()) {
                PdfPTable tabela = new PdfPTable(4);
                PdfPCell celula = new PdfPCell(new Paragraph("Codigo    " + "Nome do Funcionario  " + "Nome do Funcionario " + "Preco Total    "));
                celula.setHorizontalAlignment(Element.ALIGN_LEFT);
                celula.setBorder(PdfPCell.NO_BORDER);

                celula.setBackgroundColor(new BaseColor(100, 150, 200));
                celula.setColspan(4);

                tabela.addCell(celula);
                tabela.addCell("" + c.getId());
                tabela.addCell(" ");
                tabela.addCell("" + c.getCliente().getNome());
                tabela.addCell(" ");
                document.add(tabela);
                document.add(new Paragraph("                                                                                        ", FontFactory.getFont(FontFactory.TIMES_BOLD, 10, Font.BOLD, BaseColor.BLUE)));
                document.add(new Paragraph("                                                                                        ", FontFactory.getFont(FontFactory.TIMES_BOLD, 10, Font.BOLD, BaseColor.BLUE)));
                valorT = valorT + c.getPrecoTotal();

            }
            com.itextpdf.text.List list = new com.itextpdf.text.List(true, 0);
            list.add("Valor Total:        " + valorT);
            document.add(list);
            document.close();

        } catch (DocumentException ex) {
            Logger.getLogger(RelatoriosController.class.getName()).log(Level.SEVERE, null, ex);

        } catch (FileNotFoundException ex) {
            System.out.println("aquela falha");
            Logger.getLogger(RelatoriosController.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            Desktop.getDesktop().open(new File("Venda.pdf"));
        } catch (IOException ex) {
            Logger.getLogger(RelatoriosController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void gerarProdutoPDF() {
        double valorT = 0;
        Document document = new Document();
        try {
            PdfWriter.getInstance(document, new FileOutputStream("Produto.pdf"));

            document.open();
            document.add(new Paragraph(" " + nomeEmp, FontFactory.getFont(FontFactory.TIMES_BOLD, 20, Font.BOLD, BaseColor.BLACK)));

            document.add(new Paragraph(" Relatório de Produtos!", FontFactory.getFont(FontFactory.TIMES_BOLD, 18, Font.BOLD, BaseColor.BLACK)));
            document.add(new Paragraph("_________________________________________________________________________________________________"));

            document.add(new Paragraph("Data:  " + new Date().toString()));
            document.add(new Paragraph("_________________________________________________________________________________________________"));

            document.add(new Paragraph("                                                                                        ", FontFactory.getFont(FontFactory.TIMES_BOLD, 10, Font.BOLD, BaseColor.BLUE)));

            ProdutoDAO dao = new ProdutoDAO();
            for (Produto c : dao.consultar()) {
                PdfPTable tabela = new PdfPTable(5);
                PdfPCell celula = new PdfPCell(new Paragraph("Codigo  " + "Nome     " + "Quantidade      " + "Preco Unitario      " + "Preco Total    "));
                celula.setHorizontalAlignment(Element.ALIGN_LEFT);
                celula.setBorder(PdfPCell.NO_BORDER);

                celula.setBackgroundColor(new BaseColor(100, 150, 200));
                celula.setColspan(5);

                tabela.addCell(celula);
                tabela.addCell("" + c.getId());
                tabela.addCell(c.getNome());
                tabela.addCell("" + c.getQuantidadeEmbalagem());
                tabela.addCell("" + c.getPrecoDeVendaUnit());
                tabela.addCell("" + c.getPrecoDeVendaTotal());
                document.add(tabela);
                document.add(new Paragraph("                                                                                        ", FontFactory.getFont(FontFactory.TIMES_BOLD, 10, Font.BOLD, BaseColor.BLUE)));
                document.add(new Paragraph("                                                                                        ", FontFactory.getFont(FontFactory.TIMES_BOLD, 10, Font.BOLD, BaseColor.BLUE)));
                valorT = valorT + c.getPrecoDeVendaTotal();

            }
            com.itextpdf.text.List list = new com.itextpdf.text.List(true, 0);
            list.add("Valor Total:        " + valorT);
            document.add(list);
            document.close();

        } catch (DocumentException ex) {
            Logger.getLogger(RelatoriosController.class.getName()).log(Level.SEVERE, null, ex);

        } catch (FileNotFoundException ex) {
            System.out.println("aquela falha");
            Logger.getLogger(RelatoriosController.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            Desktop.getDesktop().open(new File("Produto.pdf"));
        } catch (IOException ex) {
            Logger.getLogger(RelatoriosController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void gerarClientePDF() {
        Document document = new Document();
        try {
            int d = 0,h=0,f=0;

            ClienteDAO dao01 = new ClienteDAO();
            for (Cliente c : dao01.consultar()) {
                d++;
                if(!c.isStatus()){
                    h++;
                }else{
                   f++; 
                }
            }
            PdfWriter.getInstance(document, new FileOutputStream("Clientela.pdf"));

            document.open();
            document.add(new Paragraph(" " + nomeEmp, FontFactory.getFont(FontFactory.TIMES_BOLD, 20, Font.BOLD, BaseColor.BLACK)));
            document.add(new Paragraph(" Relatório de Clientes!" + new Date().toString(), FontFactory.getFont(FontFactory.TIMES_BOLD, 18, Font.BOLD, BaseColor.BLACK)));
            document.add(new Paragraph("_______________________________________________________________________"));

            // document.add(new Paragraph("Data:  " + new Date().toString()));
            document.add(new Paragraph("Clientes Cadastrados: "+d+".      Clientes Removidos: "+h+".    Clientes nao removidos:"+f));
            document.add(new Paragraph("_______________________________________________________________________"));
            com.itextpdf.text.List list = new com.itextpdf.text.List(false, 20);
            ClienteDAO dao = new ClienteDAO();
            for (Cliente c : dao.consultar()) {
                list.add("          Data:        " + null);
                list.add("          Nome:        " + c.getNome());
                list.add("          Endereco:    " + c.getEndereco());
                list.add("          Telefone:    " + c.getTelefone1());
                list.add("          Observacoes: " + c.getObservacoes());
                list.add("          Natural de:  " + c.getNaturalidade());
                list.add("_______________________________________________________________________");

            }
            document.add(list);
            document.close();

        } catch (DocumentException ex) {
            Logger.getLogger(RelatoriosController.class.getName()).log(Level.SEVERE, null, ex);

        } catch (FileNotFoundException ex) {
            System.out.println("aquela falha");
            Logger.getLogger(RelatoriosController.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            Desktop.getDesktop().open(new File("Clientela.pdf"));
        } catch (IOException ex) {
            Logger.getLogger(RelatoriosController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void gerarFornecedorPDF() {
        Document document = new Document();
        try {
            
            PdfWriter.getInstance(document, new FileOutputStream("Fornecedor.pdf"));

            document.open();
            document.add(new Paragraph(" " + nomeEmp, FontFactory.getFont(FontFactory.TIMES_BOLD, 20, Font.BOLD, BaseColor.BLACK)));
            document.add(new Paragraph(" Relatório dos Fornecedores!", FontFactory.getFont(FontFactory.TIMES_BOLD, 18, Font.BOLD, BaseColor.BLACK)));
            document.add(new Paragraph("_______________________________________________________________________", FontFactory.getFont(FontFactory.TIMES_BOLD, 12, Font.BOLD, BaseColor.BLUE)));

            document.add(new Paragraph("Data:  " + new Date().toString()));
            document.add(new Paragraph("_______________________________________________________________________", FontFactory.getFont(FontFactory.TIMES_BOLD, 12, Font.BOLD, BaseColor.BLUE)));
            com.itextpdf.text.List list = new com.itextpdf.text.List(true, 20);
            FornecedorDAO dao = new FornecedorDAO();
            for (modelo.Fornecedor c : dao.consultar()) {
                list.add("Data:         " + null);
                list.add("Nome:         " + c.getNome());
                list.add("Endereco:     " + c.getEndereco());
                list.add("Telefone:     " + c.getTelefone());
                list.add("Observacoes:  " + c.getObservacoes());
                list.add("Email:        " + c.getTelefone2());
                list.add("Representante:" + c.getTelefone2());

                list.add(new Paragraph("_______________________________________________________________________", FontFactory.getFont(FontFactory.TIMES_BOLD, 12, BaseColor.BLUE)));

            }
            document.add(list);
            document.close();

        } catch (DocumentException ex) {
            Logger.getLogger(RelatoriosController.class.getName()).log(Level.SEVERE, null, ex);

        } catch (FileNotFoundException ex) {
            System.out.println("aquela falha");
            Logger.getLogger(RelatoriosController.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            Desktop.getDesktop().open(new File("Fornecedor.pdf"));
        } catch (IOException ex) {
            Logger.getLogger(RelatoriosController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
