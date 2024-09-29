package co.idesoft.jacalservices.prodotto.hexagonal.domain.services;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import co.idesoft.jacalservices.prodotto.hexagonal.domain.ports.api.CreareClienteUseCase;
import co.idesoft.jacalservices.prodotto.hexagonal.domain.ports.spi.ClienteRepository;
import co.idesoft.jacalservices.prodotto.hexagonal.domain.valuables.CreareCliente;
import co.idesoft.jacalservices.prodotto.hexagonal.domain.valuables.SalvareCliente;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Service
@Validated
@RequiredArgsConstructor
public class CreareClienteUseCaseImpl implements CreareClienteUseCase {

    private final ClienteRepository clienteRepository;

    @Override
    public Long creareCliente(@Valid CreareCliente creareCliente) {
        return clienteRepository.save(new SalvareCliente(creareCliente.nome(), creareCliente.descrizione()));
    }

}
