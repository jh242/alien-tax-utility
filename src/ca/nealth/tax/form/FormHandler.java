package ca.nealth.tax.form;

//change
public interface FormHandler {
	public void getTemplate();
	
	public boolean validate();
	
	public Object calculate();
}
