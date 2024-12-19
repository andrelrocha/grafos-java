package rocha.andre.grafos.models;

import java.util.Objects;

public class Conexao implements Comparable<Conexao> {
    private CentroDados origem;
    private CentroDados destino;
    private int custo;

    public Conexao(CentroDados origem, CentroDados destino, int custo) {
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
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Conexao other = (Conexao) obj;

        // Verifica se os vértices são iguais, independente da ordem
        return (Objects.equals(origem, other.origem) && Objects.equals(destino, other.destino)) ||
                (Objects.equals(origem, other.destino) && Objects.equals(destino, other.origem));
    }

    @Override
    public int compareTo(Conexao o) {
        return Integer.compare(this.custo, o.custo);
    }
}
