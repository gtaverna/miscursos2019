<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>

	<jsp:include page="../_header.jsp"></jsp:include>
<body>


	<div class="container-fluid">
		<div class="col-sm-10 col-xs-12 col-sm-offset-1">

			<fieldset>
				<legend class="col-sm-12 col-xs-12 col-md-12">Grupos</legend>
				<div class="col-md-12">
					<table id="tabla" class="table hidden-xs">
						<thead>
							<tr>
								<th class="col-md-2 col-sm-2">Id</th>
								<th class="col-md-2 col-sm-2">Estado</th>
								<th class="col-md-7 col-sm-7">Nombre</th>
								<th class="col-md-1 col-sm-1"><a
									class="btn btn-default" href="NuevoGrupo"
									style="font-size: 75%;"><i class="glyphicon glyphicon-plus"></i>
										Nuevo grupo</a></th>


							</tr>
						</thead>
						<tbody>
							<c:forEach var="grupo" items="${grupos }">
								<tr>
									<td>${grupo.id }</td>
									<c:choose>
										<c:when test="${grupo.activo == 'true' }">
											<td>Activo</td>
										</c:when>
										<c:when test="${grupo.activo == 'false' }">
											<td>Inactivo</td>
										</c:when>
										<c:otherwise>
											<td>${grupo.activo }</td>
										</c:otherwise>
									</c:choose>
									<td>${grupo.nombre }</td>
									<td><a href="EditarGrupo?grupoid=${grupo.id }"><i
											class="glyphicon glyphicon-pencil"></i> Editar</a></td>
									
								</tr>
							</c:forEach>

						</tbody>
					</table>
					<table id="tabla" class="table visible-xs">
						<thead>
							<tr>
								<th class="col-md-1 col-sm-1">Id</th>
								<th class="col-md-2 col-sm-2">Estado</th>
								<th class="col-md-7 col-sm-7">Nombre</th>
								<th class="col-md-2 col-sm-2"><a
									class="btn btn-default" href="NuevoGrupo"
									style="font-size: 75%;"><i class="glyphicon glyphicon-plus"></i>
										</a></th>


							</tr>
						</thead>
						<tbody>
							<c:forEach var="grupo" items="${grupos }">
								<tr>
									<td>${grupo.id }</td>
									<c:choose>
										<c:when test="${grupo.activo == 'true' }">
											<td>Activo</td>
										</c:when>
										<c:when test="${grupo.activo == 'false' }">
											<td>Inactivo</td>
										</c:when>
										<c:otherwise>
											<td>${grupo.activo }</td>
										</c:otherwise>
									</c:choose>
									<td>${grupo.nombre }</td>
									<td><a href="EditarGrupo?grupoid=${grupo.id }"><i
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
$(document).ready(function(){
    if($(document).width() < 620){            
            $(".flexy-menu").flexymenu({
                speed: 400,type: "vertical", indicator: false
            });    
    }
 });
</script>
</html>