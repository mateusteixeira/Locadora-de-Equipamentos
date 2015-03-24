package com.datacoper.locacaoequipamentos.business.service;

public class ServiceImpl extends Service {
	public ServiceImpl(Class<?> classePesquisa) {
		super(classePesquisa);
	}
	
	public Class<?> getClassePesquisa() {
		return classePesquisa;
	}

	public void setClassePesquisa(Class<?> classePesquisa) {
		this.classePesquisa = classePesquisa;
	}
}
