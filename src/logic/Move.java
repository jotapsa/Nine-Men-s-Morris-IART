package logic;

public class Move {
    private int start;
    private int end;
    private int taken;

    public Move(int startPos){
        this.start = startPos;
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
}
