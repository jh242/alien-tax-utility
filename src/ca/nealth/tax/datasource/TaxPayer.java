package ca.nealth.tax.datasource;

public class TaxPayer {
	public String idNumber;
	public String lastName;
	public String initial;
	public String firstName;
	public String address;
	public String city;
	public String country;
	public String state;
	public String postalCode;
	public int filingStatus;
	public String childrensNames;
	public String spouseLastName;
	public String spouseInitial;
	public String spouseFirstName;
	public String spouseIdNumber;

	public String getTaxId() {
		return idNumber;
	}

	public void setTaxId(String taxId) {
		this.idNumber = taxId;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public void setInitial(String initial) {
		this.initial = initial;
	}
	
	public String getFirstName() {
		return firstName + ", " + initial;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getAddress() {
		return address;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}

	public String getIdNumber() {
		return idNumber;
	}

	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public int getFilingStatus() {
		return filingStatus;
	}

	public void setFilingStatus(int filingStatus) {
		this.filingStatus = filingStatus;
	}

	public String getChildrensNames() {
		return childrensNames;
	}

	public void setChildrensNames(String childrensNames) {
		this.childrensNames = childrensNames;
	}

	public String getSpouseLastName() {
		return spouseLastName;
	}

	public void setSpouseLastName(String spouseLastName) {
		this.spouseLastName = spouseLastName;
	}

	public String getSpouseInitial() {
		return spouseInitial;
	}

	public void setSpouseInitial(String spouseInitial) {
		this.spouseInitial = spouseInitial;
	}

	public String getSpouseFirstName() {
		return spouseFirstName;
	}

	public void setSpouseFirstName(String spouseFirstName) {
		this.spouseFirstName = spouseFirstName;
	}

	public String getSpouseIdNumber() {
		return spouseIdNumber;
	}

	public void setSpouseIdNumber(String spouseIdNumber) {
		this.spouseIdNumber = spouseIdNumber;
	}

	public String getInitial() {
		return initial;
	}

}