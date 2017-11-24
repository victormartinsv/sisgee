package br.cefetrj.sisgee.view;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import br.cefetrj.sisgee.control.TermoAditivoServices;
import br.cefetrj.sisgee.model.entity.ProfessorOrientador;
import br.cefetrj.sisgee.model.entity.TermoAditivo;

/**
 * 
 * @author Paulo Cantuária
 * @since1.0
 *
 */

@WebServlet("/IncluirTermoAditivoServlet")
public class IncluirTermoAditivoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//TODO remover saída do console		
		System.out.println("iniciando o Incluir do Termo Aditivo");
		
		Date dataFimTermoAditivo = (Date)request.getAttribute("dataFim");		
		Integer cargaHorariaTermoAditivo = (Integer)request.getAttribute("cargaHoraria");
		Float valorBolsaTermoAditivo = (Float)request.getAttribute("valor");
		String enderecoTermoAditivo = (String)request.getAttribute("enderecoTermoAditivo");
		ProfessorOrientador professorOrientador = new ProfessorOrientador((Integer)request.getAttribute("idProfessor"));
		
		TermoAditivo termoAditivo = new TermoAditivo(dataFimTermoAditivo, cargaHorariaTermoAditivo,
				valorBolsaTermoAditivo, enderecoTermoAditivo, professorOrientador);
		
		String msg = "";
		Logger lg = Logger.getLogger(IncluirTermoAditivoServlet.class);
		try{
			TermoAditivoServices.incluirTermoAditivo(termoAditivo);
			msg = "Registro de Termo Aditivo concluído com sucesso.";
			request.setAttribute("msg", msg);
			lg.info(msg);
			request.getRequestDispatcher("/index.jsp").forward(request, response);
			
			//TODO remover saída do console
			
			
		}catch(Exception e) {
			msg = "Ocorreu um erro inesperado ao tentar registrar o termo aditivo. Tente novamente ou contate o suporte caso o erro persita.";
			request.setAttribute("msg", msg);
			
			lg.error("Exception ao tentar inserir o Termo Aditivo", e);
			request.getRequestDispatcher("FormTermoAditivoServlet").forward(request, response);
			
			//TODO remover saída do console
			System.out.println(msg);
		}
		
		System.out.println(msg);
		
	}

}
