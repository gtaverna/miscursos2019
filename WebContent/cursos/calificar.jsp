<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<jsp:include page="../_header.jsp"></jsp:include>
<body>




	<div class="container-fluid">
		<div class="col-sm-10 col-xs-12 col-sm-offset-1">
			<form action="Calificar" method="post">
				<fieldset>

					<legend class="col-sm-12 col-xs-12 col-md-12">Tomar Lista
						curso: ${curso.id } - ${curso.nombre }</legend>


					<input type="hidden" name="cursoid" value="${curso.id }">
					<jsp:include page="../headercurso.jsp"></jsp:include>


					<div class="container-fluid col-sm-12 col-xs-12">
						<fieldset>
							<legend><br>Empleados</legend>
							<table id="tabla" class="table table-bordered">
								<thead>
									<tr>
										<th class="col-md-1 col-sm-1 col-xs-1">Legajo</th>
										<th class="col-md-6 col-sm-6 col-xs-5">Nombre</th>
										<th class="col-md-1 col-sm-1 hidden-xs">Local</th>
										<th class="col-md-2 col-sm-2 hidden-xs">Categoria</th>
										<th class="col-md-2 col-sm-2 col-xs-6">Resultado</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="asistente" items="${asistentes }">
										<tr>
											<td>${asistente.legajo }</td>
											<td>${asistente.nombre }</td>
											<td class="hidden-xs">${asistente.local }</td>
											<td class="hidden-xs">${asistente.categoria }</td>
											<td><input class="form-control" name="resultado_${asistente.id }" type="number" required min="1" max="100"></td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</fieldset>
						<br>

						<div
							class="col-sm-6 col-xs-12 col-md-6 col-sm-offset-3 col-md-offset-3">
							<input type="submit" value="Calificar"
								class="btn btn-success btn-block">
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
		} else if ($(this).val() == 'ENVIAR') {

			$('.btn').removeClass('btn-info');
			$('.btn').removeClass('btn-success');
			$('.btn').addClass('btn-success');

			$('.btn').attr('value', 'Enviar Listado')
		}
	});
</script>
</html>