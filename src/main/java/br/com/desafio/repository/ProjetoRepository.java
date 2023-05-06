package br.com.desafio.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.desafio.model.Projeto;

@Repository
public interface ProjetoRepository extends JpaRepository<Projeto, Long> {

	List<Projeto> findByStatus(String status);

	@Query("SELECT p FROM Projeto p WHERE p.id =?1 AND p.status NOT IN ?2")
	Optional<Projeto> findByIdAndStatusNotIn(Long id, List<String> listaStatus);
}
