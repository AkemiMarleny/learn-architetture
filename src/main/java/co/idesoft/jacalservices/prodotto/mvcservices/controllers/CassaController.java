package co.idesoft.jacalservices.prodotto.mvcservices.controllers;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.idesoft.jacalservices.prodotto.mvcservices.controllers.dto.CassaCreatoDto;
import co.idesoft.jacalservices.prodotto.mvcservices.controllers.dto.CassaItemDto;
import co.idesoft.jacalservices.prodotto.mvcservices.controllers.dto.CreareCassaDto;
import co.idesoft.jacalservices.prodotto.mvcservices.services.CassaService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("api/casse")
@RequiredArgsConstructor
@Slf4j
public class CassaController {

    private final CassaService cassaService;

    @PostMapping
    public ResponseEntity<CassaCreatoDto> creareCassa(@RequestBody CreareCassaDto request) {
        log.info("creando una nuova cassa con request: {}, request");
        Long cassaId = cassaService.save(request);
        return new ResponseEntity<>(new CassaCreatoDto(cassaId), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Page<CassaItemDto>> getAllCasse(Pageable pageable) {
        Page<CassaItemDto> cassePage = cassaService.findAll(pageable)
                .map(CassaItemDto::fromEntity);
        return ResponseEntity.ok(cassePage);
    }

}
