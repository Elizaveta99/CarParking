package View;

import Model.CarParking;
import Model.CarsGenerator;
import Model.ParkingEntry;
import Model.WorkInfo;

import java.util.Scanner;

public class Main
{
    final static int carPlaces = 20;
    final static int carsAmount = 10;
    public static void main(String[] args)
    {
        ParkingEntry entry = new ParkingEntry();
        CarsGenerator carsGenerator = new CarsGenerator(entry, carsAmount);
        carsGenerator.start();

        Scanner sc = new Scanner(System.in);
        //System.out.println("Amount of car places : ");
        //int carPlaces = Integer.parseInt(sc.nextLine());
        CarParking carParking = new CarParking(carPlaces, entry);
        System.out.println("Parking opened.\n\tNew car arrived from entry: 1 <Car name>"
                + "\n\tCar left parking: 2 <Car name>"
                + "\n\tParking closed: 3");
        carParking.open();
        //carParking.close(); // ??

        /*while (sc.hasNextLine())
        {
            String input = sc.nextLine();
            String[] type = input.split("\\s");
            try {
                switch (Integer.parseInt(type[0]))
                {
                    case 1:
                        if (carParking.takePlace(entry.get().getName()))
                            Informator.inform(params[1] + " awaits its turn");
                        else
                            Informator.inform(params[1] + " is already waiting for his turn");
                        break;
                    case 2:
                        if (carParking.stopWaiting(params[1]))
                            Informator.inform(params[1] + " disconnect by himself");
                        else
                            Informator.inform(params[1] + " does not wait for operator");
                        break;
                    case 3:
                        carParking.close();
                        break;
                    default:
                        throw new Exception("Incorrect input");

                }
            } catch (Exception e) {
                WorkInfo.setInfo("Incorrect params");
            }
        }*/
    }
}
