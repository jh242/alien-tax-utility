package ca.nealth.tax.util;
import java.util.ArrayList;
import ca.nealth.tax.datasource.DataSource;
import ca.nealth.tax.datasource.DataSourceFactory;
import ca.nealth.tax.datasource.TaxInfo;

@SuppressWarnings("unused")
public class TaxFiler {
	
	private String dataSource;
	
	private static final TaxFiler instance = new TaxFiler();
	
	private TaxFiler() {}
	
	public static TaxFiler getInstance() {
		
		return instance;
		
	}
	
	///TODO: Implement Java Properties
	
	public static void file(String dataSource) {
		DataSource rawData = null;
		ArrayList<TaxInfo> tis = new ArrayList<TaxInfo>();
		
		try {
			rawData = DataSourceFactory.getDataSource(dataSource);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		for(int taxPayer = 0; taxPayer < 5/*CHANGE THIS*/; taxPayer++)
			tis.add(rawData.read(taxPayer));
		 
	}
	
	
}
