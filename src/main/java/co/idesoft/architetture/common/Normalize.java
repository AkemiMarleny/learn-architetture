package co.idesoft.architetture.common;

import java.text.Normalizer;

public class Normalize {

    private final String value;

    public Normalize(String raw) {
        this.value = Normalizer.normalize(raw, Normalizer.Form.NFD)
                .replaceAll("\\p{M}", "") // rimuove caratteri fuori dal standar NFD
                .replaceAll("[^ A-Za-z]", "") // mantiene solo lettere e spazi
                .replaceAll("\\s{2,}", " "); // rimuove il dopio o più spazi
    }

    public String get() {
        return value;
    }
}
