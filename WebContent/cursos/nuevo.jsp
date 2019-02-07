<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<jsp:include page="../_header.jsp"></jsp:include>


<link rel="stylesheet" href="css/bootstrap-select.min.css">
<link rel="stylesheet" href="css/jquery-ui-timepicker-addon.css">
<script src="js/bootstrap-select.min.js"></script>
<script src="js/jquery-ui-timepicker-addon.js"></script>

<body>
	<div class="container-fluid">
		<form action="Nuevo" method="post" class="form-horizontal">
			<fieldset>
				<legend class="col-sm-12 col-xs-12 col-md-10 col-md-offset-1">Nuevo
					Curso</legend>

				<div class="col-sm-12 col-xs-12 col-md-10 col-md-offset-1">
					<div class="form-group">
						<label for="nombre" class="col-sm-2">Nombre &nbsp;<i
							class=" indicator glyphicon glyphicon-asterisk"
							style="color: red"></i></label>
						<div class="col-sm-4">
							<input id="nombreCurso" type="text" class="form-control"
								name="nombre" placeholder="Nombre" required>
						</div>

						<label for="fecha" class="col-sm-2 col-sm-push-1  ">Fecha
							&nbsp;<i class=" indicator glyphicon glyphicon-asterisk"
							style="color: red"></i>
						</label>
						<div class="col-sm-4 date">
							<div class="input-group date" data-provide="datepicker"
								id="datePicker">
								<input type="text" class="form-control" id="datePickerForm"
									name="fecha" required>
								<div class="input-group-addon">
									<span class="glyphicon glyphicon-calendar"></span>
								</div>
							</div>
						</div>
					</div>

					<div class="form-group">
						<label for="correlativa" class="col-sm-2">Correlativa</label>
						<div class="col-sm-4">
							<input id="correlativa" type="text" class="form-control"
								name="correlativa" placeholder="Correlativa">
						</div>
						<label for="presencia" class="col-sm-1 col-sm-offset-1">Presencia</label>
						<div class="col-sm-4">
							<input id="presencia" type="text" class="form-control"
								name="presencia" placeholder="Presencia">
						</div>
					</div>
					<div class="form-group">
						<label for="grupoaprobacion" class="col-sm-2">G.Correlativa</label>
						<div class="col-sm-4">
							<input id="grupoaprobacion" type="text" class="form-control"
								name="grupoaprobacion" placeholder="G.Correlativa">
						</div>
						<label for="grupopresencia" class="col-sm-1 col-sm-offset-1">G.Presencia</label>
						<div class="col-sm-4">
							<input id="grupopresencia" type="text" class="form-control"
								name="grupopresencia" placeholder="G.Presencia">
						</div>
					</div>



					<div class="form-group">
						<label for="aprobacion" class="col-sm-2">%Aprobacion</label>
						<div class="col-sm-4">
							<input id="aprobacion" type="number" class="form-control"
								name="aprobacion" placeholder="% Aprobacion" min="0" max="100">
						</div>
						<label for="grupo" class="col-sm-1 col-sm-offset-1">Grupo</label>
						<div class="col-sm-4">
							<input id="grupo" type="text" class="form-control" name="grupo"
								placeholder="Grupo" >
						</div>
					</div>
					
					<div class="form-group">
						<label for="lugar" class="col-sm-2">Lugar</label>
						<div class="col-sm-4">
							<input id="lugar" type="text" class="form-control" name="lugar"
								placeholder="Lugar">
						</div>
						<label for="tolerancia" class="col-sm-1 col-sm-offset-1">Tolerancia</label>
						<div class="col-sm-4">
							<input id="tolerancia" type="number" class="form-control"
								name="tolerancia" placeholder="Tolerancia" min="0" required>
						</div>
					</div>
					<div class="form-group">
						<label for="descripcion" class="col-sm-2">Descripción</label>
						<div class="col-sm-10">
							<textarea class="form-control" name="descripcion"
								placeholder="Descripción"></textarea>
						</div>
					</div>
					<div class="form-group hidden">
						<label for="marca" class="col-sm-2">Marca</label>
						<div class="col-sm-6">
							<input id="marca" type="text" class="form-control" name="marca"
								value="${user.marca }" readonly>
						</div>
					</div>

					<div class="form-group">
						<label for="tiendas" class="col-sm-2">Tiendas &nbsp;<i
							class=" indicator glyphicon glyphicon-asterisk"
							style="color: red"></i></label>
						<div>
							<select class="selectpicker col-sm-10 col-xs-12" name="tiendas"
								data-live-search="true" data-actions-box="true" multiple
								title="Seleccione Tiendas">
								<c:forEach var="tienda" items="${tiendas }">
									<option value="id${tienda[0] }">${tienda[1] }-${tienda[2] }</option>
								</c:forEach>
							</select>
						</div>
					</div>
					
					<div class="form-group">
						<label for="tiendas" class="col-sm-2">Grupo Tiendas</label>
						<div>
							<select class="selectpicker col-sm-10 col-xs-12" name="tiendasGrupo"
								data-live-search="true" data-actions-box="true" multiple
								 title="Seleccione Tiendas">
								<c:forEach var="tiendaGrupo" items="${tiendasGrupo }">
									<option value="id${tiendaGrupo.id }">${tiendaGrupo.descripcion }</option>
								</c:forEach>
							</select>
						</div>
					</div>
					
					<div class="form-group">
						<div class="col-sm-4 col-md-2">
							<label for="instructores">Instructores </label> <i
								class=" indicator glyphicon glyphicon-asterisk"
								style="color: red"></i>

						</div>
						<div>
							<select
								class="selectpicker col-sm-12 col-xs-12 col-md-10 col-md-offset-1"
								name="instructores" data-live-search="true"
								data-actions-box="true" multiple required
								title="Seleccione Instructores">
								<c:forEach var="instructor" items="${instructores }">
									<option value="id${instructor[0] }">${instructor[1] } - ${instructor[2] }</option>
								</c:forEach>
							</select>
						</div>
					</div>
					<div class="form-group">
						<div class="col-sm-4 col-md-2">
							<label for="categorias">Categorias </label> <i
								class=" indicator glyphicon glyphicon-asterisk"
								style="color: red"></i>

						</div>
						<div>
							<select
								class="selectpicker col-sm-12 col-xs-12 col-md-10 col-md-offset-1"
								name="categorias" data-live-search="true"
								data-actions-box="true" multiple 
								title="Seleccione Categorias">
								<c:forEach var="categoria" items="${categorias }">
									<option value="id${categoria.id }">${categoria.nombre }</option>
								</c:forEach>
							</select>
						</div>
					</div>
					<div class="form-group">
						
					</div>
					<div class="row col-sm-7 col-sm-offset-2">
						<br> <input type="submit" class="btn btn-success btn-block "
							value="Crear">
					</div>
					<br> <br>
				</div>
			</fieldset>
		</form>
	</div>
</body>
<script>
	$('#menuNuevo').addClass('active');
	$('.selectpicker').selectpicker({
		selectAllText : 'TODOS',
		deselectAllText : 'NINGUNO'
	});

	$('#datePickerForm').datetimepicker({
		controlType : 'select',
		oneLine : true,
		minDate : '1d',
		dateFormat : 'yy/mm/dd',
		timeFormat : 'hh:mm tt'
	});

	$('#correlativa').autocomplete({
		minLength : 3,
		source : 'AutocompleteCursos'
	})
	$('#presencia').autocomplete({
		minLength : 3,
		source : 'AutocompleteCursos'
	})
	$("#grupoaprobacion").autocomplete({
		source : "AutocompleteGrupos",
		minLength : 2
	});
	$("#grupopresencia").autocomplete({
		source : "AutocompleteGrupos",
		minLength : 2
	});
	$("#grupo").autocomplete({
		source : "AutocompleteGrupos",
		minLength : 2
	});
</script>

</html>