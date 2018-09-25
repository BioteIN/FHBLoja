/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import controle.ClienteDAO;
import controle.EnderecoDAO;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.swing.JOptionPane;

/**
 * @author Faruque
 *@author Helio
 * @author Biote
 */
@Entity(name = "endereco")
public class Endereco {
    @Id
    @GeneratedValue
    private int id;
    private String bairro;
    @OneToOne(cascade = CascadeType.ALL)
    private Cidade cidade;
    private  String  rua;
    private String numero;
    private String proximidades;
    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    public Cidade getCidade() {
        return cidade;
    }

    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
    }
    
    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }


    /**
     * @return the bairro
     */
    public String getBairro() {
        return bairro;
    }

    /**
     * @param bairro the bairro to set
     */
    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    /**
     * @return the rua
     */
    public String getRua() {
        return rua;
    }

    /**
     * @param rua the rua to set
     */
    public void setRua(String rua) {
        this.rua = rua;
    }

    /**
     * @return the numero
     */
    public String getNumero() {
        return numero;
    }

    /**
     * @param numero the numero to set
     */
    public void setNumero(String numero) {
        this.numero = numero;
    }

    /**
     * @return the proximidades
     */
    public String getProximidades() {
        return proximidades;
    }

    /**
     * @param proximidades the proximidades to set
     */
    public void setProximidades(String proximidades) {
        this.proximidades = proximidades;
    }

    @Override
    public String toString() {
        return "Endereco{" + "bairro=" + bairro + ", cidade=" + cidade + ", rua=" + rua + ", numero=" + numero + '}';
    }
    private static void incluir(Endereco endereco) {
        EnderecoDAO dao = new EnderecoDAO();
        if(dao.gravar(endereco))
            JOptionPane.showMessageDialog(null,"O Endereco foi registado com sucesso!");
    }
    
    private static void excluir(Endereco endereco) {
        EnderecoDAO dao = new EnderecoDAO();
        if(dao.remover(endereco))
            JOptionPane.showMessageDialog(null,"O Endereco foi removido com sucesso!");
    }
    private static void alterar(Endereco endereco) {
        EnderecoDAO dao = new EnderecoDAO();
        if(dao.atualizar(endereco))
            JOptionPane.showMessageDialog(null,"O Endereco foi alterado com sucesso!");
    }
    
    
    
}
