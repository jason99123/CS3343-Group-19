import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class PriceCalculator {
	
	private static PriceCalculator instance=new PriceCalculator();
	
	private PriceCalculator(){}
	
	public static PriceCalculator getInstance(){
		return instance;
	}
	
	public float getBasePrice() {
		
		return 1;
	}
	
	@SuppressWarnings("resource")
	public void readData(){
		try {
			BufferedReader reader= new BufferedReader(new FileReader("data/StationList.csv")); //import csv file
			
			String row=null; //no line is read at first
			
			while((row=reader.readLine())!=null){
				
				String data[]=row.split(","); //split columns in each rows
				
				for(String s:data){
					System.out.print(s); //test code for data reading
				}
				
				System.out.println(); //test code for data reading
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block (need to edit it later)
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block (need to edit it later)
			e.printStackTrace();
		}
		
		
	}

}
