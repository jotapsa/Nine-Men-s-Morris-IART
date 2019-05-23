package cli;

import logic.GameState;

import java.util.HashMap;

public class GameView {

    public static void render(GameState gameState){
        System.out.format("┏━━━┓             ┏━━━┓             ┏━━━┓        ┏━━━┓             ┏━━━┓             ┏━━━┓\n" +
                "┃ %c ┣━━━━━━━━━━━━━┫ %c ┣━━━━━━━━━━━━━┫ %c ┃        ┃ 0 ┣━━━━━━━━━━━━━┫ 1 ┣━━━━━━━━━━━━━┫ 2 ┃\n" +
                "┗━┳━┛             ┗━┳━┛             ┗━┳━┛        ┗━┳━┛             ┗━┳━┛             ┗━┳━┛\n" +
                "  ┃   ┏━━━┓       ┏━┻━┓       ┏━━━┓   ┃            ┃   ┏━━━┓       ┏━┻━┓       ┏━━━┓   ┃\n" +
                "  ┃   ┃ %c ┣━━━━━━━┫ %c ┣━━━━━━━┫ %c ┃   ┃            ┃   ┃ 3 ┣━━━━━━━┫ 4 ┣━━━━━━━┫ 5 ┃   ┃\n" +
                "  ┃   ┗━┳━┛       ┗━┳━┛       ┗━┳━┛   ┃            ┃   ┗━┳━┛       ┗━┳━┛       ┗━┳━┛   ┃\n" +
                "  ┃     ┃   ┏━━━┓ ┏━┻━┓ ┏━━━┓   ┃     ┃            ┃     ┃   ┏━━━┓ ┏━┻━┓ ┏━━━┓   ┃     ┃\n" +
                "  ┃     ┃   ┃ %c ┣━┫ %c ┣━┫ %c ┃   ┃     ┃            ┃     ┃   ┃ 6 ┣━┫ 7 ┣━┫ 8 ┃   ┃     ┃\n" +
                "  ┃     ┃   ┗━┳━┛ ┗━━━┛ ┗━┳━┛   ┃     ┃            ┃     ┃   ┗━┳━┛ ┗━━━┛ ┗━┳━┛   ┃     ┃\n" +
                "┏━┻━┓ ┏━┻━┓ ┏━┻━┓       ┏━┻━┓ ┏━┻━┓ ┏━┻━┓        ┏━┻━┓ ┏━┻━┓ ┏━┻━┓       ┏━┻━┓ ┏━┻━┓ ┏━┻━┓\n" +
                "┃ %c ┣━┫ %c ┣━┫ %c ┃       ┃ %c ┣━┫ %c ┣━┫ %c ┃        ┃ 9 ┣━┫ 10┣━┫ 11┃       ┃ 12┣━┫ 13┣━┫ 14┃\n" +
                "┗━┳━┛ ┗━┳━┛ ┗━┳━┛       ┗━┳━┛ ┗━┳━┛ ┗━┳━┛        ┗━┳━┛ ┗━┳━┛ ┗━┳━┛       ┗━┳━┛ ┗━┳━┛ ┗━┳━┛\n" +
                "  ┃     ┃   ┏━┻━┓ ┏━━━┓ ┏━┻━┓   ┃     ┃            ┃     ┃   ┏━┻━┓ ┏━━━┓ ┏━┻━┓   ┃     ┃\n" +
                "  ┃     ┃   ┃ %c ┣━┫ %c ┣━┫ %c ┃   ┃     ┃            ┃     ┃   ┃ 15┣━┫ 16┣━┫ 17┃   ┃     ┃\n" +
                "  ┃     ┃   ┗━━━┛ ┗━┳━┛ ┗━━━┛   ┃     ┃            ┃     ┃   ┗━━━┛ ┗━┳━┛ ┗━━━┛   ┃     ┃\n" +
                "  ┃   ┏━┻━┓       ┏━┻━┓       ┏━┻━┓   ┃            ┃   ┏━┻━┓       ┏━┻━┓       ┏━┻━┓   ┃\n" +
                "  ┃   ┃ %c ┣━━━━━━━┫ %c ┣━━━━━━━┫ %c ┃   ┃            ┃   ┃ 18┣━━━━━━━┫ 19┣━━━━━━━┫ 20┃   ┃\n" +
                "  ┃   ┗━━━┛       ┗━┳━┛       ┗━━━┛   ┃            ┃   ┗━━━┛       ┗━┳━┛       ┗━━━┛   ┃\n" +
                "┏━┻━┓             ┏━┻━┓             ┏━┻━┓        ┏━┻━┓             ┏━┻━┓             ┏━┻━┓\n" +
                "┃ %c ┣━━━━━━━━━━━━━┫ %c ┣━━━━━━━━━━━━━┫ %c ┃        ┃ 21┣━━━━━━━━━━━━━┫ 22┣━━━━━━━━━━━━━┫ 23┃\n" +
                "┗━━━┛             ┗━━━┛             ┗━━━┛        ┗━━━┛             ┗━━━┛             ┗━━━┛\n",
                posToChar.get(gameState.getPos(0)),
                posToChar.get(gameState.getPos(1)),
                posToChar.get(gameState.getPos(2)),
                posToChar.get(gameState.getPos(3)),
                posToChar.get(gameState.getPos(4)),
                posToChar.get(gameState.getPos(5)),
                posToChar.get(gameState.getPos(6)),
                posToChar.get(gameState.getPos(7)),
                posToChar.get(gameState.getPos(8)),
                posToChar.get(gameState.getPos(9)),
                posToChar.get(gameState.getPos(10)),
                posToChar.get(gameState.getPos(11)),
                posToChar.get(gameState.getPos(12)),
                posToChar.get(gameState.getPos(13)),
                posToChar.get(gameState.getPos(14)),
                posToChar.get(gameState.getPos(15)),
                posToChar.get(gameState.getPos(16)),
                posToChar.get(gameState.getPos(17)),
                posToChar.get(gameState.getPos(18)),
                posToChar.get(gameState.getPos(19)),
                posToChar.get(gameState.getPos(20)),
                posToChar.get(gameState.getPos(21)),
                posToChar.get(gameState.getPos(22)),
                posToChar.get(gameState.getPos(23)));

//        System.out.println("Last move - " + gameState.getLastMove());
    }

    public static HashMap<Integer, Character> posToChar = new HashMap<>();
    static{
        posToChar.put(0, ' ');
        posToChar.put(1, 'X');
        posToChar.put(2, 'O');
    }

}
