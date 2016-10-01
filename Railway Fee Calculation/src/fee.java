
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
		price = BasePrice.getBasePrice();
		return price;
	}


}
