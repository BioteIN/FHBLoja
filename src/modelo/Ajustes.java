/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 *
 * @author Honwana
 */
@Entity(name = "ajustes")
public class Ajustes {
    @Id
    @GeneratedValue
    @Column(name = "idAjuste")
    private int Id;
    private boolean aniversario=false;
    private boolean alertaDeValidade=false;
    private boolean alertaDeAtualizacao=false;

    public boolean isAniversario() {
        return aniversario;
    }

    public void setAniversario(boolean aniversario) {
        this.aniversario = aniversario;
    }

    public boolean isAlertaDeValidade() {
        return alertaDeValidade;
    }

    public void setAlertaDeValidade(boolean alertaDeValidade) {
        this.alertaDeValidade = alertaDeValidade;
    }

    public boolean isAlertaDeAtualizacao() {
        return alertaDeAtualizacao;
    }

    public void setAlertaDeAtualizacao(boolean alertaDeAtualizacao) {
        this.alertaDeAtualizacao = alertaDeAtualizacao;
    }
    
    
    
}
