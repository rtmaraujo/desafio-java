package br.com.desafio.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.desafio.model.Membro;
import br.com.desafio.repository.MembroRepository;

@ExtendWith(MockitoExtension.class)
public class MembroServiceTest {

	@InjectMocks
	private MembroService membroService;

	@Mock
	private MembroRepository membroRepository;

	@Test
	public void membroPorIdOk() {
		Optional<Membro> membro = Optional.of(getMembro());

		when(this.membroRepository.findByIdPessoaAndIdProjeto(1L, 2L)).thenReturn(membro);

		Optional<Membro> response = membroService.membroPorIdPessoaAndIdProjeto(1L, 2L);

		assertEquals(membro.get().getId(), response.get().getId());
	}

	@Test
	public void membroPorIdNaoEncontrado() {
		Optional<Membro> membro = Optional.empty();

		when(this.membroRepository.findByIdPessoaAndIdProjeto(1L, 2L)).thenReturn(membro);

		Optional<Membro> response = membroService.membroPorIdPessoaAndIdProjeto(1L, 2L);

		assertEquals(membro.isEmpty(), response.isEmpty());
	}

	@Test
	public void salvarMembroOK() {
		Membro membro = getMembro();

		when(this.membroRepository.saveAndFlush(membro)).thenReturn(membro);

		Membro response = membroService.salvar(membro);

		Mockito.verify(membroRepository).saveAndFlush(membro);

		assertEquals(membro.getId(), response.getId());

	}

	@Test
	public void deletarMembroOK() {
		membroService.deletar(1L);
		Mockito.verify(membroRepository).deleteById(1L);
	}

	private Membro getMembro() {
		return Membro.builder().id(1L).build();
	}
}
