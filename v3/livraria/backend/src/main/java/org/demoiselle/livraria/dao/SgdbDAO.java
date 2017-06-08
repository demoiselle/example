package org.demoiselle.livraria.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Schedule;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TemporalType;
import javax.sql.DataSource;
import javax.transaction.Transactional;
import javax.ws.rs.core.Response;
import org.demoiselle.jee.security.exception.DemoiselleSecurityException;
import org.demoiselle.livraria.tenant.Sgdb;

/**
 *
 * @author 70744416353
 */
public class SgdbDAO {

    private DataSource dataSource;

    @PersistenceContext(unitName = "pu")
    private EntityManager em;

    protected EntityManager getEntityManager() {
        return em.getEntityManagerFactory().createEntityManager();
    }
   
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public boolean createSgdb(String schema) {
        Connection conn = null;
        List<String> records = new ArrayList<>();
        List<Sgdb> lista = em.createQuery("SELECT s FROM Sgdb s ORDER BY s.id", Sgdb.class).getResultList();
        lista.forEach((sgdb) -> {
            records.add(sgdb.getComando());
        });

        try {
            // Create Schema
            final Context init = new InitialContext();
            dataSource = (DataSource) init.lookup("java:jboss/datasources/PostgreSQLDS");

            conn = dataSource.getConnection();
            conn.createStatement().execute(" CREATE SCHEMA " + schema + " AUTHORIZATION postgres; ");
            conn.createStatement().execute(" SET search_path to " + schema);

            for (String ddlLine : records) {
                conn.createStatement().execute(ddlLine);
            }

        } catch (NamingException | SQLException ex) {
            Logger.getLogger(SgdbDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new DemoiselleSecurityException("Erro ao registrar Livraria", Response.Status.PRECONDITION_FAILED.getStatusCode());
        } finally {
            try {
                if (conn != null && !conn.isClosed()) {
                    conn.createStatement().execute(" SET search_path to public ");
                    conn.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(SgdbDAO.class.getName()).log(Level.SEVERE, null, ex);
                throw new DemoiselleSecurityException("Erro ao registrar Livraria", Response.Status.PRECONDITION_FAILED.getStatusCode());
            }
        }
        return true;
    }

    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public boolean updateSgdb(Date dia) {
        Connection conn = null;
        List<String> records = new ArrayList<>();
        List<Sgdb> listaComandos = em.createQuery("SELECT s FROM Sgdb s WHERE s.dia = :dia ORDER BY s.id", Sgdb.class).setParameter("dia", dia, TemporalType.DATE).getResultList();
        listaComandos.forEach((sgdb) -> {
            records.add(sgdb.getComando());
        });

        try {

            final Context init = new InitialContext();
            dataSource = (DataSource) init.lookup("java:jboss/datasources/PostgreSQLDS");
            conn.createStatement().execute(" SET search_path to information_schema ");
            ResultSet rs = conn.createStatement().executeQuery("select schema_name from information_schema.schemata where schema_name like 'tenant%'");

            while (rs.next()) {
                String schema = rs.getString("schema_name");
                conn = dataSource.getConnection();
                conn.createStatement().execute(" CREATE SCHEMA " + schema + " AUTHORIZATION postgres; ");
                conn.createStatement().execute(" SET search_path to " + schema);

                for (String ddlLine : records) {
                    conn.createStatement().execute(ddlLine);
                }
            }

        } catch (NamingException | SQLException ex) {
            Logger.getLogger(SgdbDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new DemoiselleSecurityException("Erro ao registrar Livraria", Response.Status.PRECONDITION_FAILED.getStatusCode());
        } finally {
            try {
                if (conn != null && !conn.isClosed()) {
                    conn.createStatement().execute(" SET search_path to public ");
                    conn.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(SgdbDAO.class.getName()).log(Level.SEVERE, null, ex);
                throw new DemoiselleSecurityException("Erro ao registrar Livraria", Response.Status.PRECONDITION_FAILED.getStatusCode());
            }
        }
        return true;
    }

}
