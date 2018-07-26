package ca.nealth.tax.form;

import java.io.File;
import java.net.URL;

import org.apache.commons.io.FileUtils;
import org.apache.pdfbox.pdmodel.PDDocument;

import ca.nealth.tax.datasource.TaxInfo;

public class ScheduleE extends FormHandler {
	PDDocument pdf;

	public ScheduleE() throws Exception {
		pdf = null;
		URL tenFortySrc = new URL("https://www.irs.gov/pub/irs-pdf/f1040se.pdf");
		File input = new File(System.getProperty("user.dir") + "\\schedulee.pdf");

		FileUtils.copyURLToFile(tenFortySrc, input);

		pdf = PDDocument.load(input);

	}

	public boolean validate() {
		// TODO Auto-generated method stub
		return false;
	}

	public void fill(TaxInfo ti) {
	}

}