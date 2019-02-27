package prova.beans;

public class Usuario {

	private int idusuario;
	private String email;
	private String senha;
	private String nome;
	private boolean admin;

	public Usuario() {

	}
	public Usuario(String email, String senha) {
		this.email = email;
		this.senha = senha;
	}

	public Usuario(String email, String senha, String nome, boolean admin) {
		this.email = email;
		this.senha = senha;
		this.nome = nome;
		this.admin = admin;
	}

	public String getEmail() {
		return email;
	}

	public String getSenha() {
		return senha;
	}

	public int getIdusuario() {
		return idusuario;
	}

	public void setIdusuario(int idusuario) {
		this.idusuario = idusuario;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public boolean isAdmin() {
		return admin;
	}

	public void setAdmin(boolean admin) {
		this.admin = admin;
	}

}
