package control;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import apache_poi.ReadExcel;
import apache_poi.WriteExcel;
import product.Product;
import validation.ValidationGTIN13;

public class Control {
	
	static int index;
	static ArrayList<Product> list= new ArrayList<Product>();
	
	public static void run() {

		try {
			
			// read excel
			FileInputStream fileInput= ReadExcel.getFileInput("content/exemplo.xlsx");
			XSSFWorkbook workbook= ReadExcel.getXSSFWorkbook(fileInput);
			XSSFSheet sheet= ReadExcel.getXSSFSheet(workbook);
			
			index= ReadExcel.getEanIndex(sheet);
			list = ReadExcel.getEanValues(sheet, index);
			validation(list);
			
			fileInput.close();
			
			// write in excel
			sheet= WriteExcel.createSheet(workbook);
			WriteExcel.header(sheet);
			WriteExcel.body(sheet,list);
			
			FileOutputStream fileOutput = WriteExcel.getFileOutput("content/exemplo.xlsx");
			workbook.write(fileOutput);
			fileOutput.close();
			
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	// validates by counting the number of digits of the code, checking prefix and check verifying digit
	public static void validation(ArrayList<Product> list) {
		
		for(Product p: list) {
			
			switch(p.getCode().length()) {
			case 13:
				
				if(ValidationGTIN13.prefix(p).equals("789") && ValidationGTIN13.shred(p) == true ) {
					p.setTypeCode("GTIN-13");
				}else {
					p.setTypeCode("Código Interno");
				}
				
				
				break;
			case 11:
				p.setTypeCode("Outro");
				break;
			case 8:
				break;
			default:
				p.setTypeCode("Outro");
				break;
			}
		}
		
	}

}
