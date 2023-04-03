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
   private Semaphore road = new Semaphore(1);
   private Lock eastVillageMutex = new ReentrantLock();
   private Lock westVillageMutex = new ReentrantLock();
   private int currentPriority = 0; // 0: East_village, 1: West_village

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
      // Acquire the semaphores
      westSemaphore.acquire();
      eastSemaphore.acquire();
   }

   public void releaseAccessFromWest(){
      // Release the semaphores
      westSemaphore.release();
      eastSemaphore.release();
   }
}
