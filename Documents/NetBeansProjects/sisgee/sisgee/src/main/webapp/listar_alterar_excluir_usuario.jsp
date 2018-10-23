<!DOCTYPE html>
<html lang="en">
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
            <c:if test="${ not empty msg }">
                <div class="alert alert-warning" role="alert">
                    ${ msg }
                </div>
            </c:if>
             <p class="tituloForm">
            <h5>		
                <fmt:message key = "br.cefetrj.sisgee.resources.form.alterarUsuario"/>
            </h5>
            <form action=BuscaUsuarioServlet method="post">
                <fieldset class="form-group dadosAluno" >
                    
                    <div class="container">					
                        <legend class="col-form-legend col-lg"><fmt:message key = "br.cefetrj.sisgee.resources.form.dadosUsuario"/></legend>
                        <div class="form-row">
                            <div class="form-group col-md-4">
                                <label for="login">Login</label>
                                <div class="input-group">
                                    <input type="text" class="form-control is-valid" placeholder="Digite o Login" id="login" name="login" >
                                    <span class="input-group-btn"> 
                                        <button class="btn btn-primary" type="button" id="btnBuscarLogin"><fmt:message key = "br.cefetrj.sisgee.resources.form.buscar"/></button>
                                    </span>
                                    <input type="hidden" id="idUsuario" name="idUsuario" value="${ param.idUsuario }">
                                </div>	     
                            </div>
                            <div class="form-group col-md">
                                <label for="nome"><fmt:message key = "br.cefetrj.sisgee.resources.form.nome"/></label>
                                <input type="text" class="form-control" id="nome" name="nome" value="${ param.nome }" readonly>
                            </div>
                        </div>   
                        <a  class="btn btn-primary" href="ListarUsuarioServlet"><fmt:message key = "br.cefetrj.sisgee.resources.form.listarUsuarios"/></a>
                        <button  type="submit" class="btn btn-primary"><fmt:message key = "br.cefetrj.sisgee.resources.form.alterarDados"/></button>
                        <a id="btnExcluirUsuario" class="btn btn-primary" data-toggle="modal" data-target="#myModal"><fmt:message key = "br.cefetrj.sisgee.resources.form.excluirUsuario"/></a>
                        <button type="button" class="btn btn-secondary" onclick="javascript:location.href = 'index.jsp'" ><fmt:message key = "br.cefetrj.sisgee.resources.form.cancelar"/></button>
                    </div>				
                    
                </fieldset>
                
            </form>
            <c:if test="${not empty listaUsuarios}">     
                <div class="container">
                    <div class="table-responsive">
                        <table class="table table-info table-bordered container table-hover table-striped">
                            <tr>
                                <th width="40%"><fmt:message key = "br.cefetrj.sisgee.resources.form.nome"/></th>
                                <th width="30%">Login</th>
                                <th width="30%"><fmt:message key = "br.cefetrj.sisgee.cadastrar_usuario.msg_perfil"/></th>         
                            </tr>

                            <c:forEach items="${listaUsuarios}" var="u">
                                <tr>
                                    <td>${u.getNome()}</td>
                                    <td>${u.getCpf()}</td>
                                    <td>${u.getIdPessoa()}</td>
                                </tr>
                            </c:forEach>
                        </table>
                    </div>
                </div>
            </c:if>
                    
            <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                <div class="modal-dialog" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="myModalLabel"><fmt:message key = "br.cefetrj.sisgee.resources.form.excluirUsuario"/></h5>
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>
                        <div class="modal-body">
                            <form action="" method="post">
                                <input type="hidden" id="idAluno" name="idAluno" value="${ param.idAluno }">

                                        <div class="col-xs-1" align="center">
                                            <label for="dataRescisao"><fmt:message key = "br.cefetrj.sisgee.listar_alterar_excluir_usuario.msg_confirmarExclusao"/> </label><br><br><br>

                                        </div>					

                                    <button type="submit" class="btn btn-primary"> <fmt:message key = "br.cefetrj.sisgee.listar_alterar_excluir_usuario.msg_sim"/> </button>
                                    <button type="button" class="btn btn-primary" data-dismiss="modal"><fmt:message key = "br.cefetrj.sisgee.listar_alterar_excluir_usuario.msg_nao"/> </button>
                            </form>                                   
                        </div>
                    </div>
                </div>
            </div>
        </div>
            <%@include file="import_footer.jspf"%>
            <%@include file="import_finalbodyscripts.jspf"%>
            <%@include file="import_scripts.jspf"%>
    </body>
</html>
