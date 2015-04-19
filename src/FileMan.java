package src;

import java.util.*;

public class FileMan {        
   
    /* Open the datafile identified be the given filename */
    public String open_archive(String filename) {
        return(" Method open system " + filename + " is finished (not implemented yet)");
    }
    
    /* Closes all datafiles - Also invoked when exiting */
    public String close_archive() {
        return(" Method close system is finished(not implemented yet)");
    }
    
    /* Saves all modified pages from the buffeer into the disk (flush) */
    public String save_all() {
        return(" Method save all is finished(not implemented yet)");
    }
    

    /* Inserts a record in currently open NEW archive */
    public String insert(BufferRecord buf_in) {
        return("Inserting into new archive; method finished (not implemented yet)");
    }

    /* Reads a serial file (old design) and inserts evey record read into the new datafile
     * @param: String old_filename: full path and name of the original (old) datafile
     */
    public String import (String old_filename) {
        return("Import " + old_filename +" method finished (not implemented yet)");
    }
  

    /* Begins a search in the open datafile; will seek  any record matching 
	 *the condition stated in buf_in, and will return first match in buf_out.    
    * @param: string: Archive subject to query (IGNORE)-.
    * @param: buffer buf_out: Buffer containing the search conditions, 
    * @param: buffer buf_out: buffer to place first matching record
    */ 
    public String search(String archive, BufferRecord buf_in, BufferRecord buf_out) {                      
        return("Search " + archive +" method finished (not implemented yet)");
    }
    
    /* Retrieves next record matching current search  */
    public String siguiente(BufferRecord buf_out) {              
        return("Next method finished (not implemented yet");
    }
    
}