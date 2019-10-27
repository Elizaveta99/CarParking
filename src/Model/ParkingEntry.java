package Model;

import java.util.ArrayList;
import java.util.List;

/**
 * Class represents entry to the parking limited by 3 stripes
 */
public class ParkingEntry // or static ??
{
    // можно сделать через Semaphore ??
    private List<Car> entryCars;
    private static final int maxCarEntry = 3;
    private static final int minCarEntry = 0;
    private int carsCounter = 0;

    public ParkingEntry() {
        entryCars = new ArrayList<>();
    }

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
                WorkInfo.setInfo("Car " + element.getName() + "can't enter the parking");
                wait();
                return false;
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return true;
    }

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

            WorkInfo.setInfo("There are no cars in the entrance");
            wait();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }
}
