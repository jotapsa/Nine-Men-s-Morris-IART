import logic.Computer;
import logic.GameState;
import logic.Player;
import utilities.Global;

final class Main {

	private final static int maximizerPlayer = 1;
	private final static int minimizerPlayer = 2;
	private final static int pc1Depth = 5;
	private final static int pc2Depth = 5;

    public static void main(String[] args) {
        int answer = 0;

        while (answer != 5) {
            printMainMenu();
            answer = Global.askInt("Option : ", 1, 5);

            switch (answer){
                case 1:
                    newGame(new Player(maximizerPlayer), new Computer(minimizerPlayer, pc2Depth));
                    break;
                case 2:
                    newGame(new Player(maximizerPlayer), new Player(minimizerPlayer));
                    break;
                case 3:
                    newGame(new Computer(maximizerPlayer, pc1Depth), new Computer(minimizerPlayer, pc2Depth));
                    break;
                case 4:
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
        System.out.println("1 - Singleplayer");
        System.out.println("2 - Multiplayer");
        System.out.println("3 - Computer vs Computer");
        System.out.println("4 - About");
        System.out.println("5 - Exit");
    }

    private static GameState newGame(Player player1, Player player2){
        GameState gameState = new GameState();

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

        } while(gameState.isGameOver() == -1);

        return gameState;
    }
}
