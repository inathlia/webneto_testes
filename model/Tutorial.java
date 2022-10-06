package model;

public class Tutorial {
	private int id;
	private String passos, video, titulo, foto; //lembrete; logo é o caminho do diretório onde a img está
	
	public Tutorial() {
		id = -1;
		passos = "";
		video = "";
		titulo = "";
		foto = "";
	}

	public Tutorial(int id, String passos, String video, String titulo, String foto) {
		setId(id);
		setPassos(passos);
		setVideo(video);
		setTitulo(titulo);
		setFoto(foto);
	}		
	
	public int getID() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPassos() {
		return passos;
	}

	public void setPassos(String passos) {
		this.passos = passos;
	}

	public String getVideo() {
		return video;
	}

	public void setVideo(String video) {
		this.video = video;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	/**
	 * Método sobreposto da classe Object. É executado quando um objeto precisa
	 * ser exibido na forma de String.
	 */
	@Override
	public String toString() {
		return "Titulo: " + titulo + "   Passos: " + passos;
	}
	
	// @Override
	// public boolean equals(Object obj) {
	// 	return (this.getID() == ((Produto) obj).getID());
	// }	
}
