package co.idesoft.architetture.mvc.controllers;

import co.idesoft.architetture.mvc.controllers.dto.AggiornareProdottoDisponibilitaDto;
import co.idesoft.architetture.mvc.controllers.dto.CreareProdottoDisponibilitaDto;
import co.idesoft.architetture.mvc.controllers.dto.ProdottiDisponibilitaCreatoDto;
import co.idesoft.architetture.mvc.entities.Prodotto;
import co.idesoft.architetture.mvc.entities.ProdottoDisponibilita;
import co.idesoft.architetture.mvc.entities.ProdottoDisponibilitaLogs;
import co.idesoft.architetture.mvc.entities.Warehouse;
import co.idesoft.architetture.mvc.repositories.ProdottoDisponibilitaLogsRepository;
import co.idesoft.architetture.mvc.repositories.ProdottoDisponibilitaRepository;
import co.idesoft.architetture.mvc.repositories.ProdottoRepository;
import co.idesoft.architetture.mvc.repositories.WarehouseRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.concurrent.locks.ReentrantLock;

@RequestMapping("api/prodotti/{prodottoId}/disponibilita")
@RestController
@RequiredArgsConstructor
public class ProdottoDisponibilitaController {

    private final ProdottoDisponibilitaRepository prodottoDisponibilitaRepository;
    private final ProdottoRepository prodottoRepository;
    private final WarehouseRepository warehouseRepository;
    private final ReentrantLock reentrantLock = new ReentrantLock();
    private final ProdottoDisponibilitaLogsRepository prodottoDisponibilitaLogsRepository;

    @PostMapping
    public ResponseEntity<ProdottiDisponibilitaCreatoDto> creareDisponibilitaProdotto(
            @Valid @RequestBody CreareProdottoDisponibilitaDto request,
            @PathVariable Long prodottoId) {
        reentrantLock.lock();

        try {

            ProdottoDisponibilita prodottoDisponibilita = ProdottoDisponibilita.from(request, prodottoId);

            Optional<Prodotto> prodottoOpt = prodottoRepository.findById(prodottoId);

            if (prodottoOpt.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

            Optional<Warehouse> warehouseOpt = warehouseRepository.findById(request.warehouseId());

            if (warehouseOpt.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
            }

            Long prodottoDisponibilitaId = prodottoDisponibilitaRepository.save(prodottoDisponibilita).getId();

            //este metodo sumQuantitaByProdottoId(prodottoId) va a sumar todos los productos de una misma categoria
            //que existan en diferentes almacenes y el monto total (stockTotale) se almacenará en la tabla (Producto)
            Long stockTotale = prodottoDisponibilitaRepository.sumQuantitaByProdottoId(prodottoId);
            // Prodotto prodotto = prodottoOpt.get();
            // prodotto.setStockTotale(stockTotale);
            // prodottoRepository.save(prodotto);

            prodottoOpt.get().aggiornaCon(stockTotale);
            prodottoRepository.save(prodottoOpt.get());

            return new ResponseEntity<>(new ProdottiDisponibilitaCreatoDto(prodottoDisponibilitaId), HttpStatus.CREATED);
        } finally {
            reentrantLock.unlock();
        }
    }

    @PutMapping("{prodottoDisponibilitaId}")
    public ResponseEntity<Void> updateProdottoDisponibilita(@PathVariable Long prodottoId, @PathVariable Long prodottoDisponibilitaId,
                                                            @Valid @RequestBody AggiornareProdottoDisponibilitaDto request) {

        Optional<Prodotto> prodottoOpt = prodottoRepository.findById(prodottoId);
        if (prodottoOpt.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Optional<ProdottoDisponibilita> prodottoDisponibilitaOpt = prodottoDisponibilitaRepository.findByIdAndProdottoId(prodottoDisponibilitaId, prodottoId);
        if (prodottoDisponibilitaOpt.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        ProdottoDisponibilita prodottoDisponibilitaAModificare = prodottoDisponibilitaOpt.get();

        ProdottoDisponibilitaLogs prodottoDisponibilitaLogs = ProdottoDisponibilitaLogs.from(prodottoDisponibilitaAModificare);
        prodottoDisponibilitaLogsRepository.save(prodottoDisponibilitaLogs);

        prodottoDisponibilitaAModificare.aggiornaCon(request);

        Optional<Warehouse> warehouseOpt = warehouseRepository.findById(request.warehouseId());
        if (warehouseOpt.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        }

        prodottoDisponibilitaRepository.save(prodottoDisponibilitaAModificare);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
