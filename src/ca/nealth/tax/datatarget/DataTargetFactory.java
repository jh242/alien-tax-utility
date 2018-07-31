package ca.nealth.tax.datatarget;

public class DataTargetFactory {
	
	public static DataTarget getDataTarget(String dataTarget) throws Exception{
		return (DataTarget) Class.forName(dataTarget).newInstance();
	}
}
