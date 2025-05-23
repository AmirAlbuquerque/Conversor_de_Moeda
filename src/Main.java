import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner leitura = new Scanner(System.in);
        String moedaBase = "0";
        Menu menu = new Menu();

        while (!moedaBase.equals("7")) {
            menu.getMenu();
            Map<String, String> index = menu.getIndexToCoin();
            moedaBase = leitura.nextLine();

            if (moedaBase.equals("7")) break;

            while (true) {
                String moedaFinal = menu.getSubMenu(moedaBase);

                if (moedaFinal == null) continue;
                if (moedaFinal.equals("voltar")) break;

                Moeda coin = new Moeda(moedaBase, index);
                Map<String, Double> rateMap = coin.getRateMap();
                Double taxa = rateMap.get(moedaFinal);

                if (taxa == null) {
                System.out.println("Erro ao obter a taxa de conversão");
                continue;
                }
                System.out.println("Digite o valor em " + index.get(moedaBase) + ": ");
                String valorConverter = leitura.nextLine();

                try {
                    double valor = Double.parseDouble(valorConverter);
                    double valorFinal = valor * taxa;

                    System.out.printf("Resultado: %.2f %s equivalem a %.2f %s\n\n",
                            valor, index.get(moedaBase), valorFinal, moedaFinal);
                } catch (NumberFormatException e) {
                    System.out.println("Valor inválido, tente novamente.\n");
                }
            }
        }
        System.out.println("Programa encerrado!");
    }
}
