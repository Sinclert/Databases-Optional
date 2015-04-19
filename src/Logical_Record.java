package src;

import java.util.ArrayList;

public class Logical_Record {
	private char [] name = new char [50];
	private char [] caffea = new char [9];
	private char [] varietal = new char [28];
	private char [] origin = new char [14];
	private char [] roasting = new char [7];
	private char [] process = new char [7];
	private ArrayList <Reference> references = new ArrayList <Reference> ();
	
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
	
	public ArrayList <Reference> getReferences() {
		return references;
	}
	
	public void setReferences(ArrayList <Reference> references) {
		this.references = references;
	}

}