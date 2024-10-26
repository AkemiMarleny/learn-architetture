package co.idesoft.architetture.mvc.controllers.dto;

import jakarta.validation.constraints.NotNull;

public record CreareProdottoDisponibilitaDto(
        @NotNull Long quantita,
        @NotNull Long warehouseId
) {
}
