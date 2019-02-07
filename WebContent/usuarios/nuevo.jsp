<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<title>Mis Cursos</title>
<link rel="stylesheet" href="../css/comun.css">
</head>
<body>

	<jsp:include page="../_header.jsp"></jsp:include>

	<div class="container">
		<div class="row">
			<div class="col-md-12 text-left">
				<div class="page-header">
					<h3>
						Usuarios <small>Nuevo</small>
					</h3>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-md-4"></div>
			<div class="col-md-4">
				<form role="form" action="NuevoUsuario" method="post">
					<div class="form-group">
						<label class="control-label">Usuario</label> <input
							class="form-control" name="usuario" required type="text">
					</div>
					<div class="form-group">
						<label class="control-label">Email</label> <input
							class="form-control" name="email" required type="email">
					</div>
					<div class="form-group">
						<label class="control-label">Telefono</label> 
						<input class="form-control" name="telefono" required type="text">
					</div>
					<div class="form-group">
						<label class="control-label">Marca</label> 
						<select class="form-control" name="marca" required><option value="BK">BK</option>
						<option value="SBX">SBX</option>
						</select>		
					</div>
					<div class="form-group">
						<label class="control-label">Tipo de usuario</label> <select
							class="form-control" name="nivel" required>
							 <option value="20">Instructor</option>
							 <option value="10">Tienda</option>
							 <option value="0">Administrador</option>
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