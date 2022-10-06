package model;

public class Adm {
	private int id;
	private String nome, senha;
	
	public Adm() {
		id = -1;
		nome = "";
		senha = "";
	}

	public Adm(int id, String nome, String senha) {
		setId(id);
		setNome(nome);
		setSenha(senha);
	}		
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	/**
	 * Método sobreposto da classe Object. É executado quando um objeto precisa
	 * ser exibido na forma de String.
	 */
	@Override
	public String toString() {
		return "Nome: " + nome + "   Senha: " + senha;
	}
	
	// @Override
	// public boolean equals(Object obj) {
	// 	return (this.getID() == ((Produto) obj).getID());
	// }	
}
