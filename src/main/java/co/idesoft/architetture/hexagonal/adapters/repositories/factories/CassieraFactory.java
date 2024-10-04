package co.idesoft.architetture.hexagonal.adapters.repositories.factories;

import co.idesoft.architetture.hexagonal.adapters.repositories.dao.Cassiera;
import co.idesoft.architetture.hexagonal.domain.valuables.CassieraDettaglio;
import co.idesoft.architetture.hexagonal.domain.valuables.CassieraItem;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CassieraFactory {

    public static CassieraDettaglio cassieraDettaglioFrom(Cassiera cassiera) {
        return new CassieraDettaglio(
                cassiera.getId(),
                cassiera.getNome(),
                cassiera.getDescrizione());
    }

    public static CassieraItem cassieraItemFrom(Cassiera cassiera) {
        return new CassieraItem(
                cassiera.getId(),
                cassiera.getNome(),
                cassiera.getDescrizione());
    }

}
