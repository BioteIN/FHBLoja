/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import controle.FornecedorDAO;
import controle.PedidoDAO;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
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
@Entity( name = " Pedido")
public class Pedido {
    @Id
    @GeneratedValue
    private int id;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idFuncionario")
    private Funcionario usuario;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idCliente")
    private Cliente cliente;
    @Temporal(TemporalType.DATE)
    private Date dataDoPedido;
    private String status; //finalizado ou aberto
    private double desconto, valorTotal, valorRecebido;

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

    /**
     * @return the desconto
     */
    public double getDesconto() {
        return desconto;
    }

    /**
     * @param desconto the desconto to set
     */
    public void setDesconto(double desconto) {
        this.desconto = desconto;
    }

    /**
     * @return the valorTotal
     */
    public double getValorTotal() {
        return valorTotal;
    }

    /**
     * @param valorTotal the valorTotal to set
     */
    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    /**
     * @return the valorRecebido
     */
    public double getValorRecebido() {
        return valorRecebido;
    }

    /**
     * @param valorRecebido the valorRecebido to set
     */
    public void setValorRecebido(double valorRecebido) {
        this.valorRecebido = valorRecebido;
    }
    
     private static void incluir(Pedido pedido) {
        PedidoDAO dao = new PedidoDAO();
        int k=0;
        for(Pedido ped: dao.consultar("")){
            if(ped.getCliente().equals(pedido.getCliente()) && ped.getStatus().equalsIgnoreCase("aberto")) k++;
        }
        if(k==0)
            if(dao.gravar(pedido)) JOptionPane.showMessageDialog(null,"Pedido foi registado com sucesso!");
        else
           JOptionPane.showMessageDialog(null,"Este Cliente possui ja um pedido nao finalizado");
    }
    private static void excluir(Pedido pedido) {
        PedidoDAO dao = new PedidoDAO();
        if(dao.remover(pedido)) JOptionPane.showMessageDialog(null,"Pedido foi removido com sucesso!");
    }
    private static void alterar(Pedido pedido) {
        PedidoDAO dao = new PedidoDAO();
        if(dao.atualizar(pedido)) JOptionPane.showMessageDialog(null,"Pedido foi alterado com sucesso!");
    }
    
    
    
}
