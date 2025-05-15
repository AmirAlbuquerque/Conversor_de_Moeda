import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {
    public static void main(String[] args) {

        Requisition requisition = new Requisition();
        String moedaBase = "0";

        String menu = """
                **********************************************
                Seja bem vindo(a) ao Conversor de Moedas :)
                
                Escolha a moeda que deseja converter:
                
                1) Dólar (USD)
                2) Real Brasileiro (BRL)
                3) Euro (EUR)
                4) Chinise Renminbi (CNY)
                5) Boliviano (BOB)
                6) Peso Argentino (ARS)
                7) Sair
                **********************************************
                """;

        String conversaoTemplate = """
                **********************************************
                Escolha a conversão:
                """;

        String informacaoTemplate = """
                %s) %s =>> %s""";

        Scanner leitura = new Scanner(System.in);

        while (!moedaBase.equals("7")) {
            System.out.println(menu);

            moedaBase = leitura.nextLine();

            double taxa =0;
            String moedaBaseTexto;
            String moeda;
            switch (moedaBase) {
                case "1":
                    moedaBaseTexto = "Dólar";
                    moeda = "USD";
                    break;
                case "2":
                    moedaBaseTexto = "Real Brasileiro";
                    moeda = "BRL";
                    break;
                case "3":
                    moedaBaseTexto = "Euro";
                    moeda = "EUR";
                    break;
                case "4":
                    moedaBaseTexto = "Renminbi Chinês";
                    moeda = "CNY";
                    break;
                case "5":
                    moedaBaseTexto = "Boliviano";
                    moeda = "BOB";
                    break;
                case "6":
                    moedaBaseTexto = "Peso Argentino";
                    moeda = "ARS";
                    break;
                case "7":
                    moedaBaseTexto = "";
                    moeda = "";
                    moedaBase="7";
                    break;
                default:
                    moedaBaseTexto = "";
                    moeda = "";
                    break;
            }

            if (!moedaBaseTexto.isEmpty()){

                Map<String,Double> taxas = requisition.obterTaxaConversao(moeda);
                taxas.remove(moeda);

                AtomicInteger contador = new AtomicInteger(1);
                Map<Integer,String> opcoes = new HashMap<>();

                System.out.println(conversaoTemplate);
                taxas.forEach((moedaFiltrada, taxaFiltrada) -> {
                    if (!moeda.equals(moedaFiltrada)){
                        int index = contador.getAndIncrement();
                        opcoes.put(index,moedaFiltrada);
                        String informacao = String.format(informacaoTemplate,index,moedaBaseTexto,moedaFiltrada );
                        System.out.println(informacao);
                    }
                });
                System.out.println("6) Sair");
                System.out.println("**********************************************");

                String escolha = leitura.nextLine();
                switch (escolha) {
                    case "1", "2", "3", "4", "5" -> {
                        String moedaDestino = opcoes.get(Integer.parseInt(escolha));
                        taxa = taxas.get(moedaDestino);
                        System.out.println("Informe o valor que queira converter: ");
                        double valor = leitura.nextDouble();
                        System.out.printf("O valor convertido é: %.2f%n \n", taxa*valor);
                    }
                    case "6" -> {
                    }
                };
            };
        };

    }
}