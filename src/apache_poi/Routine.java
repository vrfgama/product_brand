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

public class Routine {

	static ArrayList<Product> list = new ArrayList<Product>();

	public static FileInputStream getFileInput(String fouder) throws FileNotFoundException {
		return new FileInputStream(new File(fouder));
	}

	public static XSSFWorkbook getXSSFWorkbook(FileInputStream fileInput) throws IOException {
		return new XSSFWorkbook(fileInput);
	}

	public static XSSFSheet getXSSFSheet(XSSFWorkbook workbook) {
		return workbook.getSheetAt(0);
	}

	public static int getEanIndex(XSSFSheet sheet) {

		int index = -1;
		Row row = sheet.getRow(0);

		Iterator<Cell> iteratorCell = row.cellIterator();

		while (iteratorCell.hasNext()) {
			Cell cell = iteratorCell.next();
			if (cell.getStringCellValue().contentEquals("gtin")) {
				index = cell.getColumnIndex();
			}
		}

		return index;
	}

	
	public static ArrayList<Product> getEanValues(XSSFSheet sheet, int index) {

		for (int i = 1; i < sheet.getLastRowNum() + 1; i++) {

			Product product = new Product();
			Row row = sheet.getRow(i);
			Cell cell = row.getCell(index);

			switch (cell.getCellType()) {
				case STRING:
					product.setCode(cell.getStringCellValue());
					System.out.println("string "+product.getCode());
					break;
				case NUMERIC:
					product.setCode(Long.toString((long) cell.getNumericCellValue()));
					System.out.println("numeric "+product.getCode());
					break;
				default: // to do
					break;
			}

			list.add(product);
		}

		return list;
	}

}
