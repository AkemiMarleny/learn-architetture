package co.idesoft.architetture.cqrs.controllers;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.idesoft.architetture.cqrs.dtos.FornitoreDettaglioDto;
import co.idesoft.architetture.cqrs.dtos.FornitoreItemDto;
import co.idesoft.architetture.cqrs.entities.Fornitore;
import co.idesoft.architetture.cqrs.repositories.FornitoreRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("api/fornitori")
@RequiredArgsConstructor
@Slf4j
public class FornitoreReaderController {
    private final FornitoreRepository fornitoreRepository;

    @GetMapping("{prodottoId}")
    public ResponseEntity<FornitoreDettaglioDto> getDettaglioFornitore(@PathVariable Long prodottoId) {
        Optional<Fornitore> fornitoreOpt = fornitoreRepository.findById(prodottoId);
        if (fornitoreOpt.isPresent()) {
            return ResponseEntity.ok(FornitoreDettaglioDto.from(fornitoreOpt.get()));
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping
    public ResponseEntity<Page<FornitoreItemDto>> getAllFornitore(Pageable pageable, String q) {
        Page<FornitoreItemDto> fornitoriPage = fornitoreRepository.findByNomeContaining(pageable, q)
                .map(FornitoreItemDto::fromEntity);
        return ResponseEntity.ok(fornitoriPage);
    }
}
