public class SinglePlayerService {
    private int[][] shipPlacements;
    private SinglePlayerController singlePlayerController;

    public SinglePlayerService(int[][] shipPlacements, SinglePlayerController singlePlayerController) {
        this.shipPlacements = shipPlacements;
        this.singlePlayerController = singlePlayerController;

    }

    public int[][] generateShipPlacements() {
        //TO ADD ENEMY GENERATION
        return new int[10][10];
    }
}
