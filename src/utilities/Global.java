package utilities;

import java.util.NoSuchElementException;
import java.util.Scanner;

public class Global {

    private static Scanner s = new Scanner(System.in);

    public static int askInt(String description, int min, int max){
        int i=0;

        do{
            try{
                System.out.println(description);
                i = s.nextInt();
            }
            catch (NoSuchElementException e){
                System.exit(-1);
            }
        }while(i < min || i > max);

        return i;
    }
}