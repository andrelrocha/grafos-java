package rocha.andre.grafos.service;

import org.springframework.stereotype.Service;
import rocha.andre.grafos.models.Aresta;
import rocha.andre.grafos.models.Vertice;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class GrafoService {
    public void adicionarVertice(Vertice vertice, List<Vertice> vertices) {
        if (!vertices.contains(vertice)) {
            vertices.add(vertice);
        }
    }

    public void adicionarAresta(Aresta aresta, List<Aresta> arestas) {
        if (!arestas.contains(aresta)) {
            arestas.add(aresta);
            aresta.getOrigem().adicionarAresta(aresta);
            aresta.getDestino().adicionarAresta(aresta);
        }
    }


}
