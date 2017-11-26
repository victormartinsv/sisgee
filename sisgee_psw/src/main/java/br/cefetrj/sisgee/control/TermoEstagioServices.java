package br.cefetrj.sisgee.control;

import java.util.List;

import br.cefetrj.sisgee.model.dao.GenericDAO;
import br.cefetrj.sisgee.model.dao.PersistenceManager;
import br.cefetrj.sisgee.model.entity.TermoEstagio;

/**
 * Serviços de TermoEstagio. 
 * Trata a lógica de negócios
 * associada com a entidade TermoEstagio.
 * 
 * @author Paulo Cantuária
 * @since 1.0
 */
public class TermoEstagioServices {
	
	/**
	 * Recupera todos os Termos de Estágio e retorna em uma lista.
	 * 
	 * @return lista com todos os Termos de Estágio
	 */
	public static List<TermoEstagio> listarTermoEstagio(){
		GenericDAO<TermoEstagio> termoEstagioDao = PersistenceManager.createGenericDAO(TermoEstagio.class);
		return termoEstagioDao.buscarTodos();
	}	
	
	public static void incluirTermoEstagio(TermoEstagio termoEstagio){
		GenericDAO<TermoEstagio> termoEstagioDao = PersistenceManager.createGenericDAO(TermoEstagio.class);		
		PersistenceManager.getTransaction().begin();
		try{
			termoEstagioDao.incluir(termoEstagio);			
			PersistenceManager.getTransaction().commit();
		}catch(Exception e){
			//TODO remover saída do console
			System.out.println(e);
			e.printStackTrace();
			PersistenceManager.getTransaction().rollback();
		}
	}
}
