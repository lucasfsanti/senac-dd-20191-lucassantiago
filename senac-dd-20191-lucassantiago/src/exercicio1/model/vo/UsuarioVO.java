package exercicio1.model.vo;

public class UsuarioVO {
	
	private int id;
	private String nome;
	private String email;
	private String senha;
	private NivelVO nivel;
	
	public UsuarioVO(int id, String nome, String email, String senha, NivelVO nivel) {
		super();
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.senha = senha;
		this.nivel = nivel;
	}

	public UsuarioVO() {
		super();
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
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getSenha() {
		return senha;
	}
	
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	public NivelVO getNivel() {
		return nivel;
	}
	
	public void setNivel(NivelVO nivel) {
		this.nivel = nivel;
	}
	
	@Override
	public String toString() {
		return "Nome: " + getNome() + "\nEmail: " + getEmail() + "\nSenha: " + getSenha() + "\nNível: " + getNivel();
	}	
}
