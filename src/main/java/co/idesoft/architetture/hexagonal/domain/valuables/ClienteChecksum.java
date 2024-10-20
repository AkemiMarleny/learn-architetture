package co.idesoft.architetture.hexagonal.domain.valuables;

import co.idesoft.architetture.common.Sum;
import co.idesoft.architetture.hexagonal.domain.valueobjects.Cognome;
import co.idesoft.architetture.hexagonal.domain.valueobjects.Nome;

public class ClienteChecksum {
    private String sum;

    public ClienteChecksum(Nome nome, Cognome cognomePaterno, Cognome cognoneMaterno) {
        
        String fingerprint = String.format("%s.%s.%s", nome.get(), cognomePaterno.get(), cognoneMaterno.get()).trim().toLowerCase();

        this.sum = Sum.fromContent(fingerprint);
    }

    public String get() {
        return this.sum;
    }
}
