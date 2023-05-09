package br.com.desafio.model;

import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
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
public class Membro{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@OneToOne
	@JoinColumn(name = "idprojeto", referencedColumnName = "id", nullable = false, 
	foreignKey = @ForeignKey(name = "fk_projeto"))
	private Projeto projeto;
	
	@OneToOne
	@JoinColumn(name="idpessoa", referencedColumnName = "id", nullable = false, 
	foreignKey = @ForeignKey(name = "fk_pessoa"))
	private Pessoa pessoa;

}
