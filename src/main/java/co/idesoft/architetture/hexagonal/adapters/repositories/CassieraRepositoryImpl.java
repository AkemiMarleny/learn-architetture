package co.idesoft.architetture.hexagonal.adapters.repositories;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import co.idesoft.architetture.common.Page;
import co.idesoft.architetture.common.Pagination;
import co.idesoft.architetture.common.factories.PageableFactory;
import co.idesoft.architetture.hexagonal.adapters.repositories.dao.Cassiera;
import co.idesoft.architetture.hexagonal.adapters.repositories.factories.CassieraFactory;
import co.idesoft.architetture.hexagonal.adapters.repositories.jpa.JpaCassieraRepository;
import co.idesoft.architetture.hexagonal.domain.ports.spi.CassieraRepository;
import co.idesoft.architetture.hexagonal.domain.valuables.CassieraDettaglio;
import co.idesoft.architetture.hexagonal.domain.valuables.CassieraItem;
import co.idesoft.architetture.hexagonal.domain.valuables.SalvareCassiera;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class CassieraRepositoryImpl implements CassieraRepository {

    private final JpaCassieraRepository jpaCassieraRepository;

    @Override
    public Long countByChecksum(String checksum) {
        return jpaCassieraRepository.countByChecksum(checksum);
    }

    @Override
    public Long save(SalvareCassiera salvareCassiera) {
        return jpaCassieraRepository.save(Cassiera.from(salvareCassiera)).getId();
    }

    @Override
    public Optional<CassieraDettaglio> findById(Long cassieraId) {
        return jpaCassieraRepository.findById(cassieraId).map(CassieraFactory::cassieraDettaglioFrom);
    }

    @Override
    public Page<CassieraItem> findAll(Pagination pagination) {
        return PageableFactory.from(jpaCassieraRepository.findAll(PageableFactory.from(pagination))
                .map(CassieraFactory::cassieraItemFrom));
    }

}
