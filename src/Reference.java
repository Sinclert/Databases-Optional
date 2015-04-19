package src;

public class Reference {
	private char [] format = new char [12];
	private char [] packaging = new char [15];
	private char [] price = new char [11];
	private byte [] min_stock = new byte [3];
	private byte [] stock = new byte [4];
	private byte [] max_stock = new byte [4];
	
	public char[] getFormat() {
		return format;
	}
	
	public void setFormat(char[] format) {
		this.format = format;
	}
	
	public char[] getPackaging() {
		return packaging;
	}
	
	public void setPackaging(char[] packaging) {
		this.packaging = packaging;
	}
	
	public char[] getPrice() {
		return price;
	}
	
	public void setPrice(char[] price) {
		this.price = price;
	}
	
	public byte[] getMin_stock() {
		return min_stock;
	}
	
	public void setMin_stock(byte[] min_stock) {
		this.min_stock = min_stock;
	}
	
	public byte[] getStock() {
		return stock;
	}
	
	public void setStock(byte[] stock) {
		this.stock = stock;
	}
	
	public byte[] getMax_stock() {
		return max_stock;
	}
	
	public void setMax_stock(byte[] max_stock) {
		this.max_stock = max_stock;
	}
	
}