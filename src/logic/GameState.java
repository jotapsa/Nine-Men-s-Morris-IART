package logic;

import java.util.ArrayList;
import java.util.Arrays;

public class GameState {
    private static int boardSize = 24;

    public enum State {
        PLACING, FLYING
    }

    private static Integer[][] neighbours = {
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
    private int millCountdown;
    private int flyingMillCountdown;
    private ArrayList<ArrayList<Integer>> boardHistory;

    public GameState(){
        this.boardHistory = new ArrayList<>();

        this.board = new ArrayList<>();
        for(int i=0; i<boardSize; i++){
            this.board.add(0);
        }

        this.reset();
    }

    public void reset(){
        for(int i=0; i<boardSize; i++){
            this.board.set(i, 0);
        }

        this.currentPlayer = 1;
        this.nmoves = 0;
        this.currentState = State.PLACING;
        this.millCountdown = 50;
        this.flyingMillCountdown = 10;
        this.boardHistory.clear();
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


    public boolean isGameOver() {
        // If there are 50 moves without any mills the game ends in a tie.
        // Else if there are 10 completed moves where both player only have 3 pieces.
        // Else if the board repeats itself three times.
        if (this.millCountdown == 0
                || this.flyingMillCountdown == 0
                || this.countBoardRepeats(this.board) == 3) {
            return true;
        }
        return false;
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

    private int countBoardPieces(ArrayList<Integer> board) {
        int n=0;

        for(int cell : board){
            if(cell != 0){
                n++;
            }
        }

        return n;
    }

    public boolean moveCausesMill(Move move){
        ArrayList<Integer> moveBoard = ( ArrayList<Integer> ) this.board.clone();
        int movePos = -1;

        if(this.currentState == State.PLACING){
            moveBoard.set(move.getStart(), this.currentPlayer);

            movePos = move.getStart();
        }
        else if(this.currentState == State.FLYING){
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

        if(this.currentState == State.PLACING){
            if(this.board.get(move.getStart()) == 0){
                return true;
            }
        }
        else if(this.currentState == State.FLYING){
            if(this.board.get(move.getStart()) == this.currentPlayer && this.board.get(move.getEnd()) == 0){
                for(Integer neighbour : this.neighbours[move.getStart()]){
                        if(move.getEnd() == neighbour){
                            return true;
                        }
                }
            }
        }

        return false;
    }

    public void doMove(Move move) {
        this.boardHistory.add((( ArrayList<Integer> ) this.board.clone()));
        this.nmoves++;

        if(this.currentState == State.PLACING){
            this.board.set(move.getStart(), this.currentPlayer);
        }
        else if(this.currentState == State.FLYING){
            this.board.set(move.getStart(), 0);
            this.board.set(move.getEnd(), this.currentPlayer);
        }

        if(move.getTaken() != -1){
            this.board.set(move.getTaken(), 0);
            this.millCountdown = 50;
        }

        if(countBoardPieces(this.board) == 6){
            this.flyingMillCountdown--;
        }

        if(nmoves >= 18){
            this.currentState = State.FLYING;
        }
        else{
            this.currentState = State.PLACING;
        }

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
