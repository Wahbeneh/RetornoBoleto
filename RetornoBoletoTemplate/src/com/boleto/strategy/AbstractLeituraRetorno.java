package com.boleto.strategy;

import com.boleto.model.Boleto;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public abstract class AbstractLeituraRetorno implements LeituraRetorno {

    /**
     * MÉTODO TEMPLATE — esqueleto fixo, herdado por todas as subclasses.
     *
     * O "final" impede que qualquer subclasse sobrescreva e quebre o fluxo.
     */
    @Override
    public final List<Boleto> lerArquivo(String nomeArquivo) {
        List<Boleto> boletos = new ArrayList<>();

        // Passo 1: abrir o arquivo (fixo — igual para todos os bancos)
        try (BufferedReader reader = abrirArquivo(nomeArquivo)) {

            String linha;

            // Passo 2: ler linha por linha (fixo)
            while ((linha = reader.readLine()) != null) {
                linha = linha.trim();
                if (linha.isEmpty()) continue;

                // Passo 3: parsear a linha — GANCHO, cada banco decide como
                Boleto boleto = parseLinha(linha);
                boletos.add(boleto);
            }

        } catch (IOException e) {
            // Passo 4: tratar erro (fixo)
            System.err.println("Erro ao ler arquivo [" + nomeArquivo + "]: " + e.getMessage());
        }

        // Passo 5: retornar a lista (fixo)
        return boletos;
    }

    /**
     * Passo fixo: abre o arquivo e retorna um BufferedReader.
     * Extraído como método separado para facilitar testes (pode ser sobrescrito
     * em testes para injetar um Reader de memória, por exemplo).
     */
    protected BufferedReader abrirArquivo(String nomeArquivo) throws IOException {
        return new BufferedReader(new FileReader(nomeArquivo));
    }

    /**
     * GANCHO ABSTRATO — o único passo que varia entre os bancos.
     *
     * Recebe uma linha do arquivo e deve retornar um Boleto populado.
     * Cada subclasse conhece o formato do seu banco e implementa aqui.
     *
     * "protected" porque é um detalhe interno da hierarquia —
     * não faz parte da API pública.
     */
    protected abstract Boleto parseLinha(String linha);
}
