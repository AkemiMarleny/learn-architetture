package co.idesoft.jacalservices.prodotto.hexagonal.adapters.repositories.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import co.idesoft.jacalservices.prodotto.hexagonal.adapters.repositories.dao.Cliente;

public interface JpaClienteRepository extends JpaRepository<Cliente, Long> {
    
}
