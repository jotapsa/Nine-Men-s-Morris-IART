package logic;

import utilities.Global;

public class Player {
    protected int number;

    public Player(int number){
        this.number = number;
    }

    public Move getMove(GameState gameState){
        Move move = null;

        System.out.println("\nPlayer " + number + " - It's your turn !");
        System.out.println("Hint -> 24");

        if(gameState.getCurrentState() == GameState.State.PLACING){
            //Get placing coordinates
            do{
                move = new Move(Global.askInt("Placing (0-23): ", 0, 23));
            }while(!gameState.isValidMove(move));
        }
        else if(gameState.getCurrentState() == GameState.State.MOVING){
            //Get Start and End coordinates
            do{
                move = new Move(Global.askInt("Moving (0-23): ", 0, 23),
                                    Global.askInt("To (0-23): ", 0, 23));
            }while(!gameState.isValidMove(move));
        }


        if(gameState.moveCausesMill(move)){
            //Get opponent piece
            do{
                move.setTaken(Global.askInt("Remove Opponent Piece (0-23): ", 0, 23));
            }while(!gameState.isValidTake(move));

        }

        return move;
    }

}
