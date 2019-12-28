package apache_poi;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import product.Product;

public class WriteExcel {
	
	// instance a file output stream with the input foulder
	public static FileOutputStream getFileOutput(String fouder) throws FileNotFoundException {
		return new FileOutputStream(new File(fouder));
	}
	
	// create new tab
	public static XSSFSheet createSheet(XSSFWorkbook workbook) {
		return workbook.createSheet(date());
	}
	
	// create header
	public static void header(XSSFSheet sheet) {
		Row row = sheet.createRow(0);
		Cell cell0 = row.createCell(0);
		cell0.setCellValue("Code");
		
		Cell cell1 = row.createCell(1);
		cell1.setCellValue("Code Type");
		
		Cell cell2 = row.createCell(2);
		cell2.setCellValue("Brand Suggested");
	}
	
	// create tab body and fill in with data	
	public static void body(XSSFSheet sheet, ArrayList<Product> list) {
		
		int r= 1;
		
		for(Product p: list) {
			
			int c= 0;
			Row row = sheet.createRow(r);
			
			Cell cell0 = row.createCell(c);
			Cell cell1 = row.createCell(c+1);
			Cell cell2 = row.createCell(c+2);
			
			cell0.setCellValue(p.getCode());
			cell1.setCellValue(p.getTypeCode());
			cell2.setCellValue(p.getSuggestedBrand());
			
			r++;
		}
	}
	
	// current date and time to name tab
	public static String date() {
		
		TimeZone.setDefault(TimeZone.getTimeZone("GMT-03:00"));
	    Date date = Calendar.getInstance().getTime();
	    SimpleDateFormat sdf = new SimpleDateFormat("ddMMyy-HHmmss",new Locale("BR"));
	    
	    return sdf.format(date);
	}
}
