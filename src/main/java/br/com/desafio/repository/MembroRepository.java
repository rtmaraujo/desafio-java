package br.com.desafio.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.desafio.model.Membro;

@Repository
public interface MembroRepository extends JpaRepository<Membro, Long> {

	@Query("SELECT m FROM Membro m WHERE m.pessoa.id =?1 AND m.projeto.id = ?2")
	Optional<Membro> findByIdPessoaAndIdProjeto(Long idPessoa, Long idProjeto);
}
