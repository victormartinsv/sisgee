<%-- 
    Document   : listar_alterar_excluir_usuario
    Created on : 04/10/2018, 14:51:27
    Author     : c18634659798
--%>

<!DOCTYPE html>
<html>
    <head>
         <%@include file="import_head.jspf"%>

        <title>
            Usuários
        </title>
        <style>

            table{
                white-space: nowrap ;
            }
        </style>
    </head>
    <body>
         <%@include file="import_navbar.jspf"%>
         <div class="container">
             <p class="tituloForm">
            <h5>		
                Alterar Usuários
            </h5>
            <form >
                <fieldset class="form-group dadosAluno" >
                    
                    <div class="container">					
                        <legend class="col-form-legend col-lg">Dados do Usuário</legend>
                        <div class="form-row">
                            <div class="form-group col-md-4">
                                <label for="login">Login</label>
                                <div class="input-group">
                                    <input type="text"  class="form-control is-valid" placeholder="Digite o Login" id="login" name="login" >
                                    <span class="input-group-btn"> 
                                        <button class="btn btn-primary" type="button" id="btnBuscarMatricula"><fmt:message key = "br.cefetrj.sisgee.resources.form.buscar"/></button>
                                    </span>
                                </div>	
                                    
                            </div>
                            <div class="form-group col-md">
                                <label for="nome"><fmt:message key = "br.cefetrj.sisgee.resources.form.nome"/></label>
                                <input type="text" class="form-control" id="nome" name="nome" value="${ param.nome }" readonly>
                            </div>
                        </div>
                            
                        <button  type="button" class="btn btn-primary" >Listar Usuários</button>
                         <button  type="submit" class="btn btn-primary">Alterar Dados</button>
                        <a  class="btn btn-primary" data-toggle="modal" data-target="#myModal">Excluir Usuário</a>
                        <button type="button" class="btn btn-secondary" onclick="javascript:location.href = 'index.jsp'" ><fmt:message key = "br.cefetrj.sisgee.resources.form.cancelar"/></button>
                    </div>				
                    
                </fieldset>
                
            </form>
        </div>
    </body>
</html>
