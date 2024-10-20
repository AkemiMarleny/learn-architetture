package co.idesoft.architetture.common;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class CapitalizerTest {

    @Test
    public void dovrebbeCapitalizzareLaPrimaLetteraDellaParola() {
        Assertions.assertEquals(new Capitalizer(" lA gAta").get(), "La Gata");
        Assertions.assertEquals(new Capitalizer("il gatto giallo").get(), "Il Gatto Giallo");
    }
}