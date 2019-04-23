/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import controle.ContaDAO;
import java.util.Date;
import javax.persistence.CascadeType;
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
@Entity(name = "areceber")
public class ContaAReceber {

    @Id
    @GeneratedValue
    @Column(name = "idConta")
    private int Id;
    @OneToOne
    @JoinColumn(name = "id_Conta")
    private Venda venda;
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
    public int getId() {
        return Id;
    }

    public TipoPagamento getTipoDePagamento() {
        return tipoDePagamento;
    }

    public void setTipoDePagamento(TipoPagamento tipoDePagamento) {
        this.tipoDePagamento = tipoDePagamento;
    }
    

    public void setId(int Id) {
        this.Id = Id;
    }


    public Venda getVenda() {
        return venda;
    }

    public void setVenda(Venda venda) {
        this.venda = venda;
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
    public static void contar(ContaAReceber conta) {
        int k = 0;
        ContaDAO dao = new ContaDAO();
//        if(dao.consultar()!=null){
////        for (ContaAReceber c : dao.consultar()) {
////            if (c.getCliente().getNome().equalsIgnoreCase(conta.getCliente().getNome()) && c.getCliente().getTelefone1().equalsIgnoreCase(conta.getCliente().getTelefone1()) && !conta.isPaga()) {
////                k++;
////            }
////        }
//        }
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
    public static boolean alterar(ContaAReceber conta) {
        ContaDAO dao = new ContaDAO();
        if (dao.atualizar(conta)) {
            return true;
        }
        return false;
    }

}
