package rocha.andre.grafos.service;

import rocha.andre.grafos.models.CentroDados;
import rocha.andre.grafos.models.CentroDadosDTO;
import rocha.andre.grafos.models.Conexao;
import rocha.andre.grafos.models.Rede;

import java.util.ArrayList;
import java.util.List;

public class PrimeiroExercicioService {

    public Rede executar(String query) {
        var rede = primeiroGrafo();

        if (query.equals("segundo")) {
            return segundoGrafo(rede);
        } else if (query.equals("terceiro")) {
            return terceiroGrafo(rede);
        }

        return rede;
    }

    public Rede primeiroGrafo() {
        Rede rede = new Rede();
        RedeService redeService = new RedeService();
        var numCD = 10;
        var centroDados = criarCentroDados(numCD);
        rede.setCentroDados(centroDados);

        for (int i = 0; i < numCD - 1; i++) {
            var conexao = new Conexao(centroDados.get(i), centroDados.get(i+1), i, rede.getConexoes());
            redeService.adicionarConexoes(conexao, rede);
            if (i+2 < numCD) {
                conexao = new Conexao(centroDados.get(i), centroDados.get(i+2), i, rede.getConexoes());
                redeService.adicionarConexoes(conexao, rede);
            }
        }

        return rede;
    }

    public Rede segundoGrafo(Rede rede) {
        RedeService redeService = new RedeService();
        var numCD = 10;
        var centroDados = rede.getCentroDados();

        for (int i = 0; i < numCD - 1; i++) {
            if (i + 3 < numCD) {
                var conexao = new Conexao(centroDados.get(i), centroDados.get(i + 3), i + 2, rede.getConexoes());
                redeService.adicionarConexoes(conexao, rede);
            }

            if (i == numCD - 4) {
                for (int j = i + 1; j < numCD; j++) {
                    var destino = (j + 3) % numCD;
                    var conexaoVolta = new Conexao(centroDados.get(j), centroDados.get(destino), j + 2, rede.getConexoes());
                    redeService.adicionarConexoes(conexaoVolta, rede);
                }
            }
        }

        return rede;
    }


    public Rede terceiroGrafo(Rede rede) {
        RedeService redeService = new RedeService();
        var numCD = 10;
        var centroDados = rede.getCentroDados();

        for (int i = 0; i < numCD - 1; i++) {
            if (i + 4 < numCD) {
                var conexao = new Conexao(centroDados.get(i), centroDados.get(i + 4), i + 3, rede.getConexoes());
                redeService.adicionarConexoes(conexao, rede);
            }

            if (i == numCD - 5) {
                for (int j = i + 1; j < numCD; j++) {
                    var destino = (j + 4) % numCD;
                    var conexaoVolta = new Conexao(centroDados.get(j), centroDados.get(destino), j + 3, rede.getConexoes());
                    redeService.adicionarConexoes(conexaoVolta, rede);
                }
            }
        }

        return rede;
    }


    private List<CentroDados> criarCentroDados(int n) {
        List<CentroDados> centroDados = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            centroDados.add(new CentroDados(i));
        }
        return centroDados;
    }
}
