package com.datacoper.locacaoequipamentos.business.servicesimpl;

import java.util.List;

import com.datacoper.locacaoequipamentos.common.service.interfaces.PesquisaService;
import com.datacoper.locacaoequipamentos.persistence.dao.DAO;
import com.datacoper.locacaoequipamentos.persistence.dao.DAOFactory;

public abstract class PesquisaServiceImpl implements PesquisaService {
	protected Class<?> classePesquisa;
	private static DAO dao;

	public PesquisaServiceImpl(Class<?> classe) {
		this.classePesquisa = classe;

		if (dao == null) {
			try {
				dao = (DAO) DAOFactory.getInstance(classe);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}

	@Override
	public <T> List<T> pesquisar(String filtro, String valorFiltro) {
		try {
			return dao.search(filtro, valorFiltro);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
