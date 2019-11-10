package control;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import apache_poi.Routine;
import apache_poi.WriteExcel;
import product.Product;
import validation.ValidationGTIN13;

public class Control {
	
	static int index;
	static ArrayList<Product> list= new ArrayList<Product>();
	
	public static void run() {

		try {
			
			// read excel
			FileInputStream fileInput= Routine.getFileInput("content/exemplo.xlsx");
			XSSFWorkbook workbook= Routine.getXSSFWorkbook(fileInput);
			XSSFSheet sheet= Routine.getXSSFSheet(workbook);
			
			index= Routine.getEanIndex(sheet);
			list = Routine.getEanValues(sheet, index);
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
	
	
	public static void validation(ArrayList<Product> list) {
		
		for(Product p: list) {
			
			switch(p.getCode().length()) {
			case 13:
				System.out.println(ValidationGTIN13.prefix(p));
				System.out.println(ValidationGTIN13.shred(p));
				
				if(ValidationGTIN13.prefix(p).equals("789") && ValidationGTIN13.shred(p) == true ) {
					p.setTypeCode("GTIN-13");
				}else {
					p.setTypeCode("Código Interno");
				}
				
				
				break;
			case 11:
				System.out.println("case 11: "+p.getCode());
				break;
			case 8:
				break;
			default:
				System.out.println("default: "+p.getCode());
				break;
			}
		}
		
	}
	
	
	
	// para ver resultado
	public static String teste() {
		String s= "";
		for(Product p: list) {
			s+= p.getCode()+"\n";
		}
		return s;
	}

}
