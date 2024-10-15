package co.idesoft.architetture.hexagonal.adapters.http;

import co.idesoft.architetture.common.Page;
import co.idesoft.architetture.common.factories.PageableFactory;
import co.idesoft.architetture.hexagonal.adapters.http.dto.*;
import co.idesoft.architetture.hexagonal.domain.ports.api.*;
import co.idesoft.architetture.hexagonal.domain.valuables.AggiornareCliente;
import co.idesoft.architetture.hexagonal.domain.valuables.ClienteDettaglio;
import co.idesoft.architetture.hexagonal.domain.valuables.CreareCliente;
import co.idesoft.architetture.mvcservices.exceptions.ConflictException;
import co.idesoft.architetture.mvcservices.exceptions.RecordNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RequestMapping("api/clienti")
@RestController
@RequiredArgsConstructor
public class ClienteController {

    private final CreareClienteUseCase creareClienteUseCase;
    private final FindClienteByIdUseCase findClienteByIdUseCase;
    private final FindAllClientiUseCase findAllClientiUseCase;
    private final AggiornareClienteUseCase aggiornareClienteUseCase;
    private final CancellareClienteUseCase cancellareClienteUseCase;

    @PostMapping
    public ResponseEntity<ClienteCreatoDto> creareCliente(@RequestBody CreareClienteDto request) {

        try {
            Long clienteId = creareClienteUseCase.creareCliente(
                    new CreareCliente(
                            request.nome(),
                            request.cognomePaterno(),
                            request.cognomeMaterno(),
                            request.compleanno(),
                            request.descrizione()));
            return new ResponseEntity<>(new ClienteCreatoDto(clienteId), HttpStatus.CREATED);
        } catch (ConflictException e) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

    }

    @GetMapping("{clienteId}")
    public ResponseEntity<ClienteDettaglioDto> getDettaglioCliente(@PathVariable Long clienteId) {

        Optional<ClienteDettaglio> clienteDettaglioOpt = findClienteByIdUseCase.findClienteByid(clienteId);
        if (clienteDettaglioOpt.isPresent()) {
            return ResponseEntity.ok(ClienteDettaglioDto.from(clienteDettaglioOpt.get()));
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping
    public ResponseEntity<Page<ClienteItemDto>> getAllCliente(Pageable pageable, String q) {

        Page<ClienteItemDto> clientiPage = findAllClientiUseCase
                .findAllClienti(PageableFactory.from(pageable), q)
                .map(ClienteItemDto::from);

        return new ResponseEntity<>(clientiPage, HttpStatus.OK);
    }

    @PutMapping("{clienteId}")
    public ResponseEntity<Void> updateCliente(@PathVariable Long clienteId, @RequestBody AggiornareClienteDto request) {
        try {
            aggiornareClienteUseCase.aggiornaCliente(new AggiornareCliente(
                    clienteId,
                    request.nome(),
                    request.cognomePaterno(),
                    request.cognomeMaterno(),
                    request.descrizione()));

        } catch (ConflictException e) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);

        } catch (RecordNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("{clienteId}")
    public ResponseEntity<Void> cancellaCliente(@PathVariable Long clienteId) {
        try {
            cancellareClienteUseCase.cancellaCliente(clienteId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (RecordNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
