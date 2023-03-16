/*
 * Philosopher.java
 *
 * This class represents each philosopher thread.
 * Philosophers alternate between eating and thinking.
 */

// Needed to mnake the sleep time random from 1s to 3s
import java.util.Random;

public class Philosopher implements Runnable
{
    private int philNumber;
    private DiningServer dServer;
    private Random rand;
    
    public Philosopher(int philNumber, DiningServer server)
    {
        this.philNumber = philNumber;
        this.dServer = server;
        this.rand = new Random();
    }
    
    public void run()
    {
        try{
            while (true){
                // Take forks
                dServer.takeForks(philNumber);
                System.out.println("Forks are with Philosopher #" + philNumber);

                // Eating, then sleep
                eat();

                // Return forks
                dServer.returnForks(philNumber);

                // Thinking, then sleep
                think();
            }
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    private void think() throws InterruptedException
    {
        int sleepTime = rand.nextInt(2000) + 1000;
        System.out.println("Philosopher #" + philNumber + " took " + sleepTime + "ms thinking");
        Thread.sleep(sleepTime);
    }

    private void eat() throws InterruptedException
    {
        int sleepTime = rand.nextInt(2000) + 1000;
        System.out.println("Philosopher #" + philNumber + " took " + sleepTime + "ms eating");
        Thread.sleep(sleepTime);
    }
}
