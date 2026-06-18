package strategy;

import model.Boleto;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LeituraRetornoBancoBrasil {


    public List<Boleto> lerArquivo(String nomeArquivo) {
        List<Boleto> boletos = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(nomeArquivo))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                linha = linha.trim();
                if (linha.isEmpty()) continue;
                boletos.add(parseLinha(linha));
            }
        } catch (IOException e) {
            System.err.println("Erro ao ler arquivo do Banco do Brasil: " + e.getMessage());
        }

        return boletos;
    }


    private Boleto parseLinha(String linha) {
        String[] c = linha.split(";");
        Boleto b = new Boleto();
        b.setId(Long.parseLong(c[0].trim()));
        b.setCodBanco(Integer.parseInt(c[1].trim()));
        b.setDataVencimento(c[2].trim());
        b.setDataPagamento(c[3].trim());
        b.setCpfCliente(c[4].trim());
        b.setValor(Double.parseDouble(c[5].trim()));
        b.setMulta(Double.parseDouble(c[6].trim()));
        b.setJuros(Double.parseDouble(c[7].trim()));
        return b;
    }
}
