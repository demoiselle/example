/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.service;

import app.security.RestMessage;
import app.socket.push.PushEndpoint;
import app.socket.push.PushMessage;
import com.google.gson.Gson;
import io.swagger.annotations.Api;
import javax.ejb.Asynchronous;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import javax.ws.rs.core.Response;

/**
 *
 * @author 70744416353
 */
@Api("Push")
@Path("push")
@Produces(APPLICATION_JSON)
@Consumes(APPLICATION_JSON)
public class PushREST {

    @Inject
    private PushEndpoint pe;

    @POST
    @Asynchronous
    public void sendMenssage(@Suspended final AsyncResponse asyncResponse, RestMessage restMessage) {
        asyncResponse.resume(sendMessage(restMessage));
    }

    private Response sendMessage(RestMessage restMessage) {
        if (restMessage.getRecipient() != null && !restMessage.getRecipient().isEmpty()) {
            PushMessage pm = new PushMessage(restMessage.getEvent(), restMessage.getMessage());
            pe.sendTo(new Gson().toJson(pm), restMessage.getRecipient());
            return Response.ok().entity("{\"message\":\"Mensagem enviada\"}").build();
        }
        return Response.ok().entity("{\"message\":\"Mensagem não foi enviada, verifique a documentação\"}").build();
    }

    @GET
    @Asynchronous
    @Path("{channel}")
    public void getList(@Suspended final AsyncResponse asyncResponse, @PathParam("channel") String channel) {
        asyncResponse.resume(getList(channel));
    }

    private Response getList(String channel) {
        return Response.ok().entity(pe.listUsers(channel)).build();
    }

    @GET
    @Asynchronous
    public void getList(@Suspended final AsyncResponse asyncResponse) {
        asyncResponse.resume(getList());
    }

    private Response getList() {
        return Response.ok().entity(pe.listChannels()).build();
    }
}
