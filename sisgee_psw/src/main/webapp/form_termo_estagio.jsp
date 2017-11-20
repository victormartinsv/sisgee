<!DOCTYPE html>
<html lang="en">
<head>
<%@include file="import_head.jspf"%>

<style type="text/css">

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
		<p class="tituloForm">
		<h5>Registro de Termo de Estágio</h5>
		</p>

		<form>
			<fieldset class="form-group">
				<legend class="col-form-legend col-lg">Dados da Empresa Conveniada</legend>
				<div class="form-group col-md-6">
					<label for="numeroConvenio">Número do convênio</label>
					<input type="text" class="form-control" id="numeroConvenio">
				</div>
				
				<div class="form-row">
					<div class="form-group col-md-3">
						<label for="isAgenteIntegracao">É agente de integração?</label>
					</div>
					<div class="form-group">		
						<div class="form-check form-check-inline">
						  <label class="form-check-label">
						    <input class="form-check-input" type="radio" name="isAgenteIntegracao" id="isAgenteIntegracao" value="sim"> Sim
						  </label>
						</div>
						<div class="form-check form-check-inline">
						  <label class="form-check-label">
						    <input class="form-check-input" type="radio" name="isAgenteIntegracao" id="notAgenteIntegracao" value="nao"> Não
						  </label>
						</div>
					</div>
				</div>	
			</fieldset>
			
			
			<fieldset class="form-group">
				<legend class="col-form-legend col-lg">Dados do Aluno</legend>
				<div class="form-row">
					<div class="form-group col-md-4">
						<label for="matricula">Matrícula</label>
						<input type="text" class="form-control" id="matricula">
					</div>
					<div class="form-group col-md">
						<label for="nome">Nome</label>
						<input type="text" class="form-control" id="nome">
					</div>
				</div>
				<div class="form-row">
					<div class="form-group col-md-6">
						<label for="nomeCurso">Curso</label>
						<input type="text" class="form-control" id="nomeCurso">
					</div>
					<div class="form-group col-md-6">
						<label for="nomeCampus">Unidade</label>
						<input type="text" class="form-control" id="nomeCampus">
					</div>
				</div>
			</fieldset>
			
			<fieldset class="form-group">
				<legend class="col-form-legend col-lg">Vigência do Estágio</legend>
				
			</fieldset>
			
			<fieldset class="form-group">
				<legend class="col-form-legend col-lg">Carga Horário do Aluno</legend>
				<div class="form-row">
					<div class="form-group col-md-4">
						<label for="cargaHorariaTermoEstagio">Horas por dia</label>
						<input type="text" class="form-control" id="cargaHorariaTermoEstagio">
					</div>
				</div>
			</fieldset>
			
			<fieldset class="form-group">
				<legend class="col-form-legend col-lg">Valor da Bolsa de Estágio</legend>
				<div class="form-row">
					<div class="form-group col-md-4">
						<label for="valorBolsa">Valor</label>
						<input type="text" class="form-control" id="valorBolsa">
					</div>
				</div>
			</fieldset>
			
			<fieldset class="form-group">
				<legend class="col-form-legend col-lg">Local do Estágio</legend>
				<div class="form-row">
					<div class="form-group col-md-8">
						<label for="enderecoTermoEstagio">Endereço</label>
						<input type="text" class="form-control" id="enderecoTermoEstagio">
					</div>
					<div class="form-group col-md-4">
						<label for="complementoEnderecoTermoEstagio">Complemento</label>
						<input type="text" class="form-control" id="complementoEnderecoTermoEstagio">
					</div>
				</div>
				<div class="form-row">
					<div class="form-group col-md-2">
						<label for="bairroEnderecoTermoEstagio">Bairro</label>
						<input type="text" class="form-control" id="bairroEnderecoTermoEstagio">
					</div>
					<div class="form-group col-md-4">
						<label for="cidadeEnderecoTermoEstagio">Cidade</label>
						<input type="text" class="form-control" id="cidadeEnderecoTermoEstagio">
					</div>
					<div class="form-group col-md-4">
						<label for="estadoEnderecoTermoEstagio">Estado</label>
						<select id="estadoEnderecoTermoEstagio" class="form-control">
							<option selected>Choose...</option>
							<option>...</option>
						</select>
					</div>
					<div class="form-group col-md-2">
						<label for="cepEnderecoTermoEstagio">CEP</label>
						<input type="text" class="form-control" id="cepEnderecoTermoEstagio">
					</div>
				</div>
			</fieldset>

			<div class="form-row">
				<div class="form-group col-md-4">
					<label for="eEstagioObrigatorio">O estágio é obrigatório?</label>
				</div>
				<div class="form-group">		
					<div class="form-check form-check-inline">
					  <label class="form-check-label">
					    <input class="form-check-input" type="radio" name="eEstagioObrigatorio" id="isEstagioObrigatorio" value="sim"> Sim
					  </label>
					</div>
					<div class="form-check form-check-inline">
					  <label class="form-check-label">
					    <input class="form-check-input" type="radio" name="eEstagioObrigatorio" id="notEstagioObrigatorio" value="nao"> Não
					  </label>
					</div>
				</div>
			</div>
			<div class="form-group col-md-8">
				<label for="idProfessorOrientador">Professor orientador</label>
				<select id="idProfessorOrientador" class="form-control">
					<option selected>Choose...</option>
					<option>...</option>
				</select>
			</div>
			
			<button type="submit" class="btn btn-primary">Salvar</button>
			<button type="button" class="btn btn-secondary">Cancelar</button>
			
		</form>

	</div>
	<%@include file="import_finalbodyscripts.jspf"%>
</body>
</html>
