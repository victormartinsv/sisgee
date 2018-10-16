/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetrj.sisgee.view.usuario;

import br.cefetrj.sisgee.model.dao.GenericDAO;
import br.cefetrj.sisgee.model.dao.PersistenceManager;
import br.cefetrj.sisgee.model.entity.Pessoa;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author c18634659798
 */
@WebServlet(name = "ListarUsuarioServlet", urlPatterns = {"/ListarUsuarioServlet"})
public class ListarUsuarioServlet extends HttpServlet {

   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        GenericDAO<Pessoa> pessoaDao = PersistenceManager.createGenericDAO(Pessoa.class);
        List <Pessoa> listaUsuarios =  pessoaDao.buscarTodos();
            
        request.setAttribute("listaUsuarios", listaUsuarios);
        
        request.getRequestDispatcher("/listar_alterar_excluir_usuario.jsp").forward(request, response);
    
    }

    
}
