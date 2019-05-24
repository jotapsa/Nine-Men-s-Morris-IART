package logic;

import utilities.Global;

public class Move {
    private int start = Global.INVALID_INDEX;
    private int end = Global.INVALID_INDEX;
    private int taken = Global.INVALID_INDEX;

    public Move(int startPos){
        this.start = startPos;
    }

    public Move(int startPos, int endPos){
        if(startPos == endPos){
            return;
        }
        this.start = startPos;
        this.end = endPos;
    }

    public Move(int startPos, int endPos, int takenPos){
        if(startPos == endPos || takenPos == this.start || takenPos == this.end){
            return;
        }
        this.start = startPos;
        this.end = endPos;
        this.taken = takenPos;
    }

    public int getStart(){
        return this.start;
    }

    public int getEnd() {
        return end;
    }

    public int getTaken() {
        return taken;
    }

    public void setTaken(int takenPos) {
        if(takenPos == this.start || takenPos == this.end){
            return;
        }

        this.taken = takenPos;
    }

    @Override
    public String toString() {
    	
    	String toReturn = Integer.toString(start);
    	
    	if(end != Global.INVALID_INDEX) {
    		toReturn += " -> " + end;
    		
    		if(taken != Global.INVALID_INDEX) {
    			toReturn = " take " + taken;
    		}
    	}
    	
        return toReturn;
    }
}
