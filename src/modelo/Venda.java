/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import alerta.Alerta;
import bhf.PagamentosController;
import controle.CaixaDAO;
import controle.ProdutoDAO;
import controle.VendaDAO;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.StageStyle;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 * @author Faruque
 * @author Helio
 * @author Biote
 */
@Entity(name = "vendas")
public class Venda {

    @Id
    @GeneratedValue
    private int id;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idFuncionario")
    private Funcionario funcionario;
    private String endereco;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idCliente")
    private Cliente cliente;
    @Temporal(TemporalType.DATE)
    private Date dataVenda;
    private double precoTotal = 0;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "venda")
    private List<Vender> produtos;
//    Para apagar
    private boolean status = true;

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

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
     *
     * @return
     */
    public Funcionario getFuncionario() {
        return funcionario;
    }

    /**
     *
     * @param funcionario
     */
    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    /**
     *
     * @return
     */
    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    /**
     *
     * @return
     */
    public Cliente getCliente() {
        return cliente;
    }

    /**
     *
     * @param cliente
     */
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    /**
     * @return the dataVenda
     */
    public Date getDataVenda() {
        return dataVenda;
    }

    /**
     * @param dataVenda the dataVenda to set
     */
    public void setDataVenda(Date dataVenda) {
        this.dataVenda = dataVenda;
    }

    /**
     * @return the precoTotal
     */
    public double getPrecoTotal() {
        return precoTotal;
    }

    /**
     * @param precoTotal the precoTotal to set
     */
    public void setPrecoTotal(double precoTotal) {
        this.precoTotal = precoTotal;
    }

    private static double calcular(int numeroDeParcelas, Venda venda) {
        double valor = venda.getPrecoTotal() / numeroDeParcelas;
        return valor;
    }

    public static double calcular(int numeroDeParcelas, double venda) {
        double valor = venda / numeroDeParcelas;
        return valor;
    }

    public static void incluirABalcao(double preco, Venda venda, List<Vender> produtos) throws IOException {
        VendaDAO dao = new VendaDAO();
        Date data = new Date();
        venda.setDataVenda(data);
        venda.setProdutos(produtos);
        venda.setPrecoTotal(preco);

        int k = 0;
        CaixaDAO doa = new CaixaDAO();
        for (Caixa cc : doa.consultar()) {
            if (cc.isAberto()) {
                k++;
            }
        }
        int qunt;
        if (k > 0) {
                ContaAReceber conta = new ContaAReceber();
                conta.setPaga(false);
                conta.setVenda(venda);
                conta.setData(new Date());
                conta.setCliente(venda.getCliente());
                conta.setTipoDePagamento(TipoPagamento.BALCAO);
                conta.setDataFinal(data);
                conta.setValorDevido(venda.getPrecoTotal());
                PagamentosController.conta = conta;
                ContaAReceber.contar(conta);
            if (dao.gravar(venda)) {
                Alerta.exito("Venda Balcao", "A venda foi  efectuada com sucesso!!!");
            }
        } else {
            Alerta.errar("Abrir Caixa","Impossivel prosseguir sem abertura de um Caixa");
        }
    }

    public static double calcularPreco(int quantidade, List<Vender> produtos, boolean tipo, Produto produto, Venda venda) {
        ProdutoDAO dao = new ProdutoDAO();
        Vender vender = new Vender();
        int quant;
        if (quantidade > 0) {
            if (tipo==true) {
                quant = produto.getEstoqueFisico() - quantidade * produto.getQuantidadeEmbalagem();
            } else {
                quant = produto.getEstoqueFisico() - quantidade;
            }
            System.out.println(quant);
            if (quant > 0) {
                vender.setProduto(produto);
                vender.setQuantidade(quantidade);
                vender.setVenda(venda);
                vender.setPreco(produto.getPrecoDeVendaUnit());
                vender.setPrecoTotal(produto.getPrecoDeVendaUnit());
                produtos.add(vender);
                produto.setEstoqueFisico(quant);
                Produto.actualizar(produto);
                if (tipo) {
                    return quantidade * produto.getPrecoDeVendaTotal();
                } else {
                    return quantidade * produto.getPrecoDeVendaUnit();
                }

            } else {
                Alerta.errar("Efectuar Venda", "Quantidade Insuficiente no estoque. Impossivel fazer a Venda");
            }

        } else {
            Alerta.errar("Efectuar Venda", "Impossivel efectuar venda a quantidade deve ser maior do que zero!");
        }
        return 0;
    }
    public static double calcularPreco(int quantidade, List<Vender> produtos, boolean tipo, Produto produto, Pedido pedido) {
        ProdutoDAO dao = new ProdutoDAO();
        Vender vender = new Vender();
        int quant;
        if (quantidade > 0) {
            if (tipo==true) {
                quant = produto.getEstoqueFisico() - quantidade * produto.getQuantidadeEmbalagem();
            } else {
                quant = produto.getEstoqueFisico() - quantidade;
            }
            System.out.println(quant);
            if (quant > 0) {
                vender.setProduto(produto);
                vender.setQuantidade(quantidade);
                vender.setPedido(pedido);
                vender.setPreco(produto.getPrecoDeVendaUnit());
                vender.setPrecoTotal(produto.getPrecoDeVendaUnit());
                produtos.add(vender);
                produto.setEstoqueFisico(quant);
                Produto.actualizar(produto);
                if (tipo) {
                    return quantidade * produto.getPrecoDeVendaTotal();
                } else {
                    return quantidade * produto.getPrecoDeVendaUnit();
                }

            } else {
                Alerta.errar("Efectuar Venda", "Quantidade Insuficiente no estoque. Impossivel fazer a Venda");
            }

        } else {
            Alerta.errar("Efectuar Venda", "Impossivel efectuar venda a quantidade deve ser maior do que zero!");
        }
        return 0;
    }


    public static boolean alterar(Venda venda) {
        VendaDAO dao = new VendaDAO();
        if (dao.atualizar(venda)) {
            return true;
        }
        return false;
    }
    public static void excluirTabela(Vender vender){
        vender.getProduto().setEstoqueFisico(vender.getProduto().getEstoqueFisico()+vender.getQuantidade());
        Produto.actualizar(vender.getProduto());
    }
    public static List<Venda> buscar() {
        VendaDAO dao = new VendaDAO();
        return dao.consultar();
    }

    public static void excluir(boolean st, Venda venda) {
        venda.setStatus(st);
        Venda.alterar(venda);
    }

    @Override
    public String toString() {
        return "Venda{" + "cliente=" + cliente + ", dataVenda=" + dataVenda + ", precoTotal=" + precoTotal + ", produtos=" + getProdutos() + '}';
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


}
