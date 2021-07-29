package br.com.wsb.DonJose;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import br.com.wsb.DonJose.model.Categoria;
import br.com.wsb.DonJose.model.Cidade;
import br.com.wsb.DonJose.model.Cliente;
import br.com.wsb.DonJose.model.Endereco;
import br.com.wsb.DonJose.model.Estado;
import br.com.wsb.DonJose.model.ItemPedido;
import br.com.wsb.DonJose.model.Pagamento;
import br.com.wsb.DonJose.model.PagamentoComBoleto;
import br.com.wsb.DonJose.model.PagamentoComCartao;
import br.com.wsb.DonJose.model.Pedido;
import br.com.wsb.DonJose.model.Produto;
import br.com.wsb.DonJose.model.enums.EstadoPagamento;
import br.com.wsb.DonJose.model.enums.Perfil;
import br.com.wsb.DonJose.model.enums.TipoCliente;
import br.com.wsb.DonJose.repository.CategoriaRepository;
import br.com.wsb.DonJose.repository.ClienteRepository;
import br.com.wsb.DonJose.repository.EnderecoRepository;
import br.com.wsb.DonJose.repository.ItemPedidoRepository;
import br.com.wsb.DonJose.repository.PagamentoRepository;
import br.com.wsb.DonJose.repository.PedidoRepository;
import br.com.wsb.DonJose.repository.ProdutoRepository;

@SpringBootApplication
@EnableFeignClients
public class DonJoseApplication implements CommandLineRunner  {
	
	
	public static void main(String[] args) {
		SpringApplication.run(DonJoseApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {	
	}
	
}