package co.idesoft.architetture.mvc.controllers;

import co.idesoft.architetture.mvc.repositories.WarehouseRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("api/warehouses")
@RestController
@RequiredArgsConstructor
@Slf4j
public class WarehouseController {

    private final WarehouseRepository warehouseRepository;

    @Transactional
    @DeleteMapping("{warehouseId}")
    public ResponseEntity<Void> cancellaWarehouse(@PathVariable Long warehouseId) {

        int risultato = warehouseRepository.deleteByWareHouseId(warehouseId);
        log.info("risultato: {}", risultato);

        if (risultato == 1) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
