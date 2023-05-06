package br.com.desafio.rest.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.desafio.exception.NotFoundException;
import br.com.desafio.model.Pessoa;
import br.com.desafio.service.PessoaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;

@Validated
@RestController
@RequestMapping("/pessoa")
@Api(tags = { "Pessoa" })
@AllArgsConstructor
public class PessoaController {

	@Autowired
	private PessoaService pessoaService;

	@ApiOperation("Busca todas as pessoas")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Ok"),
			@ApiResponse(code = 404, message = "Canal nao encontrado") })
	@GetMapping("/listar")
	public ResponseEntity<List<Pessoa>> listarPessoas() {
		List<Pessoa> resultado = pessoaService.listarPessoas();
		return ResponseEntity.status(HttpStatus.OK).body(resultado);
	}

	@ApiOperation("Busca o pessoa a partir do seu id")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Ok"),
			@ApiResponse(code = 404, message = "Pessoa nao encontrada", response = NotFoundException.class) })
	@GetMapping("/{id}")
	public ResponseEntity<Optional<Pessoa>> pessoaPorId(@PathVariable @NotNull Long id) {
		Optional<Pessoa> resultado = pessoaService.pessoaPorId(id);

		if (resultado.isEmpty()) {
			throw new NotFoundException("Não encontrado");
		}
		return ResponseEntity.status(HttpStatus.OK).body(resultado);
	}

	@ApiOperation("Registra uma pessoa")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Cadastrado com sucesso"),
			@ApiResponse(code = 400, message = "Dados obrigatorios ausentes ou invalidos") })
	@PostMapping
	public ResponseEntity<Pessoa> salvar(@RequestBody @NotNull Pessoa pessoa) {
		Pessoa resultado = pessoaService.salvar(pessoa);

		return ResponseEntity.status(HttpStatus.CREATED).body(resultado);
	}

	@ApiOperation("Altera uma pessoa")
	@ApiResponses(value = { @ApiResponse(code = 204, message = "Alterado com sucesso"),
			@ApiResponse(code = 400, message = "Dados obrigatorios ausentes ou invalidos") })
	@PutMapping("/{id}")
	public ResponseEntity<Void> atualizar(@PathVariable(required = true) Long id, @RequestBody @NotNull Pessoa pessoa) {
		pessoaService.atualizar(id, pessoa);

		return ResponseEntity.noContent().build();
	}

}
