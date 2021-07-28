package br.com.wsb.DonJose.model;

import java.io.Serializable;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.Set;
import java.util.Date;
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
	private Integer id;

	@JsonFormat(pattern="dd/MM/yyyy HH:mm")
	private Date instante;

	private int numeroPedido;

	@ManyToOne
	@JoinColumn(name = "cliente_id")
	private Cliente cliente;

	@OneToOne(cascade = CascadeType.ALL, mappedBy = "pedido")
	private Pagamento pagamento;

	@OneToMany(mappedBy = "id.pedido")
	private Set<ItemPedido> itens = new HashSet<>();

	public Pedido() {
	}
	
	public Pedido(Integer id, Date instante, int numeroPedido,
			Cliente cliente, Pagamento pagamento) {
		super();
		this.id = id;
		this.instante = instante;
		this.numeroPedido = numeroPedido;
		this.cliente = cliente;
		this.pagamento = pagamento;
	}
	
	public double getValorTotal() {
		double soma = 0.0;
		for (ItemPedido ip : itens) {
			soma = soma + ip.getSubTotal();
		}
		return soma;
	}
	
	public Integer getId() {
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

	public void setId(Integer id) {
		this.id = id;
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

	@Override
	public String toString() {
		NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		StringBuilder builder = new StringBuilder();
		builder.append("Pedido número: ");
		builder.append(getId());
		builder.append(", Instante: ");
		builder.append(sdf.format(getInstante()));
		builder.append(", Cliente: ");
		builder.append(getCliente().getNome());
		builder.append(", Situação do pagamento: ");
		builder.append(getPagamento().getEstado().getDescricao());
		builder.append("\nDetalhes:\n");
		for (ItemPedido ip : getItens()) {
			builder.append(ip.toString());
		}
		builder.append("Valor total: ");
		builder.append(nf.format(getValorTotal()));
		return builder.toString();
	}
	
}
