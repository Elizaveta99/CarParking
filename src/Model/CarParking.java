package Model;

import java.util.ArrayList;
import java.util.List;

public class CarParking
{
    private int carPlacesAmount;
    private List<CarPlace> carPlaces;
    //ClientQueue clientQueue;

    /**
     * Constructor to create car parking
     * @param carPlacesAmount the number of operators in call centre
     */
    public CarParking(int carPlacesAmount)
    {
        this.carPlacesAmount = carPlacesAmount;
        this.carPlaces = new ArrayList<>();
        //this.clientQueue = new ClientQueue();
        for (int i = 0; i < carPlacesAmount; ++i)
            carPlaces.add(new CarPlace(i));
    }

    /**
     * Car parking openes
     */
     public void open()
     {
        for (Thread place: carPlaces)
            place.start();
     }

        /**
         * Try to stop work of call centre
         * @return true if no one client wait for operator
         */
        /*public synchronized boolean stopWork(){
        if (!clientQueue.isEmpty())
            return false;
        stopWorkAnyway();
        return true;
    }*/

        /**
         * Force stop of call centre
         */
        /*public synchronized void stopWorkAnyway(){
        for (Operator operator: operators)
            operator.stopWork();
        clientQueue.closeQueue();
    }*/

        /**
         * Car takes place
         * @param car
         * @return false
         */
        public synchronized boolean takePlace(Car car) // ???
        {
            //return clientQueue.call(new Client(name, complexity));
            return true; //??
        }

        /**
         * Leave queue of call centre
         * @param name of client
         * @return false f client not in queue
         */
        /*public synchronized boolean stopWaiting(String name)
        {
            return clientQueue.leaveQueue(name);
        }*/
}
