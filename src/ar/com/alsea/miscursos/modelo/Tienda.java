package ar.com.alsea.miscursos.modelo;

public class Tienda {

	private Integer id;
	private String nombre;
	private boolean seleccionado;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Tienda(Integer id, String nombre) {
		this.id = id;
		this.nombre = nombre;
	}

	public boolean isSeleccionado() {
		return seleccionado;
	}

	public void setSeleccionado(boolean seleccionado) {
		this.seleccionado = seleccionado;
	}

}
