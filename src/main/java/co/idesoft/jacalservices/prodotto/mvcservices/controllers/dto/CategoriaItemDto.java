package co.idesoft.jacalservices.prodotto.mvcservices.controllers.dto;

import co.idesoft.jacalservices.prodotto.mvcservices.entities.Categoria;

public record CategoriaItemDto(
        Long categoriaId,
        String nome,
        String descrizione) {

    public static CategoriaItemDto fromEntity(Categoria categoria) {
        return new CategoriaItemDto(
                categoria.getCategoriaId(),
                categoria.getNome(),
                categoria.getDescrizione());
    }

}
