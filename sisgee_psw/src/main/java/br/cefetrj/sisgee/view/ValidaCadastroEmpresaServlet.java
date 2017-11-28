package br.cefetrj.sisgee.view;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import br.cefetrj.sisgee.control.EmpresaServices;
import br.cefetrj.sisgee.model.entity.Empresa;
import br.cefetrj.sisgee.view.utils.ServletUtils;
import br.cefetrj.sisgee.view.utils.ValidaUtils;

/**
 * Servlet para validar os dados da tela de cadastro de empresa.
 *
 * @author Natália Nunes
 * @since 1.0
 *
 */
@WebServlet("/ValidaCadastroEmpresaServlet")
public class ValidaCadastroEmpresaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Locale locale = ServletUtils.getLocale(request);
		ResourceBundle messages = ResourceBundle.getBundle("Messages", locale);
		String cnpjEmpresa = request.getParameter("cnpjEmpresa");
		String nomeEmpresa = request.getParameter("nomeEmpresa");
		String agenteIntegracao = request.getParameter("agenteIntegracao");

		boolean isValid = true;
		Integer tamanho = 0;
		/**
		 * Validação do campo Agente Integração, usando métodos da Classe
		 * ValidaUtils. Deve ser campo booleano
		 */
		String agenteIntegracaoMsg = "";
		agenteIntegracaoMsg = ValidaUtils.validaObrigatorio("Agente Integração", agenteIntegracao);
		if (agenteIntegracaoMsg.trim().isEmpty()) {
			agenteIntegracaoMsg = ValidaUtils.validaBoolean("Agente Integração", agenteIntegracao);
			if (agenteIntegracaoMsg.trim().isEmpty()) {
				Boolean obrigatorio = Boolean.parseBoolean(agenteIntegracao);
				request.setAttribute("obrigatório", obrigatorio);
			} else {
				agenteIntegracaoMsg = messages.getString(agenteIntegracaoMsg);
				request.setAttribute("agenteIntegracaoMsg", agenteIntegracaoMsg);
				isValid = false;
			}
		} else {
			agenteIntegracaoMsg = messages.getString(agenteIntegracaoMsg);
			request.setAttribute("agenteIntegracaoMsg", agenteIntegracaoMsg);
			isValid = false;
		}
		/**
		 * Validação do CNPJ da empresa usando os métodos da Classe ValidaUtils
		 * Campo obrigatório
		 */
		String cnpjEmpresaMsg = "";
		tamanho = 14;
		cnpjEmpresaMsg = ValidaUtils.validaObrigatorio("CNPJ", cnpjEmpresa);
		if (cnpjEmpresaMsg.trim().isEmpty()) {
			cnpjEmpresaMsg = ValidaUtils.validaTamanhoExato("CNPJ", tamanho, cnpjEmpresa);
			System.out.println(cnpjEmpresaMsg);
			if (cnpjEmpresaMsg.trim().isEmpty()) {
				cnpjEmpresaMsg = ValidaUtils.validaFloat("CNPJ", cnpjEmpresa);
				if (cnpjEmpresaMsg.trim().isEmpty()) {
					request.setAttribute("cnpjEmpresa", cnpjEmpresa);
				
					if (cnpjEmpresaMsg.trim().isEmpty()) {

						Empresa e = EmpresaServices.buscarEmpresaByCnpj(cnpjEmpresa);
						if (e == null) {
							request.setAttribute("cnpjEmpresa", cnpjEmpresa);
						} else {
							cnpjEmpresaMsg = messages.getString("br.cefetrj.sisgee.valida_cadastro_empresa_servlet.msg_empresa_duplicada");
							request.setAttribute("cnpjEmpresaMsg", cnpjEmpresaMsg);
							isValid = false;
						}
					}else{
						cnpjEmpresaMsg = messages.getString(cnpjEmpresaMsg);
						cnpjEmpresaMsg = ServletUtils.mensagemFormatada(cnpjEmpresaMsg, locale, tamanho);
						request.setAttribute("cnpjEmpresaMsg", cnpjEmpresaMsg);
						isValid = false;
					}
				}
			}else{
			cnpjEmpresaMsg = messages.getString(cnpjEmpresaMsg);
			cnpjEmpresaMsg = ServletUtils.mensagemFormatada(cnpjEmpresaMsg, locale, tamanho);
			request.setAttribute("cnpjEmpresaMsg", cnpjEmpresaMsg);
			isValid = false;
			}
		}else {
			cnpjEmpresaMsg = messages.getString(cnpjEmpresaMsg);
			request.setAttribute("cnpjEmpresaMsg", cnpjEmpresaMsg);
			isValid = false;
		}
		
		/**
		 * Validação da Razão Social do Cadastro Empresa usando métodos da
		 * Classe ValidaUtils. Campo obrigatório e tamanho máximo de 100
		 * caracteres.
		 */
		String nomeEmpresaMsg = "";
		nomeEmpresaMsg = ValidaUtils.validaObrigatorio("Razão Social", nomeEmpresa);
		if (nomeEmpresaMsg.trim().isEmpty()) {
			nomeEmpresaMsg = ValidaUtils.validaTamanho("Razão Social", 100, nomeEmpresa);
			if (nomeEmpresaMsg.trim().isEmpty()) {
				request.setAttribute("nomeEmpresa", nomeEmpresa);
			}
			if (cnpjEmpresaMsg.trim().isEmpty()) {

				Empresa e = EmpresaServices.buscarEmpresaByNome(nomeEmpresa);
				if (e == null) {
					request.setAttribute("nomeEmpresa", nomeEmpresa);
				} else {
					nomeEmpresaMsg = messages
							.getString("br.cefetrj.sisgee.valida_cadastro_empresa_servlet.msg_empresa_duplicada");
					request.setAttribute("nomeEmpresaMsg", nomeEmpresaMsg);
					isValid = false;
				}
			} else {
				nomeEmpresa = messages.getString(nomeEmpresa);
				request.setAttribute("nomeEmpresaMsg", nomeEmpresaMsg);
				isValid = false;
			}
		} else {
			nomeEmpresa = messages.getString(nomeEmpresa);
			request.setAttribute("nomeEmpresaMsg", nomeEmpresaMsg);
			isValid = false;
		}
		/**
		 * Teste das variáveis booleanas após validação. Redirecionamento para a
		 * inclusão ou devolver para o formulário com as mensagens.
		 */
		if (isValid) {
			request.getRequestDispatcher("/IncluirCadastroEmpresaServlet").forward(request, response);
		} else {
			//String msg = messages.getString("br.cefetrj.sisgee.form_empresa.msg_atencao");
			String msg = "Alguns campos precisam de atenção";
			request.setAttribute("msg", msg);
			request.getRequestDispatcher("/form_empresa.jsp").forward(request, response);
		}
	}}