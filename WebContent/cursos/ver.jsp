<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<link rel="stylesheet" href="css/bootstrap-select.min.css">
<jsp:include page="../_header.jsp"></jsp:include>



<script src="js/bootstrap-select.min.js"></script>

<body>
	<div class="container-fluid">
		<form action="Editar" method="post" class="form-horizontal">
			<fieldset>
				<legend class="col-sm-12 col-xs-12 col-sm-offset-1">Curso ID: ${curso.id } - ${curso.estado }</legend>
					<input  type="hidden" name="id" value="${curso.id }" >
					<div class="col-sm-10 col-xs-12 col-sm-offset-1">	
					<jsp:include page="../headercurso.jsp"></jsp:include>
				</div>
				<div class="col-sm-10 col-xs-12 col-sm-offset-1">
				
					<div class="container-fluid col-sm-12">
						<hr>
						<div class="col-sm-12">
							<label for="tiendas" class="col-sm-12"><i class=" indicator glyphicon glyphicon-cutlery"></i> Tiendas</label>

							<div class="col-sm-12">
								<ul class="list-group  col-sm-offset-1">
									<c:forEach var="tienda" items="${tiendas }">
										<li class="list-group-item col-sm-3">${tienda[1] }-${tienda[2] } - 
											
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
						<div class="col-sm-12">
							<hr>
							<label for="instructores" class="col-sm-12"><i
								class="indicator glyphicon glyphicon-user"></i> Instructores
							</label>

							<ul class="list-group  col-sm-offset-1">
								<c:forEach var="instructor" items="${instructores }">
									<li class="list-group-item col-sm-3">${instructor[1] } - ${instructor[3] }</li>
								</c:forEach>
							</ul>
						</div>
						<div class="col-sm-12">
							<hr>
							<label for="categoria" class="col-sm-12"><i class=" indicator glyphicon glyphicon-education"></i> Categorias</label>

							<ul class="list-group  col-sm-offset-1">
								<c:forEach var="categoria" items="${categorias }">
									<li class="list-group-item col-sm-3">${categoria.nombre }</li>
								</c:forEach>
							</ul>
						</div>
						
					</div>
					<br>
					<c:if test="${curso.estado   != 'CANCELADO' }">
					<div class="col-sm-12 col-xs-12 col-md-12">
							<hr>
							<label for="empleados" class="col-sm-12 col-xs-12 col-md-12"><i class=" indicator glyphicon glyphicon-education"></i> Asistentes</label>

							<table id="tabla"class="table table-hover col-sm-12 col-xs-12 col-md-12">
								<thead>
									<tr>
										<th>Legajo</th>
										<th>Nombre</th>
										<th>Categoria</th>
										<th>N°Local</th>
										<th>Asistencia</th>
										<c:if test="${curso.aprobacion   != '0' }">
												<th>%Aprobacion</th>
												<th>Aprobacion</th>
										</c:if>
										
											
												
										
									</tr>

								</thead>
								<tbody>
								<c:forEach var="asistente" items="${asistentes }">
								<tr>
								<td>${asistente.legajo }</td>
								<td>${asistente.nombre }</td>
								<td>${asistente.categoria }</td>
								<td>${asistente.local }</td>
								
											<td><c:choose>
												<c:when
													test="${asistente.estado_asistencia  == 'PRESENTE' }">
													<b style="color: green;">P</b>
												</c:when>
												<c:when
													test="${asistente.estado_asistencia  == 'TARDE' }">
													<b style="color: orange;">T</b>
												</c:when>
												<c:when
													test="${asistente.estado_asistencia  == 'AUSENTE' }">
													<b style="color: RED;">A</b>
												</c:when>
											</c:choose></td>
											<c:if test="${curso.aprobacion   != '0' }">
												
											<td>${asistente.resultado }%</td>
											<td><c:choose>
											
												<c:when
													test="${asistente.estado_aprobacion  == 'APROBADO' }">
													<b style="color: green;" class="glyphicon glyphicon-ok"></b>
												</c:when>
												<c:when
													test="${asistente.estado_aprobacion  == 'DESAPROBADO' }">
													<b style="color: red;" class="glyphicon glyphicon-remove"></b>
												</c:when>
												<c:when
													test="${asistente.estado_asistencia  == 'AUSENTE' }">
													<b style="color: red;">A</b>
												</c:when>
											</c:choose></td>
										</c:if>
										</tr>
								</c:forEach>
								</tbody>
								
							</table>
						</div>
							</c:if>
					<div class="col-sm-12">
						<br>
						<div class="form-group">
							<hr>
							<label for="lugar" class="col-sm-2">Lugar</label>
							<div class="col-sm-8">
								<input id="lugar" type="text" class="form-control" name="lugar"
									 value="${curso.lugar }" readonly>
							</div>
						</div>
						
						<br> 
					</div>
					</div>
			</fieldset>
		</form>
	</div>
</body>
<script>
	$('#menuHistorial').addClass('active');

</script>

</html>