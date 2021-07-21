package br.com.wsb.DonJose.model;

import java.io.Serializable;
import java.util.*;
import javax.persistence.*;
import javax.validation.constraints.*;

import com.fasterxml.jackson.annotation.*;


@Entity
@Table(name = "produto")
public class Produto implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@NotNull
	@Size(max = 50)
	private String nome;

	@NotNull
	@Size(max = 250)
	private String descricao;

	@NotNull
	private String img;

	@NotNull
	@Digits(integer = 5, fraction = 2)
	private double preco;

	private long plu;

	@NotNull
	private int estoque;

	private int qtdPedidoProduto;

	@JsonIgnore
	@ManyToMany
	@JoinTable(name = "PRODUTO_CATEGORIA", joinColumns = @JoinColumn(name = "produto_id"), inverseJoinColumns = @JoinColumn(name = "categoria_id"))
	private List<Categoria> categorias = new ArrayList<>();

	@JsonIgnore
	@OneToMany(mappedBy = "id.pedido")
	private Set<ItemPedido> itens = new HashSet<>();

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(name = "produto_lista", joinColumns = @JoinColumn(name = "produto_id"), inverseJoinColumns = @JoinColumn(name = "lista_id"))
	@JsonIgnoreProperties({ "produtos", "cliente" })
	private List<ListaDeDesejos> listaDesejos = new ArrayList<>();

	@JsonIgnore
	public List<Pedido> getPedidos() {
		List<Pedido> lista = new ArrayList<>();
		for (ItemPedido x : itens) {
			lista.add(x.getPedido());
		}
		return lista;
	}
	
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

	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}

	public long getPlu() {
		return plu;
	}

	public void setPlu(long plu) {
		this.plu = plu;
	}

	public Set<ItemPedido> getItens() {
		return itens;
	}

	public void setItens(Set<ItemPedido> itens) {
		this.itens = itens;
	}

	public int getEstoque() {
		return estoque;
	}

	public void setEstoque(int estoque) {
		this.estoque = estoque;
	}

	public int getQtdPedidoProduto() {
		return qtdPedidoProduto;
	}

	public void setQtdPedidoProduto(int qtdPedidoProduto) {
		this.qtdPedidoProduto = qtdPedidoProduto;
	}

	public List<Categoria> getCategorias() {
		return categorias;
	}

	public void setCategorias(List<Categoria> categorias) {
		this.categorias = categorias;
	}

	public List<ListaDeDesejos> getListaDesejos() {
		return listaDesejos;
	}

	public void setListaDesejos(List<ListaDeDesejos> listaDesejos) {
		this.listaDesejos = listaDesejos;
	}

	public Produto() {
		super();
	}

	public Produto(long id, @NotNull @Size(max = 50) String nome, @NotNull @Size(max = 250) String descricao,
			@NotNull String img, @NotNull @Digits(integer = 5, fraction = 2) double preco, long plu,
			@NotNull int estoque, int qtdPedidoProduto) {
		super();
		this.id = id;
		this.nome = nome;
		this.descricao = descricao;
		this.img = img;
		this.preco = preco;
		this.plu = plu;
		this.estoque = estoque;
		this.qtdPedidoProduto = qtdPedidoProduto;
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
		Produto other = (Produto) obj;
		if (id != other.id)
			return false;
		return true;
	}

}
