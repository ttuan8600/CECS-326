/*
 * West_village.java
 *
 */

import java.util.Random;

public class West_village 
{
   private RoadController roadController;

   public West_village(RoadController roadController){
      this.roadController = roadController;
   }
   
   public void run(){
      Random rand = new Random();
      while(true){
         try{
            // Sleep for random time representing traveling
            Thread.sleep(rand.nextInt(1000));
            // Request access to the road
            roadController.requestAccessFromWest();
            // Sleep for random time reprensting eating
            Thread.sleep(rand.nextInt(1000));
            // Release access to the road
            roadController.releaseAccessFromWest();
         }catch(InterruptedException e){
            e.printStackTrace();
         }
      }
   }
}
