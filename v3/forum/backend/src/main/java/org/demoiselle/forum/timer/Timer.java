package org.demoiselle.forum.timer;

import java.util.logging.Logger;
import static java.util.logging.Logger.getLogger;
import javax.ejb.Schedule;
import javax.ejb.Stateless;
import javax.transaction.Transactional;

/**
 *
 * @author SERPRO
 */
@Stateless
public class Timer {

    private static final Logger LOG = getLogger(Timer.class.getName());

    /**
     *
     */
    @Transactional
    @Schedule(second = "0", minute = "0", hour = "*/1", persistent = false)
    public void atSchedule1h() {
        //LOG.info("atSchedule1h");
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
    @Schedule(second = "0", minute = "0", hour = "9", persistent = false)
    public void atScheduleOneInDay() {
       // LOG.info("atScheduleOneInDay");
    }

}
