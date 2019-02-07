package ar.com.alsea.miscursos.util;

public @interface Querys {

	public static String dbProd = "USE miscursos ";
	public static String dbTest = "USE [miscursos-uat] ";
	public static String db = dbProd; // Test cambiar para DEV
	
	public static String alsea_usuariosTest = "[Alsea-UAT].dbo.Usuarios_aplicaciones";
	public static String alsea_usuariosProd = "Alsea.dbo.Usuarios_aplicaciones";
	public static String alsea_usuarios = alsea_usuariosProd; //UAT para dev

	public static String microsBKtiendas = "micros.dbo.bktiendas";
	public static String microsSBtiendas = "micros.dbo.sbxtiendas";

	
	
	// CursosDAO
	public static String TraerCurso = db+"SELECT c.*, g.nombre nombre_grupo, gp.nombre nombre_grupo_presencia, gc.nombre nombre_grupo_correlativa, cp.nombre nombre_presencia, cc.nombre nombre_correlativa, c.tolerancia FROM cursos c LEFT JOIN grupocurso g ON g.id = c.grupo LEFT JOIN grupocurso gp ON gp.id = c.grupopresencia LEFT JOIN grupocurso gc ON gc.id = c.grupoaprobacion LEFT JOIN cursos cp ON cp.id = c.presencia LEFT JOIN cursos cc ON cc.id = c.correlativa WHERE c.id = ?";
	public static String TraerCursoConEstadoPorTienda = db+"SELECT c.id, c.nombre, c.fecha, c.correlativa, c.presencia, c.lugar, c.aprobacion, c.grupo, c.grupoaprobacion, c.grupopresencia, c.marca, c.descripcion, tc.estado, g.nombre nombre_grupo, gp.nombre nombre_grupo_presencia, gc.nombre nombre_grupo_correlativa, cp.nombre nombre_presencia, cc.nombre nombre_correlativa, c.tolerancia FROM cursos c JOIN tienda_curso tc ON tc.id_curso = c.id LEFT JOIN grupocurso g ON g.id = c.grupo LEFT JOIN grupocurso gp ON gp.id = c.grupopresencia LEFT JOIN grupocurso gc ON gc.id = c.grupoaprobacion LEFT JOIN cursos cp ON cp.id = c.presencia LEFT JOIN cursos cc ON cc.id = c.correlativa WHERE tc.id_curso = ? AND tc.id_tienda = ?";

