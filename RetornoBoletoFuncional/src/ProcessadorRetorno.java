import model.Boleto;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class ProcessadorRetorno {

    private Function<String, List<Boleto>> leituraRetorno;

    public ProcessadorRetorno(Function<String, List<Boleto>> leituraRetorno) {
        this.leituraRetorno = leituraRetorno;
    }

    public void setLeituraRetorno(Function<String, List<Boleto>> leituraRetorno) {
        this.leituraRetorno = leituraRetorno;
    }

    public List<Boleto> processar(String nomeArquivo) {

        List<Boleto> boletos = leituraRetorno.apply(nomeArquivo);

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
