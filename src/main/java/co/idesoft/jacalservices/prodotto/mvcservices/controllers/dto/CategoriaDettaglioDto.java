package co.idesoft.jacalservices.prodotto.mvcservices.controllers.dto;

import co.idesoft.jacalservices.prodotto.mvcservices.entities.Categoria;

public record CategoriaDettaglioDto(
        Long categoriaId,
        String nome,
        String descrizione) {

    public static CategoriaDettaglioDto fromEntity(Categoria categoria) {
        return new CategoriaDettaglioDto(
                categoria.getCategoriaId(),
                categoria.getNome(),
                categoria.getDescrizione());
    }
}
