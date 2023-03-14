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
    DiningServer server = new DiningServerImpl();
    Philosopher[] philosophers = new Philosopher[5];
    // Create 5 philosophers and have each of them run as a seperate thread
    for (int i = 0; i < 5; i++)
    {
      philosophers[i] = new Philosopher(i, server);
      new Thread(philosophers[i]).start();
    }
  }
}
