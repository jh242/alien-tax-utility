package ca.nealth.tax.form;

import java.io.IOException;

import org.apache.pdfbox.pdmodel.PDDocument;

import ca.nealth.tax.datasource.TaxInfo;

public abstract class FormHandler {
	
	public abstract boolean validate();
	
	public abstract void fill(TaxInfo ti) throws IOException;

}
