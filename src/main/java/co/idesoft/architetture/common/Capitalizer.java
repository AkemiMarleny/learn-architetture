package co.idesoft.architetture.common;

import org.apache.commons.text.WordUtils;

public class Capitalizer {

    private final String capitalized;

    public Capitalizer(String raw) {
        this.capitalized = WordUtils.capitalize(raw.trim().toLowerCase());
    }

    public String get() {
        return capitalized;
    }
}
