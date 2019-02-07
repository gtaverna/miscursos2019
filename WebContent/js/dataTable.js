$(document).ready(function() {
		$('#tabla').DataTable({
			"bPaginate" : false,
			"fnInfoCallback": false,
			"responsive": true,
			"oLanguage" : {
				"sSearch" : "Buscar"
			}
		});
		$('.dataTables_length').hide();
	});