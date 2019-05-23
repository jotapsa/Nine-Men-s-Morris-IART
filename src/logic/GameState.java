package logic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import utilities.Global;

public class GameState {
    private static int boardSize = 24;

    private static final int PLAYER_1 = 1;
    private static final int PLAYER_2 = 2;

    public enum State {
        PLACING, MOVING, FLYING;
    }
    
    public static Integer[][] neighbours = {
            {1, 9},
            {0, 2, 4},
            {1, 14},
            {4, 10},
            {1, 3, 5, 7},
            {4, 13},
            {7, 11},
            {4, 6, 8},
            {7, 12},
            {0, 10, 21},
            {3, 9, 11, 18},
            {6, 10, 15},
            {8, 13, 17},
            {5, 12, 14, 20},
            {2, 13, 23},
            {11, 16},
            {15, 17, 19},
            {12, 16},
            {10, 19},
            {16, 18, 20, 22},
            {13, 19},
            {9, 22},
            {19, 21, 23},
            {14, 22},
    };

    public static Integer[][] coords = {
    		{Global.VL1, Global.HL1},
    		{Global.VL4, Global.HL1},
    		{Global.VL7, Global.HL1},
    		{Global.VL2, Global.HL2},
    		{Global.VL4, Global.HL2},
    		{Global.VL6, Global.HL2},
    		{Global.VL3, Global.HL3},
    		{Global.VL4, Global.HL3},
    		{Global.VL5, Global.HL3},
    		{Global.VL1, Global.HL4},
    		{Global.VL2, Global.HL4},
    		{Global.VL3, Global.HL4},
    		{Global.VL5, Global.HL4},
    		{Global.VL6, Global.HL4},
    		{Global.VL7, Global.HL4},
    		{Global.VL3, Global.HL5},
    		{Global.VL4, Global.HL5},
    		{Global.VL5, Global.HL5},
    		{Global.VL2, Global.HL6},
    		{Global.VL4, Global.HL6},
    		{Global.VL6, Global.HL6},
    		{Global.VL1, Global.HL7},
    		{Global.VL4, Global.HL7},
    		{Global.VL7, Global.HL7},
    };

    private static Integer[][] possibleMills = {
            {0, 1, 2},
            {3, 4, 5},
            {6, 7, 8},
            {9, 10, 11},
            {12, 13, 14},
            {15, 16, 17},
            {18, 19, 20},
            {21, 22, 23},
            {0, 9, 21},
            {3, 10, 18},
            {6, 11, 15},
            {1, 4, 7 },
            {16, 19, 22},
            {8, 12, 17},
            {5, 13, 20},
            {2, 14, 23},
    };

    private ArrayList<Integer> board;

    private int currentPlayer;
    private State currentState;

    private int nmoves;
    private int[] playerPieces = {9, 9};

    private int millCountdown;
    private int flyingMillCountdown;
    private ArrayList<ArrayList<Integer>> boardHistory;
    private Move lastMove;

    public GameState(){
        this.boardHistory = new ArrayList<>();

        this.board = new ArrayList<>();
        for(int i=0; i<boardSize; i++){
            this.board.add(0);
        }

        this.reset();
    }

    public GameState(GameState gameState){
        this.boardHistory = (ArrayList<ArrayList<Integer>>) gameState.getHistoryBoard().clone();
        this.board = (ArrayList<Integer>) gameState.getBoard().clone();

        this.currentState = gameState.getCurrentState();
        this.nmoves = gameState.getNMoves();
        this.currentPlayer = gameState.getCurrentPlayer();

        this.millCountdown = gameState.getMillCountdown();
        this.flyingMillCountdown = gameState.getFlyingMillCountdown();

        this.lastMove = gameState.getLastMove();
    }

    public Move getLastMove() {
        return this.lastMove;
    }

    public int getFlyingMillCountdown() {
        return this.flyingMillCountdown;
    }

    public int getMillCountdown() {
        return this.millCountdown;
    }

