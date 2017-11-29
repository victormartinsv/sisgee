package br.cefetrj.sisgee.view;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.cefetrj.sisgee.view.utils.ValidaUtils;

/**
 * Servlet implementation class FormTermoRescisaoServlet
 */
@WebServlet("/FormTermoRescisaoServlet")
public class FormTermoRescisaoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String rescisao = "sim";
		request.setAttribute("rescisao", rescisao);
		
		request.getRequestDispatcher("/form_termo_rescisao.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		

		String dataTermoRescisao = request.getParameter("dataTermoRescisao");
		
		
		boolean isValid = true;
		
		Date dataRescisao = null;
		String dataTermoRescisaoMsg = "";
		dataTermoRescisaoMsg = ValidaUtils.validaObrigatorio("Termo Rescisão", dataTermoRescisao);
		if (dataTermoRescisaoMsg.trim().isEmpty()) {
			dataTermoRescisaoMsg = ValidaUtils.validaDate("Termo Rescisão", dataTermoRescisao);
			if (dataTermoRescisaoMsg.trim().isEmpty()) {				
				try {
					SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
					dataRescisao = format.parse(dataTermoRescisao);
					request.setAttribute("dataRescisao", dataRescisao);
				} catch (Exception e) {
					//TODO trocar saída de console por Log
					System.out.println("Data em formato incorreto, mesmo após validação na classe ValidaUtils");
					isValid = false;
				}
			}else {
				request.setAttribute("dataTermoRescisaoMsg", dataTermoRescisaoMsg);
				isValid = false;
			}
		} else {
			request.setAttribute("dataTermoRescisaoMsg", dataTermoRescisaoMsg);
			isValid = false;
		}
		
		if (isValid) {
			request.getRequestDispatcher("/IncluirTermoRescisaoServlet").forward(request, response);
		} else {
			String msg = "Alguns campos precisam de atenção";
			String rescisao = "sim";
			request.setAttribute("msg", msg);
			request.setAttribute("Rescisao", rescisao);
			
			request.getRequestDispatcher("/form_termo_estagio.jsp").forward(request, response);
		}
	}

}
