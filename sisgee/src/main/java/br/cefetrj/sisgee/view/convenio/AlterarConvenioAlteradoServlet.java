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

        int pos = request.getParameter("convenio").indexOf("/");
        //Substring iniciando em 0 até posição do caracter especial
        
        String numeroConvenio = request.getParameter("convenio").substring(0,pos);
        
        
        Convenio convenio = ConvenioServices.buscarConvenioByNumeroConvenio(numeroConvenio);
        String dataAssinaturaEmpresa;

              
        if(convenio.getEmpresa()!=null){
            request.setAttribute("isEmpresa", "sim");
            if(convenio.getEmpresa().isAgenteIntegracao()){
                request.setAttribute("simAgenteIntegracao", "sim");
            }else{
                request.setAttribute("naoAgenteIntegracao", "sim");
            }
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
               
        }else{
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
            
        }
        request.getSession().setAttribute("numero", numeroConvenio);
        
        request.setAttribute("convenioRenovar", convenio);
  
        
        
        
        request.getRequestDispatcher("form_alterar_convenio.jsp").forward(request, response);
        
    }

}
