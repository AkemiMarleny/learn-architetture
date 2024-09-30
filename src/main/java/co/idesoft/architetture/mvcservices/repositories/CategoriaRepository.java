package co.idesoft.architetture.mvcservices.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import co.idesoft.architetture.mvcservices.entities.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
    
    Long countByChecksum(String checksum);
}