package rocha.andre.grafos.models;

import java.util.ArrayList;
import java.util.List;

public class Rede {
    private List<CentroDados> centroDados;
    private List<Conexao> conexoes;

    public Rede() {
        this.centroDados = new ArrayList<>();
        this.conexoes = new ArrayList<>();
    }

    public List<CentroDados> getCentroDados() {
        return centroDados;
    }

    public List<Conexao> getConexoes() {
        return conexoes;
    }

    public void setCentroDados(List<CentroDados> centroDados) {
        this.centroDados = centroDados;
    }
}
