package co.idesoft.architetture.hexagonal.domain.services;

import java.util.Optional;

import org.springframework.stereotype.Service;

import co.idesoft.architetture.hexagonal.domain.ports.api.FindCassieraByIdUseCase;
import co.idesoft.architetture.hexagonal.domain.ports.spi.CassieraRepository;
import co.idesoft.architetture.hexagonal.domain.valuables.CassieraDettaglio;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FindCassieraByIdUseCaseImpl implements FindCassieraByIdUseCase {

    private final CassieraRepository cassieraRepository;

    @Override
    public Optional<CassieraDettaglio> findCassieraById(Long cassieraId) {
        return cassieraRepository.findById(cassieraId);
    }

}
