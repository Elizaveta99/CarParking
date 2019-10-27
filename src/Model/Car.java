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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
