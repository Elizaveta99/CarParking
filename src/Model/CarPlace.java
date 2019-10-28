package Model;

import java.util.concurrent.Exchanger;

import static java.lang.StrictMath.abs;

public class CarPlace extends Thread
{
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
                        } catch (InterruptedException e)
                        {
                            e.printStackTrace();
                        }
                    }
                    else {
                        int settedExchPlace = CarParking.getExchPlace();
                        if (abs(this.placeNumber - settedExchPlace) == 1)
                        {
                            WorkInfo.setInfo("exchange " + this.placeNumber + " settedPlace " + settedExchPlace);
                            try {
                                Car exchCar = (Car)exchanger.exchange(CarParking.getExchCar());
                                WorkInfo.setInfo("Car " + this.car.getName() + " on place # " + this.placeNumber + " exchanged places with Car " + exchCar.getName() + " on place # " + settedExchPlace);

                                setCar(exchCar);
                                //CarParking.setExchPlace(this.placeNumber);
                                CarParking.setExchCar(exchCar);
                                flag = true;
                            } catch (InterruptedException e)
                            {
                                e.printStackTrace();
                            }
                        }
                    }

                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if (!flag)
                        WorkInfo.setInfo("Car " + forLeft.getName() + " left place # " + this.placeNumber);
                    else
                        WorkInfo.setInfo("Car " + this.car.getName() + " left place # " + this.placeNumber);
                }
        }
        WorkInfo.setInfo("CarPlace " + this.placeNumber + " stopped working");
    }

    public synchronized void setCar(Car car)
    {
        this.car = car;
    }

    public synchronized int getPlaceNumber()
    {
        return placeNumber;
    }
}
