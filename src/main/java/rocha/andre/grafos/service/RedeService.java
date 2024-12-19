package rocha.andre.grafos.service;

import org.springframework.stereotype.Service;
import rocha.andre.grafos.models.Conexao;
import rocha.andre.grafos.models.CentroDados;

import java.util.List;

@Service
public class RedeService {
    public void adicionarCentroDados(CentroDados centroDados, List<CentroDados> centrosDados) {
        if (!centrosDados.contains(centroDados)) {
            centrosDados.add(centroDados);
        }
    }

    public void adicionarConexoes(Conexao conexao, List<Conexao> conexoes) {
        if (!conexoes.contains(conexao)) {
            conexoes.add(conexao);
            conexao.getOrigem().adicionarConexao(conexao);
            conexao.getDestino().adicionarConexao(conexao);
        }
    }

    public void exibirRede(List<CentroDados> centrosDados, List<Conexao> conexoes) {
        System.out.println("Centro de Dados:");
        for (CentroDados centroDados : centrosDados) {
            System.out.println("ID: " + centroDados.getId());
        }

        System.out.println("Conexões:");
        for (Conexao conexao : conexoes) {
            System.out.println("Origem: " + conexao.getOrigem().getId() +
                    ", Destino: " + conexao.getDestino().getId() +
                    ", Custo: " + conexao.getCusto());
        }
    }
}
