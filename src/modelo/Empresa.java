/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import controle.EmpresaDAO;
import java.util.List;
import javafx.scene.control.Alert;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *
 * @author bONGANI
 */
@Entity(name="empresa")
public class Empresa {
    
    @Id
    private int id;
    private String email;
    private String nome;
    private String telefone;
    private String nomeAdmin;
    private String senhaAdmin;
    
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
     * @return the telefone
     */
    public String getTelefone() {
        return telefone;
    }

    /**
     * @param telefone the telefone to set
     */
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    /**
     * @return the nomeAdmin
     */
    public String getNomeAdmin() {
        return nomeAdmin;
    }

    /**
     * @param nomeAdmin the nomeAdmin to set
     */
    public void setNomeAdmin(String nomeAdmin) {
        this.nomeAdmin = nomeAdmin;
    }

    /**
     * @return the senhaAdmin
     */
    public String getSenhaAdmin() {
        return senhaAdmin;
    }

    /**
     * @param senhaAdmin the senhaAdmin to set
     */
    public void setSenhaAdmin(String senhaAdmin) {
        this.senhaAdmin = senhaAdmin;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public static void incluir(Empresa cliente) {
        EmpresaDAO dao = new EmpresaDAO();
          int k=0;   
         if(dao.consultar()!=null){
        for ( Empresa cli :  dao.consultar()) {
         if( cliente.getNome().equalsIgnoreCase(cli.getNome()) && cliente.getTelefone().equalsIgnoreCase(cli.getTelefone()) && cliente.getEmail().equals(cli.getEmail()) && 
                 cliente.getSenhaAdmin().equals(cli.getSenhaAdmin()) && cliente.getNomeAdmin().equalsIgnoreCase(cli.getNomeAdmin())) k++; 
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
     * listar os clientes gravados
     * @return 
     */
    public static List<Empresa> buscar(){
        EmpresaDAO dao = new EmpresaDAO();
        return dao.consultar();
    }

}
