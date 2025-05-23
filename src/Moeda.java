import java.util.Map;

public class Moeda {

    private String type;
    private String index;

    public Moeda(String index, Map<String,String> type) {
        this.index = index;
        this.type = type.get(this.index);
    }

    public Map<String, Double> getRateMap() {
        Requisition requisition = new Requisition();
        Map<String, Double> rateMap = requisition.obterTaxaConversao(type);
        rateMap.remove(type);

        return rateMap;
    }
}