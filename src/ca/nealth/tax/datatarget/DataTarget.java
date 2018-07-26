package ca.nealth.tax.datatarget;

public abstract class DataTarget {
	public abstract void write();
	
	public boolean login() {
		return false;
	}
	
	public boolean logout() {
		return false;
	}
}
