/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bhf;

import modelo.Produto;

/**
 *
 * @author Blessed be
 */
public class Teste {
    public static void main(String[] args) {
        Produto pr = new Produto();
        pr.setEstoqueFisico(300);
        pr.setEstoqueMinimo(20);
        pr.setPrecoDeVendaTotal(60);
        pr.setQuantidadeEmbalagem(40);
        pr.setEstoqueMinimo(10);
        pr.setNome("Manga");
        
        Produto.incluir(pr);
    }
}
