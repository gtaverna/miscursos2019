<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>

<jsp:include page="../_header.jsp"></jsp:include>
<body>



	<div class="container-fluid">
		<div class="col-sm-10 col-xs-12 col-sm-offset-1">
			<form role="form" action="EditarGrupo" method="post">
				<fieldset>

					<legend class="col-sm-12 col-xs-12 col-md-12">Grupos -
						Editando Grupo</legend>
					<div class="form-group col-xs-12 col-md-8 col-md-offset-2">
						<label class="control-label">Id</label> <input
							class="form-control" name="id" value="${grupo.id }" required
							type="text" readonly>
					</div>
					<div class="form-group col-xs-12 col-md-8 col-md-offset-2">
						<label class="control-label">Nombre</label> <input
							class="form-control" name="nombre" required
							value="${grupo.nombre }" type="text">
					</div>
					<div class="form-group col-xs-12 col-md-8 col-md-offset-2">
						<div class="form-group">
						  <label for="sel1">Estado</label>
						  <select class="form-control" name="activo">
							<c:choose>
								<c:when test="${grupo.activo == 'true' }">
									<option>Activo</option>
									<option>Inactivo</option>
								</c:when>
								<c:when test="${grupo.activo == 'false' }">
									<option>Inactivo</option>
									<option>Activo</option>
								</c:when>
							</c:choose>
						  </select>
						</div>
					</div>
					
					<div class="form-group  col-md-8 col-md-offset-2 col-xs-12">
						<button type="submit" class="btn btn-block btn-success">Guardar</button>
					</div>

				</fieldset>
			</form>

		</div>

	</div>

</body>
</html>