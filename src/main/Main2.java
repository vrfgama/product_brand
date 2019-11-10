package main;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class Main2 {
	public static void main(String[] args) throws  IOException {
		
		FileInputStream fileInput= new FileInputStream(new File("content/teste2.xlsx"));
		XSSFWorkbook workbook = new XSSFWorkbook(fileInput);
		
		System.out.println(workbook.getNumberOfSheets());
        
		XSSFSheet sheet = workbook.getSheetAt(0);
        //***************
        
        System.out.println(sheet.getSheetName());
        /*
        System.out.println(sheetAlunos.getPhysicalNumberOfRows()); // numero de linhas da planilha
		System.out.println(sheetAlunos.getActiveCell()); // celula selecionada
		System.out.println(sheetAlunos.getLastRowNum());
		
		Iterator<Row> rowIterator = sheetAlunos.iterator();
		
		while (rowIterator.hasNext()) {
			Row row = rowIterator.next();
            Iterator<Cell> cellIterator = row.cellIterator();
		  
		while (cellIterator.hasNext()) {
            	Cell cell = cellIterator.next();
            	
            	//System.out.println(cell.getColumnIndex());
            	System.out.println(cell.getStringCellValue());
            }
		}
		*/
        //****************
        
        Row row = sheet.getRow(0);
        Iterator<Cell> coluna = row.cellIterator();
        int index=-1;
        while(coluna.hasNext()) {
        	Cell cell = coluna.next();
        	System.out.println(cell.getStringCellValue());
        	if(cell.getStringCellValue().equals("coluna2")) {
        		System.out.println(cell.getColumnIndex());
        		Cell cell2= row.getCell(index);
            	System.out.println(cell2);
            }	
        	
        }
        
        
        
	}

	
}
