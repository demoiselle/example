/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.scheduler;

import app.socket.push.PushEndpoint;
import app.socket.push.PushMessage;
import com.google.gson.Gson;
import java.util.Date;
import javax.ejb.Schedule;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

/**
 *
 * @author 70744416353
 */
@ApplicationScoped
public class TimerMensagem {

    @Inject
    private PushEndpoint pe;

    @Schedule(minute = "*/3", second = "33", persistent = false)
    public void sendHello() {
        PushMessage mm = new PushMessage("echo", "Sistema de Push Demoiselle, https://github.com/demoiselle/example/tree/master/v3/push");
        pe.sendTo(new Gson().toJson(mm), "echo");
    }

    @Schedule(minute = "*/1", second = "00", persistent = false)
    public void sendHora() {
        PushMessage mm = new PushMessage("time", (new Date(System.nanoTime())).toString());
        pe.sendTo(new Gson().toJson(mm), "time");
    }

}
