package org.demoiselle.cep.service;

import io.swagger.annotations.Api;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import javax.ws.rs.core.Response;
import org.demoiselle.cep.dao.Ip2LocationDb11DAO;
import org.demoiselle.jee.rest.annotation.CacheControl;

@Api("IP")
@Path("v1/ips")
@Produces(value = {APPLICATION_JSON})
public class Ip2LocationREST {

    @Inject
    private Ip2LocationDb11DAO v4dao;

    @GET
    @Path("v4/{ip}")
    @CacheControl(value = "max-age=3600, must-revalidate, public")
    public Response getInfoIpv4(@PathParam(value = "ip") final String ip) {
        return Response.ok().entity(v4dao.getInfoV4(ip)).build();
    }

}
