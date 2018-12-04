package com.metre;

import com.metre.domain.Categoria;
import com.metre.domain.Cidade;
import com.metre.domain.Cliente;
import com.metre.domain.Endereco;
import com.metre.domain.Estado;
import com.metre.domain.Produto;
import com.metre.domain.enums.TipoCliente;
import com.metre.repositories.CategoriaRepository;
import com.metre.repositories.CidadeRespository;
import com.metre.repositories.ClienteRepository;
import com.metre.repositories.EnderecoRepository;
import com.metre.repositories.EstadoRepository;
import com.metre.repositories.ProdutoRepository;
import com.metre.resources.CategoriaResource;
import com.metre.services.CategoriaService;
import java.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackageClasses = {CategoriaResource.class, CategoriaRepository.class, CategoriaService.class})
public class CursoApplication implements CommandLineRunner {

    @Autowired
    private CategoriaRepository categoriaRepository;
    @Autowired
    private ProdutoRepository produtoRepository;
    @Autowired
    private CidadeRespository cidadeRepository;
    @Autowired
    private EstadoRepository estadoRepository;
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private EnderecoRepository enderecoRepository;

    public static void main(String[] args) {
        SpringApplication.run(CursoApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Categoria cat1 = new Categoria(null, "Informática");
        Categoria cat2 = new Categoria(null, "Escritório");

        Produto p1 = new Produto(null, "Computador", 2000.00);
        Produto p2 = new Produto(null, "Impressora", 800.00);
        Produto p3 = new Produto(null, "Mouse", 80.00);

        cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
        cat1.getProdutos().addAll(Arrays.asList(p2));

        p1.getCategorias().addAll(Arrays.asList(cat1));
        p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
        p3.getCategorias().addAll(Arrays.asList(cat1));

        Estado est1 = new Estado(null, "Minas Gerais");
        Estado est2 = new Estado(null, "São Paulo");

        Cidade c1 = new Cidade(null, "Uberlandia", est1);
        Cidade c2 = new Cidade(null, "São Paulo", est2);
        Cidade c3 = new Cidade(null, "Campinas", est2);

        Cliente cli1 = new Cliente("Renato Gonçalves", "Contato.renatosi@hotmail.com", "10618296697", TipoCliente.PESSOAFISICA);
        cli1.getTelefones().addAll(Arrays.asList("(34)999753064", "(34)3823-3745"));

        Endereco end1 = new Endereco("Rua Flores", "260", "S/C", "Centro", "38706321", cli1, c1);
        Endereco end2 = new Endereco("Rua Flores", "260", "S/C", "Centro", "38706321", cli1, c2);

        cli1.getEnderecos().addAll(Arrays.asList(end1, end2));

        categoriaRepository.saveAll(Arrays.asList(cat1, cat2));
        produtoRepository.saveAll(Arrays.asList(p1, p2, p3));
        estadoRepository.saveAll(Arrays.asList(est1, est2));
        cidadeRepository.saveAll(Arrays.asList(c1, c2, c3));
        clienteRepository.save(cli1);
        enderecoRepository.saveAll(Arrays.asList(end1, end2));

    }
}
