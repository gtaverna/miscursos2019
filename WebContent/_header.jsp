<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<head>
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<title>MisCursos</title>
<link rel="stylesheet" href="css/bootstrap.min.css">
<link rel="stylesheet" href="css/jquery-ui.min.css">
<script src="js/jquery-1.12.4.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/jquery-ui.min.js"></script>
<script src="js/jquery.dataTables.min.js"></script>
<link rel="stylesheet" href="css/dataTables.bootstrap.min.css">
<link rel="stylesheet" href="css/comun.css">
<script src="js/dataTables.bootstrap.min.js"></script>
<script src="js/dataTable.js"></script>

</head>


<div class="navbar navbar-default navbar-static-top">
	<div class="container">
		<div class="navbar-header">
		
			<button type="button" class="navbar-toggle" data-toggle="collapse"
				data-target="#navbar-ex-collapse">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
					
			</button>
			<a class="header pull-right navbar-text hidden-sm hidden-md hidden-lg MisAplicaciones"></a>
			

			<a class="navbar-brand" style="padding-top: 5px;">
			<img height="125%" alt="Brand"
				src="img/miscursoslogo.png"></a> <a class="navbar-brand"><span>MisCursos</span></a>
				
				
				
			
		</div>
		
		<div class="collapse navbar-collapse" id="navbar-ex-collapse">
			<ul class="nav navbar-left navbar-nav">
				<li><a href="Cursos">Cursos</a></li>
				<c:if test="${user.app_nivel == 0}">
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown" role="button" aria-expanded="false">Admin
							<i class="glyphicon glyphicon-menu-down"></i>
					</a>
						<ul class="dropdown-menu" role="menu">
							<li><a href="Nuevo">Crear Curso</a></li>
							<li class="divider"></li>
							<li><a href="Usuarios">Usuarios</a></li>
							<li><a href="GrupoTiendasList">Grupo Tienda</a></li>
							<li><a href="Grupos">Grupo Cursos</a></li>
						</ul></li>
				</c:if>
				<li><a href="Historial">Historial</a></li>


			</ul>
 				<p class="header-right navbar-right navbar-text hidden-xs" >
					<a class="navbar-link MisAplicacionesWhite"></a>
 				</p>
 				
			
			<p class="navbar-right navbar-text">
				<a href="Logout" class="navbar-link"><i
					class="glyphicon glyphicon-off" style="color: red"></i> Salir</a>

			</p>
			
			
			

			<form class="navbar-form navbar-right" role="search" action="Buscar"
				method="get">
				<div class="form-group">
					<input type="text" class="form-control" placeholder="Buscar..."
						name="busqueda">
				</div>
			</form>

		</div>
	</div>
	<script>$('.MisAplicacionesWhite').load('http://172.31.1.26:29086/misaplicaciones/Aplicaciones')</script>
</div>
<br><br><br>

