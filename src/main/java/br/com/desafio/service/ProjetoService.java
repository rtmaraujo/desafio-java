package br.com.desafio.service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.desafio.dto.ProjetoDTO;
import br.com.desafio.enums.ProjetoRisco;
import br.com.desafio.enums.ProjetoStatus;
import br.com.desafio.model.Membro;
import br.com.desafio.model.Pessoa;
import br.com.desafio.model.Projeto;
import br.com.desafio.repository.PessoaRepository;
import br.com.desafio.repository.ProjetoRepository;
import br.com.desafio.util.Utils;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProjetoService {

	@Autowired
	private ProjetoRepository projetoRepository;
	
	@Autowired
	private PessoaRepository pessoaRepository;
	
	@Autowired
	private MembroService membroService;

	public List<ProjetoDTO> listarProjetos() {
		
		List<Projeto> response = projetoRepository.findAll();
		
		List<ProjetoDTO> listaDtos = new ArrayList<>();
		if (!response.isEmpty()) {
			response.forEach(p->{
				Optional<Pessoa> optPessoa = pessoaRepository.findById(p.getPessoa().getId());
				
				listaDtos.add(ProjetoDTO.builder()
						.dataFim(p.getDataFim())
						.dataFimFormat(Utils.formatarData(p.getDataFim()))
						.dataInicio(p.getDataInicio())
						.dataInicioFormat(Utils.formatarData(p.getDataInicio()))
						.dataPrevisaoFim(p.getDataPrevisaoFim())
						.dataPrevisaoFimFormat(Utils.formatarData(p.getDataPrevisaoFim()))
						.descricao(p.getDescricao()).id(p.getId())
						.idGerente(BigInteger.valueOf(p.getPessoa().getId()))
						.idGerenteAnterior(BigInteger.valueOf(p.getPessoa().getId()))
						.nome(p.getNome())
						.nomeGerente(optPessoa.get().getNome()).orcamento(p.getOrcamento()).risco(p.getRisco())
						.status(p.getStatus())
						.build());
			});
		}
		
		return listaDtos;
	}

	public Optional<ProjetoDTO> projetoPorId(Long id) {
		Optional<Projeto> optional = projetoRepository.findById(id);
		
		Optional<ProjetoDTO> projetoDTO = Optional.empty();
		if (optional.isPresent()) {
			Projeto p = optional.get();
			projetoDTO = Optional.of(ProjetoDTO.builder().dataFim(p.getDataFim()).dataFimFormat(Utils.formatarData(p.getDataFim()))
					.dataInicio(p.getDataInicio()).dataInicioFormat(Utils.formatarData(p.getDataInicio()))
					.dataPrevisaoFim(p.getDataPrevisaoFim())
					.dataPrevisaoFimFormat(Utils.formatarData(p.getDataPrevisaoFim())).descricao(p.getDescricao())
					.id(p.getId()).idGerente(BigInteger.valueOf(p.getPessoa().getId()))
					.idGerenteAnterior(BigInteger.valueOf(p.getPessoa().getId())).nome(p.getNome())
					.orcamento(p.getOrcamento()).risco(ProjetoRisco.getCodigoPorDescricao(p.getRisco()))
					.status(ProjetoStatus.getCodigoPorDescricao(p.getStatus())).build());
		}
		return projetoDTO;
	}

	public Projeto salvar(ProjetoDTO projetoDTO) {
		
		
		Optional<Pessoa> optPessoa = pessoaRepository.findById(projetoDTO.getIdGerente().longValue());
		
		Projeto projeto = Projeto.builder().nome(projetoDTO.getNome()).dataInicio(projetoDTO.getDataInicio())
				.dataFim(projetoDTO.getDataFim()).dataPrevisaoFim(projetoDTO.getDataPrevisaoFim())
				.descricao(projetoDTO.getDescricao())
				.status(ProjetoStatus.getDescricaoPorCodigo(Integer.valueOf(projetoDTO.getStatus())))
				.risco(ProjetoRisco.getDescricaoPorCodigo(Integer.valueOf(projetoDTO.getRisco())))
				.orcamento(projetoDTO.getOrcamento()).pessoa(optPessoa.get()).build();
		
		Projeto resultado = projetoRepository.save(projeto);
		
		if (null != resultado) {
			membroService.salvar(Membro.builder()
					.pessoa(resultado.getPessoa()).projeto(resultado).build());
		}
		
		return resultado;
	}
	
	public Projeto atualizar(ProjetoDTO projetoDTO) {
		
		Optional<Pessoa> optPessoa = pessoaRepository.findById(projetoDTO.getIdGerente().longValue());
		
		Projeto projeto = Projeto.builder()
				.id(projetoDTO.getId())
				.nome(projetoDTO.getNome()).dataInicio(projetoDTO.getDataInicio())
				.dataFim(projetoDTO.getDataFim()).dataPrevisaoFim(projetoDTO.getDataPrevisaoFim())
				.descricao(projetoDTO.getDescricao())
				.status(ProjetoStatus.getDescricaoPorCodigo(Integer.valueOf(projetoDTO.getStatus())))
				.risco(ProjetoRisco.getDescricaoPorCodigo(Integer.valueOf(projetoDTO.getRisco())))
				.orcamento(projetoDTO.getOrcamento()).pessoa(optPessoa.get()).build();
		
		Projeto resultado = projetoRepository.saveAndFlush(projeto);
		
		if (null != resultado) {
			Optional<Membro> membro = membroService.membroPorIdPessoaAndIdProjeto(
					projetoDTO.getIdGerenteAnterior().longValue(), projetoDTO.getId());
			membroService.salvar(membro.get());
		}
		return resultado;
				
	}
	
	public void deletar(Long id) {
		
		Optional<Projeto> response = findByIdAndStatusNotIn(id);
		
		if (response.isPresent()) {
			Optional<Membro> membro = membroService.membroPorIdPessoaAndIdProjeto(response.get().getPessoa().getId(), 
					response.get().getId());
			
			if(membro.isPresent()) {
				membroService.deletar(membro.get().getId());
			}
			projetoRepository.deleteById(response.get().getId());
		}
	}
	
	public Optional<Projeto> findByIdAndStatusNotIn(Long id) {
		
		Optional<Projeto> projeto = projetoRepository.
				findByIdAndStatusNotIn(id, Arrays.asList(ProjetoStatus.INICIADO.getDescricao(), 
						ProjetoStatus.EM_ANDAMENTO.getDescricao(),
						ProjetoStatus.ENCERRADO.getDescricao()));
		
		return projeto;
	}

	public List<Projeto> consultarPorStatus(String status) {
		return projetoRepository.findByStatus(status);
		
	}
	
}
