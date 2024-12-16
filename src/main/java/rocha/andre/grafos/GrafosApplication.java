package rocha.andre.grafos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import rocha.andre.grafos.models.Aresta;
import rocha.andre.grafos.models.Grafo;
import rocha.andre.grafos.models.Vertice;

@SpringBootApplication
public class GrafosApplication {

	public static void main(String[] args) {
		SpringApplication.run(GrafosApplication.class, args);

		var vertice1 = new Vertice(1);
		var vertice2 = new Vertice(2);
		var vertice3 = new Vertice(3);

		var grafo = new Grafo();

		grafo.adicionarVertice(vertice1);
		grafo.adicionarVertice(vertice2);
		grafo.adicionarVertice(vertice3);

		var aresta1 = new Aresta(vertice1, vertice2, 5);
		var aresta2 = new Aresta(vertice2, vertice3, 10);

		grafo.adicionarAresta(aresta1);
		grafo.adicionarAresta(aresta2);

		System.out.println("Vértices no grafo:");
		for (var v : grafo.getVertices()) {
			System.out.println("Vértice ID: " + v.getId());
		}

		System.out.println("\nArestas no grafo:");
		for (var a : grafo.getArestas()) {
			System.out.println("Aresta: Origem " + a.getOrigem().getId() + ", Destino " + a.getDestino().getId() + ", Peso " + a.getPeso());
		}

		System.out.println("\nArestas adjacentes ao vértice 2:");
		for (var a : vertice2.getArestasAdj()) {
			System.out.println("Conecta " + a.getOrigem().getId() + " e " + a.getDestino().getId() + " com peso " + a.getPeso());
		}
	}

}
