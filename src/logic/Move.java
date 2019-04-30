package logic;

public class Move {
    private int start = -1;
    private int end = -1;
    private int taken = -1;

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
}
