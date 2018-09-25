/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import controle.CidadeDAO;
import controle.EnderecoDAO;

/**
 *
 * @author bONGANI
 */
public class Teste {
    public static void main(String[] args) {
//       Cidade c = new Cidade();
//        CidadeDAO dao = new CidadeDAO();
//       
//        for(Cidade ci: dao.consultar("")){
//            c.setNome(ci.getNome());
//            c.setCodigo(ci.getCodigo());
//        }
//        
//        Endereco endereco = new Endereco();
//        endereco.setBairro("Namutekeliwa");
//        endereco.setNumero("007");
//        endereco.setProximidades("007");
//        endereco.setCidade(c);
        EnderecoDAO da = new EnderecoDAO();
        da.consultar("");
    }
}
