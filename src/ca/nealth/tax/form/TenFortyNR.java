package ca.nealth.tax.form;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Calendar;
import java.util.Properties;
import java.util.Set;
import java.lang.reflect.*;
import org.apache.commons.io.FileUtils;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocumentCatalog;
import org.apache.pdfbox.pdmodel.PDDocumentInformation;
import org.apache.pdfbox.pdmodel.interactive.form.PDAcroForm;
import org.apache.pdfbox.pdmodel.interactive.form.PDCheckBox;

import ca.nealth.tax.datasource.TaxInfo;

public class TenFortyNR extends FormHandler {

	PDDocument pdf;
	File input;
	Properties fieldIds;

	// StringBuilder sb;

	public TenFortyNR() throws Exception {
		URL tenFortySrc = new URL("https://www.irs.gov/pub/irs-pdf/f1040nr.pdf");
		input = new File(System.getProperty("user.dir") + "\\1040nr.pdf");
		FileUtils.copyURLToFile(tenFortySrc, input);
		pdf = PDDocument.load(input);

		fieldIds = new Properties();
		FileInputStream in = new FileInputStream(System.getProperty("user.dir") + "\\fieldid.properties");

		fieldIds.load(in);

	}

	public boolean validate() {//Make sure the current form is up-to-date.
		PDDocumentInformation info = pdf.getDocumentInformation();
		Calendar creationDate = info.getCreationDate();

		if (creationDate.get(Calendar.YEAR) == Calendar.getInstance().get(Calendar.YEAR))
			return true;
		else
			return false;
	}

	public void fill(TaxInfo ti) throws Exception {/// TODO: change this to return the PDF
		PDDocumentCatalog pdfCat = pdf.getDocumentCatalog();
		PDAcroForm form = pdfCat.getAcroForm();
		PDCheckBox temp;
		Method fillTarget = null;
		String year = "" + Calendar.getInstance().get(Calendar.YEAR);
		String yr = year.substring(2);
		String[] module;
		Set<String> keys = fieldIds.stringPropertyNames();

		for (String key : keys) {
			if (!fieldIds.getProperty(key).startsWith("CheckBox")) {//Check current field is not checkbox
				module = fieldIds.getProperty(key).split("\\.");
				if (module[0].equals("System")) {
					if (module[1].equals("firstMonth"))
						form.getField(key).setValue("January");
					else if (module[1].equals("lastMonth"))
						form.getField(key).setValue("December");
					else if (module[1].equals("currentYear"))
						form.getField(key).setValue(yr);
				}else if (module[0].equals("TaxPayer")) {
					fillTarget = ti.getTaxPayer().getClass().getMethod(module[1]);
					form.getField(key).setValue((String) fillTarget.invoke(ti.getTaxPayer()));
				}else if (module[0].equals("TestValue")) {
					form.getField(key).setValue(module[1]);
				}
			} else {//Handle checkboxes separately
				String trimmedName = fieldIds.getProperty(key).split("\\.")[1];
				if (trimmedName.startsWith("filing")
						&& Character.getNumericValue(trimmedName.charAt(6)) == ti.getTaxPayer().getFilingStatus()) {
					temp = (PDCheckBox) form.getField(key);
					temp.check();
				} else if (trimmedName.equals("individual")) {
					temp = (PDCheckBox) form.getField(key);
					temp.check();
				}
			}
		}

		pdf.save(input);
		pdf.close();

	}

	/*
	 * void list(PDField field) { sb.append(field.getFullyQualifiedName() +
	 * System.getProperty("line.separator")); if (field instanceof
	 * PDNonTerminalField) { PDNonTerminalField nonTerminalField =
	 * (PDNonTerminalField) field; for (PDField child :
	 * nonTerminalField.getChildren()) { list(child); } } }
	 */
}
