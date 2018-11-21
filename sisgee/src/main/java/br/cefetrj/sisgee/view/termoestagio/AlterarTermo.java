package br.cefetrj.sisgee.view.termoestagio;

import br.cefetrj.sisgee.control.AgenteIntegracaoServices;
import br.cefetrj.sisgee.control.AlunoServices;
import br.cefetrj.sisgee.control.EmpresaServices;
import br.cefetrj.sisgee.control.ProfessorOrientadorServices;
import br.cefetrj.sisgee.control.TermoAditivoServices;
import br.cefetrj.sisgee.control.TermoEstagioServices;
import br.cefetrj.sisgee.model.entity.AgenteIntegracao;
import br.cefetrj.sisgee.model.entity.Aluno;
import br.cefetrj.sisgee.model.entity.Empresa;
import br.cefetrj.sisgee.model.entity.ProfessorOrientador;
import br.cefetrj.sisgee.model.entity.TermoAditivo;
import br.cefetrj.sisgee.model.entity.TermoEstagio;
import br.cefetrj.sisgee.view.utils.UF;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet disponibiliza possibilidade de alterar os registros de termo de estagio e aditivo
 * @author Vinicius Paradellas
 */
@WebServlet("/AlterarTermo")
public class AlterarTermo extends HttpServlet {

    /**
     * 
     * @param request um objeto HttpServletRequest que contém a solicitação feita pelo cliente do servlet.
     * @param response um objeto HttpServletResponse que contém a resposta que o servlet envia para o cliente
     * @throws ServletException se o pedido do service não puder ser tratado
     * @throws IOException se um erro de entrada ou saída for detectado quando o servlet manipula o pedido 
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        String ide = req.getParameter("ide");
        String ida = req.getParameter("ida");
        String matricula = req.getParameter("matricula");
        UF[] uf = UF.asList();
        TermoEstagio termoEstagio=null;
        TermoAditivo termoAditivo=null;
        System.out.println(ida);
        System.out.println(ide);
        System.out.println(matricula);
        
        Aluno aluno=AlunoServices.buscarAlunoByMatricula(matricula);
        
        if(ide!=null)
        termoEstagio=TermoEstagioServices.buscarTermoEstagio(Integer.parseInt(ide));

        if(ida!=null)
        termoAditivo=TermoAditivoServices.buscarTermoAditivo(Integer.parseInt(ida));

        req.setAttribute("uf", uf);
        
        req.setAttribute("idTermo", ide);    
        
			/** Dados de aluno*/
                        req.setAttribute("alMatricula", aluno.getMatricula());
                        req.setAttribute("alNome", aluno.getPessoa().getNome());
                        req.setAttribute("alCampus", aluno.getCurso().getCampus().getNomeCampus());
                        req.setAttribute("alCurso", aluno.getCurso());
			req.setAttribute("alId", aluno.getIdAluno());
                        System.out.println("ID Aluno:" + aluno.getIdAluno());
                        /** Dados de convenio*/
                        req.setAttribute("cvNumero", termoEstagio.getConvenio().getNumero());
                        if(termoEstagio.getConvenio().getEmpresa()==null){
                            req.setAttribute("cvNome", termoEstagio.getConvenio().getPessoa().getNome());
                            req.setAttribute("tConvenio","pf");
                            req.setAttribute("cvCpfCnpj",termoEstagio.getConvenio().getPessoa().getCpf());
                            req.setAttribute("nomeAgenciada",termoEstagio.getNomeAgenciada());
                            
                        }else{
                            req.setAttribute("cvNome", termoEstagio.getConvenio().getEmpresa().getRazaoSocial());
                            req.setAttribute("tConvenio","pj");
                            req.setAttribute("agIntegracao",termoEstagio.getConvenio().getEmpresa().isAgenteIntegracao());
                            req.setAttribute("cvCpfCnpj", termoEstagio.getConvenio().getEmpresa().getCnpjEmpresa());
                            req.setAttribute("nomeAgenciada",termoEstagio.getNomeAgenciada());
                        }
                        
                        /** Dados de Vigência */
                        req.setAttribute("vidataInicioTermoEstagio",termoEstagio.getDataInicioTermoEstagio2());
                        req.setAttribute("vidataFimTermoEstagio",termoEstagio.getDataFimTermoEstagioVisu(termoAditivo));
                        
                        
                        /** Dados de Carga Horária */
                        req.setAttribute("cacargaHorariaTermoEstagio",termoEstagio.getCargaHorariaTermoEstagioVisu(termoAditivo));
                        
                        /** Dados de Valor Bolsa */
                        req.setAttribute("vavalorBolsa",termoEstagio.getValorBolsaVisu(termoAditivo));
                        /** Dados de Local */
                        req.getServletContext().setAttribute("enenderecoTermoEstagio",termoEstagio.getEnderecoTermoEstagioVisu(termoAditivo));
                        req.setAttribute("ennumeroEnderecoTermoEstagio",termoEstagio.getNumeroEnderecoTermoEstagioVisu(termoAditivo));
                        req.setAttribute("encomplementoEnderecoTermoEstagio",termoEstagio.getComplementoEnderecoTermoEstagioVisu(termoAditivo));
                        req.setAttribute("enbairroEnderecoTermoEstagio",termoEstagio.getBairroEnderecoTermoEstagioVisu(termoAditivo));
                        req.setAttribute("encidadeEnderecoTermoEstagio",termoEstagio.getCidadeEnderecoTermoEstagioVisu(termoAditivo));
                        req.setAttribute("enuf",termoEstagio.getEstadoEnderecoTermoEstagioVisu(termoAditivo).trim());
                        
                        
                        req.setAttribute("encepEnderecoTermoEstagio",termoEstagio.getCepEnderecoTermoEstagioVisu(termoAditivo));
                        
                        
                        /** Dados de Supervisor */
                        

                        req.setAttribute("eobrigatorio", termoEstagio.getEEstagioObrigatorio());
                        
                        req.setAttribute("nomeSupervisor",termoEstagio.getNomeSupervisorVisu(termoAditivo));
                        req.setAttribute("cargoSupervisor",termoEstagio.getCargoSupervisorVisu(termoAditivo));
                        
                        /** Dados de Professor */
                        req.setAttribute("pfnomeprofessor",termoEstagio.getProfessorOrientadorVisu(termoAditivo));

                        
                        req.setAttribute("idProfessor", termoEstagio.getProfessorOrientadorVisu(termoAditivo).getIdProfessorOrientador());
                        System.out.println("ID DO PROFESSOR: " + termoEstagio.getProfessorOrientadorVisu(termoAditivo).getIdProfessorOrientador());
                        
                        carregarListas(req);
        
        req.getRequestDispatcher("/form_termo_Altera.jsp").forward(req, resp);
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