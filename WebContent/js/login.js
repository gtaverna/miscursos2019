function validarcampos() {

	if ($('#username').val() == "") {
		alert('vacio');
		return;
	} else if ($('#userpass').val() == "") {
		alert('campo vacio');
		return;
	}

	$.ajax({
		method : "GET",
		url : "Login",
		data : {
			username : $('#username').val(),
			userpass : $('#userpass').val()
		}
	});

}