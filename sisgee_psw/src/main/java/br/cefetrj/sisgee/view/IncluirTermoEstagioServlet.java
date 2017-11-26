package br.cefetrj.sisgee.view;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import br.cefetrj.sisgee.control.TermoEstagioServices;
import br.cefetrj.sisgee.model.entity.Aluno;
import br.cefetrj.sisgee.model.entity.Convenio;
import br.cefetrj.sisgee.model.entity.ProfessorOrientador;
import br.cefetrj.sisgee.model.entity.TermoEstagio;

/**
 * 
 * @author Paulo Cantuária
 * @since1.0
 *
 */

@WebServlet("/IncluirTermoEstagioServlet")
public class IncluirTermoEstagioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//TODO remover saída do console		
		System.out.println("iniciando o Incluir o Termo");
		
		Date dataInicioTermoEstagio = (Date)request.getAttribute("dataInicio");
		Date dataFimTermoEstagio = (Date)request.getAttribute("dataFim");		
		Integer cargaHorariaTermoEstagio = (Integer)request.getAttribute("cargaHoraria");
		Float valorBolsa = (Float)request.getAttribute("valor");
		String enderecoTermoEstagio = (String)request.getAttribute("enderecoTermoEstagio");
		String numeroEnderecoTermoEstagio = (String)request.getAttribute("numeroEnderecoTermoEstagio");
		String complementoEnderecoTermoEstagio = (String)request.getAttribute("complementoEnderecoTermoEstagio");
		String bairroEnderecoTermoEstagio = (String)request.getAttribute("bairroEnderecoTermoEstagio");
		String cepEnderecoTermoEstagio = (String)request.getAttribute("cepEnderecoTermoEstagio");
		String cidadeEnderecoTermoEstagio = (String)request.getAttribute("cidadeEnderecoTermoEstagio");
		String estadoEnderecoTermoEstagio = (String)request.getAttribute("estadoEnderecoTermoEstagio");
		Boolean eEstagioObrigatorio = (Boolean)request.getAttribute("obrigatorio");
		Aluno aluno = new Aluno((Integer)request.getAttribute("idAlunoInt"));
		Convenio convenio = new Convenio((String)request.getAttribute("convenio"));
		ProfessorOrientador professorOrientador = new ProfessorOrientador((Integer)request.getAttribute("idProfessor"));
		
		TermoEstagio termoEstagio = new TermoEstagio(dataInicioTermoEstagio, dataFimTermoEstagio, cargaHorariaTermoEstagio,
				 valorBolsa,  enderecoTermoEstagio,  numeroEnderecoTermoEstagio,
				 complementoEnderecoTermoEstagio,  bairroEnderecoTermoEstagio,  cepEnderecoTermoEstagio,
				 cidadeEnderecoTermoEstagio,  estadoEnderecoTermoEstagio,  eEstagioObrigatorio,
				 aluno,  convenio,  professorOrientador);
		
		String msg = "";
		Logger lg = Logger.getLogger(IncluirTermoEstagioServlet.class);
		try{			
			TermoEstagioServices.incluirTermoEstagio(termoEstagio);
			msg = "Registro de Termo de Estágio concluído com sucesso.";
			request.setAttribute("msg", msg);
			lg.info(msg);
			request.getRequestDispatcher("/index.jsp").forward(request, response);
			
			//TODO remover saída do console
			
			
		}catch(Exception e) {
			msg = "Ocorreu um erro inesperado ao tentar registrar o termo. Tente novamente ou contate o suporte caso o erro persita.";
			request.setAttribute("msg", msg);
			
			lg.error("Exception ao tentar inserir o Termo de Estágio", e);
			request.getRequestDispatcher("FormTermoEstagioServlet").forward(request, response);
			
			//TODO remover saída do console
			System.out.println(msg);
		}
		
		System.out.println(msg);
		
	}

}
