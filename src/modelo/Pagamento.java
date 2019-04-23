/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import alerta.Alerta;
import controle.PedidoDAO;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.swing.JOptionPane;

/**
 * @author Faruque
 * @author Helio
 * @author Biote
 */
public class Pagamento {

    public static boolean pagarParcela(int numero, Contas conta, double valor) {
        int par = conta.getNumeroDeParcelas() - conta.getParcela();
        if (valor >= numero * conta.getValorPorParcela()) {
            if (par >= numero) {
                if (numero == par) {
                    conta.setPaga(true);
                    PedidoDAO dao = new PedidoDAO();
                    for (Pedido p : dao.consultar()) {
                        if (p.getContas().getId() == conta.getId()) {
                            p.setStatus("Finalizado");
                            Pedido.alterar(p);
                            break;
                        }
                    }
                }
                conta.setParcela(conta.getParcela() + numero);
                conta.setValorDevido(conta.getValorDevido() - numero * conta.getValorPorParcela());
                conta.setValorPago(conta.getValorPago() + numero * conta.getValorPorParcela());

                if (Caixa.liquidar(valor, numero * conta.getValorPorParcela(), Pagamento.trocar(numero * conta.getValorPorParcela(), valor))) {
                    Contas.alterar(conta);
                    Alerta.MostrarNotificacao("Pagamento", "Pagamento feito com sucesso");
                    return true;
                }
            } else {
                Alerta.errar("Pagamento", "Impossivel pagar este numero de parcelas, verifique o numero de parcelas ja pagas e tente novamente!!");
            }
        } else {
            Alerta.errar("Pagamento", "O valor recebido e insuficiente para este pagamento");
        }
        return false;
    }

    public static boolean pagarParcelaTotal(Contas conta, double valor) {
        int par = conta.getNumeroDeParcelas() - conta.getParcela();
        if (valor >= par * conta.getValorPorParcela()) {
            if (par > 0) {
                conta.setPaga(true);
                PedidoDAO dao = new PedidoDAO();
                for (Pedido p : dao.consultar()) {
                    if (p.getContas().equals(conta)) {
                        p.setStatus("Finalizado");
                        Pedido.alterar(p);
                        break;
                    }
                }
                conta.setValorPago(conta.getValorDevido());
                conta.setParcela(conta.getNumeroDeParcelas());
                conta.setValorDevido(0);
                if (Caixa.liquidar(valor, par * conta.getValorPorParcela(), Pagamento.trocar(par * conta.getValorPorParcela(), valor))) {
                    Contas.alterar(conta);
                    Alerta.MostrarNotificacao("Pagamento", "Pagamento feito com sucesso");
                    return true;
                }

            } else {
                Alerta.MostrarNotificacao("Pagamento", "Nao foi possivel pagar");
            }
        } else {
            JOptionPane.showMessageDialog(null, "O valor recebido e insuficiente para este pagamento");
        }
        return false;

    }

    public static double trocar(double conta, double valor) {
        double troco = valor - conta;
        return troco;
    }

    public static boolean pagarBalcao(ContaAReceber conta, double receber) {
        if (receber >= conta.getValorDevido()) {
            conta.setValorDevido(0);
            conta.setPaga(true);
            if (Caixa.liquidar(receber, conta.getValorDevido(), Pagamento.trocar(conta.getValorDevido(), receber))) {
                ContaAReceber.alterar(conta);
                Alerta.MostrarNotificacao("Pagamento", "Pagamento feito com sucesso");
                return true;
            }
        } else {
            Alerta.errar("Pagamento", "Valor recebido e insuficiente para liquidar a divida");
        }
        return false;
    }

    public static boolean pagarPrazo(Contas conta, double valorPago, double valorDesejado) {
        double con = conta.getValorDevido() - valorDesejado;
        if (con >= 0) {
            if (valorPago >= con) {
                conta.setValorDevido(con);
                if (con == 0) {
                    conta.setPaga(true);
                    PedidoDAO dao = new PedidoDAO();
                    for (Pedido p : dao.consultar()) {
                        if (p.getContas().getId() == conta.getId()) {
                            p.setStatus("Finalizado");
                            Pedido.alterar(p);
                            break;
                        }
                    }
                }
                if (Caixa.liquidar(valorPago, con, Pagamento.trocar(con, valorPago))) {
                    Contas.alterar(conta);
                    Alerta.MostrarNotificacao("Pagamento", "Pagamento feito com sucesso");
                    return true;
                }
            } else {

                Alerta.MostrarNotificacao("Pagamento", "Pagamento feito com sucesso");
            }
        } else {
            JOptionPane.showMessageDialog(null, "O valor em divida desta conta e de" + conta.getValorDevido());
        }
        return false;
    }

    public static boolean pagarPrazoTotal(Contas conta, double valorDesejado) {
        if (valorDesejado >= conta.getValorDevido()) {
            conta.setValorDevido(0);
            PedidoDAO dao = new PedidoDAO();
            for (Pedido p : dao.consultar()) {
                if (p.getContas().getId() == conta.getId()) {
                    p.setStatus("Finalizado");
                    Pedido.alterar(p);
                    break;
                }
            }
            conta.setPaga(true);
            if (Caixa.liquidar(valorDesejado, conta.getValorDevido(), Pagamento.trocar(conta.getValorDevido(), valorDesejado))) {
                Contas.alterar(conta);
                Alerta.MostrarNotificacao("Pagamento", "Pagamento feito com sucesso");
                return true;
            }
        } else {
            JOptionPane.showMessageDialog(null, "O valor em divida desta conta e de" + conta.getValorDevido());
        }
        return false;
    }

}
