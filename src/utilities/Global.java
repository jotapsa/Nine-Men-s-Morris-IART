package utilities;

import java.util.NoSuchElementException;
import java.util.Scanner;

public class Global {

    public final static int maximizerPlayer = 1;
    public final static int minimizerPlayer = 2;

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
