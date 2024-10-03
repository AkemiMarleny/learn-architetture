package co.idesoft.architetture.hexagonal.domain.services;

import java.util.Arrays;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import co.idesoft.architetture.hexagonal.domain.ports.api.AggiornareClienteUseCase;
import co.idesoft.architetture.hexagonal.domain.ports.spi.ClienteRepository;
import co.idesoft.architetture.hexagonal.domain.valuables.AggiornareCliente;
import co.idesoft.architetture.hexagonal.domain.valuables.ClienteChecksum;
import co.idesoft.architetture.hexagonal.domain.valuables.SalvaAggiornamentoCliente;
import co.idesoft.architetture.mvcservices.exceptions.ConflictException;
import co.idesoft.architetture.mvcservices.exceptions.RecordNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Service
@Validated
@RequiredArgsConstructor
public class AggiornareClienteUseCaseImpl implements AggiornareClienteUseCase {

    private final ClienteRepository clienteRepository;

    @Override
    public void aggiornaCliente(@Valid AggiornareCliente payload) throws ConflictException, RecordNotFoundException {
        ClienteChecksum clienteChecksum = new ClienteChecksum(payload.nome());

        Long clientiContatore = clienteRepository.countByChecksumAndIdNotIn(clienteChecksum.get(),
                Arrays.asList(payload.id()));

        if (clientiContatore > 0) {
            throw new ConflictException();
        }

        clienteRepository.update(new SalvaAggiornamentoCliente(
                payload.id(),
                payload.nome(),
                payload.descrizione(),
                clienteChecksum));
    }
}
