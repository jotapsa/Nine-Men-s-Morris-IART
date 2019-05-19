package logic;

import utilities.Global;

import java.util.ArrayList;
import java.util.function.Function;

public class Computer extends Player {

    private int placingDepth;
    private int depth;
    private final Function<GameState, Integer> evaluateFunction;

    public Computer(int number, int placingDepth, int depth, Function<GameState, Integer> evaluateFunction) {
        super(number);

        this.placingDepth = placingDepth;
        this.depth = depth;
        this.evaluateFunction = evaluateFunction;
    }

    @Override
    public Move getMove(GameState gameState) {
        System.out.println("\nComputer " + this.number + " : Playing...\n");
        return alphaBetaAux(gameState);
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

    private Move alphaBetaAux(GameState gameState){
        ArrayList<GameState> possibleStates = gameState.getPossibleBoards();
        int depth;
        switch (gameState.getCurrentState()){
            case PLACING:
                depth = this.placingDepth;
                break;
            default:
                depth = this.depth;
                break;
        }

        GameState bestState = possibleStates.get(0);

        for(int i=1; i<possibleStates.size(); i++){
            int bestStateValue = alphaBeta(
                    bestState,
                    depth,
                    java.lang.Integer.MIN_VALUE,
                    Integer.MAX_VALUE,
                    bestState.getCurrentPlayer() == Global.maximizerPlayer
            );

            int compareStateValue = alphaBeta(
                    possibleStates.get(i),
                    depth,
                    java.lang.Integer.MIN_VALUE,
                    Integer.MAX_VALUE,
                    possibleStates.get(i).getCurrentPlayer() == Global.maximizerPlayer
            );

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


    private int evaluateGameState(GameState gameState) {
        return evaluateFunction.apply(gameState);
    }

}
