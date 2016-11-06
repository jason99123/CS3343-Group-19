
public class Octopus implements Method{
	
	private PriceCalculator pc = PriceCalculator.getInstance();

	public float getBasePrice(int start, int dest, int quantity) {
		
		quantity = 1;
		
		double price = 0;
		double pricePerKM = 0;
		double distance = pc.stationDistance(start, dest);
		
		if(distance<1.5 && distance>0)
			pricePerKM = 2;
		else if(distance > 1.5 && distance < 8)
			pricePerKM = 1.5;
		else
			pricePerKM = 0.6;
		
		price = pricePerKM * distance * quantity;
		
		return (float) price;
	}

}