    public int getPlayerAvailableRocks(int player) {
    	if(this.nmoves > 18) {
    		return 0;
    	}
    	if(this.nmoves == 0) {
    		return 9;
    	}
    	else if(player == Global.maximizerPlayer) {
    		if(this.nmoves % 2 == 0 ) {
    			return 9 - this.nmoves / 2;
    		}
    		else {
    			return 9 - this.nmoves /2 - 1;
    		}
    	}
    	else {
    		return 9 - this.nmoves / 2;
    	}
    }

    public int getNMoves() {
        return this.nmoves;
    }

    public ArrayList<Integer> getBoard() {
        return this.board;
    }

    public ArrayList<ArrayList<Integer>> getHistoryBoard() {
        return this.boardHistory;
    }

    public int[] getPlayerPieces(){
        return this.playerPieces;
    }

    public void reset(){
        for(int i=0; i<boardSize; i++){
            this.board.set(i, 0);
        }

        this.currentPlayer = PLAYER_1;
        this.nmoves = 0;
        this.currentState = State.PLACING;
        this.millCountdown = 50;
        this.flyingMillCountdown = 10;
        this.boardHistory.clear();

        this.lastMove = null;
    }

    public State getCurrentState(){
        return this.currentState;
    }

    public int getPos(int pos){
        return this.board.get(pos);
    }

    public int getCurrentPlayer(){
        return this.currentPlayer;
    }

    public ArrayList<GameState> getPossibleBoards() {
        ArrayList<GameState> possibleBoards = new ArrayList<>();

        for(Move move : this.getPossibleMoves()){
            GameState gameState = new GameState(this);
            gameState.doMove(move);

            possibleBoards.add(gameState);
        }

        return possibleBoards;
    }

    public ArrayList<Move> getPossibleMoves() {
        ArrayList<Move> possibleMoves = new ArrayList<>();

        switch(this.currentState){
            case PLACING:
                for(int s=0; s < boardSize; s++){
                    if(this.board.get(s) == 0){
                        if(this.moveCausesMill(new Move(s))){
                            for(int t=0; t < boardSize; t++){
                                if(this.board.get(t) == 3 - this.currentPlayer){
                                    Move tempMove = new Move(s);
                                    tempMove.setTaken(t);
                                    possibleMoves.add(tempMove);
                                }
                            }
                        } else{
                            possibleMoves.add(new Move(s));
                        }

                    }
                }
                break;
            case MOVING:
                for(int s=0; s < boardSize; s++){
                    if(this.board.get(s) == this.currentPlayer){
                        for(int e : neighbours[s]){
                            if(this.board.get(e) == 0 ){
                                if(this.moveCausesMill(new Move(s, e))){
                                    for(int t=0; t < boardSize; t++){
                                        if(this.board.get(t) == 3 - this.currentPlayer){
                                            possibleMoves.add(new Move(s, e, t));
                                        }
                                    }
                                }
                                else{
                                    possibleMoves.add(new Move(s, e));
                                }
                            }
                        }
                    }
                }
                break;
            case FLYING:
                for(int s=0; s < boardSize; s++){
                    if(this.board.get(s) == this.currentPlayer){
                        for(int e=0; e < boardSize; e++){
                            if(this.board.get(e) == 0){
                                if(this.moveCausesMill(new Move(s, e))){
                                    for(int t=0; t < boardSize; t++){
                                        if(this.board.get(t) == 3 - this.currentPlayer){
                                            possibleMoves.add(new Move(s, e, t));
                                        }
                                    }
                                } else{
                                    possibleMoves.add(new Move(s, e));
                                }
                            }
                        }
                    }
                }
                break;
            default:
                break;
        }

        return possibleMoves;
    }

    public int getPossibleMillsForPlayer(int player) {
        int result=0;
        boolean flag;

        for (Integer[] possibleMill : possibleMills) {
            flag = true;
            for (Integer integer : possibleMill) {
                if (board.get(integer) != player) {
                    flag=false;
                    break;
                }

            }
            if(flag){
                result++;
            }
        }

        return result;
    }

