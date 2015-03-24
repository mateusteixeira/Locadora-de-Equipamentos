package com.datacoper.locacaoequipamentos.persistence.parameters;

public class ParametersDAO {

	private Class<?> classe;
	private Class<?> classDao;
	private Class<?> classJPA;
	private Class<?> classJDBC;

	public ParametersDAO() {
		// TODO Auto-generated constructor stub
	}

	public ParametersDAO(Class<?> classe, Class<?> classDao, Class<?> classJPA, Class<?> classJDBC) {
		super();
		this.classe = classe;
		this.classDao = classDao;
		this.classJPA = classJPA;
		this.classJDBC = classJDBC;
	}

	public Class<?> getClasse() {
		return classe;
	}

	public void setClasse(Class<?> classe) {
		this.classe = classe;
	}

	public Class<?> getClassDao() {
		return classDao;
	}

	public void setClassDao(Class<?> classDao) {
		this.classDao = classDao;
	}

	public Class<?> getClassJPA() {
		return classJPA;
	}

	public void setClassJPA(Class<?> classJPA) {
		this.classJPA = classJPA;
	}

	public Class<?> getClassJDBC() {
		return classJDBC;
	}

	public void setClassJDBC(Class<?> classJDBC) {
		this.classJDBC = classJDBC;
	}

}
