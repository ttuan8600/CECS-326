public class main {
    public static void main(String[] args) {
        RoadController roadController =  new RoadController();
        for (int i = 0; i < 5; i++){
            new East_village(roadController).start();
            new West_village(roadController).start();
        }
    }
}
