package Model;

/**
 * Class that generates input cars
 */
public class CarsGenerator extends Thread
{
    private ParkingEntry entry;
    private int carsAmount;

    public CarsGenerator(ParkingEntry entry, int carsAmount)
    {
        this.entry = entry;
        this.carsAmount = carsAmount;
    }

    @Override
    public void run()
    {
        int count = 0;
        while (count < carsAmount)
        {
            Thread.currentThread().setName(" Generator cars");
            count++;
            entry.add(new Car("Car" + count));
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
