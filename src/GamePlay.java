import Controller.CreateFilesController;
import Controller.GameController;
import Controller.GameException;

public class GamePlay {
    public static void main(String[] args) throws ClassNotFoundException {
        try {
            CreateFilesController createFilesController = new CreateFilesController();
          createFilesController.createFile("Game.db");
            GameController gameController = new GameController();
            gameController.start();
        } catch (GameException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}