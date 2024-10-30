package co.idesoft.architetture.mvc.repositories;

import co.idesoft.architetture.mvc.entities.VWarehouse;
import co.idesoft.architetture.mvc.entities.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface WarehouseRepository extends JpaRepository<Warehouse, Long> {

//    @Modifying
//    @Query("DELETE FROM Warehouse w WHERE w.id = ?1 AND NOT EXISTS " +
//            "(SELECT 1 FROM ProdottoDisponibilita pd WHERE pd.warehouseId = w.id)")
//    int deleteByWareHouseId(Long id);

    @Query(value = "SELECT \n" +
            "w.id AS warehouse_id,\n" +
            "w.nome AS warehouse_nome,\n" +
            "w.descrizone AS warehouse_descrizione,\n" +
            "w.checksum AS warehouse_checksum,\n" +
            "COALESCE(SUM(pd.quantita), 0) AS totale_prodotti\n" +
            "FROM warehouses w\n" +
            "LEFT JOIN prodotti_disponibilita pd ON w.id = pd.warehouse_id\n" +
            "GROUP BY w.id", nativeQuery = true)
    List<VWarehouse> findAllWithTotals();
}
