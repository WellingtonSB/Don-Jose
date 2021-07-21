package br.com.wsb.DonJose.service;

import java.time.*;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.wsb.DonJose.model.*;

import br.com.wsb.DonJose.repository.*;

@Service
public class ProdutoService {

	@Autowired
	private PedidoRepository pedidoRepository;

	@Autowired
	private ProdutoRepository produtoRepository;

	@Autowired
	private ListaDeDesejosRepository listaDeDesejoRepository;

	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	double a = 0;
	int posicao = 0;

	public Produto cadastrarProduto(Produto produto) {
		List<Produto> produtos = produtoRepository.findAll();
		int last = produtos.size() - 1;

		if (produtos.size() != 0) {
			produto.setPlu(produtos.get(last).getPlu() + 1);

		} else {
			produto.setPlu(1);
		}

		return produtoRepository.save(produto);
	}

	public Produto compraProduto(long idProduto, long idPedido) {

		Optional<Produto> produtoExistente = produtoRepository.findById(idProduto);
		Optional<Pedido> pedidoExistente = pedidoRepository.findById(idPedido);
	
		if (produtoExistente.isPresent() && pedidoExistente.isPresent() && produtoExistente.get().getEstoque() == 0) {
			System.out.println("estoque vazio!");
			return null;// melhorar logica
		}

		if (produtoExistente.isPresent() && pedidoExistente.isPresent() && produtoExistente.get().getEstoque() >= 0
				&& !(pedidoExistente.get().getProdutos().isEmpty())) {

			produtoExistente.get().getPedidos().add(pedidoExistente.get());

			int contador = 0;

			long[] vetor = new long[pedidoExistente.get().getProdutos().size()];

			for (int i = 0; i < pedidoExistente.get().getProdutos().size(); i++) {
				vetor[i] = pedidoExistente.get().getProdutos().get(i).getId();

				if (vetor[i] == produtoExistente.get().getId()) {
					contador++;
				}
			}
			
			pedidoExistente.get().setStatus("Aguardando aprovacao");
			pedidoExistente.get().setValorTotal(pedidoExistente.get().getValorTotal() - (produtoExistente.get().getPreco() * contador));

			contador++;
			
			produtoExistente.get().setQtdPedidoProduto(contador);

			pedidoExistente.get().setNumeroPedido(pedidoExistente.get().getNumeroPedido() + 1);
			// produtoExistente.get().setEstoque(produtoExistente.get().getEstoque() - 1);
			pedidoRepository.save(pedidoExistente.get());

			
			produtoRepository.save(produtoExistente.get());
			pedidoRepository.save(pedidoExistente.get());
			pedidoRepository.save(pedidoExistente.get()).getValorTotal();

			return produtoRepository.save(produtoExistente.get());

		} else if (produtoExistente.isPresent() && pedidoExistente.isPresent()) {

			produtoExistente.get().getPedidos().add(pedidoExistente.get());
			produtoExistente.get().setQtdPedidoProduto(1);
			// produtoExistente.get().setEstoque(produtoExistente.get().getEstoque() - 1);

			pedidoExistente.get().setValorTotal(pedidoExistente.get().getFrete() + pedidoExistente.get().getValorTotal()
					+ (produtoExistente.get().getPreco() * produtoExistente.get().getQtdPedidoProduto()));

			produtoRepository.save(produtoExistente.get());
			pedidoRepository.save(pedidoExistente.get());
			pedidoRepository.save(pedidoExistente.get()).getValorTotal();

			return produtoRepository.save(produtoExistente.get());
		}
		return null;
	}

	public void deletarProduto(long idProduto, long idPedido) {

		Optional<Produto> produtoExistente = produtoRepository.findById(idProduto);
		Optional<Pedido> pedidoExistente = pedidoRepository.findById(idPedido);

		if (pedidoExistente.get().getProdutos().contains(produtoExistente.get())) {
			produtoExistente.get().getPedidos().remove(pedidoExistente.get());
			produtoExistente.get().setEstoque(produtoExistente.get().getEstoque() + 1);

			int contador = 0;

			long[] vetor = new long[pedidoExistente.get().getProdutos().size()];

			for (int i = 0; i < pedidoExistente.get().getProdutos().size(); i++) {

				vetor[i] = pedidoExistente.get().getProdutos().get(i).getId();

				if (vetor[i] == produtoExistente.get().getId()) {
					contador++;
				}
			}

			produtoExistente.get().setQtdPedidoProduto(contador - 1);
			pedidoExistente.get()
					.setValorTotal(pedidoExistente.get().getValorTotal() - produtoExistente.get().getPreco());

			a = pedidoExistente.get().getValorTotal();
			a = Math.floor(a * 100) / 100;
			pedidoExistente.get().setValorTotal(a);

			if (pedidoExistente.get().getValorTotal() < 0) {
				pedidoExistente.get().setValorTotal(0);
			}

			produtoRepository.save(produtoExistente.get());
			pedidoRepository.save(pedidoExistente.get());
			pedidoRepository.save(pedidoExistente.get()).getValorTotal();

		}

	}


