/*
 * East_village.java
 *
 */

import java.util.Random;

public class East_village extends Thread
{
   private RoadController roadController;

   public East_village(RoadController roadController){
      this.roadController = roadController;
   }

   public void run()
   {
      Random rand = new Random();
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
