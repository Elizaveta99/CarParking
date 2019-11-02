package Model;

import java.util.ArrayList;
import java.util.List;

/**
 * Class represents entry to the parking limited by 3 stripes
 */
public class ParkingEntry
{
    private List<Car> entryCars;
    private static final int maxCarEntry = 3;
    private static final int minCarEntry = 0;
    private int carsCounter = 0;

    public List<Car> getEntryCars()
    {
        return entryCars;
    }

    /**
     * Constructor - list of carsin entrance
     */
    public ParkingEntry() {
        entryCars = new ArrayList<>();
    }

    /**
     * @param element - car that arrives
     * @return - car arrived
     */
    public synchronized boolean add(Car element)
    {
        try {
            if (carsCounter < maxCarEntry)
            {
                notifyAll();
                entryCars.add(element);
                WorkInfo.setInfo("Car " + element.getName() + " enters the parking");
                carsCounter++;

            }
            else {
                WorkInfo.setInfo("Car " + element.getName() + " can't enter the parking");
                wait();
                return false;
            }

        } catch (InterruptedException e) {
            WorkInfo.setError(e.getMessage());
            //e.printStackTrace();
        }
        return true;
    }

    /**
     * @return - car from entrance
     */
    public synchronized Car get()
    {
        try {
            if (carsCounter > minCarEntry)
            {
                notifyAll();
                for (Car car : entryCars)
                {
                    carsCounter--;
                    WorkInfo.setInfo("Car " + car.getName() + " got to the parking from the entrance");
                    entryCars.remove(car);
                    return car;
                }
            }
            wait();

        } catch (InterruptedException e) {
            WorkInfo.setError(e.getMessage());
            //e.printStackTrace();
        }
        return null;
    }
}
