package ca.nealth.tax.util;
import java.util.Properties;

@SuppressWarnings("unused")
public class TaxFiler {
	
	private static final TaxFiler instance = new TaxFiler();
	
	private TaxFiler() {}
	
	public static TaxFiler getInstance() {
		return instance;
	}
	
	static void readProperties() {}
	
	public static void file() {}
	
	
}
