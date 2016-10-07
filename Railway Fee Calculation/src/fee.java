
public class fee {

	public static void main(String[] args) {
		
		try {
			//call for the formula

			System.out.println("The price is: $"+finalCalculation());
		}
		finally {
			System.out.println("Process Completed.");
		}
	}

	private static float finalCalculation() {
		float price;
		PriceCalculator Calculator=PriceCalculator.getInstance();
		
		Calculator.readData();
		price = Calculator.getBasePrice();
		return price;
	}


}
