/*
 * East_village.java
 *
 */

import java.util.Random;
import java.util.concurrent.Semaphore;

public class East_village extends Thread
{
   private Semaphore eastSemaphore;
   private Semaphore westSemaphore;
   private Random rand;
   
   public East_village(Semaphore eastSemaphore, Semaphore westSemaphore){
      this.eastSemaphore = eastSemaphore;
      this.westSemaphore = westSemaphore;
      rand = new Random();
   }

   @Override
   public void run()
   {
      try {
         while (true) {
             // wait for a random amount of time before attempting to cross the road
             Thread.sleep(rand.nextInt(5000));
             
             // acquire the eastSemaphore to enter the road
             eastSemaphore.acquire();
             System.out.println("A person from Eastvillage is crossing the road...");
             
             // wait for a random amount of time while on the road
             Thread.sleep(rand.nextInt(5000));
             System.out.println("The person from Eastvillage has finished crossing the road and is eating a donut.");
             
             // release the eastSemaphore to exit the road
             eastSemaphore.release();
         }
     } catch (InterruptedException e) {
         System.out.println("Thread interrupted");
     }
   }
}
