package co.idesoft.jacalservices.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.idesoft.jacalservices.controllers.dto.ProdottoDto;
import co.idesoft.jacalservices.repositories.ProdottoRepository;
import lombok.RequiredArgsConstructor;

@RequestMapping("api/prodotti")
@RestController
@RequiredArgsConstructor
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

}
