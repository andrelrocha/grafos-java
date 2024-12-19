package rocha.andre.grafos.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rocha.andre.grafos.algo.BuscaEmLargura;
import rocha.andre.grafos.models.Conexao;
import rocha.andre.grafos.models.Rede;
import rocha.andre.grafos.models.CentroDados;

import java.util.ArrayList;
import java.util.List;

@Service
public class MainService {
    @Autowired
    private BuscaEmLargura buscaEmLargura;
    @Autowired
    private RedeService redeService;


    public void runMain() {
        var grafo = new Rede();

        var numVertices = 10;

        List<CentroDados> vertices = new ArrayList<>();
        for (int i = 1; i <= numVertices; i++) {
            CentroDados centroDados = new CentroDados(i);
            vertices.add(centroDados);
        }

        grafo.setCentroDados(vertices);

        redeService.adicionarConexoes(new Conexao(vertices.get(0), vertices.get(1), 5), grafo.getConexoes());  // 1 -> 2
        redeService.adicionarConexoes(new Conexao(vertices.get(0), vertices.get(2), 10), grafo.getConexoes()); // 1 -> 3
        redeService.adicionarConexoes(new Conexao(vertices.get(1), vertices.get(3), 3), grafo.getConexoes());  // 2 -> 4
        redeService.adicionarConexoes(new Conexao(vertices.get(1), vertices.get(4), 8), grafo.getConexoes());  // 2 -> 5
        redeService.adicionarConexoes(new Conexao(vertices.get(2), vertices.get(5), 2), grafo.getConexoes());  // 3 -> 6
        redeService.adicionarConexoes(new Conexao(vertices.get(3), vertices.get(6), 7), grafo.getConexoes());  // 4 -> 7
        redeService.adicionarConexoes(new Conexao(vertices.get(0), vertices.get(7), 4), grafo.getConexoes());  // 5 -> 8
        //grafoService.adicionarAresta(new Aresta(vertices.get(4), vertices.get(7), 1), grafo.getArestas());  // 5 -> 8
        //grafoService.adicionarAresta(new Aresta(vertices.get(5), vertices.get(8), 6), grafo.getArestas());  // 6 -> 9
        //grafoService.adicionarAresta(new Aresta(vertices.get(6), vertices.get(9), 4), grafo.getArestas());  // 7 -> 10
        //grafoService.adicionarAresta(new Aresta(vertices.get(7), vertices.get(9), 9), grafo.getArestas());  // 8 -> 10

        System.out.println("vertices no grafo:");
        for (var v : grafo.getCentroDados()) {
            System.out.println("vertice: " + v.getId());
        }

        System.out.println("\n");
        System.out.println("arestas no grafo:");
        for (var a : grafo.getConexoes()) {
            System.out.println("aresta: (Origem " + a.getOrigem().getId() + "), (Destino " + a.getDestino().getId() + "), (Peso " + a.getCusto() + ")");
        }

        System.out.println("\nArestas adjacentes ao vértice 2:");
        for (var a : vertices.get(1).getConexoes()) {
            System.out.println("Conecta " + a.getOrigem().getId() + " e " + a.getDestino().getId() + " com peso " + a.getCusto());
        }

        System.out.println("\nbusca em largura a partir do vértice 1:");
        var ordemDeVisita = buscaEmLargura.buscaEmLargura(vertices.get(0));
        for (var v : ordemDeVisita) {
            System.out.println("vertice visitado: " + v.getId());
        }


        ////////////////////// arvore geradora minima - kruskal
        List<Conexao> arvoreGeradoraMinima = redeService.kruskal(numVertices, grafo.getConexoes());

        System.out.println("\nArvore Geradora Minima gerada pelo Kruskal:");
        for (var a : arvoreGeradoraMinima) {
            System.out.println("Aresta: Origem " + a.getOrigem().getId() + ", Destino " + a.getDestino().getId() + ", Peso " + a.getCusto());
        }

    }

    public void primeiraQuestaoMain() {
        RedeService redeService = new RedeService();

        // Criando vértices (centros de dados)
        CentroDados centroA = new CentroDados(1);  // Centro de Dados A
        CentroDados centroB = new CentroDados(2);  // Centro de Dados B
        CentroDados centroC = new CentroDados(3);  // Centro de Dados C

        // Lista de vértices do grafo
        List<CentroDados> vertices = List.of(centroA, centroB, centroC);

        // Criando arestas (conexões entre centros de dados com custos)
        Conexao conexao1 = new Conexao(centroA, centroB, 10);  // Custo entre A e B
        Conexao conexao2 = new Conexao(centroB, centroC, 20);  // Custo entre B e C
        Conexao conexao3 = new Conexao(centroC, centroA, 15);  // Custo entre C e A

        // Lista de arestas do grafo
        List<Conexao> conexaos = List.of(conexao1, conexao2, conexao3);

        // Adicionando as arestas ao grafo
        redeService.adicionarConexoes(conexao1, conexaos);
        redeService.adicionarConexoes(conexao2, conexaos);
        redeService.adicionarConexoes(conexao3, conexaos);

        // Exibindo o grafo
        redeService.exibirRede(vertices, conexaos);
    }
}
