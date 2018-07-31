package ca.nealth.tax.datasource;

import java.util.LinkedList;

public class TaxInfo {

	public TaxPayer tp;
	public LinkedList<RentalProperty> rentalProperties;
	public LinkedList<SoldProperty> soldProperties;

	public void setTaxPayer(TaxPayer tp) {
		this.tp = tp;
	}

	public TaxPayer getTaxPayer() {
		return tp;
	}

	public void setRentalProperties(LinkedList<RentalProperty> rentalProperties) {
		this.rentalProperties = rentalProperties;
	}
	
	public LinkedList<RentalProperty> getRentalProperties() {
		return rentalProperties;
	}

	public LinkedList<SoldProperty> getSoldProperties() {
		return soldProperties;
	}

	public void setSoldProperties(LinkedList<SoldProperty> soldProperties) {
		this.soldProperties = soldProperties;
	}
	
	public double getCaptialGain() {
		double capitalGain = 0;
		for (SoldProperty sp:soldProperties) {
			capitalGain += sp.getNetGain();
		}
		return capitalGain;
	}

}