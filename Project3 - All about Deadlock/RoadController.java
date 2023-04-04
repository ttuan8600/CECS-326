/*
 * RoadController.java
 *
 */

import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
 
public class RoadController
{  
   Random rand = new Random();
   private final Semaphore road;
   private Lock eastVillageLock;
   private Lock westVillageLock;
   private int currentPriority;

   public RoadController(){
      this.road = new Semaphore(1);
      this.eastVillageLock = new ReentrantLock();
      this.westVillageLock = new ReentrantLock();
      this.currentPriority = 0;  // 0: East_village, 1: West_village
   }

   public void getEastRoad() throws InterruptedException
   {  
      eastVillageMutex.lock();
      try{
         road.acquire();
         Thread.sleep(rand.nextInt(1000));
         road.release();
      }finally{
         eastVillageMutex.unlock();
      }
   }

   public void getWestRoad() throws InterruptedException
   {  
      westVillageMutex.lock();
      try{
         road.acquire();
         Thread.sleep(rand.nextInt(1000));
         road.release();
      }finally{
         westVillageMutex.unlock();
      }
   }

   public void setPrior(int priority){
      this.currentPriority = priority;
   }

   public int getPrior(){
      return this.currentPriority;
   }
}
