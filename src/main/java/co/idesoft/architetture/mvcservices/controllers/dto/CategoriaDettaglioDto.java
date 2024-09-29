package co.idesoft.architetture.mvcservices.controllers.dto;

import co.idesoft.architetture.mvcservices.entities.Categoria;

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
