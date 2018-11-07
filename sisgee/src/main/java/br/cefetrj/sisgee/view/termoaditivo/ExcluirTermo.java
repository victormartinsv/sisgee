package br.cefetrj.sisgee.view.termoaditivo;

import br.cefetrj.sisgee.control.AlunoServices;
import br.cefetrj.sisgee.control.TermoAditivoServices;
import br.cefetrj.sisgee.control.TermoEstagioServices;
import br.cefetrj.sisgee.model.entity.Aluno;
import br.cefetrj.sisgee.model.entity.TermoAditivo;
import br.cefetrj.sisgee.model.entity.TermoEstagio;
import br.cefetrj.sisgee.view.utils.UF;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet EXCLUI o termo estágio ao pedir para ser excluido
 * @author Vinicius Paradellas
 */
@WebServlet("/ExcluirTermo")
public class ExcluirTermo extends HttpServlet {

    /**
     * 
     * @param request um objeto HttpServletRequest que contém a solicitação feita pelo cliente do servlet.
     * @param response um objeto HttpServletResponse que contém a resposta que o servlet envia para o cliente
     * @throws ServletException se o pedido do service não puder ser tratado
     * @throws IOException se um erro de entrada ou saída for detectado quando o servlet manipula o pedido 
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        String ide = req.getParameter("ide");
        String ida = req.getParameter("ida");
        TermoEstagio termoEstagioaux=null;
        
        termoEstagioaux = TermoEstagioServices.buscarTermoEstagio(Integer.parseInt(ide));
        
        try{
        	TermoEstagioServices.excluirTermoEstagio(termoEstagioaux, req, resp);
        	
        	//req.getRequestDispatcher("/form_termo_aditivo.jsp").forward(req, resp);

        	System.out.println("entrou aqui no try");
        	
        } catch (Exception e) {
        	
        	System.out.println("entrou aqui no catch");
        	req.getRequestDispatcher("/form_termo_aditivo_Erro_Exclusão.jsp").forward(req, resp);
        }
        	
        
    }
}