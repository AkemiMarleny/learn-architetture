package co.idesoft.architetture.hexagonal.domain.services;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import co.idesoft.architetture.hexagonal.domain.ports.api.CreareClienteUseCase;
import co.idesoft.architetture.hexagonal.domain.ports.spi.ClienteRepository;
import co.idesoft.architetture.hexagonal.domain.valuables.ClienteChecksum;
import co.idesoft.architetture.hexagonal.domain.valuables.CreareCliente;
import co.idesoft.architetture.hexagonal.domain.valuables.SalvareCliente;
import co.idesoft.architetture.mvcservices.exceptions.ConflictException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Service
@Validated
@RequiredArgsConstructor
public class CreareClienteUseCaseImpl implements CreareClienteUseCase {

    private final ClienteRepository clienteRepository;

    @Override
    public Long creareCliente(@Valid CreareCliente payload) throws ConflictException {
        ClienteChecksum clienteChecksum = new ClienteChecksum(payload.nome());

        Long clientiContatore = clienteRepository.countByChecksum(clienteChecksum.get());    

        if(clientiContatore > 0){
            throw new ConflictException();
        }

        return clienteRepository.save(new SalvareCliente(
            payload.nome(), 
            payload.descrizione(),
            clienteChecksum));
    }

}
