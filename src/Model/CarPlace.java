package Model;

public class CarPlace extends Thread
{
    private Car car;
    private ParkingEntry entry;
    private int placeNumber;
    private boolean isWorking;

    public CarPlace(/*Car car*/ int placeNumber, ParkingEntry entry)
    {
        //this.car = car;
        this.placeNumber = placeNumber;
        this.isWorking = true;
        this.entry = entry;
    }

    public void stopWork()
    {
        isWorking = false;
    }

    /**
     * Method represents that car takes this place
     */
    @Override
    public void run()
    {
        while (isWorking)
        {
            try {
                setCar(entry.get());
                WorkInfo.setInfo("Car " + car.getName() + "took place # " + this.placeNumber);
                Thread.sleep(2000); // ???
                WorkInfo.setInfo("Car " + car.getName() + "left place # " + this.placeNumber);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        WorkInfo.setInfo("CarPlace " + this.placeNumber + " stopped working");
    }

    public void setCar(Car car)
    {
        this.car = car;
    }

    public int getPlaceNumber()
    {
        return placeNumber;
    }
}
