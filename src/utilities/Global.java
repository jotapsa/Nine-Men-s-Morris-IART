package utilities;

import java.awt.Color;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Global {

    public final static int maximizerPlayer = 1;
    public final static int minimizerPlayer = 2;
    
    public final static Object[] GAME_MODES = {"Select...", "Player vs Player", "Player vs PC", "PC vs PC"};

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

    public final static int BOARD_BORDER = 30;
    public final static int BOARD_SPACING = 50;
    public final static int BOARD_WIDTH = 560;
    public final static int BOARD_HEIGHT = 560;
    public final static int BOARD_START_X = (Global.WIDTH - Global.BOARD_WIDTH) / 2;
    public final static int BOARD_START_Y = 100;
    public final static int ROCK_SPOT_RADIUS = 7;
    public final static int ROCK_RADIUS = 15;
    public final static Color BACKGROUND_COLOR = new Color(255,235,205);
    public final static Color P1_COLOR = new Color(255, 0, 0);
    public final static Color P2_COLOR = new Color(0, 0, 255);
    public final static Color LINES_COLOR = Color.BLACK;
    
   
//    WINDOW RELATED
    public final static int WIDTH = 720;
    public final static int HEIGHT = 720;
    
    public final static int BUTTONS_Y = 10;
    public final static int BUTTONS_HEIGHT = 25;
    public final static int BUTTONS_SPACING = 30;
    
//    ABOUT BUTTON
    public final static int ABOUT_BUTTON_WIDTH = 100;
    public final static int ABOUT_BUTTON_X = WIDTH - (WIDTH-BOARD_WIDTH)/2 - ABOUT_BUTTON_WIDTH;
    
//   START BUTTON 
    public final static int START_BUTTON_WIDTH = 130;
    public final static int START_BUTTON_X = ABOUT_BUTTON_X - START_BUTTON_WIDTH - BUTTONS_SPACING;
   
//   GAME MODE PICKER
    public final static int PICKER_WIDTH = 130;
    public final static int PICKER_START_X = START_BUTTON_X - PICKER_WIDTH - BUTTONS_SPACING;
    
//  GAME MODE PICKER LABEL
    public final static int LABEL_WIDTH = 76;
    public final static int LABEL_START_X = PICKER_START_X - LABEL_WIDTH - BUTTONS_SPACING / 4;
    
    
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
