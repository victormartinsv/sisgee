package br.cefetrj.sisgee.control;

import java.util.List;

import br.cefetrj.sisgee.model.dao.GenericDAO;
import br.cefetrj.sisgee.model.dao.PersistenceManager;
import br.cefetrj.sisgee.model.entity.TermoRescisao;

public class TermoRescisaoServices {
	
	public static List<TermoRescisao> listarTermoRescisao(){
		GenericDAO<TermoRescisao> termoRescisaoDao = PersistenceManager.createGenericDAO(TermoRescisao.class);
		return termoRescisaoDao.buscarTodos();
	}
	
	public static void incluirTermoRescisao(TermoRescisao termoRescisao){
		GenericDAO<TermoRescisao> termoRescisaoDao = PersistenceManager.createGenericDAO(TermoRescisao.class);		
		PersistenceManager.getTransaction().begin();
		try{
			termoRescisaoDao.incluir(termoRescisao);
			PersistenceManager.getTransaction().commit();
		}catch(Exception e){
			System.out.println(e);
			PersistenceManager.getTransaction().rollback();
		}
	}

}
