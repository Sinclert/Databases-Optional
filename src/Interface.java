package src;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by blayhem on 19/4/15.
 */

/*
The interface should enable you to interact with the application (some menus are enough; you don't need a GUI ).
This class should include methods input (prompting the user to enter a new record) and output (displaying a record on the screen).
Besides, menus for interaction could be the following:
- Open archive
- Insert
- Import
- Search
- Next
- End Search
- [Select]
- Close archive
- Exit
 */

public class Interface {
    Serial serial = new Serial();
    public void input(byte[] bytearray) throws IOException {
        serial.writeBlock(bytearray);
    }

    public void output() throws IOException {
        System.out.println(serial.read_record());
    }

    public void iface(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter one of the following options: " +
                "\n 1. Open Archive" +
                "\n 2. Insert" +
                "\n 3. Import" +
                "\n 4. Search" +
                "\n 5. Next" +
                "\n 6. End Search" +
                "\n 7. Select" +
                "\n 8. Close archive" +
                "\n 9. Exit");
        int option = Integer.parseInt(sc.next().charAt(0)+"");
        switch (option){
            case 1:
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;
            case 5:
                break;
            case 6:
                break;
            case 7:
                break;
            case 8:
                break;
            case 9:
                break;
            default:
                break;
        }
    }
}
