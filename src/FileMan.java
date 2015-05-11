package src;

import java.io.IOException;
import java.util.SplittableRandom;

public class FileMan {
    final int blockSize = 1022;

    /* Open the datafile identified be the given filename */
    public String open_archive(String filename) {
        return (" Method open system " + filename + " is finished (not implemented yet)");
    }

    /* Closes all datafiles - Also invoked when exiting */
    public String close_archive() {
        return (" Method close system is finished(not implemented yet)");
    }

    /* Saves all modified pages from the buffeer into the disk (flush) */
    public String save_all() {
        return (" Method save all is finished(not implemented yet)");
    }

    /* Inserts a record in currently open NEW archive */
    public String insert(BufferRecord buf_in) {
        return ("Inserting into new archive; method finished (not implemented yet)");
    }

    /* Reads a serial file (old design) and inserts evey record read into the new datafile
     * @param: String old_filename: full path and name of the original (old) datafile
     */
    public String imports(String old_filename) {
        return ("Import " + old_filename + " method finished (not implemented yet)");
    }

    /**
     * Begins a search in the open datafile; will seek  any record matching
     * the condition stated in buf_in, and will return first match in buf_out.
     *
     * @param: string: Archive subject to query (IGNORE)-.
     * @param: buffer buf_out: Buffer containing the search conditions,
     * @param: buffer buf_out: buffer to place first matching record
     */
    public BufferRecord search(String archive, BufferRecord buf_in, BufferRecord buf_out) throws IOException {
        Serial serial = new Serial();
        // TODO problema con los records que buscamos dejando espacios en blanco
        while (true) {

            if (serial.read_record().getName().equals(buf_in.getName()) &&
                    serial.read_record().getCaffea().equals(buf_in.getCaffea()) &&
                    serial.read_record().getVarietal().equals(buf_in.getVarietal()) &&
                    serial.read_record().getOrigin().equals(buf_in.getOrigin()) &&
                    serial.read_record().getRoasting().equals(buf_in.getRoasting())  &&
                    serial.read_record().getProcess().equals(buf_in.getProcess()) ) {
                //System.out.println(serial.read_record().toString());
                //buf_out = toLogicalRecord(toString(serial.read_record()));
                return buf_out;
            }
            if (serial.read_record().getName().contains("#")) {
                System.out.println("There are no records fulfilling those conditions");
                return buf_out;
            }
        }
    }

    /* Retrieves next record matching current search  */
    public String siguiente(BufferRecord buf_out) {
        return ("Next method finished (not implemented yet");
    }

    public String toString(Logical_Record record) {
        String references = "";
        for (int i = 0; i < 15; i++) {
            references += record.getBarCodes()[i] + " " + record.getFormats()[i] + " " + record.getPrices()[i] + " " + record.getMin_stocks()[i] + " "
                    + record.getStocks()[i] + " " + record.getMax_stocks()[i] + ".\n";
        }
        return "Logical_Record [name=" + record.getName() + ", caffea=" + record.getCaffea()
                + ", varietal=" + record.getVarietal() + ", origin=" + record.getOrigin()
                + ", roasting=" + record.getRoasting() + ", process=" + record.getProcess()
                + ", references= \n " + references + "]";
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
            record.setPrices(j, string.substring(i, i + 14));
            i += 15;
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
        return bucket;
    }

    private void writeFFP(int bucket, short FFP) {
        if (bucket == 0) FFP = 0;
    }
}