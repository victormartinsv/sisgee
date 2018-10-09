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
@WebServlet(name = "UsuarioValidaServlet", urlPatterns = {"/UsuarioValidaServlet"})
public class UsuarioValidaServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        Locale locale = ServletUtils.getLocale(request);
        ResourceBundle messages = ResourceBundle.getBundle("Messages", locale);
        
        String nomeUsuario= request.getParameter("nomeUsuario");
        String loginUsuario= request.getParameter("loginUsuario");
        String senhaUsuario= request.getParameter("senhaUsuario");
        String perfilUsuario= request.getParameter("perfilUsuario");
        
        
        boolean isValid = true;
        
        String nomeUsuarioMsg= "";
        nomeUsuarioMsg = ValidaUtils.validaObrigatorio("nomeUsuario", nomeUsuario);
        if(nomeUsuarioMsg.trim().isEmpty()){
            nomeUsuarioMsg=ValidaUtils.validaTamanho("nomeUsuario", 100, nomeUsuario);
            if(nomeUsuarioMsg.trim().isEmpty()){
                request.setAttribute("nomeUsuario", nomeUsuario);
            }
            else{
                nomeUsuarioMsg = messages.getString(nomeUsuarioMsg);
                   // nomeEmpresaMsg = ServletUtils.mensagemFormatada(nomeEmpresaMsg, locale, tamanho);
                request.setAttribute("nomeUsuarioMsg", nomeUsuarioMsg);
                isValid = false;
            }
        }else{
            nomeUsuarioMsg = messages.getString(nomeUsuarioMsg);
            request.setAttribute("nomeUsuarioMsg", nomeUsuarioMsg);
            isValid = false;
            
            System.out.println(nomeUsuarioMsg);
        }
        
        
        String loginUsuarioMsg= "";
        loginUsuarioMsg = ValidaUtils.validaObrigatorio("loginUsuario", loginUsuario);
        if(loginUsuarioMsg.trim().isEmpty()){
            loginUsuarioMsg=ValidaUtils.validaTamanho("loginUsuario", 15, loginUsuario);
            if(loginUsuarioMsg.trim().isEmpty()){
                request.setAttribute("loginUsuario", loginUsuario);
            }
            else{
                
                loginUsuarioMsg = messages.getString(loginUsuarioMsg);
                loginUsuarioMsg = ServletUtils.mensagemFormatada(loginUsuarioMsg, locale, 15);
                request.setAttribute("loginUsuarioMsg", loginUsuarioMsg);
                isValid = false;
            }
        }else{
            loginUsuarioMsg = messages.getString(loginUsuarioMsg);
            request.setAttribute("loginUsuarioMsg", loginUsuarioMsg);
            isValid = false;
            
            System.out.println(loginUsuarioMsg);
        }
        
        
        String senhaUsuarioMsg= "";
        senhaUsuarioMsg = ValidaUtils.validaObrigatorio("senhaUsuario", senhaUsuario);
        if(senhaUsuarioMsg.trim().isEmpty()){
            senhaUsuarioMsg=ValidaUtils.validaTamanho("senhaUsuario", 15, senhaUsuario);
            if(senhaUsuarioMsg.trim().isEmpty()){
                request.setAttribute("senhaUsuario", senhaUsuario);
            }
            else{
                
                senhaUsuarioMsg = messages.getString(senhaUsuarioMsg);
                senhaUsuarioMsg = ServletUtils.mensagemFormatada(senhaUsuarioMsg, locale, 15);
                request.setAttribute("senhaUsuarioMsg", senhaUsuarioMsg);
                isValid = false;
            }
        }else{
            senhaUsuarioMsg = messages.getString(senhaUsuarioMsg);
            request.setAttribute("senhaUsuarioMsg", senhaUsuarioMsg);
            isValid = false;
            
            System.out.println(senhaUsuarioMsg);
        }
        
        request.setAttribute("perfilUsuario", perfilUsuario);
        
        if( isValid){
            //form válido
            request.getRequestDispatcher("/cadastro_usuario_sucesso.jsp").forward(request, response);
            
        }else{
            //form inválido
            String msg = messages.getString("br.cefetrj.sisgee.valida_cadastro_empresa_servlet.msg_atencao");
            
            request.setAttribute("msg", msg);
            request.getRequestDispatcher("/cadastrar_usuario.jsp").forward(request, response);
        }
    }

    

}
