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

        Locale locale = ServletUtils.getLocale(request);
        ResourceBundle messages = ResourceBundle.getBundle("Messages", locale);

        String tipoPessoa = request.getParameter("tipoPessoa");
        
        //Dados sobre Pessoa Fisica
        boolean pessoaJuridica = true;
        String cnpjEmpresa = request.getParameter("cnpjEmpresa");
        if(cnpjEmpresa != null){
            cnpjEmpresa = cnpjEmpresa.replaceAll("[.|/|-]", "");
        }
        String nomeEmpresa = request.getParameter("nomeEmpresa");
        String agenteIntegracao = request.getParameter("agenteIntegracao");
        System.out.println("AGENTE INTEGRAÇÃO TESTE:" + agenteIntegracao);
        String dataAssinaturaConvenioEmpresa = request.getParameter("dataAssinaturaConvenioEmpresa");
        String emailEmpresa = request.getParameter("emailEmpresa");
        String telefoneEmpresa = request.getParameter("telefoneEmpresa");
        String contatoEmpresa = request.getParameter("contatoEmpresa");
        String convenioAnoEmpresa = request.getParameter("convenioAnoEmpresa");
        
        //Dados de Pessoa Fisica
        String cpfPessoa = request.getParameter("cpfPessoa");
        if(cpfPessoa != null){
            cpfPessoa = cpfPessoa.replaceAll("[.|/|-]", "");
        }        
        String nomePessoa = request.getParameter("nomePessoa");
        String emailPessoa = request.getParameter("emailPessoa");
        String telefonePessoa = request.getParameter("telefonePessoa");
        String dataAssinaturaConvenioPessoa = request.getParameter("dataAssinaturaConvenioPessoa");
        String convenioAnoPessoa = request.getParameter("convenioAnoPessoa"); 
        
        
        String convenioNumero = request.getParameter("convenioNumero");
        
        Convenio convenio = ConvenioServices.buscarConvenioByNumeroConvenio(convenioNumero);
        String dataAssinaturaEmpresa;
        String dataAssinaturaPessoa;
        
        //Informaçoes Iniciais dos dados de CPF e CNPJ
        String cpfInicial = request.getParameter("cpfInicial");
        String nomeEmpresaInicial = request.getParameter("nomeEmpresaInicial");  
        String cnpjInicial = request.getParameter("cnpjInicial");
        String nomePessoaInicial = request.getParameter("nomePessoaInicial");
        
        if(convenio.getEmpresa()!=null){
            request.setAttribute("isEmpresa", "sim");
            if(convenio.getEmpresa().isAgenteIntegracao()){
                request.setAttribute("simAgenteIntegracao", "sim");
            }else{
                request.setAttribute("naoAgenteIntegracao", "sim");
            }
            request.setAttribute("convenioNumero", convenioNumero);
            request.setAttribute("cnpj", cnpjEmpresa);
            request.setAttribute("razao", nomeEmpresa);
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            dataAssinaturaEmpresa = format.format(convenio.getDataAssinatura());
            request.setAttribute("dataAssinaturaConvenioEmpresa", dataAssinaturaEmpresa);
            request.setAttribute("emailEmpresa", emailEmpresa);
            request.setAttribute("telefoneEmpresa", telefoneEmpresa);
            request.setAttribute("contatoEmpresa", contatoEmpresa);
            request.setAttribute("convenioAnoEmpresa", convenioAnoEmpresa);
               
        }else{
            request.setAttribute("convenioNumero", convenioNumero);
            request.setAttribute("isPessoa", "sim");
            request.setAttribute("cpf", cpfPessoa);
            request.setAttribute("nome", nomePessoa);
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            dataAssinaturaPessoa = format.format(convenio.getDataAssinatura());
            request.setAttribute("dataAssinaturaConvenioPessoa", dataAssinaturaPessoa);
            request.setAttribute("emailPessoa", emailPessoa);
            request.setAttribute("telefonePessoa", telefonePessoa);
            request.setAttribute("convenioAnoPessoa", convenioAnoPessoa);
            
        }
        
        request.getSession().setAttribute("numero", convenioNumero);
        
        request.setAttribute("convenioRenovar", convenio);
        
        
        
        
        if (tipoPessoa.equals("nao")) {
            pessoaJuridica = false;
        }

        String EmpresaMsg = "";
        String cnpjEmpresaMsg = "";
        
        System.out.println("-----------------------------------------------------------------------------------------");
        System.out.println("--------------------------------Pessoa Juridica------------------------------------");
        System.out.println("DADOS RECEBIDOS PELO VALIDA");
        System.out.println("Tipo pessoa: "  + tipoPessoa);
        System.out.println("CNPJ RECEBIDO: " + cnpjEmpresa);
        System.out.println("CNPJ INICIAL RECEBIDO: " + cnpjInicial);
        System.out.println("Nome Empresa: " + nomeEmpresa);
        System.out.println("Agente Integracao: " + agenteIntegracao);
        System.out.println("Data Convenio Empresa: " + dataAssinaturaConvenioEmpresa);
        System.out.println("Email Empresa: " + emailEmpresa);
        System.out.println("Telefone Empresa: " + telefoneEmpresa);
        System.out.println("Contato Empresa: " + contatoEmpresa);
        System.out.println("Convenio Empresa: " + convenioAnoEmpresa);
        System.out.println("--------------------------------Pessoa Fisica------------------------------------");
        System.out.println("CFP RECEBIDO: " + cpfPessoa);
        System.out.println("CFP INICIAL RECEBIDO: " + cpfInicial);
        System.out.println("Nome Pessoa: " + nomePessoa);
        System.out.println("Email Pessoa: " + emailPessoa);
        System.out.println("Data Convenio Pessoa: " + dataAssinaturaConvenioPessoa);
        System.out.println("Telefone Pessoa: " + telefonePessoa);
        System.out.println("Convenio Pessoa: " + convenioAnoPessoa);
        System.out.println("-----------------------------------------------------------------------------------------");
        System.out.println("Numero Convenio Pessoa: " + convenioNumero);
        
        String cnpj = request.getParameter("cnpjEmpresa");
        System.out.println("CNPJ RECEBIDO PELO OUTRO PARAMETER: " + cnpj);
        String cpf = request.getParameter("cpfPessoa");
        System.out.println("CPF RECEBIDO PELO OUTRO PARAMETER: " + cpf);
        
        System.out.println("-----------------------------------------------------------------------------------------");
        
        Empresa empresa = EmpresaServices.buscarEmpresaByCnpj(cnpj);
        Pessoa pessoa = PessoaServices.buscarPessoaByCpf(cpf);
        Convenio convenio2 = null;
        boolean isValid = true;
        Integer tamanho = 0;
        if (empresa != null){
                
            
                EmpresaMsg = "";
                EmpresaMsg = ValidaUtils.validaObrigatorio("cnpj", cnpj);
                if (EmpresaMsg.trim().isEmpty()) {
                    EmpresaMsg = ValidaUtils.validaInteger("cnpj", cnpj);
                    if(EmpresaMsg.trim().isEmpty()){
                        if (!cnpj.equals("") || cnpj != null) {
                            if(!cnpjInicial.equals(cnpj)){
                                convenio2 = ConvenioServices.buscarConvenioByEmpresa(empresa);
                                if(convenio2 == null){
                                    request.setAttribute("cnpj", cnpj);
                            }else{
                                EmpresaMsg = messages.getString("br.cefetrj.sisgee.valida_cadastro_empresa_servlet.msg_convenio_existente");
                                request.setAttribute("EmpresaMsg", EmpresaMsg);
                                isValid = false;
                                }                          
                        }
                        }
                        else{
                           EmpresaMsg = messages.getString("br.cefetrj.sisgee.form_termo_estagio_servlet.valor_invalido");
                           request.setAttribute("EmpresaMsg", EmpresaMsg);
                           isValid = false;
                        }
                    }else{
                        EmpresaMsg = messages.getString("br.cefetrj.sisgee.form_termo_estagio_servlet.valor_invalido");
                        request.setAttribute("EmpresaMsg", EmpresaMsg);
                        isValid = false;
                    }
                }else{
                    EmpresaMsg = messages.getString("br.cefetrj.sisgee.form_termo_estagio_servlet.valor_invalido");
                    request.setAttribute("EmpresaMsg", EmpresaMsg);
                    isValid = false;
                } 
        }else{
            String PessoaMsg = "";
            
                PessoaMsg = ValidaUtils.validaObrigatorio("cpf", cpf);
                if (PessoaMsg.trim().isEmpty()) {
                    PessoaMsg = ValidaUtils.validaInteger("cpf", cpf);
                    if(PessoaMsg.trim().isEmpty()){
                        if (!cpf.equals("") || cpf != null) {
                            if(!cpfInicial.equals(cpf)){
                                convenio2 = ConvenioServices.buscarConvenioByPessoa(pessoa);
                                if(convenio2 != null){
                                 request.setAttribute("cpf", cpf);
                            }else{
                                PessoaMsg = messages.getString("br.cefetrj.sisgee.valida_cadastro_empresa_servlet.msg_convenio_existente");
                                request.setAttribute("PessoaMsg", PessoaMsg);
                                isValid = false;
                                }                          
                        }
                        }
                        else{
                           PessoaMsg = messages.getString("br.cefetrj.sisgee.form_termo_estagio_servlet.valor_invalido");
                           request.setAttribute("PessoaMsg", PessoaMsg);
                           isValid = false;
                        }
                    }else{
                        PessoaMsg = messages.getString("br.cefetrj.sisgee.form_termo_estagio_servlet.valor_invalido");
                        request.setAttribute("PessoaMsg", PessoaMsg);
                        isValid = false;
                    }
                }else{
                    PessoaMsg = messages.getString("br.cefetrj.sisgee.form_termo_estagio_servlet.valor_invalido");
                    request.setAttribute("PessoaMsg", PessoaMsg);
                    isValid = false;
                } 
                
                System.out.println("CPF Pessoa Msg" + PessoaMsg);
                System.out.println("CNPJ Pessoa Msg" + EmpresaMsg);
                
        } 
        
        if (pessoaJuridica) {
            
            /**
             * @Author Romulo Oliveira
             * Motivo de comentar a validaçao do cnpj: A mesma validaçao é feita acima.
             */    
            
//            /**
//             * Validação do CNPJ da empresa usando os métodos da Classe
//             * ValidaUtils Campo obrigatório; Tamanho de 14 caracteres; CNPJ
//             * repetido.
//             */
//            cnpjEmpresaMsg = "";
//            tamanho = 14;
//            cnpjEmpresaMsg = ValidaUtils.validaObrigatorio("CNPJ", cnpjEmpresa);
//            if (cnpjEmpresaMsg.trim().isEmpty()) {
//                //remove caracteres especiais antes de vazer a validação numérica do CNPJ
//                cnpjEmpresa = cnpjEmpresa.replaceAll("[.|/|-]", "");
//                cnpjEmpresaMsg = ValidaUtils.validaInteger("CNPJ", cnpjEmpresa);
//                if (cnpjEmpresaMsg.trim().isEmpty()) {
//                    cnpjEmpresaMsg = ValidaUtils.validaTamanhoExato("CNPJ", tamanho, cnpjEmpresa);
//                    if (cnpjEmpresaMsg.trim().isEmpty()) {
//                        if(!cnpjInicial.equals(cnpj)){
//                        Empresa e = EmpresaServices.buscarEmpresaByCnpj(cnpjEmpresa);
//                        if (e == null) {
//                            if (cnpjEmpresaMsg.trim().isEmpty()) {
//                                AgenteIntegracao a = AgenteIntegracaoServices.buscarAgenteIntegracaoByCnpj(cnpjEmpresa);
//                                if (a == null) {
//                                    request.setAttribute("cnpjEmpresa", cnpjEmpresa);
//                                } else {
//                                    cnpjEmpresaMsg = messages.getString("br.cefetrj.sisgee.valida_cadastro_empresa_servlet.msg_empresa_duplicada");
//                                    request.setAttribute("cnpjEmpresaMsg", cnpjEmpresaMsg);
//                                    isValid = false;
//                                }
//                            } else {
//                                request.setAttribute("cnpjEmpresa", cnpjEmpresa);
//                            }
//                        } else {
//                            cnpjEmpresaMsg = messages.getString("br.cefetrj.sisgee.valida_cadastro_empresa_servlet.msg_empresa_duplicada");
//                            request.setAttribute("cnpjEmpresaMsg", cnpjEmpresaMsg);
//                            isValid = false;
//                        }
//                    }}
//                    else {
//                        cnpjEmpresaMsg = messages.getString(cnpjEmpresaMsg);
//                        cnpjEmpresaMsg = ServletUtils.mensagemFormatada(cnpjEmpresaMsg, locale, tamanho);
//                        request.setAttribute("cnpjEmpresaMsg", cnpjEmpresaMsg);
//                        isValid = false;
//                    }
//                } else {
//                    cnpjEmpresaMsg = messages.getString(cnpjEmpresaMsg);
//                    request.setAttribute("cnpjEmpresaMsg", cnpjEmpresaMsg);
//                    isValid = false;
//                }
//            } else {
//                cnpjEmpresaMsg = messages.getString(cnpjEmpresaMsg);
//                request.setAttribute("cnpjEmpresaMsg", cnpjEmpresaMsg);
//                isValid = false;
//            }
            
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
                }
            } else {
                agenteIntegracaoMsg = messages.getString(agenteIntegracaoMsg);
                request.setAttribute("agenteIntegracaoMsg", agenteIntegracaoMsg);
                isValid = false;
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
                    if(!nomeEmpresaInicial.equals(nomeEmpresa)){
                        Empresa e = EmpresaServices.buscarEmpresaByNome(nomeEmpresa);
                        if (e == null) {
                            request.setAttribute("nomeEmpresa", nomeEmpresa);
                        } else {
                            nomeEmpresaMsg = messages.getString("br.cefetrj.sisgee.valida_cadastro_empresa_servlet.msg_empresa_duplicada");
                            request.setAttribute("nomeEmpresaMsg", nomeEmpresaMsg);
                            isValid = false;
                    }
                }
                } else {
                    nomeEmpresaMsg = messages.getString(nomeEmpresaMsg);
                    nomeEmpresaMsg = ServletUtils.mensagemFormatada(nomeEmpresaMsg, locale, tamanho);
                    request.setAttribute("nomeEmpresaMsg", nomeEmpresaMsg);
                    isValid = false;
                }
            } else {
                nomeEmpresaMsg = messages.getString(nomeEmpresaMsg);
                request.setAttribute("nomeEmpresaMsg", nomeEmpresaMsg);
                isValid = false;
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
                    }

                } else {
                    emailEmpresaMsg = messages.getString("br.cefetrj.sisgee.form_termo_estagio_servlet.valor_invalido");
                    request.setAttribute("emailEmpresaMsg", emailEmpresaMsg);
                    isValid = false;
                }

            }

            /**
             * Validação do Telefone do Cadastro Empresa usando métodos da
             * Classe ValidaUtils. Campo obrigatório; Tamanho máximo de 11
             * caracteres;
             */
            String telefoneEmpresaMsg = "";
            telefoneEmpresaMsg = ValidaUtils.validaObrigatorio("telefoneEmpresa", telefoneEmpresa);
            if (telefoneEmpresaMsg.trim().isEmpty()) {
                telefoneEmpresaMsg = ValidaUtils.validaTamanho("telefoneEmpresa", 11, telefoneEmpresa);

                if (telefoneEmpresaMsg.trim().isEmpty()) {
                    telefoneEmpresa = telefoneEmpresa.replaceAll("[.|/|-]", "");
                    telefoneEmpresaMsg = ValidaUtils.validaInteger("telefoneEmpresa", telefoneEmpresa);
                    if (telefoneEmpresaMsg.trim().isEmpty()) {
                        telefoneEmpresaMsg = ValidaUtils.validaTelefone("telefoneEmpresa", telefoneEmpresa);
                        if (telefoneEmpresaMsg.trim().isEmpty()) {
                            //Alterações, nova condição
                            telefoneEmpresaMsg = ValidaUtils.validaNumeroDDD("telefoneEmpresa", telefoneEmpresa);
                            if (telefoneEmpresaMsg.trim().isEmpty()) {
                                request.setAttribute("telefoneEmpresa", telefoneEmpresa);
                            }else {
                                telefoneEmpresaMsg = messages.getString("br.cefetrj.sisgee.form_termo_estagio_servlet.valor_invalido");
                                request.setAttribute("telefoneEmpresaMsg", telefoneEmpresaMsg);
                                isValid = false;
                            }
                        }else {
                            telefoneEmpresaMsg = messages.getString("br.cefetrj.sisgee.form_termo_estagio_servlet.valor_invalido");
                            request.setAttribute("telefoneEmpresaMsg", telefoneEmpresaMsg);
                            isValid = false;
                        }

                    } else {
                        telefoneEmpresaMsg = messages.getString("br.cefetrj.sisgee.form_termo_estagio_servlet.valor_invalido");
                        request.setAttribute("telefoneEmpresaMsg", telefoneEmpresaMsg);
                        isValid = false;

                    }

                } else {
                    telefoneEmpresaMsg = messages.getString("br.cefetrj.sisgee.form_termo_estagio_servlet.valor_invalido");
                    request.setAttribute("telefoneEmpresaMsg", telefoneEmpresaMsg);
                    isValid = false;
                }

            }

            /**
             * Validação do contato com a Empresa do Cadastro Empresa usando
             * métodos da Classe ValidaUtils. Campo opcional; Tamanho máximo de
             * 50 caracteres;
             */
            String contatoEmpresaMsg = "";
            contatoEmpresaMsg = ValidaUtils.validaObrigatorio("contatoEmpresa", contatoEmpresa);
            if (contatoEmpresaMsg.trim().isEmpty()) {
                contatoEmpresaMsg = ValidaUtils.validaTamanho("contatoEmpresa", 50, contatoEmpresa);
                if (contatoEmpresaMsg.trim().isEmpty()) {
                    contatoEmpresaMsg = ValidaUtils.validaSomenteLetras("contatoEmpresa", contatoEmpresa);
                    if (contatoEmpresaMsg.trim().isEmpty()) {
                        request.setAttribute("contatoEmpresa", contatoEmpresa);

                    } else {
                        contatoEmpresaMsg = messages.getString("br.cefetrj.sisgee.form_termo_estagio_servlet.valor_invalido");
                        request.setAttribute("contatoEmpresaMsg", contatoEmpresaMsg);
                        isValid = false;

                    }

                } else {
                    contatoEmpresaMsg = messages.getString("br.cefetrj.sisgee.form_termo_estagio_servlet.valor_invalido");
                    request.setAttribute("contatoEmpresaMsg", contatoEmpresaMsg);
                    isValid = false;
                    
                }

            }

            /**
             * Validação da Data de Assinatura do Convenio da Pessoa usando os
             * métodos da Classe ValidaUtils Campo obrigatório
             */
            Date dataAssinaturaEmpresa2 = null;
            String dataAssinaturaMsg = "";
            String campo = "Data de Assinatura";

            dataAssinaturaMsg = ValidaUtils.validaObrigatorio(campo, dataAssinaturaConvenioEmpresa);
            if (dataAssinaturaMsg.trim().isEmpty()) {
                dataAssinaturaMsg = ValidaUtils.validaDate(campo, dataAssinaturaConvenioEmpresa);
                if (dataAssinaturaMsg.trim().isEmpty()) {
                    dataAssinaturaMsg = ValidaUtils.validaTamanhoExato(campo, 10, dataAssinaturaConvenioEmpresa);
                    if (dataAssinaturaMsg.trim().isEmpty()) {
                        try {
                            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
                            dataAssinaturaEmpresa2 = format.parse(dataAssinaturaConvenioEmpresa);
                            System.out.println("Data assinatura validada: " + dataAssinaturaEmpresa2);
                            request.setAttribute("dataAssinaturaConvenioEmpresa", dataAssinaturaEmpresa2);
                        } catch (Exception e) {
                            //TODO trocar saída de console por Log
                            System.out.println("Data em formato incorreto, mesmo após validação na classe ValidaUtils");
                            isValid = false;
                        }
                    } else {
                        dataAssinaturaMsg = messages.getString("br.cefetrj.sisgee.form_termo_estagio_servlet.valor_invalido");
                        request.setAttribute("dataAssinaturaEmpresaMsg", dataAssinaturaMsg);
                        isValid = false;
                        //TODO Fazer log

                    }
                } else {
                    dataAssinaturaMsg = messages.getString("br.cefetrj.sisgee.form_termo_estagio_servlet.valor_invalido");
                    request.setAttribute("dataAssinaturaEmpresaMsg", dataAssinaturaMsg);
                    isValid = false;
                    //TODO Fazer log
                    System.out.println(dataAssinaturaMsg);
                }
            } else {
                dataAssinaturaMsg = messages.getString("br.cefetrj.sisgee.form_termo_estagio_servlet.valor_invalido");
                request.setAttribute("dataAssinaturaEmpresaMsg", dataAssinaturaMsg);
                isValid = false;
                //TODO Fazer log
                System.out.println(dataAssinaturaMsg);
            }
            
            String convenioAnoEmpresaMsg = "";
            convenioAnoEmpresaMsg = ValidaUtils.validaObrigatorio("convenioAnoEmpresa", convenioAnoEmpresa);
            if (convenioAnoEmpresaMsg.trim().isEmpty()) {
                convenioAnoEmpresaMsg = ValidaUtils.validaTamanhoExato("convenioAnoEmpresa", 4, convenioAnoEmpresa);
                if (convenioAnoEmpresaMsg.trim().isEmpty()) {
                    convenioAnoEmpresaMsg = ValidaUtils.validaInteger("convenioAnoEmpresa", convenioAnoEmpresa);
                    if (convenioAnoEmpresaMsg.trim().isEmpty()) {
                        request.setAttribute("convenioAnoEmpresa", convenioAnoEmpresa);
                    } else {
                        convenioAnoEmpresaMsg = messages.getString(convenioAnoEmpresaMsg);
                        request.setAttribute("convenioAnoEmpresaMsg", convenioAnoEmpresaMsg);
                        isValid = false;
                        System.out.println(convenioAnoEmpresaMsg);
                    } 
                }else {
                    convenioAnoEmpresaMsg = messages.getString(convenioAnoEmpresaMsg);
                    request.setAttribute("convenioAnoEmpresaMsg", convenioAnoEmpresaMsg);
                    isValid = false;
                    System.out.println(convenioAnoEmpresaMsg);
                    }
            }else{
                convenioAnoEmpresaMsg = messages.getString(convenioAnoEmpresaMsg);
                request.setAttribute("convenioAnoEmpresaMsg", convenioAnoEmpresaMsg);
                isValid = false;
                System.out.println(convenioAnoEmpresaMsg);
            }
            

            System.out.println("teste msg ano" + convenioAnoEmpresaMsg);

        } else {
            
            /**
             * @Author Romulo Oliveira
             * Motivo de comentar a validaçao do cpf: A mesma validaçao é feita acima.
             */  
            /**
             * Validação do CPF da pessoa usando os métodos da Classe
             * ValidaUtils Campo obrigatório; Tamanho de 11 caracteres; CNPJ
             * repetido.
             */
            
            
//            String cpfPessoaMsg = "";
//            tamanho = 11;
//            cpfPessoaMsg = ValidaUtils.validaObrigatorio("CPF", cpfPessoa);
//            if (cpfPessoaMsg.trim().isEmpty()) {
//                //remove caracteres especiais antes de vazer a validação numérica do CNPJ
//                cpfPessoa = cpfPessoa.replaceAll("[.|/|-]", "");
//                cpfPessoaMsg = ValidaUtils.validaInteger("CPF", cpfPessoa);
//                if (cpfPessoaMsg.trim().isEmpty()) {
//                    cpfPessoaMsg = ValidaUtils.validaTamanhoExato("CPF", tamanho, cpfPessoa);
//                    if (cpfPessoaMsg.trim().isEmpty()) {
//                        if(!cpfInicial.equals(cpfPessoa)){
//                            System.out.println("CPF INICIAL "+cpfInicial);
//                            System.out.println("CPF "+cpfPessoa);
//                        Pessoa e = PessoaServices.buscarPessoaByCpf(cpfPessoa);
//                        System.out.println("Pessoa achada: " + e);
//                        if (e == null) {
//                            System.out.println(cpfPessoa);
//                            //request.setAttribute("cpfPessoa", cpfPessoa.replaceAll("[.|/|-]", ""));
//                            request.setAttribute("cpfPessoa", cpfPessoa);
//                        } else {
//                            cpfPessoaMsg = messages.getString("br.cefetrj.sisgee.valida_cadastro_empresa_servlet.msg_pessoafisica_duplicada");
//                            request.setAttribute("cpfPessoaMsg", cpfPessoaMsg);
//                            isValid = false;
//                        }
//                    }}
//                    else {
//                        cpfPessoaMsg = messages.getString("br.cefetrj.sisgee.form_termo_estagio_servlet.valor_invalido");
//                        request.setAttribute("cpfPessoaMsg", cpfPessoaMsg);
//                        isValid = false;
//                    }
//                } else {
//                    cpfPessoaMsg = messages.getString("br.cefetrj.sisgee.valida_utils.msg_valida_numerico");
//                    request.setAttribute("cpfPessoaMsg", cpfPessoaMsg);
//                    isValid = false;
//                }
//            } else {
//                cpfPessoaMsg = messages.getString("br.cefetrj.sisgee.form_termo_estagio_servlet.valor_invalido");
//                cpfPessoaMsg = ServletUtils.mensagemFormatada(cpfPessoaMsg, locale, tamanho);
//                request.setAttribute("cpfPessoaMsg", cpfPessoaMsg);
//                isValid = false;
//            }
            
            /**
             * Validação do nome da Pessoa Cadastro Pessoa Fisica usando métodos
             * da Classe ValidaUtils. Campo obrigatorio; Tamanho máximo de 100
             * caracteres;
             */
            String nomePessoaMsg = "";
            nomePessoaMsg = ValidaUtils.validaObrigatorio("nomePessoa", nomePessoa);
            if (nomePessoaMsg.trim().isEmpty()) {
                nomePessoaMsg = ValidaUtils.validaTamanho("nomePessoa", 100, nomePessoa);
                if (nomePessoaMsg.trim().isEmpty()) {
                    nomePessoaMsg = ValidaUtils.validaSomenteLetras("nomePessoa", nomePessoa);
                    if (nomePessoaMsg.trim().isEmpty()) {
                        request.setAttribute("nomePessoa", nomePessoa);
                    } else {
                        nomePessoaMsg = messages.getString("br.cefetrj.sisgee.form_termo_estagio_servlet.valor_invalido");
                        request.setAttribute("nomePessoaMsg", nomePessoaMsg);
                        isValid = false;
                    }

                } else {
                    nomePessoaMsg = messages.getString("br.cefetrj.sisgee.form_termo_estagio_servlet.valor_invalido");
                    request.setAttribute("nomePessoaMsg", nomePessoaMsg);
                    isValid = false;
                }

            } else {
                nomePessoaMsg = messages.getString("br.cefetrj.sisgee.form_termo_estagio_servlet.valor_invalido");
                request.setAttribute("nomePessoaMsg", nomePessoaMsg);
                isValid = false;

            }

            /**
             * Validação do email da Pessoa Cadastro Pessoa Fisica usando
             * métodos da Classe ValidaUtils. Campo opcional; Tamanho máximo de
             * 50 caracteres;
             */
            String emailPessoaMsg = "";
            emailPessoaMsg = ValidaUtils.validaObrigatorio("emailPessoa", emailPessoa);
            if (emailPessoaMsg.trim().isEmpty()) {
                emailPessoaMsg = ValidaUtils.validaTamanho("emailPessoa", 50, emailPessoa);
                if (emailPessoaMsg.trim().isEmpty()) {
                    emailPessoaMsg = ValidaUtils.validaEmail("emailPessoa", emailPessoa);
                    if (emailPessoaMsg.trim().isEmpty()) {
                        request.setAttribute("emailPessoa", emailPessoa);
                    } else {
                        emailPessoaMsg = messages.getString("br.cefetrj.sisgee.form_termo_estagio_servlet.valor_invalido");
                        request.setAttribute("emailPessoaMsg", emailPessoaMsg);
                        isValid = false;
                    }
                } else {
                    emailPessoaMsg = messages.getString("br.cefetrj.sisgee.form_termo_estagio_servlet.valor_invalido");
                    request.setAttribute("emailPessoaMsg", emailPessoaMsg);
                    isValid = false;
                }

            }

            /**
             * Validação do telefone da Pessoa Cadastro Pessoa Fisica usando
             * métodos da Classe ValidaUtils. Campo opcional; Tamanho máximo de
             * 11 caracteres;
             */
            String telefonePessoaMsg = "";
            telefonePessoaMsg = ValidaUtils.validaObrigatorio("telefonePessoa", telefonePessoa);
            if (telefonePessoaMsg.trim().isEmpty()) {
                telefonePessoaMsg = ValidaUtils.validaTamanho("telefonePessoa", 11, telefonePessoa);
                if (telefonePessoaMsg.trim().isEmpty()) {
                    telefonePessoa = telefonePessoa.replaceAll("[.|/|-]", "");
                    telefonePessoaMsg = ValidaUtils.validaInteger("telefonePessoa", telefonePessoa);
                    if (telefonePessoaMsg.trim().isEmpty()) {
                        telefonePessoaMsg = ValidaUtils.validaTelefone("telefonePessoa", telefonePessoa);
                        if (telefonePessoaMsg.trim().isEmpty()) {
                            request.setAttribute("telefonePessoa", telefonePessoa);
                        } else {
                            telefonePessoaMsg = messages.getString("br.cefetrj.sisgee.form_termo_estagio_servlet.valor_invalido");
                            request.setAttribute("telefonePessoaMsg", telefonePessoaMsg);
                            isValid = false;
                        }
                    } else {
                        telefonePessoaMsg = messages.getString("br.cefetrj.sisgee.form_termo_estagio_servlet.valor_invalido");
                        request.setAttribute("telefonePessoaMsg", telefonePessoaMsg);
                        isValid = false;

                    }
                } else {
                    telefonePessoaMsg = messages.getString("br.cefetrj.sisgee.form_termo_estagio_servlet.valor_invalido");
                    request.setAttribute("telefonePessoaMsg", telefonePessoaMsg);
                    isValid = false;
                }

            }

            /**
             * Validação da Data de Assinatura do Convenio da Pessoa usando os
             * métodos da Classe ValidaUtils Campo obrigatório
             */
            Date dataAssinaturaPessoa2 = null;
            String dataAssinaturaMsg = "";
            String campo = "Data de Assinatura";

            dataAssinaturaMsg = ValidaUtils.validaObrigatorio(campo, dataAssinaturaConvenioPessoa);
            if (dataAssinaturaMsg.trim().isEmpty()) {
                dataAssinaturaMsg = ValidaUtils.validaDate(campo, dataAssinaturaConvenioPessoa);
                if (dataAssinaturaMsg.trim().isEmpty()) {
                    dataAssinaturaMsg = ValidaUtils.validaTamanhoExato(campo, 10, dataAssinaturaConvenioPessoa);
                    if (dataAssinaturaMsg.trim().isEmpty()) {
                        try {
                            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
                            dataAssinaturaPessoa2 = format.parse(dataAssinaturaConvenioPessoa);
                            System.out.println("Data Pessoa Validada:" + dataAssinaturaPessoa2);
                            request.setAttribute("dataAssinaturaConvenioPessoa", dataAssinaturaPessoa2);
                        } catch (Exception e) {
                            //TODO trocar saída de console por Log
                            System.out.println("Data em formato incorreto, mesmo após validação na classe ValidaUtils");
                            isValid = false;
                        }
                    } else {
                        dataAssinaturaMsg = messages.getString("br.cefetrj.sisgee.form_termo_estagio_servlet.valor_invalido");
                        request.setAttribute("dataAssinaturaPessoaMsg", dataAssinaturaMsg);
                        isValid = false;
                        //TODO Fazer log

                    }

                } else {
                    dataAssinaturaMsg = messages.getString("br.cefetrj.sisgee.form_termo_estagio_servlet.valor_invalido");
                    request.setAttribute("dataAssinaturaPessoaMsg", dataAssinaturaMsg);
                    isValid = false;
                    //TODO Fazer log
                    System.out.println(dataAssinaturaMsg);
                }
            } else {
                dataAssinaturaMsg = messages.getString("br.cefetrj.sisgee.form_termo_estagio_servlet.valor_invalido");
                request.setAttribute("dataAssinaturaPessoaMsg", dataAssinaturaMsg);
                isValid = false;
                //TODO Fazer log
                System.out.println(dataAssinaturaMsg);
            }
            
            String convenioAnoPessoaMsg = "";
            convenioAnoPessoaMsg = ValidaUtils.validaObrigatorio("convenioAnoPessoa", convenioAnoPessoa);
            if (convenioAnoPessoaMsg.trim().isEmpty()) {
                convenioAnoPessoaMsg = ValidaUtils.validaTamanhoExato("convenioAnoPessoa", 4, convenioAnoPessoa);
                if (convenioAnoPessoaMsg.trim().isEmpty()) {
                    convenioAnoPessoaMsg = ValidaUtils.validaInteger("convenioAnoPessoa", convenioAnoPessoa);
                    if (convenioAnoPessoaMsg.trim().isEmpty()) {
                        request.setAttribute("convenioAnoPessoa", convenioAnoPessoa);
                    } else {
                        convenioAnoPessoaMsg = messages.getString("br.cefetrj.sisgee.form_termo_estagio_servlet.valor_invalido");
                        request.setAttribute("convenioAnoPessoaMsg", convenioAnoPessoaMsg);
                        isValid = false;
                        System.out.println(convenioAnoPessoaMsg);
                    } 
                }else {
                    convenioAnoPessoaMsg = messages.getString("br.cefetrj.sisgee.form_termo_estagio_servlet.valor_invalido");
                    request.setAttribute("convenioAnoPessoaMsg", convenioAnoPessoaMsg);
                    isValid = false;
                    System.out.println(convenioAnoPessoaMsg);
                    }
            }else{
                convenioAnoPessoaMsg = messages.getString("br.cefetrj.sisgee.form_termo_estagio_servlet.valor_invalido");
                request.setAttribute("convenioAnoPessoaMsg", convenioAnoPessoaMsg);
                isValid = false;
                System.out.println(convenioAnoPessoaMsg);
            }
            
            System.out.println("Nome Pessoa Msg" + nomePessoaMsg);
            
            System.out.println("Data Pessoa Msg" + dataAssinaturaMsg);
            System.out.println("Telefone Pessoa Msg" + telefonePessoaMsg);
            System.out.println("Nome Pessoa Msg" + convenioAnoPessoaMsg);
            
            

        }

        /**
         * Teste das variáveis booleanas após validação. Redirecionamento para a
         * inclusão ou devolver para o formulário com as mensagens.
         */
        
        System.out.println("CNPJ MSG FINAL: " + cnpjEmpresaMsg);
        System.out.println("EMPRESA MSG FINAL: " + EmpresaMsg);
        if (isValid) {
            request.getRequestDispatcher("/IncluirCadastroAlteradoEmpresaServlet").forward(request, response);

        } else {
            String msg = messages.getString("br.cefetrj.sisgee.valida_cadastro_empresa_servlet.msg_atencao");
            request.setAttribute("msg", msg);
            request.getRequestDispatcher("/form_alterar_convenio.jsp").forward(request, response);

        }

    }

}

