import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class fee {

	private static int ageGroup = 0;
	private static int start = 0;
	private static int dest = 0;
	private static int quantity = 0;
	private static int method = 0;
	private static AgeGroup ageGroupClass = null;
	private static Method paymentMethod = null;
	private static boolean isSecond;
	private static String octId = "";
	private static PriceCalculator calculator = PriceCalculator.getInstance();
	private static boolean resetchecker = false;

	public static void main(String[] args) throws Exception {

		try {
			// call for the formula
			// Gui g = new Gui();
			// g.display();

			promptInfo();

			System.out.println("The price is: $" + (double) Math.round(finalCalculation() * 10) / 10);
			// System.out.println("The price is: $"+finalCalculation(start,dest,
			// Calculator));
		} finally {
			System.out.println("Process Completed.");
		}
	}

	private static void promptInfo() {
		/*resetchecker is for reset and make sure that the reset will not overloop*/
		
		Scanner input = new Scanner(System.in);

		ageGroup = askForAgeGroup(input);
		if (!resetchecker)
			start = getStartStation(calculator, input);
		if (!resetchecker)
			dest = getDestinationStation(calculator, input);
		if (!resetchecker)
			method = askForMethod(input);
		if (!resetchecker)
			methodHandling(method, input);
	}

	public static void methodHandling(int _method, Scanner input) {
		if (method == 1) {
			System.out.print("Please enter your octopus id: ");
			octId = input.next();

			if (isSecondTrip(octId)) {
				isSecond = true;
			}

			else
				isSecond = false;

			quantity = 1;
		}

		else if (method == 2) {
			quantity = askForQuantity(input);
			isSecond = false;
		}

	}

	public static int getDestinationStation(PriceCalculator calculator, Scanner input) {
		LineCenter lc = LineCenter.getInstance();
		System.out.println("Please input Destination Station:(input -1 if not know and 0 for reset)");
		calculator.outputAllLine();
		int userInput = input.nextInt();

		while ((userInput < -2) || userInput > 4) {
			System.out.println("Invalid input. Please enter again.:(input -1 if not know and 0 for reset)");
			calculator.outputAllLine();
			userInput = input.nextInt();
		}

		if (userInput == -1) {
			calculator.outputAllStation();
			dest = input.nextInt();
		} else if (userInput == 0) {
			promptInfo();
			resetchecker = true;
		} else {
			calculator.outputAllLineInStation(userInput);
			System.out.println("Please input Destination Station: (input 0 for reset)");
			dest = input.nextInt();
			
			if(userInput == 1)
			{
				while(dest > 17 || dest <= 0)
				{
					System.out.println("Wrong input, please enter again");
					System.out.println("Please input Destination Station:");
					dest = input.nextInt();
				}
			}
			
			else if(userInput == 2)
			{
				while(dest < 18 || dest > 34)
				{
					System.out.println("Wrong input, please enter again");
					System.out.println("Please input Destination Station:");
					dest = input.nextInt();
				}
			}
			
			else if(userInput == 3)
			{
				while(dest < 35 || dest > 42)
				{
					System.out.println("Wrong input, please enter again");
					System.out.println("Please input Destination Station:");
					dest = input.nextInt();
				}
			}
			
			else if(userInput == 4)
			{
				while(dest < 43 || dest > 58)
				{
					System.out.println("Wrong input, please enter again");
					System.out.println("Please input Destination Station:");
					dest = input.nextInt();
				}
			}
		}
		if (userInput != 0)
			System.out
					.println("You have chosen " + lc.getStationByCode(dest).getStation() + " as destination station.");
		return dest;
	}

	public static int getStartStation(PriceCalculator calculator, Scanner input) {
		LineCenter lc = LineCenter.getInstance();
		System.out.println("Please input line of Starting Station: (input -1 if not know and 0 for reset)");
		calculator.outputAllLine();
		int userInput = input.nextInt();
		while ((userInput < -2) || userInput > 4) {
			System.out.println("Invalid input. Please enter again.:(input -1 if not know and 0 for reset)");
			calculator.outputAllLine();
			userInput = input.nextInt();
		}

		if (userInput == -1) {
			calculator.outputAllStation();
			start = input.nextInt();
		} else if (userInput == 0) {
			promptInfo();
			resetchecker = true;
		} else {
			calculator.outputAllLineInStation(userInput);
			System.out.println("Please input Starting Station:");
			start = input.nextInt();
			
			if(userInput == 1)
			{
				while(start > 17 || start <= 0)
				{
					System.out.println("Wrong input, please enter again");
					System.out.println("Please input Starting Station:");
					start = input.nextInt();
				}
			}
			
			else if(userInput == 2)
			{
				while(start < 18 || start > 34)
				{
					System.out.println("Wrong input, please enter again");
					System.out.println("Please input Starting Station:");
					start = input.nextInt();
				}
			}
			
			else if(userInput == 3)
			{
				while(start < 35 || start > 42)
				{
					System.out.println("Wrong input, please enter again");
					System.out.println("Please input Starting Station:");
					start = input.nextInt();
				}
			}
			
			else if(userInput == 4)
			{
				while(start < 43 || start > 58)
				{
					System.out.println("Wrong input, please enter again");
					System.out.println("Please input Starting Station:");
					start = input.nextInt();
				}
			}
		}
		if (userInput != 0)
			System.out.println("You have chosen " + lc.getStationByCode(start).getStation() + " as starting station.");
		return start;
	}

	private static int askForMethod(Scanner input) {
		int method;
		System.out.println("Please choose your payment method");
		System.out.printf("%10s%15s", "Code", "Payment Method\n");
		System.out.printf("%10s%15s", "1", "Octopus\n");
		System.out.printf("%10s%15s", "2", "Ticket\n");
		System.out.printf("%10s%15s", "0", "Reset\n");

		method = input.nextInt();
		while (!(method == 1 || method == 2 || method == 0)) {
			System.out.println("Invalid method. Please enter again.");
			method = input.nextInt();
		}

		if (method == 1) {
			paymentMethod = new Octopus();
		}

		else if (method == 2)
			paymentMethod = new Cash();
		else if (method == 0) {
			promptInfo();
			resetchecker = true;
		}
		return method;
	}

	private static int askForQuantity(Scanner input) {
		System.out.println("Please input the number of tickets:(input 0 for reset)");
		int amount = 0;
		amount = input.nextInt();
		while (!(amount >= 0) || amount > 20) {
			System.out.println("Invalid input. Please enter again.");
			amount = input.nextInt();
		}
		if (amount == 0) {
			promptInfo();
			resetchecker = true;
		}
		System.out.println("You are going to buy " + amount + " tickets.");
		return amount;
	}

	public static int setMethod(int method) {
		if (method == 1) {
			paymentMethod = new Octopus();
		}

		else
			paymentMethod = new Cash();

		return method;
	}

	public static int setQuantity(int quan) {
		quantity = quan;
		return quan;
	}

	public static int setAgeGroup(int group) {
		if (group == 1) {
			ageGroupClass = new Child();
		}

		else if (group == 2) {
			ageGroupClass = new Student();
		}

		else if (group == 3) {
			ageGroupClass = new Adult();
		}

		else if (group == 4) {
			ageGroupClass = new Elderly();
		}

		return group;
	}

	public static int setStartCode(int startCode) {
		start = startCode;
		return start;
	}

	public static int setDestCode(int destCode) {
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
		System.out.printf("%10s%15s", "0", "Reset\n");

		group = in.nextInt();
		while (group < 0 || group > 4) {
			System.out.println("Invalid age group, please enter again:");
			group = in.nextInt();

		}

		if (group == 1) {
			ageGroupClass = new Child();
		}

		else if (group == 2) {
			ageGroupClass = new Student();
		}

		else if (group == 3) {
			ageGroupClass = new Adult();
		}

		else if (group == 4) {
			ageGroupClass = new Elderly();
		} else if (group == 0) {
			promptInfo();
			resetchecker = true;
		}
		return group;
	}

	public static float finalCalculation() {
		float price = 0;
		price = paymentMethod.getBasePrice(start, dest, quantity);
		float discount = ageGroupClass.getDiscount();

		if (isSecond) {
			return (float) (price * discount * 0.8);
		}

		else
			return (float) (price * discount);
	}

	public static int getAgeGroup() {
		return ageGroup;
	}

	public static int getQuantity() {
		return quantity;
	}

	public static int getMethod() {
		return method;
	}

	public static boolean isSecondTrip(String id) {
		try {

			BufferedReader reader = new BufferedReader(new FileReader("Railway Fee Calculation/data/record.csv"));

			String row;

			while ((row = reader.readLine()) != null) {
				if (row.equalsIgnoreCase(id)) {
					System.out.println("The outopus is second trip");
					return true;
				}
			}
			
			System.out.println("The outopus is not second trip");
			return false;

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

}
