package br.cefetrj.sisgee.control;

import java.util.List;

import br.cefetrj.sisgee.model.dao.GenericDAO;
import br.cefetrj.sisgee.model.dao.PersistenceManager;
import br.cefetrj.sisgee.model.entity.TermoAditivo;
import br.cefetrj.sisgee.model.entity.TermoEstagio;

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
	
	/**
	 * Método para persistir um termo aditivo no banco
	 * @param termoAditivo
	 */
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
	
	/**
	 * Método para buscar um termo aditivo por id
	 * @param idTermoAditivo
	 * @return
	 */
	public static TermoAditivo buscarTermoAditivo(Integer idTermoAditivo) {
		GenericDAO<TermoAditivo> termoAditivoDao = PersistenceManager.createGenericDAO(TermoAditivo.class);	
		return termoAditivoDao.buscar(idTermoAditivo);
	}
	
	/**
	 * 
	 * @param termoAditivo
	 * @return
	 */
	public static TermoEstagio termoEstagioAtualizadoByTermoAditivo(TermoAditivo termoAditivo) {
		TermoEstagio termoEstagio = TermoEstagioServices.buscarTermoEstagio(termoAditivo.getTermoEstagio().getIdTermoEstagio());
		
		if(termoAditivo.getDataFimTermoAditivo() != null) {
			termoEstagio.setDataFimTermoEstagio(termoAditivo.getDataFimTermoAditivo());
		}
		
		if(termoAditivo.getCargaHorariaTermoAditivo() != null) {
			termoEstagio.setCargaHorariaTermoEstagio(termoAditivo.getCargaHorariaTermoAditivo());
		}
		
		if(termoAditivo.getValorBolsaTermoAditivo() != null) {
			termoEstagio.setValorBolsa(termoAditivo.getValorBolsaTermoAditivo());			
		}
		
		if(termoAditivo.getEnderecoTermoAditivo() != null) {
			termoEstagio.setEnderecoTermoEstagio(termoAditivo.getEnderecoTermoAditivo());
		}
		
		if(termoAditivo.getNumeroEnderecoTermoAditivo() != null) {
			termoEstagio.setNumeroEnderecoTermoEstagio(termoAditivo.getNumeroEnderecoTermoAditivo());
		}
		
		if(termoAditivo.getComplementoEnderecoTermoAditivo() != null) {
			termoEstagio.setComplementoEnderecoTermoEstagio(termoAditivo.getComplementoEnderecoTermoAditivo());
		}
		
		if(termoAditivo.getBairroEnderecoTermoAditivo() != null) {
			termoEstagio.setBairroEnderecoTermoEstagio(termoAditivo.getBairroEnderecoTermoAditivo());
		}
		
		if(termoAditivo.getCepEnderecoTermoAditivo() != null) {
			termoEstagio.setCepEnderecoTermoEstagio(termoAditivo.getCepEnderecoTermoAditivo());
		}
		
		if(termoAditivo.getCidadeEnderecoTermoAditivo() != null) {
			termoEstagio.setCidadeEnderecoTermoEstagio(termoAditivo.getCidadeEnderecoTermoAditivo());
		}
		
		if(termoAditivo.getProfessorOrientador() != null) {
			termoEstagio.setProfessorOrientador(termoAditivo.getProfessorOrientador());
		}
		
		
		return termoEstagio;
	}
	
}
