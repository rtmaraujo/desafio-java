package br.com.desafio.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.desafio.model.Pessoa;
import br.com.desafio.repository.PessoaRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PessoaService {

	@Autowired
	private PessoaRepository pessoaRepository;

	public List<Pessoa> listarPessoas() {
		return pessoaRepository.findAll();
	}

	public Optional<Pessoa> pessoaPorId(Long id) {
		return pessoaRepository.findById(id);
	}

	public Pessoa salvar(Pessoa pessoa) {
		return pessoaRepository.saveAndFlush(pessoa);
	}
	
	public void atualizar(Long id, Pessoa pessoa) {
		pessoa.setId(id);
		pessoaRepository.saveAndFlush(pessoa);
	}

}
