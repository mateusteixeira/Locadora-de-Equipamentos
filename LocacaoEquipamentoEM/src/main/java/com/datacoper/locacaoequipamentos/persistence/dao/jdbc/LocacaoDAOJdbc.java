/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.datacoper.locacaoequipamentos.persistence.dao.jdbc;

import com.datacoper.locacaoequipamentos.common.model.Locacao;
import com.datacoper.locacaoequipamentos.persistence.dao.interfaces.LocacaoDAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Java
 */
public class LocacaoDAOJdbc implements LocacaoDAO{

    static final String idCarrinho = "idcarrinho";
    static final String idMaquina = "idmaquina";
    static final String dataDevolucao = "datadevolucao";
    static final String dataDevolvido = "datadevolvido";
    static final String valorUnitario = "valorunitario";
    static final String subTotal = "subtotal";
    static final String idLocacao = "idlocacao";
    static final String idCliente = "idcliente";
    static final String dataLocacao = "datalocacao";
    static final String total = "total";
    static final String valorPago = "valorpago";
    
	@Override
	public List pesquisar(String filtro, String valorFiltro) {
		// TODO Auto-generated method stub
		return null;
	}

//    public boolean insert(Locacao locar) throws SQLException, Exception {
//        ResultSet rs2;
//        int rs;
//        rs2 = ConnectionController.getInstance().pesquisa("INSERT INTO locacao VALUES (default, " + locar.getIdCliente() + ",'" + locar.getDataLocacao() + "', " + locar.getTotal() + "," + locar.getValorPago() + ") returning " + idLocacao + "");
//        System.out.println("INSERT INTO locacao VALUES (default, " + locar.getIdCliente() + ",'" + locar.getDataLocacao() + "', " + locar.getTotal() + "," + locar.getValorPago() + ") returning " + idLocacao + "");
//        //DEIXEI VALOR PAGO = 0
//        rs2.next();
//        if (rs2.getInt(1) <= 0) {
//            System.out.println("Erro: daoLocacao - insert - Insercao de uma locacao");
//            return false;
//        } else {
//            ArrayList<Integer> listaMaquina = locar.getIDMaquina();
//            ArrayList<String> listaDataDevolução = locar.getDataDevolucao();
//            ArrayList<Double> listaValorUnitario = locar.getUnitario();
//            ArrayList<Double> listaSubTotal = locar.getSubTotal();
//
//            for (int i = 0; i < listaMaquina.size(); i++) {
//                Integer idMaquina = listaMaquina.get(i);
//                String dataDevolucao = listaDataDevolução.get(i);
//                String dataDevolvido = "";
//                Double valorUnitario = listaValorUnitario.get(i);
//                Double subTotal = listaSubTotal.get(i);
//                rs = ConnectionController.getInstance().insere("INSERT INTO carrinhodelocacao VALUES (default, " + idMaquina + ", '" + dataDevolucao + "', '" + dataDevolvido + "', " + valorUnitario + ", " + subTotal + ", " + rs2.getInt(1) + " )");
//                System.out.println("INSERT INTO carrinhodelocacao VALUES (default, " + idMaquina + ", '" + dataDevolucao + "', '" + dataDevolvido + "', " + valorUnitario + ", " + subTotal + ", " + rs2.getInt(1) + " )");
//            }
//
//        }
//        return true;
//    }
//
//    public List<Locacao> buscaCarrinhoAll() throws Exception {
//        ResultSet rs, rs2;
//        rs = ConnectionController.getInstance().pesquisa("SELECT * FROM carrinhodelocacao");
//
//        List<Locacao> lista = new ArrayList<Locacao>();
//
//        try {
//            while (rs.next()) {
//                Locacao temp = new Locacao();
//                temp.setIdLocacao(rs.getInt(idLocacao));
//                temp.addIdMaquina(rs.getInt(idMaquina));
//                temp.addDataDevolucao(rs.getString(dataDevolucao));
//                temp.addDataDevolvido(rs.getString(dataDevolvido));
//                temp.addValorUnitario(rs.getDouble(valorUnitario));
//                temp.addSubTotal(rs.getDouble(subTotal));
//                temp.setIdLocacao(rs.getInt(idLocacao));
//                lista.add(temp);
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(MaquinaDAOJdbc.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return lista;
//    }
//
//    @Override
//    public List<Locacao> buscaLocacoesAll() throws Exception {
//
//        ResultSet rs, rs2;
//        rs = ConnectionController.getInstance().pesquisa("SELECT * FROM locacao");
//
//        List<Locacao> lista = new ArrayList<Locacao>();
//
//        try {
//            while (rs.next()) {
//                Locacao temp = new Locacao();
//                temp.setIdLocacao(rs.getInt(idLocacao));
//                temp.setIdCliente(rs.getInt(idCliente));
//                temp.setDataLocacao(rs.getString(dataLocacao));
//                temp.setTotal(rs.getDouble(total));
//                temp.setValorPago(rs.getDouble(valorPago));
//                lista.add(temp);
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(MaquinaDAOJdbc.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return lista;
//    }
//
//    public boolean devolucao(Locacao maquinaLocada) throws Exception {
//        int rs, rs2;
//
//        rs = ConnectionController.getInstance().insere("DELETE FROM locacao WHERE idlocacao = " + maquinaLocada.getIdLocacao());
//        rs = ConnectionController.getInstance().insere("DELETE FROM carrinhodelocacao WHERE idlocacao = " + maquinaLocada.getIdLocacao() + "and idmaquina = " + maquinaLocada.getIDMaquina().get(0));
//
//        if (rs < 0) {
//            System.out.println("DELETE FROM locacao WHERE idlocacao = " + maquinaLocada.getIdLocacao());
//            System.out.println("DELETE FROM carrinhodelocacao WHERE idlocacao = " + maquinaLocada.getIdLocacao() + "and idmaquina = " + maquinaLocada.getIDMaquina().get(0));
//            return false;
//        } else {
//            return true;
//        }
//    }

}
