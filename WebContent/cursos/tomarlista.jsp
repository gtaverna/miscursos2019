<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<jsp:include page="../_header.jsp"></jsp:include>

<body>





	<div class="container-fluid">
		<div class="col-sm-12 col-md-10 col-xs-12 col-md-offset-1">
			<form action="Tomarlista" method="post">
				<fieldset>

					<legend class="col-sm-12 col-xs-12 col-md-12">Tomar Lista
						curso: ${curso.id } - ${curso.nombre }</legend>


					<input type="hidden" name="cursoid" value="${curso.id }">
					<div style="font-size: 120%;" class="col-sm-12 col-xs-12 col-md-12">
						<p class="col-sm-6 col-md-6 col-xs-12">Fecha de curso: ${curso.fecha }</p> 
							<p class="col-sm-6 col-md-6 col-xs-12">Estado: ${curso.estado }</p> 
							<p class="col-sm-6 col-md-6 col-xs-12">%Aprobacion: ${curso.aprobacion }</p> 
							<p class="col-sm-6 col-md-6 col-xs-12">Lugar: ${curso.lugar }</p>
							<p class="col-sm-6 col-md-6 col-xs-12">Descripción: ${curso.descripcion }</p>
							<p class="col-sm-6 col-md-6 col-xs-12">Tolerancia: ${curso.tolerancia }</p>
							 <br> <br> <br> <br>
					</div>


					<div class="col-sm-12 col-xs-12" style="padding-right: 0px; padding-left: 0px;">
						<fieldset>
							<legend>Empleados</legend>
							<table id="tabla" class="table table-bordered">
								<thead>
									<tr>
										<th class="col-md-1 col-sm-1 col-xs-1">Legajo</th>
										<th class="col-md-4 col-sm-4 col-xs-3">Nombre</th>
										<th class="col-md-1 col-sm-1 col-xs-1 hidden-xs">Local</th>
										<th class="col-md-2 col-sm-2 col-xs-2 hidden-xs">Categoria</th>
										<th class="col-md-4 col-sm-4 col-xs-8">Asistencia</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="asistente" items="${asistentes }">
										<tr>
											<td>${asistente.legajo }</td>
											<td>${asistente.nombre }</td>
											<td class="hidden-xs">${asistente.local }</td>
											<td class="hidden-xs">${asistente.categoria }</td>
											<td  style="padding-right: 1px; padding-left: 1px;  padding-bottom: 2px;">
												<div class="col-sm-12 col-xs-12" style="padding-right: 0px; padding-left: 0px;" >
													
														<input type="radio" id="radio1_${asistente.id }" 
															name="asistencia_${asistente.id }"
															
															<c:if test="${asistente.estado_asistencia == 'PRESENTE' }">checked</c:if>
															value="PRESENTE"><label
															class="label label-presente col-sm-4  col-md-4 col-xs-12" for="radio1_${asistente.id }">Presente</label>
													
														<input type="radio" id="radio2_${asistente.id }" 
															name="asistencia_${asistente.id }"
															
															<c:if test="${asistente.estado_asistencia == 'TARDE' }">checked</c:if>
															value="TARDE"><label class="label label-tarde col-sm-4  col-md-4 col-xs-12"
															for="radio2_${asistente.id }">Tarde</label>
													
														<input type="radio" id="radio3_${asistente.id }" 
															name="asistencia_${asistente.id }"
															
															<c:if test="${asistente.estado_asistencia == 'AUSENTE' }">checked</c:if>
															value="AUSENTE"><label class="label label-ausente col-sm-4  col-md-4 col-xs-12"
															for="radio3_${asistente.id }">Ausente</label>
													
												</div>
											</td>
										</tr>

									</c:forEach>
								</tbody>
							</table>
						</fieldset>
						<br>
						<div
							class="col-sm-10 col-xs-12 col-sm-offset-1 col-md-10 col-md-offset-1">
							<div class="col-sm-6 col-xs-6 col-md-6">
								<select class="form-control selector" name="estadoGuardar">
									<option value="BORRADOR" selected>Guardar como
										borrador</option>
									<option value="ENVIAR">Guardar y enviar</option>
								</select>
							</div>
							<div class="col-sm-6 col-xs-6 col-md-6">
								<input type="submit" value="Guardar Borrador"
									class="btn btn-info btn-block">
							</div>
						</div>
					</div>

				</fieldset>

			</form>
			
		</div>
	</div>
	<br>
</body>
<script type="text/javascript">
	
	$('.selector').change(function() {
		if ($(this).val() == 'BORRADOR') {
			$('.btn').removeClass('btn-info');
			$('.btn').removeClass('btn-success');
			$('.btn').addClass('btn-info');
			$('.btn').attr('value', 'Guardar Borrador')
			$('input[type=radio]').removeAttr('required');
		} else if ($(this).val() == 'ENVIAR') {

			$('.btn').removeClass('btn-info');
			$('.btn').removeClass('btn-success');
			$('.btn').addClass('btn-success');
			$('.btn').attr('value', 'Enviar Listado')
			$('input[type=radio]').attr('required', 'required');
			
		}
	});
</script>
</html>