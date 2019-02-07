package ar.com.alsea.miscursos.modelo;

public class Usuario {

	private int id;
	private String nombre;
	private String email;
	private int app_nivel;
	private String marca;
	private int ccpayroll;
	private String tienda;
	private String telefono;

	public Usuario() {

	}

	public int getId() {
		return id;
	}

	public String getNombre() {
		return nombre;
	}

	public String getEmail() {
		return email;
	}

	public int getApp_nivel() {
		return app_nivel;
	}

	public String getMarca() {
		return marca;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setApp_nivel(int app_nivel) {
		this.app_nivel = app_nivel;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}
	
	public int getCcpayroll() {
		return ccpayroll;
	}

	public void setCcpayroll(int ccpayroll) {
		this.ccpayroll = ccpayroll;
	}

	public String getTienda() {
		return tienda;
	}

	public void setTienda(String tienda) {
		this.tienda = tienda;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

}
