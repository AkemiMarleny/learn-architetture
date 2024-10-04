package co.idesoft.architetture.hexagonal.adapters.http;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.idesoft.architetture.hexagonal.adapters.http.dto.CassieraCreataDto;
import co.idesoft.architetture.hexagonal.adapters.http.dto.CreareCassieraDto;
import co.idesoft.architetture.hexagonal.domain.ports.api.CreareCassieraUseCase;
import co.idesoft.architetture.hexagonal.domain.valuables.CreareCassiera;
import co.idesoft.architetture.mvcservices.exceptions.ConflictException;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/cassiere")
@RequiredArgsConstructor
public class CassieraController {

    private final CreareCassieraUseCase creareCassieraUseCase;

    @PostMapping
    public ResponseEntity<CassieraCreataDto> creareCassiera(@RequestBody CreareCassieraDto request) {

        try {
            Long cassieraId = creareCassieraUseCase.creareCassiera(new CreareCassiera(
                    request.nome(),
                    request.descrizione()));
            return new ResponseEntity<>(new CassieraCreataDto(cassieraId), HttpStatus.CREATED);
        } catch (ConflictException e) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

}
