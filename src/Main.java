import logic.GameState;
import logic.Player;

final class Main {

    public static void main(String[] args) {

        GameState gameState = new GameState();

        Player player1 = new Player(1);
        Player player2 = new Player(2);

        do{
            GameView.render(gameState);

            switch(gameState.getCurrentPlayer()){
                case 1:
                    gameState.doMove(player1.getMove(gameState));
                    break;
                case 2:
                    gameState.doMove(player2.getMove(gameState));
                    break;
                default:
                        break;
            }

        } while(!gameState.isGameOver());


    }
}
