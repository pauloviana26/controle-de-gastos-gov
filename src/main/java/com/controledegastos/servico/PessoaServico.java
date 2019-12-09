package com.controledegastos.servico;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.controledegastos.entidade.Categoria;
import com.controledegastos.entidade.Pessoa;
import com.controledegastos.excecao.RegraNegocioException;
import com.controledegastos.repositorio.PessoaRepositorio;

@Service
public class PessoaServico {
	
	@Autowired
	private PessoaRepositorio pessoaRepositorio;
	
	public List<Pessoa> buscarTodos(){
		return pessoaRepositorio.findAll();
	}
	
	public Optional<Pessoa> buscarPorId(Long codigo){
		return pessoaRepositorio.findById(codigo);
	}
	
	public Pessoa salvar(Pessoa pessoa) {
		return pessoaRepositorio.save(pessoa);
	}
	
	public Pessoa atualizar(Long codigo, Pessoa pessoa) {
		Optional<Pessoa> PessoaSalvar = buscarPorId(codigo);
		BeanUtils.copyProperties(pessoa, PessoaSalvar, "codigo");
		return pessoaRepositorio.save(PessoaSalvar.get());
	}
	
	public void deletar(Long codigo) {
		pessoaRepositorio.deleteById(codigo);
	}
	
	private Pessoa verificarPessoaExiste(Long codigo) {
		Optional<Pessoa> pessoa = buscarPorId(codigo);
		if(!pessoa.isPresent()) {
			throw new RegraNegocioException(String.format("A pessoa de código %s não foi encontrada", codigo));
		}
		return pessoa.get();
	}
}
