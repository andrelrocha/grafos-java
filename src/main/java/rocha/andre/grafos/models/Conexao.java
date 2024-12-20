package rocha.andre.grafos.models;

import java.util.List;

public class Conexao implements Comparable<Conexao> {
    private CentroDados origem;
    private CentroDados destino;
    private int custo;

    public Conexao(CentroDados origem, CentroDados destino, int custo, List<Conexao> conexoesExistentes) {
        if (conexaoExiste(origem, destino, conexoesExistentes)) {
            throw new IllegalArgumentException("já existe conexao entre esses centros de dados.");
        }
        this.origem = origem;
        this.destino = destino;
        this.custo = custo;
    }

    public CentroDados getOrigem() {
        return origem;
    }

    public CentroDados getDestino() {
        return destino;
    }

    public int getCusto() {
        return custo;
    }

    @Override
    public int compareTo(Conexao o) {
        return Integer.compare(this.custo, o.custo);
    }

    private boolean conexaoExiste(CentroDados origem, CentroDados destino, List<Conexao> conexoesExistentes) {
        return conexoesExistentes.stream().anyMatch(conexao ->
                (conexao.getOrigem().getId() == origem.getId() && conexao.getDestino().getId() == destino.getId()) ||
                        (conexao.getOrigem().getId() == destino.getId() && conexao.getDestino().getId() == origem.getId())
        );
    }
}
