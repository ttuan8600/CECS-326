/*
 * RoadController.java
 *
 */

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
 
public class RoadController
{  
   private Semaphore road = new Semaphore(1);
   private Lock eastVillageMutex = new ReentrantLock();
   private Lock westVillageMutex = new ReentrantLock();
   private int currentPriority = 0; // 0: East_village, 1: West_village

   public void requestAccessFromEast() throws InterruptedException
   {  
      
      // Acquire the semaphores
      eastSemaphore.acquire();
      westSemaphore.acquire();
   }

   public void releaseAccessFromEast(){
      // Release the semaphores
      eastSemaphore.release();
      westSemaphore.release();
   }

   public void requestAccessFromWest() throws InterruptedException
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
