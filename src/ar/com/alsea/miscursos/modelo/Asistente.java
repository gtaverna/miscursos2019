package ar.com.alsea.miscursos.modelo;

public class Asistente {

	private int id;
	private int id_curso;
	private String nombre;
	private String legajo;
	private int local;
	private float resultado;
	private String estado_asistencia;
	private String categoria;
	private String marca;
	private String estado_aprobacion;
	private boolean seleccionado;

	public Asistente() {
		
	}

	public int getId() {
		return id;
	}

	public int getId_curso() {
		return id_curso;
	}

	public String getNombre() {
		return nombre;
	}

	public int getLocal() {
		return local;
	}

	public float getResultado() {
		return resultado;
	}

	public String getEstado_asistencia() {
		return estado_asistencia;
	}

	public String getCategoria() {
		return categoria;
	}

	

	public void setId(int id) {
		this.id = id;
	}

	public void setId_curso(int id_curso) {
		this.id_curso = id_curso;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setLocal(int local) {
		this.local = local;
	}

	public void setResultado(float resultado) {
		this.resultado = resultado;
	}

	public void setEstado_asistencia(String estado_asistencia) {
		this.estado_asistencia = estado_asistencia;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	

	public String getLegajo() {
		return legajo;
	}

	public void setLegajo(String legajo) {
		this.legajo = legajo;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public boolean isSeleccionado() {
		return seleccionado;
	}

	public void setSeleccionado(boolean seleccionado) {
		this.seleccionado = seleccionado;
	}

	public String getEstado_aprobacion() {
		return estado_aprobacion;
	}

	public void setEstado_aprobacion(String estado_aprobacion) {
		this.estado_aprobacion = estado_aprobacion;
	}

}
