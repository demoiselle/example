package org.demoiselle.forum.dao;

import static java.lang.Long.parseLong;
import java.util.List;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.demoiselle.forum.entity.Topico;
import org.demoiselle.jee.core.api.crud.Result;
import org.demoiselle.jee.crud.AbstractDAO;
import org.demoiselle.jee.crud.DemoiselleRequestContext;
import org.demoiselle.jee.crud.pagination.ResultSet;

/**
 *
 * @author PauloGladson
 */
public class TopicoDAO extends AbstractDAO< Topico, String> {

    @Inject
    private DemoiselleRequestContext drc;

    /**
     *
     */
    @PersistenceContext(unitName = "forumPU")
    protected EntityManager em;

    /**
     *
     * @return
     */
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    /**
     *
     * @return
     */
    public Result findHandler() {
        Result result = new ResultSet();
        Integer firstResult = drc.getOffset() == null ? 0 : drc.getOffset();
        Integer total = drc.getLimit() == null ? 20 : drc.getLimit();
        List<Topico> list = getEntityManager().createQuery("SELECT c.description, c.categoria.description FROM Topico c ").setFirstResult(firstResult).setMaxResults(total).getResultList();
        drc.setCount(parseLong("" + list.size()));
        result.setContent(list);
        return result;
    }
    private static final Logger LOG = Logger.getLogger(TopicoDAO.class.getName());
}
