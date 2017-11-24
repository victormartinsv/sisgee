package br.cefetrj.sisgee.control;

import java.util.List;

import br.cefetrj.sisgee.model.dao.GenericDAO;
import br.cefetrj.sisgee.model.dao.PersistenceManager;
import br.cefetrj.sisgee.model.entity.TermoAditivo;

/**
 * Serviços de TermoAditivo. 
 * Trata a lógica de negócios
 * associada com a entidade TermoAditivo.
 * 
 * @author Paulo Cantuária
 * @since 1.0
 */
public class TermoAditivoServices {
	
	/**
	 * Recupera todos os Termos Aditivos e retorna em uma lista.
	 * 
	 * @return lista com todos os Termos Aditivos
	 */
	public static List<TermoAditivo> listarTermoAditivo(){
		GenericDAO<TermoAditivo> termoAditivoDao = PersistenceManager.createGenericDAO(TermoAditivo.class);
		return termoAditivoDao.buscarTodos();
	}	
	
	public static void incluirTermoAditivo(TermoAditivo termoAditivo){
		GenericDAO<TermoAditivo> termoAditivoDao = PersistenceManager.createGenericDAO(TermoAditivo.class);		
		PersistenceManager.getTransaction().begin();
		try{
			termoAditivoDao.incluir(termoAditivo);
			PersistenceManager.getTransaction().commit();
		}catch(Exception e){
			//TODO remover saída do console
			System.out.println(e);
			PersistenceManager.getTransaction().rollback();
		}
	}
	
}
