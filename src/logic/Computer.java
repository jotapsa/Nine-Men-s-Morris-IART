package logic;

import java.util.ArrayList;
import java.util.function.Function;

public class Computer extends Player {

    private int depth;
    private final Function<GameState, Integer> evaluateFunction;

    public Computer(int number, int depth) {
        super(number);

        this.depth = depth;
        this.evaluateFunction = Computer::fav1; // default
    }

    public Computer(int number, int depth, Function<GameState, Integer> evaluateFunction) {
        super(number);

        this.depth = depth;
        this.evaluateFunction = evaluateFunction;
    }

    @Override
    public Move getMove(GameState gameState) {
        System.out.println("Computer " + this.number + " : Playing...\n");
        return alphaBeta(gameState);
    }

    int alphaBeta(GameState gameState, int depth, int alpha, int beta, boolean maximizerPlayer) {
        if(depth == 0 || gameState.isGameOver() != -1){
            return evaluateGameState(gameState);
        }

        ArrayList<GameState> possibleBoards = gameState.getPossibleBoards();
        int possibleBoardsLength = possibleBoards.size();

        int value;
        if(maximizerPlayer) {
            value = Integer.MIN_VALUE;
            for (int i = 0; i < possibleBoardsLength; i++) {
                value = Math.max(value, alphaBeta(possibleBoards.get(i), depth - 1, alpha, beta, false));
                alpha = Math.max(alpha, value);
                if (alpha >= beta) {
                    break;
                }
            }
            return value;
        }else {
            value = Integer.MAX_VALUE;

            for (int i = 0; i < possibleBoardsLength; i++) {
                value = Math.min(value, alphaBeta(possibleBoards.get(i), depth - 1, alpha, beta, true));
                beta = Math.min(beta, value);
                if (alpha > beta) {
                    break;
                }
            }
            return value;
        }
    }

    Move alphaBeta(GameState gameState){
        ArrayList<GameState> possibleStates = gameState.getPossibleBoards();

        GameState bestState = possibleStates.get(0);

        for(int i=1; i<possibleStates.size(); i++){
            int bestStateValue = alphaBeta(bestState, this.depth,
                    java.lang.Integer.MIN_VALUE,
                    Integer.MAX_VALUE,
                    bestState.getCurrentPlayer() == 1);

            int compareStateValue = alphaBeta(possibleStates.get(i), this.depth,
                    java.lang.Integer.MIN_VALUE,
                    Integer.MAX_VALUE,
                    possibleStates.get(i).getCurrentPlayer() == 1 );

            switch(this.number){
                case 1:
                    if(bestStateValue < compareStateValue){
                        bestState = possibleStates.get(i);
                    }
                    break;
                case 2:
                    if(bestStateValue > compareStateValue){
                        bestState = possibleStates.get(i);
                    }
                default:
                    break;
            }
        }

        return bestState.getLastMove();
    }


    public int evaluateGameState(GameState gameState) {
        return evaluateFunction.apply(gameState);
    }


    public static int fav1 (GameState gameState) {
        return 1;
    }

    public static int fav2 (GameState gameState) { return 2; }
}
