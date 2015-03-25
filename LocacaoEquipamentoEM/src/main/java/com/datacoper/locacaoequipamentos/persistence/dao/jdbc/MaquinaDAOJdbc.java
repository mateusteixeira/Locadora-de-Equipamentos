/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.datacoper.locacaoequipamentos.persistence.dao.jdbc;


/**
 *
 * @author Java
 */
public class MaquinaDAOJdbc {
/*
    Connection conectar;
    static final String id = "id";
    static final String marca = "marca";
    static final String modelo = "modelo";
    static final String numeroserie = "numeroserie";
    static final String custoaquisicao = "custoaquisicao";
    static final String dataaquisicao = "dataaquisicao";
    static final String tempomanutencao = "tempomanutencao";
    static final String valordiaria = "valordiaria";
    static final String datacadastro = "datacadastro";

//    public boolean insert(Maquina m) throws SQLException, Exception {
//        ResultSet rs2;
//        rs2 = ConnectionController.getInstance().pesquisa("select nextval('maquina_id_seq') as value");
//        rs2.next();
//        int nextval = rs2.getInt(1);
//        nextval = nextval + 1;
//
//        int rs;
//        if (nextval > 0) {
//            System.out.println("INSERT INTO maquina VALUES (default,'" + m.getMarca() + "','" + m.getModelo() + "'," + m.getNumeroSerie() + "," + m.getCustoAquisicao() + ",'" + m.getDataAquisicao() + "'," + m.getTempoManutencao() + "," + m.getCustoDiaria() + ",'" + m.getDataCadastro() + "')");
//            rs = ConnectionController.getInstance().insere("INSERT INTO maquina VALUES (default,'" + m.getMarca() + "','" + m.getModelo() + "'," + m.getNumeroSerie() + "," + m.getCustoAquisicao() + ",'" + m.getDataAquisicao() + "'," + m.getTempoManutencao() + "," + m.getCustoDiaria() + ",'" + m.getDataCadastro() + "')"
//            );
//
//            if (rs > 0) {
//                System.out.println("Cadastro realizado com sucesso!");
//                return true;
//            } else {
//                System.out.println("Erro: daoCliente - insert - Insercao do cliente");
//                System.out.println("INSERT INTO maquina VALUES (default,'" + m.getMarca() + "','" + m.getModelo() + "'," + m.getNumeroSerie() + "," + m.getCustoAquisicao() + ",'" + m.getDataAquisicao() + "'," + m.getTempoManutencao() + "," + m.getCustoDiaria() + ",'" + m.getDataCadastro() + ")");
//                return false;
//            }
//        } else {
//            System.out.println("Erro: daoCliente - insert - Nao foi possivel encontrar o nextval de cliente");
//            return false;
//        }
//    }
//
//    public List<Maquina> buscaAll(int ordem, int ascDesc) throws Exception {
//        String ord, a_d;
//        switch (ordem) {
//            case 1:
//                ord = marca;
//                break;
//            case 2:
//                ord = modelo;
//                break;
//            case 3:
//                ord = id;
//                break;
//
//            case 4:
//                ord = numeroserie;
//                break;
//            default:
//                ord = id;
//                break;
//        }
//        switch (ascDesc) {
//            case 1:
//                a_d = "ASC";
//                break;
//            case 2:
//                a_d = "DESC";
//                break;
//            default:
//                a_d = "ASC";
//                break;
//        }
//
//        ResultSet rs, rs2;
//        rs = ConnectionController.getInstance().pesquisa("SELECT * FROM maquina ORDER BY " + ord + " " + a_d + "");
//
//        List<Maquina> lista = new ArrayList<Maquina>();
//
//        try {
//            while (rs.next()) {
//                Maquina temp = new Maquina();
//                temp.setID(rs.getInt(id));
//                temp.setMarca(rs.getString(marca));
//                temp.setModelo(rs.getString(modelo));
//                temp.setNumeroSerie(rs.getDouble(numeroserie));
//                temp.setCustoAquisicao(rs.getDouble(custoaquisicao));
//                temp.setDataAquisicao(rs.getString(dataaquisicao));
//                temp.setTempoManutencao(rs.getShort(tempomanutencao));
//                temp.setCustoDiaria(rs.getDouble(valordiaria));
//                temp.setDataCadastro(rs.getString(datacadastro));
//                lista.add(temp);
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(MaquinaDAOJdbc.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return lista;
//    }
//
//    public List<Maquina> buscaEsp(int ordem, int ascDesc, int ID, String cont) throws Exception {
//        String ord, a_d;
//        switch (ordem) {
//            case 1:
//                ord = marca;
//                break;
//            case 2:
//                ord = modelo;
//                break;
//            case 3:
//                ord = id;
//                break;
//
//            case 4:
//                ord = numeroserie;
//                break;
//            default:
//                ord = id;
//                break;
//        }
//        switch (ascDesc) {
//            case 1:
//                a_d = "ASC";
//                break;
//            case 2:
//                a_d = "DESC";
//                break;
//            default:
//                a_d = "ASC";
//                break;
//        }
//
//        ResultSet rs = null, rs2;
//        switch (ID) {
//            case 1:
////                rs = FabricaDeConexao.getInstance().pesquisa("SELECT to_char(" + datanascimento + ",'dd/MM/yyyy'),to_char(" + datacadastro + ",'dd/MM/yyyy'),* FROM cliente WHERE " + nome + " ~* '" + cont + "' ORDER BY " + ord + " " + a_d + "");
//                rs = ConnectionController.getInstance().pesquisa("SELECT * FROM maquina WHERE " + marca + " ~* '" + cont + "' ORDER BY " + ord + " " + a_d + "");
//                break;
//            case 2:
//                rs = ConnectionController.getInstance().pesquisa("SELECT * FROM maquina WHERE " + modelo + " ~* " + cont + " ORDER BY " + ord + " " + a_d + "");
//                break;
//            case 3:
//                rs = ConnectionController.getInstance().pesquisa("SELECT * FROM maquina WHERE " + id + " ~* '" + cont + "' ORDER BY " + ord + " " + a_d + "");
//                break;
//            case 4:
//                rs = ConnectionController.getInstance().pesquisa("SELECT * FROM maquina WHERE " + numeroserie + " ~* '" + cont + "' ORDER BY " + ord + " " + a_d + "");
//                break;
//            default:
//                System.out.println("Insira um identificador correto, 1-nome, 2-id, 3-cpf, 4-telefone");
//                break;
//        }
//
//        List<Maquina> lista = new ArrayList<Maquina>();
//
//        try {
//            while (rs.next()) {
//                Maquina temp = new Maquina();
//                temp.setID(rs.getInt(id));
//                temp.setMarca(rs.getString(marca));
//                temp.setModelo(rs.getString(modelo));
//                temp.setNumeroSerie(rs.getDouble(numeroserie));
//                temp.setCustoAquisicao(rs.getDouble(custoaquisicao));
//                temp.setDataAquisicao(rs.getString(dataaquisicao));
//                temp.setTempoManutencao(rs.getShort(tempomanutencao));
//                temp.setCustoDiaria(rs.getDouble(valordiaria));
//                temp.setDataCadastro(rs.getString(datacadastro));
//                lista.add(temp);
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(MaquinaDAOJdbc.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return lista;
//    }
//
//    public boolean updateMaquina(Maquina m) throws Exception {
//        System.out.println("UPDATE maquina SET " + marca + "='" + m.getMarca() + "'," + modelo + "='" + m.getModelo() + "'," + numeroserie + "=" + m.getNumeroSerie() + "," + custoaquisicao + "=" + m.getCustoAquisicao() + "," + dataaquisicao + "='" + m.getDataAquisicao() + "'," + tempomanutencao + "=" + m.getTempoManutencao() + "," + valordiaria + "=" + m.getCustoDiaria() + "," + datacadastro + "='" + m.getDataCadastro() + "' WHERE " + id + "=" + m.getID());
//        int rs = ConnectionController.getInstance().insere("UPDATE maquina SET " + marca + "='" + m.getMarca() + "'," + modelo + "='" + m.getModelo() + "'," + numeroserie + "=" + m.getNumeroSerie() + "," + custoaquisicao + "=" + m.getCustoAquisicao() + "," + dataaquisicao + "='" + m.getDataAquisicao() + "'," + tempomanutencao + "=" + m.getTempoManutencao() + "," + valordiaria + "=" + m.getCustoDiaria() + "," + datacadastro + "='" + m.getDataCadastro() + "' WHERE " + id + "=" + m.getID());
//
//        if (rs > 0) {
//            System.out.println("Cliente Editado com Sucesso!");
//            return true;
//        } else {
//            System.out.println("Falha ao Editar Cliente");
//            System.out.println("UPDATE maquina SET " + marca + "='" + m.getMarca() + "'," + modelo + "='" + m.getModelo() + "'," + numeroserie + "=" + m.getNumeroSerie() + "," + custoaquisicao + "=" + m.getCustoAquisicao() + "," + dataaquisicao + "='" + m.getDataAquisicao() + "'," + tempomanutencao + "=" + m.getTempoManutencao() + "," + valordiaria + "=" + m.getCustoDiaria() + "," + datacadastro + "='" + m.getDataCadastro() + "' WHERE " + id + "=" + m.getID());
//
//            return false;
//        }
//    }
//
//    public boolean excluiMaquina(Maquina m) throws Exception {
//        System.out.println("DELETE FROM maquina WHERE " + id + "=" + m.getID());
//        int rs = ConnectionController.getInstance().insere("DELETE FROM maquina WHERE " + id + "=" + m.getID());
//
//        if (rs > 0) {
//            System.out.println("Cliente Editado com Sucesso!");
//            return true;
//        } else {
//            System.out.println("Falha ao Editar Cliente");
//            System.out.println("DELETE FROM maquina WHERE " + id + "=" + m.getID());
//
//            return false;
//        }
//    }*/

}
