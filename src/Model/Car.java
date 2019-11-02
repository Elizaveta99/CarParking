package Model;

/**
 * Class-instance of car
 * @author Elizaveta Rudko
 */
public class Car
{
    /**
     * Field - name of the car
     */
    private String name;

    public Car (String name)
    {
        this.name = name;
    }


    /**
     * @return name of the Car
     */
    public synchronized String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
