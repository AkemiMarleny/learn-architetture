package co.idesoft.jacalservices.prodotto.mvcservices.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import co.idesoft.jacalservices.prodotto.mvcservices.entities.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

}