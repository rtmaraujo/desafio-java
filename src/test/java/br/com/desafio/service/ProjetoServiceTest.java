package br.com.desafio.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.desafio.dto.ProjetoDTO;
import br.com.desafio.enums.ProjetoRisco;
import br.com.desafio.enums.ProjetoStatus;
import br.com.desafio.model.Pessoa;
import br.com.desafio.model.Projeto;
import br.com.desafio.repository.PessoaRepository;
import br.com.desafio.repository.ProjetoRepository;

@ExtendWith(MockitoExtension.class)
public class ProjetoServiceTest {

	@InjectMocks
	private ProjetoService projetoService;

	@Mock
	private ProjetoRepository projetoRepository;
	
	@Mock
	private PessoaRepository pessoaRepository;

	@Test
	public void listarProjetosOk() {
		List<Projeto> projetos = getProjetos();
		
		Optional<Pessoa> optPessoa = Optional.of(Pessoa.builder().nome("Juca").build());

		when(this.projetoRepository.findAll()).thenReturn(projetos);
		
		when(this.pessoaRepository.findById(Mockito.anyLong())).thenReturn(optPessoa);

		List<ProjetoDTO> response = projetoService.listarProjetos();

		assertEquals(response.get(0).getNome(), projetos.get(0).getNome());
	}

	@Test
	public void listarProjetosNaoEncontrado() {
		List<Projeto> projetos = new ArrayList<>();

		when(this.projetoRepository.findAll()).thenReturn(projetos);

		List<ProjetoDTO> response = projetoService.listarProjetos();

		assertEquals(response.size(), projetos.size());
	}

	@Test
	public void projetoPorIdOK() {
		Optional<Projeto> projeto = Optional.of(getProjeto());

		when(this.projetoRepository.findById(1L)).thenReturn(projeto);

		Optional<Projeto> response = projetoService.projetoPorId(1L);

		assertEquals(response.get().getNome(), projeto.get().getNome());
	}

	@Test
	public void projetoPorIdNaoEncontrada() {
		Optional<Projeto> projeto = Optional.empty();

		when(this.projetoRepository.findById(1L)).thenReturn(projeto);

		Optional<Projeto> response = projetoService.projetoPorId(1L);

		assertEquals(response.isEmpty(), projeto.isEmpty());
	}

	@Test
	public void salvarProjetoOK() {
		Projeto projeto = getProjeto();

		when(this.projetoRepository.save(projeto)).thenReturn(projeto);

		Projeto response = projetoService.salvar(projeto);

		Mockito.verify(projetoRepository).save(projeto);

		assertEquals(response.getNome(), projeto.getNome());

	}

	@Test
	public void atualizarProjetoOK() {

		Projeto projeto = getProjeto();

		when(this.projetoRepository.save(projeto)).thenReturn(projeto);

		projetoService.atualizar(1L, projeto);

		Mockito.verify(projetoRepository).save(projeto);
	}
	
	@Test
	public void deletarProjetoOK() {
		Optional<Projeto> projeto = Optional.of(getProjeto());
		
		when(this.projetoRepository.
				findByIdAndStatusNotIn(1L, Arrays.asList(ProjetoStatus.INICIADO.getDescricao(), 
						ProjetoStatus.EM_ANDAMENTO.getDescricao(),
						ProjetoStatus.ENCERRADO.getDescricao()))).thenReturn(projeto);
		
		projetoService.deletar(1L);
		Mockito.verify(projetoRepository).delete(getProjeto());
	}

	private List<Projeto> getProjetos() {
		List<Projeto> lista = new ArrayList<>();
		lista.add(getProjeto());
		return lista;
	}

	private Projeto getProjeto() {
		return Projeto.builder().id(1L).nome("Carlos").descricao("teste").id(10L)
				.idGerente(new BigInteger("1")).risco(String.valueOf(ProjetoRisco.ALTO_RISCO.getCodigo()))
				.status(String.valueOf(ProjetoStatus.ANALISE_REALIZADA.getCodigo()))
				.build();
	}
}
