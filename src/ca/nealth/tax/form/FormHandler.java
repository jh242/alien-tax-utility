package ca.nealth.tax.form;

import java.util.Calendar;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocumentInformation;
import java.lang.RuntimeException;
import ca.nealth.tax.datasource.TaxInfo;

public abstract class FormHandler {
	
	public PDDocument pdf;

	public boolean validate(PDDocument pdf) {
		PDDocumentInformation info = pdf.getDocumentInformation();
		Calendar creationDate = info.getCreationDate();
		if (creationDate.get(Calendar.YEAR) == Calendar.getInstance().get(Calendar.YEAR))
			return true;
		else
			return false;
	}

	public PDDocument fileForm(TaxInfo ti) throws Exception {
		if (validate(pdf)) {
			return fill(ti);
		} else {
			throw new RuntimeException();
		}
	}

	public abstract PDDocument fill(TaxInfo ti) throws Exception;

}
