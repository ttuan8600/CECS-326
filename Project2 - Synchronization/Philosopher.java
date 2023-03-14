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
    
    public void run()
    {
        while (true)
        {
            // Think for a while
            try
            {
                Thread.sleep((int)(Math.random() * 1000));
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
            
            // Try to get forks
            server.takeForks(philNumber);
            
            // Eat for a while
            try
            {
                Thread.sleep((int)(Math.random() * 1000));
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
            
            // Return forks
            server.returnForks(philNumber);
        }
    }
}
