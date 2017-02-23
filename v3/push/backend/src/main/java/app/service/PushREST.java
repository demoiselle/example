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
import java.util.logging.Logger;
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
import org.demoiselle.jee.security.annotation.Authenticated;

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
        PushMessage pm = new PushMessage(restMessage.getEvent(), restMessage.getMessage());

        if (restMessage.getType() != null && !restMessage.getType().isEmpty()) {
           
            if (restMessage.getType().equalsIgnoreCase("user")) {
               pe.sendToUser(restMessage.getMessage(), restMessage.getRecipient(), restMessage.getChannel());
            }

            if (restMessage.getType().equalsIgnoreCase("group")) {
               pe.sendToGroup(new Gson().toJson(pm), restMessage.getRecipient()); 
            }
            
            if (restMessage.getType().equalsIgnoreCase("*")) {
                pe.sendToChannel(new Gson().toJson(pm), restMessage.getChannel());
            }
            return Response.ok().entity("{\"message\":\"Mensagem enviada\"}").build();
            
        }

        return Response.ok().entity("{\"message\":\"Mensagem não foi enviada, verifique a documentação\"}").build();
    }

    @GET
    @Asynchronous
    @Authenticated
    @Path("{channel}")
    public void getList(@Suspended final AsyncResponse asyncResponse, @PathParam("channel") String channel) {
        asyncResponse.resume(getList(channel));
    }

    private Response getList(String channel) {
        return Response.ok().entity(pe.listaUsuarios(channel)).build();
    }

    @GET
    @Asynchronous
    @Authenticated
    public void getList(@Suspended final AsyncResponse asyncResponse) {
        asyncResponse.resume(getList());
    }

    private Response getList() {
        return Response.ok().entity(pe.listChannels()).build();
    }
}
