package rocha.andre.grafos.models;

import java.util.*;

public class CentroDados {
    private int id;
    private List<Conexao> conexoes;
    public Cor cor;
    public int distancia;
    public CentroDados pai;

    public CentroDados(int id) {
        this.id = id;
        this.conexoes = new ArrayList<>();
        this.cor = Cor.BRANCO;
        this.distancia = Integer.MAX_VALUE;
        this.pai = null;
    }

    public void adicionarConexao(Conexao conexao) {
        if (!conexoes.contains(conexao)) {
            conexoes.add(conexao);
        }
    }

    public int getId() {
        return id;
    }

    public List<Conexao> getConexoes() {
        return conexoes;
    }
}
