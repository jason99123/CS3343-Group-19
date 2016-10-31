import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class fee {

	private static int ageGroup;
	private static int start;
	private static int dest;
	private static int quantity;
	private static int method;
	// Added by Ben on 14/10
	private static int isSecond;
	
	public static void main(String[] args) {
		
		PriceCalculator Calculator=PriceCalculator.getInstance();
		try {
			//call for the formula
			Scanner input=new Scanner(System.in);
			
			ageGroup = askForAgeGroup(input);
			 
			
			System.out.println("Please input Starting Station: ");
			Calculator.outputAllStation();
			start = input.nextInt();
			
			
			System.out.println("Please input Destination Station: ");
			Calculator.outputAllStation();
			dest=input.nextInt();
			
			System.out.println("Please choose whether it is second trip: ");
			isSecond = input.nextInt();
			
			quantity = askForQuantity(input);
			
			method = askForMethod(input);


			System.out.println("The price is: $"+Calculator.finalCalculation(ageGroup, quantity, method, start, dest));
			//System.out.println("The price is: $"+finalCalculation(start,dest, Calculator));
		}
		finally {
			System.out.println("Process Completed.");
		}
	}
/*
	// Added by Ben on 14/10/2016
	private static float finalCalculation(int start, int dest, PriceCalculator calculator)
	{
		class Record_Stub
		{
			public boolean recordExist(String s)
			{
				if("123".equalsIgnoreCase(s))
					return true;
				return false;
			}
		}
		
		Record_Stub rst = new Record_Stub();
		
		PriceCalculator pc = PriceCalculator.getInstance();
		pc.readData();
		// Need to translate the int to string
		float basePrice = pc.getBasePrice("HKU", "Chai Wan");
		
		
		// Octopus
		if(method == 1)
		{
			// Elderly
			if(ageGroup == 4)
			{
				float discount = (float) (basePrice*0.1);
				return (rst.recordExist("123"))?2-discount:2;
			}
			
			// Student / Child
			else if(ageGroup == 1 || ageGroup == 2 )
			{
				float discount = (float) (basePrice/2*0.1);
				System.out.println("Discount=" + discount);
				System.out.println("BasePrice=" + basePrice);
				return (rst.recordExist("123"))?basePrice/2-discount:basePrice/2;
			}
			
			else
			{
				float discount = (float) (basePrice*0.1);
				return (rst.recordExist("123"))?basePrice-discount:basePrice;
			}		
		}
		
		else
		{
			if(ageGroup == 4 || ageGroup == 1)
			{
				return basePrice/2;
			}
			
			return basePrice;
		}
	}
*/
	private static int askForMethod(Scanner input) {
		int method;
		System.out.println("Please choose your payment method");
		System.out.printf("%10s%15s", "Code", "Payment Method\n");
		System.out.printf("%10s%15s", "1", "Octopus\n");
		System.out.printf("%10s%15s", "2", "Ticket\n");
		
		method = input.nextInt();
		while (!(method==1||method==2))
		{
			System.out.println("Invalid method. Please enter again.");
			method = input.nextInt();
		}
		return method;
	}

	private static int askForQuantity(Scanner input) {
		System.out.println("Please input the number of tickets:");
		int amount = 0;
		amount = input.nextInt();
		while(!(amount>0)){
			System.out.println("Invalid input. Please enter again.");
			amount = input.nextInt();
		}
		return amount;
	}

	private static int askForAgeGroup(Scanner in) {
		
		int group = 1;
		System.out.println("Please input your age group code: ");
		System.out.printf("%10s%15s", "Code", "Age Group\n");
		
		System.out.printf("%10s%15s", "1", "Child\n");
		System.out.printf("%10s%15s", "2", "Student\n");
		System.out.printf("%10s%15s", "3", "Adult\n");
		System.out.printf("%10s%15s", "4", "Elderly\n");
		
		group = in.nextInt();
		while (group<=0||group>4)
		{
			System.out.println("Invalid age group, please enter again:");
			group = in.nextInt();
			
		}
		return group;
	}

	private static float finalCalculation(int start,int dest, PriceCalculator Calculator) {
		float price;
		
		
		price = Calculator.getBasePrice(start,dest);
		return price;
	}
	
	public static int getAgeGroup()
	{
		return ageGroup;
	}
	
	public static int getQuantity()
	{
		return quantity;
	}
	
	public static int getMethod()
	{
		return method;
	}
}
