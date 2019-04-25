import logic.GameState;
import logic.Player;

public class Main {

    public static void main(String[] args) {

        GameState gameState = new GameState();

        Player player1 = new Player(1);
        Player player2 = new Player(2);

        do{
            gameState.drawState();

            switch(gameState.getCurrentPlayer()){
                case 1:
                    gameState.doMove(player1.getMove(gameState));
                    break;
                case 2:
                    break;
                default:
                        break;
            }

            gameState.drawState();
        } while(!gameState.isGameOver());


    }
}
