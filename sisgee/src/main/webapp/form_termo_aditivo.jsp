<!DOCTYPE html>
<html lang="en">
    <head>

        <%@include file="import_head.jspf"%>

        <title>
            <fmt:message key = "br.cefetrj.sisgee.resources.form.registroTermoAditivo"/>
        </title>

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
                <fmt:message key = "br.cefetrj.sisgee.resources.form.registroTermoAditivo"/>
            </h5>		

            <form action=BuscaTermoAditivoServlet method="post" name="dadosAluno">
                <fieldset class="form-group dadosAluno" >
                    <%@include file="import_busca_aluno.jspf"%>
                </fieldset>
            </form>
                
            <div class="container">
                <button id="btnListarAditivo" type="button" onclick="document.forms['dadosAluno'].submit()" class="btn btn-secondary" disabled="true"><fmt:message key = "br.cefetrj.sisgee.resources.form.listarAditivos"/></button>
                <button type="button" id="btnNovoAditivo" class="btn btn-secondary" disabled="true" onclick="abrirModalAditivo()"><fmt:message key = "br.cefetrj.sisgee.resources.form.novo_aditivo"/></button>
                <button type="button" id="btnRescisao" class="btn btn-secondary" disabled="true" onclick=""><fmt:message key = "br.cefetrj.sisgee.resources.form.registrar_reciscisao"/></button>
                <button type="button" class="btn btn-secondary" onclick="location.href = 'index.jsp'"><fmt:message key = "br.cefetrj.sisgee.resources.form.cancelar"/></button>
            </div>

            <div class="container" id="tabela" style="display: ${not empty termoAtivo ? 'block' : 'none' }">
                <table class = "table table">
                    <thead>		
                        <tr>
                            <th><fmt:message key = "br.cefetrj.sisgee.resources.form.visualizar"/></th>
                            <th><fmt:message key = "br.cefetrj.sisgee.resources.form.dataRegistro"/></th>
                            <th><fmt:message key = "br.cefetrj.sisgee.resources.form.cnpj"/></th>
                            <th><fmt:message key = "br.cefetrj.sisgee.resources.form.razaoSocial"/></th>
                        </tr>
                    </thead>			
                    <tbody>
                        <c:url value = "/VerTermoServlet" var = "verTermoUrl" scope = "page">
                            <c:param name="idTermoAtivo" value = "${termoAtivo.idTermoEstagio}"/>  
                        </c:url>
                        <tr>
                            <td><a href = "${verTermoUrl}" ><fmt:message key = "br.cefetrj.sisgee.resources.form.termo"/></a></td>
                            <td>
                                <fmt:formatDate type="date" dateStyle="short" value="${ termoAtivo.dataInicioTermoEstagio }"/></td>	
                            <td> ${ termoAtivo.convenio.empresa.cnpjEmpresaFormatado }</td>
                            <td> ${ termoAtivo.convenio.empresa.nomeEmpresa }</td>						
                        </tr>
                        <c:forEach items = "${termosAditivos}" var = "termoAditivo">
                            <c:url value = "/VerTermoAditivoServlet" var = "verTermoAditivoUrl" scope = "page">
                                <c:param name="idTermoAditivo" value = "${termoAditivo.idTermoAditivo}"/>  
                            </c:url>
                            <tr>
                                <td><a href = "${verTermoAditivoUrl}" ><fmt:message key = "br.cefetrj.sisgee.resources.form.aditivo"/></a></td>
                                <td>
                                    <fmt:formatDate type="date" dateStyle="short" value="${ termoAditivo.termoEstagio.dataInicioTermoEstagio }"/>
                                </td>	
                                <td> ${ termoAditivo.termoEstagio.convenio.empresa.cnpjEmpresa }</td>
                                <td> ${ termoAditivo.termoEstagio.convenio.empresa.nomeEmpresa }</td>						
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>

            <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                <div class="modal-dialog" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="myModalLabel"></h5>
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>
                        <div class="modal-body"></div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-primary" data-dismiss="modal"><fmt:message key = "br.cefetrj.sisgee.resources.form.fechar"/></button>
                        </div>
                    </div>
                </div>
            </div>

            <div class="modal fade" id="novoAditivoModal" tabindex="-1" role="dialog" aria-labelledby="novoAditivoModalLabel" aria-hidden="true">
                <div class="modal-dialog" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="novoAditivoModalLabel"><fmt:message key = "br.cefetrj.sisgee.resources.form.selecione_alteracoes_aditivo"/>:</h5>
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>
                        <div class="modal-body">
                            <form class="container" style="margin-top: 0px" name="novoAditivoForm" action="TermoAditivoServlet" method="post" >
                                <table border="0">
                                    <tr>
                                        <td>
                                            <div class="form-check form-check-inline">
                                                <label class="form-check-label">
                                                    <input class="form-check-input" type="checkbox" id="vigencia" name="vigencia"  value="sim"><fmt:message key = "br.cefetrj.sisgee.resources.form.vigenciaEstagio"/>
                                                </label>
                                            </div>
                                        </td>
                                        <td>
                                            <div class="form-check form-check-inline">
                                                <label class="form-check-label">
                                                    <input class="form-check-input" type="checkbox" id="enderecoTermoEstagio" name="endereco" value="sim"><fmt:message key = "br.cefetrj.sisgee.resources.form.endereco"/>
                                                </label>
                                            </div>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            <div class="form-check form-check-inline">
                                                <label class="form-check-label">
                                                    <input class="form-check-input" type="checkbox" id="cargaHorariaTermoEstagio" name="cargaHoraria" value="sim"><fmt:message key = "br.cefetrj.sisgee.resources.form.cargaHorariaAluno"/>
                                                </label>
                                            </div>
                                        </td>
                                        <td>
                                            <div class="form-check form-check-inline">
                                                <label class="form-check-label">
                                                    <input class="form-check-input" type="checkbox" id="professorOrientador" name="professor" value="sim"><fmt:message key = "br.cefetrj.sisgee.resources.form.professorOrientador"/>
                                                </label>
                                            </div>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            <div class="form-check form-check-inline">
                                                <label class="form-check-label">
                                                    <input class="form-check-input" type="checkbox" id="valorBolsa" name="valor" value="sim"><fmt:message key = "br.cefetrj.sisgee.resources.form.valorBolsaEstagio"/>
                                                </label>
                                            </div>
                                        </td>
                                        <td></td>
                                    </tr>
                                </table>
                                <input type="hidden" id="idAlunoAdt" name="idAlunoAdt" value="" />
                            </form>
                        </div>
                        <div class="modal-footer">
                            <button type="button" id="btnAbrirNovoAditivo" class="btn btn-primary" onclick="document.forms['novoAditivoForm'].submit();"><fmt:message key = "br.cefetrj.sisgee.resources.form.preencher_aditivo"/></button>
                            <button type="button" class="btn btn-secondary" data-dismiss="modal"><fmt:message key = "br.cefetrj.sisgee.resources.form.fechar"/></button>
                        </div>
                    </div>
                </div>
            </div>

        </div>
        <%@include file="import_footer.jspf"%>
        <%@include file="import_finalbodyscripts.jspf"%>
        <script type="text/javascript">
            function showMessage(title, msg) {
                $("#myModalLabel").html(title);
                $(".modal-body").html(msg);
                $('#myModal').modal('show');
            }
            function hablitarButoes() {
                $("#btnListarAditivo").prop("disabled", false);
                $("#btnListarAditivo").removeClass("btn-secondary");
                $("#btnListarAditivo").addClass("btn-primary");

                $("#btnNovoAditivo").prop("disabled", false);
                $("#btnNovoAditivo").removeClass("btn-secondary");
                $("#btnNovoAditivo").addClass("btn-primary");
                
                $("#btnRescisao").prop("disabled", false);
                $("#btnRescisao").removeClass("btn-secondary");
                $("#btnRescisao").addClass("btn-primary");
            }
            function desablitarButoes() {
                $("#btnListarAditivo").prop("disabled", true);
                $("#btnListarAditivo").removeClass("btn-primary");
                $("#btnListarAditivo").addClass("btn-secondary");

                $("#btnNovoAditivo").prop("disabled", true);
                $("#btnNovoAditivo").removeClass("btn-primary");
                $("#btnNovoAditivo").addClass("btn-secondary");
                
                $("#btnRescisao").prop("disabled", true);
                $("#btnRescisao").removeClass("btn-primary");
                $("#btnRescisao").addClass("btn-secondary");
                
            }
            function esconderTabela(){
                $("#tabela").css("display", "none");
            }
            var buscarAlunoCallback = function myCallback(json) {
                if (json != null) {
                    if (json.idAluno != null && json.idAluno != "") {
                        if (json.idTermoEstagioAtivo != null && json.idTermoEstagioAtivo != "") {
                            //tem termo de estágio, ativa os botões
                            hablitarButoes();
                        } else {
                            desablitarButoes();
                            esconderTabela();
                            titulo = '<fmt:message key = "br.cefetrj.sisgee.resources.form.termo_nao_encontrado_titulo"/>';
                            msg = '<fmt:message key = "br.cefetrj.sisgee.resources.form.termo_nao_encontrado_msg"/>';
                            //não tem termo de estágio
                            showMessage(titulo, msg);
                        }
                    } else {
                        desablitarButoes();
                        titulo = '<fmt:message key = "br.cefetrj.sisgee.resources.form.aluno_nao_encontrado_titulo"/>';
                        msg = '<fmt:message key = "br.cefetrj.sisgee.resources.form.aluno_nao_encontrado_msg"/>';
                        showMessage(titulo, msg);
                    }
                }
            }

            function abrirModalAditivo() {
                $('#novoAditivoModal').modal('show');
            }
        </script>
        <%@include file="import_scripts.jspf"%>
        <script type="text/javascript">

            $(document).ready(function () {
                $(".form-check-input").change(function () {
                    $('#idAlunoAdt').val($("#idAluno").val());
                });
                
                <c:if test="${not empty termoAtivo.idTermoEstagio}">
                    hablitarButoes();
                </c:if>
                
                
            });

        </script>

    </body>
</html>
