package SubDirectory;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZonedDateTime;
import java.util.logging.Logger;

public class TimeExample {
    private Logger logger = Logger.getLogger(TimeExample.class.getName());

    public void doAll() {
        logger.info("Start");

        LocalDate localDate = LocalDate.now();
        logger.info(localDate.toString());

        LocalTime localTime = LocalTime.now();
        logger.info(localTime.toString());

        LocalDateTime localDateTime = LocalDateTime.now();
        logger.info(localDateTime.toString());

        ZonedDateTime zonedDateTime = ZonedDateTime.now();
        logger.info(zonedDateTime.toString());
    }
}