	public Produto adicionarProdutoListaDeDesejo(long idProduto, long idListaDeDesejo) {
		Optional<Produto> produtoExistente = produtoRepository.findById(idProduto);
		Optional<ListaDeDesejos> listaDeDesejoExistente = listaDeDesejoRepository.findById(idListaDeDesejo);
		
		if(produtoExistente.isPresent() && listaDeDesejoExistente.isPresent() && !(produtoExistente.get().getListaDesejos().contains(listaDeDesejoExistente.get()))) {
			
			produtoExistente.get().getListaDesejos().add(listaDeDesejoExistente.get());		
			produtoRepository.save(produtoExistente.get());
						
			return produtoRepository.save(produtoExistente.get());
			
		}
		
		return null;
		
	}
	
	public Produto removeProdutoListaDeDesejo(long idProduto, long idListaDeDesejo) {
		Optional<Produto> produtoExistente = produtoRepository.findById(idProduto);
		Optional<ListaDeDesejos> listaDeDesejoExistente = listaDeDesejoRepository.findById(idListaDeDesejo);

		if(produtoExistente.get().getListaDesejos().contains(listaDeDesejoExistente.get())) {

			produtoExistente.get().getListaDesejos().remove(listaDeDesejoExistente.get());			
			produtoRepository.save(produtoExistente.get());
			
			return produtoRepository.save(produtoExistente.get());
			
		}		
		return null;		
	}
	

	public List<Produto> pesquisaPorIdDeProdutoNaListaDeDesejos(long idListaDeDesejo, String nome) {
		Optional<ListaDeDesejos> listaDeDesejoExistente = listaDeDesejoRepository.findById(idListaDeDesejo);

		long[] vetor = new long[listaDeDesejoExistente.get().getProdutos().size()];
		
		for(int i = 0; i < vetor.length; i++) {
			
			if(listaDeDesejoExistente.get().getProdutos().get(i).getNome().contains(nome)) {
								return produtoRepository.findAllByNomeContainingIgnoreCase(nome);	
			}			
		}		
		return null;		
	}
	

	public List<Produto> pesquisaPorProdutoNaListaDeDesejos(long idListaDeDesejo) {
		Optional<ListaDeDesejos> listaDeDesejoExistente = listaDeDesejoRepository.findById(idListaDeDesejo);
		
		if(listaDeDesejoExistente.isPresent()) {
			listaDeDesejoExistente.get().getProdutos();
			
			return listaDeDesejoRepository.save(listaDeDesejoExistente.get()).getProdutos();
		}
		return null;		
	}
	

	public List<Produto> pesquisaPorProdutoNoCarrinho(long idPedido) {
		Optional<Pedido> pedidoExistente = pedidoRepository.findById(idPedido);
		
		if(pedidoExistente.isPresent()) {
			pedidoExistente.get().getProdutos();			
			return pedidoRepository.save(pedidoExistente.get()).getProdutos();			
		}		
		return null;
	}

	
	// Melhorar usando POO
	public Categoria verificaPromocao(long idcategoria) {
		Optional<Categoria> categoriaExistente = categoriaRepository.findById(idcategoria);
		LocalTime minutsInicio = LocalTime.of(categoriaExistente.get().getInicioPromocao().getHour(),categoriaExistente.get().getInicioPromocao().getMinute());
		LocalTime minutsFim = LocalTime.of(categoriaExistente.get().getFimPromocao().getHour(),categoriaExistente.get().getFimPromocao().getMinute());

		if (minutsFim.isBefore(LocalTime.now())) {
			categoriaExistente.get().setPromocao(false);
			System.out.println("cabo a promocao");
		}

		return categoriaRepository.save(categoriaExistente.get());
	}

	// Gambiarra(ajustar)
	public void ajusteFrete(long idPedido) {
		Optional<Pedido> pedidoExistente = pedidoRepository.findById(idPedido);
		if (pedidoExistente.get().getFrete() != 0) {
			pedidoExistente.get()
					.setValorTotal(pedidoExistente.get().getFrete() - pedidoExistente.get().getValorTotal());
		}
	}

	public Pedido finalizarPedido(long idProduto, long idPedido) {
		Optional<Produto> produtoExistente = produtoRepository.findById(idProduto);
		Optional<Pedido> pedidoExistente = pedidoRepository.findById(idPedido);
		this.verificaPromocao(produtoExistente.get().getCategoria().getId());
		this.ajusteFrete(idPedido);
		
		pedidoExistente.get().setNumeroPedido(pedidoExistente.get().getNumeroPedido() + 1);
		produtoExistente.get().setEstoque(produtoExistente.get().getEstoque() - 1);

		pedidoExistente.get().setStatus("Iniciado");

		return pedidoRepository.save(pedidoExistente.get());
	}

}
