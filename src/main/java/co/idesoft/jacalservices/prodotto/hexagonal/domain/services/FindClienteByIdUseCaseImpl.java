package co.idesoft.jacalservices.prodotto.hexagonal.domain.services;

import java.util.Optional;

import org.springframework.stereotype.Service;

import co.idesoft.jacalservices.prodotto.hexagonal.domain.ports.api.FindClienteByIdUseCase;
import co.idesoft.jacalservices.prodotto.hexagonal.domain.ports.spi.ClienteRepository;
import co.idesoft.jacalservices.prodotto.hexagonal.domain.valuables.ClienteDettaglio;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FindClienteByIdUseCaseImpl implements FindClienteByIdUseCase {

    private final ClienteRepository clienteRepository;

    @Override
    public Optional<ClienteDettaglio> findClienteByid(Long clienteId) {
        return clienteRepository.findById(clienteId);
    }
    
}
