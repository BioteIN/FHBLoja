/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import controle.ClienteDAO;
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
 *@author Helio
 * @author Biote
 */
@Entity(name = "cliente")
public class Cliente {
    @Id
    @GeneratedValue
    private int id;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "enderecoId")
    private Endereco  endereco;
    @Temporal(TemporalType.DATE)
    private Date nascimento;
    @Temporal(TemporalType.DATE)
    private Date cadastroData;
    private String nome;
    private String  telefone1;
    private String  telefone2;
    private String  naturalidade;
    private String  observacoes;
    
    /**
     * 
     * @return 
     */
    public String getNome() {
        return nome;
    }
    /**
     * 
     * @param nome 
     */
    public void setNome(String nome) {
        this.nome = nome;
    }
    /**
     * 
     * @return 
     */
    public String getObservacoes() {
        return observacoes;
    }
/**
 * 
 * @param observacoes 
 */
    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }
    /**
     * 
     * @return 
     */
    public Endereco getEndereco() {
        return endereco;
    }
    /**
     * 
     * @param endereco 
     */
    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
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
     * @return the nascimento
     */
    public Date getNascimento() {
        return nascimento;
    }

    /**
     * @param nascimento the nascimento to set
     */
    public void setNascimento(Date nascimento) {
        this.nascimento = nascimento;
    }

    /**
     * @return the cadastroData
     */
    public Date getCadastroData() {
        return cadastroData;
    }

    /**
     * @param cadastroData the cadastroData to set
     */
    public void setCadastroData(Date cadastroData) {
        this.cadastroData = cadastroData;
    }

    /**
     * @return the telefone1
     */
    public String getTelefone1() {
        return telefone1;
    }

    /**
     * @param telefone1 the telefone1 to set
     */
    public void setTelefone1(String telefone1) {
        this.telefone1 = telefone1;
    }

    /**
     * @return the telefone2
     */
    public String getTelefone2() {
        return telefone2;
    }

    /**
     * @param telefone2 the telefone2 to set
     */
    public void setTelefone2(String telefone2) {
        this.telefone2 = telefone2;
    }

    /**
     * @return the naturalidade
     */
    public String getNaturalidade() {
        return naturalidade;
    }

    /**
     * @param naturalidade the naturalidade to set
     */
    public void setNaturalidade(String naturalidade) {
        this.naturalidade = naturalidade;
    }

    private static void incluir(Cliente cliente) {
        ClienteDAO dao = new ClienteDAO();
          int k=0;   

        for ( Cliente cli :  dao.consultar("")) {
         if( cliente.getNome().equalsIgnoreCase(cli.getNome()) && cliente.getTelefone1().equalsIgnoreCase(cli.getTelefone1()) && cliente.getEndereco().equals(cli.getEndereco()) && 
                 cliente.getNascimento().equals(cli.getNascimento() ) && cliente.getNaturalidade().equalsIgnoreCase(cli.getNaturalidade())) k++; 
        }
         if ( k==0) 
           if(dao.gravar(cliente))
        JOptionPane.showMessageDialog(null,"O cliente foi registado com sucesso");
           else 
        JOptionPane.showMessageDialog(null,"O cliente nao foi  registado com sucesso");
       
    }
    
    private static void excluir(Cliente cliente) {
        ClienteDAO dao = new ClienteDAO();
        if(dao.remover(cliente))
            JOptionPane.showMessageDialog(null,"O cliente foi removido com sucesso");
    }
    private static void alterar(Cliente cliente) {
        ClienteDAO dao = new ClienteDAO();
        if(dao.atualizar(cliente))
            JOptionPane.showMessageDialog(null,"O cliente foi alterado com sucesso");
    }
    
    
    
    
    
    
}
