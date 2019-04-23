/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import controle.ClienteDAO;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.swing.JOptionPane;

/**
 * @author Faruque
 *@author Helio
 * @author Biote
 */
@Entity(name = "clientes")
public class Cliente {
    @Id
    @GeneratedValue
    private int id;
    private String  endereco;
    @Temporal(TemporalType.DATE)
    private Date nascimento;
    @Temporal(TemporalType.DATE)
    private Date cadastroData;
    private String nome;
    private String numeroBI;
    private String tipoDoc;
    private String  telefone1;
    private String  naturalidade;
    private String  observacoes;
    private String  email;
    private boolean  status = true;
    
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
    public String getEndereco() {
        return endereco;
    }
    /**
     * 
     * @param endereco 
     */
    public void setEndereco(String endereco) {
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
    /**
     * gravar o cliente 
     * @param cliente 
     */
    public static void incluir(Cliente cliente) {
        ClienteDAO dao = new ClienteDAO();
          int k=0;   
         if(dao.consultar()!=null){
        for ( Cliente cli :  dao.consultar()) {
         if( cliente.getNome().equalsIgnoreCase(cli.getNome()) && cliente.getTelefone1().equalsIgnoreCase(cli.getTelefone1()) && cliente.getEndereco().equals(cli.getEndereco()) && 
                 cliente.getNascimento().equals(cli.getNascimento() ) && cliente.getNaturalidade().equalsIgnoreCase(cli.getNaturalidade())) k++; 
        }
         if ( k==0) 
           if(dao.gravar(cliente)){
            Alert alerta = new Alert(Alert.AlertType.INFORMATION);
            alerta.setTitle("Feito");
            alerta.setContentText("O cliente foi registado com sucesso!");
            alerta.show();   
           }
           else {
               Alert alerta = new Alert(Alert.AlertType.INFORMATION);
            alerta.setTitle("Feito");
            alerta.setContentText("O cliente nao foi registado!");
            alerta.show();
           }
       
         }else{
           if(dao.gravar(cliente)){
               Alert alerta = new Alert(Alert.AlertType.INFORMATION);
            alerta.setTitle("Feito");
            alerta.setContentText("O cliente foi registado com sucesso!");
            alerta.show();
           }
             
         }
    }
    /**
     * Excluir cliente
     * @param cliente 
     */
    public static void excluir(Cliente cliente) {
        ClienteDAO dao = new ClienteDAO();
        cliente.setStatus(false);
        if(dao.atualizar(cliente)){
             Alert alerta = new Alert(Alert.AlertType.INFORMATION);
            alerta.setTitle("Feito");
            alerta.setContentText("O cliente foi removido com sucesso!");
            alerta.show();
        }
    }
    /**
     * 
     * @param cliente 
     */
    public static void alterar(Cliente cliente) {
        ClienteDAO dao = new ClienteDAO();
        if(dao.atualizar(cliente)){
             Alert alerta = new Alert(Alert.AlertType.INFORMATION);
            alerta.setTitle("Feito");
            alerta.setContentText("O cliente foi alterado com sucesso!");
            alerta.show();
        }
    }
    /**
     * listar os clientes gravados
     * @return 
     */
    public static List<Cliente> buscar(){
        ClienteDAO dao = new ClienteDAO();
        return dao.consultar();
    }
    /**
     * @return the numeroBI
     */
    public String getNumeroBI() {
        return numeroBI;
    }

    /**
     * @param numeroBI the numeroBI to set
     */
    public void setNumeroBI(String numeroBI) {
        this.numeroBI = numeroBI;
    }
/**
 * 
 * @return 
 */
    @Override
    public String toString() {
        return   nome ;
    }

    /**
     * @return the tipoDoc
     */
    public String getTipoDoc() {
        return tipoDoc;
    }

    /**
     * @param tipoDoc the tipoDoc to set
     */
    public void setTipoDoc(String tipoDoc) {
        this.tipoDoc = tipoDoc;
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
    
    
    
    
    
    
}
