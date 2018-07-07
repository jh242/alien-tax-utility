package ca.nealth.tax.datasource;

public interface DataSource {
	public Object read();
	
	public boolean login();
	
	public boolean logout();
}
