package ca.nealth.tax.datasource;

public class DataSourceFactory {
	
	public static DataSource getDataSource(String dataSource) throws Exception {
		
		return (DataSource) Class.forName(dataSource).newInstance();
		
		/*if(application.equalsIgnoreCase("Excel")) {
			return new ExcelDataSource()\;
		}
		return null;*/
	}
}