	public static String TraerCursosPorInstructor = db+"SELECT c.*, g.nombre nombre_grupo, gp.nombre nombre_grupo_presencia, gc.nombre nombre_grupo_correlativa, cp.nombre nombre_presencia, cc.nombre nombre_correlativa, c.tolerancia FROM cursos c JOIN instructor_curso ic ON ic.id_curso = c.id LEFT JOIN grupocurso g ON g.id = c.grupo LEFT JOIN grupocurso gp ON gp.id = c.grupopresencia LEFT JOIN grupocurso gc ON gc.id = c.grupoaprobacion LEFT JOIN cursos cp ON cp.id = c.presencia LEFT JOIN cursos cc ON cc.id = c.correlativa WHERE ic.id_instructor = ? AND c.estado not like 'FINALIZADO' and ( c.estado not like 'CANCELADO' OR c.fecha >= GETDATE() ) ORDER BY c.id";
	public static String TraerCursosFinalizadosPorInstructor = db+"SELECT top 100 c.*, g.nombre nombre_grupo, gp.nombre nombre_grupo_presencia, gc.nombre nombre_grupo_correlativa, cp.nombre nombre_presencia, cc.nombre nombre_correlativa, c.tolerancia FROM cursos c JOIN instructor_curso ic ON ic.id_curso = c.id LEFT JOIN grupocurso g ON g.id = c.grupo LEFT JOIN grupocurso gp ON gp.id = c.grupopresencia LEFT JOIN grupocurso gc ON gc.id = c.grupoaprobacion LEFT JOIN cursos cp ON cp.id = c.presencia LEFT JOIN cursos cc ON cc.id = c.correlativa WHERE ic.id_instructor = ? ORDER BY c.id";
	public static String TraerCursosPorTienda = db+"SELECT c.id, c.nombre, c.fecha, c.correlativa, c.presencia, c.lugar, c.aprobacion, c.grupo, c.grupoaprobacion, c.grupopresencia, c.marca, c.descripcion, tc.estado, g.nombre nombre_grupo, gp.nombre nombre_grupo_presencia, gc.nombre nombre_grupo_correlativa, cp.nombre nombre_presencia, cc.nombre nombre_correlativa, c.tolerancia FROM cursos c JOIN tienda_curso tc ON tc.id_curso = c.id LEFT JOIN grupocurso g ON g.id = c.grupo LEFT JOIN grupocurso gp ON gp.id = c.grupopresencia LEFT JOIN grupocurso gc ON gc.id = c.grupoaprobacion LEFT JOIN cursos cp ON cp.id = c.presencia LEFT JOIN cursos cc ON cc.id = c.correlativa WHERE tc.id_tienda = ? AND c.estado NOT LIKE 'FINALIZADO' AND ( c.estado NOT LIKE 'CANCELADO' OR c.fecha >= GETDATE() ) ORDER BY c.id";
	public static String TraerCursosFinalizadosPorTienda = db+"SELECT top 100 c.id, c.nombre, c.fecha, c.correlativa, c.presencia, c.lugar, c.aprobacion, c.grupo, c.grupoaprobacion, c.grupopresencia, c.marca, c.descripcion, tc.estado, g.nombre nombre_grupo, gp.nombre nombre_grupo_presencia, gc.nombre nombre_grupo_correlativa, cp.nombre nombre_presencia, cc.nombre nombre_correlativa, c.tolerancia FROM cursos c JOIN tienda_curso tc ON tc.id_curso = c.id LEFT JOIN grupocurso g ON g.id = c.grupo LEFT JOIN grupocurso gp ON gp.id = c.grupopresencia LEFT JOIN grupocurso gc ON gc.id = c.grupoaprobacion LEFT JOIN cursos cp ON cp.id = c.presencia LEFT JOIN cursos cc ON cc.id = c.correlativa WHERE tc.id_tienda = ? ORDER BY c.id";
	public static String TraerCursosPorMarca = db+"SELECT c.*, g.nombre nombre_grupo, gp.nombre nombre_grupo_presencia, gc.nombre nombre_grupo_correlativa, cp.nombre nombre_presencia, cc.nombre nombre_correlativa, c.tolerancia FROM cursos c LEFT JOIN grupocurso g ON g.id = c.grupo LEFT JOIN grupocurso gp ON gp.id = c.grupopresencia LEFT JOIN grupocurso gc ON gc.id = c.grupoaprobacion LEFT JOIN cursos cp ON cp.id = c.presencia LEFT JOIN cursos cc ON cc.id = c.correlativa WHERE c.marca = ? AND c.estado NOT LIKE 'FINALIZADO' AND ( c.estado NOT LIKE 'CANCELADO' OR c.fecha >= GETDATE() ) ORDER BY c.id";
	public static String TraerCursosFinalizadosPorMarca = db+"SELECT top 100 c.*, g.nombre nombre_grupo, gp.nombre nombre_grupo_presencia, gc.nombre nombre_grupo_correlativa, cp.nombre nombre_presencia, cc.nombre nombre_correlativa, c.tolerancia FROM cursos c LEFT JOIN grupocurso g ON g.id = c.grupo LEFT JOIN grupocurso gp ON gp.id = c.grupopresencia LEFT JOIN grupocurso gc ON gc.id = c.grupoaprobacion LEFT JOIN cursos cp ON cp.id = c.presencia LEFT JOIN cursos cc ON cc.id = c.correlativa WHERE c.marca = ? ORDER BY c.id";
	public static String TraerCursoPorBusqueda_old = "SELECT c.*, g.nombre nombre_grupo, gp.nombre nombre_grupo_presencia, gc.nombre nombre_grupo_correlativa, cp.nombre nombre_presencia, cc.nombre nombre_correlativa, c.tolerancia FROM cursos c JOIN instructor_curso ic ON ic.id_curso = c.id JOIN tienda_curso tc ON tc.id_curso = c.id LEFT JOIN grupocurso g ON g.id = c.grupo LEFT JOIN grupocurso gp ON gp.id = c.grupopresencia LEFT JOIN grupocurso gc ON gc.id = c.grupoaprobacion LEFT JOIN cursos cp ON cp.id = c.presencia LEFT JOIN cursos cc ON cc.id = c.correlativa WHERE ( c.id LIKE ? OR c.nombre LIKE ? )";
	public static String TraerCursoPorBusqueda = db+"SELECT c.*, g.nombre nombre_grupo, gp.nombre nombre_grupo_presencia, gc.nombre nombre_grupo_correlativa, cp.nombre nombre_presencia, cc.nombre nombre_correlativa, c.tolerancia FROM cursos c LEFT JOIN grupocurso g ON g.id = c.grupo LEFT JOIN grupocurso gp ON gp.id = c.grupopresencia LEFT JOIN grupocurso gc ON gc.id = c.grupoaprobacion LEFT JOIN cursos cp ON cp.id = c.presencia LEFT JOIN cursos cc ON cc.id = c.correlativa %$JOIN$% WHERE ( c.id LIKE ? OR c.nombre LIKE ? )";
	public static String TraerCursoCorrelativa = "select id, nombre from cursos where id like ? or nombre like ? order by id";
	public static String VerificarEstadoCurso = db+"SELECT count(*) estado FROM tienda_curso WHERE id_curso = ? and estado != ?";

