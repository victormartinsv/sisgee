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
 * Servlet implementation class ValidaTermoRescisaoServlet
 */
@WebServlet("/ValidaTermoRescisaoServlet")
public class ValidaTermoRescisaoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
String dataTermoRescisao = request.getParameter("dataTermoRescisao");
		
		boolean isValid = true;
		
		String dataRescisaoParam = request.getParameter("dataRescisaoParam");
		Date dataRescisao = null;
		String dataTermoRescisaoMsg = "";
		dataTermoRescisaoMsg = ValidaUtils.validaObrigatorio("Rescisão", dataTermoRescisao);
		if (dataTermoRescisaoMsg.trim().isEmpty()) {
			dataTermoRescisaoMsg = ValidaUtils.validaDate("Rescisão", dataTermoRescisao);
			if (dataTermoRescisaoMsg.trim().isEmpty()) {				
				try {
					SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
					dataRescisao = format.parse(dataTermoRescisao);
					request.setAttribute("dataRescisao", dataRescisao);
				} catch (Exception e) {
					System.out.println("Data em formato incorreto, mesmo apóss validação na classe ValidaUtils");
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
			String msg = "Alguns campos precisam de atenÃ§Ã£o";
			request.setAttribute("msg", msg);
			request.getRequestDispatcher("/form_termo_estagio.jsp").forward(request, response);
		}
	}

}
