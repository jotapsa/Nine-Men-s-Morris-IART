package utilities;

import java.util.Scanner;

public class Global {

    private static Scanner s = new Scanner(System.in);

    public static int askInt(String description, int min, int max){
        int i=0;

        do{
            System.out.println(description);
            i = s.nextInt();
        }while(i < min || i > max);

        return i;
    }
}