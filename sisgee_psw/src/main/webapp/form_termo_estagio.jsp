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

<title>Registro de Termo de Estágio</title>
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

		<h5>Registro de Termo de Estágio</h5>		
		</p>

		<form action="ValidaTermoEstagioServlet" method="post">
			<fieldset class="form-group">
				<legend class="col-form-legend col-lg">Dados da Empresa Conveniada</legend>
				<div class="form-group col-md-6">
					<label for="numeroConvenio">Número do convênio</label>
					<input type="text" class="form-control" id="numeroConvenio" name="convenio" value="${ param.convenio }" ${ not empty convenioMsg ? 'is-invalid': '' }>
					<c:if test="${ not empty convenioMsg }">
				    	<div class="invalid-feedback">${ convenioMsg }</div>
		        	</c:if>
				</div>				
				
				<div class="form-row">
					<div class="form-group col-md-3">
						<label for="isAgenteIntegracao">É Agente de Integração?</label>
					</div>
					<div class="form-group">		
						<div class="form-check form-check-inline">
						  <label class="form-check-label">
						    <input class="form-check-input isAgenteChk" type="radio" name="isAgenteIntegracao" value="sim"> Sim
						  </label>
						</div>
						<div class="form-check form-check-inline">
						  <label class="form-check-label">

						    <input class="form-check-input isAgenteChk" type="radio" name="isAgenteIntegracao" value="nao"> Não

						  </label>
						</div>
					</div>
				</div>
				<div class="form-row notAI AI">
					<div class="form-group col-md-4">
						<label for="cnpjEmpresa">CNPJ</label>
						<input type="text" class="form-control" id="cnpjEmpresa" name="cnpjEmpresa" value="${ param.cnpjEmpresa }">
					</div>
					<div class="form-group col-md-6">

						<label for="nomeEmpresa">Razão Social</label>

						<input type="text" class="form-control" id="nomeEmpresa" name="nomeEmpresa" value="${ param.nomeEmpresa }">
					</div>
					<div class="form-group col-md-2" style="padding-top: 1.9em">
