package br.com.desafio.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.desafio.model.Pessoa;
import br.com.desafio.repository.PessoaRepository;

@ExtendWith(MockitoExtension.class)
public class PessoaServiceTest {

	@InjectMocks
	private PessoaService pessoaService;

	@Mock
	private PessoaRepository pessoaRepository;

	@Test
	public void listarPessoasOk() {
		List<Pessoa> pessoas = getPessoas();

		when(this.pessoaRepository.findAll()).thenReturn(pessoas);

		List<Pessoa> response = pessoaService.listarPessoas();

		assertEquals(response.get(0).getNome(), pessoas.get(0).getNome());
	}

	@Test
	public void listarPessoasNaoEncontrada() {
		List<Pessoa> pessoas = new ArrayList<>();

		when(this.pessoaRepository.findAll()).thenReturn(pessoas);

		List<Pessoa> response = pessoaService.listarPessoas();

		assertEquals(response.size(), pessoas.size());
	}

	@Test
	public void pessoaPorIdOK() {
		Optional<Pessoa> pessoa = Optional.of(getPessoa());

		when(this.pessoaRepository.findById(1L)).thenReturn(pessoa);

		Optional<Pessoa> response = pessoaService.pessoaPorId(1L);

		assertEquals(response.get().getNome(), pessoa.get().getNome());
	}

	@Test
	public void pessoaPorIdNaoEncontrada() {
		Optional<Pessoa> pessoa = Optional.empty();

		when(this.pessoaRepository.findById(1L)).thenReturn(pessoa);

		Optional<Pessoa> response = pessoaService.pessoaPorId(1L);

		assertEquals(response.isEmpty(), pessoa.isEmpty());
	}

	@Test
	public void salvarPessoaOK() {
		Pessoa pessoa = getPessoa();

		when(this.pessoaRepository.save(pessoa)).thenReturn(pessoa);

		Pessoa response = pessoaService.salvar(getPessoa());

		Mockito.verify(pessoaRepository).save(pessoa);

		assertEquals(response.getNome(), pessoa.getNome());

	}

	@Test
	public void atualizarPessoaOK() {

		Pessoa pessoa = getPessoa();

		when(this.pessoaRepository.save(pessoa)).thenReturn(pessoa);

		pessoaService.atualizar(1L, pessoa);

		Mockito.verify(pessoaRepository).save(pessoa);
	}

	private List<Pessoa> getPessoas() {
		List<Pessoa> lista = new ArrayList<>();
		lista.add(getPessoa());
		return lista;
	}

	private Pessoa getPessoa() {
		return Pessoa.builder().id(1L).nome("Carlos").cpf("12345612389").funcionario(false).build();
	}
}
