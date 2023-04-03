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
      while(true){
         try{
            // Sleep for random time representing traveling
            Thread.sleep(rand.nextInt(1000));
            // Request access to the road
            roadController.requestAccessFromEast();
            // Sleep for random time reprensts doing an activity
            Thread.sleep(rand.nextInt(1000));
            // Release access to the road
            roadController.releaseAccessFromEast();
         }catch(InterruptedException e){
            e.printStackTrace();
         }
      }
   }   
}
