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
   
   public void setCurrentPriority(int priority){
      this.currentPriority = priority;
   }

   public int getCurrentPriority(){
      return currentPriority;
   }

   public Semaphore getRoad(){
      return road;
   }

   public Lock getEastMutex(){
      return eastVillageLock;
   }

   public Lock getWestMutex(){
      return westVillageLock;
   }
}
