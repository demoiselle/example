package org.demoiselle.livraria.dao;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.sql.DataSource;
import javax.transaction.Transactional;
import javax.ws.rs.core.Response;
import static javax.ws.rs.core.Response.Status.UNAUTHORIZED;
import org.demoiselle.jee.security.exception.DemoiselleSecurityException;
import static org.hibernate.annotations.common.util.impl.LoggerFactory.logger;

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

        try {
            // Create Schema
            final Context init = new InitialContext();
            dataSource = (DataSource) init.lookup("java:jboss/datasources/PostgreSQLDS");

            conn = dataSource.getConnection();

            // Create schema
            conn.createStatement().execute(" CREATE SCHEMA " + schema + " AUTHORIZATION postgres; ");

            // Set USE database
            conn.createStatement().execute(" SET search_path to " + schema);

            // Run o DDL - CREATE
            createDatabase(conn);

        } catch (NamingException | SQLException | IOException ex) {
            Logger.getLogger(SgdbDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new DemoiselleSecurityException("Erro ao registrar Livraria", Response.Status.PRECONDITION_FAILED.getStatusCode());
        } finally {
            try {
                // Closes the connection
                if (conn != null && !conn.isClosed()) {
                    // Set master database
                    conn.createStatement().execute(" SET search_path to public");
                    // Close connection
                    conn.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(SgdbDAO.class.getName()).log(Level.SEVERE, null, ex);
                throw new DemoiselleSecurityException("Erro ao registrar Livraria", Response.Status.PRECONDITION_FAILED.getStatusCode());
            }
        }
        return true;
    }

    private void createDatabase(Connection conn) throws SQLException, IOException {
        List<String> ddl = getDDLString(System.getProperty("jboss.server.data.dir") + "/database.sql");
        for (String ddlLine : ddl) {
            conn.createStatement().execute(ddlLine);
        }
    }

    private List<String> getDDLString(String filename) throws IOException {
        List<String> records = new ArrayList<String>();

        FileReader f = new FileReader(filename);
        BufferedReader reader = new BufferedReader(f);
        String line;
        while ((line = reader.readLine()) != null) {
            records.add(line + ";");
        }
        reader.close();
        return records;
    }

}
