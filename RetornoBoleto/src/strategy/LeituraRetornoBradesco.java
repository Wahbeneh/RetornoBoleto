package strategy;

import model.Boleto;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LeituraRetornoBradesco implements LeituraRetorno {

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
            System.err.println("Erro ao ler arquivo do Bradesco: " + e.getMessage());
        }

        return boletos;
    }

    private Boleto parseLinha(String linha) {
        // Campos: id ; codBanco ; agencia ; conta ; dataVenc ; dataHoraPag ; cpf ; valor ; multa ; juros
        String[] campos = linha.split(";");

        Boleto boleto = new Boleto();
        boleto.setId(Long.parseLong(campos[0].trim()));
        boleto.setCodBanco(Integer.parseInt(campos[1].trim()));
        boleto.setAgencia(Integer.parseInt(campos[2].trim()));
        boleto.setConta(Long.parseLong(campos[3].trim()));
        boleto.setDataVencimento(campos[4].trim());

        // Campo 6 do Bradesco é "dd/MM/yyyy HH:mm:ss" — separamos data e hora
        String dataHora = campos[5].trim();
        String[] partes = dataHora.split(" ");
        boleto.setDataPagamento(partes[0]);
        if (partes.length > 1) {
            boleto.setHoraPagamento(partes[1]);
        }

        boleto.setCpfCliente(campos[6].trim());
        boleto.setValor(Double.parseDouble(campos[7].trim()));
        boleto.setMulta(Double.parseDouble(campos[8].trim()));
        boleto.setJuros(Double.parseDouble(campos[9].trim()));

        return boleto;
    }

}
