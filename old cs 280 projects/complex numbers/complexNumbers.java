//Matthew Butler
//CS 280: Programming Languages Concepts
// Section CS - 280-102
// Professor Kapleau
public class complexNumbers {
	 double a=0,b=0,x=0,y=0;
	void complexNumber(double a, double b, double c, double d){
		this.a=a;
		this.b=b;
		x=c;
		y=d;
		// double x=0,y=9;
		System.out.println("\n \n the complex numbers "+"("+ a+" + "+b+'i'+")"
				+"("+ x+" + "+y+'i'+")"+ " will be used for computation \n");
		
		System.out.println("----------------------------------------------------------------------------");
		 addition    (a,b,x,y);
		 subtraction (a,b,x,y);
		 mutliply    (a,b,x,y);
		 divid       (a,b,x,y);
	}
 static public void addition( double a, double b, double c, double d){
	 double cReal=0,cImag=0;
  
    cReal=(a+c);
	cImag=(b+d);  
 
  System.out.println("addition ="+"("+cReal+" + "+cImag+'i'+")");
  }
 static public void mutliply( double a, double b, double c, double d){
	    double cReal=0,cImag=0;
	  
	    cReal=((a*c)-(b*d));
		cImag=((b*c)+(a*d)); 
	 
	  System.out.println("mutiply ="+"("+cReal+" + "+cImag+'i'+")");
	  }
 
 
 static public void divid( double a, double b, double c, double d){
	 double cReal=0,cImag=0;
	  
	    cReal=(  ( (a*c)+(b*d) ) / ( (c*c)+(d*d) )  );
		cImag=(  ( (b*c)-(a*d) ) / ( (c*c)+(d*d) )  ); 
	  System.out.println("divid ="+"("+cReal+" + "+cImag+'i'+")");
	  }


 static public void subtraction ( double a, double b, double c, double d){
	    double cReal=0,cImag=0;
	  
	    cReal=(a-c);
		cImag=(b-d);  
	 
	  System.out.println("subtraction ="+"("+cReal+" + "+cImag+'i'+")");
	  }


}