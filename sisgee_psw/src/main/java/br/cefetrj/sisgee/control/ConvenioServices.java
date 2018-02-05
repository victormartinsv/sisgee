package br.cefetrj.sisgee.control;

import java.util.List;

import br.cefetrj.sisgee.model.dao.ConvenioDAO;
import br.cefetrj.sisgee.model.dao.GenericDAO;
import br.cefetrj.sisgee.model.dao.PersistenceManager;
import br.cefetrj.sisgee.model.entity.Convenio;
import br.cefetrj.sisgee.model.entity.Empresa;

/**
 * Serviços de alunos. Trata a lógica de negócios
 * associada com a entidade Convênio.
 * 
 * @author Paulo Cantuária
 * @since 1.0
 */
public class ConvenioServices {
	
	/**
	 * Recupera todos os convênios e retorna em uma lista.
	 * 
	 * @return lista com todos os alunos
	 */
	public static List<Convenio> listarConvenios(){
		GenericDAO<Convenio> convenioDao = PersistenceManager.createGenericDAO(Convenio.class);
		return convenioDao.buscarTodos();
	}
	
	public static Convenio buscarConvenio(Convenio convenio) {
		GenericDAO<Convenio> convenioDao = PersistenceManager.createGenericDAO(Convenio.class);
		return convenioDao.buscar(convenio.getIdConvenio());
	}
	
	public static void incluirConvenio(Convenio convenio){
		GenericDAO<Convenio> convenioDao = PersistenceManager.createGenericDAO(Convenio.class);		
		PersistenceManager.getTransaction().begin();
		try{
			convenioDao.incluir(convenio);
			PersistenceManager.getTransaction().commit();
		}catch(Exception e){
			PersistenceManager.getTransaction().rollback();
		}
	}
	
	public static Convenio buscarConvenioByNumeroEmpresa(String numero, Empresa emp) {
		ConvenioDAO convenioDao = new ConvenioDAO();
		try{
			Convenio c = convenioDao.buscarByNumeroEmpresa(numero, emp);
			return c;
		}catch(Exception e){
			return null;
		}
	}
}
