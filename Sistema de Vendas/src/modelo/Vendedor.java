/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author bONGANI
 */
public class Vendedor extends Funcionario{
    
    private double percentagemDaVenda;
    

    /**
     * @return the percentagemDaVenda
     */
    public double getPercentagemDaVenda() {
        return percentagemDaVenda;
    }

    /**
     * @param percentagemDaVenda the percentagemDaVenda to set
     */
    public void setPercentagemDaVenda(double percentagemDaVenda) {
        this.percentagemDaVenda = percentagemDaVenda;
    }
    
    
}
