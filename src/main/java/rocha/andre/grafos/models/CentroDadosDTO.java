package rocha.andre.grafos.models;

import java.util.List;

public record CentroDadosDTO(int origem, List<Adjacencia> vizinhos) {
    public record Adjacencia(int destino, int custo) {}
}
