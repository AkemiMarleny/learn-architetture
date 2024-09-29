package co.idesoft.architetture.mvcservices.controllers.dto;

import co.idesoft.architetture.mvcservices.entities.Categoria;

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
