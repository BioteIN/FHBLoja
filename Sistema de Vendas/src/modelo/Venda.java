/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @author Faruque
 *@author Helio
 * @author Biote
 */
@Entity(name = "Venda")
public class Venda {
    @Id
    @GeneratedValue
    private int id;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name ="idFuncionario")
    private Funcionario funcionario;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idEndereco")
    private Endereco endereco;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idCliente")
    private Cliente cliente;
    @Temporal(TemporalType.DATE)
    private Date dataVenda;
    private  double precoTotal;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name="idProduto")
    private List<Produto> produtos;

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
    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
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
     * 
     * @return 
     */
    public List<Produto> getProdutos() {
        return produtos;
    }
    /**
     * 
     * @param produtos 
     */
    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
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

 
    
}
