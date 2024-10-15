package co.idesoft.architetture.hexagonal.domain.valueobjects;

import co.idesoft.architetture.common.Normalize;

import java.time.LocalDate;

public class Username {

    private final String value;

    private static final String[] PREPOSIZIONI = {"de", "la", "del", "los", "y", "las"};

    public Username(String nome, String cognomePaterno, String cognomeMaterno, LocalDate compleanno) {

        String[] nomeParti = new Normalize(nome).get().toLowerCase().split("\\s+");

        String nomeAsUsername = String.valueOf(nomeParti[0].charAt(0));

        String cognomePaternoAsUsername = getCognomePaterno(cognomePaterno);

        String cognomeMaternoAsUsername = getCognomeMaterno(cognomeMaterno);


        this.value = String.format("%s%s%s%02d", nomeAsUsername,
                cognomePaternoAsUsername, cognomeMaternoAsUsername, compleanno.getDayOfMonth());
    }

    private String getCognomePaterno(String cognomePaterno) {

        String[] parti = new Normalize(cognomePaterno).get().toLowerCase().split("\\s+");

        StringBuilder result = new StringBuilder();

        for (String parte : parti) {
            boolean epreposizione = false;
            for (String preposizione : PREPOSIZIONI) {
                if (parte.equals(preposizione)) {
                    epreposizione = true;
                    break;
                }
            }

            if (!epreposizione) {
                result.append(parte);
            }
        }

        return result.toString();

    }

    private String getCognomeMaterno(String cognomeMaterno) {

        String[] parti = new Normalize(cognomeMaterno).get().toLowerCase().split("\\s+");

        StringBuilder result = new StringBuilder();

        for (String parte : parti) {
            boolean epreposizione = false;
            for (String preposizione : PREPOSIZIONI) {
                if (parte.equals(preposizione)) {
                    epreposizione = true;
                    break;
                }
            }

            if (!epreposizione) {
                result.append(parte);
            }
        }

        return result.length() >= 3 ? result.substring(0, 3) : result.toString();
    }

    public String get() {
        return value;
    }
}
