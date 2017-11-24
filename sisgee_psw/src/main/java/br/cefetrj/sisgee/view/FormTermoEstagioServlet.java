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

import br.cefetrj.sisgee.control.AgenteIntegracaoServices;
import br.cefetrj.sisgee.control.AlunoServices;
import br.cefetrj.sisgee.control.EmpresaServices;
import br.cefetrj.sisgee.control.ProfessorOrientadorServices;
import br.cefetrj.sisgee.model.entity.AgenteIntegracao;
import br.cefetrj.sisgee.model.entity.Aluno;
import br.cefetrj.sisgee.model.entity.Empresa;
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
@WebServlet("/FormTermoEstagioServlet")
public class FormTermoEstagioServlet extends HttpServlet {
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
		
		String dataInicioTermoEstagio = request.getParameter("dataInicioTermoEstagio");
		String dataFimTermoEstagio = request.getParameter("dataFimTermoEstagio");		
		String cargaHorariaTermoEstagio = request.getParameter("cargaHorariaTermoEstagio");
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
		//TODO alterar o parametro de matricula para idAluno
		String idAluno = request.getParameter("matricula");
		String convenio = request.getParameter("convenio");
						
		/**
		 * Validação da Data de início do estágio usando os métodos da Classe ValidaUtils
		 * Campo obrigatório
		 */
		boolean isValid = true;
		String campo = "";
		
