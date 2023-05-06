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
		
		when(this.membroRepository.findById(1L)).thenReturn(membro);
		
		Optional<Membro> response = membroService.membroPorId(1L);

		assertEquals(response.get().getIdPessoa(), membro.get().getIdPessoa());
	}

	@Test
	public void membroPorIdNaoEncontrado() {
		Optional<Membro> membro = Optional.empty();
		
		when(this.membroRepository.findById(1L)).thenReturn(membro);
		
		Optional<Membro> response = membroService.membroPorId(1L);

		assertEquals(response.isEmpty(), membro.isEmpty());
	}

	@Test
	public void salvarMembroOK() {
		Membro membro = getMembro();

		when(this.membroRepository.save(membro)).thenReturn(membro);

		Membro response = membroService.salvar(membro);

		Mockito.verify(membroRepository).save(membro);

		assertEquals(response.getIdProjeto(), membro.getIdProjeto());

	}

	@Test
	public void atualizarMembroOK() {

		Membro membro = getMembro();

		when(this.membroRepository.save(membro)).thenReturn(membro);

		membroService.atualizar(membro);

		Mockito.verify(membroRepository).save(membro);
	}
	
	@Test
	public void deletarMembroOK() {
		Optional<Membro> membro = Optional.of(getMembro());
		when(this.membroRepository.findById(1L)).thenReturn(membro);
		
		membroService.deletar(1L);
		Mockito.verify(membroRepository).delete(getMembro());
	}


	private Membro getMembro() {
		return Membro.builder().idPessoa(1L).idProjeto(2L)
				.build();
	}
}
