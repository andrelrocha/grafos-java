package rocha.andre.grafos.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rocha.andre.grafos.algo.BuscaEmLargura;
import rocha.andre.grafos.models.Aresta;
import rocha.andre.grafos.models.Grafo;
import rocha.andre.grafos.models.Vertice;

import java.util.ArrayList;
import java.util.List;

@Service
public class MainService {
    @Autowired
    private BuscaEmLargura buscaEmLargura;
    @Autowired
    private GrafoService grafoService;


    public void runMain() {
        var grafo = new Grafo();

        var numVertices = 10;

        List<Vertice> vertices = new ArrayList<>();
        for (int i = 1; i <= numVertices; i++) {
            Vertice vertice = new Vertice(i);
            vertices.add(vertice);
        }

        grafo.setVertices(vertices);

        grafoService.adicionarAresta(new Aresta(vertices.get(0), vertices.get(1), 5), grafo.getArestas());  // 1 -> 2
        grafoService.adicionarAresta(new Aresta(vertices.get(0), vertices.get(2), 10), grafo.getArestas()); // 1 -> 3
        grafoService.adicionarAresta(new Aresta(vertices.get(1), vertices.get(3), 3), grafo.getArestas());  // 2 -> 4
        grafoService.adicionarAresta(new Aresta(vertices.get(1), vertices.get(4), 8), grafo.getArestas());  // 2 -> 5
        grafoService.adicionarAresta(new Aresta(vertices.get(2), vertices.get(5), 2), grafo.getArestas());  // 3 -> 6
        grafoService.adicionarAresta(new Aresta(vertices.get(3), vertices.get(6), 7), grafo.getArestas());  // 4 -> 7
        grafoService.adicionarAresta(new Aresta(vertices.get(0), vertices.get(7), 4), grafo.getArestas());  // 5 -> 8
        //grafoService.adicionarAresta(new Aresta(vertices.get(4), vertices.get(7), 1), grafo.getArestas());  // 5 -> 8
        //grafoService.adicionarAresta(new Aresta(vertices.get(5), vertices.get(8), 6), grafo.getArestas());  // 6 -> 9
        //grafoService.adicionarAresta(new Aresta(vertices.get(6), vertices.get(9), 4), grafo.getArestas());  // 7 -> 10
        //grafoService.adicionarAresta(new Aresta(vertices.get(7), vertices.get(9), 9), grafo.getArestas());  // 8 -> 10

        System.out.println("vertices no grafo:");
        for (var v : grafo.getVertices()) {
            System.out.println("vertice: " + v.getId());
        }

        System.out.println("\n");
        System.out.println("arestas no grafo:");
        for (var a : grafo.getArestas()) {
            System.out.println("aresta: (Origem " + a.getOrigem().getId() + "), (Destino " + a.getDestino().getId() + "), (Peso " + a.getPeso() + ")");
        }

        System.out.println("\nArestas adjacentes ao vértice 2:");
        for (var a : vertices.get(1).getArestasAdj()) {
            System.out.println("Conecta " + a.getOrigem().getId() + " e " + a.getDestino().getId() + " com peso " + a.getPeso());
        }

        System.out.println("\nbusca em largura a partir do vértice 1:");
        var ordemDeVisita = buscaEmLargura.buscaEmLargura(vertices.get(0));
        for (var v : ordemDeVisita) {
            System.out.println("vertice visitado: " + v.getId());
        }


        ////////////////////// KRUSKAL

    }
}
