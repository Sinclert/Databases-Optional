package src;

public class BufferRecord extends Logical_Record{
    private boolean[] fields = {false, false, false, false, false, false};

    public boolean getFields(int i) {
        return fields[i];
    }

    public void setFields(int i, boolean field) {
        this.fields[i] = field;
    }

    public int countFields(){
        int count = 0;
        for (int i = 0; i < 6; i++) {
            if(fields[i]) count++;
        }
        return count;
    }
}
