package co.idesoft.architetture.hexagonal.domain.valueobjects;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class CognomeTest {
    @Test
    public void dovrebbeMantenereICognomiComplessi() {
        Assertions.assertEquals(new Cognome("33De la ve3ga").get(), "De La Vega");
        Assertions.assertEquals(new Cognome("...##33De L@@A   T00ORr.E   ").get(), "De La Torre");
        Assertions.assertEquals(new Cognome("  @@33De  la   ve3ga").get(), "De La Vega");
    }
}