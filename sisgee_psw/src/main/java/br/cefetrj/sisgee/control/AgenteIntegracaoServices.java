package br.cefetrj.sisgee.control;

import java.util.List;

import br.cefetrj.sisgee.model.dao.GenericDAO;
import br.cefetrj.sisgee.model.dao.PersistenceManager;
import br.cefetrj.sisgee.model.entity.AgenteIntegracao;
import br.cefetrj.sisgee.model.entity.TermoEstagio;

/**
 * Serviços de AgenteIntegracao. 
 * Trata a lógica de negócios
 * associada com a entidade AgenteIntegracao.
 * 
 * @author Paulo Cantuária
 * @since 1.0
 */
public class AgenteIntegracaoServices {
	
	/**
	 * Recupera todos os Agentes de Integração e retorna em uma lista.
	 * 
	 * @return lista com todos os Agentes de Integração
	 */
	public static List<AgenteIntegracao> listarAgenteIntegracao(){
		GenericDAO<AgenteIntegracao> agenteIntegracaoDao = PersistenceManager.createGenericDAO(AgenteIntegracao.class);
		return agenteIntegracaoDao.buscarTodos();
	}	
	
	public static void incluirAgenteIntegracao(AgenteIntegracao agenteIntegracao){
		GenericDAO<AgenteIntegracao> agenteIntegracaoDao = PersistenceManager.createGenericDAO(AgenteIntegracao.class);		
		PersistenceManager.getTransaction().begin();
		try{
			agenteIntegracaoDao.incluir(agenteIntegracao);
			PersistenceManager.getTransaction().commit();
		}catch(Exception e){
			PersistenceManager.getTransaction().rollback();
		}
	}
}

