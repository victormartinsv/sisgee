package br.cefetrj.sisgee.view;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import br.cefetrj.sisgee.control.TermoRescisaoServices;
import br.cefetrj.sisgee.model.entity.TermoRescisao;

/**
 * Servlet implementation class IncluirTermoRescisaoServlet
 */
@WebServlet("/IncluirTermoRescisaoServlet")
public class IncluirTermoRescisaoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Date dataTermoRescisao = (Date)request.getAttribute("dataTermoRescisao");
		
		TermoRescisao termoRescisao = new TermoRescisao(dataTermoRescisao);
		
		String msg = "";
		Logger lg = Logger.getLogger(IncluirTermoRescisaoServlet.class);
		try{
			TermoRescisaoServices.incluirTermoRescisao(termoRescisao);
			msg = "Registro de Termo Rescião concluído com sucesso.";
			request.setAttribute("msg", msg);
			lg.info(msg);
			request.getRequestDispatcher("/index.jsp").forward(request, response);
			
			
		}catch(Exception e) {
			msg = "Ocorreu um erro inesperado ao tentar registrar o termo de rescisão. Tente novamente ou contate o suporte caso o erro persita.";
			request.setAttribute("msg", msg);
			
			lg.error("Exception ao tentar inserir o Termo de Rescisão", e);
			request.getRequestDispatcher("FormTermoRescisãoServlet").forward(request, response);
			
			System.out.println(msg);
		}
		
		System.out.println(msg);
	}

}
