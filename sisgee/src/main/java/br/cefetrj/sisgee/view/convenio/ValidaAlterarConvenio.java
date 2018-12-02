    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetrj.sisgee.view.convenio;

import br.cefetrj.sisgee.control.AgenteIntegracaoServices;
import br.cefetrj.sisgee.control.ConvenioServices;
import br.cefetrj.sisgee.control.EmpresaServices;
import br.cefetrj.sisgee.control.PessoaServices;
import br.cefetrj.sisgee.model.entity.AgenteIntegracao;
import br.cefetrj.sisgee.model.entity.Convenio;
import br.cefetrj.sisgee.model.entity.Empresa;
import br.cefetrj.sisgee.model.entity.Pessoa;
import br.cefetrj.sisgee.view.utils.ServletUtils;
import br.cefetrj.sisgee.view.utils.ValidaUtils;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet para validar dados do renovar convenio
 * 
 * @author Lucas Lima
 */
@WebServlet("/ValidaAlterarConvenio")
public class ValidaAlterarConvenio extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * Post para validar dados de renovar convenio
     * @param request um objeto HttpServletRequest que contém a solicitação feita pelo cliente do servlet.
     * @param response um objeto HttpServletResponse que contém a resposta que o servlet envia para o cliente
     * @throws ServletException se o pedido do service não puder ser tratado
     * @throws IOException se um erro de entrada ou saída for detectado quando o servlet manipula o pedido 
     */
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("ACESSOU O ValidaAlterarConvenio");
        Locale locale = ServletUtils.getLocale(request);
        ResourceBundle messages = ResourceBundle.getBundle("Messages", locale);
        
        //Recebe as IDs
        String idPessoa = request.getParameter("idPessoa");
        String idEmpresa = request.getParameter("idEmpresa");
        String idConvenio = request.getParameter("idConvenio");
        System.out.println("idPessoa: " + idPessoa);
        System.out.println("idEmpresa: " + idEmpresa);
        System.out.println("idConvenio: " + idConvenio);

        
        boolean pessoaJuridica = true;
        
        //Recebe as informaçoes(alteradas ou nao) quando é Pessoa Juridica
        String cnpj = request.getParameter("cnpj").replaceAll("[.|/|-]", "");
        String nomeEmpresa = request.getParameter("nomeEmpresa");
        String isEmpresa = request.getParameter("isEmpresa");
        String agenteIntegracao = request.getParameter("agenteIntegracao");
        String dataAssinaturaConvenioEmpresa = request.getParameter("dataAssinaturaConvenioEmpresa");
        String emailEmpresa = request.getParameter("emailEmpresa");
        String telefoneEmpresa = request.getParameter("telefoneEmpresa");
        String contatoEmpresa = request.getParameter("contatoEmpresa");
        String convenioAnoEmpresa = request.getParameter("convenioAnoEmpresa");

        System.out.println("CNPJ RECEBIDO: " + cnpj);
        System.out.println(nomeEmpresa);
        System.out.println(agenteIntegracao);
        System.out.println(dataAssinaturaConvenioEmpresa);
        System.out.println(emailEmpresa);
        System.out.println(telefoneEmpresa);
        System.out.println(contatoEmpresa);
        System.out.println(convenioAnoEmpresa);
        
        
        //Recebe as informaçoes quando é Pessoa Fisica
        String cpf = request.getParameter("cpf").replaceAll("[.|/|-]", "");
        String nomePessoa = request.getParameter("nomePessoa");
        String dataAssinaturaConvenioPessoa = request.getParameter("dataAssinaturaConvenioPessoa");
        String emailPessoa = request.getParameter("emailPessoa");
        String telefonePessoa = request.getParameter("telefonePessoa");
        String convenioAnoPessoa = request.getParameter("convenioAnoPessoa");
        String isPessoa = request.getParameter("isPessoa");
        
        System.out.println("CPF RECEBIDO: " + cpf);
        System.out.println(nomePessoa);
        System.out.println(emailPessoa);
        System.out.println(telefonePessoa);
        System.out.println(convenioAnoPessoa);
        
        
        
        //Recebe o numero do convenio
        String convenioNumero = request.getParameter("convenioNumero");
        System.out.println(convenioNumero);
        
        Convenio convenio = ConvenioServices.buscarConvenioByNumeroConvenio(convenioNumero);
        String dataAssinaturaEmpresa;
        
        request.setAttribute("numero", convenioNumero);
        request.setAttribute("convenioRenovar", convenio);
        
        if (idPessoa != null) {
            pessoaJuridica = false;
        }

        String EmpresaMsg = "";
        String cnpjMsg = "";
        
        
        //String cnpj = request.getParameter("cnpj");
        //System.out.println("CNPJ RECEBIDO PELO OUTRO PARAMETER: " + cnpj);
        //String cpf = request.getParameter("cpf");
        
        Empresa empresa = EmpresaServices.buscarEmpresaByCnpj(cnpj);
        Pessoa pessoa = PessoaServices.buscarPessoaByCpf(cpf);
        
        //System.out.println(pessoa.getIdPessoa());
        Convenio convenio2 = null;
        boolean isValid = true;
        Integer tamanho = 0;  
        
        /**
         * --------------------------------------------------------------------------------------------------------
         */
        
        if (pessoaJuridica) {
            /**
             * Validação do CNPJ da empresa usando os métodos da Classe
             * ValidaUtils Campo obrigatório; Tamanho de 14 caracteres; CNPJ
             * repetido.
             */
            cnpjMsg = "";
            tamanho = 14;
            cnpjMsg = ValidaUtils.validaObrigatorio("CNPJ", cnpj);
            if (cnpjMsg.trim().isEmpty()) {
                cnpjMsg = ValidaUtils.validaInteger("CNPJ", cnpj);
                if (cnpjMsg.trim().isEmpty()) {
                    cnpjMsg = ValidaUtils.validaTamanhoExato("CNPJ", tamanho, cnpj);
                    if (cnpjMsg.trim().isEmpty()) {
                        if (cnpjMsg.trim().isEmpty()) {
                            AgenteIntegracao a = AgenteIntegracaoServices.buscarAgenteIntegracaoByCnpj(cnpj);
                            if (a == null) {
                                request.setAttribute("cnpj", cnpj);
                            } else {
                                cnpjMsg = messages.getString("br.cefetrj.sisgee.valida_cadastro_empresa_servlet.msg_empresa_duplicada");
                                request.setAttribute("cnpjMsg", cnpjMsg);
                                isValid = false;
                                System.out.println(cnpjMsg);
                            }
                        } else {
                            request.setAttribute("cnpj", cnpj);
                        }
                    }else {
                        cnpjMsg = messages.getString(cnpjMsg);
                        cnpjMsg = ServletUtils.mensagemFormatada(cnpjMsg, locale, tamanho);
                        request.setAttribute("cnpjMsg", cnpjMsg);
                        isValid = false;
                        System.out.println(cnpjMsg);
                    }
                } else {
                    cnpjMsg = messages.getString(cnpjMsg);
                    request.setAttribute("cnpjMsg", cnpjMsg);
                    isValid = false;
                    System.out.println(cnpjMsg);
                }
            } else {
                cnpjMsg = messages.getString(cnpjMsg);
                request.setAttribute("cnpjMsg", cnpjMsg);
                isValid = false;
                System.out.println(cnpjMsg);
            }
        
            
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
                    request.setAttribute("obrigatorio", obrigatorio);
                } else {
                    agenteIntegracaoMsg = messages.getString(agenteIntegracaoMsg);
                    request.setAttribute("agenteIntegracaoMsg", agenteIntegracaoMsg);
                    isValid = false;
                    System.out.println(agenteIntegracaoMsg);
                }
            } else {
                agenteIntegracaoMsg = messages.getString(agenteIntegracaoMsg);
                request.setAttribute("agenteIntegracaoMsg", agenteIntegracaoMsg);
                isValid = false;
                System.out.println(agenteIntegracaoMsg);
            }
            
            
            /**
             * Validação da Razão Social do Cadastro Empresa usando métodos da
             * Classe ValidaUtils. Campo obrigatório; Tamanho máximo de 100
             * caracteres; Razão Social já existente.
             */
            
            String nomeEmpresaMsg = "";
            

            nomeEmpresaMsg = ValidaUtils.validaObrigatorio("Razão Social", nomeEmpresa);
            if (nomeEmpresaMsg.trim().isEmpty()) {
                nomeEmpresaMsg = ValidaUtils.validaTamanho("Razão Social", 100, nomeEmpresa);
                if (nomeEmpresaMsg.trim().isEmpty()) {
                        Empresa e = EmpresaServices.buscarEmpresaByNome(nomeEmpresa);
                        if (e == null) {
                            request.setAttribute("nomeEmpresa", nomeEmpresa);
                        } else {
                            nomeEmpresaMsg = messages.getString("br.cefetrj.sisgee.valida_cadastro_empresa_servlet.msg_empresa_duplicada");
                            request.setAttribute("nomeEmpresaMsg", nomeEmpresaMsg);
                            isValid = false;
                            System.out.println(nomeEmpresaMsg);
                    }
                } else {
                    nomeEmpresaMsg = messages.getString(nomeEmpresaMsg);
                    nomeEmpresaMsg = ServletUtils.mensagemFormatada(nomeEmpresaMsg, locale, tamanho);
                    request.setAttribute("nomeEmpresaMsg", nomeEmpresaMsg);
                    isValid = false;
                    System.out.println(nomeEmpresaMsg);
                }
            } else {
                nomeEmpresaMsg = messages.getString(nomeEmpresaMsg);
                request.setAttribute("nomeEmpresaMsg", nomeEmpresaMsg);
                isValid = false;
                System.out.println(nomeEmpresaMsg);
            }
            
            /**
             * Validação do Email do Cadastro Empresa usando métodos da Classe
             * ValidaUtils. Campo obrigatório; Tamanho máximo de 50 caracteres;
             */
            String emailEmpresaMsg = "";
            emailEmpresaMsg = ValidaUtils.validaObrigatorio("emailEmpresa", emailEmpresa);
            if (emailEmpresaMsg.trim().isEmpty()) {
                emailEmpresaMsg = ValidaUtils.validaTamanho("emailEmpresa", 50, emailEmpresa);
                if (emailEmpresaMsg.trim().isEmpty()) {
                    emailEmpresaMsg = ValidaUtils.validaEmail("emailEmpresa", emailEmpresa);
                    if (emailEmpresaMsg.trim().isEmpty()) {
                        request.setAttribute("emailEmpresa", emailEmpresa);
                    } else {
                        emailEmpresaMsg = messages.getString("br.cefetrj.sisgee.form_termo_estagio_servlet.valor_invalido");
                        request.setAttribute("emailEmpresaMsg", emailEmpresaMsg);
                        isValid = false;
                        System.out.println(emailEmpresaMsg);
                    }

                } else {
                    emailEmpresaMsg = messages.getString("br.cefetrj.sisgee.form_termo_estagio_servlet.valor_invalido");
                    request.setAttribute("emailEmpresaMsg", emailEmpresaMsg);
                    isValid = false;
                    System.out.println(emailEmpresaMsg);
                }

            }

        }

        /**
         * Teste das variáveis booleanas após validação. Redirecionamento para a
         * inclusão ou devolver para o formulário com as mensagens.
         */
        
//        System.out.println("CNPJ MSG FINAL: " + cnpjMsg);
//        System.out.println("EMPRESA MSG FINAL: " + EmpresaMsg);
        if (isValid) {
            request.getRequestDispatcher("/IncluirCadastroAlteradoEmpresaServlet").forward(request, response);

        } else {
            String msg = messages.getString("br.cefetrj.sisgee.valida_cadastro_empresa_servlet.msg_atencao");
            request.setAttribute("msg", msg);
            request.getRequestDispatcher("/form_alterar_convenio.jsp").forward(request, response);

        }
    }
}
    



