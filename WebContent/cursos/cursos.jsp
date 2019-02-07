<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>

<jsp:include page="../_header.jsp"></jsp:include>

<script src="js/accordion.js"></script>


<body>



	<div class="container-fluid">

		<div class="row col-md-10  col-sm-12 col-xs-12 col-md-offset-1">
			<fieldset>
				<legend class="col-md-12 col-sm-12 col-xs-12"> Cursos
					Activos </legend>

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

				<div id="btn-div" class="col-xs-6 col-md-2 col-sm-2">
					<label> Ordenar </label>
					<button type=button data-type="accordion-ordering" ordering="asc"
						class="btn btn-default btn-sm glyphicon glyphicon-sort-by-attributes  col-xs-12 col-md-12 col-sm-12"></button>
				</div>

				<div id="btn-div" class="col-xs-6 col-md-2 col-sm-2">
					<label>&nbsp; </label>
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
						<div class="col-sm-12 col-xs-12 col-md-12"
							data-type="accordion-section" data-filter="${curso.estado }">
							<div class="col-sm-12 col-xs-12 col-md-12"
								onclick="changeicon(${curso.id })"
								data-type="accordion-section-title">

								<div class="col-sm-6 col-xs-12 col-md-6"
									style="font-size: 120%; font-weight: bold;">
									<i id="iexpand${curso.id }"
										class="indicator glyphicon glyphicon-plus-sign " style=""></i>&nbsp;&nbsp;
									Curso: ${curso.id } - ${curso.nombre }
								</div>
								<div class="col-sm-4 hidden-xs col-md-5  text-right"
									style="font-weight: bold;">Fecha: ${curso.fecha }</div>
								<div class="hidden-sm hidden-lg hidden-md col-xs-12"
									style="font-weight: bold;">Fecha: ${curso.fecha }</div>
								<c:choose>
									<c:when test="${curso.estado  == 'PENDIENTE_TIENDAS' }">
										<div
											class="col-sm-2 col-xs-4 col-md-1 pull-right text-right label label-warning">P.Confirmar</div>
									</c:when>
									<c:when test="${curso.estado  == 'CONFIRMADO' }">
										<div
											class="col-sm-2 col-xs-4 col-md-1 pull-right text-right label label-success">Confirmado</div>
									</c:when>
									<c:when test="${curso.estado  == 'BORRADOR' }">
										<div
											class="col-sm-2 col-xs-4 col-md-1 pull-right text-right label label-default">Borrador</div>
									</c:when>
									<c:when test="${curso.estado  == 'FINALIZADO' }">
										<div
											class="col-sm-2 col-xs-4 col-md-1 pull-right text-right label label-primary">Finalizado</div>
									</c:when>
									<c:when test="${curso.estado  == 'FINALIZADO' }">
										<div
											class="col-sm-2 col-xs-4 col-md-1 pull-right text-right label label-danger">CANCELADO</div>
									</c:when>
									<c:when test="${curso.estado  == 'PENDIENTE_LISTA' }">
										<div
											class="col-sm-2 col-xs-4 col-md-1 pull-right text-right label label-warning">P.Lista</div>
									</c:when>
									<c:when test="${curso.estado  == 'PENDIENTE_CALIFICACION' }">
										<div
											class="col-sm-2 col-xs-4 col-md-1 pull-right text-right label label-warning"
											style="background-color: purple;">P.Calificar</div>
									</c:when>
									<c:otherwise>
										<div
											class="col-sm-2 col-xs-4 col-md-1 pull-right text-right label label-warning"
											style="background-color: RED;">${curso.estado}</div>
									</c:otherwise>
								</c:choose>
							</div>

							<div class="accordion-content" data-type="accordion-section-body"
								style="display: none;">

								<div class="row">
									<div class="col-xs-12 col-sm-10 col-md-10">
										<div class="col-md-2 col-sm-2 col-xs-6">
											<c:if test="${curso.nombre_grupo_correlativa != NULL }">
											G.Correlativa:
												${curso.nombre_grupo_correlativa }
											</c:if>
											
											<c:if test="${curso.nombre_correlativa != NULL }">
											 Correlativa:
												${curso.nombre_correlativa }
											</c:if>	
										</div>
									<div class="col-xs-6 hidden-md hidden-lg hidden-sm"  style="padding-left: 10px;">
										<c:if test="${user.app_nivel == 0 }">
											<c:if test="${curso.estado != 'CANCELADO' }">
											
												<div class="col-md-6 col-xs-6 text-center " style="padding-left: 10px;">

													<a class="glyphicon glyphicon-edit" aria-hidden="true"
														style="color: orange; font-weight: bold"
														href="Editar?cursoid=${curso.id }"><br>Editar</a>
												</div>
												<div class="col-md-6 col-xs-6 text-center" style="padding-left: 10px;">
													<a class="glyphicon glyphicon-remove" aria-hidden="true"
														style="color: red; font-weight: bold; cursor: pointer"
														data-toggle="modal" data-target="#myModal"><br>Cancelar</a>
												</div>
												
											</c:if>
											<c:if test="${curso.estado == 'CANCELADO' }">
											
												<div class="col-md-2 col-xs-6 text-center pull-right">
														<a class="glyphicon glyphicon-eye-open"
															style="color: green; font-weight: bold"
															href="VerCurso?cursoid=${curso.id }"><br>Ver</a>
													</div>
												
											</c:if>
											
										</c:if>
										
										
										<c:if test="${user.app_nivel == 10 }">
											<c:choose>
												<c:when test="${curso.estado =='NUEVO' }">
													<div class="col-md-2 col-xs-6 text-center pull-right">
														<a class="glyphicon glyphicon-ok-circle"
															style="color: orange; font-weight: bold"
															href="Aceptar?cursoid=${curso.id }"><br>Aceptar</a>
													</div>

												</c:when>
												<c:when test="${curso.estado =='BORRADOR' }">
													<div class="col-md-2 col-xs-6 text-center pull-right">
														<a class="glyphicon glyphicon-ok-circle"
															style="color: orange; font-weight: bold"
															href="Aceptar?cursoid=${curso.id }"><br>Continuar</a>
													</div>
												</c:when>
												<c:otherwise>
													<div class="col-md-2 col-xs-6 text-center pull-right">
														<a class="glyphicon glyphicon-eye-open"
															style="color: green; font-weight: bold"
															href="VerCurso?cursoid=${curso.id }"><br>Ver</a>
													</div>
												</c:otherwise>
											</c:choose>
										</c:if>
										<c:if test="${user.app_nivel == 20 }">
											<c:choose>
												<c:when test="${curso.estado =='PENDIENTE_TIENDAS' }">
													<div class="col-md-6 col-xs-6 text-center " style="padding-left: 10px;">

														<a class="glyphicon glyphicon-eye-open"
															style="color: green; font-weight: bold"
															href="VerCurso?cursoid=${curso.id }"><br>Ver</a>
													</div>
													<div class="col-md-6 col-xs-6 text-center" style="padding-left: 10px;">
														<a class="glyphicon glyphicon-envelope"
															style="color: #3879D9; font-weight: bold"
															href="Recordatorio?cursoid=${curso.id }"><br>Notificar</a>
													</div>
												</c:when>
												<c:when test="${curso.estado =='CONFIRMADO' }">
													<div class="col-md-2 col-xs-6 text-center pull-right">
														<a class="glyphicon glyphicon-check"
															style="color: purple; font-weight: bold"
															href="Tomarlista?cursoid=${curso.id }"><br>Lista</a>
													</div>
												</c:when>
												<c:when test="${curso.estado =='BORRADOR' }">
													<div class="col-md-2 col-xs-6 text-center pull-right">
														<a class="glyphicon glyphicon-list-alt"
															style="color: purple; font-weight: bold"
															href="Tomarlista?cursoid=${curso.id }"><br>Lista</a>
													</div>
												</c:when>
												<c:when test="${curso.estado =='PENDIENTE_CALIFICACION' }">
													<div class="col-md-2 col-xs-6 text-center pull-right">
														<a class="glyphicon glyphicon-flash"
															style="color: purple; font-weight: bold"
															href="Calificar?cursoid=${curso.id }"><br>Calificar</a>
													</div>
												</c:when>
												<c:when test="${curso.estado =='PENDIENTE_LISTA' }">
													<div class="col-md-2 col-xs-6 text-center pull-right">
														<a class="glyphicon glyphicon-flash"
															style="color: purple; font-weight: bold"
															href="Tomarlista?cursoid=${curso.id }"><br>Lista</a>
													</div>
												</c:when>
												<c:otherwise>
													<div class="col-md-2 col-xs-6 text-center pull-right">
														<a class="glyphicon glyphicon-eye-open"
															style="color: green; font-weight: bold"
															href="VerCurso?cursoid=${curso.id }"><br>ver</a>
													</div>
												</c:otherwise>
											</c:choose>
										</c:if>
										</div>
										
										<div class="col-md-2 col-sm-2 col-xs-10">
											<c:if test="${curso.nombre_presencia != NULL }">
											Presencia:
											${curso.nombre_presencia }
											</c:if>
											
											<c:if test="${curso.nombre_grupo_presencia != NULL }">
											G.Presencia:
											${curso.nombre_grupo_presencia }
											</c:if>
											
											</div>
										<div class="col-md-2 col-sm-2 col-xs-10">Aprobacion:
											${curso.aprobacion }</div>
										<div class="col-md-2 col-sm-2 col-xs-10">Grupo:
											${curso.grupo }</div>
										<div class="col-md-4 col-sm-4 col-xs-10">Lugar:
											${curso.lugar }</div>
									</div>
									<div class="col-md-2 col-sm-2 hidden-xs"  style="padding-left: 0px;">
										<c:if test="${user.app_nivel == 0 }">
											<c:if test="${curso.estado != 'CANCELADO' }">
											
												<div class="col-md-6 col-xs-6 text-center " style="padding-left: 10px;">

													<a class="glyphicon glyphicon-edit" aria-hidden="true"
														style="color: orange; font-weight: bold"
														href="Editar?cursoid=${curso.id }"><br>Editar</a>
												</div>
												<div class="col-md-6 col-xs-6 text-center" style="padding-left: 10px;">
													<a class="glyphicon glyphicon-remove" aria-hidden="true"
														style="color: red; font-weight: bold; cursor: pointer"
														 data-toggle="modal" onclick="setCancel(${curso.id })" data-target="#myModal"><br>Cancelar</a>
												</div>
												
											</c:if>
											
											
											<c:if test="${curso.estado == 'CANCELADO' }">
											
												<div class="col-md-2 col-xs-6 text-center pull-right">
														<a class="glyphicon glyphicon-eye-open"
															style="color: green; font-weight: bold"
															href="VerCurso?cursoid=${curso.id }"><br>Ver</a>
													</div>
												
											</c:if>
										</c:if>
										
										<c:if test="${user.app_nivel == 10 }">
											<c:choose>
												<c:when test="${curso.estado =='NUEVO' }">
													<div class="col-md-2 col-xs-6 text-center pull-right" style="padding-left: 0px;padding-right: 45px;">
														<a class="glyphicon glyphicon-ok-circle"
															style="color: orange; font-weight: bold"
															href="Aceptar?cursoid=${curso.id }"><br>Aceptar</a>
													</div>

												</c:when>
												<c:when test="${curso.estado =='BORRADOR' }">
													<div class="col-md-2 col-xs-6 text-center pull-right" style="padding-left: 0px;padding-right: 55px;">
														<a class="glyphicon glyphicon-ok-circle"
															style="color: orange; font-weight: bold"
															href="Aceptar?cursoid=${curso.id }"><br>Continuar</a>
													</div>
												</c:when>
												<c:otherwise>
													<div class="col-md-2 col-xs-6 text-center pull-right" style="padding-left: 0px;padding-right: 25px;">
														<a class="glyphicon glyphicon-eye-open"
															style="color: green; font-weight: bold"
															href="VerCurso?cursoid=${curso.id }"><br>Ver</a>
													</div>
												</c:otherwise>
											</c:choose>
										</c:if>
										<c:if test="${user.app_nivel == 20 }">
											<c:choose>
												<c:when test="${curso.estado =='PENDIENTE_TIENDAS' }">
													
													<div class="col-md-6 col-xs-6 text-center " style="padding-left: 10px;">

														<a class="glyphicon glyphicon-eye-open"
															style="color: green; font-weight: bold"
															href="VerCurso?cursoid=${curso.id }"><br>Ver</a>
													</div>
													<div class="col-md-6 col-xs-6 text-center" style="padding-left: 10px;">
														<a class="glyphicon glyphicon-envelope"
															style="color: #3879D9; font-weight: bold"
															 data-toggle="modal" onclick="enviarRecordatorio(${curso.id })"data-target="#modalRecordatorio"><br>Notificar</a>
													</div>
												</c:when>
												<c:when test="${curso.estado =='CONFIRMADO' }">
													<div class="col-md-2 col-xs-6 text-center pull-right" style="padding-left: 0px;padding-right: 45px;">
														<a class="glyphicon glyphicon-check"
															style="color: purple; font-weight: bold"
															href="Tomarlista?cursoid=${curso.id }"><br>Lista</a>
													</div>
												</c:when>
												<c:when test="${curso.estado =='BORRADOR' }">
													<div class="col-md-2 col-xs-6 text-center pull-right" style="padding-left: 0px;padding-right: 45px;">
														<a class="glyphicon glyphicon-list-alt"
															style="color: purple; font-weight: bold"
															href="Tomarlista?cursoid=${curso.id }"><br>Lista</a>
													</div>
												</c:when>
												<c:when test="${curso.estado =='PENDIENTE_CALIFICACION' }">
													<div class="col-md-2 col-xs-6 text-center pull-right" style="padding-left: 0px;padding-right: 45px;">
														<a class="glyphicon glyphicon-flash"
															style="color: purple; font-weight: bold"
															href="Calificar?cursoid=${curso.id }"><br>Calificar</a>
													</div>
												</c:when>
												<c:when test="${curso.estado =='PENDIENTE_LISTA' }">
													<div class="col-md-2 col-xs-6 text-center pull-right" style="padding-left: 0px;padding-right: 45px;">
														<a class="glyphicon glyphicon-flash"
															style="color: purple; font-weight: bold"
															href="Tomarlista?cursoid=${curso.id }"><br>Lista</a>
													</div>
												</c:when>
												<c:otherwise>
													<div class="col-md-2 col-xs-6 text-center pull-right" style="padding-left: 0px;padding-right: 45px;">
														<a class="glyphicon glyphicon-eye-open"
															style="color: green; font-weight: bold"
															href="VerCurso?cursoid=${curso.id }"><br>ver</a>
													</div>
												</c:otherwise>
											</c:choose>
										</c:if>
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
		

		$('#menuCursos').addClass('active');
		
		function setCancel(id){
			$('#cursoid').attr('value',id)
		}

		
		function enviarRecordatorio(id){
			$.ajax({
				method : "GET",
				url : "Recordatorio?cursoid="+id,
				statusCode: {
				   200: function() {
				      $('#estado').text('Notificaciones enviadas con exito!')
				    },
				    500: function() {
					      $('#estado').text('ERROR: No se pudo enviar notificacion')
					    }
				  }
			});
			
		}
	</script>
