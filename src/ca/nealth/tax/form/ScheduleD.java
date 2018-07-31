package ca.nealth.tax.form;

import java.io.File;
import java.io.FileInputStream;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.Calendar;
import java.util.Properties;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocumentCatalog;
import org.apache.pdfbox.pdmodel.interactive.form.PDAcroForm;
import org.apache.pdfbox.pdmodel.interactive.form.PDCheckBox;

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

	public PDDocument fill(TaxInfo ti) {
		PDDocumentCatalog pdfCat = pdf.getDocumentCatalog();
		PDAcroForm form = pdfCat.getAcroForm();
		PDCheckBox temp;
		Method fillTarget = null;
		String year = "" + Calendar.getInstance().get(Calendar.YEAR);
		String yr = year.substring(2);
		String[] module;
		String[] dependents;
		Set<String> keys = fieldIds.stringPropertyNames();

		return pdf;
	}

}
