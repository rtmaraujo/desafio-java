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


	public Optional<Membro> membroPorId(Long id) {
		return membroRepository.findById(id);
	}

	public Membro salvar(Membro membro) {
		return membroRepository.save(membro);
	}
	
	public void atualizar(Membro membro) {
		membroRepository.save(membro);
	}
	
	public void deletar(Long id) {
		Optional<Membro> response = membroPorId(id);
		
		if (response.isPresent()) {
			membroRepository.delete(response.get());
		}
	}

}
