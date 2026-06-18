package strategy;

import model.Boleto;

import java.util.List;

public interface LeituraRetorno {

    List<Boleto> lerArquivo(String nomeArquivo);

}