	public static String NuevoCurso = db+"INSERT INTO cursos ( nombre, fecha, correlativa, presencia, lugar, aprobacion, estado, marca, grupo, grupoaprobacion, grupopresencia, descripcion, tolerancia ) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )";
	public static String NuevoCursoTienda = db+"INSERT INTO tienda_curso ( id_curso, id_tienda, estado ) VALUES ( ?, ?, ? )";
	public static String NuevoCursoInstructor = db+"INSERT INTO instructor_curso ( id_instructor, id_curso ) VALUES ( ?, ? )";
	public static String NuevoCursoCategoria = db+"INSERT INTO categoria_curso ( id_categoria, id_curso ) VALUES ( ?, ? )";
	public static String EditarCurso = db+"UPDATE cursos SET nombre = ?, fecha = ?, lugar = ?, aprobacion = ?, grupo = ?, descripcion = ?, tolerancia = ? WHERE id = ?";
	public static String LimpiarInstructor = db+"delete from instructor_curso WHERE id_curso = ?";
	public static String ModificarEstadoCurso = db+"UPDATE cursos SET estado = ? WHERE id = ?";
	public static String ModificarEstadoCursoTienda = db+"UPDATE tienda_curso SET estado = ? WHERE id_curso = ? and id_tienda = ?";

	// InstructoresDAO
	public static String TraerInstructoresPorMarca = db+"SELECT id, nombre, telefono FROM "+alsea_usuarios+" u WHERE app_id = 8 and app_nivel = 20";
	public static String TraerInstructoresSeleccionadosPorMarca = db+"SELECT id, nombre, telefono, case when isnull(id_curso,0)=0 then 'false' else 'true' end as asignado FROM "+alsea_usuarios+" u left join instructor_curso ic on ic.id_instructor = u.id and ic.id_curso = ? WHERE app_id = 8 and app_nivel = 20";
	public static String TraerInstructoresPorCurso = db+"SELECT id, nombre, telefono, id_curso FROM "+alsea_usuarios+" u join instructor_curso ic on ic.id_instructor = u.id and ic.id_curso = ? WHERE app_id = 8 and app_nivel = 20";

