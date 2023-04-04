/*
 * West_village.java
 *
 */

import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;

public class West_village extends Thread
{
   private Semaphore road;
   private Lock westMutex;
   private int priority;
   private RoadController roadController;
   private String[] activities = {"playing cards", "reading JJK", "eating donuts", "drinking wine"};
   
   public West_village(RoadController roadController, String name){
      this.roadController = roadController;
      this.road =  roadController.getRoad();
      this.westMutex = roadController.getEastMutex();
      this.priority = roadController.getCurrentPriority();
      this.setName(name);
   }

   public String getAction(String[] activities){
      Random rand = new Random();
      int index = rand.nextInt(activities.length);
      return activities[index];
   }

   @Override
   public void run()
   {
      Random rand = new Random();
      try {
         Thread.sleep(rand.nextInt(1000));
         westMutex.lock();
         synchronized(RoadController.class){
            if (priority == 1){
               roadController.setCurrentPriority(0);
            }
         }
         try{
            road.acquire();
            System.out.println(getName() + " is traveling on the road.");
            Thread.sleep(rand.nextInt(1000));
            System.out.println(getName() + " is " + getAction(activities) + ".");
            Thread.sleep(rand.nextInt(1000));
            System.out.println(getName() + " has finished the exchange.");
         }finally{
            road.release();
            westMutex.unlock();
         }
      }catch (InterruptedException e) {
         System.out.println("Thread interrupted");
      }
   }
}
