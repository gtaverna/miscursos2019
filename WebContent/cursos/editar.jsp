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
		<form action="Editar" method="post" class="form-horizontal">
			<fieldset>
				<legend class="col-sm-10 col-xs-12 col-sm-offset-1">Editando
					curso ID: ${curso.id } - ${curso.estado }</legend>
					<input  type="hidden" name="id" value="${curso.id }" >

				<div class="col-sm-10 col-xs-12 col-sm-offset-1">
					<div class="form-group">
						<label for="nombre" class="col-sm-2">Nombre &nbsp;<i
							class=" indicator" style="color: red"></i></label>
						<div class="col-sm-4">
							<input id="nombreCurso" type="text" class="form-control"
								name="nombre" value="${curso.nombre }" required>
						</div>

						<label for="fecha" class="col-sm-2 col-sm-push-1  ">Fecha
							&nbsp;<i class=" indicator" style="color: red"></i>
						</label>
						<div class="col-sm-4">

							<input type="text" class="form-control" name="fecha"
								value="${curso.fecha }" readonly>
						</div>
					</div>

					<div class="form-group">
						<label for="correlativa" class="col-sm-2">Correlativa</label>
						<div class="col-sm-4">
							<input id="correlativa" type="number" class="form-control"
								name="correlativa" value="${curso.correlativa }" readonly>
						</div>
						<label for="presencia" class="col-sm-1 col-sm-offset-1">Presencia</label>
						<div class="col-sm-4">
							<input id="presencia" type="number" class="form-control"
								name="presencia" value="${curso.presencia }" readonly>
						</div>
					</div>
					<div class="form-group">
						<label for="grupoaprobacion" class="col-sm-2">G.Correlativa</label>
						<div class="col-sm-4">
							<input id="grupoaprobacion" type="number" class="form-control"
								name="grupoaprobacion" value="${curso.grupo_correlativa }" readonly>
						</div>
						<label for="grupopresencia" class="col-sm-1 col-sm-offset-1">G.Presencia</label>
						<div class="col-sm-4">
							<input id="grupopresencia" type="number" class="form-control"
								name="grupopresencia" value="${curso.grupo_presencia }" readonly>
						</div>
					</div>


					<div class="form-group">
						<label for="aprobacion" class="col-sm-2">%Aprobacion</label>
						<div class="col-sm-4">
							<input id="aprobacion" type="number" class="form-control"
								name="aprobacion" value="${curso.aprobacion }" min="0" max="100">
						</div>
						<label for="grupo" class="col-sm-1 col-sm-offset-1">Grupo</label>
						<div class="col-sm-4">
							<input id="grupo" type="text" class="form-control" name="grupo"
								placeholder="Grupo" value="${curso.grupo }">
						</div>
					</div>
					<div class="form-group">
							<label for="tolerancia" class="col-sm-2">Tolerancia</label>
						<div class="col-sm-10">
							<input id="tolerancia" type="number" class="form-control"
								name="tolerancia" value="${curso.tolerancia }" min="0" required>
						</div>
						
					</div>
					<div class="form-group">
							<label for="descripcion" class="col-sm-2">Descripción</label>
							<div class="col-sm-10">
								<textarea class="form-control" name="descripcion">${curso.descripcion }</textarea>
							</div>
					</div>
					<input type="text" name="marca" value="${curso.marca }" readonly
						class="hidden">
					<div class="form-group hidden">
						<label for="marca" class="col-sm-2">Marca</label>
						<div class="col-sm-6">
							<input id="marca" type="text" class="form-control" name="marca"
								value="${user.marca }" readonly>
						</div>
					</div>
					<hr>
					<div class="form-group">
						
							<label for="tiendas" class="col-sm-2"><i class=" indicator glyphicon glyphicon-cutlery"></i> Tiendas</label>

							<div class="col-sm-10">
								<ul class="list-group">
									<c:forEach var="tienda" items="${tiendas }">
										<li class="list-group-item col-sm-3 col-sm-offset-1">${tienda[1] }-${tienda[2] } - 
											
											<c:choose>
												<c:when test="${tienda[4] == 'NUEVO' }">
													Pendiente
												</c:when>
												<c:when test="${tienda[4] == 'CONFIRMADO' }">
													Confirmado
												</c:when>
												<c:when test="${tienda[4] == 'BORRADOR' }">
													Borrador
												</c:when>
												<c:otherwise>
													${tienda[4] }
												</c:otherwise>
											</c:choose>
										
										
										</li>
									</c:forEach>
								</ul>
							</div>
						</div>
						<hr>
						<div class="form-group">
							
							<label for="instructores" class="col-sm-2"><i
								class="indicator glyphicon glyphicon-user"></i> Instructores
							</label>
							
							<select
								class="selectpicker col-sm-10"
								name="instructores" data-live-search="true"
								data-actions-box="true" multiple required
								title="Seleccione Instructores">
								<c:forEach var="instructor" items="${instructores }">
									<option value="gi${instructor[0]  }"   <c:if test="${instructor[3] eq true}">selected="selected"</c:if>      >  ${instructor[1] } - ${instructor[2] }</option>
								</c:forEach>
							</select>
							
												
						</div>
						
						<hr>
						<div class="form-group">
							
							<label for="categoria" class="col-sm-2"><i class=" indicator glyphicon glyphicon-education"></i> Categorias</label>

							<ul class="list-group col-sm-10">
								<c:forEach var="categoria" items="${categorias }">
									<li class="list-group-item col-sm-3 col-sm-offset-1">${categoria.nombre }</li>
								</c:forEach>
							</ul>
						</div>
					
					<hr>
					<div class="form-group">
						
						<label for="lugar" class="col-sm-2">Lugar</label>
						<div class="col-sm-10">
							<input id="lugar" type="text" class="form-control" name="lugar"
								 value="${curso.lugar }">
						</div>
					</div>
						
					<div class="form-group">
						<div class="col-sm-6 col-sm-offset-3">
							<input type="submit" class=" btn btn-success btn-block " 
								value="Guardar">
						</div>
					</div>
					
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
	$('#datePickerForm2').datepicker({
		autoclose : true,
		dateFormat : 'yy/mm/dd',
		maxDate : '0d'
	})
</script>

</html>