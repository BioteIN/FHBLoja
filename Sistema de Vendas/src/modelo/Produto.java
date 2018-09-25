/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import controle.ProdutoDAO;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.swing.JOptionPane;

/**
 * @author Faruque
 *@author Helio
 * @author Biote
 */
@Entity( name = "Produto")
public class Produto {
    @Id
    @GeneratedValue
    private int id;
    private String nome;
    private String marca;
    private double peso;
    @Temporal(TemporalType.DATE)
    private Date dataCompra;
    private int estoqueFisico;
    private int estoqueMinimo;
    private double precoDeCustoUnit;
    private double precoDeCustoTotal;
    private double precoDeVendaUnit;
    private double precoDeVendaTotal;
    private String transportadoraNome;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "idFornecedor")
    private List<Fornecedor> fornecedores;

    /**
     * @return 
     */
    public double getPrecoDeVendaUnit() {
        return precoDeVendaUnit;
    }
    /**
     * 
     * @param precoDeVendaUnit 
     */
    public void setPrecoDeVendaUnit(double precoDeVendaUnit) {
        this.precoDeVendaUnit = precoDeVendaUnit;
    }
    /**
     * 
     * @return 
     */
    public double getPrecoDeVendaTotal() {
        return precoDeVendaTotal;
    }
    /**
     * 
     * @param precoDeVendaTotal 
     */
    public void setPrecoDeVendaTotal(double precoDeVendaTotal) {
        this.precoDeVendaTotal = precoDeVendaTotal;
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
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @param nome the nome to set
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * @return the marca
     */
    public String getMarca() {
        return marca;
    }

    /**
     * @param marca the marca to set
     */
    public void setMarca(String marca) {
        this.marca = marca;
    }

    /**
     * @return the peso
     */
    public double getPeso() {
        return peso;
    }

    /**
     * @param peso the peso to set
     */
    public void setPeso(double peso) {
        this.peso = peso;
    }

    /**
     * @return the dataCompra
     */
    public Date getDataCompra() {
        return dataCompra;
    }

    /**
     * @param dataCompra the dataCompra to set
     */
    public void setDataCompra(Date dataCompra) {
        this.dataCompra = dataCompra;
    }

    /**
     * @return the estoqueFisico
     */
    public int getEstoqueFisico() {
        return estoqueFisico;
    }

    /**
     * @param estoqueFisico the estoqueFisico to set
     */
    public void setEstoqueFisico(int estoqueFisico) {
        this.estoqueFisico = estoqueFisico;
    }

    /**
     * @return the estoqueMinimo
     */
    public int getEstoqueMinimo() {
        return estoqueMinimo;
    }

    /**
     * @param estoqueMinimo the estoqueMinimo to set
     */
    public void setEstoqueMinimo(int estoqueMinimo) {
        this.estoqueMinimo = estoqueMinimo;
    }

    /**
     * @return the precoDeCustoUnit
     */
    public double getPrecoDeCustoUnit() {
        return precoDeCustoUnit;
    }

    /**
     * @param precoDeCustoUnit the precoDeCustoUnit to set
     */
    public void setPrecoDeCustoUnit(double precoDeCustoUnit) {
        this.precoDeCustoUnit = precoDeCustoUnit;
    }

    /**
     * @return the precoDeCustoTotal
     */
    public double getPrecoDeCustoTotal() {
        return precoDeCustoTotal;
    }

    /**
     * @param precoDeCustoTotal the precoDeCustoTotal to set
     */
    public void setPrecoDeCustoTotal(double precoDeCustoTotal) {
        this.precoDeCustoTotal = precoDeCustoTotal;
    }

    /**
     * @return the transportadoraNome
     */
    public String getTransportadoraNome() {
        return transportadoraNome;
    }

    /**
     * @param transportadoraNome the transportadoraNome to set
     */
    public void setTransportadoraNome(String transportadoraNome) {
        this.transportadoraNome = transportadoraNome;
    }

    public List<Fornecedor> getFornecedores() {
        return fornecedores;
    }

    public void setFornecedores(List<Fornecedor> fornecedores) {
        this.fornecedores = fornecedores;
    }
    
    
  public static void incluir( Produto produto) {
      
      ProdutoDAO dao = new ProdutoDAO();
      
      int k=0;
      for (Produto prod : dao.consultar("")) {
          if( produto.getNome().equals(prod.getNome()) && produto.getMarca().equalsIgnoreCase(prod.getMarca())  && produto.getPeso() == prod.getPeso()) k++;
          
      }
      if( dao.gravar(produto)) JOptionPane.showMessageDialog(null, "Produto cadastrado com sucesso");
      else
   JOptionPane.showMessageDialog(null, "Produto nao cadastrado com sucesso");       
  }  
    public static void excluir( Produto produto) {
   ProdutoDAO dao = new ProdutoDAO();
        if( dao.remover(produto)) JOptionPane.showMessageDialog(null, "Produto removido com sucesso");

        
    }
   
     public static void actualizar( Produto produto) {
   ProdutoDAO dao = new ProdutoDAO();
        if( dao.atualizar(produto)) JOptionPane.showMessageDialog(null, "Produto actualizado com sucesso");

        
    }
   
}
