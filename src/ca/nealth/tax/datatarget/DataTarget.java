package ca.nealth.tax.datatarget;

import org.apache.pdfbox.pdmodel.PDDocument;

public abstract class DataTarget {
	public abstract void write(PDDocument pdf);
	
	public boolean login() {
		return false;
	}
	
	public boolean logout() {
		return false;
	}
}
