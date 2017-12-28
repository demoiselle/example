package org.demoiselle.biblia.bc;

import java.util.List;
import org.demoiselle.biblia.entity.Versiculo;
import java.util.logging.Logger;
import static java.util.logging.Logger.getLogger;
import org.demoiselle.biblia.constants.ResponseFTS;
import org.demoiselle.biblia.dao.VersiculoDAO;
import org.demoiselle.jee.crud.AbstractBusiness;

public class VersiculoBC extends AbstractBusiness< Versiculo, Integer> {

    private static final Logger LOG = getLogger(VersiculoBC.class.getName());

    public List<ResponseFTS> listarVersiculosFTS(String nome) {
        return ((VersiculoDAO) dao).listarVersiculosFTS(nome);
    }

    public void reindex() {
        ((VersiculoDAO) dao).reindex();
    }
}
