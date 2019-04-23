/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import controle.ContaDAO;
import controle.ContasDAO;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.swing.JOptionPane;

/**
 * @author Faruque
 * @author Helio
 * @author Biote
 */
@Entity(name = "contas")
public class Contas {

    @Id
    @GeneratedValue
    @Column(name = "idConta")
    private int Id;
    @OneToOne
    @JoinColumn(name = "id_Conta")
    private Pedido pedido;
    private TipoPagamento tipoDePagamento;
    @Temporal(TemporalType.TIMESTAMP)
    private Date data;
    @OneToOne
    @JoinColumn(name = "id_cliente")
    private Cliente cliente;
    private double valorDevido;
    private double valorPago;
    @Temporal(TemporalType.DATE)
    private Date dataFinal;
    private boolean paga;
    private int numeroDeParcelas;
    private double valorPorParcela;
    //paga
    private int parcela;

    public int getId() {
        return Id;
    }

    public TipoPagamento getTipoDePagamento() {
        return tipoDePagamento;
    }

    public void setTipoDePagamento(TipoPagamento tipoDePagamento) {
        this.tipoDePagamento = tipoDePagamento;
    }
    
    public int getNumeroDeParcelas() {
        return numeroDeParcelas;
    }

    public void setNumeroDeParcelas(int numeroDeParcelas) {
        this.numeroDeParcelas = numeroDeParcelas;
    }

    public double getValorPorParcela() {
        return valorPorParcela;
    }

    public void setValorPorParcela(double valorPorParcela) {
        this.valorPorParcela = valorPorParcela;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public int getParcela() {
        return parcela;
    }

    public void setParcela(int parcela) {
        this.parcela = parcela;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }
    

    public Date getDataFinal() {
        return dataFinal;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public double getValorDevido() {
        return valorDevido;
    }

    public void setValorDevido(double valorDevido) {
        this.valorDevido = valorDevido;
    }

    public double getValorPago() {
        return valorPago;
    }

    public void setValorPago(double valorPago) {
        this.valorPago = valorPago;
    }

    public boolean isPaga() {
        return paga;
    }

    public void setPaga(boolean paga) {
        this.paga = paga;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setDataFinal(Date dataFinal) {
        this.dataFinal = dataFinal;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    /**
     * Metodo para gravar as contas no banco de dados
     *
     * @param conta
     */
    public static void contar(Contas conta) {
        int k = 0;
        ContasDAO dao = new ContasDAO();
        if(dao.consultar()!=null){
        for (Contas c : dao.consultar()) {
            if (c.getCliente().getNome().equalsIgnoreCase(conta.getCliente().getNome()) && c.getCliente().getTelefone1().equalsIgnoreCase(conta.getCliente().getTelefone1()) && !conta.isPaga()) {
                k++;
            }
        }
        }
        if (k == 0) {
            dao.gravar(conta);
        } else {
            JOptionPane.showMessageDialog(null, "Este cliente possui uma conta nao paga");
        }
    }

    /**
     * Metodo para atualizar dados da Conta no banco de dados
     *
     * @param conta
     * @return
     */
    public static boolean alterar(Contas conta) {
        ContasDAO dao = new ContasDAO();
        if (dao.atualizar(conta)) {
            return true;
        }
        return false;
    }

}
