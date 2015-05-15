# DB-Optional methods

1. BufferRecord
    * boolean[] getFields()
    * boolean getFields(int i)
    * setFields (i, field)
    * countFields

2. FileMan
    * FileMan()
    * String open_archive(String filename)
    * String close_archive()
    * String save_all()
    * String insert(BufferRecord? buf_in)
    * String imports(String old_filename)
    * LogicalRecord search (String archive, BR buf_in)
    * String siguiente (BR buf_out)
    * String toString (LR record)
    * LR toLR (String)

3. Interface
    * input
    * output
    * iface
        * Open Archive
        * Insert
        * Import
        * Search
        * Next
        * End Search
        * Close Archive
        * Exit

4. LogicalRecord = producto.
    * Atributos, getters y setters.
    * set/getAttribute (i, String att)

5. RABuffer
    * RAB (int numOfPages int Blocksize)
    * int releasePagePolicy (FC, int blocknum)
    * referencedPage(int i)

6. Serial
    * openFile(String file, mode)
    * closeFile()
    * readBlock()
    * readString(i)
    * readbyte()
    * LR readRecord()
    * reset()
    * insertHash()
    * writeBlock_byte, String, writeBlock()
    * write_bucket()
    * fileSize()
    * FFPToInt()
    * intToFFP()