		Date dataInicio = null;
		String dataInicioMsg = "";
		campo = "Data de Início";
		dataInicioMsg = ValidaUtils.validaObrigatorio(campo, dataInicioTermoEstagio);
		if (dataInicioMsg.trim().isEmpty()) {
			dataInicioMsg = ValidaUtils.validaDate(campo, dataInicioTermoEstagio);
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
				//TODO Fazer log
				System.out.println(dataInicioMsg);
			}
		} else {
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
		if (!dataFimTermoEstagio.trim().isEmpty()) {
			String dataFimMsg = "";
			dataFimMsg = ValidaUtils.validaDate(campo , dataFimTermoEstagio);
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
				//TODO Fazer log
				System.out.println(dataFimMsg);
			} 
		}
		/**
		 * Validação do período (entre o início e fim do estágio) usando o método validaDatas da Classe ValidaUtils
		 */
		String periodoMsg = "";
		if(!(dataFimTermoEstagio == null || dataFimTermoEstagio.isEmpty())) {
			periodoMsg = ValidaUtils.validaDatas(dataInicio, dataFim);
			if(!periodoMsg.trim().isEmpty()) {
				request.setAttribute("periodoMsg", periodoMsg);
				isValid = false;
				//TODO Fazer log
				System.out.println(periodoMsg);
			}	
		}		
		
		/**
		 * Validação da carga horária usando os métodos da Classe ValidaUtils
		 * Campo obrigatório e valor menor que 255 (No banco), valor menor que 24, por ser horas diárias.
		 */
		String cargaHorariaMsg = "";
		campo = "Horas por dia";
		cargaHorariaMsg = ValidaUtils.validaObrigatorio(campo , cargaHorariaTermoEstagio);
		if (cargaHorariaMsg.trim().isEmpty()) {
			cargaHorariaMsg = ValidaUtils.validaInteger(campo, cargaHorariaTermoEstagio);
			if (cargaHorariaMsg.trim().isEmpty()) {
				Integer cargaHoraria = Integer.parseInt(cargaHorariaTermoEstagio);
				if (cargaHorariaMsg.trim().isEmpty()) {
					cargaHorariaMsg = ValidaUtils.validaTamanho(campo, 6, cargaHoraria);
					if (cargaHorariaMsg.trim().isEmpty()) {
					request.setAttribute("cargaHoraria", cargaHoraria);
					}else {
						request.setAttribute("cargaHorariaMsg", cargaHorariaMsg);
					}
				} else {
					request.setAttribute("cargaHorariaMsg", cargaHorariaMsg);
					isValid = false;
					//TODO Fazer log
					System.out.println(cargaHorariaMsg);
					
				}
			} else {
				request.setAttribute("cargaHorariaMsg", cargaHorariaMsg);
				isValid = false;
				//TODO Fazer log
				System.out.println(cargaHorariaMsg);
			}
		} else {
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
		valorBolsaMsg = ValidaUtils.validaObrigatorio(campo, valorBolsa);
		if (valorBolsaMsg.trim().isEmpty()) {
			valorBolsaMsg = ValidaUtils.validaFloat(campo, valorBolsa);
			if (valorBolsaMsg.trim().isEmpty()) {
				Float valor = Float.parseFloat(valorBolsa);
				request.setAttribute("valor", valor);
			} else {
				request.setAttribute("valorBolsaMsg", valorBolsaMsg);
				isValid = false;
				//TODO Fazer log
				System.out.println(valorBolsaMsg);
			}
		} else {
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
		enderecoMsg = ValidaUtils.validaObrigatorio(campo, enderecoTermoEstagio);
		if(enderecoMsg.trim().isEmpty()) {
			enderecoMsg = ValidaUtils.validaTamanho(campo, 255, enderecoTermoEstagio);
			if(enderecoMsg.trim().isEmpty()) {
				request.setAttribute("enderecoTermoEstagio", enderecoTermoEstagio);
			}else {
				request.setAttribute("enderecoMsg", enderecoMsg);
				isValid = false;
				//TODO Fazer log
				System.out.println(enderecoMsg);
			}
		}else {
			request.setAttribute("enderecoMsg", enderecoMsg);
			isValid = false;
			//TODO Fazer log
			System.out.println(enderecoMsg);
		}
		
		
		/**
		 * Validação do número do endereço do TermoEstagio usando os métodos da Classe ValidaUtils.
		 * Campo obrigatório e tamanho máximo de 10 caracteres.
		 */
		String numeroEnderecoMsg = "";
		campo = "Número";
		numeroEnderecoMsg = ValidaUtils.validaObrigatorio(campo , numeroEnderecoTermoEstagio);
		if(numeroEnderecoMsg.trim().isEmpty()) {
			numeroEnderecoMsg = ValidaUtils.validaTamanho(campo, 10, numeroEnderecoTermoEstagio);
			if(numeroEnderecoMsg.trim().isEmpty()) {
				request.setAttribute("numeroEnderecoTermoEstagio", numeroEnderecoTermoEstagio);
			}else {
				request.setAttribute("numeroEnderecoMsg", numeroEnderecoMsg);
				isValid = false;
				//TODO Fazer log
				System.out.println(numeroEnderecoMsg);
			}
		}else {
			request.setAttribute("numeroEnderecoMsg", numeroEnderecoMsg);
			isValid = false;
			//TODO Fazer log
			System.out.println(numeroEnderecoMsg);
		}		
		
		/**
		 * Validação do complemento do endereço do TermoEstagio usando os métodos da Classe ValidaUtils.
		 * Campo obrigatório e tamanho máximo de 150 caracteres.
		 */		
		String complementoEnderecoMsg = "";
		campo = "Complemento";
		complementoEnderecoMsg = ValidaUtils.validaObrigatorio(campo, complementoEnderecoTermoEstagio);
		if(complementoEnderecoMsg.trim().isEmpty()) {
			numeroEnderecoMsg = ValidaUtils.validaTamanho(campo, 150, complementoEnderecoTermoEstagio);
			if(complementoEnderecoMsg.trim().isEmpty()) {
				request.setAttribute("complementoEnderecoTermoEstagio", complementoEnderecoTermoEstagio);
			}else {
				request.setAttribute("complementoEnderecoMsg", complementoEnderecoMsg);
				isValid = false;
				//TODO Fazer log
				System.out.println(complementoEnderecoMsg);
			}
		}else {
			request.setAttribute("complementoEnderecoMsg", complementoEnderecoMsg);
			isValid = false;
			//TODO Fazer log
			System.out.println(complementoEnderecoMsg);
		}		
		
		/**
		 * Validação do bairro do endereço do TermoEstagio usando métodos da Classe ValidaUtils.
		 * Campo obrigatório e tamanho máximo de 150 caracteres.
		 */
		String bairroEnderecoMsg = "";
		campo = "Bairro";
		bairroEnderecoMsg = ValidaUtils.validaObrigatorio(campo, bairroEnderecoTermoEstagio);
		if(bairroEnderecoMsg.trim().isEmpty()) {
			bairroEnderecoMsg = ValidaUtils.validaTamanho(campo, 150, bairroEnderecoTermoEstagio);
			if(bairroEnderecoMsg.trim().isEmpty()) {
				request.setAttribute("bairroEnderecoTermoEstagio", bairroEnderecoTermoEstagio);
			}else {
				request.setAttribute("bairroEnderecoMsg", bairroEnderecoMsg);
				isValid = false;
				//TODO Fazer log
				System.out.println(bairroEnderecoMsg);
			}
		}else {
			request.setAttribute("bairroEnderecoMsg", bairroEnderecoMsg);
			isValid = false;
			//TODO Fazer log
			System.out.println(bairroEnderecoMsg);
		}			
				
		/**
		 * Validação do cep do endereço do TermoEstagio usando métodos da Classe ValidaUtils.
		 * Campo obrigatório e tamanho máximo de 15 caracteres.
		 */
		String cepEnderecoMsg = "";	
		campo = "CEP";
		cepEnderecoMsg = ValidaUtils.validaObrigatorio(campo, cepEnderecoTermoEstagio);
		if(cepEnderecoMsg.trim().isEmpty()) {
			cepEnderecoMsg = ValidaUtils.validaTamanho(campo, 15, cepEnderecoTermoEstagio);
			if(bairroEnderecoMsg.trim().isEmpty()) {
				request.setAttribute("cepEnderecoTermoEstagio", cepEnderecoTermoEstagio);
			}else {
				request.setAttribute("cepEnderecoMsg", cepEnderecoMsg);
				isValid = false;
				//TODO Fazer log
				System.out.println(cepEnderecoMsg);
			}
		}else {
			request.setAttribute("cepEnderecoMsg", cepEnderecoMsg);
			isValid = false;
			//TODO Fazer log
			System.out.println(cepEnderecoMsg);
		}			
		
		
		/**
		 * Validação da Cidade do endereço do TermoEstagio, usando métodos da Classe ValidaUtils.
		 * Campo obrigatório e tamanho máximo de 150 caracteres.
		 */
		String cidadeEnderecoMsg = "";
		campo = "Cidade";
		cidadeEnderecoMsg = ValidaUtils.validaObrigatorio(campo, cidadeEnderecoTermoEstagio);
		if(cidadeEnderecoMsg.trim().isEmpty()) {
			cidadeEnderecoMsg = ValidaUtils.validaTamanho(campo, 150, cidadeEnderecoTermoEstagio);
			if(cidadeEnderecoMsg.trim().isEmpty()) {
				request.setAttribute("cidadeEnderecoTermoEstagio", cidadeEnderecoTermoEstagio);
			}else {
				request.setAttribute("cidadeEnderecoMsg", cidadeEnderecoMsg);
				isValid = false;
				//TODO Fazer log
				System.out.println(cidadeEnderecoMsg);
			}
		}else {
			request.setAttribute("cidadeEnderecoMsg", cidadeEnderecoMsg);
			isValid = false;
			//TODO Fazer log
			System.out.println(cidadeEnderecoMsg);
		}					
		
		/**
		 * Validação do Estado do endereço do TermoEstagio, usando métodos da Classe ValidaUtils.
		 * Campo obrigatório e contido na Enum de UFs.
		 */
		String estadoEnderecoMsg = "";
		campo = "Estado";
		estadoEnderecoMsg = ValidaUtils.validaObrigatorio(campo, estadoEnderecoTermoEstagio);
		if(estadoEnderecoMsg.trim().isEmpty()) {
			estadoEnderecoMsg = ValidaUtils.validaUf(campo, estadoEnderecoTermoEstagio);
			if(estadoEnderecoMsg.trim().isEmpty()) {
				request.setAttribute("estadoEnderecoTermoEstagio", estadoEnderecoTermoEstagio);
			}else {
				request.setAttribute("estadoEnderecoMsg", estadoEnderecoMsg);
				isValid = false;
				//TODO Fazer log
				System.out.println(estadoEnderecoMsg);
			}
		}else {
			request.setAttribute("estadoEnderecoMsg", estadoEnderecoMsg);
			isValid = false;
			//TODO Fazer log
			System.out.println(estadoEnderecoMsg);
		}					
		
		/**
		 * Validação do campo Estágio Obrigatório, usando métodos da Classe ValidaUtils.
		 * Deve ser campo booleano
		 */
		String eEstagioObrigatorioMsg = "";
		campo = "Estágio obrigatório";
		eEstagioObrigatorioMsg = ValidaUtils.validaObrigatorio(campo, eEstagioObrigatorio);
		if(eEstagioObrigatorioMsg.trim().isEmpty()) {
			Boolean obrigatorio;
			if(eEstagioObrigatorio.equals("sim")) {				
				obrigatorio = true;
				request.setAttribute("obrigatorio", obrigatorio);
			} else if(eEstagioObrigatorio.equals("nao")) {
				obrigatorio = false;
				request.setAttribute("obrigatorio", obrigatorio);
			}else {
				eEstagioObrigatorioMsg = "Valor inválido";
				request.setAttribute("eEstagioObrigatorioMsg", eEstagioObrigatorioMsg);
				isValid = false;
				//TODO Fazer log
				System.out.println(eEstagioObrigatorioMsg);
			}
				
			}else {
				request.setAttribute("eEstagioObrigatorioMsg", eEstagioObrigatorioMsg);
				isValid = false;
				//TODO Fazer log
				System.out.println(eEstagioObrigatorioMsg);
			}			
		
				
		
		/**
		 * Validação do Id do Professor Orientador, usando métodos da Classe ValidaUtils.
		 * Consultando a lista de Professores para validar 
		 */
		String idProfessorMsg = "";
		campo = "Professor Orientador";
		if (!(idProfessorOrientador.trim().isEmpty() || idProfessorOrientador == null)) {
			idProfessorMsg = ValidaUtils.validaInteger(campo, idProfessorOrientador);
			if (idProfessorMsg.trim().isEmpty()) {
				Integer idProfessor = Integer.parseInt(idProfessorOrientador);
				List<ProfessorOrientador> listaProfessores = ProfessorOrientadorServices.listarProfessorOrientador();
				if (listaProfessores != null) {
					if (listaProfessores.contains(new ProfessorOrientador(idProfessor))) {
						request.setAttribute("idProfessor", idProfessor);
					} else {
						idProfessorMsg = "Professor escolhido não está cadastrado";
						isValid = false;
						//TODO Fazer log
						System.out.println(idProfessorMsg);
					}
				} else {
					idProfessorMsg = "Nenhum professor cadastrado no banco";
					isValid = false;
					//TODO Fazer log
					System.out.println(idProfessorMsg);
				}
			} else {
				request.setAttribute("idProfessorMsg", idProfessorMsg);
				isValid = false;
				//TODO Fazer log
				System.out.println(idProfessorMsg);
			}
		}
		
		/**
		 * Validação do Id do Aluno, usando métodos da Classe ValidaUtils.
		 * Instanciando o objeto e passando como Atributo para a inclusão, caso seja validado.
		 */
		String idAlunoMsg = "";
		campo = "Aluno";
		idAlunoMsg = ValidaUtils.validaObrigatorio(campo, idAluno);
		if (idAlunoMsg.trim().isEmpty()) {
			idAlunoMsg = ValidaUtils.validaInteger(campo, idAluno);
			if (idAlunoMsg.trim().isEmpty()) {
				Integer idAlunoInt = Integer.parseInt(idAluno);
				List<Aluno> listaAlunos = AlunoServices.listarAlunos();
				if (listaAlunos != null) {
					if (listaAlunos.contains(new Aluno(idAlunoInt))) {
						request.setAttribute("id", idAlunoInt);
					} else {
						idAlunoMsg = "Aluno escolhido não está cadastrado";
						isValid = false;
						//TODO Fazer log
						System.out.println(idAlunoMsg);
					}
				} else {
					idAlunoMsg = "Nenhum aluno cadastrado no banco";
					isValid = false;
					//TODO Fazer log
					System.out.println(idAlunoMsg);
				}

			} else {
				request.setAttribute("idAlunoMsg", idAlunoMsg);
				isValid = false;
				//TODO Fazer log
				System.out.println(idAlunoMsg);
			}
		} else {
			request.setAttribute("idAlunoMsg", idAlunoMsg);
			isValid = false;
			//TODO Fazer log
			System.out.println(idAlunoMsg);
		}
		
		String convenioMsg = "";
		campo = "Número do convênio";
		convenioMsg = ValidaUtils.validaObrigatorio(campo, convenio);
		if (convenioMsg.trim().isEmpty()) {
			convenioMsg = ValidaUtils.validaTamanho(campo, 10, convenio);
			if (convenioMsg.trim().isEmpty()) {
				request.setAttribute("convenio", convenio);
			} else {
				request.setAttribute("convenioMsg", convenioMsg);
				isValid = false;
				//TODO Fazer log
				System.out.println(convenioMsg);
			}
		} else {
			request.setAttribute("convenioMsg", convenioMsg);
			isValid = false;
			//TODO Fazer log
			System.out.println(convenioMsg);
		}				
		
		/**
		 * Teste da variável booleana após validação.
		 * Redirecionamento para a inclusão ou devolver para o formulário com as mensagens.
		 */
		if (isValid) {
			request.getRequestDispatcher("/IncluirTermoEstagioServlet").forward(request, response);
		} else {
			String msg = "Alguns campos precisam de atenção";
			request.setAttribute("msg", msg);
			request = carregarListas(request);
			
			request.getRequestDispatcher("/form_termo_estagio.jsp").forward(request, response);
		}	

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
