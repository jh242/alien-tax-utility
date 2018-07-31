package ca.nealth.tax.form;

import java.io.File;
import java.io.FileInputStream;
import java.net.URL;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.pdfbox.pdmodel.PDDocument;

import ca.nealth.tax.datasource.TaxInfo;

public class FourFiveSixTwo extends FormHandler{
	
	File input;
	Properties fieldIds;
	
	public FourFiveSixTwo() throws Exception {
		URL formSrc = new URL("https://www.irs.gov/pub/irs-pdf/f4562.pdf");
		input = new File(System.getProperty("user.dir") + "\\4562.pdf");
		FileUtils.copyURLToFile(formSrc, input);
		this.pdf = PDDocument.load(input);
		fieldIds = new Properties();
		FileInputStream in = new FileInputStream(System.getProperty("user.dir") + "\\4562.properties");
		fieldIds.load(in);
	}

	@Override
	public PDDocument fill(TaxInfo ti) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
