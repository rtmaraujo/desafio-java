package br.com.desafio.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.desafio.model.Membro;
import br.com.desafio.repository.MembroRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MembroService {

	@Autowired
	private MembroRepository membroRepository;
	
	public Optional<Membro> membroPorIdPessoaAndIdProjeto(Long idPessoa, Long idProjeto) {
		return membroRepository.findByIdPessoaAndIdProjeto(idPessoa, idProjeto);
	}

	public Membro salvar(Membro membro) {
		return membroRepository.saveAndFlush(membro);
	}

	public void deletar(Long id) {
		membroRepository.deleteById(id);
	}

}
