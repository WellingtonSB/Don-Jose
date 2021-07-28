package br.com.wsb.DonJose.model;


import java.io.Serializable;
import java.time.*;
import java.util.*;


import javax.persistence.*;

import javax.validation.constraints.*;

import com.fasterxml.jackson.annotation.*;


@Entity
@Table(name = "categoria")
public class Categoria implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String nome;
	 
	@ManyToMany(mappedBy="categorias")
	private List<Produto> produtos = new ArrayList<>();

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}

	

	public Categoria() {
		super();
	}

	public Categoria(Integer id,String nome) {
		super();
		this.id = id;
		this.nome = nome;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Categoria other = (Categoria) obj;
		if (id != other.id)
			return false;
		return true;
	}

	
}
