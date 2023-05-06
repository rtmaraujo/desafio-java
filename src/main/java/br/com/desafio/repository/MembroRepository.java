package br.com.desafio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.desafio.model.Membro;

@Repository
public interface MembroRepository extends JpaRepository<Membro, Long> {
}
