package logic;

import utilities.Global;

public class Player {
    private int number;

    public Player(int number){
        this.number = number;
    }

    public Move getMove(GameState gameState){
        Move move = null;

        if(gameState.getCurrentState() == GameState.State.PLACING){
            //Get placing coordinates
            do{
                move = new Move(Global.askInt("Placing (0-23): ", 0, 23));
            }while(!gameState.isValidMove(move));
        }
        else if(gameState.getCurrentState() == GameState.State.FLYING){
            //Get Start and End coordinates
            do{
                move = new Move(Global.askInt("Moving (0-23): ", 0, 23),
                                    Global.askInt("To (0-23): ", 0, 23));
            }while(!gameState.isValidMove(move));
        }

        if(gameState.moveCausesMill(move)){
            //Get opponent piece
            do{
                do{
                    move.setTaken(Global.askInt("Remove Opponent Piece (0-23): ", 0, 23));
                }while(!gameState.isValidMove(move));
            }while(!gameState.isValidMove(move));
        }

        return move;
    }

}
