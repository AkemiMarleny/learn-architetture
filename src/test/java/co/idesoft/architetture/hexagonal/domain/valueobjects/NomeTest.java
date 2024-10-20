package co.idesoft.architetture.hexagonal.domain.valueobjects;

import co.idesoft.architetture.common.Normalize;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class NomeTest {
    @Test
    public void dovrebbeMantenereINomiComplessi() {
        Assertions.assertEquals(new Normalize("M4aria Magdal3ena!!").get(), "Maria Magdalena");
    }
}