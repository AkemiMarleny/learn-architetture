package co.idesoft.architetture.mvc.controllers;

import co.idesoft.architetture.mvc.entities.VWarehouse;
import co.idesoft.architetture.mvc.entities.Warehouse;
import co.idesoft.architetture.mvc.repositories.ProdottoDisponibilitaRepository;
import co.idesoft.architetture.mvc.repositories.WarehouseRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping("api/warehouses")
@RestController
@RequiredArgsConstructor
@Slf4j
public class WarehouseController {

    private final WarehouseRepository warehouseRepository;
    private final ProdottoDisponibilitaRepository prodottoDisponibilitaRepository;

    @GetMapping
    public ResponseEntity<List<VWarehouse>> getAllWarehouses() {

        List<VWarehouse> vWarehouseList = warehouseRepository.findAllWithTotals();

        return ResponseEntity.ok(vWarehouseList);
    }

    //@Transactional
    @DeleteMapping("{warehouseId}")
    public ResponseEntity<Void> cancellaWarehouse(@PathVariable Long warehouseId) {

        Optional<Warehouse> warehouseOpt = warehouseRepository.findById(warehouseId);

        if (warehouseOpt.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        // no permite eliminar (se usa el metodo countBy...) porque warehouseId tiene una relacion con otra tabla
        long totaleWarehouseId = prodottoDisponibilitaRepository.countByWarehouseId(warehouseId);

        if (totaleWarehouseId > 0) {
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        }

        warehouseRepository.deleteById(warehouseId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        //      int risultato = warehouseRepository.deleteByWareHouseId(warehouseId);
//        log.info("risultato: {}", risultato);
//
//        if (risultato == 1) {
//            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//        }
    }
}
