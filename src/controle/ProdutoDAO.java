/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import java.util.List;
import javax.swing.JOptionPane;
import modelo.Produto;
import org.hibernate.Criteria;
import org.hibernate.Query;
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
public class ProdutoDAO {

    private SessionFactory sf = ArquivoUtil.getSessionFactory();

    public boolean gravar(Produto cliente) {
        Session sec = sf.openSession();
        Transaction tx = sec.beginTransaction();

        try {
            sec.save(cliente);
            tx.commit();
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        } finally {
            sec.close();
        }
    }

    public boolean atualizar(Produto cliente) {
        Session sec = sf.openSession();
        Transaction tx = sec.beginTransaction();

        try {
            sec.merge(cliente);
            tx.commit();
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        } finally {
            sec.close();
        }
    }

    public boolean remover(Produto cliente) {
        Session sec = sf.openSession();
        Transaction tx = sec.beginTransaction();

        try {
            sec.delete(cliente);
            tx.commit();
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        } finally {
            sec.close();
        }
    }

    public List<Produto> consultar() {
        Session sec = sf.openSession();

        Query c = sec.createQuery("from produtos01");
        List<Produto> list = c.list();
        if (list.size() > 0) {
            return c.list();
        } else {
            return null;
        }

    }
}