package ca.nealth.tax.datatarget;

import java.io.File;
import java.io.IOException;
import org.apache.pdfbox.pdmodel.PDDocument;

public class LocalDisk extends DataTarget {
	
	int formNumber = 1;

	public void write(PDDocument pdf) throws IOException {
		File output = new File(System.getProperty("user.dir") + "\\1040nr-person" + formNumber + ".pdf");
		pdf.save(output);
		pdf.close();
		formNumber++;
		
	}


}
