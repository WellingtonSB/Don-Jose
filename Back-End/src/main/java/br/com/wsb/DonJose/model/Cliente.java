package br.com.wsb.DonJose.model;

import java.util.*;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.hibernate.validator.constraints.br.CPF;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "cliente")
public class Cliente {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Size(max = 50)
	private String nome;

	@Size(max = 11)
	private String celular;

	@Email
	private String email;

	@CPF
	private String cpf;

	private String cep;

	private String senha;

	private String endereco;

	private String numero;

	private String complemento;

	private String bairro;

	private String cidade;

	private String estado;

	private String foto;

	@OneToOne(mappedBy = "cliente", cascade = CascadeType.ALL)
	@PrimaryKeyJoinColumn
	@JsonIgnoreProperties("cliente")
	private Pedido pedidos;

	@OneToOne(mappedBy = "cliente", cascade = CascadeType.ALL)
	@PrimaryKeyJoinColumn
	@JsonIgnoreProperties("cliente")
	private Carrinho carrinho;

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

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public Pedido getPedidos() {
		return pedidos;
	}

	public void setPedidos(Pedido pedidos) {
		this.pedidos = pedidos;
	}

	public Carrinho getCarrinho() {
		return carrinho;
	}

	public void setCarrinho(Carrinho carrinho) {
		this.carrinho = carrinho;
	}

	public Cliente(String cep, String endereco, String numero, String complemento, String bairro, String cidade,
			String estado) {
		super();
		this.cep = cep;
		this.endereco = endereco;
		this.numero = numero;
		this.complemento = complemento;
		this.bairro = bairro;
		this.cidade = cidade;
		this.estado = estado;
	}

}
