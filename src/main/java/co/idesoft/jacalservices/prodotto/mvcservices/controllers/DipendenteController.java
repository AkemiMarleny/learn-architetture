package co.idesoft.jacalservices.prodotto.mvcservices.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.idesoft.jacalservices.prodotto.mvcservices.controllers.dto.CreareDipendenteDto;
import co.idesoft.jacalservices.prodotto.mvcservices.controllers.dto.DipendenteCreatoDto;
import co.idesoft.jacalservices.prodotto.mvcservices.services.DipendenteService;
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
        Long dipendenteId = dipendenteService.save(request);
        return new ResponseEntity<>(new DipendenteCreatoDto(dipendenteId), HttpStatus.CREATED);
    }

}
