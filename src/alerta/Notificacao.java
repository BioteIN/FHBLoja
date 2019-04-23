/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alerta;

import controle.DiferencaData;
import java.util.Date;
import modelo.Produto;

/**
 *
 * @author Honwana
 */
public class Notificacao {
    public static void produtos(){
        Date data = new Date();
        for(Produto v : Produto.buscar()){
            if(data.compareTo(v.getMarca())==1){
                Alerta.MostrarNotificacao("Produto Expirado",v.getNome() + " Ja se encontra expirado!!!");
            }
        }
    }
    public static void produtosPrestes(){
        Date data = new Date();
        for(Produto v : Produto.buscar()){
            if(DiferencaData.dataDif(data,v.getMarca())<=10 && DiferencaData.dataDif(data,v.getMarca())>0){
                Alerta.MostrarNotificacao("Produto Prestes",v.getNome() + " Esta prestes a expirar\nFaltam "+DiferencaData.dataDif(data,v.getMarca())+ " dias para expirar");
            }
        }
    }
}
