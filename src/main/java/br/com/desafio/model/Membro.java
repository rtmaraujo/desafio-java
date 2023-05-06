package br.com.desafio.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "membros")
public class Membro {

	@Column(name = "idpessoa", nullable = false)
	private Long idPessoa;
	
	@Id
	@Column(name = "idprojeto", nullable = false)
	private Long idProjeto;

}
