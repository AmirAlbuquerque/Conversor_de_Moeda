import com.google.gson.Gson;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Requisition {

    Set<String> moedasDesejadas = Set.of("BRL", "EUR", "USD", "CNY", "ARS", "BOB");

    public Map<String, Double> obterTaxaConversao(String moedaBase) {
        String API = "2f4f02b4758925ecc0261bab";
        URI base = URI.create("https://v6.exchangerate-api.com/v6/" + API + "/latest/" + moedaBase);

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(base)
                .build();

        try {
            HttpResponse<String> response = client
                    .send(request, HttpResponse.BodyHandlers.ofString());
            TaxaConversao taxaConversao = new Gson().fromJson(response.body(), TaxaConversao.class);

            Map<String, Double> taxasFiltradas = new HashMap<>();
            for (String moeda : moedasDesejadas){
                if (taxaConversao.conversion_rates().containsKey(moeda)){
                    taxasFiltradas.put(moeda, taxaConversao.conversion_rates().get(moeda));
                }
            }
            return taxasFiltradas;
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException("Não foi possível obter as informações de conversão");
        }
    }
}
