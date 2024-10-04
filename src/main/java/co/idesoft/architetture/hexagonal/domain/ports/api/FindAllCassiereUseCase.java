package co.idesoft.architetture.hexagonal.domain.ports.api;

import co.idesoft.architetture.common.Page;
import co.idesoft.architetture.common.Pagination;
import co.idesoft.architetture.hexagonal.domain.valuables.CassieraItem;

public interface FindAllCassiereUseCase {
    Page<CassieraItem> findAllCassiere(Pagination pagination);

}
