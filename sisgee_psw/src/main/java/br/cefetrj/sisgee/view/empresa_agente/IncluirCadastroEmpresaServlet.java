package br.cefetrj.sisgee.view.empresa_agente;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import br.cefetrj.sisgee.control.AgenteIntegracaoServices;
import br.cefetrj.sisgee.control.EmpresaServices;
import br.cefetrj.sisgee.model.entity.AgenteIntegracao;
import br.cefetrj.sisgee.model.entity.Empresa;

@WebServlet("/IncluirCadastroEmpresaServlet")
public class IncluirCadastroEmpresaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String cnpjEmpresa = request.getParameter("cnpjEmpresa");
		String nomeEmpresa = request.getParameter("nomeEmpresa");
		String agenteIntegracao = request.getParameter("agenteIntegracao");


		Empresa empresa = new Empresa(cnpjEmpresa, nomeEmpresa, null);
		AgenteIntegracao agente = new AgenteIntegracao(nomeEmpresa, cnpjEmpresa);
		

		if (agenteIntegracao.equals("nao")) {
			System.out.println(agenteIntegracao);
			String msg = "";
			Logger lg = Logger.getLogger(IncluirCadastroEmpresaServlet.class);
			try {
				EmpresaServices.incluirEmpresa(empresa);
				msg = "Agente Integração cadastrada com sucesso.";
				request.setAttribute("msg", msg);
				lg.info(msg);
				request.getRequestDispatcher("/index.jsp").forward(request, response);

			} catch (Exception e) {
				msg = "Ocorreu um erro inesperado ao tentar cadastrar a Empresa. Tente novamente ou contate o suporte caso o erro persista.";
				request.setAttribute("msg", msg);
				lg.error("Exception ao tentar inserir uma Empresa", e);
				request.getRequestDispatcher("ValidaCadastroEmpresaServlet").forward(request, response);

			}

		} else {
			String msg = "";
			Logger lg = Logger.getLogger(IncluirCadastroEmpresaServlet.class);
			try {
				AgenteIntegracaoServices.incluirAgenteIntegracao(agente);
				msg = "Empresa cadastrada com sucesso.";
				request.setAttribute("msg", msg);
				lg.info(msg);
				request.getRequestDispatcher("/index.jsp").forward(request, response);

			} catch (Exception e) {
				msg = "Ocorreu um erro inesperado ao tentar cadastrar a Empresa. Tente novamente ou contate o suporte caso o erro persista.";
				request.setAttribute("msg", msg);
				lg.error("Exception ao tentar inserir uma Empresa", e);
				request.getRequestDispatcher("ValidaCadastroEmpresaServlet").forward(request, response);

			}

		}
	}

}
