/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;


import controle.FornecedorDAO;
import java.util.Date;
import java.util.List;
import javafx.scene.control.Alert;
import javax.persistence.CascadeType;
import javax.persistence.Column;
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
@Entity( name = "fornecedor")
public class Fornecedor {
    @Id
    @GeneratedValue
    private int id;
    @Column(name =" Endereco")
    private String endereco;
    @Column(name=" pessoaTipo")
    private String pessoaTipo;
    @ManyToOne
    private Produto produto;
    private String nome;

    private String slogan;
    private String nuit;
    private String contaCorrente;
    private String observacoes;
    private String representante;
    private String fax;
    private String telefone;
    private String telefone2;
    @Temporal(TemporalType.DATE)
    private Date dataCadastro;
    private Date fundacao;
    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    

    public String getEndereco() {
        return endereco;
    }

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
     * @return the pessoaTipo
     */
    public String getPessoaTipo() {
        return pessoaTipo;
    }

    /**
     * @param pessoaTipo the pessoaTipo to set
     */
    public void setPessoaTipo(String pessoaTipo) {
        this.pessoaTipo = pessoaTipo;
    }

    /**
     * @return the nickName
     */
    public String getSlogan() {
        return slogan;
    }

    /**
     * @param nickName the nickName to set
     */
    public void setSlogan(String nickName) {
        this.slogan = nickName;
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
     * @return the contaCorrente
     */
    public String getContaCorrente() {
        return contaCorrente;
    }

    /**
     * @param contaCorrente the contaCorrente to set
     */
    public void setContaCorrente(String contaCorrente) {
        this.contaCorrente = contaCorrente;
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
     * @return the representante
     */
    public String getRepresentante() {
        return representante;
    }

    /**
     * @param representante the representante to set
     */
    public void setRepresentante(String representante) {
        this.representante = representante;
    }

    /**
     * @return the fax
     */
    public String getFax() {
        return fax;
    }

    /**
     * @param fax the fax to set
     */
    public void setFax(String fax) {
        this.fax = fax;
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
     * @return the dataCadastro
     */
    public Date getDataCadastro() {
        return dataCadastro;
    }

    /**
     * @param dataCadastro the dataCadastro to set
     */
    public void setDataCadastro(Date dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    /**
     * @return the fundacao
     */
    public Date getFundacao() {
        return fundacao;
    }

    /**
     * @param fundacao the fundacao to set
     */
    public void setFundacao(Date fundacao) {
        this.fundacao = fundacao;
    }
    /**
     * gravar o cliente
     * @param fornecedor 
     */

    public static void incluir(Fornecedor fornecedor) {
        int k =0;
        FornecedorDAO dao = new FornecedorDAO();
        for( Fornecedor forn : dao.consultar()) {
            if( fornecedor.getNome().equalsIgnoreCase(forn.getNome()) && fornecedor.getNuit().equalsIgnoreCase(forn.getNuit())) k++;
        }
        if (k==0)
            if(dao.gravar(fornecedor)){
                Alert alerta = new Alert(Alert.AlertType.INFORMATION);
                alerta.setTitle("Feito");
                alerta.setContentText("O Fornecedor foi cadastrado com sucesso!");
                alerta.show();
            }
            else {
                Alert alerta = new Alert(Alert.AlertType.WARNING);
                alerta.setTitle("Repeticao");
                alerta.setContentText("O Fornecedor nao foi cadastrado com sucesso!");
                alerta.show();
            }
    }
    /**
     * excluir fornecedor
     * @param fornecedor 
     */
    public static void excluir(Fornecedor fornecedor) {
        FornecedorDAO dao = new FornecedorDAO();
        if(dao.remover(fornecedor)) JOptionPane.showMessageDialog(null,"Fornecedor foi removido com sucesso!");
    }
    /**
     * alterar forncedor
     * @param fornecedor 
     */
    public static void alterar(Fornecedor fornecedor) {
        FornecedorDAO dao = new FornecedorDAO();
        if(dao.atualizar(fornecedor)) if(dao.atualizar(fornecedor)){
             Alert alerta = new Alert(Alert.AlertType.INFORMATION);
            alerta.setTitle("Feito");
            alerta.setContentText("O cliente foi alterado com sucesso!");
            alerta.show();
        }
    }
     public static List<Fornecedor> buscar(){
        FornecedorDAO dao = new FornecedorDAO();
        return dao.consultar();
    }
    /**
     * @return the produto
     */
    public Produto getProduto() {
        return produto;
    }

    /**
     * @param produto the produto to set
     */
    public void setProduto(Produto produto) {
        this.produto = produto;
    }
    
}
