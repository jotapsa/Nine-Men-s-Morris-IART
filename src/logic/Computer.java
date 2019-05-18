package logic;

import java.util.ArrayList;
import java.util.Random;
import java.util.function.Function;

public class Computer extends Player {

    private int depth;
    private final Function<GameState, Integer> evaluateFunction;

    private final static int winPoints = 50000;
    private final static int drawPoints = 10000;
    private final static int piecePoints = 1000;
    private final static int movePoints = 200;

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
        System.out.println("Eval Current Board: " + fav1(gameState));
        return alphaBetaAux(gameState);
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

    Move alphaBetaAux(GameState gameState){
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
        int evalResult = evaluateFunction.apply(gameState);
        System.out.println("evalResult: " + evalResult);
        return evaluateFunction.apply(gameState);
    }


    public static int fav1 (GameState gameState) {
    	int value=0;

    	value += evaluateNumberOfPieces(gameState);
    	value += evaluatePossibleMoves(gameState);
    	value += evaluateGameOver(gameState);
    	value += randomComponent();

        return value;
    }

	private static int evaluateNumberOfPieces(GameState gameState) {
		int value=0;
        int maximizerPieces, minimizerPieces;

		maximizerPieces = gameState.countBoardPieces(gameState.getBoard(), 1);
		minimizerPieces = gameState.countBoardPieces(gameState.getBoard(), 2);

		value += piecePoints * maximizerPieces;
		value -= piecePoints * minimizerPieces;

		return value;
	}

	private static int evaluatePossibleMoves(GameState gameState) {
		int value=0;
        int maximizerMoves, minimizerMoves;
        GameState gameStateOtherTurn = new GameState(gameState);
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

	private static int evaluateGameOver (GameState gameState) {
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
     * Generate random number between 1 and 10.
     */
    private static int randomComponent() {
    	Random generator = new Random();
    	int value = generator.nextInt(10) + 1;

    	return value;
	}

	public static int fav2 (GameState gameState) { return 2; }
}
