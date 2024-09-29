package co.idesoft.architetture.mvc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import co.idesoft.architetture.mvc.entities.Prodotto;

public interface ProdottoRepository extends JpaRepository<Prodotto, Long> {

}
