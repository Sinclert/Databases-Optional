package src;

import java.io.IOException;
import java.nio.channels.FileChannel;

public class FileMan {

    RABuffer buffer;
    FileChannel fc;
    Serial oldSerial = new Serial();
    Serial newSerial = new Serial();

    public FileMan() {
        buffer = new RABuffer();
    }

    /* Open the datafile identified be the given filename */
    public String open_archive(String fileName) throws IOException {
        if(fileName.equalsIgnoreCase("coffea.sql")) oldSerial.openFile(fileName, "rw");
        else if(fileName.equalsIgnoreCase("newCoffea.sql")) newSerial.openFile("newCoffea.sql", "rw");
        //buffer.openFile(fileName, "rw");
        return "File system " + fileName + " opened";
    }

    /* Closes all datafiles - Also invoked when exiting */
    public String close_archive() throws IOException {
        if (fc != null) {
            buffer.releasePagePolicy(fc, buffer.getNumberOfPages());
            buffer.close(fc);
        }
        return "File system closed";
    }

    /* Saves all modified pages from the buffeer into the disk (flush) */
    public String save_all() {
        buffer.save(fc);
        return "Files are correctly saved";
    }

    /* Inserts a record in currently open NEW archive */
    public String insert(Logical_Record new_record) throws IOException {
        newSerial.openFile("newCoffea.sql", "rw");
        while (true){
            new_record = newSerial.read_record();
            if(toString(new_record).contains("EOF")) {
                newSerial.writeBlock("EOF");
                break;
            }
        }
        return ("Inserting the new record");
    }

    /* Reads a serial file (old design) and inserts evey record read into the new datafile
     * @param: String old_filename: full path and name of the original (old) datafile
     */
    public String imports(String old_filename) throws IOException {
        open_archive(old_filename);
        open_archive("newCoffea.sql");
        Logical_Record record;

        while (true){
            record = oldSerial.read_record();
            if(toString(record).contains("#")) {
                newSerial.writeBlock("EOF");
                return ("Import " + old_filename + " method finished.");
            }
            newSerial.writeBlock(toString(record));
        }
    }

    /**
     * Begins a search in the open datafile; will seek  any record matching
     * the condition stated in buf_in, and will return first match in buf_out.
     *
     * @param: string: Archive subject to query (IGNORE)-.
     * @param: buffer buf_out: Buffer containing the search conditions,
     * @param: buffer buf_out: buffer to place first matching record
     */
    public Logical_Record search(String archive, BufferRecord buf_in, int times) throws IOException {

        Logical_Record buf_out = new Logical_Record();
        open_archive(archive);
        int counterF = 0, counterT = 0;

        while (true) {
            buf_out = oldSerial.read_record();
            for (int i = 0; i < 6; i++) {
                if (buf_in.getFields(i) && buf_out.getAttribute(i).contains(buf_in.getAttribute(i))) {
                    counterF++;
                }
                else if (buf_in.getFields(i) && !buf_out.getAttribute(i).equals(buf_in.getAttribute(i))) continue;
            }

            if (buf_in.countFields() == counterF) counterT++;
            if (counterT == times) return buf_out;

            if (toString(buf_out).contains("#")) {
                System.out.println("There are no records fulfilling those conditions");
                break;
            }
        }
        return buf_out;
    }

    /* Retrieves next record matching current search  */
    public String next(BufferRecord buf_out) {
        return ("Next method finished (not implemented yet");
    }

    public String toString(Logical_Record record) {
        String references = "";
        boolean write;
        for (int i = 0; i < 15; i++) {
            write = true;
            for (int j = 0; j < 7; j++) {
                if(record.getRefferences(j, i) == null) write = false;
            }
            if (!write) break;

            references += record.getBarCodes()[i] + record.getFormats()[i] + record.getPackagings()[i] + record.getPrices()[i]
                    + record.getMin_stocks()[i] + record.getStocks()[i] + record.getMax_stocks()[i];
        }

        return record.getName() + record.getCaffea()
                + record.getVarietal() + record.getOrigin()
                + record.getRoasting() + record.getProcess()
                + references;
    }

    private Logical_Record toLogicalRecord(String string) throws IOException {

        Logical_Record record = new Logical_Record();

        record.setName(string.substring(0, 49));
        record.setCaffea(string.substring(50, 58));
        record.setVarietal(string.substring(59, 86));
        record.setOrigin(string.substring(87, 100));
        record.setRoasting(string.substring(101, 107));
        record.setProcess(string.substring(108, 114));

        for (int i = 115, j = 0; i < 911; j++) {
            if (string.charAt(i) == ' ') break;
            record.setBarCodes(j, string.substring(i, i + 14));
            i += 15;
            record.setFormats(j, string.substring(i, i + 11));
            i += 12;
            record.setPackagings(j, string.substring(i, i + 14));
            i += 15;
            record.setPrices(j, string.substring(i, i + 10));
            i += 11;
            record.setMin_stocks(j, string.substring(i, i + 2));
            i += 3;
            record.setStocks(j, string.substring(i, i + 3));
            i += 4;
            record.setMax_stocks(j, string.substring(i, i + 3));
            i += 4;
        }
        return record;
    }

    private int readFFP(int bucket) {
        return 0;
    }

    private void writeFFP(int bucket, short FFP) {
    }
}