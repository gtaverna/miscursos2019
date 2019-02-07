package ar.com.alsea.miscursos.modelo;

import java.util.ArrayList;

public class GrupoTiendas {

	private Integer id;
	private String descripcion;
	private ArrayList<Tienda> tiendas;
	private String marca;
	
	public GrupoTiendas(Integer id, String descripcion, ArrayList<Tienda> tiendas, String marca) {
		this.id = id;
		this.descripcion = descripcion;
		this.tiendas = tiendas;
		this.marca = marca;
	}
	public Integer getId() {
		return id;
	}
	public String getDescripcion() {
		return descripcion;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public ArrayList<Tienda> getTiendas() {
		return tiendas;
	}

	public void setTiendas(ArrayList<Tienda> tiendas) {
		this.tiendas = tiendas;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}
	
}
