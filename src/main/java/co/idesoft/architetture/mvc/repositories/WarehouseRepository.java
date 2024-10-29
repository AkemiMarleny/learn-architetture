package co.idesoft.architetture.mvc.repositories;

import co.idesoft.architetture.mvc.entities.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface WarehouseRepository extends JpaRepository<Warehouse, Long> {

    @Modifying
    @Query("DELETE FROM Warehouse w WHERE w.id = ?1 AND NOT EXISTS " +
            "(SELECT 1 FROM ProdottoDisponibilita pd WHERE pd.warehouseId = w.id)")
    int deleteByWareHouseId(Long id);

}
