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
		
		<form action=BuscaTermoAditivoServlet method="post">
			
			<fieldset class="form-group dadosAluno" ${ not empty aditivo ? 'disabled' :'' }>
				
				<%@include file="import_busca_aluno.jspf"%>
				<div class="container">
					
					<button type="submit" class="btn btn-primary">Listar Termos aditivos</button>
				</div>				
				
			</fieldset>
		</form>
		
		<div class="container">
		<table class = "table table">
			
			<thead>		
				<tr>
					<th>Data de Registro</th>
					<th>CNPJ</th>
					<th>Razao Social</th>
				</tr>
			</thead>			
			<tbody>
				<c:forEach items = "${termosAditivos}" var = "termoAditivo">
						<tr>
						<td> ${ termoAditivo.termoEstagio.dataInicioTermoEstagio } </td>
						<td> ${ termoAditivo.termoEstagio.convenio.empresa.cnpjEmpresa }</td>
						<td> ${ termoAditivo.termoEstagio.convenio.empresa.cnpjEmpresa }</td>
							
							<c:url value = "/BuscaTermoAditivoServlet" var = "buscaTermoAditivoUrl" scope = "page">
								<c:param name="id" value = "${termoAditivo.idTermoAditivo}">  </c:param>
							</c:url>
							<td><a href = "${buscaTermoAditivoUrl}" >Data de Registro</a></td>
						</tr>
				</c:forEach>
			</tbody>

		</table>
		</div>
		
			
		<form action="ValidaTermoAditivoServlet" method="post">
				
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
		        <button type="button" class="btn btn-primary" data-dismiss="modal">Fechar</button>
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