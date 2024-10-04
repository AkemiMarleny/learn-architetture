package co.idesoft.architetture.hexagonal.domain.services;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import co.idesoft.architetture.hexagonal.domain.ports.api.CreareCassieraUseCase;
import co.idesoft.architetture.hexagonal.domain.ports.spi.CassieraRepository;
import co.idesoft.architetture.hexagonal.domain.valuables.CassieraChecksum;
import co.idesoft.architetture.hexagonal.domain.valuables.CreareCassiera;
import co.idesoft.architetture.hexagonal.domain.valuables.SalvareCassiera;
import co.idesoft.architetture.mvcservices.exceptions.ConflictException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Service
@Validated
@RequiredArgsConstructor
public class CreareCassieraUseCaseImpl implements CreareCassieraUseCase {

    private final CassieraRepository cassieraRepository;

    @Override
    public Long creareCassiera(@Valid CreareCassiera payload) throws ConflictException {

        CassieraChecksum cassieraChecksum = new CassieraChecksum(payload.nome());

        Long cassiereContatore = cassieraRepository.countByChecksum(cassieraChecksum.get());

        if (cassiereContatore > 0) {
            throw new ConflictException();
        }

        return cassieraRepository.save(new SalvareCassiera(
                payload.nome(),
                payload.descrizione(),
                cassieraChecksum));

    }

}
