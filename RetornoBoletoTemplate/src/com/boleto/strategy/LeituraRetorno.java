package com.boleto.strategy;

import com.boleto.model.Boleto;
import java.util.List;


public interface LeituraRetorno {
    List<Boleto> lerArquivo(String nomeArquivo);
}
