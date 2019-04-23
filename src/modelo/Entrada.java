/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import controle.EntradaDAO;
import java.util.Date;
import java.util.List;
import javafx.scene.control.Alert;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.swing.JOptionPane;

/**
 *@author Faruque
 *@author Helio
 * @author Biote
 */


@Entity
@Table( name = "Entrada")

public class Entrada {
    @Id
    @GeneratedValue
    private int id;
    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "idProduto")
    private Produto produto;
    private int quantidade;
    private String tipoDeEntrada;
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataDeEntrada;
    private String obs;
    private boolean status=true;
    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }
    
    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }
    

    /**
     * @return the quantidade
     */
    public int getQuantidade() {
        return quantidade;
    }

    /**
     * @param quantidade the quantidade to set
     */
    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    /**
     * @return the tipoDeEntrada
     */
    public String getTipoDeEntrada() {
        return tipoDeEntrada;
    }

    /**
     * @param tipoDeEntrada the tipoDeEntrada to set
     */
    public void setTipoDeEntrada(String tipoDeEntrada) {
        this.tipoDeEntrada = tipoDeEntrada;
    }

    /**
     * @return the dataDeEntrada
     */
    public Date getDataDeEntrada() {
        return dataDeEntrada;
    }

    /**
     * @param dataDeEntrada the dataDeEntrada to set
     */
    public void setDataDeEntrada(Date dataDeEntrada) {
        this.dataDeEntrada = dataDeEntrada;
    }
    /**
     * Metodo para salvar os dados para a base de dados
     * baseando-se no metodo gravar da classe EntradaDAO
     * @param entrada
     * @param quantidade 
     */
    public static void incluir(Entrada entrada,int quantidade){
        EntradaDAO dao = new EntradaDAO();
        int qtd= entrada.getProduto().getEstoqueFisico()+quantidade;
        Date data =new Date();
        entrada.setQuantidade(quantidade);
        entrada.getProduto().setEstoqueFisico(qtd);
        entrada.setDataDeEntrada(data);
        if(dao.gravar(entrada)) {
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
            alerta.setTitle("Feito");
            alerta.setContentText("Entrada foi registada com sucesso!!!");
            alerta.show();
        }
        Produto.actualizar(entrada.getProduto());
    }
    
    /**
     *     Metodo para  excluir logicamente os dados;
     *     
     *
     * @param entrada
     */
    public static void excluir( Entrada entrada){
        entrada.setStatus(false);
        Entrada.alterar(entrada);
    }
/**
 * 
 * @return 
 */
    public boolean isStatus() {
        return status;
    }
/**
 * 
 * @param status 
 */
    public void setStatus(boolean status) {
        this.status = status;
    }
    /**
     * Metodo para alterar os dados para a base de dados baseando-se no
     * metodo actualizar da classe EntradaDAO
     * @param entrada 
     */
    public static void alterar(Entrada entrada){
        EntradaDAO dao = new EntradaDAO();
        if(dao.atualizar(entrada)) {
             Alert alerta = new Alert(Alert.AlertType.INFORMATION);
            alerta.setTitle("Feito");
            alerta.setContentText("Os dados da entrada foram alterados com sucesso!");
            alerta.show();
        }
    }
  /**
   * faz a busca na base dados
   * @return 
   */
    
    public static  List<Entrada> buscar(){
      
        EntradaDAO dao = new EntradaDAO();
        return dao.consultar();
    }
}
