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

    for (int i = 0; i < 5; i++)
    {
      philosophers[i] = new Philosopher(i, server);
      new Thread(philosophers[i]).start();
    }
  }
}
