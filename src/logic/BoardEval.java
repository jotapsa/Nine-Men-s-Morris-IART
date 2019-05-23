package logic;

import utilities.Global;

import java.util.ArrayList;
import java.util.Random;

public class BoardEval {

    private final static int winPoints = 50000;
    private final static int drawPoints = 10000;
    private final static int piecePoints = 1000;
    private final static int placementPoints = 50;
    private final static int possibleMillsPoints = 250;
    private final static int movePoints = 500;
    private final static int openMovePoints = 250;


    public static int fav1 (GameState gameState) {
        int value=0;

        if(gameState.getCurrentState().equals(GameState.State.PLACING)){
            value += evaluatePiecePlacement(gameState);
            //value += evaluatePossibleMovesDuringPlacing(gameState);

        } else{
            value += evaluatePossibleMoves(gameState);
        }

        //value += evaluatePossibleMills(gameState);
        value += evaluateNumberOfPieces(gameState);
        value += evaluateGameOver(gameState);
//        value += randomComponent();


        return value;
    }

    private static int evaluatePossibleMovesDuringPlacing(GameState gameState) {
        int value=0;
        ArrayList<Integer> board = gameState.getBoard();

        for(int s=0; s < board.size(); s++){
            switch(board.get(s)){
                case Global.maximizerPlayer:
                    for(int e : GameState.neighbours[s]){
                       if(board.get(e) == 0){
                           value += 1;
                       }
                    }
                    break;
                case Global.minimizerPlayer:
                    for(int e : GameState.neighbours[s]){
                        if(board.get(e) == 0){
                            value -= 1;
                        }
                    }
                    break;
                default:
                    break;
            }
        }

        return value * openMovePoints;
    }

    public static int evaluatePossibleMills(GameState gameState) {
        return  (
                gameState.getPossibleMillsForPlayer(Global.maximizerPlayer-1) -
                gameState.getPossibleMillsForPlayer(Global.minimizerPlayer-1)
                ) * possibleMillsPoints;
    }

    public static int evaluatePiecePlacement(GameState gameState) {
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
        int[] playerPieces = gameState.getPlayerPieces();

        return (playerPieces[Global.maximizerPlayer-1] -
                playerPieces[Global.minimizerPlayer-1])* piecePoints;
    }

    public static int evaluatePossibleMoves(GameState gameState) {
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

        return (maximizerMoves - minimizerMoves) * movePoints;
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
