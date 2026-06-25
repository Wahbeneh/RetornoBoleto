package com.boleto.strategy;

import com.boleto.model.Boleto;


public class LeituraRetornoBancoBrasil extends AbstractLeituraRetorno {


    @Override
    protected Boleto parseLinha(String linha) {
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
