package br.com.wsb.DonJose.model;


import java.time.*;
import java.util.*;


import javax.persistence.*;

import javax.validation.constraints.*;

import com.fasterxml.jackson.annotation.*;


@Entity
@Table(name = "categoria")
public class Categoria {
	
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "nome", unique = true, nullable = false)
	@Size(max = 50)
	private String nome;
	
	private boolean promocao=false;
	
	private int porcentagemPromocao;
	
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
	private LocalDateTime inicioPromocao;

	@JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
	private LocalDateTime fimPromocao;
		
	
	@Temporal(TemporalType.TIMESTAMP)
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
	private Date data = new java.sql.Date(System.currentTimeMillis());
	
	@OneToMany(mappedBy = "categoria", cascade = CascadeType.REMOVE)
	@JsonIgnoreProperties("categoria")
	private List<Produto> produtos;

	public long getId() {
		return id;
	}

	public void setId(long id) {
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

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public boolean isPromocao() {
		return promocao;
	}

	public void setPromocao(boolean promocao) {
		this.promocao = promocao;
	}

	public int getPorcentagemPromocao() {
		return porcentagemPromocao;
	}

	public void setPorcentagemPromocao(int porcentagemPromocao) {
		this.porcentagemPromocao = porcentagemPromocao;
	}

	public LocalDateTime getInicioPromocao() {
		return inicioPromocao;
	}

	public void setInicioPromocao(LocalDateTime inicioPromocao) {
		this.inicioPromocao = inicioPromocao;
	}

	public LocalDateTime getFimPromocao() {
		return fimPromocao;
	}

	public void setFimPromocao(LocalDateTime fimPromocao) {
		this.fimPromocao = fimPromocao;
	}



	
	
}
