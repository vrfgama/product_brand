package apache_poi;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import product.Product;

public class ReadExcel {

	static ArrayList<Product> list = new ArrayList<Product>();
	
	// instance a file input stream with the input foulder
	public static FileInputStream getFileInput(String fouder) throws FileNotFoundException {
		return new FileInputStream(new File(fouder));
	}
	
	// instantiate a workbook
	public static XSSFWorkbook getXSSFWorkbook(FileInputStream fileInput) throws IOException {
		return new XSSFWorkbook(fileInput);
	}

	// return index from first tab
	public static XSSFSheet getXSSFSheet(XSSFWorkbook workbook) {
		return workbook.getSheetAt(0);
	}

	// return index of desired column
	public static int getEanIndex(XSSFSheet sheet) {

		int index = -1;
		Row row = sheet.getRow(0); // first line 

		Iterator<Cell> iteratorCell = row.cellIterator();

		while (iteratorCell.hasNext()) {
			Cell cell = iteratorCell.next();
			if (cell.getStringCellValue().contentEquals("gtin")) {
				index = cell.getColumnIndex();
			}
		}

		return index;
	}

	// get ean and instantiate product object
	public static ArrayList<Product> getEanValues(XSSFSheet sheet, int index) {

		for (int i = 1; i < sheet.getLastRowNum() + 1; i++) {

			Product product = new Product();
			Row row = sheet.getRow(i);
			Cell cell = row.getCell(index);

			switch (cell.getCellType()) {
				case STRING:
					product.setCode(cell.getStringCellValue());
					
					break;
				case NUMERIC:
					product.setCode(Long.toString((long) cell.getNumericCellValue()));
					
					break;
				default: // to do
					break;
			}

			list.add(product);
		}

		return list;
	}

}
