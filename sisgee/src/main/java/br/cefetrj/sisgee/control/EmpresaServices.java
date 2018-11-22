package br.cefetrj.sisgee.control;

import java.util.List;

import br.cefetrj.sisgee.model.dao.EmpresaDAO;
import br.cefetrj.sisgee.model.dao.GenericDAO;
import br.cefetrj.sisgee.model.dao.PersistenceManager;
import br.cefetrj.sisgee.model.entity.Empresa;

/**
 * Serviços de Empresa. 
 * Trata a lógica de negócios
 * associada com a entidade Empresa.
 * 
 * @author Paulo Cantuária
 * @since 1.0
 */
public class EmpresaServices {
	
	/**
	 * Recupera todas as empresas e retorna em uma lista.
	 * 
	 * @return lista com todas as empresas
	 */
	public static List<Empresa> listarEmpresas(){
		GenericDAO<Empresa> empresaDao = PersistenceManager.createGenericDAO(Empresa.class);
		return empresaDao.buscarTodos();
	}	
	
	public static Empresa buscarEmpresaByCnpj(String cnpj) {
		EmpresaDAO empresaDao = new EmpresaDAO();
		try{
			Empresa e = empresaDao.buscarByCnpj(cnpj);
			return e;
		}catch(Exception e){
			return null;
		}
		
	}
        
        public static Empresa buscarEmpresaByNome(String nome) {
		EmpresaDAO empresaDao = new EmpresaDAO();
		try{
			Empresa e = empresaDao.buscarByNome(nome);
			return e;
		}catch(Exception e){
			return null;
		}
		
	}
        
        
	
	public static List<Empresa> buscarEmpresaByNomeList(String nome) {
		EmpresaDAO empresaDao = new EmpresaDAO();
		try{
			List<Empresa> e = empresaDao.buscarByNomeList(nome);
			return e;
		}catch(Exception e){
			return null;
		}
		
	}
        
	
	public static Empresa buscarEmpresa(Integer idEmpresa) {
		EmpresaDAO empresaDao = new EmpresaDAO();
		try{
			Empresa e = empresaDao.buscar(idEmpresa);
			return e;
		}catch(Exception e){
			return null;
		}
		
	}
	
	public static void incluirEmpresa(Empresa empresa){
		GenericDAO<Empresa> empresaDao = PersistenceManager.createGenericDAO(Empresa.class);	
		PersistenceManager.getTransaction().begin();
		try{
			empresaDao.incluir(empresa);
			PersistenceManager.getTransaction().commit();
		}catch(Exception e){
			PersistenceManager.getTransaction().rollback();
		}
	}
        
        public static void alterarEmpresa(Empresa empresa){
		GenericDAO<Empresa> empresaDao = PersistenceManager.createGenericDAO(Empresa.class);	
		PersistenceManager.getTransaction().begin();
		try{
			empresaDao.alterar(empresa);
			PersistenceManager.getTransaction().commit();
		}catch(Exception e){
			PersistenceManager.getTransaction().rollback();
		}
	}
        
}

