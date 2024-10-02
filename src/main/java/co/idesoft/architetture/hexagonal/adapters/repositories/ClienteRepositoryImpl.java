package co.idesoft.architetture.hexagonal.adapters.repositories;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import co.idesoft.architetture.common.Page;
import co.idesoft.architetture.common.Pagination;
import co.idesoft.architetture.common.factories.PageableFactory;
import co.idesoft.architetture.hexagonal.adapters.repositories.dao.Cliente;
import co.idesoft.architetture.hexagonal.adapters.repositories.factories.ClienteFactory;
import co.idesoft.architetture.hexagonal.adapters.repositories.jpa.JpaClienteRepository;
import co.idesoft.architetture.hexagonal.domain.ports.spi.ClienteRepository;
import co.idesoft.architetture.hexagonal.domain.valuables.ClienteDettaglio;
import co.idesoft.architetture.hexagonal.domain.valuables.ClienteItem;
import co.idesoft.architetture.hexagonal.domain.valuables.SalvareCliente;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Repository
public class ClienteRepositoryImpl implements ClienteRepository {

    private final JpaClienteRepository jpaClienteRepository;

    @Override
    public Long countByChecksum(String checksum) {
      return jpaClienteRepository.countByChecksum(checksum);
    }

    @Override
    public Long save(SalvareCliente salvareCliente) {
        return jpaClienteRepository.save(Cliente.from(salvareCliente)).getId();
    }

    @Override
    public Optional<ClienteDettaglio> findById(Long clienteId) {
        return jpaClienteRepository.findById(clienteId).map(ClienteFactory::clienteDettaglioFrom);  
    }

    @Override
    public Page<ClienteItem> findAll(Pagination pagination) {
        return PageableFactory.from(jpaClienteRepository.findAll(PageableFactory.from(pagination))
            .map(ClienteFactory::clienteItemFrom));
    }

    

}
