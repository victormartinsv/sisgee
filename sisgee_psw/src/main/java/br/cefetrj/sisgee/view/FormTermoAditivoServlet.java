package br.cefetrj.sisgee.view;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.cefetrj.sisgee.control.AlunoServices;
import br.cefetrj.sisgee.control.ProfessorOrientadorServices;
import br.cefetrj.sisgee.model.entity.Aluno;
import br.cefetrj.sisgee.model.entity.ProfessorOrientador;
import br.cefetrj.sisgee.view.utils.UF;
import br.cefetrj.sisgee.view.utils.ValidaUtils;

/**
 * Servlet para trazer os dados do banco para a tela de cadastro de Termo
 * de Estágio.
 * 
 * @author Paulo Cantuária
 * @since 1.0
 *
 */
@WebServlet("/FormTermoAditivoServlet")
public class FormTermoAditivoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Método doGet: carrega as listas necessárias para seleção dos atributos de relacionamento e redireciona para a tela de Registro de Termo
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request = carregarListas(request); 

		request.getRequestDispatcher("/form_termo_estagio.jsp").forward(request, response);

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String dataInicioTermoAditivo = request.getParameter("dataInicio");
		String dataFimTermoAditivo = request.getParameter("dataFim");		
		String cargaHorariaTermoAditivo = request.getParameter("cargaHoraria");
		String valorBolsaTermoAditivo = request.getParameter("valor");
		String enderecoTermoAditivo = request.getParameter("enderecoTermoAditivo");
		String idProfessorOrientador = request.getParameter("idProfessor");
		String idAluno = request.getParameter("idAluno");

		/**
		 * Validação da Data de início do aditivo usando os métodos da Classe ValidaUtils
		 * Campo obrigatório
		 */
		boolean isValid = true;

		Date dataInicio = null;
		String dataInicioMsg = "";
		dataInicioMsg = ValidaUtils.validaObrigatorio("Início do aditivo", dataInicioTermoAditivo);
		if (dataInicioMsg.trim().isEmpty()) {
			dataInicioMsg = ValidaUtils.validaDate("Início do estágio", dataInicioTermoAditivo);
			if (dataInicioMsg.trim().isEmpty()) {				
				try {
					SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
					dataInicio = format.parse(dataInicioTermoAditivo);
					request.setAttribute("dataInicio", dataInicio);
				} catch (Exception e) {
					//TODO trocar saída de console por Log
					System.out.println("Data em formato incorreto, mesmo após validação na classe ValidaUtils");
					isValid = false;
				}
			}else {
				request.setAttribute("dataInicioMsg", dataInicioMsg);
				isValid = false;
			}
		} else {
			request.setAttribute("dataInicioMsg", dataInicioMsg);
			isValid = false;
		}

		/**
		 * Validação da Data de fim do aditivo usando os métodos da Classe ValidaUtils		 * 
		 */
		Date dataFim = null;
		String dataFimMsg = "";
		dataFimMsg = ValidaUtils.validaDate("Fim do estágio", dataFimTermoAditivo);
		if (dataFimMsg.trim().isEmpty()) {
			SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");			
			try {
				dataFim = format.parse(dataFimTermoAditivo);
				request.setAttribute("dataFim", dataFim);
			} catch (Exception e) {
				//TODO trocar saída de console por Log
				System.out.println("Data em formato incorreto, mesmo após validação na classe ValidaUtils");
				isValid = false;
			}
		} else {
			request.setAttribute("dataFimMsg", dataFimMsg);
			isValid = false;
		}

		/**
		 * Validação do período (entre o início e fim do aditivo) usando o método validaDatas da Classe ValidaUtils
		 */
		String periodoMsg = "";
		if(!(dataFimTermoAditivo == null || dataFimTermoAditivo.isEmpty())) {
			periodoMsg = ValidaUtils.validaDatas(dataInicio, dataFim);
			if(periodoMsg.trim().isEmpty()) {
				request.setAttribute("periodoMsg", periodoMsg);
				isValid = false;
			}	
		}		

		/**
		 * Validação da carga horária usando os métodos da Classe ValidaUtils
		 * Campo obrigatório e valor menor que 255.
		 */
		String cargaHorariaMsg = "";
		cargaHorariaMsg = ValidaUtils.validaObrigatorio("Carga Horária", cargaHorariaTermoAditivo);
		if (cargaHorariaMsg.trim().isEmpty()) {
			cargaHorariaMsg = ValidaUtils.validaInteger("Carga Horária", cargaHorariaTermoAditivo);
			if (cargaHorariaMsg.trim().isEmpty()) {
				Integer cargaHoraria = Integer.parseInt(cargaHorariaTermoAditivo);
				if (cargaHorariaMsg.trim().isEmpty()) {
					cargaHorariaMsg = ValidaUtils.validaTamanho("Carga Horária", 255, cargaHoraria);
					request.setAttribute("cargaHoraria", cargaHoraria);
				} else {
					request.setAttribute("cargaHorariaMsg", cargaHorariaMsg);
					isValid = false;
				}
			} else {
				request.setAttribute("cargaHorariaMsg", cargaHorariaMsg);
				isValid = false;
			}
		} else {
			request.setAttribute("cargaHorariaMsg", cargaHorariaMsg);
			isValid = false;
		}

		/**
		 * Validação do valor da bolsa usando os métodos da Classe ValidaUtils
		 * Campo obrigatório e valor float.
		 */
		String valorBolsaMsg = "";
		valorBolsaMsg = ValidaUtils.validaObrigatorio("Valor da Bolsa", valorBolsaTermoAditivo);
		if (valorBolsaMsg.trim().isEmpty()) {
			valorBolsaMsg = ValidaUtils.validaFloat("Valor da Bolsa", valorBolsaTermoAditivo);
			if (valorBolsaMsg.trim().isEmpty()) {
				Float valor = Float.parseFloat(valorBolsaTermoAditivo);
				request.setAttribute("valor", valor);
			} else {
				request.setAttribute("valorBolsaMsg", valorBolsaMsg);
				isValid = false;
			}
		} else {
			request.setAttribute("valorBolsaMsg", valorBolsaMsg);
			isValid = false;
		}			

		/**
		 * Validação do endereço do TermoEstagio usando métodos da Classe ValidaUtils.
		 * Campo obrigatório e tamanho máximo de 255 caracteres.
		 */
		String enderecoMsg = "";
		enderecoMsg = ValidaUtils.validaObrigatorio("Endereço", enderecoTermoAditivo);
		if(enderecoMsg.trim().isEmpty()) {
			enderecoMsg = ValidaUtils.validaTamanho("Endereço", 255, enderecoTermoAditivo);
			if(enderecoMsg.trim().isEmpty()) {
				request.setAttribute("enderecoTermoEstagio", enderecoTermoAditivo);
			}else {
				request.setAttribute("enderecoMsg", enderecoMsg);
				isValid = false;
			}
		}else {
			request.setAttribute("enderecoMsg", enderecoMsg);
			isValid = false;
		}

		/**
		 * Validação do Id do Professor Orientador, usando métodos da Classe ValidaUtils.
		 * Consultando a lista de Professores para validar 
		 */
		String idProfessorMsg = "";
		if (!(idProfessorOrientador.trim().isEmpty() || idProfessorOrientador == null)) {
			idProfessorMsg = ValidaUtils.validaInteger("Professor Orientador", idProfessorOrientador);
			if (idProfessorMsg.trim().isEmpty()) {
				Integer idProfessor = Integer.parseInt(idProfessorOrientador);
				List<ProfessorOrientador> listaProfessores = ProfessorOrientadorServices.listarProfessorOrientador();
				if (listaProfessores != null) {
					if (listaProfessores.contains(new ProfessorOrientador(idProfessor))) {
						request.setAttribute("idProfessor", idProfessor);
					} else {
						idProfessorMsg = "Professor escolhido não está cadastrado";
						isValid = false;
					}
				} else {
					idProfessorMsg = "Nenhum professor cadastrado no banco";
					isValid = false;
				}
			} else {
				request.setAttribute("idProfessorMsg", idProfessorMsg);
				isValid = false;
			}
		}

		/**
		 * Validação do Id do Aluno, usando métodos da Classe ValidaUtils.
		 * Instanciando o objeto e passando como Atributo para a inclusão, caso seja validado.
		 */
		String idAlunoMsg = "";
		idAlunoMsg = ValidaUtils.validaObrigatorio("Aluno", idAluno);
		if (idAlunoMsg.trim().isEmpty()) {
			idAlunoMsg = ValidaUtils.validaInteger("Aluno", idAluno);
			if (idAlunoMsg.trim().isEmpty()) {
				Integer idAlunoInt = Integer.parseInt(idAluno);
				List<Aluno> listaAlunos = AlunoServices.listarAlunos();
				if (listaAlunos != null) {
					if (listaAlunos.contains(new Aluno(idAlunoInt))) {
						request.setAttribute("id", idAlunoInt);
					} else {
						idAlunoMsg = "Aluno escolhido não está cadastrado";
					}
				} else {
					idAlunoMsg = "Nenhum aluno cadastrado no banco";
				}

			} else {
				request.setAttribute("idAlunoMsg", idAlunoMsg);
				isValid = false;
			}
		} else {
			request.setAttribute("idAlunoMsg", idAlunoMsg);
			isValid = false;
		}

		if (isValid) {
			request.getRequestDispatcher("/IncluirTermoAditivoServlet").forward(request, response);
		} else {
			String msg = "Alguns campos precisam de atenção";
			request.setAttribute("msg", msg);
			request.getRequestDispatcher("/form_termo_estagio.jsp").forward(request, response);
		}

	}

	private static HttpServletRequest carregarListas(HttpServletRequest request) {

		List<ProfessorOrientador> professores = ProfessorOrientadorServices.listarProfessorOrientador();
		UF[] uf = UF.asList();

		request.setAttribute("professores", professores);
		request.setAttribute("uf", uf);

		return request;

	}

}
