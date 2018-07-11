package ca.nealth.tax.datasource;


public abstract class DataSource {
	public abstract TaxInfo read(int taxPayer);
	
	public boolean login() {
		return false;
	}
	
	public boolean logout() {
		return false;
	}
}
