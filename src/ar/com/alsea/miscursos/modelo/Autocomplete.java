package ar.com.alsea.miscursos.modelo;

public class Autocomplete {
	
	private int value;
	private String label;
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = this.value + " - " + label;
	}
	

}
