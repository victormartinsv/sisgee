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
import br.cefetrj.sisgee.view.utils.ValidaUtils;

/**
 * Servlet para validar os dados da tela de cadastro de Termo de Estágio.
 * 
 * @author Paulo Cantuária
 * @since 1.0
 *
 */
@WebServlet("/ValidaTermoEstagioServlet")
public class ValidaTermoEstagioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String dataInicioTermoEstagio = request.getParameter("dataInicioTermoEstagio");
		String dataFimTermoEstagio = request.getParameter("dataFimTermoEstagio");		
		
		String cargaHorariaTermoEstagio = request.getParameter("situacaoTermoEstagio");
		String valorBolsa = request.getParameter("valorBolsa");
		String enderecoTermoEstagio = request.getParameter("enderecoTermoEstagio");
		String numeroEnderecoTermoEstagio = request.getParameter("numeroEnderecoTermoEstagio");
		String complementoEnderecoTermoEstagio = request.getParameter("complementoEnderecoTermoEstagio");
		String bairroEnderecoTermoEstagio = request.getParameter("bairroEnderecoTermoEstagio");
		String cepEnderecoTermoEstagio = request.getParameter("cepEnderecoTermoEstagio");
		String cidadeEnderecoTermoEstagio = request.getParameter("cidadeEnderecoTermoEstagio");
		String estadoEnderecoTermoEstagio = request.getParameter("estadoEnderecoTermoEstagio");
		String eEstagioObrigatorio = request.getParameter("eEstagioObrigatorio");
		String idProfessorOrientador = request.getParameter("idProfessorOrientador");
		String idAluno = request.getParameter("aluno");
		String convenio = request.getParameter("convenio");
						
		/**
		 * Validação da Data de início do estágio usando os métodos da Classe ValidaUtils
		 * Campo obrigatório
		 */
		Date dataInicio = null;
		String dataInicioMsg = "";
		boolean dataInicioIsValid = true;
		dataInicioMsg = ValidaUtils.validaObrigatorio("Início do estágio", dataInicioTermoEstagio);
		if (dataInicioMsg.isEmpty()) {
			dataInicioMsg = ValidaUtils.validaDate("Início do estágio", dataInicioTermoEstagio);
			if (dataInicioMsg.isEmpty()) {
				SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
				try {
					dataInicio = format.parse(dataInicioTermoEstagio);
					request.setAttribute("dataInicio", dataInicio);
				} catch (Exception e) {
					//TODO trocar saída de console por Log
					System.out.println("Data em formato incorreto, mesmo após validação na classe ValidaUtils");
					dataInicioIsValid = false;
				}
			}else {
				request.setAttribute("dataInicioMsg", dataInicioMsg);
				dataInicioIsValid = false;
			}
		} else {
			request.setAttribute("dataInicioMsg", dataInicioMsg);
			dataInicioIsValid = false;
		}
		
		/**
		 * Validação da Data de fim do estágio usando os métodos da Classe ValidaUtils		 * 
		 */
		Date dataFim = null;
		String dataFimMsg = "";
		boolean dataFimIsValid = true;
		dataFimMsg = ValidaUtils.validaDate("Fim do estágio", dataFimTermoEstagio);
		if (dataFimMsg.isEmpty()) {
			SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");			
			try {
				dataFim = format.parse(dataFimTermoEstagio);
				request.setAttribute("dataFim", dataFim);
			} catch (Exception e) {
				//TODO trocar saída de console por Log
				System.out.println("Data em formato incorreto, mesmo após validação na classe ValidaUtils");
				dataFimIsValid = false;
			}
		} else {
			request.setAttribute("dataFimMsg", dataFimMsg);
			dataFimIsValid = false;
		}
		
		/**
		 * Validação do período (entre o início e fim do estágio) usando o método validaDatas da Classe ValidaUtils
		 */
		String periodoMsg = "";
		boolean periodoIsValid = true;
		if(!(dataFimTermoEstagio == null || dataFimTermoEstagio.isEmpty())) {
			periodoMsg = ValidaUtils.validaDatas(dataInicio, dataFim);
			if(periodoMsg.isEmpty()) {
				request.setAttribute("periodoMsg", periodoMsg);
				periodoIsValid = false;
			}	
		}		
		
		/**
		 * Validação da carga horária usando os métodos da Classe ValidaUtils
		 * Campo obrigatório e valor menor que 255.
		 */
		String cargaHorariaMsg = "";
		boolean cargaHorariaIsValid = true;
		cargaHorariaMsg = ValidaUtils.validaObrigatorio("Carga Horária", cargaHorariaTermoEstagio);
		if (cargaHorariaMsg.isEmpty()) {
			cargaHorariaMsg = ValidaUtils.validaInteger("Carga Horária", cargaHorariaTermoEstagio);
			if (cargaHorariaMsg.isEmpty()) {
				Integer cargaHoraria = Integer.parseInt(cargaHorariaTermoEstagio);
				if (cargaHorariaMsg.isEmpty()) {
					cargaHorariaMsg = ValidaUtils.validaTamanho("Carga Horária", 255, cargaHoraria);
					request.setAttribute("cargaHoraria", cargaHoraria);
				} else {
					request.setAttribute("cargaHorariaMsg", cargaHorariaMsg);
					cargaHorariaIsValid = false;
				}
			} else {
				request.setAttribute("cargaHorariaMsg", cargaHorariaMsg);
				cargaHorariaIsValid = false;
			}
		} else {
			request.setAttribute("cargaHorariaMsg", cargaHorariaMsg);
			cargaHorariaIsValid = false;
		}
				
		/**
		 * Validação do valor da bolsa usando os métodos da Classe ValidaUtils
		 * Campo obrigatório e valor float.
		 */
		String valorBolsaMsg = "";
		boolean valorBolsaIsValid = true;
		valorBolsaMsg = ValidaUtils.validaObrigatorio("Valor da Bolsa", valorBolsa);
		if (valorBolsaMsg.isEmpty()) {
			valorBolsaMsg = ValidaUtils.validaFloat("Valor da Bolsa", valorBolsa);
			if (valorBolsaMsg.isEmpty()) {
				Float valor = Float.parseFloat(valorBolsa);
				request.setAttribute("valor", valor);
			} else {
				request.setAttribute("valorBolsaMsg", valorBolsaMsg);
				valorBolsaIsValid = false;
			}
		} else {
			request.setAttribute("valorBolsaMsg", valorBolsaMsg);
			valorBolsaIsValid = false;
		}			
		
		/**
		 * Validação do endereço do TermoEstagio usando métodos da Classe ValidaUtils.
		 * Campo obrigatório e tamanho máximo de 255 caracteres.
		 */
		String enderecoMsg = "";
		boolean enderecoIsValid = true;
		enderecoMsg = ValidaUtils.validaObrigatorio("Endereço", enderecoTermoEstagio);
		if(enderecoMsg.isEmpty()) {
			enderecoMsg = ValidaUtils.validaTamanho("Endereço", 255, enderecoTermoEstagio);
			if(enderecoMsg.isEmpty()) {
				request.setAttribute("enderecoTermoEstagio", enderecoTermoEstagio);
			}else {
				request.setAttribute("enderecoMsg", enderecoMsg);
				enderecoIsValid = false;
			}
		}else {
			request.setAttribute("enderecoMsg", enderecoMsg);
			enderecoIsValid = false;
		}
		
		
		/**
		 * Validação do número do endereço do TermoEstagio usando os métodos da Classe ValidaUtils.
		 * Campo obrigatório e tamanho máximo de 10 caracteres.
		 */
		String numeroEnderecoMsg = "";
		boolean numeroEnderecoIsValid = true;
		numeroEnderecoMsg = ValidaUtils.validaObrigatorio("Número", numeroEnderecoTermoEstagio);
		if(numeroEnderecoMsg.isEmpty()) {
			numeroEnderecoMsg = ValidaUtils.validaTamanho("Número", 10, numeroEnderecoTermoEstagio);
			if(numeroEnderecoMsg.isEmpty()) {
				request.setAttribute("numeroEnderecoTermoEstagio", numeroEnderecoTermoEstagio);
			}else {
				request.setAttribute("numeroEnderecoMsg", numeroEnderecoMsg);
				enderecoIsValid = false;
			}
		}else {
			request.setAttribute("numeroEnderecoMsg", numeroEnderecoMsg);
			enderecoIsValid = false;
		}		
		
		/**
		 * Validação do complemento do endereço do TermoEstagio usando os métodos da Classe ValidaUtils.
		 * Campo obrigatório e tamanho máximo de 150 caracteres.
		 */		
		String complementoEnderecoMsg = "";
		boolean complementoEnderecoIsValid = true;
		complementoEnderecoMsg = ValidaUtils.validaObrigatorio("Complemento", complementoEnderecoTermoEstagio);
		if(complementoEnderecoMsg.isEmpty()) {
			numeroEnderecoMsg = ValidaUtils.validaTamanho("Complemento", 150, complementoEnderecoTermoEstagio);
			if(complementoEnderecoMsg.isEmpty()) {
				request.setAttribute("complementoEnderecoTermoEstagio", complementoEnderecoTermoEstagio);
			}else {
				request.setAttribute("complementoEnderecoMsg", complementoEnderecoMsg);
				complementoEnderecoIsValid = false;
			}
		}else {
			request.setAttribute("complementoEnderecoMsg", complementoEnderecoMsg);
			complementoEnderecoIsValid = false;
		}		
		
		/**
		 * Validação do bairro do endereço do TermoEstagio usando métodos da Classe ValidaUtils.
		 * Campo obrigatório e tamanho máximo de 150 caracteres.
		 */
		String bairroEnderecoMsg = "";
		boolean bairroEnderecoIsValid = true;
		bairroEnderecoMsg = ValidaUtils.validaObrigatorio("Bairro", bairroEnderecoTermoEstagio);
		if(bairroEnderecoMsg.isEmpty()) {
			bairroEnderecoMsg = ValidaUtils.validaTamanho("Bairro", 150, bairroEnderecoTermoEstagio);
			if(bairroEnderecoMsg.isEmpty()) {
				request.setAttribute("bairroEnderecoTermoEstagio", bairroEnderecoTermoEstagio);
			}else {
				request.setAttribute("bairroEnderecoMsg", bairroEnderecoMsg);
				bairroEnderecoIsValid = false;
			}
		}else {
			request.setAttribute("bairroEnderecoMsg", bairroEnderecoMsg);
			bairroEnderecoIsValid = false;
		}		
		
		
				
		/**
		 * Validação do cep do endereço do TermoEstagio usando métodos da Classe ValidaUtils.
		 * Campo obrigatório e tamanho máximo de 15 caracteres.
		 */
		String cepEnderecoMsg = "";
		boolean cepEnderecoIsValid = true;
		cepEnderecoMsg = ValidaUtils.validaObrigatorio("CEP", cepEnderecoTermoEstagio);
		if(cepEnderecoMsg.isEmpty()) {
			cepEnderecoMsg = ValidaUtils.validaTamanho("CEP", 15, cepEnderecoTermoEstagio);
			if(bairroEnderecoMsg.isEmpty()) {
				request.setAttribute("cepEnderecoTermoEstagio", cepEnderecoTermoEstagio);
			}else {
				request.setAttribute("cepEnderecoMsg", cepEnderecoMsg);
				cepEnderecoIsValid = false;
			}
		}else {
			request.setAttribute("cepEnderecoMsg", cepEnderecoMsg);
			cepEnderecoIsValid = false;
		}			
		
		
		/**
		 * Validação da Cidade do endereço do TermoEstagio, usando métodos da Classe ValidaUtils.
		 * Campo obrigatório e tamanho máximo de 150 caracteres.
		 */
		String cidadeEnderecoMsg = "";
		boolean cidadeEnderecoIsValid = true;
		cidadeEnderecoMsg = ValidaUtils.validaObrigatorio("Cidade", cidadeEnderecoTermoEstagio);
		if(cidadeEnderecoMsg.isEmpty()) {
			cidadeEnderecoMsg = ValidaUtils.validaTamanho("Cidade", 150, cidadeEnderecoTermoEstagio);
			if(cidadeEnderecoMsg.isEmpty()) {
				request.setAttribute("cidadeEnderecoTermoEstagio", cidadeEnderecoTermoEstagio);
			}else {
				request.setAttribute("cidadeEnderecoMsg", cidadeEnderecoMsg);
				cidadeEnderecoIsValid = false;
			}
		}else {
			request.setAttribute("cidadeEnderecoMsg", cidadeEnderecoMsg);
			cidadeEnderecoIsValid = false;
		}					
		
		/**
		 * Validação do Estado do endereço do TermoEstagio, usando métodos da Classe ValidaUtils.
		 * Campo obrigatório e contido na Enum de UFs.
		 */
		String estadoEnderecoMsg = "";
		boolean estadoEnderecoIsValid = true;
		estadoEnderecoMsg = ValidaUtils.validaObrigatorio("Estado", estadoEnderecoTermoEstagio);
		if(estadoEnderecoMsg.isEmpty()) {
			estadoEnderecoMsg = ValidaUtils.validaUf("Estado", estadoEnderecoTermoEstagio);
			if(estadoEnderecoMsg.isEmpty()) {
				request.setAttribute("estadoEnderecoTermoEstagio", estadoEnderecoTermoEstagio);
			}else {
				request.setAttribute("estadoEnderecoMsg", estadoEnderecoMsg);
				estadoEnderecoIsValid = false;
			}
		}else {
			request.setAttribute("estadoEnderecoMsg", estadoEnderecoMsg);
			estadoEnderecoIsValid = false;
		}					
		
		/**
		 * Validação do campo Estágio Obrigatório, usando métodos da Classe ValidaUtils.
		 * Deve ser campo booleano
		 */
		String eEstagioObrigatorioMsg = "";
		boolean eEstagioObrigatorioIsValid = true;		
		eEstagioObrigatorioMsg = ValidaUtils.validaObrigatorio("Estágio Obrigatório", eEstagioObrigatorio);
		if(eEstagioObrigatorioMsg.isEmpty()) {
			eEstagioObrigatorioMsg = ValidaUtils.validaBoolean("Estágio Obrigatório", eEstagioObrigatorio);
			if(eEstagioObrigatorioMsg.isEmpty()) {
				Boolean obrigatorio = Boolean.parseBoolean(eEstagioObrigatorio);
				request.setAttribute("obrigatorio", obrigatorio);
			}else {
				request.setAttribute("eEstagioObrigatorioMsg", eEstagioObrigatorioMsg);
				eEstagioObrigatorioIsValid = false;
			}			
		}else {
			request.setAttribute("eEstagioObrigatorioMsg", eEstagioObrigatorioMsg);
			eEstagioObrigatorioIsValid = false;
		}		
		
		/**
		 * Validação do Id do Professor Orientador, usando métodos da Classe ValidaUtils.
		 * Consultando a lista de Professores para validar 
		 */
		String idProfessorMsg = "";
		boolean idProfessorIsValid = true;
		if (!(idProfessorOrientador.trim().isEmpty() || idProfessorOrientador == null)) {
			idProfessorMsg = ValidaUtils.validaInteger("Professor Orientador", idProfessorOrientador);
			if (idProfessorMsg.isEmpty()) {
				Integer idProfessor = Integer.parseInt(idProfessorOrientador);
				List<ProfessorOrientador> listaProfessores = ProfessorOrientadorServices.listarProfessorOrientador();
				if (listaProfessores != null) {
					if (listaProfessores.contains(new ProfessorOrientador(idProfessor))) {
						request.setAttribute("idProfessor", idProfessor);
					} else {
						idProfessorMsg = "Professor escolhido não está cadastrado";
						idProfessorIsValid = false;
					}
				} else {
					idProfessorMsg = "Nenhum professor cadastrado no banco";
					idProfessorIsValid = false;
				}
			} else {
				request.setAttribute("idProfessorMsg", idProfessorMsg);
				idProfessorIsValid = false;
			}
		}
		
		/**
		 * Validação do Id do Aluno, usando métodos da Classe ValidaUtils.
		 * Instanciando o objeto e passando como Atributo para a inclusão, caso seja validado.
		 */
		String idAlunoMsg = "";
		boolean idAlunoIsValid = true;
		idAlunoMsg = ValidaUtils.validaObrigatorio("Aluno", idAluno);
		if (idAlunoMsg.isEmpty()) {
			idAlunoMsg = ValidaUtils.validaInteger("Aluno", idAluno);
			if (idAlunoMsg.isEmpty()) {
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
				idAlunoIsValid = false;
			}
		} else {
			request.setAttribute("idAlunoMsg", idAlunoMsg);
			idAlunoIsValid = false;
		}
		
		String convenioMsg = "";
		boolean convenioIsValid = true;
		convenioMsg = ValidaUtils.validaObrigatorio("Convênio", convenio);
		if (convenioMsg.isEmpty()) {
			convenioMsg = ValidaUtils.validaTamanho("Convênio", 10, convenio);
			if (convenioMsg.isEmpty()) {
				request.setAttribute("convenio", convenio);
			} else {
				request.setAttribute("convenioMsg", convenioMsg);
				convenioIsValid = false;
			}
		} else {
			request.setAttribute("convenioMsg", convenioMsg);
			convenioIsValid = false;
		}				
		
		/**
		 * Teste das variáveis booleanas após validação.
		 * Redirecionamento para a inclusão ou devolver para o formulário com as mensagens.
		 */
		if (idAlunoIsValid && idProfessorIsValid && eEstagioObrigatorioIsValid && cidadeEnderecoIsValid
				&& cepEnderecoIsValid && bairroEnderecoIsValid && complementoEnderecoIsValid && numeroEnderecoIsValid
				&& enderecoIsValid && valorBolsaIsValid && cargaHorariaIsValid && periodoIsValid && dataFimIsValid
				&& dataInicioIsValid && convenioIsValid && estadoEnderecoIsValid) {

			request.getRequestDispatcher("/IncluirTermoEstagioServlet").forward(request, response);
		} else {
			request.getRequestDispatcher("/form_termo_estagio.jsp").forward(request, response);
		}
	}
}
