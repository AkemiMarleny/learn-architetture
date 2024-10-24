package co.idesoft.architetture.mvc.controllers;

import co.idesoft.architetture.mvc.controllers.dto.*;
import co.idesoft.architetture.mvc.entities.Prodotto;
import co.idesoft.architetture.mvc.entities.UnitaMisura;
import co.idesoft.architetture.mvc.repositories.ProdottoRepository;
import co.idesoft.architetture.mvc.repositories.UnitaMisuraRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Optional;

@RequestMapping("api/prodotti")
@RestController
@RequiredArgsConstructor
@Slf4j
public class ProdottoController {

    private final ProdottoRepository prodottoRepository;
    private final UnitaMisuraRepository unitaMisuraRepository;

    @GetMapping
    public ResponseEntity<Page<ProdottoItemDto>> getAllProdotti(Pageable pageable, @RequestParam String q) {
        // List<ProdottoItemDto> prodotti = prodottoRepository.findAll()
        // .stream()
        // .map(ProdottoItemDto::fromEntity)
        // .collect(Collectors.toList());

        Page<ProdottoItemDto> prodottiPage = prodottoRepository.findByNomeContaining(pageable, q)
                .map(ProdottoItemDto::fromEntity);

        return ResponseEntity.ok(prodottiPage);
    }

    @PostMapping
    public ResponseEntity<ProdottoCreatoDto> creareProdotto(@Valid @RequestBody CreareProdottoDto request) {
        log.info("creando un nuovo prodotto con request: {}", request);

        Prodotto prodotto = Prodotto.from(request);

        Long prodottiContatore = prodottoRepository.countByChecksum(prodotto.getChecksum());

        if (prodottiContatore > 0) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        Long prodottoId = prodottoRepository.save(prodotto)
                .getProdottoId();

        return new ResponseEntity<>(new ProdottoCreatoDto(prodottoId), HttpStatus.CREATED);
    }

    @GetMapping("{prodottoId}")
    public ResponseEntity<ProdottoDettaglioDto> getDettaglioProdotto(@PathVariable Long prodottoId) {
        Optional<Prodotto> prodotto = prodottoRepository.findById(prodottoId);

        if (prodotto.isPresent()) {
            ProdottoDettaglioDto prodottoDettaglioDto = ProdottoDettaglioDto.fromEntity(prodotto.get());

            return new ResponseEntity<>(prodottoDettaglioDto, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("{prodottoId}")
    public ResponseEntity<Void> updateProdotto(@PathVariable Long prodottoId,
                                               @Valid @RequestBody AggiornareProdottoDto request) {
        log.info("aggiornando il prodotto con id: {}", prodottoId);

        Optional<Prodotto> prodotto = prodottoRepository.findById(prodottoId);

        if (prodotto.isPresent()) {
            Prodotto prodottoAModificare = prodotto.get();

            prodottoAModificare.aggiornaCon(request);

            Long prodottiContatore = prodottoRepository.countByChecksumAndProdottoIdNotIn(
                    prodottoAModificare.getChecksum(),
                    Arrays.asList(prodottoId));

            if (prodottiContatore > 0) {
                return new ResponseEntity<>(HttpStatus.CONFLICT);
            }

            Optional<UnitaMisura> unitaMisuraOpt = unitaMisuraRepository.findById(request.unitaMisuraId());

            if (unitaMisuraOpt.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
            }

            prodottoRepository.save(prodottoAModificare);

            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("{prodottoId}")
    public ResponseEntity<Void> cancellaProdotto(@PathVariable Long prodottoId) {
        log.info("cancellando il prodotto con id: {}", prodottoId);
        prodottoRepository.deleteById(prodottoId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
