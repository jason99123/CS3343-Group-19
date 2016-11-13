import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class fee {

	private static int ageGroup;
	private static int start;
	private static int dest;
	private static int quantity;
	private static int method;
	private static AgeGroup ageGroupClass;
	private static Method paymentMethod;
	private static boolean isSecond;
	
	public static void main(String[] args) throws Exception{
		
		PriceCalculator Calculator=PriceCalculator.getInstance();
		try {
			//call for the formula
			Gui g = new Gui();
			g.display();
			Scanner input=new Scanner(System.in);

			ageGroup = askForAgeGroup(input);
			 
			
			System.out.println("Please input Starting Station: ");
			Calculator.outputAllStation();
			
			start = input.nextInt();
			
			System.out.println("Please input Destination Station: ");
			Calculator.outputAllStation();
			dest=input.nextInt();
			
			method = askForMethod(input);
			
			if(method == 1)
			{
				System.out.println("Please indicate whether it is second trip: (y/n)");
				char isSecondChar = input.next().charAt(0);
				
				quantity = 1;
				if(isSecondChar == 'Y')
				{
					isSecond = true;
				}
				
				else
					isSecond = false;
			}
			
			else if(method == 2)
			{
				quantity = askForQuantity(input);
				isSecond = false;
			}
		

			System.out.println("The price is: $"+(double)Math.round(finalCalculation()*10)/10);
			//System.out.println("The price is: $"+finalCalculation(start,dest, Calculator));
		}
		finally {
			System.out.println("Process Completed.");
		}
	}
	
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
		
		if(method == 1)
		{
			paymentMethod = new Octopus();
		}
		
		else
			paymentMethod = new Cash();
		
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
	
	public static int setMethod(int method)
	{
		if(method == 1)
		{
			paymentMethod = new Octopus();
		}
		
		else
			paymentMethod = new Cash();
		
		return method;
	}
	
	public static int setQuantity(int quan)
	{
		quantity = quan;
		return quan;
	}
	
	public static int setAgeGroup(int group)
	{
		if(group == 1)
		{
			ageGroupClass = new Child();
		}
		
		else if(group == 2)
		{
			ageGroupClass = new Student();
		}
		
		else if(group == 3)
		{
			ageGroupClass = new Adult();
		}
		
		else if(group == 4)
		{
			ageGroupClass = new Elderly();
		}
		
		return group;
	}
	
	public static int setStartCode(int startCode)
	{
		start = startCode;
		return start;
	}
	
	public static int setDestCode(int destCode)
	{
		dest = destCode;
		return dest;
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
		
		if(group == 1)
		{
			ageGroupClass = new Child();
		}
		
		else if(group == 2)
		{
			ageGroupClass = new Student();
		}
		
		else if(group == 3)
		{
			ageGroupClass = new Adult();
		}
		
		else if(group == 4)
		{
			ageGroupClass = new Elderly();
		}
		
		return group;
	}

	public static float finalCalculation() {
		float price = 0;
		price = paymentMethod.getBasePrice(start, dest, quantity);
		float discount = ageGroupClass.getDiscount();
		
		if(isSecond)
		{
			return (float) (price*discount*0.8);
		}
		
		else		
			return (float) (price*discount);
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
