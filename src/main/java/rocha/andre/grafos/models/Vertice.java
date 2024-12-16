package rocha.andre.grafos.models;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

public class Vertice {
    private int id;
    private List<Aresta> arestasAdj;

    public Vertice(int id) {
        this.id = id;
        this.arestasAdj = new ArrayList<>();
    }

    public void adicionarAresta(Aresta aresta) {
        if (!arestasAdj.contains(aresta)) {
            arestasAdj.add(aresta);
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Aresta> getArestasAdj() {
        return arestasAdj;
    }

    public void setArestasAdj(List<Aresta> arestasAdj) {
        this.arestasAdj = arestasAdj;
    }
}
