package co.idesoft.architetture.hexagonal.adapters.http;

import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.idesoft.architetture.common.Page;
import co.idesoft.architetture.common.factories.PageableFactory;
import co.idesoft.architetture.hexagonal.adapters.http.dto.ClienteCreatoDto;
import co.idesoft.architetture.hexagonal.adapters.http.dto.ClienteDettaglioDto;
import co.idesoft.architetture.hexagonal.adapters.http.dto.ClienteItemDto;
import co.idesoft.architetture.hexagonal.adapters.http.dto.CreareClienteDto;
import co.idesoft.architetture.hexagonal.domain.ports.api.CreareClienteUseCase;
import co.idesoft.architetture.hexagonal.domain.ports.api.FindAllClientiUseCase;
import co.idesoft.architetture.hexagonal.domain.ports.api.FindClienteByIdUseCase;
import co.idesoft.architetture.hexagonal.domain.valuables.ClienteDettaglio;
import co.idesoft.architetture.hexagonal.domain.valuables.CreareCliente;
import co.idesoft.architetture.mvcservices.exceptions.ConflictException;
import lombok.RequiredArgsConstructor;


@RequestMapping("api/clienti")
@RestController
@RequiredArgsConstructor
public class ClienteController {

    private final CreareClienteUseCase creareClienteUseCase;
    private final FindClienteByIdUseCase findClienteByIdUseCase;
    private final FindAllClientiUseCase findAllClientiUseCase;

    @PostMapping
    public ResponseEntity<ClienteCreatoDto> creareCliente(@RequestBody CreareClienteDto request) {
       
        try {
           Long clienteId = creareClienteUseCase.creareCliente(
                    new CreareCliente(request.nome(), request.descrizione()));
                    return new ResponseEntity<>(new ClienteCreatoDto(clienteId), HttpStatus.CREATED);
        } catch (ConflictException e) {
           return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

    }

    @GetMapping("{clienteId}")
    public ResponseEntity<ClienteDettaglioDto> getDettaglioCliente(@PathVariable Long clienteId) {
        Optional<ClienteDettaglio> clienteDettaglioOpt = findClienteByIdUseCase.findClienteByid(clienteId);
        if(clienteDettaglioOpt.isPresent()) {
            return ResponseEntity.ok(ClienteDettaglioDto.from(clienteDettaglioOpt.get()));
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping
    public ResponseEntity<Page<ClienteItemDto>> getAllCliente(Pageable pageable) {
        Page<ClienteItemDto> clientiPage = findAllClientiUseCase
            .findAllClienti(PageableFactory.from(pageable))
            .map(ClienteItemDto::from);

        return new ResponseEntity<>(clientiPage, HttpStatus.OK);
    }    
}
