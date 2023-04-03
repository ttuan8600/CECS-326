
import java.util.concurrent.Semaphore;;

public class Main {
    public static void main(String[] args) {
        Semaphore roadSemaphore = new Semaphore(1);
        Semaphore westMutex = new Semaphore(1);
        Semaphore eastMutex = new Semaphore(0);

        Thread eastVillage1 = new Thread(new East_village(roadSemaphore, eastMutex, westMutex, "East_village_1"));
        Thread eastVillage2 = new Thread(new EastVillage(roadSemaphore, eastMutex, westMutex, "East_village_2"));
        Thread eastVillage3 = new Thread(new EastVillage(roadSemaphore, eastMutex, westMutex, "East_village_3"));

        Thread westVillage1 = new Thread(new West_village(roadSemaphore, westMutex, eastMutex, "West_village_1"));
        Thread westVillage2 = new Thread(new WestVillage(roadSemaphore, westMutex, eastMutex, "West_village_2"));

        eastVillage1.start();
        eastVillage2.start();
        eastVillage3.start();
        westVillage1.start();
        westVillage2.start();
    }
}
