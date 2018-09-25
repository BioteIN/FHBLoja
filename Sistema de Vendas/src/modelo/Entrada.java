/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.util.Date;
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
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idProduto")
    private Produto produto;
    private int quantidade;
    private String tipoDeEntrada;
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataDeEntrada;

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
    
    
    
}
