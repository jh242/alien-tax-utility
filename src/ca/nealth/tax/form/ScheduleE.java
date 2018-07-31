package ca.nealth.tax.form;

import java.io.File;
import java.io.FileInputStream;
import java.net.URL;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.pdfbox.pdmodel.PDDocument;

import ca.nealth.tax.datasource.TaxInfo;

public class ScheduleE extends FormHandler {

	File input;
	Properties fieldIds;

	public ScheduleE() throws Exception {
		URL formSrc = new URL("https://www.irs.gov/pub/irs-pdf/f1040se.pdf");
		input = new File(System.getProperty("user.dir") + "\\scheduleE.pdf");
		FileUtils.copyURLToFile(formSrc, input);
		this.pdf = PDDocument.load(input);
		fieldIds = new Properties();
		FileInputStream in = new FileInputStream(System.getProperty("user.dir") + "\\schedE.properties");
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