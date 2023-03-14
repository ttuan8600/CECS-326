/*
 * Philosopher.java
 *
 * This class represents each philosopher thread.
 * Philosophers alternate between eating and thinking.
 */

public class Philosopher implements Runnable
{
    private int philNumber;
    private DiningServer server;
    
    public Philosopher(int philNumber, DiningServer server)
    {
        this.philNumber = philNumber;
        this.server = server;
    }
    
    @Override
    public void run()
    {
        while (true)
        {
        try
        {
            Thread.sleep((int)(Math.random() * 1000));
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        
        System.out.println("Philosopher " + philNumber + " is thinking.");
        server.takeForks(philNumber);
        System.out.println("Philosopher " + philNumber + " is eating.");
        server.returnForks(philNumber);
        }
    }
}
