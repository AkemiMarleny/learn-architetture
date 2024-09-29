package co.idesoft.jacalservices.prodotto.hexagonal.domain.ports.spi;

import co.idesoft.jacalservices.prodotto.hexagonal.domain.valuables.SalvareCliente;

public interface ClienteRepository {
    Long save(SalvareCliente salvareCliente);
}
