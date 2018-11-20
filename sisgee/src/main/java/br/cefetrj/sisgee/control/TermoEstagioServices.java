package br.cefetrj.sisgee.control;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.cefetrj.sisgee.model.dao.GenericDAO;
import br.cefetrj.sisgee.model.dao.PersistenceManager;
import br.cefetrj.sisgee.model.dao.TermoEstagioDAO;
import br.cefetrj.sisgee.model.entity.AgenteIntegracao;
import br.cefetrj.sisgee.model.entity.Aluno;
import br.cefetrj.sisgee.model.entity.Convenio;
import br.cefetrj.sisgee.model.entity.Empresa;
import br.cefetrj.sisgee.model.entity.TermoEstagio;

/**
 * Serviços de TermoEstagio. 
 * Trata a lógica de negócios
 * associada com a entidade TermoEstagio.
 * 
 * @author Paulo Cantuária, Augusto Jose
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
	
	public static TermoEstagio buscarTermoEstagio(Integer idTermoEstagio) {
		GenericDAO<TermoEstagio> termoEstagioDao = PersistenceManager.createGenericDAO(TermoEstagio.class);
		return termoEstagioDao.buscar(idTermoEstagio);
	}
	
	/**
	 * 
	 * Metodo para receber uma matriz de com conteudo do banco
	 * @author Marcos Eduardo
	 * @param  obrigatorio boolean do form para filtrar resultado
	 * @param  inicio date do form para filtrar resultado
	 * @param  termino date do form para filtrar resultado
	 * @return author matriz com conteúdo obtido do banco ou null
	 */

	public static List<Object[]> listarTermoEstagioFiltrado(Boolean obrigatorio, Date inicio, Date termino){
		TermoEstagioDAO termoEstagioDAO = new TermoEstagioDAO();
		
		try{
			List<Object[]> author = null;
			
			if(obrigatorio == null) {
				author = termoEstagioDAO.buscarFiltrado( inicio, termino);
			}else {
				author = termoEstagioDAO.buscarFiltrado(obrigatorio , inicio, termino);
			}
			return author;
		}catch(Exception e){
			return null;
		}
	}
	
	public static void incluirTermoEstagio(TermoEstagio termoEstagio, Convenio convenio){
		
		/**
		 * Lógica de negócio
		 * 
		 * 
		 * Encapsular Convênio em Termo Estágio
		 * 
		 * Registrar Termo
		 * 
		 */

		PersistenceManager.getTransaction().begin();
		try{
			
                        
			GenericDAO<Convenio> convenioDao = PersistenceManager.createGenericDAO(Convenio.class);
                        
			Convenio con = convenioDao.buscar(convenio.getIdConvenio());                        
			
			// Convênio
			Convenio conv = ConvenioServices.buscarConvenioByNumeroConvenio(termoEstagio.getConvenio().getNumeroConvenio());
			if(conv != null) {
				// Encapsular em termo estagio
				termoEstagio.setConvenio(conv);
			}

			// encapsula aluno
			GenericDAO<Aluno> alunoDao = PersistenceManager.createGenericDAO(Aluno.class);
			Aluno al = alunoDao.buscar(termoEstagio.getAluno().getIdAluno());
			termoEstagio.setAluno(al);
						
			GenericDAO<TermoEstagio> termoEstagioDao = PersistenceManager.createGenericDAO(TermoEstagio.class);		
			termoEstagioDao.incluir(termoEstagio);
			
			PersistenceManager.getTransaction().commit();
		}catch(Exception e){
			//TODO remover saída do console
			System.out.println(e);
			e.printStackTrace();
			PersistenceManager.getTransaction().rollback();
		}

	}
	
	public static void alterarTermoEstagio(TermoEstagio termoEstagio) {
		
		GenericDAO<TermoEstagio> termoEstagioDao = PersistenceManager.createGenericDAO(TermoEstagio.class);		
		
		try {
			PersistenceManager.getTransaction().begin();
			termoEstagioDao.alterar(termoEstagio);
			PersistenceManager.getTransaction().commit();
			
		} catch (Exception e) {			
			e.printStackTrace();
			PersistenceManager.getTransaction().rollback();
		}
	}
	
	public static void excluirTermoEstagio(TermoEstagio termoEstagioaux,HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		GenericDAO<TermoEstagio> termoEstagioDao = PersistenceManager.createGenericDAO(TermoEstagio.class);
		
		try {
			PersistenceManager.getTransaction().begin();
			termoEstagioDao.excluir(termoEstagioaux);
			PersistenceManager.getTransaction().commit();
			req.getRequestDispatcher("/form_termo_aditivo_sucesso_exclusao.jsp").forward(req, resp);
		} catch (Exception e) {		
			
			e.printStackTrace();
			PersistenceManager.getTransaction().rollback(); 
			req.getRequestDispatcher("/form_termo_aditivo_Erro_Exclusão.jsp").forward(req, resp);
		}
}

}