package src;

import java.util.ArrayList;

public class Logical_Record {
	
	private char [] name = new char [50];
	private char [] caffea = new char [9];
	private char [] varietal = new char [28];
	private char [] origin = new char [14];
	private char [] roasting = new char [7];
	private char [] process = new char [7];
	private ArrayList <char[]> barCodes = new ArrayList <char[]>();
	private ArrayList <char[]> formats = new ArrayList <char[]>();
	private ArrayList <char[]> price = new ArrayList <char[]>();
	private ArrayList <byte[]> min_stock = new ArrayList <byte[]>();
	private ArrayList <byte[]> stock = new ArrayList <byte[]>();
	private ArrayList <byte[]> max_stock = new ArrayList <byte[]>();
	

	public char[] getName() {
		return name;
	}
	
	public void setName(char[] name) {
		this.name = name;
	}
	
	public char[] getCaffea() {
		return caffea;
	}
	
	public void setCaffea(char[] caffea) {
		this.caffea = caffea;
	}
	
	public char[] getVarietal() {
		return varietal;
	}
	
	public void setVarietal(char[] varietal) {
		this.varietal = varietal;
	}
	
	public char[] getOrigin() {
		return origin;
	}
	
	public void setOrigin(char[] origin) {
		this.origin = origin;
	}
	
	public char[] getRoasting() {
		return roasting;
	}
	
	public void setRoasting(char[] roasting) {
		this.roasting = roasting;
	}
	
	public char[] getProcess() {
		return process;
	}
	
	public void setProcess(char[] process) {
		this.process = process;
	}

	public ArrayList<char[]> getBarCodes() {
		return barCodes;
	}

	public void setBarCodes(ArrayList<char[]> barCodes) {
		this.barCodes = barCodes;
	}

	public ArrayList<char[]> getFormats() {
		return formats;
	}

	public void setFormats(ArrayList<char[]> formats) {
		this.formats = formats;
	}

	public ArrayList<char[]> getPrice() {
		return price;
	}

	public void setPrice(ArrayList<char[]> price) {
		this.price = price;
	}

	public ArrayList<byte[]> getMin_stock() {
		return min_stock;
	}

	public void setMin_stock(ArrayList<byte[]> min_stock) {
		this.min_stock = min_stock;
	}

	public ArrayList<byte[]> getStock() {
		return stock;
	}

	public void setStock(ArrayList<byte[]> stock) {
		this.stock = stock;
	}

	public ArrayList<byte[]> getMax_stock() {
		return max_stock;
	}

	public void setMax_stock(ArrayList<byte[]> max_stock) {
		this.max_stock = max_stock;
	}

}