//Matthew Butler
//CS 280: Programming Languages Concepts
// Section CS - 280-102
// Professor Kapleau

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class DecentParsing {
	/*
	A-> I|=I | |I=E
	E->D| (A)
	I->a|b|c
	D -> 0|1


 
A -> I = E
E -> T + E | T - E | T
T -> P * T | P / T | P
P -> I | (E)
I -> a | b | ... | y | z


	*/
	static 	String s=" ";

	static int idx =0;


public static void main (String [] args)
{	
	
	 File in = new File("input.dat");
     Scanner scan = null;

     try {
         scan = new Scanner(in);
     }
     catch (FileNotFoundException e) {
        System.out.println("hey it missed");
    	 System.exit(1);
     }

     while (scan.hasNext()) {
        s = scan.next();
        idx = 0;
     
        if(A()&& s.length() == idx){
        	System.out.println ("the string "+ s + "  in the the language");
        	}else{
        		System.out.println ("the string "+ s +" not in language");
        		}
     }

     scan.close();


	

	

	}


static boolean A(){ 
//cout<<"A() \n";
	if(I()){
		if (idx<=s.length()&& s.charAt(idx)== '='){
		
			++idx ;

		if (E()){
			return true;
		} 
		//falls through if not either and returns niether pointer arthnetic

		}
	}


	return false;
}



static boolean E(){ 

//cout<<"E() \n";
	if (T()){
		if(idx<s.length()&&(s.charAt(idx)=='-'  ||s.charAt(idx)=='+')){
			idx ++;
			if(E()) 

				return true;
				else {return false;}
		}
		return true;
	}
	return false;
}

static boolean T(){ 
//cout<<"T() \n";
	if (P()) {
	
		if(idx<s.length()&&(s.charAt(idx)=='*' ||s.charAt(idx)=='/')){
			idx ++;
			if(T()) 
         	return true;
    
		else {return false;}//add this line?
		}
		return true;
	}
	return false;
}
static boolean P(){ 
//cout<<"P() \n";
	if(idx<s.length()&& s.charAt(idx)=='('){
	++idx ;
	if(E()){
      
		if (idx<s.length()&&s.charAt(idx)==')'){
			++idx ;
   	return true;
			}
		}
	}
	if (I()){
		return true;
	}


	return false;
}


static boolean I(){ 
//cout<<"I() \n";
	if(idx<s.length()&&(s.charAt(idx) >= 'a' && s.charAt(idx) <= 'z')){
	++idx ;// this line consumes the token see what the fuck
		return true;}


	return false;
}
}

