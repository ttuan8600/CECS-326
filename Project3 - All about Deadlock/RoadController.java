/*
 * RoadController.java
 *
 */

 import java.util.concurrent.Semaphore;
 
public class RoadController
{  
   private Semaphore eastSemaphore = new Semaphore(1);
   private Semaphore westSemaphore = new Semaphore(1);

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