<div class="container">
  
  <!-- Modal -->
  <div class="modal fade" id="myModal" role="dialog">
    <div class="modal-dialog">
    <form action="Cancelar" method="get">
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4 class="modal-title">¿Esta seguro que desea cancelar el curso?</h4>
        </div>
        <div class="modal-body">
          <p>Elija un motivo de cancelacion</p>
       
	        <select  class="form-control" name="motivo">
	        	<option>Cambio de fecha</option>
				<option>Cambio de instructor</option>
				<option>Cancelado por clima</option>
				<option>Cancelado por feriado</option>
				<option>Otros</option>
	        </select>
        </div>
        <div class="modal-footer">
        	<input type="hidden" id="cursoid" name="cursoid">	
        	<button type="submit" class="btn btn-default" >SI</button>
            <button type="reset" class="btn btn-default" data-dismiss="modal">NO</button>
        </div>
      </div>
      </form>
    </div>
  </div>
  
</div>

<div class="container">
  
  <!-- Modal -->
  <div class="modal fade" id="modalRecordatorio" role="dialog">
    <div class="modal-dialog">
    
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4 class="modal-title">Enviando Notificaciones</h4>
        </div>
        <div class="modal-body">
          <p id="estado" style="font-size: 150%">Enviando...
       		 <span class="glyphicon glyphicon-refresh glyphicon-refresh-animate"></span></p>
	       
        </div>
        <div class="modal-footer">
        	<input type="hidden" id="cursoidRecordatorio" name="cursoidRecordatorio">	
        	<button class="btn btn-default" data-dismiss="modal">Cerrar</button>
        </div>
      </div>
    </div>
  </div>
  
</div>

</body>


</html>