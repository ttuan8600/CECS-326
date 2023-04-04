
// import java.util.concurrent.Semaphore;
// import java.util.concurrent.locks.Lock;
// import java.util.concurrent.locks.ReentrantLock;


public class Main {
    public static void main(String[] args) {
        RoadController roadController = new RoadController();

        West_village west_village1 = new West_village(roadController, "West_village_1");
        West_village west_village2 = new West_village(roadController, "West_village_2");
        West_village west_village3 = new West_village(roadController, "West_village_3");

        East_village east_village1 = new East_village(roadController, "East_village_1");
        East_village east_village2 = new East_village(roadController, "East_village_2");
        East_village east_village3 = new East_village(roadController, "East_village_3");


        west_village1.start();
        west_village2.start();
        west_village3.start();

        east_village1.start();
        east_village2.start();
        east_village3.start();
    }
}
