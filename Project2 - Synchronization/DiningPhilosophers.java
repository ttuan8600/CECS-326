/*
 * DiningPhilosophers.java
 *
 * This program starts the dining philosophers problem.
 * 
 */

public class DiningPhilosophers
{  
  public static void main(String args[])
  {  
    // Create 5 philosophers 
    int numPhil = 5;
    DiningServer server = new DiningServerImpl(numPhil);
    Philosopher[] philosophers = new Philosopher[numPhil];
    
    // Have each of them run as a seperate thread
    for (int i = 0; i < numPhil; i++)
    {
      philosophers[i] = new Philosopher(i, server);
      new Thread(philosophers[i]).start();
    }
  }
}
