package br.com.wsb.DonJose.model;

import java.io.Serializable;
import java.util.*;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.*;

@Entity
@Table(name="lista_de_desejos")
public class ListaDeDesejos implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@OneToOne
    @MapsId
    @JoinColumn(name = "cliente_id")
	@JsonIgnoreProperties("listaDeDesejos")
	private Cliente cliente;
	
	@ManyToMany(mappedBy = "listaDesejos", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JsonIgnoreProperties({"nome", "descricao", "img", "preco", "estoque", "categoria", "pedidos", "qtdPedidoProduto", "listaDesejos","plu","promocao"})
	private List<Produto> produtos = new ArrayList<>();

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public List<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}

	
}
