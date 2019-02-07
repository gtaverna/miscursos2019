<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<jsp:include page="_header.jsp"></jsp:include>
<body>

	<div class="container-fluid">
		<div class="row col-md-10  col-sm-10 col-xs-12 col-md-offset-1 col-sm-offset-1">
			<fieldset>
				<legend>Ups..algo ocurrió</legend>
				<c:if test="${error != null}">
					<div class="row col-sm-12   col-md-12">
						<h3 class="text-error">
							<i class="glyphicon glyphicon-thumbs-down" style="color: #D9534F;"></i>&nbsp;&nbsp;${error }
						</h3>
						<div class="row col-sm-5  col-xs-5 col-md-5">
							<br> <a onclick="goBack()"><input
								class="btn btn-danger btn-block " value="Volver"></a>
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
<script>
	function goBack() {
		window.history.back();
	}
</script>
</html>