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
    
    public void input(int references_num, byte[] bytearray) throws IOException {
    	
    	int i = 0;
    	Scanner sc = new Scanner(System.in);
    	
    	System.out.println("Introduce a name");
    	for (i = 0 ; i < 50 ; i++) {
    		bytearray[i] = sc.nextByte();
    	}
    	
    	System.out.println("Introduce a coffea");
    	for (i = 0 ; i < 9 ; i++) {
    		bytearray[i] = sc.nextByte();
    	}
    	
    	System.out.println("Introduce a varietal");
    	for (i = 0 ; i < 28 ; i++) {
    		bytearray[i] = sc.nextByte();
    	}

    	System.out.println("Introduce an origin");
    	for (i = 0 ; i < 14 ; i++) {
    		bytearray[i] = sc.nextByte();
    	}

    	System.out.println("Introduce a roasting");
    	for (i = 0 ; i < 7 ; i++) {
    		bytearray[i] = sc.nextByte();
    	}

    	System.out.println("Introduce a process");
    	for (i = 0 ; i < 7 ; i++) {
    		bytearray[i] = sc.nextByte();
    	}
    	
    	// Here is where the references are introduced
    	for ( i= 0 ; i < references_num ; i++) {
    		
    		System.out.println("Introduce a barcode");
        	for (i = 0 ; i < 15 ; i++) {
        		bytearray[i] = sc.nextByte();
        	}

        	System.out.println("Introduce a format");
        	for (i = 0 ; i < 12 ; i++) {
        		bytearray[i] = sc.nextByte();
        	}

        	System.out.println("Introduce a packaging");
        	for (i = 0 ; i < 15 ; i++) {
        		bytearray[i] = sc.nextByte();
        	}

        	System.out.println("Introduce a price");
        	for (i = 0 ; i < 11 ; i++) {
        		bytearray[i] = sc.nextByte();
        	}
        	
        	System.out.println("Introduce a minimum stock");
        	for (i = 0 ; i < 3 ; i++) {
        		bytearray[i] = sc.nextByte();
        	}
        	
        	System.out.println("Introduce a stock");
        	for (i = 0 ; i < 4 ; i++) {
        		bytearray[i] = sc.nextByte();
        	}
        	
        	System.out.println("Introduce a maximum stock");
        	for (i = 0 ; i < 4 ; i++) {
        		bytearray[i] = sc.nextByte();
        	}
    	}
    	
        serial.writeBlock(bytearray);
        sc.close();
    }

    public void output() throws IOException {
        System.out.println(serial.read_record());
    }

    public void iface() throws IOException{
    	
    	int references_num = 1;
    	boolean exit = false, stop = false, valid_number;
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
                	
                	valid_number = false;
                	System.out.println("Process to introduce a new record:");
                	while (valid_number == false) {
                		System.out.println("How many references do you want to introduce for this record?");
                		references_num = sc.nextInt();
                		if (references_num > 1 && references_num < 15) {
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
