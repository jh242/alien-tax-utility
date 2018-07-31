package ca.nealth.tax.datasource;

public class SoldProperty {
	public String address;
	public double netGain;
	public double totalDepreciation;
	public String invoice;
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public double getNetGain() {
		return netGain;
	}
	public void setNetGain(double netGain) {
		this.netGain = netGain;
	}
	public double getTotalDepreciation() {
		return totalDepreciation;
	}
	public void setTotalDepreciation(double totalDepreciation) {
		this.totalDepreciation = totalDepreciation;
	}
	public String getInvoice() {
		return invoice;
	}
	public void setInvoice(String invoice) {
		this.invoice = invoice;
	}

}
