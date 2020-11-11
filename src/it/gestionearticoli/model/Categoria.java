package it.gestionearticoli.model;

public class Categoria {
	
	 private Long id;
	 private String categoria;
	 
	 
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCategoria() {
		return categoria;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	
	public Categoria() {
		
	}
	
	public Categoria(String categoria) {
		this.categoria = categoria;
	}
	
	public String toString() {
		return categoria;
	}

}
