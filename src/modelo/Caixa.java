/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import alerta.Alerta;
import controle.CaixaDAO;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.swing.JOptionPane;

/**
 *
 * @author bONGANI
 */
@Entity(name = "box")
public class Caixa {

    @Id
    @GeneratedValue
    private int id;
    private boolean aberto = false;
    private double saldoCaixa=0;
    private double saldoFecho;
    private double saldoEntrada;
    private double valorEntrou;
    private double valorSaiu;
    @Temporal(TemporalType.TIME)
    private Date dataFecho;
    @Temporal(TemporalType.TIME)
    private Date dataAbertura;
    public static void abrir(Caixa caixa, double entrada) {
        caixa.setAberto(true);
        CaixaDAO dao = new CaixaDAO();
        int k = 0;
        if (dao.consultar() != null) {
            for (Caixa c : dao.consultar()) {
                if (c.isAberto()) {
                    k++;
                }
            }
        }
        if (k == 0) {
            caixa.setSaldoEntrada(entrada);
            if (dao.gravar(caixa)) {
                Alerta.MostrarNotificacao("Abertura do Caixa", "O Caixa foi aberto com sucesso!!!");
            }
        } else {
            Alerta.MostrarNotificacao("Abertura do Caixa", "Nao sera possivel abrir o caixa porque ja existe um aberto!!!");
        }
    }

    public static boolean liquidar(double entrar, double valor, double sair) {
        CaixaDAO dao = new CaixaDAO();
        int k = 0;
        double val;
        for (Caixa c : dao.consultar()) {
            if (c.isAberto()) {
                c.setValorEntrou(c.getValorEntrou() + entrar);
                val=c.getSaldoCaixa() + valor;
                System.out.print(val);
                c.setSaldoCaixa(val);
                k++;
                c.setValorSaiu(c.getValorSaiu() + sair);
                Caixa.alterar(c);
                break;
            }
        }
        if (k == 0) {
            Alerta.MostrarNotificacao("Caixa", "Nenhum caixa esta aberto para efectuar o pagamento!");
            return false;
        }
        return true;
    }

    public static void adicionar(double entrar, double valor, Caixa caixa) {
        CaixaDAO dao = new CaixaDAO();
        double val;
        for (Caixa c : dao.consultar()) {
            if (c.getId() == caixa.getId()) {
                val=c.getSaldoCaixa() + valor;
                c.setValorEntrou(c.getValorEntrou() + entrar);
                c.setSaldoCaixa(val);
                Caixa.alterar(c);
                break;
            }
        }
    }

    public static void retirar(double sair, Caixa caixa) {
        CaixaDAO dao = new CaixaDAO();
        for (Caixa c : dao.consultar()) {
            if (c.getId() == caixa.getId()) {
                c.setValorSaiu(c.getValorEntrou() + sair);
                c.setSaldoCaixa(c.getSaldoCaixa() - sair);
                if (c.getSaldoCaixa() < 0) {
                    Alerta.MostrarNotificacao("Retirar valor", "Impossivel retirar o valor porque o Caixa nao pode ter Saldo negativo");
                    break;
                } else {
                    Caixa.alterar(c);
                    Alerta.MostrarNotificacao("Retirar valor", "O valor foi retirado do Caixa");
                    break;
                }
            }
        }
    }

    /**
     * confimacao na hora de fechar a caixa
     *
     * @param caixa
     * @param valor
     */
    public static void fechar(Caixa caixa) {
        CaixaDAO dao = new CaixaDAO();
        caixa.setAberto(false);
        caixa.setDataFecho(new Date());
        dao.atualizar(caixa);
        
        Alerta.exito("Caixa","O caixa foi fechado");
    }

    public static List<Caixa> buscar() {
        CaixaDAO dao = new CaixaDAO();
        return dao.consultar();
    }

    /**
     * para alterar os dados da caixa
     *
     * @param caixa
     */
    public static void alterar(Caixa caixa) {
        CaixaDAO dao = new CaixaDAO();
        dao.atualizar(caixa);
    }
    
    @Override
    public String toString() {
        return "Caixa{" + "Id=" + getId() + ", aberto=" + isAberto() + ", saldoCaixa=" + getSaldoCaixa() + ", saldoFecho=" + getSaldoFecho() + ", saldoEntrada=" + getSaldoEntrada() + ", valorEntrou=" + getValorEntrou() + ", valorSaiu=" + getValorSaiu() + ", dataFecho=" + getDataFecho() + ", dataAbertura=" + getDataAbertura() + '}';
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
     * @return the aberto
     */
    public boolean isAberto() {
        return aberto;
    }

    /**
     * @param aberto the aberto to set
     */
    public void setAberto(boolean aberto) {
        this.aberto = aberto;
    }

    /**
     * @return the saldoCaixa
     */
    public double getSaldoCaixa() {
        return saldoCaixa;
    }

    /**
     * @param saldoCaixa the saldoCaixa to set
     */
    public void setSaldoCaixa(double saldoCaixa) {
        this.saldoCaixa = saldoCaixa;
    }

    /**
     * @return the saldoFecho
     */
    public double getSaldoFecho() {
        return saldoFecho;
    }

    /**
     * @param saldoFecho the saldoFecho to set
     */
    public void setSaldoFecho(double saldoFecho) {
        this.saldoFecho = saldoFecho;
    }

    /**
     * @return the saldoEntrada
     */
    public double getSaldoEntrada() {
        return saldoEntrada;
    }

    /**
     * @param saldoEntrada the saldoEntrada to set
     */
    public void setSaldoEntrada(double saldoEntrada) {
        this.saldoEntrada = saldoEntrada;
    }

    /**
     * @return the valorEntrou
     */
    public double getValorEntrou() {
        return valorEntrou;
    }

    /**
     * @param valorEntrou the valorEntrou to set
     */
    public void setValorEntrou(double valorEntrou) {
        this.valorEntrou = valorEntrou;
    }

    /**
     * @return the valorSaiu
     */
    public double getValorSaiu() {
        return valorSaiu;
    }

    /**
     * @param valorSaiu the valorSaiu to set
     */
    public void setValorSaiu(double valorSaiu) {
        this.valorSaiu = valorSaiu;
    }

    /**
     * @return the dataFecho
     */
    public Date getDataFecho() {
        return dataFecho;
    }

    /**
     * @param dataFecho the dataFecho to set
     */
    public void setDataFecho(Date dataFecho) {
        this.dataFecho = dataFecho;
    }

    /**
     * @return the dataAbertura
     */
    public Date getDataAbertura() {
        return dataAbertura;
    }

    /**
     * @param dataAbertura the dataAbertura to set
     */
    public void setDataAbertura(Date dataAbertura) {
        this.dataAbertura = dataAbertura;
    }

}
