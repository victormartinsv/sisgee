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
	Registro do Termo de Rescisão
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
			Registro do Termo de Rescisão
		</h5>		

		</p>		
		
		<form action="ValidaTermoRescisaoServlet" method="post">
			
			<fieldset class="form-group dadosAluno" ${ not empty rescisao ? 'disabled' :'' }>
				
				<%@include file="import_busca_aluno.jspf"%>
				
			</fieldset>
			
			<fieldset class="form-group">

				
				<div class="form-group col-md-4">

						<label for="dataRescisaoParam">Data de Rescisão</label>
						<input type="text" class="form-control " id="dataRescisaoParam"  name="dataRescisaoParam" value="${ param.dataRescisaoParam }" >
					</div>
			</fieldset>
		
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