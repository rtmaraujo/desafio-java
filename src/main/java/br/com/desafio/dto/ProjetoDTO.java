package br.com.desafio.dto;

import java.math.BigInteger;
import java.time.LocalDate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

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
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dataInicio;
	private String dataInicioFormat;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dataPrevisaoFim;
	private String dataPrevisaoFimFormat;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dataFim;
	private String dataFimFormat;
	private String descricao;
	private String status;
	private Float orcamento;
	private String risco;
	@NotNull
	private BigInteger idGerente;
	private BigInteger idGerenteAnterior;
	private String nomeGerente;

}
