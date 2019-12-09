package com.controledegastos.controlador;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.controledegastos.entidade.Categoria;
import com.controledegastos.entidade.Pessoa;
import com.controledegastos.servico.PessoaServico;

@RestController
@RequestMapping("/pessoas")
public class PessoaControlador {
	
	@Autowired
	private PessoaServico pessoaServico;
	
	@GetMapping
	public List<Pessoa> buscarTodos(){
		return pessoaServico.buscarTodos();
	}
	
	@GetMapping("/buscarPorId")
	public ResponseEntity<Pessoa> buscarPorId(@RequestParam("codigo") Long codigo) {
		Optional<Pessoa> pessoa = pessoaServico.buscarPorId(codigo);
		if (pessoa.isPresent())
			return ResponseEntity.ok(pessoa.get());
		else
			return ResponseEntity.notFound().build();
	}
	
	@PostMapping
	public ResponseEntity<Pessoa> salvar(@RequestBody Pessoa pessoa){
		return ResponseEntity.status(HttpStatus.CREATED).body(pessoaServico.salvar(pessoa));
	}
	
	@PutMapping("/{codigo}")
	public ResponseEntity<Pessoa> atualizar(@PathVariable("codigo") Long codigo, @Valid @RequestBody Pessoa pessoa) {
		return ResponseEntity.ok(pessoaServico.atualizar(codigo, pessoa));
	}
	
	@DeleteMapping("/{codigo}")
	@ResponseStatus(HttpStatus.NO_CONTENT)	
	public void deletar(@PathVariable("codigo") Long codigo) {
		pessoaServico.deletar(codigo);
	}
}
