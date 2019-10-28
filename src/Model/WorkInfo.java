package Model;

/**
 * Class for writing output
 * @author Elizaveta Rudko
 */
public class WorkInfo
{
    /**
     * Write info to stdout
     * @param info
     */
    public static synchronized void setInfo(String info){
        System.out.println(info);
        System.out.flush();
    }
}
