package co.idesoft.architetture.hexagonal.adapters.repositories;

import java.util.List;
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
import co.idesoft.architetture.hexagonal.domain.valuables.SalvaAggiornamentoCliente;
import co.idesoft.architetture.hexagonal.domain.valuables.SalvareCliente;
import co.idesoft.architetture.mvcservices.exceptions.RecordNotFoundException;
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
        return jpaClienteRepository.findByIdAndDataEliminazioneIsNull(clienteId)
                .map(ClienteFactory::clienteDettaglioFrom);
    }

    @Override
    public Page<ClienteItem> findAll(Pagination pagination, String query) {
        return PageableFactory
                .from(jpaClienteRepository
                        .findByNomeContainingAndDataEliminazioneIsNull(PageableFactory.from(pagination), query)
                        .map(ClienteFactory::clienteItemFrom));
    }

    @Override
    public void update(SalvaAggiornamentoCliente payload) throws RecordNotFoundException {
        Cliente cliente = jpaClienteRepository.findById(payload.id()).orElseThrow(RecordNotFoundException::new);

        cliente.aggiornaCon(payload);

        jpaClienteRepository.save(cliente);
    }

    @Override
    public Long countByChecksumAndIdNotIn(String checksum, List<Long> ids) {
        return jpaClienteRepository.countByChecksumAndIdNotIn(checksum, ids);
    }

    @Override
    public void deleteById(Long clienteId) throws RecordNotFoundException {
        Cliente cliente = jpaClienteRepository.findById(clienteId).orElseThrow(RecordNotFoundException::new);

        cliente.cancella();

        jpaClienteRepository.save(cliente);
    }

}
