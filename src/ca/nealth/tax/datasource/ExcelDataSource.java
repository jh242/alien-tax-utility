package ca.nealth.tax.datasource;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;
import java.io.*;
import java.math.BigDecimal;
import java.util.*;

public class ExcelDataSource extends DataSource {

	Workbook wb;
	InputStream inFile;
	FormulaEvaluator eval;
	Cell target;

	public ExcelDataSource() throws IOException {
		inFile = new FileInputStream(System.getProperty("user.dir") + "\\TEMPLATE.xlsx");
		wb = new XSSFWorkbook(inFile);
		eval = wb.getCreationHelper().createFormulaEvaluator();
	}

	@SuppressWarnings("deprecation")
	double getNumberCellValue(Sheet sheet, int rowNum, int cellNum) {
		target = sheet.getRow(rowNum).getCell(cellNum);

		if (target.getCellType() == Cell.CELL_TYPE_NUMERIC) {
			return target.getNumericCellValue();
		} else if (target.getCellType() == Cell.CELL_TYPE_FORMULA) {
			return eval.evaluateFormulaCell(target);
		} else if (target.getCellType() == Cell.CELL_TYPE_BLANK){
			return -1;
		}else {
			throw new RuntimeException("Excel format error!");
		}
	}

	@SuppressWarnings("deprecation")
	String getStringCellValue(Sheet sheet, int rowNum, int cellNum) {
		target = sheet.getRow(rowNum).getCell(cellNum);

		if (target == null){
			return " ";
		}else if (target.getCellType() == Cell.CELL_TYPE_STRING) {
			return target.getStringCellValue();
		} else if (target.getCellType() == Cell.CELL_TYPE_BLANK){
			return " ";
		}else {
			throw new RuntimeException("Excel format error!");
		}
	}

	@SuppressWarnings("deprecation")
	@Override
	public TaxInfo read(int taxPayer) {

		LinkedList<RentalProperty> rentalProperties = new LinkedList<RentalProperty>();
		LinkedList<SoldProperty> soldProperties = new LinkedList<SoldProperty>();
		LinkedList<Double> ownershipOfRps = new LinkedList<Double>();
		LinkedList<Double> ownershipOfSps = new LinkedList<Double>();
		int rentalProperty = 0;
		int soldProperty = 0;
		String[] dependents = new String[4];
		String[] dependentRelationships = new String[4];
		
		TaxPayer tp = new TaxPayer();
		TaxInfo ti = new TaxInfo();

		Sheet personalInfo = wb.getSheetAt(taxPayer);
		tp.setLastName(getStringCellValue(personalInfo, 1, 1));
		tp.setInitial(getStringCellValue(personalInfo, 2, 1));
		tp.setFirstName(getStringCellValue(personalInfo, 3, 1));
		tp.setIdNumber(new BigDecimal(getNumberCellValue(personalInfo, 4, 1)).toPlainString());
		tp.setAddress(getStringCellValue(personalInfo, 5, 1));
		tp.setCity(getStringCellValue(personalInfo, 6, 1));
		tp.setCountry(getStringCellValue(personalInfo, 7, 1));
		tp.setState(getStringCellValue(personalInfo, 8, 1));
		tp.setPostalCode(getStringCellValue(personalInfo, 9, 1));
		tp.setFilingStatus((int) getNumberCellValue(personalInfo, 10, 1));
		
		for(int dependent = 0; dependent < 4; dependent++) {
			dependents[dependent] = getStringCellValue(personalInfo, 11+dependent, 1);
			dependentRelationships[dependent] = getStringCellValue(personalInfo, 11+dependent, 3);
		}
		
		tp.setDependents(dependents);
		tp.setDependentRelationships(dependentRelationships);

		if (tp.getFilingStatus() == 3 || tp.getFilingStatus() == 4 || tp.getFilingStatus() == 5) {
			tp.setSpouseLastName(getStringCellValue(personalInfo, 16, 1));
			tp.setSpouseInitial(getStringCellValue(personalInfo, 17, 1));
			tp.setSpouseFirstName(getStringCellValue(personalInfo, 18, 1));
			tp.setSpouseIdNumber(new BigDecimal(getNumberCellValue(personalInfo, 19, 1)).toPlainString());
		}
		
		for(int rp = 0; rp < 6; rp++) {
			if(personalInfo.getRow(21+rp).getCell(1).getCellType() == Cell.CELL_TYPE_BLANK) {
				break;
			}else {
				ownershipOfRps.add(getNumberCellValue(personalInfo, 21+rp, 1));
			}
		}
		
		for(int sp = 0; sp < 6; sp++) {
			if(personalInfo.getRow(28+sp).getCell(1).getCellType() == Cell.CELL_TYPE_BLANK) {
				break;
			}else {
				ownershipOfSps.add(getNumberCellValue(personalInfo, 28+sp, 1));
			}
		}
		
		Double[] oor = ownershipOfRps.toArray(new Double[ownershipOfSps.size()]);
		Double[] oos = ownershipOfSps.toArray(new Double[ownershipOfSps.size()]);

		for (Sheet sheet : wb) {
			if (getNumberCellValue(sheet, 0, 1) == 1 && sheet.getRow(2).getCell(1).getCellType() != Cell.CELL_TYPE_BLANK) {// Read rental property info, calculate percents
				RentalProperty rp = new RentalProperty();
				rp.setTotalRentalIncome(getNumberCellValue(sheet, 3, 13)*oor[rentalProperty]);
				rp.setTotalExpenses(getNumberCellValue(sheet, 24, 13)*oor[rentalProperty]);
				rp.setPropertyAddress(getStringCellValue(sheet, 28, 1));
				rp.setClosingDate(getStringCellValue(sheet, 29, 1));
				rentalProperty++;
				rentalProperties.add(rp);

			} else if (getNumberCellValue(sheet, 0, 1) == 2 && sheet.getRow(1).getCell(1).getCellType() != Cell.CELL_TYPE_BLANK) {// Read sold property info, calculate percents
				SoldProperty sp = new SoldProperty();
				sp.setAddress(getStringCellValue(sheet, 1, 1));
				sp.setNetGain(getNumberCellValue(sheet, 8 ,1)*oos[soldProperty]);
				sp.setInvoice(getStringCellValue(sheet, 9, 1));
				sp.setTotalDepreciation(getNumberCellValue(sheet, 7, 1));
				soldProperty++;
				soldProperties.add(sp);
			}

		}

		ti.setTaxPayer(tp);
		ti.setRentalProperties(rentalProperties);
		ti.setSoldProperties(soldProperties);

		return ti;
	}

}
