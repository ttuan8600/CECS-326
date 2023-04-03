/*
 * West_village.java
 *
 */


public class West_village implements Runnable
{
   private RoadController roadController;

   public West_village(RoadController roadController){
      this.roadController = roadController;
   }
   
   @Override
   public void run(){
      try{
         while(true){
            if (roadController.getPrior() == 1){
               roadController.getWestRoad();
            }else{
               Thread.sleep(1000);
            }
         }
      }catch(InterruptedException e){
         e.printStackTrace();
      }
   }
}
