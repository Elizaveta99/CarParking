package View;

import Model.CarParking;
import Model.CarsGenerator;
import Model.ParkingEntry;
import Model.WorkInfo;

import java.util.Scanner;

public class Main
{
    final static int carPlaces = 4;
    final static int carsAmount = 8;
    public static void main(String[] args) throws InterruptedException
    {
        WorkInfo.setInfo("Parking opened");
        ParkingEntry entry = new ParkingEntry();
        CarsGenerator carsGenerator = new CarsGenerator(entry, carsAmount);
        carsGenerator.start();

        CarParking carParking = new CarParking(carPlaces, entry);
        carParking.open();

        Thread.sleep(40000);
        carParking.close();
        WorkInfo.setInfo("Parking closed");
    }
}
