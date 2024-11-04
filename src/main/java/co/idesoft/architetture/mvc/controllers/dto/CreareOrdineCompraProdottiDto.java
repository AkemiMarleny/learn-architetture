package co.idesoft.architetture.mvc.controllers.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record CreareOrdineCompraProdottiDto(
        @NotNull Long prodottoId,
        @Min(0) Long quantita,
        @NotNull Long customerId
) {
}
