package br.com.desafio.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.com.desafio.dto.ProjetoDTO;
import br.com.desafio.enums.ProjetoRisco;
import br.com.desafio.enums.ProjetoStatus;
import br.com.desafio.model.Projeto;
import br.com.desafio.service.PessoaService;
import br.com.desafio.service.ProjetoService;

@Controller
public class ProjetoController {

	@Autowired
	private ProjetoService projetoService;

	@Autowired
	private PessoaService pessoaService;
	
	@GetMapping("/cadastrarProjeto")
	public ModelAndView cadastrarProjeto() {
		ModelAndView model = new ModelAndView("cadastrarProjeto");

		model.addObject("listaRiscos", carregarRiscosProjeto());
		model.addObject("listaStatus", carregarStatusProjeto());
		model.addObject("listaPessoas", pessoaService.listarPessoas());

		return model;
	}

	@PostMapping("/salvar")
	public ModelAndView salvarForm(@Valid @ModelAttribute("projeto") ProjetoDTO projetoDTO) {

		Projeto response = projetoService.salvar(projetoDTO);
		
        if (response == null) {
        	ModelAndView model = new ModelAndView("redirect:/cadastrarProjeto");
            model.addObject("mensagemError", "Erro ao realizar o cadastro");
            return model;
        }
        return new ModelAndView("redirect:/consultarProjeto");
	}
	
	@GetMapping("/editarProjeto")
	public ModelAndView editarProjeto(@RequestParam(value = "id", required = true) Long id) {
		ModelAndView model = new ModelAndView("editarProjeto");
		
		Optional<ProjetoDTO> response = projetoService.projetoPorId(id);
		
		model.addObject("projeto", response.get());

		model.addObject("listaRiscos", carregarRiscosProjeto());
		model.addObject("listaStatus", carregarStatusProjeto());
		model.addObject("listaPessoas", pessoaService.listarPessoas());

		return model;
	}
	
	@PostMapping("/editar")
	public ModelAndView editarForm(@Valid @ModelAttribute("projeto") ProjetoDTO projetoDTO) {

		Projeto response = projetoService.atualizar(projetoDTO);
		
        if (response == null) {
        	ModelAndView model = new ModelAndView("redirect:/cadastrarProjeto");
            model.addObject("mensagemError", "Erro ao realizar o cadastro");
            return model;
        }
        return new ModelAndView("redirect:/consultarProjeto");
	}
	
	@GetMapping("/excluirProjeto")
	public ModelAndView excluirProjeto(@RequestParam(value = "id", required = true) Long id) {
		
		projetoService.deletar(id);
		
		return new ModelAndView("redirect:/consultarProjeto");
	}

	@GetMapping(value = "/consultarProjeto")
	public ModelAndView listarProjetos() {

		List<ProjetoDTO> projetos = projetoService.listarProjetos();
		ModelAndView model = new ModelAndView("consultarProjeto");
		model.addObject("listaProjetos", projetos);

		return model;
	}

	private List<ProjetoRisco> carregarRiscosProjeto() {
		return Arrays.asList(ProjetoRisco.values());
	}

	private List<ProjetoStatus> carregarStatusProjeto() {
		return Arrays.asList(ProjetoStatus.values());
	}

}
