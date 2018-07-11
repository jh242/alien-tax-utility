package ca.nealth.tax.datasource;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;
import java.io.*;
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
		} else {
			throw new RuntimeException("Excel format error!");
		}
	}

	@SuppressWarnings("deprecation")
	String getStringCellValue(Sheet sheet, int rowNum, int cellNum) {
		target = sheet.getRow(rowNum).getCell(cellNum);

		if (target.getCellType() == Cell.CELL_TYPE_STRING) {
			return target.getStringCellValue();
		} else {
			throw new RuntimeException("Excel format error!");
		}
	}

	@Override
	public TaxInfo read(int taxPayer) {

		LinkedList<RentalProperty> rentalProperties = new LinkedList<RentalProperty>();
		LinkedList<SoldProperty> soldProperties = new LinkedList<SoldProperty>();
		LinkedList<Double> ownershipOfRps = new LinkedList<Double>();
		LinkedList<Double> ownershipOfSps = new LinkedList<Double>();
		int rentalProperty = 0;
		int soldProperty = 0;
		
		TaxPayer tp = new TaxPayer();
		TaxInfo ti = new TaxInfo();

		Sheet personalInfo = wb.getSheetAt(taxPayer);
		tp.setLastName(getStringCellValue(personalInfo, 1, 1));
		tp.setInitial(getStringCellValue(personalInfo, 2, 1));
		tp.setFirstName(getStringCellValue(personalInfo, 3, 1));
		tp.setIdNumber(getStringCellValue(personalInfo, 4, 1));
		tp.setAddress(getStringCellValue(personalInfo, 5, 1));
		tp.setCity(getStringCellValue(personalInfo, 6, 1));
		tp.setCountry(getStringCellValue(personalInfo, 7, 1));
		tp.setState(getStringCellValue(personalInfo, 8, 1));
		tp.setPostalCode(getStringCellValue(personalInfo, 9, 1));
		tp.setFilingStatus((int) getNumberCellValue(personalInfo, 10, 1));
		tp.setChildrensNames(getStringCellValue(personalInfo, 11, 1));

		if (tp.getFilingStatus() == 3 || tp.getFilingStatus() == 4 || tp.getFilingStatus() == 5) {
			tp.setSpouseLastName(getStringCellValue(personalInfo, 13, 1));
			tp.setSpouseInitial(getStringCellValue(personalInfo, 14, 1));
			tp.setSpouseFirstName(getStringCellValue(personalInfo, 15, 1));
			tp.setSpouseIdNumber(getStringCellValue(personalInfo, 16, 1));
		}
		
		for(int rp = 0; rp < 6; rp++) {
			if(personalInfo.getRow(18+rp).getCell(1).getCellType() == Cell.CELL_TYPE_BLANK) {
				break;
			}else {
				ownershipOfRps.add(getNumberCellValue(personalInfo, 18+rp, 1));
			}
		}
		
		for(int sp = 0; sp < 6; sp++) {
			if(personalInfo.getRow(25+sp).getCell(1).getCellType() == Cell.CELL_TYPE_BLANK) {
				break;
			}else {
				ownershipOfSps.add(getNumberCellValue(personalInfo, 25+sp, 1));
			}
		}
		
		Double[] oor = ownershipOfRps.toArray(new Double[ownershipOfSps.size()]);
		Double[] oos = ownershipOfSps.toArray(new Double[ownershipOfSps.size()]);

		for (Sheet sheet : wb) {
			if (getNumberCellValue(sheet, 0, 1) == 1) {// Read rental property info, calculate percents
				RentalProperty rp = new RentalProperty();
				rp.setTotalRentalIncome(getNumberCellValue(sheet, 3, 13)*oor[rentalProperty]);
				rp.setTotalExpenses(getNumberCellValue(sheet, 24, 13)*oor[rentalProperty]);
				rp.setPropertyAddress(getStringCellValue(sheet, 28, 1));
				rp.setClosingDate(getStringCellValue(sheet, 29, 1));
				rentalProperty++;
				rentalProperties.add(rp);

			} else if (getNumberCellValue(sheet, 0, 1) == 2) {// Read sold property info, calculate percents
				SoldProperty sp = new SoldProperty();
				sp.setAddress(getStringCellValue(sheet, 1, 1));
				sp.setNetGain(getNumberCellValue(sheet, 8 ,1)*oos[soldProperty]);
				sp.setInvoice(getStringCellValue(sheet, 9, 1));
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
