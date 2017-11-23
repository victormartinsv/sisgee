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
		String idAluno = request.getParameter("idAluno");
		String convenio = request.getParameter("convenio");
						
		/**
		 * Validação da Data de início do estágio usando os métodos da Classe ValidaUtils
		 * Campo obrigatório
		 */
		boolean isValid = true;
		
		Date dataInicio = null;
		String dataInicioMsg = "";
		dataInicioMsg = ValidaUtils.validaObrigatorio("Início do estágio", dataInicioTermoEstagio);
		if (dataInicioMsg.trim().isEmpty()) {
			dataInicioMsg = ValidaUtils.validaDate("Início do estágio", dataInicioTermoEstagio);
			if (dataInicioMsg.trim().isEmpty()) {				
				try {
					SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
					dataInicio = format.parse(dataInicioTermoEstagio);
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
		 * Validação da Data de fim do estágio usando os métodos da Classe ValidaUtils		 * 
		 */
		Date dataFim = null;
		String dataFimMsg = "";
		dataFimMsg = ValidaUtils.validaDate("Fim do estágio", dataFimTermoEstagio);
		if (dataFimMsg.trim().isEmpty()) {
			SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");			
			try {
				dataFim = format.parse(dataFimTermoEstagio);
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
		 * Validação do período (entre o início e fim do estágio) usando o método validaDatas da Classe ValidaUtils
		 */
		String periodoMsg = "";
		if(!(dataFimTermoEstagio == null || dataFimTermoEstagio.isEmpty())) {
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
		cargaHorariaMsg = ValidaUtils.validaObrigatorio("Carga Horária", cargaHorariaTermoEstagio);
		if (cargaHorariaMsg.trim().isEmpty()) {
			cargaHorariaMsg = ValidaUtils.validaInteger("Carga Horária", cargaHorariaTermoEstagio);
			if (cargaHorariaMsg.trim().isEmpty()) {
				Integer cargaHoraria = Integer.parseInt(cargaHorariaTermoEstagio);
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
		valorBolsaMsg = ValidaUtils.validaObrigatorio("Valor da Bolsa", valorBolsa);
		if (valorBolsaMsg.trim().isEmpty()) {
			valorBolsaMsg = ValidaUtils.validaFloat("Valor da Bolsa", valorBolsa);
			if (valorBolsaMsg.trim().isEmpty()) {
				Float valor = Float.parseFloat(valorBolsa);
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
		enderecoMsg = ValidaUtils.validaObrigatorio("Endereço", enderecoTermoEstagio);
		if(enderecoMsg.trim().isEmpty()) {
			enderecoMsg = ValidaUtils.validaTamanho("Endereço", 255, enderecoTermoEstagio);
			if(enderecoMsg.trim().isEmpty()) {
				request.setAttribute("enderecoTermoEstagio", enderecoTermoEstagio);
			}else {
				request.setAttribute("enderecoMsg", enderecoMsg);
				isValid = false;
			}
		}else {
			request.setAttribute("enderecoMsg", enderecoMsg);
			isValid = false;
		}
		
		
		/**
		 * Validação do número do endereço do TermoEstagio usando os métodos da Classe ValidaUtils.
		 * Campo obrigatório e tamanho máximo de 10 caracteres.
		 */
		String numeroEnderecoMsg = "";
		numeroEnderecoMsg = ValidaUtils.validaObrigatorio("Número", numeroEnderecoTermoEstagio);
		if(numeroEnderecoMsg.trim().isEmpty()) {
			numeroEnderecoMsg = ValidaUtils.validaTamanho("Número", 10, numeroEnderecoTermoEstagio);
			if(numeroEnderecoMsg.trim().isEmpty()) {
				request.setAttribute("numeroEnderecoTermoEstagio", numeroEnderecoTermoEstagio);
			}else {
				request.setAttribute("numeroEnderecoMsg", numeroEnderecoMsg);
				isValid = false;
			}
		}else {
			request.setAttribute("numeroEnderecoMsg", numeroEnderecoMsg);
			isValid = false;
		}		
		
		/**
		 * Validação do complemento do endereço do TermoEstagio usando os métodos da Classe ValidaUtils.
		 * Campo obrigatório e tamanho máximo de 150 caracteres.
		 */		
		String complementoEnderecoMsg = "";
		complementoEnderecoMsg = ValidaUtils.validaObrigatorio("Complemento", complementoEnderecoTermoEstagio);
		if(complementoEnderecoMsg.trim().isEmpty()) {
			numeroEnderecoMsg = ValidaUtils.validaTamanho("Complemento", 150, complementoEnderecoTermoEstagio);
			if(complementoEnderecoMsg.trim().isEmpty()) {
				request.setAttribute("complementoEnderecoTermoEstagio", complementoEnderecoTermoEstagio);
			}else {
				request.setAttribute("complementoEnderecoMsg", complementoEnderecoMsg);
				isValid = false;
			}
		}else {
			request.setAttribute("complementoEnderecoMsg", complementoEnderecoMsg);
			isValid = false;
		}		
		
		/**
		 * Validação do bairro do endereço do TermoEstagio usando métodos da Classe ValidaUtils.
		 * Campo obrigatório e tamanho máximo de 150 caracteres.
		 */
		String bairroEnderecoMsg = "";
		bairroEnderecoMsg = ValidaUtils.validaObrigatorio("Bairro", bairroEnderecoTermoEstagio);
		if(bairroEnderecoMsg.trim().isEmpty()) {
			bairroEnderecoMsg = ValidaUtils.validaTamanho("Bairro", 150, bairroEnderecoTermoEstagio);
			if(bairroEnderecoMsg.trim().isEmpty()) {
				request.setAttribute("bairroEnderecoTermoEstagio", bairroEnderecoTermoEstagio);
			}else {
				request.setAttribute("bairroEnderecoMsg", bairroEnderecoMsg);
				isValid = false;
			}
		}else {
			request.setAttribute("bairroEnderecoMsg", bairroEnderecoMsg);
			isValid = false;
		}		
		
		
				
		/**
		 * Validação do cep do endereço do TermoEstagio usando métodos da Classe ValidaUtils.
		 * Campo obrigatório e tamanho máximo de 15 caracteres.
		 */
		String cepEnderecoMsg = "";		
		cepEnderecoMsg = ValidaUtils.validaObrigatorio("CEP", cepEnderecoTermoEstagio);
		if(cepEnderecoMsg.trim().isEmpty()) {
			cepEnderecoMsg = ValidaUtils.validaTamanho("CEP", 15, cepEnderecoTermoEstagio);
			if(bairroEnderecoMsg.trim().isEmpty()) {
				request.setAttribute("cepEnderecoTermoEstagio", cepEnderecoTermoEstagio);
			}else {
				request.setAttribute("cepEnderecoMsg", cepEnderecoMsg);
				isValid = false;
			}
		}else {
			request.setAttribute("cepEnderecoMsg", cepEnderecoMsg);
			isValid = false;
		}			
		
		
		/**
		 * Validação da Cidade do endereço do TermoEstagio, usando métodos da Classe ValidaUtils.
		 * Campo obrigatório e tamanho máximo de 150 caracteres.
		 */
		String cidadeEnderecoMsg = "";
		cidadeEnderecoMsg = ValidaUtils.validaObrigatorio("Cidade", cidadeEnderecoTermoEstagio);
		if(cidadeEnderecoMsg.trim().isEmpty()) {
			cidadeEnderecoMsg = ValidaUtils.validaTamanho("Cidade", 150, cidadeEnderecoTermoEstagio);
			if(cidadeEnderecoMsg.trim().isEmpty()) {
				request.setAttribute("cidadeEnderecoTermoEstagio", cidadeEnderecoTermoEstagio);
			}else {
				request.setAttribute("cidadeEnderecoMsg", cidadeEnderecoMsg);
				isValid = false;
			}
		}else {
			request.setAttribute("cidadeEnderecoMsg", cidadeEnderecoMsg);
			isValid = false;
		}					
		
		/**
		 * Validação do Estado do endereço do TermoEstagio, usando métodos da Classe ValidaUtils.
		 * Campo obrigatório e contido na Enum de UFs.
		 */
		String estadoEnderecoMsg = "";
		estadoEnderecoMsg = ValidaUtils.validaObrigatorio("Estado", estadoEnderecoTermoEstagio);
		if(estadoEnderecoMsg.trim().isEmpty()) {
			estadoEnderecoMsg = ValidaUtils.validaUf("Estado", estadoEnderecoTermoEstagio);
			if(estadoEnderecoMsg.trim().isEmpty()) {
				request.setAttribute("estadoEnderecoTermoEstagio", estadoEnderecoTermoEstagio);
			}else {
				request.setAttribute("estadoEnderecoMsg", estadoEnderecoMsg);
				isValid = false;
			}
		}else {
			request.setAttribute("estadoEnderecoMsg", estadoEnderecoMsg);
			isValid = false;
		}					
		
		/**
		 * Validação do campo Estágio Obrigatório, usando métodos da Classe ValidaUtils.
		 * Deve ser campo booleano
		 */
		String eEstagioObrigatorioMsg = "";
		eEstagioObrigatorioMsg = ValidaUtils.validaObrigatorio("Estágio Obrigatório", eEstagioObrigatorio);
		if(eEstagioObrigatorioMsg.trim().isEmpty()) {
			eEstagioObrigatorioMsg = ValidaUtils.validaBoolean("Estágio Obrigatório", eEstagioObrigatorio);
			if(eEstagioObrigatorioMsg.trim().isEmpty()) {
				Boolean obrigatorio = Boolean.parseBoolean(eEstagioObrigatorio);
				request.setAttribute("obrigatorio", obrigatorio);
			}else {
				request.setAttribute("eEstagioObrigatorioMsg", eEstagioObrigatorioMsg);
				isValid = false;
			}			
		}else {
			request.setAttribute("eEstagioObrigatorioMsg", eEstagioObrigatorioMsg);
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
		
		String convenioMsg = "";
		convenioMsg = ValidaUtils.validaObrigatorio("Convênio", convenio);
		if (convenioMsg.trim().isEmpty()) {
			convenioMsg = ValidaUtils.validaTamanho("Convênio", 10, convenio);
			if (convenioMsg.trim().isEmpty()) {
				request.setAttribute("convenio", convenio);
			} else {
				request.setAttribute("convenioMsg", convenioMsg);
				isValid = false;
			}
		} else {
			request.setAttribute("convenioMsg", convenioMsg);
			isValid = false;
		}				
		
		/**
		 * Teste das variáveis booleanas após validação.
		 * Redirecionamento para a inclusão ou devolver para o formulário com as mensagens.
		 */
		if (isValid) {
			request.getRequestDispatcher("/IncluirTermoEstagioServlet").forward(request, response);
		} else {
			String msg = "Alguns campos precisam de atenção";
			request.setAttribute("msg", msg);
			request.getRequestDispatcher("/form_termo_estagio.jsp").forward(request, response);
		}
	}
}
