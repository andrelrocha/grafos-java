package rocha.andre.grafos.service;

import org.springframework.stereotype.Service;


@Service
public class MainService {

    public void runMain() {
        //var primeiraQuestao = new PrimeiroExercicioService();
        //var rede = primeiraQuestao.primeiraQuestao();

        var segundaQuestao = new SegundoExercicioService();
        segundaQuestao.segundaQuestao();
    }
}