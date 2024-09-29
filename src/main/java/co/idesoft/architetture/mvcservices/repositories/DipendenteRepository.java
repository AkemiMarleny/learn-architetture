package co.idesoft.architetture.mvcservices.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import co.idesoft.architetture.mvcservices.entities.Dipendente;

public interface DipendenteRepository extends JpaRepository<Dipendente, Long> {

}
