package br.cefetrj.sisgee.view;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.cefetrj.sisgee.control.AlunoServices;
import br.cefetrj.sisgee.control.ProfessorOrientadorServices;
import br.cefetrj.sisgee.model.entity.Aluno;
import br.cefetrj.sisgee.model.entity.ProfessorOrientador;
import br.cefetrj.sisgee.view.utils.ServletUtils;
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

		String aditivo = "sim";
		request = carregarListas(request);
		request.setAttribute("aditivo", aditivo);

		request.getRequestDispatcher("/form_termo_estagio.jsp").forward(request, response);

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Locale locale = ServletUtils.getLocale(request);
		ResourceBundle messages = ResourceBundle.getBundle("Messages", locale);
		
		String dataInicioTermoAditivo = request.getParameter("dataInicio");
		String dataFimTermoAditivo = request.getParameter("dataFim");		
		String cargaHorariaTermoAditivo = request.getParameter("cargaHoraria");
		String valorBolsaTermoAditivo = request.getParameter("valorBolsaTermoAditivo");
		String enderecoTermoAditivo = request.getParameter("enderecoTermoAditivo");
		String idProfessorOrientador = request.getParameter("idProfessor");
		String idAluno = request.getParameter("idAluno");

		/**
		 * Validação da Data de início do aditivo usando os métodos da Classe ValidaUtils
		 * Campo obrigatório
		 */
		boolean isValid = true;
		String campo = "";
		Integer tamanho = 0;

		Date dataInicio = null;
		String dataInicioMsg = "";
		campo = "Data de Início";
		
		dataInicioMsg = ValidaUtils.validaObrigatorio(campo, dataInicioTermoAditivo);
		if (dataInicioMsg.trim().isEmpty()) {
			dataInicioMsg = ValidaUtils.validaDate(campo, dataInicioTermoAditivo);
			if (dataInicioMsg.trim().isEmpty()) {				
				try {
					SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
					dataInicio = format.parse(dataInicioTermoAditivo);
					//System.out.println("dataInicio no FormTermoEstagioServlet: " + dataInicio);
					request.setAttribute("dataInicio", dataInicio);
				} catch (Exception e) {
					//TODO trocar saída de console por Log
					System.out.println("Data em formato incorreto, mesmo após validação na classe ValidaUtils");
					isValid = false;
				}
			}else {
				dataInicioMsg = messages.getString(dataInicioMsg);
				request.setAttribute("dataInicioMsg", dataInicioMsg);
				isValid = false;
				//TODO Fazer log
				System.out.println(dataInicioMsg);
			}
		} else {
			dataInicioMsg = messages.getString(dataInicioMsg);
			request.setAttribute("dataInicioMsg", dataInicioMsg);
			isValid = false;
			//TODO Fazer log
			System.out.println(dataInicioMsg);
		}

		/**
		 * Validação da Data de fim do estágio usando os métodos da Classe ValidaUtils		 * 
		 */
		Date dataFim = null;
		campo = "Data de Término";
		Boolean hasDataFim = false;
		if (!dataFimTermoAditivo.trim().isEmpty()) {
			String dataFimMsg = "";
			dataFimMsg = ValidaUtils.validaDate(campo , dataFimTermoAditivo);
			if (dataFimMsg.trim().isEmpty()) {
				SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
				try {
					dataFim = format.parse(dataFimTermoAditivo);
					request.setAttribute("dataFim", dataFim);
					hasDataFim = true;
				} catch (Exception e) {
					//TODO trocar saída de console por Log
					System.out.println("Data em formato incorreto, mesmo após validação na classe ValidaUtils");
					isValid = false;
				}
			} else {
				dataFimMsg = messages.getString(dataFimMsg);
				request.setAttribute("dataFimMsg", dataFimMsg);
				isValid = false;
				//TODO Fazer log
				System.out.println(dataFimMsg);
			} 
		}
		request.setAttribute("hasDataFim", hasDataFim);		

		/**
		 * Validação da carga horária usando os métodos da Classe ValidaUtils
		 * Campo obrigatório e valor menor que 255.
		 */
		String cargaHorariaMsg = "";
		campo = "Horas por dia";
		tamanho = 6;		
		cargaHorariaMsg = ValidaUtils.validaObrigatorio(campo , cargaHorariaTermoAditivo);
		if (cargaHorariaMsg.trim().isEmpty()) {
			cargaHorariaMsg = ValidaUtils.validaInteger(campo, cargaHorariaTermoAditivo);
			if (cargaHorariaMsg.trim().isEmpty()) {
				Integer cargaHoraria = Integer.parseInt(cargaHorariaTermoAditivo);
				if (cargaHorariaMsg.trim().isEmpty()) {
					cargaHorariaMsg = ValidaUtils.validaTamanho(campo, tamanho, cargaHoraria);
					if (cargaHorariaMsg.trim().isEmpty()) {
					request.setAttribute("cargaHoraria", cargaHoraria);
					}else {
						cargaHorariaMsg = messages.getString(cargaHorariaMsg);
						cargaHorariaMsg = ServletUtils.mensagemFormatada(cargaHorariaMsg, locale, tamanho);
						request.setAttribute("cargaHorariaMsg", cargaHorariaMsg);
					}
				} else {
					cargaHorariaMsg = messages.getString(cargaHorariaMsg);
					request.setAttribute("cargaHorariaMsg", cargaHorariaMsg);
					isValid = false;
					//TODO Fazer log
					System.out.println(cargaHorariaMsg);
					
				}
			} else {
				cargaHorariaMsg = messages.getString(cargaHorariaMsg);
				request.setAttribute("cargaHorariaMsg", cargaHorariaMsg);
				isValid = false;
				//TODO Fazer log
				System.out.println(cargaHorariaMsg);
			}
		} else {
			cargaHorariaMsg = messages.getString(cargaHorariaMsg);
			request.setAttribute("cargaHorariaMsg", cargaHorariaMsg);
			isValid = false;
			//TODO Fazer log
			System.out.println(cargaHorariaMsg);
		}

		/**
		 * Validação do valor da bolsa usando os métodos da Classe ValidaUtils
		 * Campo obrigatório e valor float.
		 */
		String valorBolsaMsg = "";
		campo = "Valor";
		valorBolsaMsg = ValidaUtils.validaObrigatorio(campo, valorBolsaTermoAditivo);
		if (valorBolsaMsg.trim().isEmpty()) {
			valorBolsaMsg = ValidaUtils.validaFloat(campo, valorBolsaTermoAditivo);
			if (valorBolsaMsg.trim().isEmpty()) {
				Float valor = Float.parseFloat(valorBolsaTermoAditivo);
				request.setAttribute("valor", valor);
			} else {
				valorBolsaMsg = messages.getString(valorBolsaMsg);
				request.setAttribute("valorBolsaMsg", valorBolsaMsg);
				isValid = false;
				//TODO Fazer log
				System.out.println(valorBolsaMsg);
			}
		} else {
			valorBolsaMsg = messages.getString(valorBolsaMsg);
			request.setAttribute("valorBolsaMsg", valorBolsaMsg);
			isValid = false;
			//TODO Fazer log
			System.out.println(valorBolsaMsg);
		}			

		/**
		 * Validação do endereço do TermoEstagio usando métodos da Classe ValidaUtils.
		 * Campo obrigatório e tamanho máximo de 255 caracteres.
		 */
		String enderecoMsg = "";
		campo = "Endereço";
		tamanho = 255;
		enderecoMsg = ValidaUtils.validaObrigatorio(campo, enderecoTermoAditivo);
		if(enderecoMsg.trim().isEmpty()) {
			enderecoMsg = ValidaUtils.validaTamanho(campo, tamanho, enderecoTermoAditivo);
			if(enderecoMsg.trim().isEmpty()) {
				request.setAttribute("enderecoTermoEstagio", enderecoTermoAditivo);
			}else {
				enderecoMsg = messages.getString(enderecoMsg);
				enderecoMsg = ServletUtils.mensagemFormatada(enderecoMsg, locale, tamanho);
				request.setAttribute("enderecoMsg", enderecoMsg);
				isValid = false;
				//TODO Fazer log
				System.out.println(enderecoMsg);
			}
		}else {
			enderecoMsg = messages.getString(enderecoMsg);
			request.setAttribute("enderecoMsg", enderecoMsg);
			isValid = false;
			//TODO Fazer log
			System.out.println(enderecoMsg);
		}

		/**
		 * Validação do Id do Professor Orientador, usando métodos da Classe ValidaUtils.
		 * Consultando a lista de Professores para validar 
		 */
		String idProfessorMsg = "";
		campo = "Professor Orientador";
		Boolean hasProfessor = false;
		if (!(idProfessorOrientador.trim().isEmpty() || idProfessorOrientador == null)) {
			idProfessorMsg = ValidaUtils.validaInteger(campo, idProfessorOrientador);
			if (idProfessorMsg.trim().isEmpty()) {
				Integer idProfessor = Integer.parseInt(idProfessorOrientador);
				List<ProfessorOrientador> listaProfessores = ProfessorOrientadorServices.listarProfessorOrientador();
				if (listaProfessores != null) {
					if (listaProfessores.contains(new ProfessorOrientador(idProfessor))) {
						request.setAttribute("idProfessor", idProfessor);
						hasProfessor = true;
					} else {
						idProfessorMsg = messages.getString("br.cefetrj.sisgee.incluir_termo_aditivo_servlet.msg_professorEscolhido");
						isValid = false;
						//TODO Fazer log
						System.out.println(idProfessorMsg);
					}
				} else {
					idProfessorMsg = messages.getString("br.cefetrj.sisgee.incluir_termo_aditivo_servlet.msg_nenhumProfessor");
					isValid = false;
					//TODO Fazer log
					System.out.println(idProfessorMsg);
				}
			} else {
				idProfessorMsg = messages.getString(idProfessorMsg);
				request.setAttribute("idProfessorMsg", idProfessorMsg);
				isValid = false;
				//TODO Fazer log
				System.out.println(idProfessorMsg);
			}
		}
		request.setAttribute("hasProfessor", hasProfessor);

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
						idProfessorMsg = messages.getString("br.cefetrj.sisgee.incluir_termo_aditivo_servlet.msg_AlunoEscolhido");
						request.setAttribute("idAlunoMsg", idAlunoMsg);
					}
				} else {
					idProfessorMsg = messages.getString("br.cefetrj.sisgee.incluir_termo_aditivo_servlet.msg_nenhumAluno");
					request.setAttribute("idAlunoMsg", idAlunoMsg);
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
			String aditivo = "sim";
			request = carregarListas(request);
			request.setAttribute("msg", msg);
			request.setAttribute("aditivo", aditivo);
			
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
