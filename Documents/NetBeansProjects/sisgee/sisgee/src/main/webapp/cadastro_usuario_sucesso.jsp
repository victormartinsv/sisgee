
<!DOCTYPE html>
<html >

    <head>

        <%@include file="import_head.jspf"%>

        <title>
            Cadastro de Usuário
        </title>

    </head>

    <body>
        <%@include file="import_navbar.jspf"%>

                <fieldset class="form-group col-10 offset-1 mt-5 mb-5">
                    <div class="alert alert-sucess offset-2 mt-5" style="font-family: Tajawal, Helvetica; color: #28a745; font-size: 30px; font-weight: bolder" role="alert">
                       Cadastrado de Usuário concluído com sucesso
                    </div>
                        <div class="offset-6 mb-5" >
                            <button type="button" class="btn btn-primary " onclick="javascript:location.href = 'index.jsp'">Ok</button>
                        </div>
                </fieldset>


        <%@include file="import_footer.jspf"%>
        <%@include file="import_finalbodyscripts.jspf"%>

    </body>
</html>
