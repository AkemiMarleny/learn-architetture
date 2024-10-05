package co.idesoft.architetture.hexagonal.domain.services;

import org.springframework.stereotype.Service;

import co.idesoft.architetture.hexagonal.domain.ports.api.CancellareClienteUseCase;
import co.idesoft.architetture.hexagonal.domain.ports.spi.ClienteRepository;
import co.idesoft.architetture.mvcservices.exceptions.RecordNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CancellareClienteUseCaseImpl implements CancellareClienteUseCase {

    private final ClienteRepository clienteRepository;

    @Override
    public void cancellaCliente(Long clienteId) throws RecordNotFoundException {
        clienteRepository.deleteById(clienteId);
    }

}
