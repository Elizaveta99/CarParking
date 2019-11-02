package Model;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Class for writing output
 * @author Elizaveta Rudko
 */
public class WorkInfo
{
    static final Logger rootLogger = LogManager.getRootLogger();
    static final Logger workInfoLogger = LogManager.getLogger(WorkInfo.class);

    /**
     * @param info - info about carParking work
     */
    public static synchronized void setInfo(String info){
        workInfoLogger.info(info);
    }

    /**
     * @param info - info about errors (exceptions)
     */
    public static synchronized void setError(String info){
        workInfoLogger.error(info);
    }
}
