/*
 * Demoiselle Framework
 *
 * License: GNU Lesser General Public License (LGPL), version 3 or later.
 * See the lgpl.txt file in the root directory or <https://www.gnu.org/licenses/lgpl.html>.
 */
package org.demoiselle.livraria.core;

import java.sql.Connection;
import java.sql.SQLException;

import javax.enterprise.context.RequestScoped;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.hibernate.engine.jdbc.connections.spi.MultiTenantConnectionProvider;
import org.hibernate.service.spi.ServiceRegistryAwareService;
import org.hibernate.service.spi.ServiceRegistryImplementor;

/**
 * Implementation of @MultiTenantConnectionProvider in Hibernate.
 *
 * @author SERPRO
 *
 */
@RequestScoped
public class MultiTenantProvider implements MultiTenantConnectionProvider, ServiceRegistryAwareService {

    private static final long serialVersionUID = 1L;
    private DataSource dataSource;

    @Override
    public boolean supportsAggressiveRelease() {
        return false;
    }

    /**
     * Instance Datasource for manipulate Tenants on Server Startup
     *
     * @param serviceRegistry
     */
    @Override
    public void injectServices(ServiceRegistryImplementor serviceRegistry) {
        try {
            final Context init = new InitialContext();
            dataSource = (DataSource) init
                    .lookup("java:jboss/datasources/TenantDS");// configuration.getMultiTenancyTenantsDatabaseDatasource());
        } catch (final NamingException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("rawtypes")
    @Override
    public boolean isUnwrappableAs(Class clazz) {
        return false;
    }

    @Override
    public <T> T unwrap(Class<T> clazz) {
        return null;
    }

    @Override
    public Connection getAnyConnection() throws SQLException {
        final Connection connection = dataSource.getConnection();
        return connection;
    }

    /**
     * Get connection for Tenant using configurations.
     *
     * @param tenantIdentifier
     * @return
     * @throws java.sql.SQLException
     */
    @Override
    public Connection getConnection(String tenantIdentifier) throws SQLException {
        final Connection connection = getAnyConnection();
        try {
            String setDatabase = " SET search_path to ";
            connection.createStatement().execute(setDatabase + " " + tenantIdentifier);
        } catch (final SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    @Override
    public void releaseAnyConnection(Connection connection) throws SQLException {
        connection.close();
    }

    @Override
    public void releaseConnection(String tenantIdentifier, Connection connection) throws SQLException {
        connection.createStatement().execute("SET search_path to public");
    }

}
