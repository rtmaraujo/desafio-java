package br.com.desafio.dto;

import java.math.BigInteger;
import java.time.LocalDate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProjetoDTO {
	
	private Long id;

	@NotBlank
	private String nome;
	private LocalDate dataInicio;
	private String dataInicioFormat;
	private LocalDate dataPrevisaoFim;
	private String dataPrevisaoFimFormat;
	private LocalDate dataFim;
	private String dataFimFormat;
	private String descricao;
	private String status;
	private Float orcamento;
	private String risco;
	@NotNull
	private BigInteger idGerente;
	private String nomeGerente;

}
