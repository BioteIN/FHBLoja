/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import alerta.Alerta;
import static bhf.PagamentosController.func;
import static bhf.PagamentosController.venda;
import bhf.RelatoriosController;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfWriter;
import controle.PedidoDAO;
import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.swing.JOptionPane;

/**
 *
 * @author Biote
 * @author Faruque
 * @author Helio
 */
@Entity(name = "pedid")
public class Pedido {

    @Id
    @GeneratedValue
    private int id;
    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "idFuncionario")
    private Funcionario usuario;
    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "idCliente")
    private Cliente cliente;
    @Temporal(TemporalType.DATE)
    private Date dataDoPedido;
    private String status; //finalizado ou aberto
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pedido",fetch = FetchType.EAGER)
    private List<Vender> produtos;
    @OneToOne(cascade = CascadeType.ALL)
    private Contas contas;

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the usuario
     */
    public Funcionario getUsuario() {
        return usuario;
    }

    /**
     * @param usuario the usuario to set
     */
    public void setUsuario(Funcionario usuario) {
        this.usuario = usuario;
    }

    /**
     * @return the cliente
     */
    public Cliente getCliente() {
        return cliente;
    }

    /**
     * @param cliente the cliente to set
     */
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    /**
     * @return the dataDoPedido
     */
    public Date getDataDoPedido() {
        return dataDoPedido;
    }

    /**
     * @param dataDoPedido the dataDoPedido to set
     */
    public void setDataDoPedido(Date dataDoPedido) {
        this.dataDoPedido = dataDoPedido;
    }

    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }

    private static void incluir(Pedido pedido) {
        PedidoDAO dao = new PedidoDAO();
        int k = 0;
        for (Pedido ped : dao.consultar()) {
            if (ped.getCliente().equals(pedido.getCliente()) && ped.getStatus().equalsIgnoreCase("aberto")) {
                k++;
            }
        }
        if (k == 0) {
            if (dao.gravar(pedido)) {
            }
        } else {
            JOptionPane.showMessageDialog(null, "Este Cliente possui ja um pedido nao finalizado");
        }
    }

    private static void excluir(Pedido pedido) {
        PedidoDAO dao = new PedidoDAO();
        if (dao.remover(pedido)) {
            JOptionPane.showMessageDialog(null, "Pedido foi removido com sucesso!");
        }
    }

    public static void incluirAPrazoouParcelas(int numeroParcela, Pedido pedido, List<Vender> produtos, double precoTotal, TipoPagamento paga) {
        int numero;
        if (precoTotal < 1000) {
            numero = 20;
        } else {
            if (precoTotal < 2000) {
                numero = 40;
            } else {

            }
        }
        PedidoDAO dao = new PedidoDAO();
        Date data = new Date();
        pedido.setDataDoPedido(data);
        int k = 0;
        int qunt;
        pedido.setProdutos(produtos);
        pedido.setStatus("aberto");
        Contas contas = new Contas();
        contas.setPaga(false);
        Date data1 = new Date();
        contas.setData(new Date());
        contas.setCliente(pedido.getCliente());
        contas.setTipoDePagamento(paga);
        contas.setDataFinal(data1);
        contas.setParcela(0);
        contas.setValorDevido(precoTotal);
        contas.setNumeroDeParcelas(numeroParcela);
        contas.setValorPorParcela(Venda.calcular(numeroParcela, precoTotal));
        contas.setValorPago(0);
        contas.setPedido(pedido);
        pedido.setContas(contas);
        if (dao.gravar(pedido)) {
            Alerta.MostrarNotificacao("Efectuar Pedido", "Pedido efectuado com sucesso!!");
            imprimir(contas, pedido);
        }
    }

    public static void alterar(Pedido pedido) {
        PedidoDAO dao = new PedidoDAO();
        if (dao.atualizar(pedido)) {
            Alerta.MostrarNotificacao("Efectuar Pedido", "Pedido alterado com sucesso!!");
        }
    }

    /**
     * @return the produtos
     */
    public List<Vender> getProdutos() {
        return produtos;
    }

    /**
     * @param produtos the produtos to set
     */
    public void setProdutos(List<Vender> produtos) {
        this.produtos = produtos;
    }

    /**
     * @return the contas
     */
    public Contas getContas() {
        return contas;
    }

    /**
     * @param contas the contas to set
     */
    public void setContas(Contas contas) {
        this.contas = contas;
    }
     private  static void imprimir(Contas conta,Pedido pedido){
         Rectangle rect = new Rectangle(400, 700);
      Document  document = new Document();
      document.setPageSize(rect);
        try {
                        PdfWriter.getInstance(document, new FileOutputStream("fact.pdf"));

            document.open();
            
            document.add(new Paragraph(" Factura",FontFactory.getFont(FontFactory.TIMES_BOLD,20,Font.BOLD,BaseColor.BLACK)));
            document.add(new Paragraph("_______________________________________________________________________",FontFactory.getFont(FontFactory.TIMES_BOLD,12,Font.BOLD,BaseColor.BLUE)));

            
            
            
            com.itextpdf.text.List list = new com.itextpdf.text.List(false,20);
           
                  list.add("              FHBLoja!                    ");
                list.add("                          ");
               list.add("          Nome:        "+pedido.getCliente().getNome());
                list.add("          Endereco:    "+pedido.getCliente().getEndereco());
                list.add("          Nome do Funcionario:   "+    pedido.getUsuario().getNome());
                list.add("                               ");

                list.add("          Preco A Pagar:  "+conta.getValorDevido());

                list.add("_________________________________________________");

            document.add(list);
            document.close();
            
        } catch(DocumentException ex){
                        Logger.getLogger(RelatoriosController.class.getName()).log(Level.SEVERE, null, ex);

        }catch (FileNotFoundException ex) {
            System.out.println("aquela falha");
            Logger.getLogger(RelatoriosController.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            Desktop.getDesktop().open(new File("fact.pdf"));
        } catch (IOException ex) {
            Logger.getLogger(RelatoriosController.class.getName()).log(Level.SEVERE, null, ex);
        }
           
    }
   
}
