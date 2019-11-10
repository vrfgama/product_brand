package validation;

import product.Product;

public class ValidationGTIN13 {

	
	public static String prefix(Product product) {
		System.out.println("prefix  "+product.getCode().substring(0, 3));
		return product.getCode().substring(0, 3);
	}
	
	
	public static boolean shred(Product product) {
		char[] pair= new char[6];
		char[] odd= new char[6];
		int j= 0;
		int k= 0;
		
		for(int i= 0; i<product.getCode().length()-1; i++) {	
			
			if(i%2 != 0) {
				//System.out.println("impar "+i);
				odd[j]= product.getCode().charAt(i);
				//System.out.println("char "+product.getGtin().charAt(i));
				//System.out.println("char "+odd[j]);
				j++;
			}
			else {
				//System.out.println("par "+i);
				pair[k]= product.getCode().charAt(i);
				//System.out.println("char "+product.getGtin().charAt(i));
				//System.out.println("char "+pair[k]);
				k++;
			}
		}
		
		
		if( calculation(odd,pair) == Integer.parseInt(product.getCode().substring(product.getCode().length()-1, product.getCode().length()))) {
			return true;
		}else {
			return false;
		}
	}
	
	public static int calculation(char[]odd, char[] pair) {
		
		int x=0;
		int y=0;
		int z=0;
		int sum=0;
		
		for(int i= 0; i<pair.length; i++) {
			x+= Character.getNumericValue(odd[i])*3; 
			//System.out.println(x);
			y+= Character.getNumericValue(pair[i]);
			//System.out.println(y);
			
		}
		
		sum= x+y;
		z= sum;
		System.out.println(x);
		System.out.println(y);		
		System.out.println(sum);
		//System.out.println(z);
		
		while(z %10 != 0) z ++;
		
		System.out.println(z);
		System.out.println(z-sum);
		
		return z - sum;
	}
}
