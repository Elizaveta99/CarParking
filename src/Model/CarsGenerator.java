package Model;

import java.util.List;

/**
 * Class that generates input cars
 */
public class CarsGenerator extends Thread
{
    private ParkingEntry entry;
    private int carsAmount;

    /**
     * @param entry - where cars arrive
     * @param carsAmount - amount of cars that arrive
     */
    public CarsGenerator(ParkingEntry entry, int carsAmount)
    {
        this.entry = entry;
        this.carsAmount = carsAmount;
    }

    /**
     * @return - cars from entrance
     */
    public synchronized ParkingEntry getEntry()
    {
        return this.entry;
    }

    @Override
    public void run()
    {
        int count = 0;
        while (count < carsAmount)
        {
            Thread.currentThread().setName(" Generator cars");
            count++;
            entry.add(new Car("Car_" + count));
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                WorkInfo.setError(e.getMessage());
                //e.printStackTrace();
            }
        }
    }
}
