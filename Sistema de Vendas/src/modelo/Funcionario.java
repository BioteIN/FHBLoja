/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import controle.FornecedorDAO;
import controle.FuncionarioDAO;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.swing.JOptionPane;

/**
 * @author Faruque
 *@author Helio
 * @author Biote
 */
@Entity( name = "Funcionario")
public class Funcionario {
    
    @Id
    @GeneratedValue
    private int id;
    @Column(name="endereco")
    @OneToOne(cascade = CascadeType.ALL)
    private Endereco endereco;
    private String tipo;
    private String nome;
    private String login;
    private String senha;
    private String email;
    private String telefone;
    private String telefone2;
    private String nuit;
    private String observacoes;
    @Temporal(TemporalType.DATE)
    private Date dataContratacao;
    @Temporal(TemporalType.DATE)
    private Date dataNascimento;
    

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

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    

    /**
     * @return the tipo
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * @param tipo the tipo to set
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
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
     * @return the login
     */
    public String getLogin() {
        return login;
    }

    /**
     * @param login the login to set
     */
    public void setLogin(String login) {
        this.login = login;
    }

    /**
     * @return the senha
     */
    public String getSenha() {
        return senha;
    }

    /**
     * @param senha the senha to set
     */
    public void setSenha(String senha) {
        this.senha = senha;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the nuit
     */
    public String getNuit() {
        return nuit;
    }

    /**
     * @param nuit the nuit to set
     */
    public void setNuit(String nuit) {
        this.nuit = nuit;
    }

    /**
     * @return the observacoes
     */
    public String getObservacoes() {
        return observacoes;
    }

    /**
     * @param observacoes the observacoes to set
     */
    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    /**
     * @return the dataContratacao
     */
    public Date getDataContratacao() {
        return dataContratacao;
    }

    /**
     * @param dataContratacao the dataContratacao to set
     */
    public void setDataContratacao(Date dataContratacao) {
        this.dataContratacao = dataContratacao;
    }

    /**
     * @return the dataNascimento
     */
    public Date getDataNascimento() {
        return dataNascimento;
    }

    /**
     * @param dataNascimento the dataNascimento to set
     */
    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getTelefone2() {
        return telefone2;
    }

    public void setTelefone2(String telefone2) {
        this.telefone2 = telefone2;
    }

    private static void incluir(Funcionario funcionario) {
        FuncionarioDAO dao = new FuncionarioDAO();
        int k=0;
        for(Funcionario f : dao.consultar("")){
            if(funcionario.getLogin().equalsIgnoreCase(f.getLogin())) k++;
        }
        if(k==0){
            if(dao.gravar(funcionario)) JOptionPane.showMessageDialog(null,"Funcionario foi registado com sucesso!");
        } else{
            JOptionPane.showMessageDialog(null,"Impossivel registar! Ja existe funcionario com este login.");
        }
    }
    private static void excluir(Funcionario funcionario) {
        FuncionarioDAO dao = new FuncionarioDAO();
        if(dao.remover(funcionario)) JOptionPane.showMessageDialog(null,"Funcionario foi removido com sucesso!");
    }
    private static void alterar(Funcionario funcionario) {
        FuncionarioDAO dao = new FuncionarioDAO();
        if(dao.atualizar(funcionario)) JOptionPane.showMessageDialog(null,"Funcionario foi alterado com sucesso!");
    }
    
}
