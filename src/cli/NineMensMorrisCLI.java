package cli;

import cli.GameView;
import logic.*;
import utilities.Global;

public class NineMensMorrisCLI {

    public static void main(String[] args) {
        int answer = 0;

        while (answer != 6) {
            printMainMenu();
            answer = Global.askInt("Option : ", 1, 6);

            switch (answer){
                case 1:
                    newGame(
                            new Player(Global.maximizerPlayer),
                            new Computer(Global.minimizerPlayer, Global.pc2Depth, Global.pc2DecFunc, BoardEval::fav1)
                    );
                case 2:
                    newGame(
                            new Computer(Global.maximizerPlayer, Global.pc1Depth, Global.pc1DecFunc, BoardEval::fav1),
                            new Player(Global.minimizerPlayer)
                    );
                    break;
                case 3:
                    newGame(
                    		new Player(Global.maximizerPlayer),
                            new Player(Global.minimizerPlayer)
                    );
                    break;
                case 4:
                    newGame(
                            new Computer(Global.maximizerPlayer, Global.pc1Depth, Global.pc1DecFunc, BoardEval::fav1),
                            new Computer(Global.minimizerPlayer, Global.pc2Depth, Global.pc2DecFunc, BoardEval::fav2)
                    );
                    break;
                case 5:
                    printAbout();
                    break;
                default:
                    break;
            }
        }
    }

    private static void printAbout() {
        System.out.println("\n\n---- About ----");

        System.out.println();

        System.out.println("Bernardo Manuel Costa Barbosa - up201503477");
        System.out.println("Duarte Nuno Esteves André Lima de Carvalho - up201503661");
        System.out.println("João Pedro Teixeira Pereira de Sá - up201506252");

        System.out.println();

        System.out.println("Artificial Intelligence");
        System.out.println("Master in Informatics and Computing Engineering");
        System.out.println("Faculty of Engineering  --- University of Porto");
    }

    private static void printMainMenu() {
        System.out.println("\n\n\n---- Main Menu ----");
        System.out.println("1 - Singleplayer - Player vs Computer");
        System.out.println("2 - Singleplayer - Computer vs Player");
        System.out.println("3 - Multiplayer");
        System.out.println("4 - Computer vs Computer");
        System.out.println("5 - About");
        System.out.println("6 - Exit");
    }

    public static void printBoardStats(GameState gameState) {
        System.out.println("\n---- Board Stats ----");
        System.out.println("Last move - " + gameState.getLastMove());
        System.out.println("NOT FLYING : ");
        System.out.println("\tevaluatePossibleMovingMoves - " + BoardEval.evaluatePossibleMovingMoves(gameState));

        System.out.println("evaluatePiecePlacement - " + BoardEval.evaluatePiecePlacement(gameState));
        System.out.println("evaluateNumberOfPieces - " + BoardEval.evaluateNumberOfPieces(gameState));
        System.out.println("evaluateGameOver - " + BoardEval.evaluateGameOver(gameState));
    }

    private static GameState newGame(Player player1, Player player2){
        GameState gameState = new GameState();

        long startTime = System.currentTimeMillis();

        do{
            GameView.render(gameState);

            switch(gameState.getCurrentPlayer()){
                case Global.maximizerPlayer:
                    gameState.doMove(player1.getMove(gameState));
                    break;
                case Global.minimizerPlayer:
                    gameState.doMove(player2.getMove(gameState));
                    break;
                default:
                    break;
            }

//            printBoardStats(gameState);
        } while(gameState.isGameOver() == -1);

        long estimatedTime = System.currentTimeMillis() - startTime;

        System.out.println("Winner -> " + gameState.isGameOver());
        System.out.println("Time -> " + estimatedTime/100);
        System.out.println("Moves -> " + gameState.getNMoves());

        return gameState;
    }
}
