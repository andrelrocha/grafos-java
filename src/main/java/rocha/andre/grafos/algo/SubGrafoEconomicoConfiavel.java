package rocha.andre.grafos.algo;

import rocha.andre.grafos.models.CentroDados;
import rocha.andre.grafos.models.Conexao;
import rocha.andre.grafos.models.Cor;
import rocha.andre.grafos.models.Rede;
import rocha.andre.grafos.service.RedeService;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class SubGrafoEconomicoConfiavel {
    public List<Conexao> gerarSubgrafoConfiavel(int numCentros, List<Conexao> mst, List<Conexao> conexoesOriginais) {
        List<Conexao> subgrafoConfiavel = new ArrayList<>(mst);

        List<List<Integer>> listaVizinhosCentroDados = new ArrayList<>();
        for (int i = 0; i < numCentros; i++) {
            listaVizinhosCentroDados.add(new ArrayList<>());
        }


        for (Conexao conexao : mst) {
            listaVizinhosCentroDados.get(conexao.getOrigem().getId()).add(conexao.getDestino().getId());
            listaVizinhosCentroDados.get(conexao.getDestino().getId()).add(conexao.getOrigem().getId());
        }

        conexoesOriginais.sort(Comparator.comparingInt(Conexao::getCusto));


        for (Conexao conexao : conexoesOriginais) {
            int origem = conexao.getOrigem().getId();
            int destino = conexao.getDestino().getId();

            if (listaVizinhosCentroDados.get(origem).size() == 1 && listaVizinhosCentroDados.get(destino).size() == 1) {
                subgrafoConfiavel.add(conexao);
                listaVizinhosCentroDados.get(origem).add(destino);
                listaVizinhosCentroDados.get(destino).add(origem);
                break;
            }
        }

        var buscaEmLargura = new BuscaEmLargura();

        buscaEmLargura.buscaEmLargura(subgrafoConfiavel.get(0).getOrigem());

        var centrosDados = subgrafoConfiavel.stream()
                .flatMap(conexao -> Stream.of(conexao.getOrigem(), conexao.getDestino()))
                .collect(Collectors.toSet())
                .stream().toList();

        var rede = new Rede(centrosDados, subgrafoConfiavel);

        if (buscaEmLargura.eConexa(rede)) {
            System.out.println("A rede está conectada");
        }

        for (int i = 0; i < 10; i++) {
            if (subgrafoConfiavel.size() > i) {
                Conexao arestaRemovida = subgrafoConfiavel.get(i);
                subgrafoConfiavel.remove(i);

                listaVizinhosCentroDados.get(arestaRemovida.getOrigem().getId()).remove(Integer.valueOf(arestaRemovida.getDestino().getId()));
                listaVizinhosCentroDados.get(arestaRemovida.getDestino().getId()).remove(Integer.valueOf(arestaRemovida.getOrigem().getId()));

                for (CentroDados centro : centrosDados) {
                    centro.setCor(Cor.BRANCO);
                    centro.setDistancia(Integer.MAX_VALUE);
                    centro.setPai(null);
                }

                buscaEmLargura.buscaEmLargura(subgrafoConfiavel.get(0).getOrigem());

                if (buscaEmLargura.eConexa(rede)) {
                    System.out.println("A rede continua conectada após remoção da aresta " + arestaRemovida.getOrigem() + "-" + arestaRemovida.getDestino());
                } else {
                    throw new IllegalArgumentException("A rede não é mais conexa.");
                }

                subgrafoConfiavel.add(i, arestaRemovida);
                listaVizinhosCentroDados.get(arestaRemovida.getOrigem().getId()).add(arestaRemovida.getDestino().getId());
                listaVizinhosCentroDados.get(arestaRemovida.getDestino().getId()).add(arestaRemovida.getOrigem().getId());
            }
        }

        return subgrafoConfiavel;
    }

}