/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.datacoper.locacaoequipamentos.common.model;

import java.util.ArrayList;

/**
 *
 * @author Mateus
 */
public class Locacao {

    private int idCliente;
    private int idLocacao;
    private ArrayList<Integer> idMaquina = new ArrayList();
    private String dataLocacao;
    private ArrayList<String> dataDevolucao = new ArrayList();
    private ArrayList<String> dataDevolvido = new ArrayList();
    private ArrayList<Double> valorUnitario = new ArrayList();
    private double total;
    private double valorPago;
    private ArrayList<Double> subTotal = new ArrayList();
   
    
    public void addIdMaquina(int i) {
        this.idMaquina.add(i);
    }

    public void addDataDevolucao(String data) {
        this.dataDevolucao.add(data);
    }

    public void addDataDevolvido(String data) {
        this.dataDevolvido.add(data);
    }

    public void addValorUnitario(Double a) {
        this.valorUnitario.add(a);
    }

    public int getIdLocacao() {
        return idLocacao;
    }

    public double getValorPago() {
        return valorPago;
    }

    public void setIdLocacao(int id) {
        this.idLocacao = id;
    }

    public void setValorPago(double t) {
        this.valorPago = t;
    }

    public Locacao() {
    }

    public ArrayList<String> getDataDevolucao() {
        return dataDevolucao;
    }

    public ArrayList<String> getDataDevolvido() {
        return dataDevolvido;
    }

    public void setDataDevolucao(ArrayList<String> dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }

    public void setDataDevolvido(ArrayList<String> dataDevolvido) {
        this.dataDevolvido = dataDevolvido;
    }

    public String getDataLocacao() {
        return dataLocacao;
    }

    public void setDataLocacao(String dataLocacao) {
        this.dataLocacao = dataLocacao;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public ArrayList<Integer> getIDMaquina() {
        return idMaquina;
    }

    public void setIDMaquina(ArrayList<Integer> idMaquina) {
        this.idMaquina = idMaquina;
    }

    public ArrayList<Double> getUnitario() {
        return valorUnitario;
    }

    public void setUnitario(ArrayList<Double> unitario) {
        this.valorUnitario = unitario;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public ArrayList<Double> getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(ArrayList<Double> subTotal) {
        this.subTotal = subTotal;
    }
    
    public void addSubTotal(double s){
        this.subTotal.add(s);
    }
    
}
