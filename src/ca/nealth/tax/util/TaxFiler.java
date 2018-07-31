package ca.nealth.tax.util;
import java.io.FileInputStream;
import java.util.AbstractSet;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Properties;
import org.apache.pdfbox.pdmodel.PDDocument;
import ca.nealth.tax.datasource.*;
import ca.nealth.tax.datatarget.DataTarget;
import ca.nealth.tax.datatarget.DataTargetFactory;
import ca.nealth.tax.form.FormHandler;
import ca.nealth.tax.form.TenFortyNR;

@SuppressWarnings("unused")
public class TaxFiler {
	
	private static String dataSource;
	private static String dataTarget;
	private static int taxPayers;
	
	private static final TaxFiler instance = new TaxFiler();
	
	private TaxFiler() {}
	
	public static TaxFiler getInstance() throws Exception {
		Properties prop = new Properties();
		FileInputStream in = new FileInputStream(System.getProperty("user.dir") + "\\java.properties");
		
		prop.load(in);
		
		if(prop.getProperty("datasource").equalsIgnoreCase("Excel")) {
			dataSource = "ca.nealth.tax.datasource.ExcelDataSource";
		}
		
		if(prop.getProperty("datatarget").equalsIgnoreCase("LocalDisk")){
			dataTarget = "ca.nealth.tax.datatarget.LocalDisk";
		}
		
		taxPayers = Integer.parseInt(prop.getProperty("taxpayers"));
		
		return instance;
		
	}
	
	
	public void file(String dataSource, String dataTarget, int taxPayers) throws Exception {
		DataSource rawData = null;
		DataTarget saveTarget = null;
		FormHandler tenForty = new TenFortyNR();
		ArrayList<TaxInfo> tis = new ArrayList<TaxInfo>();
		LinkedList<RentalProperty> rps;
		LinkedList<SoldProperty> sps;
		LinkedList<PDDocument> completedForms = new LinkedList<PDDocument>();
		
		try {
			rawData = DataSourceFactory.getDataSource(dataSource);
			saveTarget = DataTargetFactory.getDataTarget(dataTarget);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		for(int taxPayer = 0; taxPayer < taxPayers; taxPayer++)
			tis.add(rawData.read(taxPayer));
		
		for(TaxInfo ti:tis) {
			completedForms.add(tenForty.fileForm(ti));
			rps = ti.getRentalProperties();
			sps = ti.getSoldProperties();
			for(RentalProperty rp:rps) {
				//Schedule E block
			}
			for(SoldProperty sp:sps) {
				//Schedule D block
			}
		}
		for (PDDocument completedForm : completedForms) {
			saveTarget.write(completedForm);
		}
	}
	
	public static void main(String[] args) {
		TaxFiler tf = null;
		TenFortyNR form = null;
		try {
			tf = TaxFiler.getInstance();
			tf.file(dataSource, dataTarget, taxPayers);
		} catch (Exception e) {
			e.printStackTrace();
		}	

	}
	
	
}
