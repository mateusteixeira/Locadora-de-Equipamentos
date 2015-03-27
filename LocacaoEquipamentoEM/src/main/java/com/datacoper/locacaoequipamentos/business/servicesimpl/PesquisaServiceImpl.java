package com.datacoper.locacaoequipamentos.business.servicesimpl;

import java.util.List;

import com.datacoper.locacaoequipamentos.common.exception.BusinessException;
import com.datacoper.locacaoequipamentos.common.service.interfaces.PesquisaService;
import com.datacoper.locacaoequipamentos.persistence.dao.DAO;
import com.datacoper.locacaoequipamentos.persistence.dao.DAOFactory;

public  class PesquisaServiceImpl implements PesquisaService {
	protected Class<?> classePesquisa;
	private static DAO dao;
	
	public PesquisaServiceImpl(Class classePesquisa) {
		super();
		this.classePesquisa = classePesquisa;

		if (dao == null) {
			try {
				dao = (DAO) DAOFactory.getInstance(classePesquisa);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}

	@Override
	public <T> List<T> pesquisar(String filtro, String valorFiltro) {
		try {
			return dao.search(filtro, valorFiltro, classePesquisa);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public <T> T pesquisarById(T object) throws BusinessException {
		try {
			return (T)dao.findByIdObj(object);
		} catch (Exception ex) {
			throw new BusinessException(ex);
		}
	}
}
