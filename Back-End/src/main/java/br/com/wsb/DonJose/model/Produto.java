package br.com.wsb.DonJose.model;

import java.util.*;
import javax.persistence.*;
import javax.validation.constraints.*;

import org.hibernate.validator.constraints.URL;

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
	private double preco;

	private long plu;

	@NotNull
	private int estoque;

	private int qtdPedidoProduto;

	@ManyToOne
	@JsonIgnoreProperties("produtos")
	private Categoria categoria;

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(name = "produto_pedido", joinColumns = @JoinColumn(name = "produto_id"), inverseJoinColumns = @JoinColumn(name = "pedido_id"))
	@JsonIgnoreProperties({ "data", "valorTotal", "produtos", "cliente", "qtdProduto","numeroPedido","status"})
	private List<Pedido> pedidos = new ArrayList<>();

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(name = "produto_lista", joinColumns = @JoinColumn(name = "produto_id"), inverseJoinColumns = @JoinColumn(name = "lista_id"))
	@JsonIgnoreProperties({ "produtos", "cliente" })
	private List<Carrinho> carrinho = new ArrayList<>();

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

	public List<Carrinho> getCarrinho() {
		return carrinho;
	}

	public void setCarrinho(List<Carrinho> carrinho) {
		this.carrinho = carrinho;
	}

	
}
