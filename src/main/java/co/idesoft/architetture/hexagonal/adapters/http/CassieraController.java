package co.idesoft.architetture.hexagonal.adapters.http;

import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.idesoft.architetture.common.Page;
import co.idesoft.architetture.common.factories.PageableFactory;
import co.idesoft.architetture.hexagonal.adapters.http.dto.AggiornareCassieraDto;
import co.idesoft.architetture.hexagonal.adapters.http.dto.CassieraCreataDto;
import co.idesoft.architetture.hexagonal.adapters.http.dto.CassieraDettaglioDto;
import co.idesoft.architetture.hexagonal.adapters.http.dto.CassieraItemDto;
import co.idesoft.architetture.hexagonal.adapters.http.dto.CreareCassieraDto;
import co.idesoft.architetture.hexagonal.domain.ports.api.AggiornareCassieraUseCase;
import co.idesoft.architetture.hexagonal.domain.ports.api.CancellareCassieraUseCase;
import co.idesoft.architetture.hexagonal.domain.ports.api.CreareCassieraUseCase;
import co.idesoft.architetture.hexagonal.domain.ports.api.FindAllCassiereUseCase;
import co.idesoft.architetture.hexagonal.domain.ports.api.FindCassieraByIdUseCase;
import co.idesoft.architetture.hexagonal.domain.valuables.AggiornaCassiera;
import co.idesoft.architetture.hexagonal.domain.valuables.CassieraDettaglio;
import co.idesoft.architetture.hexagonal.domain.valuables.CreareCassiera;
import co.idesoft.architetture.mvcservices.exceptions.ConflictException;
import co.idesoft.architetture.mvcservices.exceptions.RecordNotFoundException;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/cassiere")
@RequiredArgsConstructor
public class CassieraController {

    private final CreareCassieraUseCase creareCassieraUseCase;
    private final FindCassieraByIdUseCase findCassieraByIdUseCase;
    private final FindAllCassiereUseCase findAllCassiereUseCase;
    private final AggiornareCassieraUseCase aggiornareCassieraUseCase;
    private final CancellareCassieraUseCase cancellareCassieraUseCase;

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

    @GetMapping("{cassieraId}")
    public ResponseEntity<CassieraDettaglioDto> getDettaglioCassiera(@PathVariable Long cassieraId) {

        Optional<CassieraDettaglio> cassieraDettaglio = findCassieraByIdUseCase.findCassieraById(cassieraId);

        if (cassieraDettaglio.isPresent()) {
            return ResponseEntity.ok(CassieraDettaglioDto.from(cassieraDettaglio.get()));
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping
    public ResponseEntity<Page<CassieraItemDto>> getAllCassiera(Pageable pageable, String q) {

        Page<CassieraItemDto> cassierePage = findAllCassiereUseCase.findAllCassiere(
                PageableFactory.from(pageable), q).map(CassieraItemDto::from);

        return new ResponseEntity<>(cassierePage, HttpStatus.OK);
    }

    @PutMapping("{cassieraId}")
    public ResponseEntity<Void> updateCassiera(@PathVariable Long cassieraId,
            @RequestBody AggiornareCassieraDto request) {

        try {
            aggiornareCassieraUseCase.aggiornaCassiera(new AggiornaCassiera(
                    cassieraId,
                    request.nome(),
                    request.descrizione()));
        } catch (ConflictException e) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);

        } catch (RecordNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("{cassieraId}")
    public ResponseEntity<Void> cancellaCassiera(@PathVariable Long cassieraId) {
        cancellareCassieraUseCase.cancellaCassiera(cassieraId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
