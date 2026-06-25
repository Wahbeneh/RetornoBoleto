package com.boleto.model;

/**
 * Representa um boleto bancário lido a partir de um arquivo de retorno.
 */
public class Boleto {
    private long id;
    private int codBanco;
    private String dataVencimento;
    private String dataPagamento;
    private String cpfCliente;
    private double valor;
    private double multa;
    private double juros;

    // Campos exclusivos do Bradesco
    private int agencia;
    private long conta;
    private String horaPagamento; // parte da data/hora do Bradesco

    public Boleto() {}

    // Getters e Setters
    public long getId() { return id; }
    public void setId(long id) { this.id = id; }

    public int getCodBanco() { return codBanco; }
    public void setCodBanco(int codBanco) { this.codBanco = codBanco; }

    public String getDataVencimento() { return dataVencimento; }
    public void setDataVencimento(String dataVencimento) { this.dataVencimento = dataVencimento; }

    public String getDataPagamento() { return dataPagamento; }
    public void setDataPagamento(String dataPagamento) { this.dataPagamento = dataPagamento; }

    public String getCpfCliente() { return cpfCliente; }
    public void setCpfCliente(String cpfCliente) { this.cpfCliente = cpfCliente; }

    public double getValor() { return valor; }
    public void setValor(double valor) { this.valor = valor; }

    public double getMulta() { return multa; }
    public void setMulta(double multa) { this.multa = multa; }

    public double getJuros() { return juros; }
    public void setJuros(double juros) { this.juros = juros; }

    public int getAgencia() { return agencia; }
    public void setAgencia(int agencia) { this.agencia = agencia; }

    public long getConta() { return conta; }
    public void setConta(long conta) { this.conta = conta; }

    public String getHoraPagamento() { return horaPagamento; }
    public void setHoraPagamento(String horaPagamento) { this.horaPagamento = horaPagamento; }

    @Override
    public String toString() {
        return String.format(
            "Boleto { id=%d, banco=%d, vencimento=%s, pagamento=%s, cpf=%s, valor=R$%.2f, multa=R$%.2f, juros=R$%.2f }",
            id, codBanco, dataVencimento, dataPagamento, cpfCliente, valor, multa, juros
        );
    }
}
