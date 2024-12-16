package rocha.andre.grafos.models;

import lombok.Getter;

import java.util.*;

public class Vertice {
    private int id;
    private List<Aresta> arestasAdj;
    public Cor cor;
    public int distancia;
    public Vertice pai;

    public Vertice(int id) {
        this.id = id;
        this.arestasAdj = new ArrayList<>();
        this.cor = Cor.BRANCO;
        this.distancia = Integer.MAX_VALUE;
        this.pai = null;
    }

    public void adicionarAresta(Aresta aresta) {
        if (!arestasAdj.contains(aresta)) {
            arestasAdj.add(aresta);
        }
    }


    public int getId() {
        return id;
    }

    public List<Aresta> getArestasAdj() {
        return arestasAdj;
    }
}
