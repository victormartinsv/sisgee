
<!DOCTYPE html>
<html lang="en">
    <head>
        <%@include file="import_head.jspf"%>

        <title>
            <fmt:message key = "br.cefetrj.sisgee.form_empresa.msg_titulo"/>
        </title>

        <link rel="stylesheet" href="node_modules/bootstrap/dist/css/bootstrap.min.css" >
        <link rel="stylesheet" href="node_modules/bootstrap/dist/css/bootstrap.min.css" >
        <link rel="stylesheet" href="node_modules/bootstrap/dist/css/bootstrap.css" >

    </head>
    <body>
        <%@include file="import_navbar.jspf"%>

        <div class="container">
            <c:if test="${ not empty msg }">
                <div class="alert alert-warning" role="alert">
                    ${ msg }
                </div>
            </c:if>

            <p class="tituloForm ">
            <h5 class="offset-5"><fmt:message key="br.cefetrj.sisgee.form_empresa.msg_titulo_renovar" /></h5>		



            




                <fieldset class="form-group col-auto offset-1">

                    <form action="ValidaBuscarConvenioServlet" method="GET" >


                        <div class="form-row mb-3 mt-3 " >

                            <div class="form-inline form-group col-md-8 mt-2 offset-2" >
                                <label for="numeroConvenio" class="mr-1"><fmt:message key="br.cefetrj.sisgee.form_empresa.msg_numeroConvenio_renovar" /></label>
                                <input type="text" class="ml-5 form-control ${ not empty numeroConvenioMsg ? 'is-invalid': 'is-valid' }" id="numeroConvenio" name="numeroConvenio"  maxlength="6" value="">
                                <span class="input-group-btn"> 
                                    <button class="btn btn-primary  " type="submit" id="btnBuscarPeloNumero" ><i class="fas fa-search"></i> <fmt:message key = "br.cefetrj.sisgee.form_empresa.msg_buscar"/></button>
                                </span>
                                <c:if test="${ not empty numeroConvenioMsg }">
                                <div class="invalid-feedback ml-3">${ numeroConvenioMsg }</div>
                                </c:if>

                            </div>
                        </div>
                    </form>
                    <form action="ValidaBuscarConvenioServlet" method="GET" >

                        <div class="form-row mb-3 " >
                            <div class="form-inline col-md-8 mt-2 offset-2 mb-3" >

                                <label for="razaoSocial" class="mr-3"><fmt:message key="br.cefetrj.sisgee.form_empresa.msg_razaoSocial_renovar" /></label>
                                <input type="text" class="ml-5 form-control ${ not empty nomeMsg ? 'is-invalid': 'is-valid' }" id="razaoSocial" name="razaoSocial" maxlength="100"  value="">
                                <span class="input-group-btn"> 
                                    <button class="btn btn-primary" type="submit" id="btnBuscarPeloNome"  ><i class="fas fa-search"></i> <fmt:message key = "br.cefetrj.sisgee.form_empresa.msg_buscar"/></button>
                                </span>
                                <c:if test="${ not empty nomeMsg }">
                                <div class="invalid-feedback ml-3">${ nomeMsg }</div>
                                </c:if>

                            </div>


                        </div>

                    </form>
                    <div class="offset-5 mb-3 mt-5">               


                        <button type="button" class="btn btn-secondary" onclick="javascript:location.href = 'index.jsp'"><i class="far fa-times-circle"></i> <fmt:message key = "br.cefetrj.sisgee.form_empresa.msg_cancelar"/></button>
                    </div>

                    <c:if test="${ not empty filtro }">
                        <table id="myTable" class="table table-info table-bordered container table-hover table-striped " >
                            <thead>
                                <tr>


                                    <th scope="col"><fmt:message key="br.cefetrj.sisgee.resources.form.numeroConvenio" /></th>
                                    <th scope="col"><fmt:message key="br.cefetrj.sisgee.form_empresa.msg_razaoSocial_renovar" /></th>
                                    <th scope="col"><fmt:message key="br.cefetrj.sisgee.19" /></th>
                                    <th scope="col"><fmt:message key="br.cefetrj.sisgee.form_empresa.msg_titulo_renovar" /></th>
                                    <th scope="col"><fmt:message key="br.cefetrj.sisgee.form_empresa.msg_titulo_excluir" /></th>
                                    <th scope="col"><fmt:message key="br.cefetrj.sisgee.form_empresa.msg_titulo_alterar" /></th>
                                   
                                </tr>
                            </thead>
                            <c:forEach items="${ filtro}" var="b" >
                                <tr>

                                    <td>${not empty b.numeroConvenio ? b.numeroConvenio : null }</td>
                                    <td >${ not empty b.empresa ? b.empresa.razaoSocial: b.pessoa.nome } </td>
                                    <td>${ not empty b.empresa ? b.empresa.cnpjEmpresa : b.pessoa.cpf }</td>
                                    <td><a class="btn btn-sm btn-primary btn-block" href="RenovarConvenioServlet?convenio=${b.numeroConvenio}" ><fmt:message key="br.cefetrj.sisgee.form_empresa.msg_clique_renovar" /></td>
                                    <td><a class="btn btn-sm btn-primary btn-block" href="ExcluirConvenio?nomeEmpresa=${b.empresa.razaoSocial}"><fmt:message key="br.cefetrj.sisgee.form_empresa.msg_clique_excluir" /></td>
                                    <td><a class="btn btn-sm btn-primary btn-block" href="AlterarConvenioAlteradoServlet?convenio=${b.numeroConvenio}"><fmt:message key="br.cefetrj.sisgee.form_empresa.msg_clique_alterar" /></td>                                    

                                </tr>
                            </c:forEach>
                        </table>
                    </c:if>         
                </fieldset>

            

        </div>
        <%@include file="import_footer.jspf"%>
        <%@include file="import_finalbodyscripts.jspf"%>
        <%@include file="import_scripts.jspf"%>





        <%--

        <div class="modal fade myModal-lg" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-lg" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="myModalLabel"> Tabela de Conv�nios</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body"> 
                        <table id="myTable" class="table table-info table-bordered container table-hover table-striped " >
                            <thead>
                                <tr>


                                    <th scope="col">N�mero do Conv�nio</th>
                                    <th scope="col">Raz�o Social/Nome</th>
                                    <th scope="col">CNPJ/CPF</th>
                                    <th scope="col">Renovar</th>

                                </tr>
                            </thead>


                            <tr>

                                <td>${filtro.numeroConvenio}</td>
                                <td>${filtro.empresa.razaoSocial}${filtro.pessoa.nome}</td>
                                <td>${filtro.empresa.cnpjEmpresa} ${filtro.pessoa.cpf}</td>
                                <td ><center><button type="submit" class="ml-2 btn btn-primary" >Clique para Renovar</button></center></td>


                            </tr>
                        </table>

                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-primary" data-dismiss="modal">Fechar</button>
                    </div>
                </div>
            </div>
        </div>

        --%>




    </body>

</html>
