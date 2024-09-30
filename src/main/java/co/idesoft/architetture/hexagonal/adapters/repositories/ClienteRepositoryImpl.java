package co.idesoft.architetture.hexagonal.adapters.repositories;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import co.idesoft.architetture.hexagonal.adapters.repositories.dao.Cliente;
import co.idesoft.architetture.hexagonal.adapters.repositories.factories.ClienteFactory;
import co.idesoft.architetture.hexagonal.adapters.repositories.jpa.JpaClienteRepository;
import co.idesoft.architetture.hexagonal.domain.ports.spi.ClienteRepository;
import co.idesoft.architetture.hexagonal.domain.valuables.ClienteDettaglio;
import co.idesoft.architetture.hexagonal.domain.valuables.SalvareCliente;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Repository
public class ClienteRepositoryImpl implements ClienteRepository {

    private final JpaClienteRepository jpaClienteRepository;

    @Override
    public Long save(SalvareCliente salvareCliente) {
        return jpaClienteRepository.save(Cliente.from(salvareCliente)).getId();
    }

    @Override
    public Optional<ClienteDettaglio> findById(Long clienteId) {
        return jpaClienteRepository.findById(clienteId).map(ClienteFactory::from);  
    }

}