<!-- 						<button type="button" class="btn btn-default" aria-label="Adicionar"> -->
<!-- 							<span class="glyphicon glyphicons-plus" aria-hidden="true"></span> -->
<!-- 						</button> -->
						<button type="button" class="btn btn-primary">+</button>
					</div>
				</div>
				
				<div class="form-row isAI AI">
					<div class="form-group col-md-6">
						<label for="nomeEmpresa">RazÃ£o Social</label>
						<select id="nomeEmpresa" class="form-control">
							<option value="" selected>---</option>
							<c:forEach items="${ agentesIntegracao }" var="agenteIntegracao">
								<option value="${ agenteIntegracao.idAgenteIntegracao }">${ agenteIntegracao.nomeAgenteIntegracao }</option>
							</c:forEach>
						</select>
					</div>
					<div class="form-group col-md-2" style="padding-top: 1.9em">
						<button type="button" class="btn btn-primary">+</button>
					</div>
				</div>
				<div class="form-row isAI AI">
					<div class="form-group col-md-5">

						<label for="nomeEmpresa">CNPJ da Empresa ligada ao Agente de Integração</label>
						<input type="text" class="form-control" id="cnpjEmpresa" name="cnpjEmpresa" value="${ param.cnpjEmpresa }">
					</div>
					<div class="form-group col-md-5">
						<label for="nomeEmpresa">Razão social da Empresa ligada ao Agente de Integração</label>

						<input type="text" class="form-control" id="nomeEmpresa" name="nomeEmpresa" value="${ param.nomeEmpresa }">
					</div>
					<div class="form-group col-md-2" style="padding-top: 1.9em">
						<button type="button" class="btn btn-primary">+</button>
					</div>
				</div>							
			</fieldset>
			
			
			<fieldset class="form-group">
				<legend class="col-form-legend col-lg">Dados do Aluno</legend>
				<div class="form-row">
					<div class="form-group col-md-4">

						<label for="matricula">Matrícula</label>

						<input type="text" class="form-control" id="matricula" name="matricula" value="${ param.matricula }">
					</div>
					<div class="form-group col-md">
						<label for="nome">Nome</label>
						<input type="text" class="form-control" id="nome" name="nome" value="${ param.nome }">
					</div>
				</div>
				<div class="form-row">
					<div class="form-group col-md-6">
						<label for="nomeCurso">Curso</label>
						<input type="text" class="form-control" id="nomeCurso"  name="nomeCurso" value="${ param.nomeCurso }">
					</div>
					<div class="form-group col-md-6">
						<label for="nomeCampus">Unidade</label>
						<input type="text" class="form-control" id="nomeCampus"  name="nomeCampus" value="${ param.nomeCampus }">
					</div>
				</div>
			</fieldset>
			
			
			<fieldset class="form-group">
				<legend class="col-form-legend col-lg">VigÃªncia do EstÃ¡gio</legend>
				<div class="form-row">
					<div class="form-group col-md-4">

						<label for="dataInicioTermoEstagio">Data de início</label>
						<input type="text" class="form-control" id="dataInicioTermoEstagio"  name="dataInicioTermoEstagio" value="${ param.dataInicioTermoEstagio }">
					</div>
					<div class="form-group col-md-4">
						<label for="dataFimTermoEstagio">Data de término</label>

						<input type="text" class="form-control" id="dataFimTermoEstagio"   name="dataFimTermoEstagio" value="${ param.dataFimTermoEstagio }">
					</div>
				</div>
			</fieldset>
			
			
			<fieldset class="form-group">
				<legend class="col-form-legend col-lg">Carga HorÃ¡rio do Aluno</legend>
				<div class="form-row">
					<div class="form-group col-md-4">
						<label for="cargaHorariaTermoEstagio">Horas por dia</label>
						<input type="text" class="form-control" id="cargaHorariaTermoEstagio" name="cargaHorariaTermoEstagio" value="${ param.cargaHorariaTermoEstagio }">
					</div>
				</div>
			</fieldset>
			
			
			<fieldset class="form-group">
				<legend class="col-form-legend col-lg">Valor da Bolsa de EstÃ¡gio</legend>
				<div class="form-row">
					<div class="form-group col-md-4">
						<label for="valorBolsa">Valor</label>
						<input type="text" class="form-control" id="valorBolsa" name="valorBolsa" value="${ param.valorBolsa }">
					</div>
				</div>
			</fieldset>
			
			
			<fieldset class="form-group">
				<legend class="col-form-legend col-lg">Local do EstÃ¡gio</legend>
				<div class="form-row">
					<div class="form-group col-md-8">

						<label for="enderecoTermoEstagio">Endereço</label>

						<input type="text" class="form-control" id="enderecoTermoEstagio" name="enderecoTermoEstagio" value="${ param.enderecoTermoEstagio }">
					</div>
					<div class="form-group col-md-4">
						<label for="complementoEnderecoTermoEstagio">Complemento</label>
						<input type="text" class="form-control" id="complementoEnderecoTermoEstagio" name="complementoEnderecoTermoEstagio" value="${ param.complementoEnderecoTermoEstagio }">
					</div>
				</div>
				<div class="form-row">
					<div class="form-group col-md-2">
						<label for="bairroEnderecoTermoEstagio">Bairro</label>
						<input type="text" class="form-control" id="bairroEnderecoTermoEstagio" name="bairroEnderecoTermoEstagio" value="${ param.bairroEnderecoTermoEstagio }">
					</div>
					<div class="form-group col-md-4">
						<label for="cidadeEnderecoTermoEstagio">Cidade</label>
						<input type="text" class="form-control" id="cidadeEnderecoTermoEstagio" name="cidadeEnderecoTermoEstagio" value="${ param.cidadeEnderecoTermoEstagio }">
					</div>
					<div class="form-group col-md-4">
						<label for="estadoEnderecoTermoEstagio">Estado</label>
						<select id="estadoEnderecoTermoEstagio" class="form-control">
							<option value="" selected>---</option>
							<c:forEach items="${ uf }" var="uf">
								<option value="${ uf }">${ uf }</option>
							</c:forEach>
							
						</select>
					</div>
					<div class="form-group col-md-2">
						<label for="cepEnderecoTermoEstagio">CEP</label>
						<input type="text" class="form-control" id="cepEnderecoTermoEstagio" name="cepEnderecoTermoEstagio" value="${ param.cepEnderecoTermoEstagio }">
					</div>
				</div>
			</fieldset>


			<div class="form-row">
				<div class="form-group col-md-4">
					<label for="eEstagioObrigatorio">O estÃ¡gio Ã© obrigatÃ³rio?</label>
				</div>
				<div class="form-group">		
					<div class="form-check form-check-inline">
					  <label class="form-check-label">
					    <input class="form-check-input" type="radio" name="eEstagioObrigatorio" value="sim"> Sim
					  </label>
					</div>
					<div class="form-check form-check-inline">
					  <label class="form-check-label">

					    <input class="form-check-input" type="radio" name="eEstagioObrigatorio" value="nao"> Não

					  </label>
					</div>
				</div>
			</div>
			
			
			<div class="form-group col-md-8">
				<label for="idProfessorOrientador">Professor orientador</label>
				<select name="idProfessorOrientador" id="idProfessorOrientador" class="form-control">
					<option value="" selected>---</option>
					<c:forEach items="${ professores }" var="professor">
						<option value="${ professor.idProfessorOrientador }">${ professor.nomeProfessorOrientador }</option>
					</c:forEach>
					
				</select>
			</div>
			
			<button type="submit" class="btn btn-primary">Salvar</button>
			<button type="button" class="btn btn-secondary">Cancelar</button>
			
		</form>

	</div>
	<%@include file="import_footer.jspf"%>
	<%@include file="import_finalbodyscripts.jspf"%>
    <script type="text/javascript">
	    $('#dataInicioTermoEstagio, #dataFimTermoEstagio').datepicker({
	    	<c:if test="${ lang eq 'pt_BR' }">
	    	language: 'pt-BR'
	        </c:if>
	    });
	    
	    $('.isAI, .notAI').hide();
	    
	    $('.isAgenteChk').change(function(){
	    	$('.AI').hide();
	    	$(this).val() == 'sim' ? $('.isAI').show("slow") : $('.notAI').show("slow");
	    });
	    
    </script>
</body>
</html>
