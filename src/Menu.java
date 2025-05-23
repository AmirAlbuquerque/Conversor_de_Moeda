import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Menu {
    private String getNomeCompleto(String sigla) {
        return switch (sigla) {
            case "USD" -> "Dólar";
            case "BRL" -> "Real Brasileiro";
            case "EUR" -> "Euro";
            case "CNY" -> "Renminbi Chinês";
            case "BOB" -> "Boliviano";
            case "ARS" -> "Peso Argentino";
            default -> "Moeda Desconhecida";
        };
    }


    public Map<String, String> getIndexToCoin(){

        Set<String> moedasDesejadas = ValoresReferencia.getMoedasDesejadas();
        Map<String,String> index = new LinkedHashMap<>();
        AtomicInteger ind = new AtomicInteger(1);

        for (String moeda : moedasDesejadas) {
            index.put(String.valueOf(ind.getAndIncrement()), moeda);
        }

        return index;
    }

    public void getMenu(){

        Map<String, String> index = getIndexToCoin();
        StringBuilder menu = new StringBuilder();
        menu.append("**********************************************\n");
        menu.append("Seja bem vindo(a) ao Conversor de Moedas :)\n\n");
        menu.append("Escolha a moeda que deseja converter:\n\n");

        for (Map.Entry<String,String> entry : index.entrySet()) {
            String numero = entry.getKey();
            String sigla = entry.getValue();

            menu.append(numero).append(") ").append(getNomeCompleto(sigla)).append(" (").append(sigla).append(")\n");
        }

        menu.append(index.size()+1).append(") Sair\n");
        menu.append("**********************************************");

        System.out.println(menu.toString());
    }

    public String getSubMenu(String moedaBaseIndex){
        Map<String, String> index = getIndexToCoin();
        String moedaBase = index.get(moedaBaseIndex);

        if (moedaBase == null){
            System.out.println("Opção Inválida!");
            return null;
        }

        Scanner leitura = new Scanner(System.in);
        List<String> chaves = new ArrayList<>();
        int contador = 1;

        System.out.println("**********************************************");
        System.out.println("Conversões possíveis a partir de " + getNomeCompleto(moedaBase) + " (" + moedaBase + "):\n");

        for (Map.Entry<String, String> entry : index.entrySet()) {
            String sigla = entry.getValue();

            if (!sigla.equals(moedaBase)) {
                System.out.println(contador + ") " + getNomeCompleto(moedaBase) + " (" + moedaBase + ") -> " + getNomeCompleto(sigla) + " (" + sigla + ")");
                chaves.add(sigla); // salva sigla de destino
                contador++;
            }
        }

        System.out.println(contador + ") Voltar");
        System.out.println("**********************************************");

        String escolha = leitura.nextLine();

        try {
            int opcao = Integer.parseInt(escolha);

            if (opcao == contador) {
                return "voltar";
            } else if (opcao > 0 && opcao < contador) {
                return chaves.get(opcao - 1); // retorna a moedaFinal
            } else {
                System.out.println("Escolha inválida.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Entrada inválida.");
        }

        return null;
    }

}