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
   private final Lock eastVillageLock;
   private final Lock westVillageLock;
   private int currentPriority;

   public RoadController(){
      this.road = new Semaphore(1);
      this.eastVillageLock = new ReentrantLock();
      this.westVillageLock = new ReentrantLock();
      this.currentPriority = 0;  // 0: East_village, 1: West_village
   }

   public void getEastRoad(String villager) throws InterruptedException
   {  
      eastVillageLock.lock();

      // Check if the villager has the priority to use the road
      if (currentPriority != 0){
         eastVillageLock.unlock();
         westVillageLock.lock();
         westVillageLock.unlock();
         eastVillageLock.lock();
      }
      road.acquire();
      eastVillageLock.unlock();

      System.out.println(villager + " is traveling on the road.");
      Thread.sleep(rand.nextInt(5000));
      System.out.println(villager + " is playing cards.");
      Thread.sleep(rand.nextInt(5000));
      System.out.println(villager + " has finished the exchange.");

      road.release();
   }

   public void getWestRoad(String villager) throws InterruptedException
   {  
      westVillageLock.lock();
      if (currentPriority != 1){
         westVillageLock.unlock();
         eastVillageLock.lock();
         eastVillageLock.unlock();
         westVillageLock.lock();
      }

      road.acquire();
      westVillageLock.unlock();

      System.out.println(villager + " is traveling on the road.");
      Thread.sleep(rand.nextInt(5000));
      System.out.println(villager + " is playing cards.");
      Thread.sleep(rand.nextInt(5000));
      System.out.println(villager + " has finished the exchange.");

      road.release();
   }
   
}
