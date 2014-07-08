//Matthew Butler
//Cs 114 Lab
import java.util.Scanner;

public class Linkrun
{
    private static Scanner scan;
    private static Scanner scanw;
    private static LinkedList list = new LinkedList();


    public static void main(String[] args)
    {
	scan = new Scanner(System.in);
	scanw = new Scanner(System.in);
	printMenu();
	int choice = scan.nextInt();
	while (choice != 0)
	    {
		dispatch(choice);
		printMenu();
		choice = scan.nextInt();

	    }
    }

    public static void dispatch(int choice)
    {
	String Val=" ";
	
	switch(choice)
	    {
	    case 0: 
		System.out.println("Bye!");
		break;

	    case 1:   
		System.out.println("Enter a word to add to front");
		Val = scanw.next();
		list.addToFront(Val);
		break;

	    case 2:   
		System.out.println("Enter a word to add to end");
		Val = scanw.next();
		list.addToEnd(Val);
		break;

	    case 3:  
		list.removeFirst();
		break;

	    case 4: 
		list.print();
		break;
	    case 5:  
		System.out.println(list.head());
		break;
	    default:
		System.out.println("Sorry you can't use that");
	    
	    }
    }

  
    public static void printMenu()
    {
	System.out.println("\n   Menu   ");
	System.out.println("   ====");
	System.out.println("0: Exit");
	System.out.println("1: Add a word to the front of the list");
	System.out.println("2: Add a word to the end of the list");
	System.out.println("3: Removes a word from the front of the list");
	System.out.println("4: Print the list");
	System.out.println("5: Print the Head element ");
	System.out.print("\nEnter your choice: ");
    }
}
 


