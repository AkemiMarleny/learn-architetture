package co.idesoft.jacalservices.prodotto.hexagonal.adapters.http;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.idesoft.jacalservices.prodotto.hexagonal.adapters.http.dto.ClienteCreatoDto;
import co.idesoft.jacalservices.prodotto.hexagonal.adapters.http.dto.CreareClienteDto;
import co.idesoft.jacalservices.prodotto.hexagonal.domain.ports.api.CreareClienteUseCase;
import co.idesoft.jacalservices.prodotto.hexagonal.domain.valuables.CreareCliente;
import lombok.RequiredArgsConstructor;

@RequestMapping("api/clienti")
@RestController
@RequiredArgsConstructor
public class ClienteController {

    private final CreareClienteUseCase creareClienteUseCase;

    @PostMapping
    public ResponseEntity<ClienteCreatoDto> creareCliente(@RequestBody CreareClienteDto request) {
        Long clienteId = creareClienteUseCase.creareCliente(
                new CreareCliente(request.nome(), request.descrizione()));

        return ResponseEntity.ok(new ClienteCreatoDto(clienteId));
    }

}
