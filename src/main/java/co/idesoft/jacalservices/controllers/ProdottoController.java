package co.idesoft.jacalservices.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.idesoft.jacalservices.controllers.dto.CreareProdottoDto;
import co.idesoft.jacalservices.controllers.dto.ProdottoCreatoDto;
import co.idesoft.jacalservices.controllers.dto.ProdottoDto;
import co.idesoft.jacalservices.entities.Prodotto;
import co.idesoft.jacalservices.repositories.ProdottoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequestMapping("api/prodotti")
@RestController
@RequiredArgsConstructor
@Slf4j
public class ProdottoController {

    private final ProdottoRepository prodottoRepository;

    @GetMapping
    public ResponseEntity<List<ProdottoDto>> getAllProdotti() {
        List<ProdottoDto> prodotti = prodottoRepository.findAll()
                .stream()
                .map(ProdottoDto::fromEntity)
                .collect(Collectors.toList());

        return ResponseEntity.ok(prodotti);
    }

    @PostMapping
    public ResponseEntity<ProdottoCreatoDto> creareProdotto(@RequestBody CreareProdottoDto request) {
        log.info("creando un nuovo prodotto con request: {}", request);

        Prodotto prodotto = new Prodotto();
        prodotto.setNome(request.nome());
        prodotto.setDescrizione(request.descrizione());

        Long prodottoId = prodottoRepository.save(prodotto).getProdottoId();

        return new ResponseEntity<>(new ProdottoCreatoDto(prodottoId), HttpStatus.CREATED);
    }

}
