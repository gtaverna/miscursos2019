<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<jsp:include page="_header.jsp"></jsp:include>
<body>

	<div class="container-fluid">
		<div class="row col-md-10  col-sm-10 col-xs-12 col-md-offset-1 col-sm-offset-1">
			<fieldset>
				<legend>Exito</legend>
				<c:if test="${exito != null}">
					<div class="row col-sm-12   col-md-12">
						<h3>
							<i class="glyphicon glyphicon-thumbs-up" style="color: #286296;"></i>&nbsp;&nbsp;${exito }
						</h3>
						<div class="row col-sm-6  col-xs-12 col-md-6">
							<br> <a href="Cursos"><input
								class="btn btn-success btn-block " value="Volver"></a>
						</div>
					</div>

					<br>
					<br>
					<br>
					<br>
				</c:if>




			</fieldset>
		</div>
	</div>
</body>
</html>