package rocha.andre.grafos.models;

import lombok.Getter;

@Getter
public class Aresta implements Comparable<Aresta> {

    private Vertice origem;
    private Vertice destino;
    private int peso;

    public Aresta(Vertice origem, Vertice destino, int peso) {
        this.origem = origem;
        this.destino = destino;
        this.peso = peso;
    }

    public Vertice getOrigem() {
        return origem;
    }

    public Vertice getDestino() {
        return destino;
    }

    public int getPeso() {
        return peso;
    }

    @Override
    public int compareTo(Aresta o) {
        return Integer.compare(this.peso, o.peso);
    }
}
