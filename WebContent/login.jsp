<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>MisCursos</title>
<link rel="stylesheet" href="css/bootstrap.min.css">
<link rel="stylesheet" href="css/comun.css">
<script src="js/jquery-1.12.4.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/login.js"></script>

<link rel="icon" href="img/miscursoslogo.png" type="image/png">

</head>
<body>

	<div class="container-fluid">

		<div class="pull-right login MisAplicacionesBlack"></div>

		<div class="row">

			<div
				class="col-xs-12 col-sm-6 col-sm-offset-3 col-md-4 col-md-offset-4">
				<form action="Login" method="post">
					<div class="form-group text-center">
						<span> <img src="img/miscursoslogo.svg"
							class="img-responsive center-block" style="height: 250px"
							alt="Alsea">
						</span>

						<h2>MisCursos</h2>
					</div>

					<div class="form-group">
						<input type="text" id="username" class="form-control"
							name="username" placeholder="Usuário" required autofocus>
					</div>

					<div class="form-group">
						<input type="password" id="userpass" class="form-control"
							name="userpass" placeholder="Contraseña" required>
					</div>
					<div class="form-group">
						<button id="botoningresar" class="btn btn-block">Ingresar</button>
					</div>
				</form>
			</div>
		</div>
		<div class="row">
			<div class="col-xs-12 col-md-4 col-md-offset-4">
				<div class="row">
					<div class="col-xs-12">
						<img src="img/alsea.png" class="img-responsive center-block"
							alt="Alsea">
					</div>
				</div>
				<br> <br>
				<div class="row">
					<div class="col-xs-3">
						<img src="img/bk.png" class="img-responsive center-block"
							alt="Burger King">
					</div>
					<div class="col-xs-3">
						<img src="img/sb.png" class="img-responsive center-block"
							alt="Starbucks">
					</div>
					<div class="col-xs-6">
						<img src="img/pf.png" class="img-responsive center-block"
							alt="PF Changs">
					</div>
				</div>
			</div>
		</div>

		<c:if test="${invalida != null}">
			<div class="modal" tabindex="-1" role="dialog" id="myModal">
				<div class="modal-dialog modal-sm">
					<div class="modal-content">
						<div class="modal-body">${invalida}</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-default"
								data-dismiss="modal">Cerrar</button>
						</div>
					</div>
				</div>
			</div>
			<script>
				$('#myModal').modal('show');
			</script>
		</c:if>
	</div>

</body>

<script>
$('.MisAplicacionesBlack').load('http://172.31.1.26:29086/misaplicaciones/Aplicaciones')
</script>
</html>


