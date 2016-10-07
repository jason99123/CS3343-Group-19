import java.util.Scanner;

public class fee {

	public static void main(String[] args) {
		
		try {
			//call for the formula
			Scanner input=new Scanner(System.in);
			String start;
			String dest;
			
			System.out.println("Please input Starting Station: ");
			start=input.nextLine();
			
			System.out.println("Please input Destination Station: ");
			dest=input.nextLine();

			System.out.println("The price is: $"+finalCalculation(start,dest));
		}
		finally {
			System.out.println("Process Completed.");
		}
	}

	private static float finalCalculation(String start,String dest) {
		float price;
		PriceCalculator Calculator=PriceCalculator.getInstance();
		
		price = Calculator.getBasePrice(start,dest);
		return price;
	}


}
