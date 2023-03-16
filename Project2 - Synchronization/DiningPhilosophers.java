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
    int philNum = 5;
    DiningServer server = new DiningServerImpl(philNum);
    Philosopher[] philosophers = new Philosopher[philNum];
    
    // Have each of them run as a seperate thread
    for (int i = 0; i < 5; i++)
    {
      philosophers[i] = new Philosopher(i, server);
      new Thread(philosophers[i]).start();
    }
  }
}
