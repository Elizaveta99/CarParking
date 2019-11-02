package Model;

import java.util.concurrent.Exchanger;

import static java.lang.StrictMath.abs;

/**
 * Class for Car place, each car place is thread
 */
public class CarPlace extends Thread
{
    /**
     * Fileds - car, cars from entry, place number, flag for thread to work, object to exchange
     */
    private Car car;
    private ParkingEntry entry;
    private int placeNumber;
    private boolean isWorking;
    private Exchanger exchanger;

    public CarPlace(int placeNumber, ParkingEntry entry, Exchanger exchanger)
    {
        this.placeNumber = placeNumber;
        this.isWorking = true;
        this.entry = entry;
        this.exchanger = exchanger;
    }

    public synchronized void stopWork()
    {
        isWorking = false;
    }

    /**
     * Method represents that car takes this place
     * Cars on the near places can exchange by places
     */
    @Override
    public void run()
    {
        while (isWorking)
        {
                Car tempcar = entry.get();
                boolean flag = false;
                if (tempcar != null)
                {
                    setCar(tempcar);
                    Car forLeft = this.car;
                    WorkInfo.setInfo("Car " + this.car.getName() + " took place # " + this.placeNumber);
                    if (this.placeNumber % 2 != 0)
                    {
                        CarParking.setExchPlace(this.placeNumber);
                        CarParking.setExchCar(tempcar);
                        try {
                            WorkInfo.setInfo("I'm ready to change! PlaceNumber " + this.placeNumber + " Car" + tempcar.getName());
                            Car exchCar = (Car) exchanger.exchange(CarParking.getExchCar());
                            setCar(exchCar);
                        } catch (InterruptedException e)
                        {
                            WorkInfo.setError(e.getMessage());
                            //e.printStackTrace();
                        }
                    }
                    else {
                        int settedExchPlace = CarParking.getExchPlace();
                        if (abs(this.placeNumber - settedExchPlace) == 1)
                        {
                            WorkInfo.setInfo("Want to exchange place " + this.placeNumber + " and settedPlace " + settedExchPlace);
                            try {
                                WorkInfo.setInfo("Car " + this.car.getName() + " on place # " + this.placeNumber + " exchanged places with Car on place # " + settedExchPlace);
                                Car exchCar = (Car)exchanger.exchange(tempcar);
                                setCar(exchCar);
                            } catch (InterruptedException e)
                            {
                                WorkInfo.setError(e.getMessage());
                                //e.printStackTrace();
                            }
                        }
                    }
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        WorkInfo.setError(e.getMessage());
                        //e.printStackTrace();
                    }
                    WorkInfo.setInfo("Car " + this.car.getName() + " left place # " + this.placeNumber);
                }
        }
        WorkInfo.setInfo("CarPlace " + this.placeNumber + " stopped working");
    }

    /**
     * @param car - current car on this place
     */
    public synchronized void setCar(Car car)
    {
        this.car = car;
    }

    public synchronized int getPlaceNumber()
    {
        return placeNumber;
    }
}
