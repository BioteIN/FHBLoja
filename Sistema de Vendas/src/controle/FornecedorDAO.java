/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import java.util.List;
import javax.swing.JOptionPane;
import modelo.Endereco;
import modelo.Entrada;
import modelo.Fornecedor;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import util.ArquivoUtil;

/**
 *
 * @author bONGANI
 */
public class FornecedorDAO {
    public boolean gravar(Fornecedor cliente){
        SessionFactory sf = ArquivoUtil.getSessionFactory();
        Session sec = sf.openSession();
        Transaction tx = sec.beginTransaction();
        
        try {
            sec.save(cliente);
            tx.commit();
            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            return false;
        } finally{
            sec.close();
        }
    }
    public boolean atualizar(Fornecedor cliente){
        SessionFactory sf = ArquivoUtil.getSessionFactory();
        Session sec = sf.openSession();
        Transaction tx = sec.beginTransaction();
        
        try {
            sec.merge(cliente);
            tx.commit();
            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            return false;
        } finally{
            sec.close();
        }
    }
    public boolean remover(Fornecedor cliente){
        SessionFactory sf = ArquivoUtil.getSessionFactory();
        Session sec = sf.openSession();
        Transaction tx = sec.beginTransaction();
        
        try {
            sec.delete(cliente);
            tx.commit();
            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            return false;
        } finally{
            sec.close();
        }
    }
    public List<Fornecedor> consultar(String chave){
        SessionFactory sf = ArquivoUtil.getSessionFactory();
        Session sec = sf.openSession();
        
        Criteria c = sec.createCriteria(Endereco.class);
        c.add(Restrictions.like("nome", chave, MatchMode.ANYWHERE));
        
        List<Endereco> list = c.list();
        if(list.size()>0){
            return c.list();
        } else{
            JOptionPane.showMessageDialog(null,"Nao encontrado!");
            return null;
        }
    }
}