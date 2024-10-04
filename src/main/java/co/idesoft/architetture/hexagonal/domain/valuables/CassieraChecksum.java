package co.idesoft.architetture.hexagonal.domain.valuables;

import co.idesoft.architetture.common.Sum;

public class CassieraChecksum {

    private String sum;

    public CassieraChecksum(String nome) {
        this.sum = Sum.fromContent(nome.trim().toLowerCase());
    }

    public String get() {
        return this.sum;
    }
}
