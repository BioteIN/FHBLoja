/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import alerta.Alerta;
import controle.EntradaDAO;
import controle.SaidaDAO;
import java.util.Date;
import java.util.List;
import javafx.scene.control.Alert;
import javax.jws.Oneway;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.swing.JOptionPane;

/**
 * @author Faruque
 * @author Helio
 * @author Biote
 */
@Entity(name = "saida")
public class Saida {

    @Id
    @GeneratedValue
    @Column(name = "idSaida")
    private int id;
    private String motivo;
    private int quantidade;
    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "idProduto")
    private Produto produto;
    private boolean status;
    private Date dataDeSaida;
    private String obs;

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Date getDataDeSaida() {
        return dataDeSaida;
    }

    public void setDataDeSaida(Date dataDeSaida) {
        this.dataDeSaida = dataDeSaida;
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
     * @return the motivo
     */
    public String getMotivo() {
        return motivo;
    }

    /**
     * @param motivo the motivo to set
     */
    public void setMotivo(String motivo) {
        this.motivo = motivo;
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

    public static void incluir(Saida saida, int quantidade) {
        SaidaDAO dao = new SaidaDAO();
        int qtd = saida.getProduto().getEstoqueFisico() - quantidade;
        Date data = new Date();
        saida.getProduto().setEstoqueFisico(qtd);
        saida.setDataDeSaida(data);
        saida.setQuantidade(quantidade);
        if (qtd > 0) {
            if (dao.gravar(saida)) {
                Alert alerta = new Alert(Alert.AlertType.INFORMATION);
                alerta.setTitle("Feito");
                alerta.setContentText("Saida foi  cadastrada com sucesso!!");
                alerta.show();
            }
            Produto.actualizar(saida.getProduto());
        } else {
            Alerta.errar("Saida","Impossivel efectuar a saida!!!");
        }
    }

    public static void excluir(boolean st, Saida saida) {
        saida.setStatus(st);
        Saida.alterar(saida);
    }

    public static void alterar(Saida saida) {
        SaidaDAO dao = new SaidaDAO();
        if (dao.atualizar(saida)) {
            Alert alerta = new Alert(Alert.AlertType.INFORMATION);
            alerta.setTitle("Feito");
            alerta.setContentText("Os dados da saida foram atualizados com sucesso!");
            alerta.show();
        }
    }

    public static List<Saida> buscar() {

        SaidaDAO dao = new SaidaDAO();
        return dao.consultar();
    }

    /**
     * @return the obs
     */
    public String getObs() {
        return obs;
    }

    /**
     * @param obs the obs to set
     */
    public void setObs(String obs) {
        this.obs = obs;
    }
}
