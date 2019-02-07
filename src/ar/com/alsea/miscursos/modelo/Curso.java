package ar.com.alsea.miscursos.modelo;

public class Curso {

	private String nombre,
					fecha,
					lugar,
					estado,
					marca,
					nombre_correlativa,
					nombre_presencia,
					nombre_grupo_correlativa,
					nombre_grupo_presencia,
					nombre_grupo,
					descripcion;
	private Integer id,
					correlativa,
					presencia,
					grupo_correlativa,
					grupo_presencia,
					grupo,
					tolerancia;
	private float aprobacion;
	private Integer[] tiendas,
					instructores,
					categorias;
	
	public Curso() {
		//correlativa = presencia = null;
	}

	public Integer getId() {
		return id;
	}

	public String getNombre() {
		return nombre;
	}

	public String getFecha() {
		return fecha;
	}

	public String getLugar() {
		return lugar;
	}

	public float getAprobacion() {
		return aprobacion;
	}

	public String getEstado() {
		return estado;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha.substring(0,16);
	}

	public void setLugar(String lugar) {
		this.lugar = lugar;
	}

	public void setAprobacion(float aprobacion) {
		this.aprobacion = aprobacion;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Integer[] getTiendas() {
		return tiendas;
	}

	public void setTiendas(Integer[] tiendas) {
		this.tiendas = tiendas;
	}

	public Integer[] getInstructores() {
		return instructores;
	}

	public void setInstructores(Integer[] instructores) {
		this.instructores = instructores;
	}

	public Integer[] getCategorias() {
		return categorias;
	}

	public void setCategorias(Integer[] categorias) {
		this.categorias = categorias;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public Integer getCorrelativa() {
		return correlativa;
	}

	public void setCorrelativa(Integer correlativa) {
		this.correlativa = correlativa;
	}

	public Integer getPresencia() {
		return presencia;
	}

	public Integer setPresencia(Integer presencia) {
		this.presencia = presencia;
		return presencia;
	}

	public Integer getGrupo() {
		return grupo;
	}

	public void setGrupo(Integer grupo) {
		this.grupo = grupo;
	}

	public Integer getGrupo_correlativa() {
		return grupo_correlativa;
	}

	public void setGrupo_correlativa(Integer grupo_correlativa) {
		this.grupo_correlativa = grupo_correlativa;
	}

	public Integer getGrupo_presencia() {
		return grupo_presencia;
	}

	public void setGrupo_presencia(Integer grupo_presencia) {
		this.grupo_presencia = grupo_presencia;
	}

	public String getNombre_correlativa() {
		return nombre_correlativa;
	}

	public void setNombre_correlativa(String nombre_correlativa) {
		this.nombre_correlativa = nombre_correlativa;
	}

	public String getNombre_presencia() {
		return nombre_presencia;
	}

	public void setNombre_presencia(String nombre_presencia) {
		this.nombre_presencia = nombre_presencia;
	}

	public String getNombre_grupo_correlativa() {
		return nombre_grupo_correlativa;
	}

	public void setNombre_grupo_correlativa(String nombre_grupo_correlativa) {
		this.nombre_grupo_correlativa = nombre_grupo_correlativa;
	}

	public String getNombre_grupo_presencia() {
		return nombre_grupo_presencia;
	}

	public void setNombre_grupo_presencia(String nombre_grupo_presencia) {
		this.nombre_grupo_presencia = nombre_grupo_presencia;
	}

	public String getNombre_grupo() {
		return nombre_grupo;
	}

	public void setNombre_grupo(String nombre_grupo) {
		this.nombre_grupo = nombre_grupo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Integer getTolerancia() {
		return tolerancia;
	}

	public void setTolerancia(Integer tolerancia) {
		this.tolerancia = tolerancia;
	}
}
