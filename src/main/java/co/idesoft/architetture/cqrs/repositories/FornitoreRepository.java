package co.idesoft.architetture.cqrs.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import co.idesoft.architetture.cqrs.entities.Fornitore;

public interface FornitoreRepository extends JpaRepository<Fornitore, Long> {

}
