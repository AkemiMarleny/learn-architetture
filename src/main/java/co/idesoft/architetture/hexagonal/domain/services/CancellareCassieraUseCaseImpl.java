package co.idesoft.architetture.hexagonal.domain.services;

import org.springframework.stereotype.Service;

import co.idesoft.architetture.hexagonal.domain.ports.api.CancellareCassieraUseCase;
import co.idesoft.architetture.hexagonal.domain.ports.spi.CassieraRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CancellareCassieraUseCaseImpl implements CancellareCassieraUseCase {

    private final CassieraRepository cassieraRepository;

    @Override
    public void cancellaCassiera(Long cassieraId) {
        cassieraRepository.deleteById(cassieraId);
    }

}
