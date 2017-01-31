/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.service;

import io.swagger.annotations.Api;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import javax.ws.rs.core.Response;

@Api("Teste")
@Path("teste")
@Produces(APPLICATION_JSON)
@Consumes(APPLICATION_JSON)
public class TesteREST {

    @GET
    public Response geraDados() {
        return Response.ok("{\"token\":\"" + "Processado" + "\"}").build();
    }

}
