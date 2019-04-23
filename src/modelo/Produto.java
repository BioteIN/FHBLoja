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
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.swing.JOptionPane;

/**
 * @author Faruque
 *@author Helio
 * @author Biote
 */
@Entity( name = "produtos01")
public class Produto {
    @Id
    @GeneratedValue
    private int id;
    private String nome;
    private double peso;
   @Temporal(TemporalType.DATE)
    private Date marca;
   @Temporal(TemporalType.DATE)
    private Date dataCompra;
    private int estoqueFisico;
    private int estoqueMinimo;
    private int quantidadeEmbalagem;
    @OneToMany(cascade = CascadeType.MERGE,mappedBy = "produto")
    private List<Fornecedor> fornecedores;
    private String descricao;
    private double precoDeVendaUnit;
    private double precoDeVendaTotal;
    private boolean  status = true;
    
    @ManyToOne
    private Pedido pedido;
    
  
   
    
  public static void incluir( Produto produto) {
      
      ProdutoDAO dao = new ProdutoDAO();
      
      int k=0;
//      for (Produto prod : dao.consultar()) {
//          if( produto.getNome().equals(prod.getNome()) ) k++;
//          
//      }
     dao.gravar(produto);
//      {
//   JOptionPane.showMessageDialog(null, "Produto nao cadastrado com sucesso");       
  }  
    public static void excluir( Produto produto) {
      produto.setStatus(false);
        
         ProdutoDAO dao = new ProdutoDAO();
        if( dao.atualizar(produto)) JOptionPane.showMessageDialog(null, "Produto removido com sucesso");

        
    }
   /**
    * Metodo para actualizar os dados
    * @param produto
    * @return 
    */
     public static boolean actualizar( Produto produto) {
   ProdutoDAO dao = new ProdutoDAO();
        if( dao.atualizar(produto))  return true;
        return false;
    }
     /**
      * Metodo que lista os Produtos
      * @return 
      */
     public static List<Produto> buscar(){
         ProdutoDAO dao = new ProdutoDAO();
         return dao.consultar();
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
     * @return the quantidadeEmbalagem
     */
    public int getQuantidadeEmbalagem() {
        return quantidadeEmbalagem;
    }

    /**
     * @param quantidadeEmbalagem the quantidadeEmbalagem to set
     */
    public void setQuantidadeEmbalagem(int quantidadeEmbalagem) {
        this.quantidadeEmbalagem = quantidadeEmbalagem;
    }

    /**
     * @return the precoDeVendaUnit
     */
    public double getPrecoDeVendaUnit() {
        return precoDeVendaUnit;
    }

    /**
     * @param precoDeVendaUnit the precoDeVendaUnit to set
     */
    public void setPrecoDeVendaUnit(double precoDeVendaUnit) {
        this.precoDeVendaUnit = precoDeVendaUnit;
    }

    /**
     * @return the precoDeVendaTotal
     */
    public double getPrecoDeVendaTotal() {
        return precoDeVendaTotal;
    }

    /**
     * @param precoDeVendaTotal the precoDeVendaTotal to set
     */
    public void setPrecoDeVendaTotal(double precoDeVendaTotal) {
        this.precoDeVendaTotal = precoDeVendaTotal;
    }

    /**
     * @return the fornecedores
     */
    public List<Fornecedor> getFornecedores() {
        return fornecedores;
    }

    /**
     * @param fornecedores the fornecedores to set
     */
    public void setFornecedores(List<Fornecedor> fornecedores) {
        this.fornecedores = fornecedores;
    }

    /**
     * @return the descricao
     */
    public String getDescricao() {
        return descricao;
    }

    /**
     * @param descricao the descricao to set
     */
    public void setDescricao(String descricao) {
        this.descricao = descricao;
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
     * @return the marca
     */
    public Date getMarca() {
        return marca;
    }

    /**
     * @param marca the marca to set
     */
    public void setMarca(Date marca) {
        this.marca = marca;
    }

    @Override
    public String toString() {
        return "Nome: " + nome ;
    }

    /**
     * @return the pedido
     */
    public Pedido getPedido() {
        return pedido;
    }

    /**
     * @param pedido the pedido to set
     */
    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    /**
     * @return the status
     */
    public boolean isStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(boolean status) {
        this.status = status;
    }
    

}
