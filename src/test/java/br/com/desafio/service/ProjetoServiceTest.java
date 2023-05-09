package br.com.desafio.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.math.BigInteger;
import java.util.ArrayList;
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
import br.com.desafio.repository.MembroRepository;
import br.com.desafio.repository.PessoaRepository;
import br.com.desafio.repository.ProjetoRepository;

@ExtendWith(MockitoExtension.class)
public class ProjetoServiceTest {

	@InjectMocks
	private ProjetoService projetoService;

	@Mock
	private MembroService membroService;

	@Mock
	private ProjetoRepository projetoRepository;

	@Mock
	private PessoaRepository pessoaRepository;

	@Mock
	private MembroRepository membroRepository;

	@Test
	public void listarProjetosOk() {
		List<Projeto> projetos = getProjetos();

		Optional<Pessoa> optPessoa = Optional.of(Pessoa.builder().nome("Juca").build());

		when(this.projetoRepository.findAll()).thenReturn(projetos);

		when(this.pessoaRepository.findById(Mockito.anyLong())).thenReturn(optPessoa);

		List<ProjetoDTO> response = projetoService.listarProjetos();

		assertEquals(projetos.get(0).getNome(), response.get(0).getNome());
	}

	@Test
	public void listarProjetosNaoEncontrado() {
		List<Projeto> projetos = new ArrayList<>();

		when(this.projetoRepository.findAll()).thenReturn(projetos);

		List<ProjetoDTO> response = projetoService.listarProjetos();

		assertEquals(projetos.size(), response.size());
	}

	@Test
	public void projetoPorIdOK() {
		Optional<Projeto> projeto = Optional.of(getProjeto());

		when(this.projetoRepository.findById(1L)).thenReturn(projeto);

		Optional<ProjetoDTO> response = projetoService.projetoPorId(1L);

		assertEquals(projeto.get().getNome(), response.get().getNome());
	}

	@Test
	public void projetoPorIdNaoEncontrada() {

		Optional<Projeto> mock = Optional.empty();

		when(this.projetoRepository.findById(1L)).thenReturn(mock);

		Optional<ProjetoDTO> response = projetoService.projetoPorId(1L);

		assertEquals(mock.isEmpty(), response.isEmpty());
	}

	@Test
	public void salvarProjetoOK() {
		ProjetoDTO projetoDTO = getProjetoDTO();

		Projeto projeto = Projeto.builder().nome("Carlos").descricao("teste")
				.risco(String.valueOf(ProjetoRisco.ALTO_RISCO.getDescricao()))
				.pessoa(getPessoa())
				.status(String.valueOf(ProjetoStatus.ANALISE_REALIZADA.getDescricao())).build();

		when(pessoaRepository.findById(projetoDTO.getIdGerente().longValue()))
				.thenReturn(Optional.of(getPessoa()));

		projetoService.salvar(projetoDTO);

		Mockito.verify(projetoRepository).save(projeto);

	}

	@Test
	public void atualizarProjetoOK() {

		ProjetoDTO projetoDTO = getProjetoDTO();

		Projeto projeto = Projeto.builder().nome("Carlos").descricao("teste")
				.risco(String.valueOf(ProjetoRisco.ALTO_RISCO.getDescricao()))
				.pessoa(getPessoa())
				.status(String.valueOf(ProjetoStatus.ANALISE_REALIZADA.getDescricao())).build();

		when(pessoaRepository.findById(projetoDTO.getIdGerente().longValue()))
				.thenReturn(Optional.of(getPessoa()));

		projetoService.atualizar(projetoDTO);

		Mockito.verify(projetoRepository).saveAndFlush(projeto);
	}

	private List<Projeto> getProjetos() {
		List<Projeto> lista = new ArrayList<>();
		lista.add(getProjeto());
		return lista;
	}

	private Projeto getProjeto() {
		return Projeto.builder().nome("Carlos").descricao("teste")
				.pessoa(Pessoa.builder().id(1L).build()).risco(String.valueOf(ProjetoRisco.ALTO_RISCO.getCodigo()))
				.status(String.valueOf(ProjetoStatus.ANALISE_REALIZADA.getCodigo())).build();
	}

	private ProjetoDTO getProjetoDTO() {
		return ProjetoDTO.builder().nome("Carlos").descricao("teste").idGerente(new BigInteger("1"))
				.risco(String.valueOf(ProjetoRisco.ALTO_RISCO.getCodigo()))
				.status(String.valueOf(ProjetoStatus.ANALISE_REALIZADA.getCodigo())).build();
	}

	private Pessoa getPessoa() {
		return Pessoa.builder().cpf("123456789").id(10L).funcionario(true).nome("Joao Souza")
				.build();
	}
}
