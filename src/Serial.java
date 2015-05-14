package src;

import java.io.*;
import java.lang.reflect.Array;
import java.util.Arrays;


/**
 * Serial Driver, representing a serial device.
 */
public class Serial {

    private RandomAccessFile f;
    private long filesize;
    public static final int BLOCKSIZE = 1022;
    byte[] ffps = new byte[2];
    int cnt_byte = 0;
    byte[] block = new byte[BLOCKSIZE];

    /**
     * Opens the file in the specified mode, pointing to first block.
     *
     * @param file   nombre del fichero a abrir.
     * @param mode,, mode = "R" -> read ; <br/> "W" -> write ; <br/> "RW" -> read-write
     */
    public void openFile(String file, String mode) throws IOException {
        if (mode.equalsIgnoreCase("R")) {
            f = new RandomAccessFile(file, "r");
            filesize = f.length();
        } else if (mode.equalsIgnoreCase("W")) {
            f = new RandomAccessFile(file, "w");
            filesize = f.length();
        } else if (mode.equalsIgnoreCase("RW")) {
            f = new RandomAccessFile(file, "rw");
            filesize = f.length();
        } else {
            throw new IOException("Wrong mode");
        }
        cnt_byte = 0;
        readBlock();
    }

    /**
     * Close the file (access ends)
     */
    public void closeFile() throws IOException {
        f.close();
    }

    /**
     * Reads currently pointed block. Then, the following block is pointed.<br/>
     * The file must be open in read mode (either "R" or "RW").<br/>
     */
    public byte[] readBlock() throws IOException {
        byte[] block = new byte[BLOCKSIZE];
        f.read(block);
        return block;
    }

    /**
     * It reads a whole string, used in the read_record method
     */
    public String read_string(int n) throws IOException {
        String readed_string = "";
        char c;
        int spaces = 0;
        for (int i = 0; i < n; i++) {
            c = read_byte();
            if(c == ' ') spaces++;
            if(c != ' ') spaces--;
            if(spaces<2)readed_string = readed_string + c;
        }
        return readed_string;
    }

    public char read_byte() throws IOException {
        boolean reset = false;
        if (cnt_byte > 1021) {
            block = readBlock();
            cnt_byte = 0;
            reset = true;
        }
        if(cnt_byte == 0 && !reset) block = readBlock();
        return (char) block[cnt_byte++];
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

        String barCode;
        for (int j = 0; j < 15; j++) {
            barCode = read_string(15);
            if (barCode.contains(" ") || record.getBarCodes()[j] == null) break;

            record.setBarCodes(j, barCode);
            record.setFormats(j, read_string(12));
            record.setPackagings(j, read_string(15));
            record.setPrices(j, read_string(11));
            record.setMin_stocks(j, read_string(3));
            record.setStocks(j, read_string(4));
            record.setMax_stocks(j, read_string(4));
            //for (int i = 0; i < 7; i++) {if(record.getRefferences(i, j) == null) return record;}
        }
        return record;
    }

    /**
     * Moves pointer to the beginning (points to first block).
     */
    public void reset() throws IOException {
        f.seek(0);
    }

    /**
     * It writes bytes in the new file
     *
     * @param string
     * @param pos
     * @param b
     * @throws IOException
     */
    public void writeBlock_byte(String string, int pos, char b) throws IOException {
        if (pos < BLOCKSIZE) {
            byte[] block = new byte[string.length()];
            for (int i = 0; i < string.length(); i++) {
                block[i] = (byte) string.toCharArray()[i];
            }
            f.write(block, pos, b);
        }
    }

    /**
     * It writes strings in the new file
     *
     * @param block
     * @param pos
     * @param s
     * @throws IOException
     */
    public void writeBlock_string(String block, int pos, String s) throws IOException {
        for (int i = 0; i < s.length(); i++) {
            writeBlock_byte(block, pos, s.charAt(i));
        }
    }

    /**
     * Writes a block (1024 bytes) on currently pointed position.<br/>
     * The file must be open in write mode (either "W" or "RW").<br/>
     *
     * @param block : the array of bytes (1024) to be written
     */
    public void writeBlock(String block) throws IOException {
        f.writeBytes(block);
    }

    /**
     * Provides current number of blocks in the file (n).
     */
    public long fileSize() throws IOException {
        long res = f.length() + (BLOCKSIZE - 1);
        res /= BLOCKSIZE;
        return res;
    }


    public int readFFP(int bucket) throws IOException {
        byte[] block = new byte[BLOCKSIZE + 2];
        f.read(block);
        return bucket;
    }

    public int writeFFP(int bucket, int FFP) throws IOException {
        return bucket;
    }

    public int FFPtoInt(byte[] ffps) {
        int[] array = new int[10];

        int ffps0 = (int) ffps[0] + 128;
        for (int i = 1; i >= 0; i--) {
            array[i] = ffps0 % 2;
            ffps0 = ffps0 / 2;
        }

        int ffps1 = (int) ffps[1] + 128;
        for (int i = 9; i > 1; i--) {
            array[i] = ffps1 % 2;
            ffps1 = ffps1 / 2;
        }
        /*
         * 0 1 2 3 4 5 6 7 8 9
         * 0 - 128 - 256
         * -128 - 0 - 128
         * sumamos 128 para ajustar la escala hasta 256
         */
        int sum = 0;
        for (int i = 9, j = 0; i >= 0; i--, j++) {
            if (i != 2) sum += array[i] * (Math.pow(2, j));
            else j--;
        }
        return sum;
    }

    public byte[] intToFFP(int number) {
        String inttoS = Integer.toBinaryString(number);
        int[] array = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0};

        for (int i = 0, j = inttoS.length() - 1, k = 9; i < inttoS.length(); i++, j--, k--) {
            if (k != 2) array[k] = Integer.parseInt("" + inttoS.charAt(j));
            else {
                i--;
                j++;
            }    //intentamos replicar el array anterior, donde concatenamos los dos bytes
        }
        array[2] = 1;           //problemas con el signo, mas o menos
        int ffps21 = 0;
        for (int i = 1, j = 0; i >= 0; i--, j++) {
            ffps21 += array[i] * (Math.pow(2, j));
        }
        //ffps21 -= 128;
        int ffps22 = 0;
        for (int i = 9, j = 0; i > 2; i--, j++) {
            ffps22 += array[i] * (Math.pow(2, j));
        }
        //if(ffps22 < 128)ffps22 -= 128;
        return new byte[]{(byte) ffps21, (byte) ffps22};
    }
}