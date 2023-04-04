/*
 * East_village.java
 *
 */

import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class East_village extends Thread
{
   private Semaphore road;
   private Lock eastMutex;
   private Lock westMutex;
   private int priority;
   
   public East_village(Semaphore road, Lock eastMutex, Lock westMutex, int priority){
      this.road =  road;
      this.eastMutex = eastMutex;
      this.westMutex = westMutex;
      this.priority = priority;
   }

   @Override
   public void run()
   {
      Random rand = new Random();
      try {
         Thread.sleep(rand.nextInt(1000));
         eastMutex.lock();
         synchronized(RoadController.class){
            if (priority == 1){
               RoadController.setCurrentPriority(2);
            }
         }
         try{
            road.acquire();
            System.out.println(getName() + " is traveling on the road.");
            Thread.sleep(rand.nextInt(1000));
            System.out.println(getName() + " is playing cards.");
            Thread.sleep(rand.nextInt(1000));
            System.out.println(getName() + " has finished the exchange.");
         }finally{
            road.release();
            eastMutex.unlock();
         }
      }catch (InterruptedException e) {
         System.out.println("Thread interrupted");
      }
   }
}
