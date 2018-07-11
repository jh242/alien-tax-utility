package ca.nealth.tax.datasource;

import java.util.LinkedList;

public class TaxInfo {

	public TaxPayer tp;
	public LinkedList rentalProperties;
	public LinkedList soldProperties;

	public void setTaxPayer(TaxPayer tp) {
		this.tp = tp;
	}

	public TaxPayer getTaxPayer() {
		return tp;
	}

	public void setRentalProperties(LinkedList rentalProperties) {
		this.rentalProperties = rentalProperties;
	}
	
	public LinkedList getRentalProperties() {
		return rentalProperties;
	}

	public LinkedList getSoldProperties() {
		return soldProperties;
	}

	public void setSoldProperties(LinkedList soldProperties) {
		this.soldProperties = soldProperties;
	}
	
	

}