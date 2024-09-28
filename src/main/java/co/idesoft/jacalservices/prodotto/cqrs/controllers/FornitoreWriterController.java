package co.idesoft.jacalservices.prodotto.cqrs.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.idesoft.jacalservices.prodotto.cqrs.commands.CreareFornitoreCommand;
import co.idesoft.jacalservices.prodotto.cqrs.dtos.CreareFornitoreDto;
import co.idesoft.jacalservices.prodotto.cqrs.dtos.FornitoreCreatoDto;
import co.idesoft.jacalservices.prodotto.cqrs.services.FornitoreService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("api/fornitori")
@RequiredArgsConstructor
@Slf4j
public class FornitoreWriterController {

    private final FornitoreService prodottoService;

    @PostMapping
    public ResponseEntity<FornitoreCreatoDto> creareFornitore(@RequestBody CreareFornitoreDto request) {

        Long fornitoreId = prodottoService.creareFornitore(
                new CreareFornitoreCommand(request.nome(), request.descrizione()));

        return ResponseEntity.ok(new FornitoreCreatoDto(fornitoreId));
    }

}
