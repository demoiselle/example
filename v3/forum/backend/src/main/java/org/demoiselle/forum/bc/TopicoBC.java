package org.demoiselle.forum.bc;

import org.demoiselle.forum.dao.TopicoDAO;
import org.demoiselle.forum.entity.Topico;
import org.demoiselle.jee.core.api.crud.Result;
import org.demoiselle.jee.crud.AbstractBusiness;

public class TopicoBC extends AbstractBusiness< Topico, String> {

    public Result findHandler() {
        return ((TopicoDAO) dao).findHandler();
    }
}
