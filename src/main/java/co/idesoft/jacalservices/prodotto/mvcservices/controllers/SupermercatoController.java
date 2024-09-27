package co.idesoft.jacalservices.prodotto.mvcservices.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.idesoft.jacalservices.prodotto.mvcservices.controllers.dto.CreareSupermercatoDto;
import co.idesoft.jacalservices.prodotto.mvcservices.controllers.dto.SupermercatoCreatoDto;
import co.idesoft.jacalservices.prodotto.mvcservices.services.SupermercatoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("api/supermercati")
@RequiredArgsConstructor
@Slf4j
public class SupermercatoController {

    private final SupermercatoService supermercatoService;

    @PostMapping
    public ResponseEntity<SupermercatoCreatoDto> creareSupermercato(@RequestBody CreareSupermercatoDto request) {
        log.info("creando un nuovo supermercato con request: {}, request");
        Long supermercatoId = supermercatoService.save(request);
        return new ResponseEntity<>(new SupermercatoCreatoDto(supermercatoId), HttpStatus.CREATED);
    }

}
