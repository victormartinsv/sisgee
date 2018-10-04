<%-- 
    Document   : cadastrar_usuario
    Created on : 04/10/2018, 13:30:52
    Author     : c18634659798
--%>
<!DOCTYPE html>
<html>
    <head>
        <%@include file="import_head.jspf"%>
        <title> Cadastrar Usuário</title>
    </head>
    <body style="font-family: 'Roboto Slab', Helvetica">
        <%@include file="import_navbar.jspf"%>
        <div class="container"> 
            <p class="tituloForm">
            <h5 class="offset-5">Cadastro do Usuário</h5>	
            <form id="meuForm"> 
                <fieldset class="form-group ">
                    
                    <div class="form-group col-md-6"> 
                        <label for="nomeUsuario"><br>Nome</label>
                        <input type="text" class="form-control is-valid " id="nomeUsuario" name="nomeUsuario" value="">
                    </div>
                    
                    <div class="form-group col-md-6"> 
                        <label for="nomeUsuario">Login</label>
                        <input type="text" class="form-control is-valid " id="nomeUsuario" name="nomeUsuario" value="">
                    </div>
                    
                    <div class="form-group col-md-6"> 
                        <label for="nomeUsuario">Senha</label>
                        <input type="text" class="form-control is-valid " id="nomeUsuario" name="nomeUsuario" value="">
                    </div>
                    
                </fieldset>
                
                <button type="submit" class="btn btn-primary" ><i class="far fa-save"></i> <fmt:message key = "br.cefetrj.sisgee.form_empresa.msg_salvar"/></button>
                <button type="button" class="btn btn-secondary" onclick="javascript:location.href = 'index.jsp'"><i class="far fa-times-circle"></i> <fmt:message key = "br.cefetrj.sisgee.form_empresa.msg_cancelar"/></button>

            </form>
        </div>
        <%@include file="import_footer.jspf"%>
        <%@include file="import_finalbodyscripts.jspf"%>
        <%@include file="import_scripts.jspf"%>
    </body>
    
</html>
