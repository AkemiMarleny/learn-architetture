package co.idesoft.architetture.hexagonal.domain.valuables;

import co.idesoft.architetture.common.Sum;

public class ClienteChecksum {
    private String sum;

    public ClienteChecksum(String nome) {
        this.sum = Sum.fromContent(nome.trim().toLowerCase());
    }

    public String get() {
        return this.sum;
    }
}
