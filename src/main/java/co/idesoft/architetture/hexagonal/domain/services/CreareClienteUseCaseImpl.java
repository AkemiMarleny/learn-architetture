package co.idesoft.architetture.hexagonal.domain.services;

import co.idesoft.architetture.common.enums.UtenteStati;
import co.idesoft.architetture.hexagonal.domain.ports.api.CreareClienteUseCase;
import co.idesoft.architetture.hexagonal.domain.ports.spi.ClienteRepository;
import co.idesoft.architetture.hexagonal.domain.ports.spi.UtenteRepository;
import co.idesoft.architetture.hexagonal.domain.valuables.ClienteChecksum;
import co.idesoft.architetture.hexagonal.domain.valuables.CreareCliente;
import co.idesoft.architetture.hexagonal.domain.valuables.SalvareCliente;
import co.idesoft.architetture.hexagonal.domain.valuables.SalvareUtente;
import co.idesoft.architetture.hexagonal.domain.valueobjects.Cognome;
import co.idesoft.architetture.hexagonal.domain.valueobjects.Nome;
import co.idesoft.architetture.hexagonal.domain.valueobjects.UserPassword;
import co.idesoft.architetture.hexagonal.domain.valueobjects.Username;
import co.idesoft.architetture.mvcservices.exceptions.ConflictException;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDateTime;

@Slf4j
@Service
@Validated
@RequiredArgsConstructor
public class CreareClienteUseCaseImpl implements CreareClienteUseCase {

    private final ClienteRepository clienteRepository;
    private final UtenteRepository utenteRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public Long creareCliente(@Valid CreareCliente payload) throws ConflictException {

        Nome nome = new Nome(payload.nome());
        Cognome cognomePaterno = new Cognome(payload.cognomePaterno());
        Cognome cognomeMaterno = new Cognome(payload.cognomeMaterno());

        ClienteChecksum clienteChecksum = new ClienteChecksum(nome, cognomePaterno, cognomeMaterno);

        Long clientiContatore = clienteRepository.countByChecksum(clienteChecksum.get());

        if (clientiContatore > 0) {
            throw new ConflictException();
        }

        Username username = new Username(
                nome.get(),
                cognomePaterno.get(),
                cognomeMaterno.get(),
                payload.compleanno()
        );

        Long utentiContatore = utenteRepository.countByUsername(username.get());

        if (utentiContatore > 0) {
            throw new ConflictException();
        }

        UserPassword userPassword = new UserPassword(
                passwordEncoder,
                LocalDateTime.now().toString()
        );

        Long utenteId = utenteRepository.save(new SalvareUtente(
                username.get(),
                userPassword.get(),
                UtenteStati.UNAUTHORIZED.getCodice()
        ));

        log.info("utente creato con id: {}", utenteId);

        Long clienteId = clienteRepository.save(new SalvareCliente(
                nome.get(),
                cognomePaterno.get(),
                cognomeMaterno.get(),
                payload.compleanno(),
                payload.descrizione(),
                clienteChecksum,
                utenteId
        ));

        log.info("cliente creato con id: {}", clienteId);

        return utenteId;
    }

}
