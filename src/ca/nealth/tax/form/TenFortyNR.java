package ca.nealth.tax.form;

import java.io.File;
import java.net.URL;
import ca.nealth.tax.datasource.*;

import org.apache.commons.io.FileUtils;
import org.apache.pdfbox.pdmodel.PDDocument;

public class TenFortyNR implements FormHandler{

	@Override
	public PDDocument getTemplate() {
		PDDocument pdf = null;
		try {
			URL tenFortySrc = new URL("https://www.irs.gov/pub/irs-pdf/f1040nr.pdf");
			File input = new File(System.getProperty("user.dir") + "\\1040nr.pdf");
			
			FileUtils.copyURLToFile(tenFortySrc, input);
			
			pdf = PDDocument.load(input);
			
			}catch(Exception e) {
				e.printStackTrace();
			}
		return pdf;
	}

	@Override
	public boolean validate() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public FormInfo calculate(TaxInfo ti) {
		FormInfo fill = new FormInfo();
		return fill;
	}


}
