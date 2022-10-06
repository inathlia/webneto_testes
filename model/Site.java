package model;

public class Site {
	private int id;
	private String link, logo, nome; //lembrete; logo é o caminho do diretório onde a img está
	
	public Site() {
		id = -1;
		link = "";
		logo = "";
		nome = "";
	}

	public Site(int id, String link, String logo, String nome) {
		setId(id);
		setLink(link);
		setLogo(logo);
		setNome(nome);
	}		
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	
	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

    public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * Método sobreposto da classe Object. É executado quando um objeto precisa
	 * ser exibido na forma de String.
	 */
	@Override
	public String toString() {
		return "Nome: " + nome + "   Link: " + link;
	}
	
	// @Override
	// public boolean equals(Object obj) {
	// 	return (this.getID() == ((Produto) obj).getID());
	// }	
}
