package com.controledegastos.entidade;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "categoria")//ligar a tabela categoria
public class Categoria implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id//chave primaria
	@GeneratedValue(strategy = GenerationType.IDENTITY)//auto-incremento
	private Long codigo;
	
	@NotBlank(message = "Nome")
	@Column(name = "nome")
	private String nome;

	public Long getCodigo() {
		return codigo;
	}
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	//botao direito, source hashcode
	@Override
	public int hashCode() {
		return Objects.hash(nome);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Categoria)) {
			return false;
		}
		Categoria other = (Categoria) obj;
		return Objects.equals(nome, other.nome);
	}
	
	
	
}
