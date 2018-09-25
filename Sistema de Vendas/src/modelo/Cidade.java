/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import controle.CidadeDAO;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.swing.JOptionPane;

/**
 * @author Faruque
 *@author Helio
 * @author Biote
 */
@Entity(name = "cidade")
public class Cidade {
    @Id
    @Column(name = "codigo")
    @GeneratedValue
    private  int codigo;
    @Column(name = "nome_cidade")
    private String nome;

    /**
     * @return the codigo
     */
    public int getCodigo() {
        return codigo;
    }

    /**
     * @param codigo the codigo to set
     */
    public void setCodigo(int codigo) {
        this.codigo = codigo;
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

    @Override
    public String toString() {
        return "Cidade{" + "nome=" + nome + '}';
    }
  
    private static void incluir(Cidade cidade) {
        CidadeDAO dao = new CidadeDAO();
        if(dao.gravar(cidade)) JOptionPane.showMessageDialog(null,"A cidade foi registada com sucesso!");
    }
    private static void excluir(Cidade cidade) {
        CidadeDAO dao = new CidadeDAO();
        if(dao.remover(cidade)) JOptionPane.showMessageDialog(null,"A cidade foi removida com sucesso!");
    }
    private static void alterar(Cidade cidade) {
        CidadeDAO dao = new CidadeDAO();
        if(dao.atualizar(cidade)) JOptionPane.showMessageDialog(null,"A cidade foi alterada com sucesso!");
    }
    
    
   }
