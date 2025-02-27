package com.alexandre.cursomc;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.alexandre.cursomc.domain.Categoria;
import com.alexandre.cursomc.domain.Cidade;
import com.alexandre.cursomc.domain.Cliente;
import com.alexandre.cursomc.domain.Endereco;
import com.alexandre.cursomc.domain.Estado;
import com.alexandre.cursomc.domain.ItemPedido;
import com.alexandre.cursomc.domain.Pagamento;
import com.alexandre.cursomc.domain.PagamentoComBoleto;
import com.alexandre.cursomc.domain.PagamentoComCartao;
import com.alexandre.cursomc.domain.Pedido;
import com.alexandre.cursomc.domain.Produto;
import com.alexandre.cursomc.domain.enums.EstadoPagamento;
import com.alexandre.cursomc.domain.enums.TipoCliente;
import com.alexandre.cursomc.repositories.CategoriaRepository;
import com.alexandre.cursomc.repositories.CidadeRepository;
import com.alexandre.cursomc.repositories.ClienteRepository;
import com.alexandre.cursomc.repositories.EnderecoRepository;
import com.alexandre.cursomc.repositories.EstadoRepository;
import com.alexandre.cursomc.repositories.ItemPedidoRepository;
import com.alexandre.cursomc.repositories.PagamentoRepository;
import com.alexandre.cursomc.repositories.PedidoRepository;
import com.alexandre.cursomc.repositories.ProdutoRepository;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner {
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Autowired
	private EstadoRepository estadoRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private PagamentoRepository pagamentoRepository;
	
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;

	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");
		
		Produto p1 = new Produto(null, "Computador", 2000.00);
		Produto p2 = new Produto(null, "Impressora", 800.00);
		Produto p3 = new Produto(null, "Mouse", 80.00);
		
		/* Associando produtos a categorias */
		cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));
		
		/* Associando categorias a produtos */
		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		
		List<Categoria> categories = new ArrayList<>();
		categories.addAll(Arrays.asList(cat1, cat2));
		
		
		List<Produto> products = new ArrayList<>();
		products.addAll(Arrays.asList(p1, p2, p3));
		
		
		/* Salvando categorias */
		for(Categoria cat : categories) {
			categoriaRepository.save(cat);
		}
		
		
		/* Salvando produtos */
		for(Produto prod : products) {
			produtoRepository.save(prod);
		}
		
		
		Estado est1 = new Estado(null, "Minas Gerais");
		Estado est2 = new Estado(null, "São Paulo");
		
		/* Associando estado a cidade */
		Cidade c1 = new Cidade(null, "Uberlândia", est1);
		Cidade c2 = new Cidade(null, "São Paulo", est2);
		Cidade c3 = new Cidade(null, "Campinas", est2);
		
		/* Associando cidades a estados */
		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2, c3));
		
		/* Salvando estados */
		List<Estado> estados = new ArrayList<>();
		estados.addAll(Arrays.asList(est1, est2));
		
		for(Estado est : estados) {
			estadoRepository.save(est);
		}
		
		/* Salvando cidades */
		List<Cidade> cidades = new ArrayList<>();
		cidades.addAll(Arrays.asList(c1, c2, c3));
		
		for(Cidade c : cidades) {
			cidadeRepository.save(c);
		}
		
		Cliente cli1 = new Cliente(null,"MariaSilva", "Maria@gmail.com", "236446627", TipoCliente.PESSOA_FISICA);
		
		/* Associando telefones a cliente */
		cli1.getTelefones().addAll(Arrays.asList("932360292", "925679483"));
		
		/* Associando cliente e cidade a endereco */
		Endereco e1 = new Endereco(null, "Rua flores", "300", "Apto 303", "Jardim", "245737237", cli1, c1);
		Endereco e2 = new Endereco(null, "Avenida Matos","105", "Sala 800", "Centro", "2646326",cli1, c2);
		
		cli1.getEnderecos().addAll(Arrays.asList(e1, e2));
		
		/* Salvando cliente */
	    clienteRepository.save(cli1);
	    
	    /* salvando endereco */
	    List<Endereco> enderecos = new ArrayList<>();
	    enderecos.addAll(Arrays.asList(e1, e2));
	    
	    for(Endereco e: enderecos) {
	    	enderecoRepository.save(e);
	    }
	    
	    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm");
	    
	    /* O pagamento foi removido do construtor de pedido por que primeiro nós fazemos
	     * um pedido e depois efetuamos o pagamento, isso quer dizer
	     * que a associação entre o pedido e o pagamento será posterior
	     * as instâncias dos pedidos.
	     * */
	    Pedido ped1 = new Pedido(null, sdf.parse("30/09/2017 10:03"), cli1, e1);
	    Pedido ped2 = new Pedido(null, sdf.parse("10/10/2017 19:35"), cli1, e2);
	    
	    /* Associando pagamento a pedido */
	    Pagamento pgto1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6);
	    ped1.setPagamento(pgto1);
	    
	    /* Associando pagamento a pedido */
	    Pagamento pgto2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2, sdf.parse("20/10/2017 00:00"), null);
	    ped2.setPagamento(pgto2);
	    
	    /* Associando pedidos a cliente */
	    cli1.getPedidos().addAll(Arrays.asList(ped1, ped2));
	    
	    /* Salvando pedidos */
	    List<Pedido> pedidos = new ArrayList<>();
	    pedidos.addAll(Arrays.asList(ped1, ped2));
	    
	    for(Pedido pedido : pedidos) {
	    	pedidoRepository.save(pedido);
	    }
	    
	    /* Salvando pagamentos */
	    List<Pagamento> pagamentos = new ArrayList<>();
	    pagamentos.addAll(Arrays.asList(pgto1, pgto2));
	    
	    for(Pagamento pgto : pagamentos) {
	    	pagamentoRepository.save(pgto);
	    }
	    
	    
	    /* Associando pedido e produto a ItemPedido */
	    ItemPedido ip1 = new ItemPedido(ped1, p1, 0.00, 1, 2000.00);
	    ItemPedido ip2 = new ItemPedido(ped1, p3, 0.00, 2, 80.00);
	    ItemPedido ip3 = new ItemPedido(ped2, p2, 100.00, 1, 800.00);
	    
	    /* Associando ItemPedido a pedido */
	    ped1.getItems().addAll(Arrays.asList(ip1, ip2));
	    ped2.getItems().addAll(Arrays.asList(ip3));
	    
	    /* Associando ItemPedido a produto */
	    p1.getItems().addAll(Arrays.asList(ip1));
	    p2.getItems().addAll(Arrays.asList(ip2));
	    
	    /* Salvando ItemPedido */
	    List<ItemPedido> items = new ArrayList<>();
	    items.addAll(Arrays.asList(ip1, ip2, ip3));
	    
	    for(ItemPedido ip : items) {
	    	itemPedidoRepository.save(ip);
	    }
	   
	 		
	}

}