    /*
     * Returns 0 if game is a draw
     * Returns 1 or 2 corresponding to victorious player if a player has won the game.
     * Returns -1 if game is not finished
     */
    public int isGameOver() {
        // If there are 50 moves without any mills the game ends in a tie.
        // Else if there are 10 completed moves where both player only have 3 pieces.
        // Else if the board repeats itself three times.
        if (this.millCountdown == 0
                || this.flyingMillCountdown == 0
                || this.countBoardRepeats(this.board) == 3) {
            return 0; // Draw
        }

        if(this.getPossibleMoves().size() == 0 || this.playerPieces[this.currentPlayer -1] == 2){
            return 3 - this.currentPlayer; // Winner
        }

        return -1;
    }

    private int countBoardRepeats(ArrayList<Integer> board) {
        int n=0;

        for(ArrayList<Integer> historyBoard : this.boardHistory){
            if(historyBoard.equals(board)){
                n++;
            }
        }

        return n;
    }
    
    public Move giveHint(GameState g) {
    	Move toReturn = null;
    	GameState aux = new GameState(g);
    	Computer auxPlayer = new Computer(aux.currentPlayer, Global.pc1Depth, Global.pc1DecFunc , BoardEval::fav1);
    	
    	toReturn = auxPlayer.getMove(aux);
    	
    	
    	return toReturn;
    }

    public boolean moveCausesMill(Move move){
        ArrayList<Integer> moveBoard = ( ArrayList<Integer> ) this.board.clone();
        int movePos = -1;

        if(this.currentState == State.PLACING){
            moveBoard.set(move.getStart(), this.currentPlayer);

            movePos = move.getStart();
        }
        else {
            moveBoard.set(move.getStart(), 0);
            moveBoard.set(move.getEnd(), this.currentPlayer);

            movePos = move.getEnd();
        }

        for(Integer[] mill : this.possibleMills){
            ArrayList<Integer> possibleMill = new ArrayList<>(Arrays.asList(mill));

            if(possibleMill.contains(movePos)){
                if( moveBoard.get(mill[0]) == this.currentPlayer &&
                        moveBoard.get(mill[1]) == this.currentPlayer &&
                        moveBoard.get(mill[2]) == this.currentPlayer){
                    return true;
                }
            }
        }

        return false;
    }

    public boolean isValidMove(Move move){

        switch(this.currentState){
            case PLACING:
                if(this.board.get(move.getStart()) == 0){
                    return true;
                }
                break;
            case MOVING:
                if(this.board.get(move.getStart()) == this.currentPlayer && this.board.get(move.getEnd()) == 0){
                    for(Integer neighbour : this.neighbours[move.getStart()]){
                        if(move.getEnd() == neighbour){
                            return true;
                        }
                    }
                }
                break;
            case FLYING:
                if(this.board.get(move.getStart()) == this.currentPlayer && this.board.get(move.getEnd()) == 0){
                    return true;
                }
                break;
            default:
                break;
        }

        return false;
    }

    public void doMove(Move move) {
        this.boardHistory.add((( ArrayList<Integer> ) this.board.clone()));
        this.nmoves++;

        if(this.currentState == State.PLACING){
            this.board.set(move.getStart(), this.currentPlayer);
        }
        else if(this.currentState == State.MOVING){
            this.board.set(move.getStart(), 0);
            this.board.set(move.getEnd(), this.currentPlayer);
        }

        if(move.getTaken() != -1){
            this.board.set(move.getTaken(), 0);
            this.playerPieces[(3 - this.currentPlayer) - 1]--;
            this.millCountdown = 50;
        }

        // A tie can occur when both players are reduced to 3 men and no mills are formed during ten turns.
        if(this.playerPieces[0] + this.playerPieces[1] == 6){
            this.flyingMillCountdown--;
        }

        changeTurn();
        this.lastMove = move;

        if(nmoves >= 18 && this.playerPieces[this.currentPlayer -1] == 3){
            this.currentState = State.FLYING;
        }
        else if(nmoves >= 18){
            this.currentState = State.MOVING;
        }
    }

    public void changeTurn(){
        this.currentPlayer = 3 - this.currentPlayer;
    }

    public boolean isValidTake(Move move) {
        int opponent = 3 - this.currentPlayer;
        if(this.board.get(move.getTaken()) == opponent){
            return true;
        }

        return false;
    }
}
