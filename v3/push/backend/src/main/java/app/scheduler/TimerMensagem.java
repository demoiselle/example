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
import javax.inject.Inject;
import javax.inject.Singleton;

/**
 *
 * @author 70744416353
 */
@Singleton
public class TimerMensagem {

    @Inject
    private PushEndpoint pe;

    @Schedule(second = "30", minute = "*/3", hour = "*", persistent = false)
    public void sendHello() {
        PushMessage mm = new PushMessage("echo", "Sistema de Push Demoiselle, https://github.com/demoiselle/example/tree/master/v3/push");
        pe.sendTo(new Gson().toJson(mm), "echo");
    }

    @Schedule(second = "00", minute = "*/1", hour = "*", persistent = false)
    public void sendHora() {
        PushMessage mm = new PushMessage("time", (new Date(System.nanoTime())).toString());
        pe.sendTo(new Gson().toJson(mm), "time");
    }

}
