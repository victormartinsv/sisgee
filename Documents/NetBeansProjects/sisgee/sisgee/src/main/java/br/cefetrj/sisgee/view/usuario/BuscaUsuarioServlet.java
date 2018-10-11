/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetrj.sisgee.view.usuario;

import br.cefetrj.sisgee.view.utils.ServletUtils;
import br.cefetrj.sisgee.view.utils.ValidaUtils;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Locale;
import java.util.ResourceBundle;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author c18634659798
 */
@WebServlet(name = "BuscaUsuarioServlet", urlPatterns = {"/BuscaUsuarioServlet"})
public class BuscaUsuarioServlet extends HttpServlet {

   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Locale locale = ServletUtils.getLocale(request);
        ResourceBundle messages = ResourceBundle.getBundle("Messages", locale);
        
        String msg = null;
	String idUsuario = request.getParameter("idUsuario");
        String login = request.getParameter("loginUsuario");
        
        Integer id = null;
        System.out.println("Aqui >>> "+idUsuario);
        if(Integer.parseInt(idUsuario)!=-1){
            msg = ValidaUtils.validaObrigatorio("idUsuario", idUsuario);		
            if(msg.trim().isEmpty()){
                msg = ValidaUtils.validaInteger("idUsuario", idUsuario);
                if(msg.trim().isEmpty()){
                    id = Integer.parseInt(idUsuario);					
                }else{
                    msg = messages.getString(msg);
                }
            }else{
                msg = messages.getString(msg);
            }

            //Chamar métodos de busca 
            request.setAttribute("msg",msg);
        //    request.setAttribute("nomeUsuario",nomeUsuario);
        }
        request.getRequestDispatcher("/listar_alterar_excluir_usuario.jsp").forward(request, response);
    }

}
