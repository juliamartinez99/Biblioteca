package biblioteca;

public class Libro {

	private String titulo;
	private String autor;
	private String anio;
	private String disponible;
	private String falta;
	private String id;
	
	//constructor
	public Libro(String id, String titulo, String autor, String anio, String disponible, String falta) {
		
		this.titulo = titulo;
		this.autor = autor;
		this.anio = anio;
		this.disponible = disponible;
		this.falta = falta;
		this.id = id;
		
	}
	
	//constructor por defecto
	public Libro() {}
	
	
	//getters y setters
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getAutor() {
		return autor;
	}
	public void setAutor(String autor) {
		this.autor = autor;
	}
	public String getAnio() {
		return anio;
	}
	public void setAnio(String anio) {
		this.anio = anio;
	}
	public String getDisponible() {
		return disponible;
	}
	public void setDisponible(String disponible) {
		this.disponible = disponible;
	}
	public String getFalta() {
		return falta;
	}
	public void setFalta(String falta) {
		this.falta = falta;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	
}
