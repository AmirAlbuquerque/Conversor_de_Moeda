import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class ValoresReferencia {

    public static Set<String> getMoedasDesejadas() {
        return new LinkedHashSet<>(List.of("BRL", "EUR", "USD", "CNY", "ARS", "BOB"));
    }
}
