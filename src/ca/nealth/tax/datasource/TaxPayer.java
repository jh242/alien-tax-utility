package ca.nealth.tax.datasource;

public class TaxPayer {
	public String taxId;
	public String lastName;
	public String firstName;
	public String address;
	public String country;
	
	public String getTaxId() {
		return taxId;
	}
	
	public void setTaxId(String taxId) {
		this.taxId=taxId;
	}
}
