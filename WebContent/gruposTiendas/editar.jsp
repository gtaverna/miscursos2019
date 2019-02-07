<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>

<jsp:include page="../_header.jsp"></jsp:include>
<link rel="stylesheet" href="css/bootstrap-duallistbox.css">
<script src="js/jquery.bootstrap-duallistbox.js"></script>
<body>
<div class="section">
		<div class="container">
			<div class="row">
				<div class="col-md-12">
					<h4 class="text-center">
						Editando Grupo de tiendas - ${grupo.descripcion }
					</h4>
					<hr>
				</div>
			</div>
			<div class="row">
				<div class="col-md-1"></div>
				<div class="col-md-8">
					<form id="form" method="post" class="form-horizontal" action="EditarGrupoTienda">
						<div class="form-group">
							<div class="col-sm-3">
								<label class="control-label">Id</label>
							</div>
							<div class="col-sm-9">
								<input type="text" name="id" class="form-control" value="${grupo.id }" 
								placeholder="Id" readonly>
							</div>
						</div>
						<div class="form-group">
							<div class="col-sm-3">
								<label class="control-label">Nombre del grupo</label>
							</div>
							<div class="col-sm-9">
								<input type="text" name="grupo" class="form-control" value="${grupo.descripcion }" 
								placeholder="Grupo" required>
							</div>
						</div>
						<div class="form-group">
							<div class="col-sm-3">
								<label class="control-label">Marca</label>
							</div>
							<div class="col-sm-9">
								<input type="text" name="marca" class="form-control" value="${grupo.marca }" 
									placeholder="Marca" required readonly>
							</div>
						</div>
						
						
						<div class="form-group">
							<div class="col-sm-3">
								<label class="control-label">Tiendas</label>
							</div>
							<div class="col-sm-9">
								<div id="demoform">
									<select multiple="multiple" size="10"
										name="tiendas" id="listadotiendas">
										<c:forEach var="tienda" items="${tiendas }">
										<option value="${tienda.id }" <c:if test="${tienda.seleccionado }">selected</c:if> >${tienda.nombre }</option>
									</c:forEach>
									</select> 
									
								</div>
								<script>
									var demo1 = $(
											'select[name="tiendas"]')
											.bootstrapDualListbox();
									$("#demoform")
											.submit(
													function() {
														alert($(
																'[name="tiendas"]')
																.val());
														return false;
													});
								</script>
							</div>
						</div>
						<div class="form-group">
						<div class="col-sm-3"></div>
							<div class="col-xs-12 col-sm-9">
								<button id="confirmar" type="submit" class="btn btn-block btn-success"  onclick="myFunction()">Guardar</button>
							</div>
						</div>
					</form>

				</div>

			</div>
		</div>
	</div>

</body>
<script>
function myFunction() {
	if( !$('#listadotiendas').val() ) { 
    
    var r = confirm("El grupo sera borrado por no tener tiendas. ¿Desea continuar?");
    
    
	    if (r == true) {
	    	$('form').unbind('submit').submit();
	    } else {
	    	$("form").submit(function(e){
	            e.preventDefault();
	        });
	    }
	}
}
</script>
</html>