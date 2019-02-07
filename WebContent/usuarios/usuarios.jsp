<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>

<jsp:include page="../_header.jsp"></jsp:include>
<body>


	<div class="container-fluid">
		<div class="col-sm-10 col-xs-12 col-sm-offset-1">

			<fieldset>
				<legend class="col-sm-12 col-xs-12 col-md-12">Usuarios</legend>
				<div class="col-md-12">
					<table id="tabla" class="table hidden-xs">
						<thead>
							<tr>
								<th class="col-md-1 col-sm-1">Id</th>
								<th class="col-md-3 col-sm-3">Nombre</th>
								<th class="col-md-3 col-sm-3">Email</th>
								<th class="col-md-2 col-sm-2">Telefono</th>
								<th class="col-md-1 col-sm-1">Tipo</th>
								<th class="col-md-1 col-sm-1">Marca</th>

								<th class="col-md-1 col-sm-1"><a class="btn btn-default"
									href="NuevoUsuario" style="font-size: 75%;"><i
										class="glyphicon glyphicon-plus"></i> Nuevo Usuario</a></th>


							</tr>
						</thead>
						<tbody>
							<c:forEach var="usuario" items="${usuarios }">
								<tr>
									<td>${usuario.id }</td>
									<td>${usuario.nombre }</td>
									<td>${usuario.email }</td>
									<td>${usuario.telefono }</td>
									<td><c:if test="${usuario.app_nivel == 20}">Instructor</c:if>
										<c:if test="${usuario.app_nivel == 10}">Tienda</c:if> <c:if
											test="${usuario.app_nivel == 0}">Admin</c:if></td>
									<td>${usuario.marca }</td>
									<td><a href="EditarUsuario?id=${usuario.id }"><i
											class="glyphicon glyphicon-pencil"></i> Editar</a></td>

								</tr>
							</c:forEach>

						</tbody>
					</table>
					<table id="tabla" class="table visible-xs">
						<thead>
							<tr>
								<th class="col-md-1 col-sm-1">Id</th>
								<th class="col-md-4 col-sm-4">Nombre</th>
								<th class="col-md-3 col-sm-3">Email</th>
								<th class="col-md-1 col-sm-1">Tipo</th>
								<th class="col-md-1 col-sm-1">Marca</th>
								<th class="col-md-2 col-sm-2"><a class="btn btn-default"
									href="NuevoUsuario" style="font-size: 75%;"><i
										class="glyphicon glyphicon-plus"></i> </a></th>


							</tr>
						</thead>
						<tbody>
							<c:forEach var="usuario" items="${usuarios }">
								<tr>
									<td>${usuario.id }</td>
									<td>${usuario.nombre }</td>
									<td>${usuario.email }</td>
									<td><c:if test="${usuario.app_nivel == 20}">Instructor</c:if>
										<c:if test="${usuario.app_nivel == 10}">Tienda</c:if> <c:if
											test="${usuario.app_nivel == 0}">Admin</c:if></td>
									<td>${usuario.marca }</td>
									<td><a href="EditarUsuario?id=${usuario.id }"><i
											class="glyphicon glyphicon-pencil"></i></a></td>

								</tr>
							</c:forEach>

						</tbody>
					</table>
				</div>
			</fieldset>
		</div>
	</div>

</body>
<script>
	$(document).ready(function() {
		if ($(document).width() < 620) {
			$(".flexy-menu").flexymenu({
				speed : 400,
				type : "vertical",
				indicator : false
			});
		}
	});
</script>
</html>