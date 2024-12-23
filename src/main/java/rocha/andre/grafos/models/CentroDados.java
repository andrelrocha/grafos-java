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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CentroDados that = (CentroDados) o;
        return id == that.id;
    }

    @Override
    public String toString() {
        return "CentroDados{" +
                "id=" + id +
                ", conexoes=" + conexoes +
                ", cor=" + cor +
                ", distancia=" + distancia +
                ", pai=" + pai +
                '}';
    }
}
