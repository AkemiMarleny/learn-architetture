package co.idesoft.architetture.mvc.repositories;

import co.idesoft.architetture.mvc.entities.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WarehouseRepository extends JpaRepository<Warehouse, Long> {
}
