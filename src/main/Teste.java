package main;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Teste {
	public static void main(String[] args) throws IOException {

		FileInputStream fileInput = new FileInputStream(new File("content/teste2.xlsx"));
		XSSFWorkbook workbook = new XSSFWorkbook(fileInput);
		XSSFSheet sheet = workbook.getSheetAt(0);

		System.out.println(sheet.getLastRowNum());
		Row row = null;
		Cell cell = null;
		int index = -1;

		row = sheet.getRow(0);

		// Cell cell= row.getCell(1);
		// Iterator<Cell> iteratorCell= row.cellIterator();
		// System.out.println(cell.getStringCellValue());

		Iterator<Cell> iteratorCell = row.cellIterator();
		while (iteratorCell.hasNext()) {
			cell = iteratorCell.next();
			if (cell.getStringCellValue().contentEquals("coluna3")) {
				System.out.println(cell.getStringCellValue());
				System.out.println(cell.getColumnIndex());
				index = cell.getColumnIndex();
			}
		}

		Iterator<Row> iteratorRow = sheet.iterator();
		while (iteratorRow.hasNext()) {
			row = iteratorRow.next();
			cell = row.getCell(index);

			switch (cell.getCellType()) {
			case STRING:
				System.out.println(cell.getStringCellValue());
				break;
			case NUMERIC:
				System.out.println((int)cell.getNumericCellValue());
				break;
			}

		}
		fileInput.close();
	}
}
