import java.io.*;

/**
 * Serial Driver, representing a serial device.
 */
public class Serial {
	
	private RandomAccessFile f;
	private long filesize;
	public static final int BLOCKSIZE = 1024;
	
    /**     
     * Opens the file in the specified mode, pointing to first block.
     *@param file nombre del fichero a abrir.
     *@param mode,, mode = "R" -> read ; <br/> "W" -> write ; <br/> "RW" -> read-write
     */
	public void openFile(String file, String mode) throws IOException{
		if(mode.equals("R")){
			f = new RandomAccessFile(file,"r");
			filesize = f.length();
		}
		else if(mode.equals("W")){
			f = new RandomAccessFile(file,"w");
			filesize = f.length();
		}
		else if(mode.equals("RW")){
			f = new RandomAccessFile(file,"rw");
			filesize = f.length();
		}
		else{
			throw new IOException("Wrong mode");
		}
	}
	
	
    /** Close the file (access ends)*/
	public void closeFile() throws IOException{
		f.close();
	}

	
    /**
     * Reads currently pointed block. Then, the following block is pointed.<br/>
     * The file must be open in read mode (either "R" or "RW").<br/>
     */
	public byte[] readBlock() throws IOException{
		byte[] block = new byte[BLOCKSIZE];
		f.read(block);
		return block;
	}
	
	
    /**
     * Moves pointer to the beginning (points to first block).
     */
	public void reset() throws IOException{
		f.seek(0);
	}

	
    /** Writes a block (1024 bytes) on currently pointed position.<br/>
    * The file must be open in write mode (either "W" or "RW").<br/>
    *@param block : the array of bytes (1024) to be written 
    */
	public void writeBlock(byte[] block) throws IOException{
		f.write(block);
	}

	
    /** Provides current number of blocks in the file (n).*/
	public long fileSize() throws IOException{
		long res = f.length() + (BLOCKSIZE -1);
		res /= BLOCKSIZE;
		return res;
	}	
}