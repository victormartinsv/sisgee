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

<title>
	Registro de Termo Aditivo
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
			Registro de Termo Aditivo
		</h5>		

		</p>		
		
		<form action="ValidaTermoAditivoServlet" method="post">
			
			<fieldset class="form-group" ${ not empty aditivo ? 'disabled' :'' }>
				
				<%@include file="import_busca_aluno.jspf"%>
				
			</fieldset>
			
			<table class = "table table">
			
			<thead>		
				<tr>
					<th>Data de Registro</th>
					<th>CNPJ</th>
					<th>Razao Social</th>
				</tr>
			</thead>
			
			<tbody>
				<c:forEach items = "${empresas}" var = "empresa">
						<tr>
							<td>${empresa.cnpj}</td>
							<td>${empresa.razaoSocial}</td>
							<c:url value = "/DataRegistroServlet" var = "dataRegistroUrl" scope = "page">
								<c:param name="id" value = "${empresa.id}"></c:param>
							</c:url>
							<td><a href = "${dataRegistroUrl}">Data de Registro</a></td>
						</tr>
				</c:forEach>
			</tbody>

		</table>
		
		<br>
			
			<div class="mx-auto" style="width: 500px;">
				<div class="row">
					<div class="form-check form-check-inline">
						<label class="form-check-label">
							<input class="form-check-input" type="checkbox" id="vigencia" name="vigencia"  value="sim">Vigencia
						</label>
					</div>
				
					<div class="mx-auto" style="width: 200px;">
					<div class="form-check form-check-inline">
						<label class="form-check-label">
							<input class="form-check-input" type="checkbox" id="enderecoTermoEstagio" name="enderecoTermoEstagio" value="sim">Endereco do Estagio
						</label>
					</div>
					</div>
				
				</div>
			</div>
			
			<div class="mx-auto" style="width: 500px;">
				<div class="row">
					<div class="form-check form-check-inline">
						<label class="form-check-label">
							<input class="form-check-input" type="checkbox" id="cargaHorariaTermoEstagio" name="cargaHorariaTermoEstagio" value="sim">Carga Horaria
						</label>
					</div>
				
					<div class="mx-auto" style="width: 236px;">
					<div class="form-check form-check-inline">
						<label class="form-check-label">
							<input class="form-check-input" type="checkbox" id="professorOrientador" name="professorOrientador" value="sim">Professor Orientador
						</label>
					</div>
					</div>				
				</div>
			</div>
			
			<div class="mx-auto" style="width: 500px;">
				<div class="row">
					<div class="form-check form-check-inline">
						<label class="form-check-label">
							<input class="form-check-input" type="checkbox" id="valorBolsa" name="valorBolsa" value="sim">Valor da Bolsa
						</label>
					</div>
				</div>
			</div>			
			<br>			
		
			<button type="submit" class="btn btn-primary">Salvar</button>
			<button type="button" class="btn btn-secondary">Cancelar</button>			
		</form>

	</div>
	<%@include file="import_footer.jspf"%>
	<%@include file="import_finalbodyscripts.jspf"%>
	<%@include file="import_scripts.jspf"%>
    
</body>
</html>