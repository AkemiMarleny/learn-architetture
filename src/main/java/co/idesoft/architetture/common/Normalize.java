package co.idesoft.architetture.common;

import java.text.Normalizer;

public class Normalize {

    private final String value;

    public Normalize(String raw) {
        this.value = Normalizer.normalize(
                raw, Normalizer.Form.NFD).replaceAll("\\p{M}", "").replaceAll("[^A-Za-z]", "");
    }

    public String get() {
        return value;
    }
}
