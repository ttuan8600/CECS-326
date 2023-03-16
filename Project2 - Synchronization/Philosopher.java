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
                // Think for a while, and take a fork
                think();
                dServer.takeForks(philNumber);
                // Eat for a while, then return a fork
                eat();
                dServer.returnForks(philNumber);
            }
        }catch (InterruptedException e){
            Thread.currentThread().interrupt();
            return;
        }
    }

    private void think() throws InterruptedException
    {
        int sleepTime = rand.nextInt(2000) + 1000;
        System.out.println("Philosopher #" + philNumber + " is thinking for " + sleepTime + "ms");
        Thread.sleep(sleepTime);
    }

    private void eat() throws InterruptedException
    {
        int sleepTime = rand.nextInt(2000) + 1000;
        System.out.println("Philosopher #" + philNumber + " is eating for " + sleepTime + "ms");
        Thread.sleep(sleepTime);
    }
}
