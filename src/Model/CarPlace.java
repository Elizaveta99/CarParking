package Model;

public class CarPlace extends Thread
{
    private Car car;
    private int placeNumber;

    public CarPlace(/*Car car*/ int placeNumber)
    {
        //this.car = car;
        this.placeNumber = placeNumber;
    }


    /**
     * Method represents that car takes this place
     */
    @Override
    public void run()
    {
        WorkInfo.setInfo("Car " + car.getName() + "took place # " + this.placeNumber);
        try {
            Thread.sleep(2000); // ???
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        WorkInfo.setInfo("Car " + car.getName() + "left place # " + this.placeNumber);
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
