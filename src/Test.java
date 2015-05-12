package src;

import java.util.Scanner;

/**
 * Created by blayhem on 12/5/15.
 */
public class Test {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String a="", b="", c="";

        String asdf = sc.nextLine();
        if(asdf.length()>0) a = asdf;
        asdf = sc.nextLine();
        if(asdf.length()>0) b = asdf;
        asdf = sc.nextLine();
        if(asdf.length()>0) c = asdf;
        sc.close();
        System.out.println("" + a + b + c);
    }
}
