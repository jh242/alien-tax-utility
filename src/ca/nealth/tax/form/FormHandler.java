package ca.nealth.tax.form;

public interface FormHandler {
	public void getTemplate();
	
	public boolean validate();
	
	public Object calculate();
}
