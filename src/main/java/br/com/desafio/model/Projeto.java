package br.com.desafio.model;

import java.math.BigInteger;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
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
@Entity
@Table(name = "projeto")
public class Projeto {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@NotBlank
	@Column(name = "nome", nullable = false, length = 200)
	private String nome;

	@Column(name = "data_inicio")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dataInicio;

	@Column(name = "data_previsao_fim")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dataPrevisaoFim;

	@Column(name = "data_fim")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dataFim;

	@Column(name = "descricao", length = 5000)
	private String descricao;

	@Column(name = "status", length = 45)
	private String status;

	@Column(name = "orcamento")
	private Float orcamento;

	@Column(name = "risco", length = 45)
	private String risco;

	@NotNull
	@Column(name = "idgerente", nullable = false)
	private BigInteger idGerente;

}
