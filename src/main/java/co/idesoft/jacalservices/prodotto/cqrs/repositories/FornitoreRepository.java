package co.idesoft.jacalservices.prodotto.cqrs.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import co.idesoft.jacalservices.prodotto.cqrs.entities.Fornitore;

public interface FornitoreRepository extends JpaRepository<Fornitore, Long> {

}
