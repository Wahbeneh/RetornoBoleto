package strategy;

import model.Boleto;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LeituraRetornoBancoBrasil implements LeituraRetorno{


    @Override
    public List<Boleto> lerArquivo(String nomeArquivo) {
        List<Boleto> boletos = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(nomeArquivo))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                linha = linha.trim();
                if (linha.isEmpty()) continue;

                Boleto boleto = parseLinha(linha);
                boletos.add(boleto);
            }
        } catch (IOException e) {
            System.err.println("Erro ao ler arquivo do Banco do Brasil: " + e.getMessage());
        }

        return boletos;
    }

    private Boleto parseLinha(String linha) {
        // Campos: id ; codBanco ; dataVenc ; dataPag ; cpf ; valor ; multa ; juros
        String[] campos = linha.split(";");

        Boleto boleto = new Boleto();
        boleto.setId(Long.parseLong(campos[0].trim()));
        boleto.setCodBanco(Integer.parseInt(campos[1].trim()));
        boleto.setDataVencimento(campos[2].trim());
        boleto.setDataPagamento(campos[3].trim());
        boleto.setCpfCliente(campos[4].trim());
        boleto.setValor(Double.parseDouble(campos[5].trim()));
        boleto.setMulta(Double.parseDouble(campos[6].trim()));
        boleto.setJuros(Double.parseDouble(campos[7].trim()));

        return boleto;
    }
}
