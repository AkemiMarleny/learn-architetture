package co.idesoft.architetture.hexagonal.domain.services;

import org.springframework.stereotype.Service;

import co.idesoft.architetture.common.Page;
import co.idesoft.architetture.common.Pagination;
import co.idesoft.architetture.hexagonal.domain.ports.api.FindAllCassiereUseCase;
import co.idesoft.architetture.hexagonal.domain.ports.spi.CassieraRepository;
import co.idesoft.architetture.hexagonal.domain.valuables.CassieraItem;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FindAllCassiereUseCaseImpl implements FindAllCassiereUseCase {

    private final CassieraRepository cassieraRepository;

    @Override
    public Page<CassieraItem> findAllCassiere(Pagination pagination) {
        return cassieraRepository.findAll(pagination);
    }

}
