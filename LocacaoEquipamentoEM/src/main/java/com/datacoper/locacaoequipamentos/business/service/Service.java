package com.datacoper.locacaoequipamentos.business.service;

import java.util.List;

import com.datacoper.locacaoequipamentos.persistence.dao.interfaces.DAO;
import com.datacoper.locacaoequipamentos.persistence.dao.interfaces.DAOFactory;
import com.datacoper.locacaoequipamentos.persistence.parameters.ParametersLoader;

public abstract class Service implements com.datacoper.locacaoequipamentos.common.service.Service {
	protected Class<?> classePesquisa;
	private static DAO dao;
	
	public Service (Class<?> classePesquisa) {
		this.classePesquisa = classePesquisa;
		
		if (dao == null) {
			try {
				dao = (DAO) DAOFactory.instantiate(ParametersLoader.getClassDao(classePesquisa));
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}

	@Override
	public <T> List<T> pesquisar(String filtro, String valorFiltro) {
		try {
			return dao.pesquisar(filtro, valorFiltro);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
