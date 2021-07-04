package br.com.wsb.DonJose.model;

import java.util.*;
import javax.persistence.*;
import javax.validation.constraints.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "produto")
public class Produto {

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

	private double promocao;
	
	@ManyToOne
	@JsonIgnoreProperties("produtos")
	private Categoria categoria;

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(name = "produto_pedido", joinColumns = @JoinColumn(name = "produto_id"), inverseJoinColumns = @JoinColumn(name = "pedido_id"))
	@JsonIgnoreProperties({ "data", "valorTotal", "produtos", "cliente", "qtdProduto","numeroPedido","status","frete"})
	private List<Pedido> pedidos = new ArrayList<>();

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(name = "produto_lista", joinColumns = @JoinColumn(name = "produto_id"), inverseJoinColumns = @JoinColumn(name = "lista_id"))
	@JsonIgnoreProperties({ "produtos", "cliente" })
	private List<ListaDeDesejos> listaDesejos  = new ArrayList<>();

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

	public double getPromocao() {
		return promocao;
	}

	public void setPromocao(double promocao) {
		this.promocao = promocao;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public List<Pedido> getPedidos() {
		return pedidos;
	}

	public void setPedidos(List<Pedido> pedidos) {
		this.pedidos = pedidos;
	}

	public List<ListaDeDesejos> getListaDesejos() {
		return listaDesejos;
	}

	public void setListaDesejos(List<ListaDeDesejos> listaDesejos) {
		this.listaDesejos = listaDesejos;
	}

	
	
}
