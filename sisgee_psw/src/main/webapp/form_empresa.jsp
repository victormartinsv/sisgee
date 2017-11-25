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

<title>Registro de Empresa (ou Agente Integração)</title>
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

		<h5>Registro de Empresa (ou Agente Integração)</h5>		
		</p>
		
		<form action="ValidaCadastroEmpresaServlet" method="post">
			<fieldset class="form-group">
				<div class="form-row" >
				<div class="form-group col-md-3" >
					<label for="agenteIntegracao">É Agente de Integração?</label>
				</div>

				<div class="custom-controls-stacked d-block my-3">
					<label class="custom-control custom-radio"> 
						<input id="agenteSim" name="agenteIntegracao" type="radio" class="custom-control-input" required value = "sim" ${ not empty aditivo ? 'disabled' :'' }> 
						<span class="custom-control-indicator"></span> 
						<span class="custom-control-description" >Sim</span>
					</label> 
					<label class="custom-control custom-radio"> 
						<input id="agenteNao" name="agenteIntegracao" type="radio" class="custom-control-input" required value = "nao" ${ not empty aditivo ? 'disabled' :'' }> 
						<span class="custom-control-indicator"></span> 
						<span class="custom-control-description">Não</span>
					</label>
				</div>				
			</div>
				
					<div class="form-group col-md-4">
						<label for="cnpjEmpresa">CNPJ</label>
						<input type="text" class="form-control ${ not empty cnpjEmpresaMsg ? 'is-invalid': 'is-valid' }" id="cnpjEmpresa" name="cnpjEmpresa" required value="${ param.cnpjEmpresa }">
						<c:if test="${ not empty cnpjEmpresaMsg }">
				    		<div class="invalid-feedback">${ cnpjEmpresaMsg }</div>
		        		</c:if>
					</div>
					<div class="form-group col-md-6">

						<label for="nomeEmpresa">Razão Social</label>
						<input type="text" class="form-control ${ not empty nomeEmpresaMsg ? 'is-invalid': 'is-valid' }" id="nomeEmpresa" name="nomeEmpresa" required value="${ param.nomeEmpresa }">
						<c:if test="${ not empty nomeEmpresaMsg }">
				    		<div class="invalid-feedback">${ nomeEmpresaMsg }</div>
		        		</c:if>
					</div>
		</fieldset>
			
				<button type="submit" class="btn btn-primary">Salvar</button>
			<button type="button" class="btn btn-secondary">Cancelar</button>
			
		</form>
	</div>
	<%@include file="import_footer.jspf"%>
	<%@include file="import_finalbodyscripts.jspf"%>
</body>
</html>