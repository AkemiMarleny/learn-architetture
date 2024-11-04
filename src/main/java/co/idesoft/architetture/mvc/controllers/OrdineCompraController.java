package co.idesoft.architetture.mvc.controllers;

import co.idesoft.architetture.mvc.controllers.dto.CreareOrdineCompraProdottiDto;
import co.idesoft.architetture.mvc.controllers.dto.OrdineCompraCreatoDto;
import co.idesoft.architetture.mvc.entities.Customer;
import co.idesoft.architetture.mvc.entities.OrdineCompra;
import co.idesoft.architetture.mvc.entities.Prodotto;
import co.idesoft.architetture.mvc.entities.ProdottoDisponibilita;
import co.idesoft.architetture.mvc.repositories.CustomerRepository;
import co.idesoft.architetture.mvc.repositories.OrdineCompraRepository;
import co.idesoft.architetture.mvc.repositories.ProdottoDisponibilitaRepository;
import co.idesoft.architetture.mvc.repositories.ProdottoRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RequestMapping("api/ordini/compre")
@RestController
@RequiredArgsConstructor
@Slf4j
public class OrdineCompraController {

    private final OrdineCompraRepository ordineCompraRepository;
    private final ProdottoRepository prodottoRepository;
    private final CustomerRepository customerRepository;
    private final ProdottoDisponibilitaRepository prodottoDisponibilitaRepository;

    @Transactional
    @PostMapping
    public ResponseEntity<OrdineCompraCreatoDto> creareOrdineCompraProdotti(@Valid @RequestBody CreareOrdineCompraProdottiDto request) {

        log.info("creando una ordine di compra dei prodotti: {}", request);

        Optional<Prodotto> prodottoOpt = prodottoRepository.findById(request.prodottoId());

        if (prodottoOpt.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        }

        Optional<Customer> customerOpt = customerRepository.findById(request.customerId());

        if (customerOpt.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        }


        Optional<ProdottoDisponibilita> prodottoDisponibilitaOpt = prodottoDisponibilitaRepository
                .findFirstByProdottoIdAndQuantitaGreaterThanEqual(request.prodottoId(), request.quantita());
        if (prodottoDisponibilitaOpt.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        }

        ProdottoDisponibilita prodottoDisponibilitaAModificare = prodottoDisponibilitaOpt.get();
        prodottoDisponibilitaAModificare.setQuantita(prodottoDisponibilitaAModificare.getQuantita() - request.quantita());
        prodottoDisponibilitaRepository.save(prodottoDisponibilitaAModificare);

        Prodotto prodottoAModificare = prodottoOpt.get();

        Long stockTotale = prodottoDisponibilitaRepository.sumQuantitaByProdottoId(request.prodottoId());

        prodottoAModificare.setStockTotale(stockTotale);
        prodottoRepository.save(prodottoAModificare);

        OrdineCompra ordineCompra = OrdineCompra.from(request);

        Long ordineCompraId = ordineCompraRepository.save(ordineCompra).getId();

        return new ResponseEntity<>(new OrdineCompraCreatoDto(ordineCompraId), HttpStatus.CREATED);
    }
}
