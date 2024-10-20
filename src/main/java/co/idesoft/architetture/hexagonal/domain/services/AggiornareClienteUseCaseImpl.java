package co.idesoft.architetture.hexagonal.domain.services;

import co.idesoft.architetture.hexagonal.domain.ports.api.AggiornareClienteUseCase;
import co.idesoft.architetture.hexagonal.domain.ports.spi.ClienteRepository;
import co.idesoft.architetture.hexagonal.domain.ports.spi.UtenteRepository;
import co.idesoft.architetture.hexagonal.domain.valuables.*;
import co.idesoft.architetture.hexagonal.domain.valueobjects.Cognome;
import co.idesoft.architetture.hexagonal.domain.valueobjects.Nome;
import co.idesoft.architetture.hexagonal.domain.valueobjects.Username;
import co.idesoft.architetture.mvcservices.exceptions.ConflictException;
import co.idesoft.architetture.mvcservices.exceptions.RecordNotFoundException;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.Arrays;
import java.util.Optional;

@Service
@Validated
@RequiredArgsConstructor
public class AggiornareClienteUseCaseImpl implements AggiornareClienteUseCase {

    private final ClienteRepository clienteRepository;
    private final UtenteRepository utenteRepository;

    @Override
    @Transactional
    public void aggiornaCliente(@Valid AggiornareCliente payload) throws ConflictException, RecordNotFoundException {

        Optional<ClienteDettaglio> clienteDettaglioOpt = clienteRepository.findById(payload.clienteId());

        if (clienteDettaglioOpt.isEmpty()) {
            throw new RecordNotFoundException();
        }

        Nome nome = new Nome(payload.nome());
        Cognome cognomePaterno = new Cognome(payload.cognomePaterno());
        Cognome cognomeMaterno = new Cognome(payload.cognomeMaterno());

        ClienteChecksum clienteChecksum = new ClienteChecksum(nome, cognomePaterno, cognomeMaterno);

        Long clientiContatore = clienteRepository.countByChecksumAndIdNotIn(clienteChecksum.get(),
                Arrays.asList(payload.clienteId()));

        if (clientiContatore > 0) {
            throw new ConflictException();
        }

        clienteRepository.update(new SalvaAggiornamentoCliente(
                payload.clienteId(),
                nome.get(),
                cognomePaterno.get(),
                cognomeMaterno.get(),
                payload.compleanno(),
                payload.descrizione(),
                clienteChecksum));

        Username username = new Username(
                nome.get(),
                cognomePaterno.get(),
                cognomeMaterno.get(),
                payload.compleanno()
        );

        Long utentiContatore = utenteRepository.countByUsernameAndIdNotIn(username.get(), Arrays.asList(clienteDettaglioOpt.get().utenteId()));
        if (utentiContatore > 0) {
            throw new ConflictException();
        }

        utenteRepository.update(new SalvaNuovoUsernameUtente(
                clienteDettaglioOpt.get().utenteId(),
                username.get()
        ));

    }
}
