package logic;

import java.util.Random;

public class BoardEval {

    private final static int winPoints = 50000;
    private final static int drawPoints = 10000;
    private final static int piecePoints = 1000;
    private final static int movePoints = 200;

    public static int fav1 (GameState gameState) {
        int value=0;

        value += evaluateNumberOfPieces(gameState);
        value += evaluatePossibleMoves(gameState);
        value += evaluateGameOver(gameState);
        value += randomComponent();

        return value;
    }



    public static int evaluateNumberOfPieces(GameState gameState) {
        int value=0;
        int maximizerPieces, minimizerPieces;

        maximizerPieces = gameState.countBoardPieces(gameState.getBoard(), 1);
        minimizerPieces = gameState.countBoardPieces(gameState.getBoard(), 2);

        value += piecePoints * maximizerPieces;
        value -= piecePoints * minimizerPieces;

        return value;
    }

    public static int evaluatePossibleMoves(GameState gameState) {
        int value=0;
        int maximizerMoves, minimizerMoves;
        GameState gameStateOtherTurn = new GameState(gameState);
        gameStateOtherTurn.changeTurn();
        switch(gameState.getCurrentPlayer()){
            case 1:
                maximizerMoves = gameState.getPossibleMoves().size();
                minimizerMoves = gameStateOtherTurn.getPossibleMoves().size();
                break;
            case 2:
                minimizerMoves = gameState.getPossibleMoves().size();
                maximizerMoves = gameStateOtherTurn.getPossibleMoves().size();
                break;
            default:
                maximizerMoves = 0;
                minimizerMoves = 0;
                break;
        }

        value = maximizerMoves * movePoints - minimizerMoves * movePoints;

        return value;
    }

    public static int evaluateGameOver (GameState gameState) {
        int value=0;
        switch (gameState.isGameOver()){
            case 1:
                value = winPoints;
                break;
            case 2:
                value = -winPoints;
                break;
            case 0: //draw
                value = drawPoints;
                break;
            default:
                break;
        }

        return value;
    }

    /*
     * Generate random number between -10 and 10.
     */
    private static int randomComponent() {
        Random generator = new Random();
        return generator.nextInt(21) -10;
    }
}
