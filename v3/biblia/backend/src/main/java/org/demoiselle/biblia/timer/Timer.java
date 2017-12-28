package org.demoiselle.biblia.timer;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import static java.util.logging.Logger.getLogger;
import javax.ejb.Schedule;
import javax.ejb.Stateless;
import javax.transaction.Transactional;
import javax.inject.Inject;
import org.demoiselle.biblia.bc.FingerprintBC;
import org.demoiselle.biblia.bc.VersiculoBC;
import org.demoiselle.biblia.cloud.CloudSender;
import org.demoiselle.biblia.entity.Fingerprint;
import org.demoiselle.biblia.entity.Versiculo;
import org.demoiselle.jee.core.lifecycle.annotation.Startup;

/**
 *
 * @author SERPRO
 */
@Stateless
public class Timer {

    private static final Logger LOG = getLogger(Timer.class.getName());

    @Inject
    private FingerprintBC fingerprintBC;

    @Inject
    private VersiculoBC versiculoBC;

    @Inject
    private CloudSender sender;

    /**
     *
     */
    @Transactional
    @Schedule(second = "0", minute = "0", hour = "*/1", persistent = false)
    public void atSchedule1h() {
        List<Fingerprint> fps = (List<Fingerprint>) fingerprintBC.find().getContent();

        fps.stream().filter((fp) -> (fp.getCodigo().contains("send"))).map((fp) -> {
            sender.send(fp.getCodigo().split("send/")[1], "Sistema atualizado");
            return fp;
        }).forEachOrdered((fp) -> {
            fingerprintBC.remove(fp.getId());
            LOG.info(fp.getUsuario() + " apagado ");
        });
    }

    /**
     *
     */
    @Transactional
    @Schedule(second = "0", minute = "*/1", hour = "*", persistent = false)
    public void atSchedule1m() {
        //LOG.info("atSchedule1m");
    }

    /**
     *
     */
    @Transactional
    @Schedule(second = "3", minute = "3", hour = "3", persistent = false)
    public void atScheduleOneInDay() {
        versiculoBC.reindex();
    }

    @Startup
    @Transactional
    public void load() {
        try {
            if (versiculoBC.find().getContent().isEmpty()) {
                // arquivo pode ser baixado em www.demoiselle.org/biblia.zip
                JsonReader jr = new JsonReader(new FileReader("/opt/biblia.json"));
                Versiculo[] vers = new Gson().fromJson(jr, Versiculo[].class);
                for (Versiculo ver : vers) {
                    versiculoBC.persist(ver);
                }
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(VersiculoBC.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
