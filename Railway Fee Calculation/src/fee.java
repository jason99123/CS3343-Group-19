import java.util.Scanner;

public class fee {

	private static int ageGroup;
	private static int start;
	private static int dest;
	private static int quantity;
	private static int method;
	public static void main(String[] args) {
		
		try {
			//call for the formula
			Scanner input=new Scanner(System.in);
			
			ageGroup = askForAgeGroup(input);
			
			System.out.println("Please input Starting Station: ");
			start = input.nextInt();
			
			System.out.println("Please input Destination Station: ");
			dest=input.nextInt();
			
			quantity = askForQuantity(input);
			
			method = askForMethod(input);

			System.out.println("The price is: $"+finalCalculation(start,dest));
		}
		finally {
			System.out.println("Process Completed.");
		}
	}

	private static int askForMethod(Scanner input) {
		int method;
		System.out.println("Please choose your payment method");
		System.out.format("%10s%15s", "Code", "Payment Method");
		System.out.format("%10s%15s", "1", "Octopus");
		System.out.format("%10s%15s", "2", "Ticket");
		
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
		System.out.format("%10s%15s", "Code", "Age Group");
		
		System.out.format("%10s%15s", "1", "Child");
		System.out.format("%10s%15s", "2", "Student");
		System.out.format("%10s%15s", "3", "Adult");
		System.out.format("%10s%15s", "4", "Elderly");
		
		group = in.nextInt();
		while (group<=0||group>4)
		{
			System.out.println("Invalid age group, please enter again:");
			group = in.nextInt();
			
		}
		return group;
	}

	private static float finalCalculation(String start,String dest) {
		float price;
		PriceCalculator Calculator=PriceCalculator.getInstance();
		
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
