package logic;

import utilities.Global;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class BoardEval {

    private final static int winPoints = 50000;
    private final static int drawPoints = 10000;
    private final static int piecePoints = 1000;
    private final static int placementPoints = 50;
    private final static int movePoints = 200;


    public static int fav1 (GameState gameState) {
        int value=0;

        if(gameState.getCurrentState().equals(GameState.State.PLACING)){
            value += evaluatePiecePlacement(gameState);
        } else{
            value += evaluatePossibleMoves(gameState);
        }

        value += evaluateNumberOfPieces(gameState);
        value += evaluateGameOver(gameState);
        value += randomComponent();

        return value;
    }

    private static int evaluatePiecePlacement(GameState gameState) {
        int value=0;

        ArrayList<Integer> board = gameState.getBoard();

        for(int i=0; i < board.size(); i++){
            switch(board.get(i)){
                case Global.maximizerPlayer:
                    value += placementPoints * GameState.neighbours[i].length;
                    break;
                case Global.minimizerPlayer:
                    value -= placementPoints * GameState.neighbours[i].length;
                    break;
                default:
                    break;
            }
        }

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
            case Global.maximizerPlayer:
                maximizerMoves = gameState.getPossibleMoves().size();
                minimizerMoves = gameStateOtherTurn.getPossibleMoves().size();
                break;
            case Global.minimizerPlayer:
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
