package strategy;

import model.Boleto;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LeituraRetornoBradesco {

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
            System.err.println("Erro ao ler arquivo do Bradesco: " + e.getMessage());
        }

        return boletos;
    }

    private Boleto parseLinha(String linha) {
        String[] c = linha.split(";");
        Boleto b = new Boleto();
        b.setId(Long.parseLong(c[0].trim()));
        b.setCodBanco(Integer.parseInt(c[1].trim()));
        b.setAgencia(Integer.parseInt(c[2].trim()));
        b.setConta(Long.parseLong(c[3].trim()));
        b.setDataVencimento(c[4].trim());
        String[] dataHora = c[5].trim().split(" ");
        b.setDataPagamento(dataHora[0]);
        if (dataHora.length > 1) b.setHoraPagamento(dataHora[1]);
        b.setCpfCliente(c[6].trim());
        b.setValor(Double.parseDouble(c[7].trim()));
        b.setMulta(Double.parseDouble(c[8].trim()));
        b.setJuros(Double.parseDouble(c[9].trim()));
        return b;
    }

}
