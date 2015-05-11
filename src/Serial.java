package src;

import fileSystem.utils.Buffer;
import fileSystem.utils.LogicalRecord;
import java.io.*;


/**
 * Serial Driver, representing a serial device.
 */
public class Serial {
	
	private RandomAccessFile f;
	private long filesize;
	public static final int BLOCKSIZE = 1024;
	int i, cnt_byte;
	
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
		cnt_byte = 0; readBlock();
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
     * It reads a whole string, used in the read_record method
     */
	public String read_string (int n) throws IOException {
		String readed_string = "";
		for (int i = 0; i < n; i++) {
			readed_string = readed_string+read_byte();
		}
		return readed_string;
	}
	
	public char read_byte() throws IOException {
		if (cnt_byte > 1023){
			readBlock();
			cnt_byte = 0;
		}
		byte[] block = readBlock();
		return (char) block[++cnt_byte];
	}
	
	/**
	 * Method used to read the records from the old file
	 */
	public Logical_Record read_record() throws IOException {
		
		Logical_Record record = new Logical_Record();

		record.setName(read_string(50));
		record.setCaffea(read_string(9));
		record.setVarietal(read_string(28));
		record.setOrigin(read_string(14));
		record.setRoasting(read_string(7));
		record.setProcess(read_string(7));
		
		for (int i = 115, j = 0; i<911; i = i+53, j++) {
			if(f.readLine().toCharArray()[i]==' ') break;
			record.setBarCodes(j, read_string(15));
			record.setFormats(j, read_string(12));
			record.setPrices(j, read_string(15));
			record.setMin_stocks(j, read_string(3));
			record.setStocks(j, read_string(4));
			record.setMax_stocks(j, read_string(4));
		}		
		return record;
	}

    /**
     * Moves pointer to the beginning (points to first block).
     */
	public void reset() throws IOException{
		f.seek(0);
	}
	
	
	/**
	 * It writes bytes in the new file
	 * @param block
	 * @param pos
	 * @param b
	 * @throws IOException
	 */
	public void writeBlock_byte(byte[] block, int pos, char b) throws IOException{
		if (pos < BLOCKSIZE) {
			f.write(block, pos, b);
		}
	}
	
	/**
	 * It writes strings in the new file
	 * @param block
	 * @param pos
	 * @param s
	 * @throws IOException
	 */
	public void writeBlock_string(byte[] block, int pos, String s) throws IOException{
		for (i = 0; i < s.length() ; i++){
			writeBlock_byte(block, pos, s.charAt(i));
		}
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