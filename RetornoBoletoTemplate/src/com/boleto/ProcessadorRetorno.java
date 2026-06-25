package com.boleto;

import com.boleto.model.Boleto;
import com.boleto.strategy.LeituraRetorno;

import java.util.ArrayList;
import java.util.List;


public class ProcessadorRetorno {

    private LeituraRetorno leituraRetorno;

    public ProcessadorRetorno(LeituraRetorno leituraRetorno) {
        this.leituraRetorno = leituraRetorno;
    }

    public void setLeituraRetorno(LeituraRetorno leituraRetorno) {
        this.leituraRetorno = leituraRetorno;
    }

    public List<Boleto> processar(String nomeArquivo) {
        List<Boleto> boletos = leituraRetorno.lerArquivo(nomeArquivo);

        System.out.println("  → " + boletos.size() + " boleto(s) lido(s):");
        for (Boleto b : boletos) {
            System.out.println("    " + b);
        }

        return boletos;
    }

    public List<Boleto> processarVarios(String... arquivos) {
        List<Boleto> todos = new ArrayList<>();
        for (String arquivo : arquivos) {
            todos.addAll(processar(arquivo));
        }
        return todos;
    }
}
