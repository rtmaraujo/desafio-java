package br.com.desafio.enums;

public enum ProjetoRisco {

	BAIXO_RISCO(1, "BAIXO RISCO"), MEDIO_RISCO(2, "MEDIO RISCO"), ALTO_RISCO(3, "ALTO RISCO");

	private int codigo;
	private String descricao;

	private ProjetoRisco(int codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public static String getDescricaoPorCodigo(int codigo) {
		for (ProjetoRisco risco : ProjetoRisco.values()) {
			if (risco.getCodigo() == codigo) {
				return risco.getDescricao();
			}
		}
		return "";
	}
	
	public static String getCodigoPorDescricao(String descricao) {
		for (ProjetoRisco risco : ProjetoRisco.values()) {
			if (risco.getDescricao().equals(descricao)) {
				return String.valueOf(risco.getCodigo());
			}
		}
		return "";
	}

}