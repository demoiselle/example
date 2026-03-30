package app.service;

import app.security.RestMessage;
import app.socket.push.PushEndpoint;
import app.socket.push.PushMessage;
import com.google.gson.Gson;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import static jakarta.ws.rs.core.MediaType.APPLICATION_JSON;
import jakarta.ws.rs.core.Response;
import org.demoiselle.jee.security.annotation.Authenticated;

/**
 *
 * @author PauloGladson
 */
@Path("push")
@Authenticated
@Produces(APPLICATION_JSON)
@Consumes(APPLICATION_JSON)
public class PushREST {

    @Inject
    private PushEndpoint pe;

    @POST
    @Authenticated(enable = false)
    public Response sendMenssage(RestMessage restMessage) {
        if (restMessage.getRecipient() != null && !restMessage.getRecipient().isEmpty()) {
            PushMessage pm = new PushMessage(restMessage.getEvent(), restMessage.getMessage());
            pe.sendTo(new Gson().toJson(pm), restMessage.getRecipient());
            return Response.ok().entity("{\"message\":\"Mensagem enviada - Use sempre UUID nos seus canais\"}").build();
        }
        return Response.ok().entity("{\"message\":\"Mensagem não foi enviada, verifique a documentação\"}").build();
    }

    @GET
    @Path("{channel}")
    public Response getList(@PathParam("channel") String channel) {
        return Response.ok().entity(pe.listUsers(channel)).build();
    }

    @GET
    @Path("{channel}/count")
    public Response getContChannel(@PathParam("channel") String channel) {
        return Response.ok().entity(pe.count(channel)).build();
    }

    @GET
    @Path("count")
    @Authenticated(enable = false)
    public Response getCount() {
        return Response.ok().entity(pe.count()).build();
    }

    @GET
    public Response getList() {
        return Response.ok().entity("").build();
    }
}
