<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>

<jsp:include page="../_header.jsp"></jsp:include>
<script src="js/accordion.js"></script>
<body>


	<div class="container-fluid">

		<div class="row col-md-10  col-sm-12 col-xs-12 col-md-offset-1">
			<fieldset>
				<legend class="col-md-12 col-sm-12 col-xs-12"> Historial de Cursos </legend>

				<div class="col-md-4 col-sm-4">
					<label>Filtrar estado</label> <select class="form-control"
						name="filter" id="filter" data-type="accordion-filter">
						<option value="default">Todos</option>
						<option value="CONFIRMADO">Confirmado</option>
						<option value="BORRADOR">Borrador</option>
						<option value="PENDIENTE_TIENDAS">Pendiente Confirmar</option>
						<option value="PENDIENTE_LISTA">Pendiente Lista</option>
						<option value="NUEVO">Nuevo</option>
						<option value="FINALIZADO">Finalizado</option>
						<option value="CANCELADO">Cancelado</option>
					</select>
				</div>
				<div class="col-md-4 col-sm-4">
					<label>Buscar</label> <input type="text" placeholder="Buscar..."
						name="search" id="search" data-type="accordion-search"
						class="form-control col-md-4 col-sm-4 pull-right">
				</div>
				
				<div id="btn-div" class="col-xs-6 col-md-2 col-sm-2"><label> Ordenar </label>
					<button type=button data-type="accordion-ordering" ordering="asc"
						class="btn btn-default btn-sm glyphicon glyphicon-sort-by-attributes  col-xs-12 col-md-12 col-sm-12"></button>
				</div>
				
				<div id="btn-div" class="col-xs-6 col-md-2 col-sm-2"><label>&nbsp;  </label>
					<button type=button data-type="accordion-ordering" ordering="desc"
						class="btn btn-default btn-sm glyphicon glyphicon-sort-by-attributes-alt fa-lg col-xs-12 col-md-12 col-sm-12"></button>
				</div>
			</fieldset>
		</div>
		<div class="row">


			<div class="col-sm-12 col-md-10 col-xs-12 col-md-offset-1">
				<section id="accordion">
					<br>
					<c:forEach var="curso" items="${cursos }">
						<div class="col-sm-12 col-xs-12 col-md-12" data-type="accordion-section" data-filter="${curso.estado }">
							<div class="col-sm-12 col-xs-12 col-md-12" onclick="changeicon(${curso.id })"
								data-type="accordion-section-title">
								
									<div class="col-sm-7 col-xs-12 col-md-8" style="font-size: 120%; font-weight: bold;"><i id="iexpand${curso.id }"
									class="indicator glyphicon glyphicon-plus-sign "
									style=""></i>&nbsp;&nbsp; Curso: ${curso.id } - ${curso.nombre }</div>
									<div class="hidden-sm hidden-lg hidden-md col-xs-12" style=" font-weight: bold;">Fecha: ${curso.fecha } </div>
									<div class="col-sm-3 hidden-xs col-md-3  text-right" style=" font-weight: bold;">Fecha: ${curso.fecha } </div>
									<c:choose>
									  <c:when  test="${curso.estado  == 'PENDIENTE_TIENDAS' }">
									   <div class="col-sm-2 col-xs-4 col-md-1 pull-right text-right label label-warning">P.Confirmar</div>
									  </c:when>
									  <c:when test="${curso.estado  == 'CONFIRMADO' }">
									    <div class="col-sm-2 col-xs-4 col-md-1 pull-right text-right label label-success">Confirmado</div>
									  </c:when>
									  <c:when test="${curso.estado  == 'BORRADOR' }">
									    <div class="col-sm-2 col-xs-4 col-md-1 pull-right text-right label label-default">Borrador</div>
									  </c:when>
									   <c:when test="${curso.estado  == 'FINALIZADO' }">
									    <div class="col-sm-2 col-xs-4 col-md-1 pull-right text-right label label-primary">Finalizado</div>
									  </c:when>
									   <c:when test="${curso.estado  == 'CANCELADO' }">
									    <div class="col-sm-2 col-xs-4 col-md-1 pull-right text-right label label-danger" style="background-color: RED;">Cancelado</div>
									  </c:when>
									  <c:when test="${curso.estado  == 'NUEVO' }">
									    <div class="col-sm-2 col-xs-4 col-md-1 pull-right text-right label label-danger" style="background-color: RED;">Nuevo</div>
									  </c:when>
									  <c:when test="${curso.estado  == 'PENDIENTE_LISTA' }">
											  <c:if test="${user.app_nivel == 20 }">
											    <div class="col-sm-2 col-xs-4 col-md-1 pull-right text-right label label-default">Borrador</div>
											  </c:if>
											  <c:if test="${user.app_nivel != 20 }">
											    <div class="col-sm-2 col-xs-4 col-md-1 pull-right text-right label label-warning">P.Calificar</div>
											  </c:if>
										</c:when>
									   <c:when test="${curso.estado  == 'PENDIENTE_CALIFICACION' }">
									    <div class="col-sm-2 col-xs-4 col-md-1 pull-right text-right label label-warning" style="background-color: purple;">P.Calificar</div>
									  </c:when>
									</c:choose>
									</div>
							<div class="accordion-content" data-type="accordion-section-body"
								style="display: none;">

								<div class="row">
									<div class="col-xs-12 col-sm-11 col-md-11">
										<div class="col-md-2 col-sm-2 col-xs-10">Correlativa:
											${curso.correlativa }
										</div>
										<div class=" col-xs-2 hidden-lg hidden-md hidden-sm text-center ">
											<a href="VerCurso?cursoid=${curso.id }" style="color: green; font-weight: bold" class="glyphicon glyphicon-eye-open pull-right" aria-hidden="true" ><br>Ver</a>
										</div>
										<div class="col-md-2  col-sm-2 col-xs-10">Presencia:
											${curso.presencia }</div>
										
										<div class="col-md-2  col-sm-2 col-xs-10">Aprobacion:
											${curso.aprobacion }</div>
										<div class="col-md-2  col-sm-2 col-xs-10">Grupo: ${curso.grupo }</div>
										<div class="col-md-4  col-sm-4  col-xs-10">Lugar: ${curso.lugar }</div>
									</div>
									<div class="col-md-1  col-sm-1 hidden-xs text-center ">
										<a href="VerCurso?cursoid=${curso.id }" style="color: green; font-weight: bold" class="glyphicon glyphicon-eye-open pull-right" aria-hidden="true" ><br>Ver</a>
									</div>

								</div>

							</div>

						</div>

					</c:forEach>

				</section>
			</div>
		</div>

	</div>

	<script>
	$(document).ready(function() {
		$("#accordion").accordion();
	});
	
	function changeicon(id) {
		$('#iexpand'+id).toggleClass("glyphicon-plus-sign").toggleClass(
				"glyphicon-minus-sign");
		
		if ($(".glyphicon-minus-sign")[0]){
		    $('.glyphicon-minus-sign').not('#iexpand'+id).removeClass("glyphicon-minus-sign").addClass(
			"glyphicon-plus-sign");
		} 
	}
		
	</script>

</body>

</html>