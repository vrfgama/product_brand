package validation;

import product.Product;

public class ValidationGTIN13 {

	// check prefix
	public static String prefix(Product product) {
		return product.getCode().substring(0, 3);
	}
	
	
	public static boolean shred(Product product) {
		char[] pair= new char[6];
		char[] odd= new char[6];
		int j= 0;
		int k= 0;
		
		// separates gtin-13 digits into pairs and odd
		for(int i= 0; i<product.getCode().length()-1; i++) {	
			
			if(i%2 != 0) {
				odd[j]= product.getCode().charAt(i);
				j++;
			}
			else {
				pair[k]= product.getCode().charAt(i);
				k++;
			}
		}
		
		// compare calculated number with check digit
		if( calculation(odd,pair) == Integer.parseInt(product.getCode().substring(product.getCode().length()-1, product.getCode().length()))) {
			return true;
		}else {
			return false;
		}
	}
	
	// calculate comparison number with check digit
	public static int calculation(char[] odd, char[] pair) {
		
		int x=0;
		int y=0;
		int z=0;
		int sum=0;
		
		for(int i= 0; i<pair.length; i++) {
			x+= Character.getNumericValue(odd[i])*3; 
			y+= Character.getNumericValue(pair[i]);
			
		}
		
		sum= x+y;
		z= sum;
		
		while(z % 10 != 0) z ++;
		
		return z - sum;
	}
}
