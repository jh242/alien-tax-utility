package ca.nealth.tax.datatarget;

import java.io.IOException;

import org.apache.pdfbox.pdmodel.PDDocument;

public abstract class DataTarget {
	public abstract void write(PDDocument pdf) throws IOException;
	
	public boolean login() {
		return false;
	}
	
	public boolean logout() {
		return false;
	}
}
