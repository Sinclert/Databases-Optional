package src;

public class Bucket {
	
	public static final int BLOCKSIZE = 1024;
	public static int FFP = 0;
	
	private int readFFP() {
		return (FFP);
	}
	
	private void writeFFP(int FFP) {
		this.FFP = FFP;
	}
	
}
