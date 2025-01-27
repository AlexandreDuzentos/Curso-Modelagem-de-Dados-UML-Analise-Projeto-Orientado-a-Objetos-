package com.alexandre.cursomc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.alexandre.cursomc.domain.Categoria;
import com.alexandre.cursomc.domain.Cidade;
import com.alexandre.cursomc.domain.Estado;
import com.alexandre.cursomc.domain.Produto;
import com.alexandre.cursomc.repositories.CategoriaRepository;
import com.alexandre.cursomc.repositories.CidadeRepository;
import com.alexandre.cursomc.repositories.EstadoRepository;
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
		
		
		for(Categoria cat : categories) {
			categoriaRepository.save(cat);
		}
		
		
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
		
		List<Estado> estados = new ArrayList<>();
		estados.addAll(Arrays.asList(est1, est2));
		
		for(Estado est : estados) {
			estadoRepository.save(est);
		}
		
		List<Cidade> cidades = new ArrayList<>();
		cidades.addAll(Arrays.asList(c1, c2, c3));
		
		for(Cidade c : cidades) {
			cidadeRepository.save(c);
		}
	
			
	}

}
