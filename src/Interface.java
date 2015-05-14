package src;

import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

/**
 * The interface should enable you to interact with the application (some menus are enough; you don't need a GUI ).
 * This class should include methods input (prompting the user to enter a new record) and output (displaying a record on the screen).
 * Besides, menus for interaction could be the following:
 * - Open archive
 * - Insert
 * - Import
 * - Search
 * - Next
 * - End Search
 * - [Select]
 * - Close archive
 * - Exit
 */

public class Interface {

    Serial serial = new Serial();
    FileMan fileman = new FileMan();

    public Logical_Record input(int references_num, Logical_Record record) throws IOException {

        String input;
        Scanner sc = new Scanner(System.in);

        System.out.println("Introduce a name");
        input = sc.nextLine();
        if (input.length() <= 50) record.setName(input);

        System.out.println("Introduce a coffea");
        input = sc.nextLine();
        if (input.length() <= 9) record.setCaffea(input);

        System.out.println("Introduce a varietal");
        input = sc.nextLine();
        if (input.length() <= 28) record.setVarietal(input);

        System.out.println("Introduce an origin");
        input = sc.nextLine();
        if (input.length() <= 14) record.setOrigin(input);

        System.out.println("Introduce a roasting");
        input = sc.nextLine();
        if (input.length() <= 7) record.setRoasting(input);

        System.out.println("Introduce a process");
        input = sc.nextLine();
        if (input.length() <= 7) record.setProcess(input);

        // Here is where the references are introduced
        for (int i = 0; i < references_num; i++) {

            System.out.println("Introduce a barcode");
            input = sc.nextLine();
            if (input.length() <= 15) record.setBarCodes(i, input);

            System.out.println("Introduce a format");
            input = sc.nextLine();
            if (input.length() <= 12) record.setFormats(i, input);

            System.out.println("Introduce a packaging");
            input = sc.nextLine();
            if (input.length() <= 15) record.setPackagings(i, input);

            System.out.println("Introduce a price");
            input = sc.nextLine();
            if (input.length() <= 11) record.setPrices(i, input);

            System.out.println("Introduce a minimum stock");
            input = sc.nextLine();
            if (input.length() <= 3) record.setMin_stocks(i, input);

            System.out.println("Introduce a stock");
            input = sc.nextLine();
            if (input.length() <= 4) record.setStocks(i, input);

            System.out.println("Introduce a maximum stock");
            input = sc.nextLine();
            if (input.length() <= 4) record.setMax_stocks(i, input);
        }

        sc.close();
        return record;
    }

    public void output(Logical_Record record) throws IOException {
       if (fileman.toString(record).length() >= 168){
            System.out.println(fileman.toString(record));
        }
    }

    public void iface() throws IOException {

        int references_num = 1, times = 1;
        boolean exit = false;
        BufferRecord buf_in = new BufferRecord();
        Scanner sc = new Scanner(System.in);

        while (!exit) {
            System.out.println("Enter one of the following options:" +
                    "\n 1. Open Archive" +
                    "\n 2. Insert" +
                    "\n 3. Import" +
                    "\n 4. Search" +
                    "\n 5. Next" +
                    "\n 6. End Search" +
                    "\n 7. Close archive" +
                    "\n 8. Exit");

            int option = Integer.parseInt(sc.next().charAt(0) + "");
            switch (option) {

                // Case in which we open a file
                case 1:
                    fileman.open_archive("newCoffea.sql");
                    break;

                // Case in which we insert a record
                case 2:

                    boolean valid_number = false;
                    System.out.println("Process to introduce a new record:");
                    while (!valid_number) {
                        System.out.println("How many references do you want to introduce for this record?");
                        references_num = sc.nextInt();
                        if (references_num > 0 && references_num < 16) {
                            valid_number = true;
                        }
                    }

                    Logical_Record new_record = new Logical_Record();
                    new_record = input(references_num, new_record);

                    fileman.insert(new_record);
                    break;

                // Case in which we import from another file
                case 3:

                    System.out.println("Introduce the name of the file from which you want to import the records:");
                    System.out.println(fileman.imports(sc.next()));
                    break;

                // Case in which we search a record
                case 4:
                    String input;
                    times = 1;
                    for (int i = 0; i < 6; i++) {
                        buf_in.setFields(i, false);
                    } //set fields to 0 for next search and on

                    System.out.println("Introducing a record to be found:");

                    System.out.println("Introduce a name");
                    input = sc.nextLine();
                    input = sc.nextLine();
                    if (input.length() > 0 && input.length() <= 50) {
                        buf_in.setName(input);
                        buf_in.setFields(0, true);
                    }

                    System.out.println("Introduce a caffea");
                    input = sc.nextLine();
                    if (input.length() > 0 && input.length() <= 9) {
                        buf_in.setCaffea(input);
                        buf_in.setFields(1, true);
                    }

                    System.out.println("Introduce a varietal");
                    input = sc.nextLine();
                    if (input.length() > 0 && input.length() <= 28) {
                        buf_in.setVarietal(input);
                        buf_in.setFields(2, true);
                    }

                    System.out.println("Introduce a origin");
                    input = sc.nextLine();
                    if (input.length() > 0 && input.length() <= 14) {
                        buf_in.setOrigin(input);
                        buf_in.setFields(3, true);
                    }

                    System.out.println("Introduce a roasting");
                    input = sc.nextLine();
                    if (input.length() > 0 && input.length() <= 7) {
                        buf_in.setRoasting(input);
                        buf_in.setFields(4, true);
                    }

                    System.out.println("Introduce a process");
                    input = sc.nextLine();
                    if (input.length() > 0 && input.length() <= 7) {
                        buf_in.setProcess(input);
                        buf_in.setFields(5, true);
                    }

                    output(fileman.search("coffea.sql", buf_in, times));
                    break;


                // Case in which we search the next record
                case 5:
                    if (!(buf_in.countFields() > 0)) break;
                    times++;
                    output(fileman.search("Coffea", buf_in, times));
                    break;

                // Case in which the search is finished
                case 6:
                    times = 1;
                    for (int i = 0; i < 6; i++) buf_in.setAttribute(i, "");
                    break;

                // Case in which we open a file
                case 7:
                    fileman.close_archive();
                    break;

                // Case in which we exit the menu
                case 8:
                    exit = true;
                    break;

                // Wrong input
                default:
                    System.out.println("Wrong option, please select a correct option");
                    break;
            }
        }

        sc.close();
    }
}
