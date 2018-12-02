/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetrj.sisgee.view.convenio;

import br.cefetrj.sisgee.control.ConvenioServices;
import br.cefetrj.sisgee.model.entity.Convenio;
import br.cefetrj.sisgee.view.utils.ServletUtils;
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
import org.apache.log4j.Logger;

/**
 * Servlet para alterar dados do novo convenio ao renovar
 *
 * @author Lucas Lima
 */
@WebServlet("/AlterarConvenioAlteradoServlet")
public class AlterarConvenioAlteradoServlet extends HttpServlet {
    /**
     * 
     * @param requestuest um objeto HttpServletRequest que contém a solicitação feita pelo cliente do servlet.
     * @param response um objeto HttpServletResponse que contém a resposta que o servlet envia para o cliente
     * @throws ServletException se o pedido do service não puder ser tratado
     * @throws IOException se um erro de entrada ou saída for detectado quando o servlet manipula o pedido 
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("ACESSOU O AlterarConvenioAlteradoServlet");
        int pos = request.getParameter("convenio").indexOf("/");
        //Substring iniciando em 0 até posição do caracter especial
        
        String numeroConvenio = request.getParameter("convenio").substring(0,pos);
        
        
        Convenio convenio = ConvenioServices.buscarConvenioByNumeroConvenio(numeroConvenio);
        int idConvenio = convenio.getIdConvenio();
        int idPessoa = 0;
        int idEmpresa = 0;
        
        request.setAttribute("idConvenio", idConvenio);
        System.out.println("ID do Convenio --> "+ idConvenio);
        String dataAssinaturaEmpresa;
              
        if(convenio.getEmpresa()!=null){
            request.setAttribute("isEmpresa", "sim");
            if(convenio.getEmpresa().isAgenteIntegracao()){
                request.setAttribute("simAgenteIntegracao", "sim");
                System.out.println("Agente Integracao: Sim");
            }else{
                request.setAttribute("naoAgenteIntegracao", "sim");
                System.out.println("Agente Integracao: Nao");
            }
            //Recupera dados de um convenio que é Pessoa Fisica
            idEmpresa = convenio.getEmpresa().getIdEmpresa();
            System.out.println("ID da Empresa --> "+ idEmpresa);
            request.setAttribute("idEmpresa", idEmpresa);
            request.setAttribute("convenioNumero", numeroConvenio);
            request.setAttribute("cnpj", convenio.getEmpresa().getCnpjEmpresa());
            request.setAttribute("razao", convenio.getEmpresa().getRazaoSocial());
            request.setAttribute("cnpjInicial", convenio.getEmpresa().getCnpjEmpresa());
            request.setAttribute("nomeEmpresaInicial", convenio.getEmpresa().getRazaoSocial());
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            dataAssinaturaEmpresa = format.format(convenio.getDataAssinatura());
            request.setAttribute("dataAssinaturaConvenioEmpresa", dataAssinaturaEmpresa);
            request.setAttribute("emailEmpresa", convenio.getEmpresa().getEmailEmpresa());
            request.setAttribute("telefoneEmpresa", convenio.getEmpresa().getTelefoneEmpresa());
            request.setAttribute("contatoEmpresa", convenio.getEmpresa().getContatoEmpresa());
            request.setAttribute("convenioAnoEmpresa", convenio.getAno());
            System.out.println("idEmpresa: " + idEmpresa);
            System.out.println("convenioNumero: " + numeroConvenio);
            System.out.println("cnpj: " + convenio.getEmpresa().getCnpjEmpresa());
            System.out.println("razao: " + convenio.getEmpresa().getRazaoSocial());
            System.out.println("cnpjInicial: " + convenio.getEmpresa().getCnpjEmpresa());
            System.out.println("nomeEmpresaInicial: " + convenio.getEmpresa().getRazaoSocial());
            System.out.println("dataAssinaturaConvenioEmpresa: " + dataAssinaturaEmpresa);
            System.out.println("emailEmpresa: " + convenio.getEmpresa().getEmailEmpresa());
            System.out.println("telefoneEmpresa: " + convenio.getEmpresa().getTelefoneEmpresa());
            System.out.println("contatoEmpresa: " + convenio.getEmpresa().getContatoEmpresa());
            System.out.println("convenioAnoEmpresa: " + convenio.getAno());
               
        }else{
            //Recupera dados de um convenio que é Pessoa Fisica
            idPessoa = convenio.getPessoa().getIdPessoa();
            System.out.println("ID da Empresa --> "+ idPessoa);
            request.setAttribute("idPessoa", idPessoa);
            request.setAttribute("convenioNumero", numeroConvenio);
            request.setAttribute("isPessoa", "sim");
            request.setAttribute("cpf", convenio.getPessoa().getCpf());
            request.setAttribute("nome", convenio.getPessoa().getNome());
            request.setAttribute("cpfInicial", convenio.getPessoa().getCpf());
            request.setAttribute("nomePessoaInicial", convenio.getPessoa().getNome());
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            dataAssinaturaEmpresa = format.format(convenio.getDataAssinatura());
            request.setAttribute("dataAssinaturaConvenioPessoa", dataAssinaturaEmpresa);
            request.setAttribute("emailPessoa", convenio.getPessoa().getEmail());
            request.setAttribute("telefonePessoa", convenio.getPessoa().getTelefone());
            request.setAttribute("convenioAnoPessoa", convenio.getAno());
            System.out.println("idPessoa: " + idPessoa);
            System.out.println("convenioNumero: " + numeroConvenio);
            System.out.println("isPessoa: " + "sim");
            System.out.println("cpf: " + convenio.getPessoa().getCpf());
            System.out.println("nome: " + convenio.getPessoa().getNome());
            System.out.println("cpfInicial: " + convenio.getPessoa().getCpf());
            System.out.println("nomePessoaInicial: " + convenio.getPessoa().getNome());
            System.out.println("dataAssinaturaConvenioPessoa: " + dataAssinaturaEmpresa);
            System.out.println("emailPessoa: " + convenio.getPessoa().getEmail());
            System.out.println("telefonePessoa: " + convenio.getPessoa().getTelefone());
            System.out.println("convenioAnoPessoa: " + convenio.getAno());
        }
        request.getSession().setAttribute("numero", numeroConvenio);
        
        request.setAttribute("convenioRenovar", convenio);
  
        
        
        
        request.getRequestDispatcher("form_alterar_convenio.jsp").forward(request, response);
        
    }

}
