package org.demoiselle.cep.service;

import io.swagger.annotations.Api;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import org.demoiselle.cep.dao.Ip2LocationDb11DAO;

@Api("IP")
@Path("v1/ips")
public class Ip2LocationREST {

    @Inject
    private Ip2LocationDb11DAO v4dao;

    @GET
    @Path("v4/{ip}")
    public Response getInfoIpv4(@PathParam(value = "ip") final String ip) {
        return Response.ok().entity(v4dao.getInfoV4(ip)).build();
    }

}
