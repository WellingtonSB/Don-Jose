package br.com.wsb.DonJose.dto;

import java.io.Serializable;

import br.com.wsb.DonJose.model.Produto;

public class ProdutoDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;
	private String nome;
	private String descricao;
	private String img;
	private double preco;
	private long plu;
	private int estoque;

	public ProdutoDTO() {
	}

	public ProdutoDTO(Produto obj) {
		super();
		id = obj.getId();
		nome = obj.getNome();
		descricao = obj.getDescricao();
		img = obj.getImg();
		preco = obj.getPreco();
		plu = obj.getPlu();
		estoque = obj.getEstoque();
	}

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

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public long getPlu() {
		return plu;
	}

	public void setPlu(long plu) {
		this.plu = plu;
	}

	public int getEstoque() {
		return estoque;
	}

	public void setEstoque(int estoque) {
		this.estoque = estoque;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}

}