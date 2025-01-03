package rocha.andre.grafos.algo;

import org.springframework.stereotype.Component;
import rocha.andre.grafos.models.Conexao;
import rocha.andre.grafos.models.Cor;
import rocha.andre.grafos.models.CentroDados;
import rocha.andre.grafos.models.Rede;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

@Component
public class BuscaEmLargura {
    public List<CentroDados> buscaEmLargura(CentroDados centroDados) {
        List<CentroDados> centroDadosVisitados = new ArrayList<>();
        Queue<CentroDados> filaDeVisita = new LinkedList<>();

        centroDados.cor = Cor.CINZA;
        centroDados.distancia = 0;
        filaDeVisita.add(centroDados);
        centroDadosVisitados.add(centroDados);

        while (!filaDeVisita.isEmpty()) {
            var centroDadosEmAnalise = filaDeVisita.poll();

            for (Conexao c : centroDadosEmAnalise.getConexoes()) {
                var vizinho = c.getDestino();
                if (vizinho.cor == Cor.BRANCO) {
                    vizinho.cor = Cor.CINZA;
                    vizinho.distancia = centroDadosEmAnalise.distancia + 1;
                    vizinho.pai = centroDadosEmAnalise;
                    filaDeVisita.add(vizinho);
                    centroDadosVisitados.add(vizinho);
                }

                if (c.getOrigem() != null) {
                    var origem = c.getOrigem();
                    if (origem.cor == Cor.BRANCO) {
                        origem.cor = Cor.CINZA;
                        origem.distancia = centroDadosEmAnalise.distancia + 1;
                        origem.pai = centroDadosEmAnalise;
                        filaDeVisita.add(origem);
                        centroDadosVisitados.add(origem);
                    }
                }
            }

            centroDadosEmAnalise.cor = Cor.PRETO;
        }

        return centroDadosVisitados;
    }

    public boolean eConexa(Rede rede) {
        return rede.getCentroDados().stream().allMatch(c -> c.cor.equals(Cor.PRETO));
    }
}
