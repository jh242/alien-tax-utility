package ca.nealth.tax.util;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Properties;

import ca.nealth.tax.datasource.*;
import ca.nealth.tax.form.*;

@SuppressWarnings("unused")
public class TaxFiler {
	
	private static String dataSource;
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
		
		taxPayers = Integer.parseInt(prop.getProperty("taxpayers"));
		
		return instance;
		
	}
	
	///TODO: Implement Java Properties
	
	public void file(String dataSource, int taxPayers) throws Exception {
		DataSource rawData = null;
		TenFortyNR form = new TenFortyNR();
		ArrayList<TaxInfo> tis = new ArrayList<TaxInfo>();
		
		try {
			rawData = DataSourceFactory.getDataSource(dataSource);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		for(int taxPayer = 0; taxPayer < taxPayers; taxPayer++)
			tis.add(rawData.read(taxPayer));
		
		for(TaxInfo ti:tis) {
			form.fill(ti);
		}
		 
	}
	
	public static void main(String[] args) {
		TaxFiler tf = null;
		TenFortyNR form = null;
		try {
			tf = TaxFiler.getInstance();
			tf.file(dataSource, taxPayers);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	
}
