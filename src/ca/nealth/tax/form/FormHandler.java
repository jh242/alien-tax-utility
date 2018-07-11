package ca.nealth.tax.form;

import org.apache.pdfbox.pdmodel.PDDocument;

import ca.nealth.tax.datasource.TaxInfo;

public interface FormHandler {
	public PDDocument getTemplate();
	
	public boolean validate();
	
	public FormInfo calculate(TaxInfo ti);

}
