<!DOCTYPE html>
<html lang="en">
    <head>
        <%@include file="import_head.jspf"%>

        <style type="text/css">

            div.container {
                margin-bottom: 2em;
            }
            form {
                margin-top: 50px;
            }
            fieldset.form-group {
                border:1px solid #999999;
            }
            fieldset legend.col-form-legend {
                font-weight: bold;
            }
            div.form-row {
                padding: 0px 15px;
            }

        </style>

        <title><fmt:message key = "br.cefetrj.sisgee.resources.form.registroTermoAditivo"/></title>
    </head>
    <body>
        <%@include file="import_navbar.jspf"%>	


        <div class="container">
            <c:if test="${ not empty msg and empty msg3 }">
                <div class="alert alert-warning" role="alert">
                    ${ msg }
                </div>
            </c:if>
            <c:if test="${ not empty msg2 }">
                <div class="alert alert-warning" role="alert">
                    ${ msg2 }
                </div>
            </c:if>
            <c:if test="${ not empty msg3 }">
                <div class="alert alert-warning" role="alert">
                    ${ msg3 }
                </div>
            </c:if>

            <p class="tituloForm">

            <h5><fmt:message key = "br.cefetrj.sisgee.resources.form.alterarTermoEstagio"/></h5>		
        </p>
        <form action="FormaAlteraTermoEstagioServlet" method="post">
        <%-- ALTERA��O VICTOR--%>
            <%--<form action=BuscaTermoAditivoServlet method="post">

                <fieldset class="form-group dadosAluno" >
                    <%@include file="import_busca_aluno_alterar.jspf"%>
                    
				
                <input type="hidden" name="termoAditivo" id="termoAditivo" value="${ param.termoAditivo }">
                </fieldset>
                --%>
            <%--</form>--%>
            <input type="hidden" id="idTermo" name="idTermo" value="${ idTermo }">    
            <legend class="col-form-legend col-lg"><fmt:message key = "br.cefetrj.sisgee.resources.form.dadosAluno"/></legend>
                <div class="form-row">
                    <div class="form-group col-md-4">
                        <label for="matricula"><fmt:message key = "br.cefetrj.sisgee.resources.form.matricula"/></label>
                        <div class="input-group">
                            <input type="hidden" id="idAluno" name="idAluno" value="${ alId }">
                            <input type="hidden" id="tipoAluno" name="tipoAluno" value="${ param.tipoAluno }">
                            ${param.idAluno>-1?'<input type="hidden" id="idAluno" name="idAluno" value="${ alId }">':''}
                            <input type="text" required="required" minlength="10" pattern="[1-9]{7}[a-z\s]{3,6}+$"  maxlength="13"  class="form-control ${ not empty idAlunoMsg ? 'is-invalid': 'is-valid' }" placeholder="<fmt:message key = "br.cefetrj.sisgee.import_busca_aluno.placeholder_matricula"/>" id="matricula" name="matricula" value="${ alMatricula }">
                            <span class="input-group-btn"> 
                                <button class="btn btn-primary" type="button" id="btnBuscarMatricula"><fmt:message key = "br.cefetrj.sisgee.resources.form.buscar"/></button>
                            </span>
                            <c:if test="${ not empty idAlunoMsg }">
                                <div class="invalid-feedback">${ idAlunoMsg }</div>
                            </c:if>

                        </div>	


                    </div>
                    <div class="form-group col-md">
                        <label for="nome"><fmt:message key = "br.cefetrj.sisgee.resources.form.nome"/></label>
                        <input type="text" class="form-control" id="nome" name="nome" value="${ alNome }" readonly>
                    </div>
                </div>
                <div class="form-row">
                    <div class="form-group col-md-6">
                        <label for="nomeCurso"><fmt:message key = "br.cefetrj.sisgee.resources.form.curso"/></label>
                        <input type="text" class="form-control" id="nomeCurso"  name="nomeCurso" value="${ alCurso }" readonly>
                    </div>
                    <div class="form-group col-md-6">
                        <label for="nomeCampus"><fmt:message key = "br.cefetrj.sisgee.resources.form.unidade"/></label>
                        <input type="text" class="form-control" id="nomeCampus"  name="nomeCampus" value="${ alCampus }" readonly>
                    </div>
                </div>
                <fieldset class="form-group" >
                    <legend class="col-form-legend col-lg"><fmt:message key = "br.cefetrj.sisgee.resources.form.dadosEmpresaConveniada"/></legend>
                    <div class="form-group col-md-15">
                        <!-- AQUI VEM O NOME E NUMERO DO CONV�NIO-->
                      <div class="form-row">  
                            <div class="form-group col-md-5">
                                <label for="numeroConvenio"><fmt:message key = "br.cefetrj.sisgee.resources.form.numeroConvenio"/></label>
                                    <div class="input-group">
                                        <input type="text" class="form-control" id="numeroConvenio" name="numeroConvenio" maxlength="10" value="${ cvNumero }" >
                                        <span class="input-group-btn">
                                        <button class="btn btn-primary" type="button" id="btnBuscarConvenio"><fmt:message key = "br.cefetrj.sisgee.resources.form.buscar"/></button>
                                        </span>    
                                        <c:if test="${ not empty numeroConvenioMsg }">
                                            <div class="invalid-feedback">${ numeroConvenioMsg }</div>
                                        </c:if> 
                                        </div> 
                            </div>        
                            <div class="form-group col-md-7" disabled>
                             <label for="nomeConvenio"><fmt:message key = "br.cefetrj.sisgee.resources.form.nomeConvenio"/></label>
                                <div class="input-group">
                                                        
                                   <input type="text" class="form-control" id="nomeConvenio" name="nomeConvenio" maxlength="100"  value="${ cvNome }" >   
                                   <span class="input-group-btn">
                                        <button class="btn btn-primary" type="button" id="btnBuscarNomeConvenio"><fmt:message key = "br.cefetrj.sisgee.resources.form.buscar"/></button>
                                    </span>   
                                    <c:if test="${ not empty nomeConvenioMsg }">
                                        <div class="invalid-feedback">${ nomeConvenioMsg }</div>
                                    </c:if>   
                                </div>     
                             </div>
                    </div>   
                    <!-- AQUI TERMINA O NOME E NUMERO DO CONV�NIO-->
                        <div class="form-group" disabled>
                            <div class="input-group" disabled>
                                                                
                                    <div class="custom-controls-stacked d-block my-3">
                                            <label><fmt:message key = "br.cefetrj.sisgee.resources.form.tipoPJ_PF"/></label>                                
                                            <label class="custom-control custom-radio">
                                                <input id="pj" class="custom-control-input" type="radio" name="tipoConvenio" value="pj" ${ tConvenio == 'pj' ? 'checked' : '' } disabled> 
                                                <span class="custom-control-indicator"></span> 
                                                <span class="custom-control-description" ><fmt:message key = "br.cefetrj.sisgee.resources.form.pj"/></span>
                                            </label>						

                                            <label class="custom-control custom-radio">
                                                <input id="pf" class="custom-control-input" type="radio" name="tipoConvenio" value="pf" ${ tConvenio == 'pf' ? 'checked' : '' } disabled> 
                                                <span class="custom-control-indicator"></span> 
                                                <span class="custom-control-description"><fmt:message key = "br.cefetrj.sisgee.resources.form.pf"/></span>
                                            </label>                
                                    </div>						              
                                <!-- AQUI SELECIONA AGENTE DE INTEGRACAO-->
                                <div class="custom-controls-stacked d-block my-3" >
                                     <label for="isAgenteIntegracao"><fmt:message key = "br.cefetrj.sisgee.form_empresa.msg_agente_integracao"/></label>
                                     <label class="custom-control custom-radio" disabled>

                                         <input id="agenteSim" class="custom-control-input" type="radio" name="isAgenteIntegracao" ${ agIntegracao == 'true' ? 'checked' : '' } value="true" disabled> 
                                         <span class="custom-control-indicator"></span> 
                                         <span class="custom-control-description" ><fmt:message key = "br.cefetrj.sisgee.resources.form.sim"/></span><!-- AQUI SELECIONA SIM PARA AGENTE DE INTEGRACAO-->
                                     </label>						

                                     <label class="custom-control custom-radio">
                                         <input id="agenteNao" class="custom-control-input" type="radio" name="isAgenteIntegracao" ${ agIntegracao != 'true' ? 'checked' : '' } value="false" disabled> 
                                         <span class="custom-control-indicator"></span> 
                                         <span class="custom-control-description"><fmt:message key = "br.cefetrj.sisgee.resources.form.nao"/></span><!-- AQUI SELECIONA NAO PARA AGENTE DE INTEGRACAO-->
                                     </label>
                                 </div>						
                                <!-- AQUI TERMINA SELECIONA AGENTE DE INTEGRACAO-->                            
                                <input type="hidden" class="form-control nomeAgenciada nomeAgenciada"  id="nomeAgenciada1" name="nomeAgenciada1" value="${ nomeAgenciada}" readonly>  
                                <label for="nomeAgenciada"><fmt:message key = "br.cefetrj.sisgee.resources.form.nomeAgenciada"/></label>
                                <label class="custom-control" disabled>
                                    <input type="text" class="form-control" id="nomeAgenciada"  name="nomeAgenciada" value="${ nomeAgenciada }" maxlength="20" readonly>
                                </label>
                            </div>
                        </div> 
                    </div>                      

                    <!-- AQUI COMECA EMPRESA-->
                    <div class="form-row" >
                        <div class="form-group col-md-4">
                            <input type="hidden" class="form-control cnpjEcpf cnpjEcpf"  id="cnpjEcpf1" name="cnpjEcpf1" value="${cvCpfCnpj}" readonly>    
                                <label for="cnpjEcpf"><fmt:message key = "br.cefetrj.sisgee.resources.form.exibirCPFeCNPJ"/></label>
                                <div class="input-group">						  
                                    <input type="text" class="form-control cnpjEcpf" id="cnpjEcpf" name="cnpjEcpf" value="${cvCpfCnpj}" readonly>
                                </div>
                        </div>
                        <div class="form-group col-md-6" disabled>
                            <label for="nomeEmpresaPessoa"><fmt:message key = "br.cefetrj.sisgee.resources.form.razaoSocial"/></label>
                            <input type="text" class="form-control nomeEmpresaPessoa nomeEmpresaPessoa" id="nomeEmpresaPessoa" name="nomeEmpresaPessoa" value="${ cvNome }" readonly>
                        </div>
                    </div>
                </fieldset>
            <%-- ALTERA��O VICTOR--%>
                        
                       
                
                <!-- Aqui Come�a O termo Aditivo -->
                
                <!-- Aqui come�a Vigencia-->
                <c:if test="${ not empty periodoMsg }">
                    <div class="alert alert-danger" role="alert">${ periodoMsg }</div>
                </c:if>
                <fieldset class="form-group" }>
                    <legend class="col-form-legend col-lg"><fmt:message key = "br.cefetrj.sisgee.resources.form.vigenciaEstagio"/></legend>
                    <div class="form-row">
                        <div class="form-group col-md-6">

                            <label for="dataInicioTermoEstagio"><fmt:message key = "br.cefetrj.sisgee.resources.form.dataInicio"/></label>
                            <input type="text" class="form-control col-sm-4 " id="dataInicioTermoEstagio"  name="dataInicioTermoEstagio" value="${ vidataInicioTermoEstagio }"  onchange="sugereData();">
                            <p class="valid-feedback" id="dataIni" name="dataIni"></p>
                            <c:if test="${ not empty dataInicioMsg }">
                                <div class="invalid-feedback">${ dataInicioMsg }</div>
                            </c:if>
                        </div>
                        <div class="form-group col-md-6">
                            <label for="dataFimTermoEstagio"><fmt:message key = "br.cefetrj.sisgee.resources.form.dataTermino"/></label>
                            <input type="text" class="form-control col-sm-4 " id="dataFimTermoEstagio"   name="dataFimTermoEstagio" value="${showVigencia eq 'sim' ? '' : vidataFimTermoEstagio }"} >
                            <c:if test="${ not empty dataFimMsg }">
                                <div class="invalid-feedback">${ dataFimMsg }</div>
                            </c:if>
                        </div>
                    </div>
                </fieldset>
                <!-- Aqui Termina Vigencia-->
                
                <!-- Aqui come�a Carga Hor�ria-->
                <fieldset class="form-group">
                    <legend class="col-form-legend col-lg"><fmt:message key = "br.cefetrj.sisgee.resources.form.cargaHorariaAluno"/></legend>
                    <div class="form-row">
                        <div class="form-group col-md-4">
                            <label for="cargaHorariaTermoEstagio"><fmt:message key = "br.cefetrj.sisgee.resources.form.horasDia"/></label>
                            <input type="text" required="required" maxlength="1" pattern="[1-6]+$" class="form-control col-sm-2 " id="cargaHorariaTermoEstagio" name="cargaHorariaTermoEstagio" value="${ showCargaHoraria eq 'sim' ? '' :cacargaHorariaTermoEstagio }" }>
                            <c:if test="${ not empty cargaHorariaMsg }">
                                <div class="invalid-feedback">${ cargaHorariaMsg }</div>
                            </c:if>
                        </div>
                    </div>
                </fieldset>
                <!-- Aqui Termina Carga Hor�ria-->
                
                <!-- Aqui come�a Valor Bolsa-->
                <fieldset class="form-group">
                    <legend class="col-form-legend col-lg"><fmt:message key = "br.cefetrj.sisgee.resources.form.valorBolsaEstagio"/></legend>
                    <div class="form-row">
                        <div class="form-group col-md-4">
                            <label for="valorBolsa">Valor (R$)</label>
                            <input type="text" class="form-control col-sm-6 ${ not empty valorBolsaMsg ? 'is-invalid': not empty valorBolsaMsg ? 'is-invalid' : 'is-valid' }" id="valorBolsa" name="valorBolsa" value="${ showValorBolsa eq 'sim' ? '' : vavalorBolsa }">
                            <c:if test="${ not empty valorBolsaMsg }">
                                <div class="invalid-feedback">${ valorBolsaMsg }</div>
                            </c:if>
                        </div>
                    </div>
                </fieldset>
                <!-- Aqui termina Valor Bolsa-->

                <!-- Aqui come�a local est�gio-->
                <fieldset class="form-group">
                    <legend class="col-form-legend col-lg"><fmt:message key = "br.cefetrj.sisgee.resources.form.localEstagio"/></legend>
                    <div class="form-row">
                        <div class="form-group col-md-12">

                            <label for="enderecoTermoEstagio"><fmt:message key = "br.cefetrj.sisgee.resources.form.endereco"/></label>
                            <input type="text" required="required" <%--pattern="[1-9,a-z\s]+$"--%> maxlength="255" class="form-control" id="enderecoTermoEstagio" name="enderecoTermoEstagio" value="${ showLocal eq 'sim' ? '' :enenderecoTermoEstagio }">
                            <c:if test="${ not empty enderecoMsg }">
                                <div class="invalid-feedback">${ enderecoMsg }</div>
                            </c:if>
                        </div>
                    </div>
                    <div class="form-row">
                        <div class="form-group col-md-2">
                            <label for="numeroEnderecoTermoEstagio"><fmt:message key = "br.cefetrj.sisgee.resources.form.numero"/></label>
                            <input type="text" maxlength="10" class="form-control" id="numeroEnderecoTermoEstagio" name="numeroEnderecoTermoEstagio" value="${ showLocal eq 'sim' ? '' :ennumeroEnderecoTermoEstagio }">
                            <c:if test="${ not empty numeroEnderecoMsg }">
                                <div class="invalid-feedback">${ numeroEnderecoMsg }</div>
                            </c:if>
                        </div>
                        <div class="form-group col-md-4">
                            <label for="complementoEnderecoTermoEstagio"><fmt:message key = "br.cefetrj.sisgee.resources.form.complemento"/></label>
                            <input maxlength="150" type="text" class="form-control" id="complementoEnderecoTermoEstagio" name="complementoEnderecoTermoEstagio" value="${ showLocal eq 'sim' ? '' :encomplementoEnderecoTermoEstagio }">
                            <c:if test="${ not empty complementoEnderecoMsg }">
                                <div class="invalid-feedback">${ complementoEnderecoMsg }</div>
                            </c:if>
                        </div>
                        <div class="form-group col-md-6">
                            <label for="bairroEnderecoTermoEstagio"><fmt:message key = "br.cefetrj.sisgee.resources.form.bairro"/></label>
                            <input type="text" maxlength="150" class="form-control" id="bairroEnderecoTermoEstagio" name="bairroEnderecoTermoEstagio" value="${ showLocal eq 'sim' ? '' :enbairroEnderecoTermoEstagio }">
                            <c:if test="${ not empty bairroEnderecoMsg }">
                                <div class="invalid-feedback">${ bairroEnderecoMsg }</div>
                            </c:if>
                        </div>
                    </div>
                    <div class="form-row">					
                        <div class="form-group col-md-6">
                            <label for="cidadeEnderecoTermoEstagio"><fmt:message key = "br.cefetrj.sisgee.resources.form.cidade"/></label>
                            <input type="text" maxlength="150" class="form-control" id="cidadeEnderecoTermoEstagio" name="cidadeEnderecoTermoEstagio" value="${ showLocal eq 'sim' ? '' :encidadeEnderecoTermoEstagio }">
                            <c:if test="${ not empty cidadeEnderecoMsg }">
                                <div class="invalid-feedback">${ cidadeEnderecoMsg }</div>
                            </c:if>
                        </div>
                        <div class="form-group col-md-2">
                            <label for="estadoEnderecoTermoEstagio"><fmt:message key = "br.cefetrj.sisgee.resources.form.estado"/></label>
                            <select name = "estadoEnderecoTermoEstagio" id="estadoEnderecoTermoEstagio" value="${enuf}" class="form-control ${ not empty estadoEnderecoMsg ? 'is-invalid': not empty estadoEnderecoMsg ? 'is-invalid' : 'is-valid' }">
                                <option value=" ${enuf}" selected>${showLocal eq 'sim' ? '' :enuf}</option>
                                <c:forEach items="${ uf }" var="uf">
                                    <option value="${ uf }">${ uf }</option>
                                </c:forEach>							
                            </select>
                            <c:if test="${ not empty estadoEnderecoMsg }">
                                <div class="invalid-feedback">${ estadoEnderecoMsg }</div>
                            </c:if>
                        </div>
                        <div class="form-group col-md-4">
                            <label for="cepEnderecoTermoEstagio"><fmt:message key = "br.cefetrj.sisgee.resources.form.cep"/></label>
                            <input type="text" class="form-control" id="cepEnderecoTermoEstagio" name="cepEnderecoTermoEstagio" value="${ showLocal eq 'sim' ? '' :encepEnderecoTermoEstagio }">
                            <c:if test="${ not empty cepEnderecoMsg }">
                                <div class="invalid-feedback">${ cepEnderecoMsg }</div>
                            </c:if>
                        </div>
                    </div>
                </fieldset>
                <!-- Aqui come�a local est�gio-->
                
                <!-- Aqui come�a Supervisor-->
                <fieldset class="form-group" >
                    <legend class="col-form-legend col-lg">Supervisor do Est�gio</legend>    
                        <div class="form-row" > 
                            <div class="form-group col-md-3"  >
                                <label for="eEstagioObrigatorio"><fmt:message key = "br.cefetrj.sisgee.resources.form.estagioObrigatorio"/></label>
                            </div>

                            <div class="custom-controls-stacked d-block my-3" >
                                <label class="custom-control custom-radio"> 
                                    <input id="estagioSim" name="eEstagioObrigatorio" type="radio" class="custom-control-input" value = "sim"  ${ eobrigatorio eq 'true'? 'checked' : '' }> 
                                    <span class="custom-control-indicator"></span> 
                                    <span class="custom-control-description" ><fmt:message key = "br.cefetrj.sisgee.resources.form.sim"/></span>
                                </label> 
                                <label class="custom-control custom-radio"> 
                                    <input id="estagioNao" name="eEstagioObrigatorio" type="radio" class="custom-control-input" value = "nao"  ${ eobrigatorio eq 'false'? 'checked' : ''  }> 
                                    <span class="custom-control-indicator"></span> 
                                    <span class="custom-control-description"><fmt:message key = "br.cefetrj.sisgee.resources.form.nao"/></span>
                                </label>
                            </div>				
                        </div>

                        <div class="form-row">
                            <div class="form-group col-md-6">
                                <label for="nomeSupervisor"><fmt:message key = "br.cefetrj.sisgee.resources.form.nomeSupervisor"/></label>
                                <input type="text" class="form-control" id="nomeSupervisor"  name="nomeSupervisor" value="${ showSupervisor eq 'sim' ? '' :nomeSupervisor }" maxlength="80">
                                <c:if test="${ not empty nomeSupervisorMsg }">
                                    <div class="invalid-feedback">${ nomeSupervisorMsg }</div>
                                </c:if>                        
                            </div>
                            <div class="form-group col-md-6">
                                <label for="cargoSupervisor"><fmt:message key = "br.cefetrj.sisgee.resources.form.cargoSupervisor"/></label>
                                <input type="text" class="form-control" id="cargoSupervisor"  name="cargoSupervisor" value="${ showSupervisor eq 'sim' ? '' :cargoSupervisor }" maxlength="80">
                                <c:if test="${ not empty cargoSupervisorMsg }">
                                    <div class="invalid-feedback">${ cargoSupervisorMsg }</div>
                                </c:if>                      
                            </div>
                        </div>                        
                </fieldset>
                <!-- Aqui termina Supervisor-->
                
                <!-- Aqui come�a Professor Orientador-->             
                <fieldset class="form-group">
                  <fieldset ${ isVisualizacao eq true ? 'disabled' :'' }>
                    <div class="form-group col-md-8">
                        <label for="nomeProfessorOrientador"><fmt:message key = "br.cefetrj.sisgee.resources.form.professorOrientador"/></label>
                        <input type="hidden" name="idProfessorOrientador" id="idProfessorOrientador" value = "${idProfessor}"/>
                         
                        <select name="nomeProfessorOrientador" id="nomeProfessorOrientador" class="form-control ${ not empty idProfessorMsg ? 'is-invalid': not empty idProfessorMsg ? 'is-invalid' : 'is-valid' }" >
                            <c:forEach items="${ professores }" var="professor">
                                <c:if test="${professor.idProfessorOrientador eq idProfessor}">
                                    <option value="${ professor.idProfessorOrientador }" selected> ${ professor.nomeProfessorOrientador }</option>
                                </c:if>
                                <c:if test="${idProfessor != professor.idProfessorOrientador}">
                                    <option value="${ professor.idProfessorOrientador }"> ${ professor.nomeProfessorOrientador }</option>
                                </c:if>       
                            </c:forEach>				
                        </select>
                        <c:if test="${ not empty idProfessorMsg }">
                            <div class="invalid-feedback">${ idProfessorMsg }</div>
                        </c:if>				
                    </div>
                </fieldset>
                                
                    <button type="button" class="btn btn-secondary" href="AlterarTermoEAditivo?ide=">Salvar</button>	
                    <button type="button" class="btn btn-secondary" onclick="javascript:location.href = 'index.jsp'">Cancelar</button>

            
        </form>
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
        </form>
    </div>
    <%@include file="import_footer.jspf"%>
    <%@include file="import_finalbodyscripts.jspf"%>
    <%@include file="import_scripts.jspf"%>
    <script>
        $(document).ready(function () {
            var tamanho = $("#cnpjEcpf1").val().length;
            
            $('#cargaHorariaTermoEstagio').mask('9');
            
            $('#dataInicioTermoEstagio').mask('99/99/9999');
            $('#dataFimTermoEstagio').mask('99/99/9999');
            $("#cnpjEcpf1").mask("99.999.999/9999-99");        
            $('#cepEnderecoTermoEstagio').mask('99.999-999');
            $('#dataIni').mask('99/99/9999');
            $('#nomeProfessorOrientador').editableSelect().on('select.editable-select', 
            function (e, li) {
                $('#idProfessorOrientador').val(
                    li.val()
                );
            });          
        });
        
        
        function alerta(){
            alert("aqui");
        }
        
        function sugereData() {
            //var data = new Date(document.getElementById('dataInicioTermoEstagio').value);
            var tipoDeAluno = document.getElementById('tipoAluno').value;

            var data = document.getElementById('dataInicioTermoEstagio').value;
            var dia = data.substring(0, 2);
            var mes = data.substring(3, 5);
            var ano = data.substring(6, 10);
            
            var dataNova = new Date(mes + "-" + dia + "-" + ano);
            var tipoDeAluno = "";
            if(tipoDeAluno != null){            
                if (tipoDeAluno == "tecnico") {
                    dataNova.setMonth(dataNova.getMonth() + 6);
                    tipoDeAluno = "Curso T�cnico";
                }else{
                    dataNova.setMonth(dataNova.getMonth() + 12);
                    tipoDeAluno = "Gradua��o";
                }            
                document.getElementById("dataIni").innerHTML = "Esse Est�gio terminaria em " + dataNova.toLocaleDateString() + " para este aluno de " + tipoDeAluno;
            }
                              
        }   
        
    </script>
</body>
</html>
