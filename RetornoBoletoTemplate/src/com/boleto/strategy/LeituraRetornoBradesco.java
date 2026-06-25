package com.boleto.strategy;

import com.boleto.model.Boleto;


public class LeituraRetornoBradesco extends AbstractLeituraRetorno {


    @Override
    protected Boleto parseLinha(String linha) {
        String[] c = linha.split(";");

        Boleto b = new Boleto();
        b.setId(Long.parseLong(c[0].trim()));
        b.setCodBanco(Integer.parseInt(c[1].trim()));
        b.setAgencia(Integer.parseInt(c[2].trim()));
        b.setConta(Long.parseLong(c[3].trim()));
        b.setDataVencimento(c[4].trim());

        // Bradesco envia data e hora juntos: "dd/MM/yyyy HH:mm:ss"
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
