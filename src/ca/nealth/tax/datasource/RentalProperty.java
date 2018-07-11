package ca.nealth.tax.datasource;

public class RentalProperty {
	public double totalRentalIncome;
	public double totalExpenses;
	public String propertyAddress;
	public String closingDate;
	public double getTotalRentalIncome() {
		return totalRentalIncome;
	}
	public void setTotalRentalIncome(double totalRentalIncome) {
		this.totalRentalIncome = totalRentalIncome;
	}
	public double getTotalExpenses() {
		return totalExpenses;
	}
	public void setTotalExpenses(double totalExpenses) {
		this.totalExpenses = totalExpenses;
	}
	public String getPropertyAddress() {
		return propertyAddress;
	}
	public void setPropertyAddress(String propertyAddress) {
		this.propertyAddress = propertyAddress;
	}
	public String getClosingDate() {
		return closingDate;
	}
	public void setClosingDate(String closingDate) {
		this.closingDate = closingDate;
	}
	public double getNetRentalIncome() {
		return totalRentalIncome-totalExpenses;
	}

}
