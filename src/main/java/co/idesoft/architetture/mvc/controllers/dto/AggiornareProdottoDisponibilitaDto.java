package co.idesoft.architetture.mvc.controllers.dto;

import jakarta.validation.constraints.NotNull;

public record AggiornareProdottoDisponibilitaDto(
        @NotNull Long quantita,
        @NotNull Long warehouseId
) {
}
