package exercicio1.model.vo;

public class NivelVO {
	
	private int idNivel;
	private String descricao;
	
	public NivelVO() {
		super();
	}

	public NivelVO(int idNivel, String descricao) {
		super();
		this.idNivel = idNivel;
		this.descricao = descricao;
	}

	public int getIdNivel() {
		return idNivel;
	}
	
	public void setIdNivel(int idNivel) {
		this.idNivel = idNivel;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	@Override
	public String toString() {
		return idNivel + " " + descricao;
	}

}
