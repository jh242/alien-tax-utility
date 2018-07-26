package ca.nealth.tax.form;

import java.io.File;
import java.net.URL;

import org.apache.commons.io.FileUtils;
import org.apache.pdfbox.pdmodel.PDDocument;

import ca.nealth.tax.datasource.TaxInfo;

public class ScheduleD extends FormHandler {

	PDDocument pdf;

	public ScheduleD() throws Exception {
		pdf = null;

		URL tenFortySrc = new URL("https://www.irs.gov/pub/irs-pdf/f1040sd.pdf");
		File input = new File(System.getProperty("user.dir") + "\\scheduled.pdf");

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