	// AsistentesDAO
	//public static String PayrollStarbucks = "use paystar ";
	//public static String PayrollBurgerKing = "use payArg ";
//	public static String TraerEmpleadosPorTiendaCategoria = "SELECT Q.codigo Legajo, Q.Nombre, r.codigo CC_id, R.descrip Tienda, s.codigo Categoria_id, s.descrip Categoria frOM REMPLES Q LEFT OUTER JOIN TBCASAPA A ON Q.CODIGO = A.APALEGAJO left outer join rtablas R on Q.cencos = R.codigo and R.cotab = 1 left outer join rtablas S on Q.catego = S.codigo and S.cotab = 2 where estado = 'A' and r.codigo = ? and s.codigo in ( &CATEGORIAS& ) GROUP BY Q.CODIGO, Q.NOMBRE, r.codigo, R.descrip, s.codigo, s.descrip order by q.codigo";
	
	public static String TraerEmpleadoPorLegajoMarca = "SELECT Q.codigo Legajo, Q.Nombre, r.codigo CC_id, R.descrip Tienda, s.codigo Categoria_id, s.descrip Categoria FROM REMPLES Q LEFT OUTER JOIN TBCASAPA A ON Q.CODIGO = A.APALEGAJO LEFT OUTER JOIN rtablas R ON Q.cencos = R.codigo AND R.cotab = 1 LEFT OUTER JOIN rtablas S ON Q.catego = S.codigo AND S.cotab = 2 WHERE estado = 'A' AND Q.codigo = ? GROUP BY Q.CODIGO, Q.NOMBRE, r.codigo, R.descrip, s.codigo, s.descrip ORDER BY q.codigo";
	public static String NuevoAsistenteCurso = db+"INSERT INTO asistentes ( id_curso, nombre, local, categoria, marca, legajo ) VALUES ( ?, ?, ?, ?, ?, ? );";
	public static String BorrarAsistentesDeCursoPorTienda = db+"DELETE FROM asistentes WHERE id_curso = ? AND local = ?";
	public static String TraerEmpleadosAsignados = db+"SELECT * FROM asistentes where id_curso = ?";
	public static String TraerEmpleadosAsignadosPorTienda = db+"SELECT * from asistentes where id_curso = ? and local = ?";
	public static String TraerEmpleadosAsignadosConPresencia = db+"SELECT * from asistentes where id_curso = ? and (estado_asistencia = 'PRESENTE' or estado_asistencia = 'TARDE')";
	public static String GuardarAsistenciaDelEmpleado = db+"UPDATE asistentes SET estado_asistencia = ? WHERE id = ?";
	public static String GuardarCalificacionDelEmpleado = db+"UPDATE asistentes SET resultado = ?, estado_aprobacion = ? WHERE id = ?";
	public static String validarPresencia = db+"SELECT count(*) seleccionado FROM asistentes WHERE id_curso = ? AND legajo = ? and estado_asistencia != 'AUSENTE'";
	public static String validarCorrelativa = db+"SELECT count(*) seleccionado FROM asistentes WHERE id_curso = ? AND legajo = ? and estado_aprobacion = 'APROBADO'";
	public static String validarGrupoPresencia = db+"SELECT top 1 a.estado_asistencia FROM asistentes a JOIN cursos c ON a.id_curso = c.id WHERE c.grupo = ? AND a.legajo = ? ORDER BY a.id DESC";
	public static String validarGrupoCorrelativa = db+"SELECT top 1 a.estado_aprobacion FROM asistentes a JOIN cursos c ON a.id_curso = c.id WHERE c.grupo = ? AND a.legajo = ? ORDER BY a.id DESC";
	public static String EmpleadoSeleccionado = db+"SELECT count(*) seleccionado FROM asistentes WHERE id_curso = ? AND legajo = ?";

	// CategoriasDAO
	public static String TraerCategoriasPorMarca = db+"SELECT id, idpayroll, nombre, marca FROM categorias where marca = ? order by nombre";
	public static String TraerCategoriasPorCurso = db+"SELECT cat.id, cat.idpayroll, cat.nombre, cat.marca from categorias cat join categoria_curso cc on cc.id_categoria = cat.id and cc.id_curso = ?";

