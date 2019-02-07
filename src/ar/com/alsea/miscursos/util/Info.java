package ar.com.alsea.miscursos.util;

public @interface Info {
	
	//permisos
	public static int PERM_ADMIN = 0;
	public static int PERM_TIENDA = 10;
	public static int PERM_INSTRUCTOR = 20;
	
	//curso
	public static String CursoPendienteTiendas = "PENDIENTE_TIENDAS";
	public static String CursoPendienteCalificacion = "PENDIENTE_CALIFICACION";
	public static String CursoConfirmado = "CONFIRMADO";
	public static String CursoBorrador = "PENDIENTE_LISTA";
	public static String CursoFinalizado = "FINALIZADO";
	public static String CursoCancelado = "CANCELADO";
	
	//curso-tienda
	public static String CursoTiendaNuevo = "NUEVO";
	public static String CursoTiendaBorrador = "BORRADOR";
	public static String CursoTiendaConfirmado = "CONFIRMADO";
	public static String CursoTiendaCancelado = "CANCELADO";
	public static String CursoTiendaFinalizado = "FINALIZADO";
	public static String CursoTiendaNoConfirmado = "NO_CONFIRMADO";
	
	//empleados asistencia
	public static String AlumnoPresente = "PRESENTE";
	public static String AlumnoAusente = "AUSENTE";
	public static String AlumnoLlegadaTarde = "TARDE";
	
	//empleados aprobacion
	public static String AlumnoDesaprobado = "DESAPROBADO";
	public static String AlumnoAprobado = "APROBADO";
	
}
