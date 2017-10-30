package org.demoiselle.forum.bc;

import java.util.logging.Logger;
import org.demoiselle.forum.dao.TopicoDAO;
import org.demoiselle.forum.entity.Topico;
import org.demoiselle.jee.core.api.crud.Result;
import org.demoiselle.jee.crud.AbstractBusiness;

/**
 *
 * @author PauloGladson
 */
public class TopicoBC extends AbstractBusiness< Topico, String> {

    /**
     *
     * @return
     */
    public Result findHandler() {
        return ((TopicoDAO) dao).findHandler();
    }
    private static final Logger LOG = Logger.getLogger(TopicoBC.class.getName());
}
