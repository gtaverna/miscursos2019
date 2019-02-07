<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<jsp:include page="../_header.jsp"></jsp:include>
<body>

	<div class="container-fluid">
		<div class="col-sm-10 col-xs-12 col-sm-offset-1">
			<form action="Aceptar" method="post">
				<fieldset>

					<legend class="col-sm-12 col-xs-12 col-md-12">Aceptar Curso: ${curso.id } - ${curso.nombre }</legend>


					<input type="hidden" name="cursoid" value="${curso.id }">
					
							<jsp:include page="../headercurso.jsp"></jsp:include>
					

					<div class="col-sm-12 col-xs-12">
						<fieldset>
						
							<legend><br>Empleados</legend>
							<table id="tabla" class="table table-bordered">
								<thead>
									<tr>
										<th>Legajo</th>
										<th>Nombre</th>
										<th>Categoria</th>
										<th>Asistencia</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="asistente" items="${asistentes }">
										<tr>
											<td>${asistente.legajo }</td>
											<td>${asistente.nombre }</td>
											<td>${asistente.categoria }</td>
											<td style="text-align: center; vertical-align: middle;"><input
												type="checkbox" name="asistentes"
												style="width: 25px; height: 25px;"
												value="${asistente.legajo }"
												<c:if test="${asistente.seleccionado }">checked</c:if>></td>
										</tr>

									</c:forEach>
								</tbody>
							</table>
						</fieldset>
						<br>
						<div class="col-sm-10 col-xs-12 col-sm-offset-1 col-md-10 col-md-offset-1">
							<div class="col-sm-6 col-xs-6 col-md-6">
								<select class="form-control selector" name="estadoGuardar">
									<option value="BORRADOR" selected>Guardar como
										borrador</option>
									<option value="ENVIAR">Guardar y enviar</option>
								</select> 
							</div>
							<div class="col-sm-6 col-xs-6 col-md-6">
							<input onclick="guardar()" type="submit" value="Guardar Borrador" class="btn btn-info btn-block">
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

	$('.selector').change(function(){
		  if($(this).val() == 'BORRADOR'){ 
		    $('.btn').removeClass('btn-info');
		    $('.btn').removeClass('btn-success');
		    $('.btn').addClass('btn-info');
		    $('.btn').attr('value', 'Guardar Borrador')
		  }else if($(this).val() == 'ENVIAR'){ 

			    $('.btn').removeClass('btn-info');
			    $('.btn').removeClass('btn-success');
			    $('.btn').addClass('btn-success');

			    $('.btn').attr('value', 'Enviar Listado')
		  }
		});
	
	function guardar(){
	
		$('input[type="search"]').val('').keyup();
		$("form").submit();
	}
	
</script>
</html>