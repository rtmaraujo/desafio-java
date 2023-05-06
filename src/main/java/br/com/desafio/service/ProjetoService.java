package br.com.desafio.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.desafio.dto.ProjetoDTO;
import br.com.desafio.enums.ProjetoRisco;
import br.com.desafio.enums.ProjetoStatus;
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
				Optional<Pessoa> optPessoa = pessoaRepository.findById(p.getIdGerente().longValue());
				
				listaDtos.add(ProjetoDTO.builder()
						.dataFim(p.getDataFim())
						.dataFimFormat(Utils.formatarData(p.getDataFim()))
						.dataInicio(p.getDataInicio())
						.dataInicioFormat(Utils.formatarData(p.getDataInicio()))
						.dataPrevisaoFim(p.getDataPrevisaoFim())
						.dataPrevisaoFimFormat(Utils.formatarData(p.getDataPrevisaoFim()))
						.descricao(p.getDescricao()).id(p.getId()).idGerente(p.getIdGerente()).nome(p.getNome())
						.nomeGerente(optPessoa.get().getNome()).orcamento(p.getOrcamento()).risco(p.getRisco())
						.status(p.getStatus())
						.build());
			});
		}
		
		return listaDtos;
	}

	public Optional<Projeto> projetoPorId(Long id) {
		Optional<Projeto> optional = projetoRepository.findById(id);
		
		if (optional.isPresent()) {
			Projeto projeto = optional.get();
			optional.get().setRisco(ProjetoRisco.getCodigoPorDescricao(projeto.getRisco()));
			optional.get().setStatus(ProjetoStatus.getCodigoPorDescricao(projeto.getStatus()));
		}
		return optional;
	}

	public Projeto salvar(Projeto projeto) {
		
		projeto.setRisco(ProjetoRisco.getDescricaoPorCodigo(Integer.valueOf(projeto.getRisco())));
		projeto.setStatus(ProjetoStatus.getDescricaoPorCodigo(Integer.valueOf(projeto.getStatus())));
		
		Projeto resultado = projetoRepository.save(projeto);
		
//		if (null!= resultado) {
//			membroService.salvar(Membro.builder().idPessoa(resultado.getIdGerente().longValue())
//					.idProjeto(resultado.getId()).build());
//		}
		
		return resultado;
		
	}
	
	public void atualizar(Long id, Projeto projeto) {
		projeto.setId(id);
		Projeto resultado = projetoRepository.save(projeto);
		
//		if (null != resultado) {
//			membroService.salvar(Membro.builder().idPessoa(resultado.getIdGerente().longValue())
//					.idProjeto(resultado.getId()).build());
//		}
	}
	
	public void deletar(Long id) {
		
		Optional<Projeto> response = findByIdAndStatusNotIn(id);
		
		if (response.isPresent()) {
			projetoRepository.delete(response.get());
//			membroService.deletar(response.get().getId());
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
