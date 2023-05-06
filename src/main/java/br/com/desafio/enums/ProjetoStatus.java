package br.com.desafio.enums;

public enum ProjetoStatus {

	EM_ANALISE(1, "EM ANALISE"), ANALISE_REALIZADA(2, "ANALISE REALIZADA"), ANALISE_APROVADA(3, "ANALISE APROVADA"),
	INICIADO(4, "INICIADO"), PLANEJADO(5, "PLANEJADO"), EM_ANDAMENTO(6, "EM ANDAMENTO"), ENCERRADO(7, "ENCERRADO"),
	CANCELADO(8, "CANCELADO");

	private int codigo;
	private String descricao;

	private ProjetoStatus(int codigo, String descricao) {
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
		for (ProjetoStatus risco : ProjetoStatus.values()) {
			if (risco.getCodigo() == codigo) {
				return risco.getDescricao();
			}
		}
		return "";
	}

	public static String getCodigoPorDescricao(String descricao) {
		for (ProjetoStatus risco : ProjetoStatus.values()) {
			if (risco.getDescricao().equals(descricao)) {
				return String.valueOf(risco.getCodigo());
			}
		}
		return "";
	}

}