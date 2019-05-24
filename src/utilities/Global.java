package utilities;

import java.awt.Color;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Global {

    public enum decFunc {
        MiniMax,
        AlphaBeta
    }

    public final static int maximizerPlayer = 1;
    public final static int minimizerPlayer = 2;
    public final static int pc1PlacingDepth = 3;
    public final static int pc2PlacingDepth = 3;
    public final static int pc1Depth = 5;
    public final static int pc2Depth = 5;
    public final static decFunc pc1DecFunc = decFunc.AlphaBeta;
    public final static decFunc pc2DecFunc = decFunc.AlphaBeta;

    public final static int INVALID_INDEX = -1;

    public final static Object[] GAME_MODES = {"Select...", "Player vs Player", "Player vs PC", "PC vs PC"};

//  WINDOW RELATED
    public final static int WIDTH = 720;
    public final static int HEIGHT = 800;

    public final static int BUTTONS_Y = 10;
    public final static int BUTTONS_HEIGHT = 25;
    public final static int BUTTONS_SPACING = 30;

//    GAME BOARD RELATED
    public final static int HL1 = 0;
    public final static int HL2 = 1;
    public final static int HL3 = 2;
    public final static int HL4 = 3;
    public final static int HL5 = 4;
    public final static int HL6 = 5;
    public final static int HL7 = 6;

    public final static int VL1 = 0;
    public final static int VL2 = 1;
    public final static int VL3 = 2;
    public final static int VL4 = 3;
    public final static int VL5 = 4;
    public final static int VL6 = 5;
    public final static int VL7 = 6;

    public final static int BOARD_WIDTH = WIDTH - 60;
    public final static int BOARD_HEIGHT = 700;
    public final static int BOARD_BORDER_X = (WIDTH - BOARD_WIDTH) /2;
    public final static int BOARD_BORDER_Y = 75;
    public final static int BOARD_SPACING = (BOARD_WIDTH - 2 * BOARD_BORDER_X) /6;
    public final static int BOARD_START_X = (WIDTH - BOARD_WIDTH) / 2;
    public final static int BOARD_START_Y = 50;
    public final static int ROCK_SPOT_RADIUS = 10;
    public final static int ROCK_RADIUS = 40;
    public final static int P1_N_AVAILABLE_ROCKS_X = BOARD_WIDTH / 4 + 50;
    public final static int P2_N_AVAILABLE_ROCKS_X = BOARD_WIDTH / 2 + BOARD_WIDTH / 4 - 50;
    public final static int N_AVAILABLE_ROCKS_Y = 15;
    public final static Color BACKGROUND_COLOR = new Color(255,235,205);
    public final static Color P1_COLOR = new Color(255, 0, 0);
    public final static Color P2_COLOR = new Color(0, 0, 255);
    public final static Color SELECTED_ROCK_COLOR = new Color(255, 255, 0);
    public final static Color LINES_COLOR = Color.BLACK;

//    ABOUT BUTTON
    public final static int ABOUT_BUTTON_WIDTH = 100;
    public final static int ABOUT_BUTTON_X = WIDTH - (WIDTH - BOARD_WIDTH) / 2 - ABOUT_BUTTON_WIDTH;

//   START BUTTON
    public final static int START_BUTTON_WIDTH = 130;
    public final static int START_BUTTON_X = ABOUT_BUTTON_X - START_BUTTON_WIDTH - BUTTONS_SPACING;

//   GAME MODE PICKER
    public final static int PICKER_WIDTH = 150;
    public final static int PICKER_START_X = START_BUTTON_X - PICKER_WIDTH - BUTTONS_SPACING;
    
//   GAME MODE PICKER LABEL
    public final static int LABEL_WIDTH = 76;
    public final static int LABEL_START_X = PICKER_START_X - LABEL_WIDTH - BUTTONS_SPACING / 4;
      
//    HINT BUTTON
    public final static int HINT_BUTTON_WIDTH = Global.START_BUTTON_WIDTH - 50;
    public final static int HINT_BUTTON_X = BOARD_START_X;
    
//    HINT LABEL
    public final static int HINT_LABEL_WIDTH = 50;
    public final static int HINT_LABEL_X = HINT_BUTTON_X + HINT_BUTTON_WIDTH + BUTTONS_SPACING/3;
    
    
    private static Scanner s = new Scanner(System.in);

    public static int askInt(String description, int min, int max){
        int i=0;

        do{
            try{
                System.out.print(description);
                i = s.nextInt();
            }
            catch (NoSuchElementException e){
                System.exit(-1);
            }
        }while(i < min || i > max);

        return i;
    }

    public static void promptEnterKey(){
        System.out.println("Press \"ENTER\" to continue...");
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
    }
}
