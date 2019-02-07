<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<title>Mis Cursos</title>
</head>
<body>

	<jsp:include page="../_header.jsp"></jsp:include>


	<div class="container">
		<div class="row">
			<div class="col-md-12 text-left">
				<div class="page-header">
					<h3>
						Usuarios <small>Editar</small>
					</h3>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-md-4"></div>
			<div class="col-md-4">
				<form role="form" action="EditarUsuario" method="post">
					<div class="form-group">
						<label class="control-label">Id</label> <input
							class="form-control" name="id" value="${usuario.id }" required type="text" readonly>
					</div>
					<div class="form-group">
						<label class="control-label">Usuario</label> <input
							class="form-control" name="usuario" value="${usuario.nombre }" required type="text">
					</div>
					<div class="form-group">
						<label class="control-label">Email</label> <input
							class="form-control" name="email" value="${usuario.email }" required type="email">
					</div>
					<div class="form-group">
						<label class="control-label">Telefono</label> 
						<input class="form-control" name="telefono" value="${usuario.telefono }" required type="text">
					</div>
					<div class="form-group">
						<label class="control-label">Marca</label> 
						<input class="form-control" name="marca" value="${usuario.marca }" required type="text" readonly>
					</div>
					<div class="form-group">
						<label class="control-label">Tipo de usuario</label> <select
							class="form-control" name="nivel" required>
							 <option value="20" <c:if test="${usuario.app_nivel == 20}">selected</c:if> >Instructor</option>
							 <option value="10" <c:if test="${usuario.app_nivel == 10}">selected</c:if> >Tienda</option>
							 <option value="0"  <c:if test="${usuario.app_nivel == 0}">selected</c:if> >Administrador</option>
							 </select>
					</div>
					<button type="submit" class="btn btn-primary">Guardar</button>
					<a href="javascript:window.history.back();" class="btn btn-default">Volver</a>
				</form>
			</div>
			<div class="col-md-4"></div>
		</div>
	</div>


</body>
</html>