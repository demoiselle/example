package org.demoiselle.cep.service;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import static jakarta.ws.rs.core.MediaType.APPLICATION_JSON;
import jakarta.ws.rs.core.Response;
import org.demoiselle.cep.dao.Ip2LocationDb11DAO;
import org.demoiselle.jee.rest.annotation.CacheControl;

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
