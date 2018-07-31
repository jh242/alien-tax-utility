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
	public String[] dependents;
	public String[] dependentRelationships;
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
		if(initial != null)
			return firstName + ", " + initial;
		else return firstName;
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

	public String[] getDependents() {
		return dependents;
	}

	public void setDependents(String[] dependents) {
		this.dependents = dependents;
	}

	public String getSpouseLastName() {
		return spouseLastName;
	}

	public void setSpouseLastName(String spouseLastName) {
		this.spouseLastName = spouseLastName;
	}

	public void setSpouseInitial(String spouseInitial) {
		this.spouseInitial = spouseInitial;
	}

	public String getSpouseFirstName() {
		if(spouseInitial != null)
			return spouseFirstName + ", " + spouseInitial;
		else return spouseFirstName;
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
	
	public String[] getDependentRelationships() {
		return dependentRelationships;
	}

	public void setDependentRelationships(String[] dependentRelationships) {
		this.dependentRelationships = dependentRelationships;
	}
	
	public String getDependent1() {
		return dependents[0];
	}
	
	public String getDependent1Relationship() {
		return dependentRelationships[0];
	}
	
	public String getDependent2() {
		return dependents[1];
	}
	
	public String getDependent2Relationship() {
		return dependentRelationships[1];
	}
	
	public String getDependent3() {
		return dependents[2];
	}
	
	public String getDependent3Relationship() {
		return dependentRelationships[2];
	}
	
	public String getDependent4() {
		return dependents[3];
	}
	
	public String getDependent4Relationship() {
		return dependentRelationships[3];
	}

}
