package ar.com.alsea.miscursos.modelo;

public class Categoria {
	private int id;
	private int idpayroll;
	private String nombre;
	private String marca;

	public Categoria() { }

	public int getId() {
		return id;
	}

	public int getIdpayroll() {
		return idpayroll;
	}

	public String getNombre() {
		return nombre;
	}

	public String getMarca() {
		return marca;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setIdpayroll(int idpayroll) {
		this.idpayroll = idpayroll;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}
}
