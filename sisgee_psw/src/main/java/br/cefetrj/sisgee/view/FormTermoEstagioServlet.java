package br.cefetrj.sisgee.view;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.cefetrj.sisgee.control.AgenteIntegracaoServices;
import br.cefetrj.sisgee.control.AlunoServices;
import br.cefetrj.sisgee.control.EmpresaServices;
import br.cefetrj.sisgee.control.ProfessorOrientadorServices;
import br.cefetrj.sisgee.model.entity.AgenteIntegracao;
import br.cefetrj.sisgee.model.entity.Aluno;
import br.cefetrj.sisgee.model.entity.Empresa;
import br.cefetrj.sisgee.model.entity.ProfessorOrientador;
import br.cefetrj.sisgee.view.utils.UF;

/**
 * Servlet para trazer os dados do banco para a tela de cadastro de Termo
 * de Estágio.
 * 
 * @author Paulo Cantuária
 * @since 1.0
 *
 */
@WebServlet("/FormTermoEstagioServlet")
public class FormTermoEstagioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request = carregarListas(request); 
		
		request.getRequestDispatcher("/form_termo_estagio.jsp").forward(request, response);

	}
	
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		/*CODIGO DO VALIDA SERVLET*/
		request.setAttribute("convenio", "augusto");
		request.setAttribute("convenioMsg", "Campo invalido");
		
		request = carregarListas(request);

		request.getRequestDispatcher("/form_termo_estagio.jsp").forward(request, response);

	}
	
	
	
	private static HttpServletRequest carregarListas(HttpServletRequest request) {
		
		List<AgenteIntegracao> agentesIntegracao = AgenteIntegracaoServices.listarAgenteIntegracao();
		List<Empresa> empresas = EmpresaServices.listarEmpresas();
		List<Aluno> alunos = AlunoServices.listarAlunos();
		List<ProfessorOrientador> professores = ProfessorOrientadorServices.listarProfessorOrientador();
		UF[] uf = UF.asList();
		
		request.setAttribute("agentesIntegracao", agentesIntegracao);
		request.setAttribute("empresas", empresas);
		request.setAttribute("alunos", alunos);
		request.setAttribute("professores", professores);
		request.setAttribute("uf", uf);
		
		return request;
		
	}
	
}
