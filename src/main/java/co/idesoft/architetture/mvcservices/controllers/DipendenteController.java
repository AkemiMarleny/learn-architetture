package co.idesoft.architetture.mvcservices.controllers;

import org.springframework.data.domain.Page;
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

import co.idesoft.architetture.mvcservices.controllers.dto.AggiornareDipendenteDto;
import co.idesoft.architetture.mvcservices.controllers.dto.CreareDipendenteDto;
import co.idesoft.architetture.mvcservices.controllers.dto.DipendenteCreatoDto;
import co.idesoft.architetture.mvcservices.controllers.dto.DipendenteDettaglioDto;
import co.idesoft.architetture.mvcservices.controllers.dto.DipendenteItemDto;
import co.idesoft.architetture.mvcservices.entities.Dipendente;
import co.idesoft.architetture.mvcservices.exceptions.ConflictException;
import co.idesoft.architetture.mvcservices.exceptions.RecordNotFoundException;
import co.idesoft.architetture.mvcservices.services.DipendenteService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("api/dipendenti")
@RequiredArgsConstructor
@Slf4j
public class DipendenteController {

    private final DipendenteService dipendenteService;

    @PostMapping
    public ResponseEntity<DipendenteCreatoDto> creareDipendente(@RequestBody CreareDipendenteDto request) {
        log.info("creando un nuovo dipendente con request: {}, request");

        try {
            Long dipendenteId = dipendenteService.save(request);
            return new ResponseEntity<>(new DipendenteCreatoDto(dipendenteId), HttpStatus.CREATED);
        } catch (ConflictException e) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @GetMapping
    public ResponseEntity<Page<DipendenteItemDto>> getAllDipendenti(Pageable pageable, String q) {
        Page<DipendenteItemDto> dipendentiPage = dipendenteService.findAll(pageable, q)
                .map(DipendenteItemDto::fromEntity);

        return ResponseEntity.ok(dipendentiPage);
    }

    @GetMapping("{dipendentiId}")
    public ResponseEntity<DipendenteDettaglioDto> getDettaglioDipendente(@PathVariable Long dipendentiId) {
        try {
            Dipendente dipendenteEnt = dipendenteService.findDettaglio(dipendentiId);
            DipendenteDettaglioDto dipendenteDettaglioDto = DipendenteDettaglioDto.fromEntity(dipendenteEnt);
            return new ResponseEntity<>(dipendenteDettaglioDto, HttpStatus.OK);
        } catch (RecordNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("{dipendentiId}")
    public ResponseEntity<Void> updateDipendente(@PathVariable Long dipendentiId,
            @RequestBody AggiornareDipendenteDto request) throws ConflictException {
        try {
            dipendenteService.update(dipendentiId, request);
        } catch (ConflictException e) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        catch (RecordNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("{dipendentiId}")
    public ResponseEntity<Void> cancellaDipendente(@PathVariable Long dipendentiId) {
        dipendenteService.cancella(dipendentiId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
