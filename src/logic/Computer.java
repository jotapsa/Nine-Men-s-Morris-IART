package logic;

import utilities.Global;

import java.util.ArrayList;
import java.util.function.Function;

public class Computer extends Player {

    private int depth;
    private Global.decFunc decFunc;
    private final Function<GameState, Integer> evalFunc;

    public Computer(int number, int depth, Global.decFunc decFunc, Function<GameState, Integer> evalFunc) {
        super(number);

        this.depth = depth;
        this.decFunc = decFunc;
        this.evalFunc = evalFunc;
    }

    @Override
    public Move getMove(GameState gameState) {
        System.out.println("\nComputer " + this.number + " : Playing...\n");
        return computerAux(gameState);
    }

    private Move computerAux(GameState gameState){
        ArrayList<GameState> possibleStates = gameState.getPossibleBoards();

        GameState bestState = possibleStates.get(0);

        int bestStateValue, compareStateValue;
        for(int i=1; i<possibleStates.size(); i++){
            switch (decFunc){
                case MiniMax:
                    bestStateValue = miniMax(
                            bestState,
                            depth,
                            bestState.getCurrentPlayer() == Global.maximizerPlayer
                    );

                    compareStateValue = miniMax(
                            possibleStates.get(i),
                            depth,
                            bestState.getCurrentPlayer() == Global.maximizerPlayer
                    );
                    break;
                case AlphaBeta:
                    bestStateValue = alphaBeta(
                            bestState,
                            depth,
                            java.lang.Integer.MIN_VALUE,
                            Integer.MAX_VALUE,
                            bestState.getCurrentPlayer() == Global.maximizerPlayer
                    );

                    compareStateValue = alphaBeta(
                            possibleStates.get(i),
                            depth,
                            java.lang.Integer.MIN_VALUE,
                            Integer.MAX_VALUE,
                            possibleStates.get(i).getCurrentPlayer() == Global.maximizerPlayer
                    );
                    break;
                default:
                    bestStateValue = 0;
                    compareStateValue = 0;
                    break;
            }


            switch(this.number){
                case Global.maximizerPlayer:
                    if(bestStateValue < compareStateValue){
                        bestState = possibleStates.get(i);
                    }
                    break;
                case Global.minimizerPlayer:
                    if(bestStateValue > compareStateValue){
                        bestState = possibleStates.get(i);
                    }
                default:
                    break;
            }
        }


        return bestState.getLastMove();
    }

    private int alphaBeta(GameState gameState, int depth, int alpha, int beta, boolean maximizerPlayer) {
        if(depth == 0 || gameState.isGameOver() != -1){
            return evaluateGameState(gameState);
        }

        ArrayList<GameState> possibleBoards = gameState.getPossibleBoards();

        int value;
        if(maximizerPlayer) {
            value = Integer.MIN_VALUE;

            for (GameState possibleBoard : possibleBoards) {
                value = Math.max(value, alphaBeta(possibleBoard, depth - 1, alpha, beta, false));
                alpha = Math.max(alpha, value);

                if (alpha >= beta) {
                    break;
                }
            }

            return value;
        }else {
            value = Integer.MAX_VALUE;

            for (GameState possibleBoard : possibleBoards) {
                value = Math.min(value, alphaBeta(possibleBoard, depth - 1, alpha, beta, true));
                beta = Math.min(beta, value);

                if (alpha > beta) {
                    break;
                }
            }

            return value;
        }
    }

    private int miniMax(GameState gameState, int depth, boolean maximizerPlayer){
        if(depth == 0 || gameState.isGameOver() != -1){
            return evaluateGameState(gameState);
        }

        ArrayList<GameState> possibleBoards = gameState.getPossibleBoards();

        int value;
        if(maximizerPlayer){
            value = Integer.MIN_VALUE;

            for(GameState possibleBoard : possibleBoards){
                value = Math.max(value, miniMax(possibleBoard, depth -1, false));
            }

            return value;
        } else{
            value = Integer.MAX_VALUE;

            for(GameState possibleBoard : possibleBoards){
                value = Math.min(value, miniMax(possibleBoard, depth -1, true));
            }

            return value;
        }
    }

    private int evaluateGameState(GameState gameState) {
        return evalFunc.apply(gameState);
    }

}
