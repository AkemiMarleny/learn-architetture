package co.idesoft.architetture.hexagonal.domain.services;

import org.springframework.stereotype.Service;

import co.idesoft.architetture.common.Page;
import co.idesoft.architetture.common.Pagination;
import co.idesoft.architetture.hexagonal.domain.ports.api.FindAllClientiUseCase;
import co.idesoft.architetture.hexagonal.domain.ports.spi.ClienteRepository;
import co.idesoft.architetture.hexagonal.domain.valuables.ClienteItem;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FindAllClientiUseCaseImpl implements FindAllClientiUseCase {
  private final ClienteRepository clienteRepository;

  @Override
  public Page<ClienteItem> findAllClienti(Pagination pagination, String query) {
    return clienteRepository.findAll(pagination, query);
  }

}
