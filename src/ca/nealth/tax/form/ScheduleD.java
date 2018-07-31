package ca.nealth.tax.form;

import java.io.File;
import java.io.FileInputStream;
import java.net.URL;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.pdfbox.pdmodel.PDDocument;

import ca.nealth.tax.datasource.TaxInfo;

public class ScheduleD extends FormHandler {

	File input;
	Properties fieldIds;

	public ScheduleD() throws Exception {
		URL formSrc = new URL("https://www.irs.gov/pub/irs-pdf/f1040sd.pdf");
		input = new File(System.getProperty("user.dir") + "\\scheduleD.pdf");
		FileUtils.copyURLToFile(formSrc, input);
		this.pdf = PDDocument.load(input);
		fieldIds = new Properties();
		FileInputStream in = new FileInputStream(System.getProperty("user.dir") + "\\schedD.properties");
		fieldIds.load(in);

	}

	public boolean validate() {
		// TODO Auto-generated method stub
		return false;
	}

	public PDDocument fill(TaxInfo ti) {
		return null;
	}

}
