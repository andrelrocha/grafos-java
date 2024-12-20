package rocha.andre.grafos.service;

import org.springframework.stereotype.Service;


@Service
public class MainService {

    public void runMain() {
        var primeiraQuestao = new PrimeiroExercicioService();
        primeiraQuestao.primeiraQuestao();
    }
}