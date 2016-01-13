/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estacionamento.service;

import br.gov.frameworkdemoiselle.NotFoundException;
import br.gov.frameworkdemoiselle.security.LoggedIn;
import br.gov.frameworkdemoiselle.security.RequiredRole;
import br.gov.frameworkdemoiselle.transaction.Transactional;
import estacionamento.business.EstacionamentoBC;
import estacionamento.entity.Estacionamento;
import estacionamento.entity.Fabricante;
import estacionamento.security.Roles;
import estacionamento.util.Util;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import io.swagger.annotations.AuthorizationScope;
import java.io.Serializable;
import java.util.List;
import java.util.ListIterator;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import javax.ws.rs.core.Response;

/**
 *
 * @author 70744416353
 */
@Api(authorizations = {
    @Authorization(value = "JWT",
                   scopes = {
                       @AuthorizationScope(scope = "read:events", description = "Ler entidades"),
                       @AuthorizationScope(scope = "write:events", description = "Escrever entidades")
                   })
})
@Path("estacionamento")
@Produces(APPLICATION_JSON)
@Consumes(APPLICATION_JSON)
public class EstacionamentoREST implements Serializable {

    private static final Logger LOG = Logger.getLogger(EstacionamentoREST.class.getName());

    @Inject
    private EstacionamentoBC dao;

    /**
     *
     * @param field
     * @param order
     * @param init
     * @param qtde
     * @return
     * @throws NotFoundException
     */
    @GET
    @Path("list/{field}/{order}/{init}/{qtde}")
    @Transactional
    @LoggedIn
    @ApiOperation(value = "Lista com paginação no servidor",
                  notes = "Informe o campo/ordem(asc/desc)/posição do primeiro registro/quantidade de registros",
                  response = Estacionamento.class
    )
    public Response list(@PathParam("field") String field, @PathParam("order") String order, @PathParam("init") int init, @PathParam("qtde") int qtde) throws NotFoundException {
        if ((order.equalsIgnoreCase("asc") || order.equalsIgnoreCase("desc")) && (Util.fieldInClass(field, Estacionamento.class))) {
            return Response.ok().entity(dao.list(field, order, init, qtde)).build();
        }
        return Response.ok().entity(null).build();
    }

    /**
     *
     * @return @throws NotFoundException
     */
    @GET
    @Path("count")
    @Transactional
    @LoggedIn
    @ApiOperation(value = "Quantidade de registro",
                  notes = "Usado para trabalhar as tabelas com paginação no servidor",
                  response = Integer.class
    )
    public Response count() throws NotFoundException {
        return Response.ok().entity(dao.count()).build();
    }

    /**
     * Removes a instance from delegate.
     *
     * @param id Entity with the given identifier
     */
    @DELETE
    @Path("{id}")
    @Transactional
    @LoggedIn
    @ApiOperation(value = "Remove entidade",
                  response = Estacionamento.class,
                  authorizations = {
                      @Authorization(value = "JWT",
                                     scopes = {
                                         @AuthorizationScope(scope = "read:events", description = "Read your events")
                                     })
                  }
    )
    public void delete(@PathParam("id") final Long id) {
        dao.delete(id);
    }

    /**
     * Removes a list of instances from delegate.
     *
     * @param ids List of entities identifiers
     */
    @DELETE
    @Path("{ids}")
    @Transactional
    @LoggedIn
    @ApiOperation(value = "Remove várias entidades a partir de um lista de IDs",
                  response = Estacionamento.class,
                  authorizations = {
                      @Authorization(value = "JWT",
                                     scopes = {
                                         @AuthorizationScope(scope = "read:events", description = "Read your events")
                                     })
                  }
    )

    public void delete(@PathParam("ids") final List<Long> ids) {
        ListIterator<Long> iter = ids.listIterator();

        while (iter.hasNext()) {
            this.delete(iter.next());
        }
    }

    /**
     * Gets the results from delegate.
     *
     * @return The list of matched query results.
     */
    @GET
    @ApiOperation(value = "Lista de todos os registros", response = Estacionamento.class)
    public Response findAll() {
        return Response.ok().entity(dao.findAll()).build();
    }

    /**
     * Delegates the insert operation of the given instance.
     *
     * @param bean A entity to be inserted by the delegate
     */
    @POST
    @Transactional
    @LoggedIn
    @ApiOperation(value = "Insere entidade no banco",
                  response = Estacionamento.class,
                  authorizations = {
                      @Authorization(value = "JWT",
                                     scopes = {
                                         @AuthorizationScope(scope = "read:events", description = "Read your events")
                                     })
                  }
    )
    public Response insert(final Estacionamento bean) {
        return Response.ok().entity(dao.insert(bean)).build();
    }

    /**
     * Returns the instance of the given entity with the given identifier
     *
     * @return The instance
     */
    @GET
    @Path("{id}")
    @Transactional
    @LoggedIn
    @ApiOperation(value = "Busca entidade a partir do ID",
                  response = Estacionamento.class,
                  authorizations = {
                      @Authorization(value = "JWT",
                                     scopes = {
                                         @AuthorizationScope(scope = "read:events", description = "Read your events")
                                     })
                  }
    )
    public Response load(@PathParam("id") final Long id) {
        return Response.ok().entity(dao.load(id)).build();
    }

    /**
     * Delegates the update operation of the given instance.
     *
     * @param bean The instance containing the updated state.
     */
    @PUT
    @Transactional
    @LoggedIn
    @ApiOperation(value = "Atualiza a entidade",
                  response = Estacionamento.class,
                  authorizations = {
                      @Authorization(value = "JWT",
                                     scopes = {
                                         @AuthorizationScope(scope = "read:events", description = "Read your events")
                                     })
                  }
    )
    public Response update(final Estacionamento bean) {
        return Response.ok().entity(dao.update(bean)).build();
    }

}
