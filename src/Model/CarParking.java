package Model;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Exchanger;

public class CarParking
{
    private int carPlacesAmount;
    private ParkingEntry entry;
    private List<CarPlace> carPlaces;
    private static Exchanger exchanger;
    private static Car exchCar;
    private static int exchPlace;

    /**
     * Constructor to create car parking
     * @param carPlacesAmount the number of operators in call centre
     */
    public CarParking(int carPlacesAmount, ParkingEntry entry)
    {
        this.carPlacesAmount = carPlacesAmount;
        this.entry = entry;
        this.carPlaces = new ArrayList<>();
    }

    public static synchronized int getExchPlace() {
        return exchPlace;
    }

    public static synchronized void setExchPlace(int _exchPlace) {
        exchPlace = _exchPlace;
    }

    /**
     * Car parking opens
     */
     public void open()
     {
         exchanger = new Exchanger();
         for (int i = 0; i < this.carPlacesAmount; i++)
         {
             CarPlace place = new CarPlace(i + 1, entry, exchanger);
             carPlaces.add(place);
             place.start();
         }
     }

     public void close()
     {
         for (CarPlace place: carPlaces)
             place.stopWork();
     }

    public static synchronized Car getExchCar() {
        return exchCar;
    }

    public static synchronized void setExchCar(Car _exchCar) {
        exchCar = _exchCar;
    }
}
