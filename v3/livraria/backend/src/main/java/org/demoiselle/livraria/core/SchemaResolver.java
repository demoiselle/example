package org.demoiselle.livraria.core;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.spi.CDI;
import org.demoiselle.jee.core.api.security.DemoiselleUser;
import org.demoiselle.jee.core.api.security.SecurityContext;

import org.hibernate.context.spi.CurrentTenantIdentifierResolver;

/**
 * This class get the Tenant in MultiTenantContext for hibernate uses,
 * implementation of @CurrentTenantIdentifierResolver in Hibernate.
 *
 * @author SERPRO
 *
 */
public class SchemaResolver implements CurrentTenantIdentifierResolver {

    @Override
    public String resolveCurrentTenantIdentifier() {
        DemoiselleUser user = CDI.current().select(SecurityContext.class).get().getUser();
        return (user == null || user.getParams("tenant") == null || user.getParams("tenant").isEmpty()) ? "public" : user.getParams("tenant");
    }

    @Override
    public boolean validateExistingCurrentSessions() {
        return false;
    }

}
