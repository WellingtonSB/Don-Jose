package br.com.wsb.DonJose.model;

import java.io.Serializable;
import java.sql.Date;
import java.util.Set;
import java.util.HashSet;

import javax.persistence.*;
import javax.validation.constraints.Digits;

import com.fasterxml.jackson.annotation.*;

@Entity
@Table(name = "pedido")
public class Pedido implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@JsonFormat(pattern = "dd/MM/yyyy HH:mm")
	private Date instante;

	private int numeroPedido;

	@Digits(integer = 5, fraction = 2)
	private double frete = 10;

	@ManyToOne
	@JoinColumn(name = "cliente_id")
	private Cliente cliente;

	@OneToOne(cascade = CascadeType.ALL, mappedBy = "pedido")
	private Pagamento pagamento;

	@OneToMany(mappedBy = "id.pedido")
	private Set<ItemPedido> itens = new HashSet<>();

	public long getId() {
		return id;
	}

	public Date getInstante() {
		return instante;
	}

	public void setInstante(Date instante) {
		this.instante = instante;
	}

	public int getNumeroPedido() {
		return numeroPedido;
	}

	public void setNumeroPedido(int numeroPedido) {
		this.numeroPedido = numeroPedido;
	}

	public double getFrete() {
		return frete;
	}

	public void setFrete(double frete) {
		this.frete = frete;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Pagamento getPagamento() {
		return pagamento;
	}

	public void setPagamento(Pagamento pagamento) {
		this.pagamento = pagamento;
	}

	
	public Set<ItemPedido> getItens() {
		return itens;
	}

	public void setItens(Set<ItemPedido> itens) {
		this.itens = itens;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Pedido(long id, Date instante, int numeroPedido, @Digits(integer = 5, fraction = 2) double frete,
			Cliente cliente, Pagamento pagamento) {
		super();
		this.id = id;
		this.instante = instante;
		this.numeroPedido = numeroPedido;
		this.frete = frete;
		this.cliente = cliente;
		this.pagamento = pagamento;
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
		Pedido other = (Pedido) obj;
		if (id != other.id)
			return false;
		return true;
	}

}
