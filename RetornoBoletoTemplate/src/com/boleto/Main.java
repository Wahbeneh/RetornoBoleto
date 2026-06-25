package com.boleto;

import com.boleto.model.Boleto;
import com.boleto.strategy.LeituraRetornoBancoBrasil;
import com.boleto.strategy.LeituraRetornoBradesco;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        System.out.println("============================================");
        System.out.println("  Retorno de Boletos");
        System.out.println("  Strategy + Template Method");
        System.out.println("============================================\n");

        System.out.println(">>> Processando arquivo do BANCO DO BRASIL...");
        ProcessadorRetorno processador = new ProcessadorRetorno(new LeituraRetornoBancoBrasil());
        List<Boleto> boletosBB = processador.processar("banco-brasil-1.csv");

        System.out.println();

        System.out.println(">>> Processando arquivo do BRADESCO...");
        processador.setLeituraRetorno(new LeituraRetornoBradesco());
        List<Boleto> boletosBradesco = processador.processar("bradesco-1.csv");

        System.out.println();

        // Resumo consolidado
        System.out.println("============================================");
        System.out.println("  RESUMO CONSOLIDADO");
        System.out.println("============================================");

        List<Boleto> todos = new ArrayList<>();
        todos.addAll(boletosBB);
        todos.addAll(boletosBradesco);

        double totalValor  = todos.stream().mapToDouble(Boleto::getValor).sum();
        double totalMultas = todos.stream().mapToDouble(Boleto::getMulta).sum();
        double totalJuros  = todos.stream().mapToDouble(Boleto::getJuros).sum();

        System.out.printf("  Boletos processados : %d%n", todos.size());
        System.out.printf("  Total valores       : R$ %.2f%n", totalValor);
        System.out.printf("  Total multas        : R$ %.2f%n", totalMultas);
        System.out.printf("  Total juros         : R$ %.2f%n", totalJuros);
        System.out.printf("  TOTAL GERAL         : R$ %.2f%n", totalValor + totalMultas + totalJuros);
        System.out.println("============================================");
    }
}
