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
import com.controledegastos.servico.CategoriaServico;

@RestController
@RequestMapping("/categorias")
public class CategoriaControlador {
	
	@Autowired
	private CategoriaServico categoriaServico;
	
	@GetMapping
	public List<Categoria> buscarTodos(){
		return categoriaServico.buscarTodos();
	}
	@GetMapping("/buscarPorId")
	public ResponseEntity<Categoria> buscarPorId(@RequestParam("codigo") Long codigo) {
		Optional<Categoria> categoria = categoriaServico.buscarPorId(codigo);
		if (categoria.isPresent())
			return ResponseEntity.ok(categoria.get());
		else
			return ResponseEntity.notFound().build();
	}
	
	@GetMapping("/filtrarPorTexto")
	public ResponseEntity<List<Categoria>> filtrarCategoriaPorTexto(@RequestParam("texto") String texto){
		return ResponseEntity.ok(categoriaServico.filtrarCategoriaPorTexto(texto));
	}
	@PostMapping
	public ResponseEntity<Categoria> salvar(@RequestBody Categoria categoria){
		return ResponseEntity.status(HttpStatus.CREATED).body(categoriaServico.salvar(categoria));
	}
	@PutMapping("/{codigo}")
	public ResponseEntity<Categoria> atualizar(@PathVariable("codigo") Long codigo, @Valid @RequestBody Categoria categoria) {
		return ResponseEntity.ok(categoriaServico.atualizar(codigo, categoria));
	}
	@DeleteMapping("/{codigo}")
	@ResponseStatus(HttpStatus.NO_CONTENT)	
	public void deletar(@PathVariable("codigo") Long codigo) {
		categoriaServico.deletar(codigo);
	}
}
