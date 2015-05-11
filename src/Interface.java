package src;

import java.io.IOException;
import java.util.Scanner;

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
    
    public void input(int references_num, byte[] byteArray) throws IOException {
 
    	String input, buffer="";
    	Scanner sc = new Scanner(System.in);
    	
    	System.out.println("Introduce a name");
    	input = sc.nextLine();
    	if (input.length() <= 50) buffer += input;
    	
    	System.out.println("Introduce a coffea");
    	input = sc.nextLine();
    	if (input.length() <= 9) buffer += input;
    	
    	System.out.println("Introduce a varietal");
    	input = sc.nextLine();
    	if (input.length() <= 28) buffer += input;

    	System.out.println("Introduce an origin");
    	input = sc.nextLine();
    	if (input.length() <= 14) buffer += input;

    	System.out.println("Introduce a roasting");
    	input = sc.nextLine();
    	if (input.length() <= 7) buffer += input;

    	System.out.println("Introduce a process");
    	input = sc.nextLine();
    	if (input.length() <= 7) buffer += input;
    	
    	// Here is where the references are introduced
    	for (int i= 0 ; i < references_num ; i++) {
    		
    		System.out.println("Introduce a barcode");
    		input = sc.nextLine();
        	if (input.length() <= 15) buffer += input;
        	
        	System.out.println("Introduce a format");
        	input = sc.nextLine();
        	if (input.length() <= 12) buffer += input;

        	System.out.println("Introduce a packaging");
        	input = sc.nextLine();
        	if (input.length() <= 15) buffer += input;

        	System.out.println("Introduce a price");
        	input = sc.nextLine();
        	if (input.length() <= 11) buffer += input;
        	
        	System.out.println("Introduce a minimum stock");
        	input = sc.nextLine();
        	if (input.length() <= 3) buffer += input;
        	
        	System.out.println("Introduce a stock");
        	input = sc.nextLine();
        	if (input.length() <= 4) buffer += input;
        	
        	System.out.println("Introduce a maximum stock");
        	input = sc.nextLine();
        	if (input.length() <= 4) buffer += input;
    	}
    	
    	byteArray = buffer.getBytes();
        serial.writeBlock(byteArray);
        sc.close();
    }

    public void output() throws IOException {
        System.out.println(serial.read_record());
    }
    
    public void output(Logical_Record record) throws IOException {
        System.out.println(record.toString());
    }

    public void iface() throws IOException{
    	
    	int references_num = 1;
    	boolean exit = false, stop = false;
    	String name = "", caffea = "", varietal = "", origin = "", roasting = "", process = "";
    	Scanner sc = new Scanner(System.in);
    	
    	while (exit == false) {
    		System.out.println("Enter one of the following options:" +
                    "\n 1. Open Archive" +
                    "\n 2. Insert" +
                    "\n 3. Import" +
                    "\n 4. Search" +
                    "\n 5. Next" +
                    "\n 6. End Search" +
                    "\n 7. Close archive" +
                    "\n 8. Exit");
            
            int option = Integer.parseInt(sc.next().charAt(0)+"");
            switch (option){
            
            	// Case in which we open a file
                case 1:
                	serial.openFile("Coffea", "R");
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
                	
                	byte [] new_record = new byte [115 + references_num*15];
                	input(references_num, new_record);
                    break;
                
                // Case in which we import from another file
                case 3:
                	
                	boolean EOF = false;
                	byte [] block = new byte [1024];
                	System.out.println("Introduce the name of the file from which you want to import the records:");
                	serial.openFile(sc.next(), "R");
                	
                	while (EOF = false) {
                		block = serial.readBlock();
                		
                		//if () {
                			//EOF = true;
                		//break;
                		//}
                		serial.writeBlock(block);
                	}
                	
                    break;
                	
                // Case in which we search a record
                case 4:
                	
                	System.out.println("Introducing a record to be found:");
                	System.out.println("Introduce a name");
                	if (sc.hasNext() == true) {
                		name = sc.next();
                	}
                	
                	System.out.println("Introduce a caffea");
                	if (sc.hasNext() == true) {
                		caffea = sc.next();
                	}
                	
                	System.out.println("Introduce a varietal");
                	if (sc.hasNext() == true) {
                		varietal = sc.next();
                	}

                	System.out.println("Introduce a origin");
                	if (sc.hasNext() == true) {
                		origin = sc.next();
                	}

                	System.out.println("Introduce a roasting");
                	if (sc.hasNext() == true) {
                		roasting = sc.next();
                	}

                	System.out.println("Introduce a process");
                	if (sc.hasNext() == true) {
                		process = sc.next();
                	}
                	
                	// TODO problema con los records que buscamos dejando espacios en blanco
                	while (stop == false) {
                		if (serial.read_record().getName() == name && 
                				serial.read_record().getCaffea() == caffea && 
                				serial.read_record().getVarietal() == varietal && 
                				serial.read_record().getOrigin() == origin && 
                				serial.read_record().getRoasting() == roasting && 
                				serial.read_record().getProcess() == process) {
                			output();
                			stop = true;
                		}
                		
                		// if (EOF == true) {
            				// System.out.println("There are no records fulfilling those conditions");
                		// }
                	}
                	
                	System.out.println("Do you want to see the next record?");
                    break;
                    
                // Case in which we search the next record
                case 5:
                	
                	stop = false;
                	while (stop == false) {
                		if (serial.read_record().getName() == name && 
                				serial.read_record().getCaffea() == caffea && 
                				serial.read_record().getVarietal() == varietal && 
                				serial.read_record().getOrigin() == origin && 
                				serial.read_record().getRoasting() == roasting && 
                				serial.read_record().getProcess() == process) {
                			output();
                			stop = true;
                		}
                		
                		// if (EOF == true) {
                			// System.out.println("There are no records fulfilling those conditions");
                		// }
                	}
                    break;
                    
                case 6:
                	// TODO
                    break;
                    
                // Case in which we open a file
                case 7:
                	serial.closeFile();
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
