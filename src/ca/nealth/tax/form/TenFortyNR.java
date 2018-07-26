package ca.nealth.tax.form;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Calendar;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocumentCatalog;
import org.apache.pdfbox.pdmodel.PDDocumentInformation;
import org.apache.pdfbox.pdmodel.interactive.form.PDAcroForm;
import org.apache.pdfbox.pdmodel.interactive.form.PDField;
import org.apache.pdfbox.pdmodel.interactive.form.PDNonTerminalField;

import ca.nealth.tax.datasource.TaxInfo;

public class TenFortyNR extends FormHandler {

	PDDocument pdf;

	public TenFortyNR() throws Exception {
		URL tenFortySrc = new URL("https://www.irs.gov/pub/irs-pdf/f1040nr.pdf");
		File input = new File(System.getProperty("user.dir") + "\\1040nr.pdf");

		FileUtils.copyURLToFile(tenFortySrc, input);

		pdf = PDDocument.load(input);

	}

	public boolean validate() {
		PDDocumentInformation info = pdf.getDocumentInformation();
		Calendar creationDate = info.getCreationDate();

		if (creationDate.get(Calendar.YEAR) == Calendar.getInstance().get(Calendar.YEAR))
			return true;
		else
			return false;
	}

	public void fill(TaxInfo ti) throws IOException {
		PDDocumentCatalog pdfCat = pdf.getDocumentCatalog();
		PDAcroForm form = pdfCat.getAcroForm();
		List<PDField> fields = form.getFields();

		for (PDField field : fields) {
			list(field);
		}
		
		pdf.close();

	}

	void list(PDField field) {
		System.out.println(field.getFullyQualifiedName());
		System.out.println(field.getPartialName());
		if (field instanceof PDNonTerminalField) {
			PDNonTerminalField nonTerminalField = (PDNonTerminalField) field;
			for (PDField child : nonTerminalField.getChildren()) {
				list(child);
			}
		}
	}
}
