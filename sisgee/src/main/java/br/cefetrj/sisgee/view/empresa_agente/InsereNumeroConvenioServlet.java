/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetrj.sisgee.view.empresa_agente;

import br.cefetrj.sisgee.control.ConvenioServices;
import br.cefetrj.sisgee.model.entity.Convenio;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/InsereNumeroConvenioServlet")
public class InsereNumeroConvenioServlet extends HttpServlet {

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    
    
    
    @Override
    public void service(ServletRequest req, ServletResponse res)
            throws ServletException, IOException {
        
                req.setAttribute("convenioNumero", gerarNumeroConvenio());        

                req.getRequestDispatcher("/form_empresa.jsp").forward(req, res);        
    }

//    @Override
//    protected void s(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        request.setAttribute("convenioNumero", gerarNumeroConvenio());
//        
//        request.getRequestDispatcher("/form_empresa.jsp").forward(request, response);
//    }

    /**
     * Metodo que gera um numero de convenio
     * @return uma string
     */
    public static String gerarNumeroConvenio(){
        Convenio comparador = new Convenio();
        List<Convenio> x = ConvenioServices.listarConvenios();
        Collections.sort(x, comparador);
        System.out.println(x.size());
        int numConv = Integer.parseInt(x.get(0).getNumero()) + 1;
        System.out.println("Primeiro da lista: " + String.valueOf(numConv));
        String a = String.valueOf(numConv);
        return a;
    }
}
