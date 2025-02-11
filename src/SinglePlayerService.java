public class SinglePlayerService {
    private int[][] shipPlacements;
    private SinglePlayerController singlePlayerController;

    public SinglePlayerService(int[][] shipPlacements, SinglePlayerController singlePlayerController) {
        this.shipPlacements = shipPlacements;
        this.singlePlayerController = singlePlayerController;

    }
}
