package com.controledegastos.servico;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.controledegastos.entidade.Lancamento;
import com.controledegastos.excecao.RegraNegocioException;
import com.controledegastos.repositorio.LancamentoRepositorio;

@Service
public class LancamentoServico {
	
	@Autowired
	private LancamentoRepositorio lancamentoRepositorio;
	
	public List<Lancamento> buscarTodos(){
		return lancamentoRepositorio.findAll();
	}
	
	public Optional<Lancamento> buscarPorCodigo(Long codigo){
		return lancamentoRepositorio.findById(codigo);
	}
	
	public Lancamento salvar(Lancamento lancamento) {
		return lancamentoRepositorio.save(lancamento);
	}
	
	public Lancamento atualizar(Long codigo, Lancamento lancamento) {
		Lancamento lancamentoAtualizar = verificarLancamentoExiste(codigo);
		BeanUtils.copyProperties(lancamento, lancamentoAtualizar, "codigo");
		return lancamentoRepositorio.save(lancamentoAtualizar);
	}
	private Lancamento verificarLancamentoExiste(Long codigo) {
		Optional<Lancamento> lancamento = buscarPorCodigo(codigo);
		if(!lancamento.isPresent()) {
			throw new RegraNegocioException(String.format("O lançamento de código %s não existe",codigo));
		}
		return lancamento.get();
	}
	 public void deletar (Long codigo) {
		 verificarLancamentoExiste(codigo);
		 lancamentoRepositorio.deleteById(codigo);
	 }
}
