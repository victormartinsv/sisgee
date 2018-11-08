package br.cefetrj.sisgee.control;

import java.util.List;

import br.cefetrj.sisgee.model.dao.ConvenioDAO;
import br.cefetrj.sisgee.model.dao.GenericDAO;
import br.cefetrj.sisgee.model.dao.PersistenceManager;
import br.cefetrj.sisgee.model.entity.Convenio;
import br.cefetrj.sisgee.model.entity.Empresa;
import br.cefetrj.sisgee.model.entity.Pessoa;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Serviços de alunos. Trata a lógica de negócios associada com a entidade
 * Convênio.
 *
 * @author Lucas Lima
 * @since 1.0
 */
public class ConvenioServices {

    /**
     * Recupera todos os convênios e retorna em uma lista.
     *
     * @return lista com todos os alunos
     */
    public static List<Convenio> listarConvenios() {
        GenericDAO<Convenio> convenioDao = PersistenceManager.createGenericDAO(Convenio.class);
        return convenioDao.buscarTodos();
    }
    
    /**
     * Método para listar convênios a vencer
     * @return lista de convênios a vencer
     */

    public static List<Convenio> listarConveniosVencer() {

        GenericDAO<Convenio> convenioDao = PersistenceManager.createGenericDAO(Convenio.class);

       

        List<Convenio> x = convenioDao.buscarTodos();
        List<Convenio> aVencer = new ArrayList();
        for (Convenio convenio : x) {
            Date dataAssinou = convenio.getDataAssinatura();
            Date dataFinal = convenio.getDataFinal();
            Date dataHoje = new Date();
            
            Calendar cal = Calendar.getInstance();
            cal.setTime(dataHoje);
            cal.add(Calendar.MONTH, 2);
            int ultimodia = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
            cal.set(Calendar.DATE, ultimodia);
            System.out.println(cal);
            Date dataVenceu =  cal.getTime();
            

            if ((dataFinal.getTime()> dataHoje.getTime()) && (dataFinal.getTime()<dataVenceu.getTime())) {
                
                aVencer.add(convenio);

            }

        }
        return aVencer;

    }

    /**
     * Método para buscar um convênio pelo convênio
     * @param convenio
     * @return 
     */
    public static Convenio buscarConvenio(Convenio convenio) {
        GenericDAO<Convenio> convenioDao = PersistenceManager.createGenericDAO(Convenio.class);
        return convenioDao.buscar(convenio.getIdConvenio());
    }

    /**
     * Método para incluir um convênio no banco de dados
     * @param convenio 
     */
    public static void incluirConvenio(Convenio convenio) {
        GenericDAO<Convenio> convenioDao = PersistenceManager.createGenericDAO(Convenio.class);
        PersistenceManager.getTransaction().begin();
        try {
            convenioDao.incluir(convenio);
            PersistenceManager.getTransaction().commit();
        } catch (Exception e) {
            PersistenceManager.getTransaction().rollback();
        }
    }
    
    /**
     * Método para buscar um convênio pelo cnpj da empresa
     * @param numero
     * @param emp
     * @return 
     */

    public static Convenio buscarConvenioByNumeroEmpresa(String numero, Empresa emp) {
        ConvenioDAO convenioDao = new ConvenioDAO();
        try {
            Convenio c = convenioDao.buscarByNumeroEmpresa(numero, emp);
            return c;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Método para buscar um convênio pelo número convênio
     * @param numero
     * @return 
     */
    public static Convenio buscarConvenioByNumeroConvenio(String numero) {
        ConvenioDAO convenioDao = new ConvenioDAO();
        try {
            Convenio a = convenioDao.buscarByNumero(numero);
            return a;
        } catch (Exception e) {
            return null;
        }
    }
    /**
     * Método para buscar um Convênio pela Empresa
     * @param empresa
     * @return 
     */

    public static Convenio buscarConvenioByEmpresa(Empresa empresa) {
        ConvenioDAO convenioDao = new ConvenioDAO();
        try {
            Convenio a = convenioDao.buscarByEmpresa(empresa);
            return a;
        } catch (Exception e) {
            return null;
        }
    }
    /**
     * Método para buscar um convênio pela pessoa
     * @param pessoa
     * @return 
     */
    
    public static Convenio buscarConvenioByPessoa(Pessoa pessoa) {
        ConvenioDAO convenioDao = new ConvenioDAO();
        try {
            Convenio a = convenioDao.buscarByPessoa(pessoa);
            return a;
        } catch (Exception e) {
            return null;
        }
    }
    
    /**
     * Método para fazer um merge de um convenio
     * @param convenio 
     */
    public static void alterarConvenio(Convenio convenio) {
		
		GenericDAO<Convenio> convenioDao = PersistenceManager.createGenericDAO(Convenio.class);		
		
		try {
			PersistenceManager.getTransaction().begin();
			convenioDao.alterar(convenio);
			PersistenceManager.getTransaction().commit();
		} catch (Exception e) {			
			e.printStackTrace();
			PersistenceManager.getTransaction().rollback();
		}
	}
        /**
        * Método para fazer exclusão de um convenio
        * @param convenio 
        */
    	public static void excluirConvenio(Convenio convenio,HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		GenericDAO<Convenio> convenioDao = PersistenceManager.createGenericDAO(Convenio.class);
		System.out.println(convenio.getEmpresa());
                
		try {
			PersistenceManager.getTransaction().begin();
			convenioDao.excluir(convenio);
			PersistenceManager.getTransaction().commit();
			
			req.getRequestDispatcher("/form_renovar_convenio.jsp").forward(req, resp);
                        
		} catch (Exception e) {		
			
			e.printStackTrace();
			PersistenceManager.getTransaction().rollback(); 
			req.getRequestDispatcher("/form_renovar_convenio_erro_exclusao.jsp").forward(req, resp);
		}
}
    
}
