package co.idesoft.architetture.hexagonal.domain.valueobjects;

import co.idesoft.architetture.common.Capitalizer;
import co.idesoft.architetture.common.Normalize;

public class Nome {

    private final String value;

    public Nome(String raw) {
        this.value = new Capitalizer(new Normalize(raw).get()).get();
    }

    public String get() {
        return value;
    }

}
