package org.demoiselle.forum.dao;

import java.util.List;
import javax.inject.Inject;
import org.demoiselle.forum.entity.Topico;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.demoiselle.jee.core.api.crud.Result;
import org.demoiselle.jee.crud.AbstractDAO;
import org.demoiselle.jee.crud.DemoiselleRequestContext;
import org.demoiselle.jee.crud.pagination.ResultSet;

public class TopicoDAO extends AbstractDAO< Topico, String> {

    @Inject
    private DemoiselleRequestContext drc;

    @PersistenceContext(unitName = "forumPU")
    protected EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public Result findHandler() {
        Result result = new ResultSet();
        Integer firstResult = drc.getOffset() == null ? 0 : drc.getOffset();
        Integer total = drc.getLimit() == null ? 20 : drc.getLimit();
        List<Topico> list = getEntityManager().createQuery("SELECT c.description, c.categoria.description FROM Topico c ").setFirstResult(firstResult).setMaxResults(total).getResultList();
        drc.setCount(Long.parseLong("" + list.size()));
        result.setContent(list);
        return result;
    }
}