	// UsuariosDAO
	public static String TraerUsuario = "WITH tiendas AS ( SELECT t.email, t.nombre, t.ccpayroll FROM "+microsBKtiendas+" t UNION ALL SELECT t.email, t.nombre, t.ccpayroll FROM "+microsSBtiendas+" t ) SELECT u.*, t.nombre 'tienda', t.ccpayroll FROM "+alsea_usuarios+" u LEFT JOIN tiendas t ON t.email = u.email WHERE app_id = 8 AND u.nombre = ?";

	// TiendasDAO
	public static String TraerTiendasBk = db+"SELECT u.id, t.ccpayroll, t.nombre FROM "+alsea_usuarios+" u JOIN "+microsBKtiendas+" t ON t.email = u.email WHERE app_id = 8";
	public static String TraerTiendasSbx = db+"SELECT u.id, t.ccpayroll, t.nombre FROM "+alsea_usuarios+" u JOIN "+microsSBtiendas+" t ON t.email = u.email WHERE app_id = 8";
	public static String TraerTiendasBkPorCurso = db+"SELECT u.id, t.ccpayroll, t.nombre, tc.id_curso, tc.estado FROM "+alsea_usuarios+" u JOIN "+microsBKtiendas+" t ON t.email = u.email join tienda_curso tc on tc.id_tienda = u.id and tc.id_curso = ? WHERE u.app_id = 8 order by tc.estado asc, ccpayroll asc";
	public static String TraerTiendasSbxPorCurso = db+"SELECT u.id, t.ccpayroll, t.nombre, tc.id_curso, tc.estado FROM "+alsea_usuarios+" u JOIN "+microsSBtiendas+" t ON t.email = u.email join tienda_curso tc on tc.id_tienda = u.id and tc.id_curso = ? WHERE u.app_id = 8 order by tc.estado asc, ccpayroll asc";
	public static String TraerTiendasPorCurso = db+"SELECT * FROM tienda_curso WHERE id_curso = ?";
	
	
	public static String Tolerancia = db+"SELECT * FROM cursos WHERE tolerancia IS NOT NULL AND estado = 'PENDIENTE_TIENDAS' AND DATEDIFF(dd,GETDATE(),fecha) <= tolerancia";
	public static String Tolerancia2 = db+"select count(*) from tienda_curso WHERE id_curso = ?";
	public static String Tolerancia3 = db+"UPDATE tienda_curso SET estado = ? WHERE id_curso = ? AND estado = ?";
	public static String Tolerancia4 = db+"UPDATE tienda_curso SET estado = ? WHERE id_curso = ? AND estado = ?";
	public static String Tolerancia5 = db+"UPDATE cursos SET estado = ? WHERE id = ?";
	
	String TraerGrupos = db+"SELECT * FROM grupocurso where marca = ?";
	String TraerGruposIDyNombre = db+"SELECT * FROM grupocurso where activo = 'true' and marca = ? and nombre like ?";
	String TraerGrupo = db+"SELECT * FROM grupocurso WHERE id = ?";
	String editarGrupo = db+"UPDATE grupocurso SET nombre = ?, activo = ? WHERE id = ?";
	
	String TraerUsuarios = db+"SELECT * FROM "+alsea_usuarios+" WHERE app_id = 8";
	String TraerUsuarioPorId = db+"SELECT * FROM "+alsea_usuarios+" WHERE app_id = 8 and id = ?";
	String editarUsuario = db+"UPDATE "+alsea_usuarios+" SET nombre = ?, email = ?, app_nivel = ?, marca = ?, telefono = ? WHERE id = ?";
	String nuevoUsuario = db+"INSERT INTO "+alsea_usuarios+" (nombre, email, app_id, app_nivel, marca, telefono ) VALUES ( ?, ?, ?, ?, ?, ? );";

}
