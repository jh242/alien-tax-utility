package ca.nealth.tax.datasource;

public class ExcelImpl implements DataSource {

	@Override
	public TaxInfo read() {
		TaxInfo ti = new TaxInfo();
		TaxPayer tp = new TaxPayer();
		return ti;
	}

	@Override
	public boolean login() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean logout() {
		// TODO Auto-generated method stub
		return false;
	}

}
