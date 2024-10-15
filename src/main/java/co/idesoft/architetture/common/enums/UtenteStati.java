package co.idesoft.architetture.common.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum UtenteStati {

    UNAUTHORIZED("unauthorized", "Non autorizzato"),
    AUTHORIZED("authorized", "Autorizzato");

    private final String codice;
    private final String nome;

}
