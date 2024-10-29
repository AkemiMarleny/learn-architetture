package co.idesoft.architetture.mvc.repositories;

import co.idesoft.architetture.mvc.entities.ProdottoDisponibilita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProdottoDisponibilitaRepository extends JpaRepository<ProdottoDisponibilita, Long> {

    @Query("SELECT SUM(p.quantita) FROM ProdottoDisponibilita p WHERE p.prodottoId= ?1")
    Long sumQuantitaByProdottoId(Long prodottoId);

    Long countByWarehouseId(Long warehouseId);
}
