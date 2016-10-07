import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class PriceCalculator {
	
	private static PriceCalculator instance = new PriceCalculator();
	private List<Station> stationList = new ArrayList<>();
	
	private PriceCalculator(){
		readData();
	}
	
	public static PriceCalculator getInstance(){
		return instance;
	}
	
	public float getBasePrice(String start, String dest) {
		
		int countS = 0;
		int countD = 0;
		
		for(Station s: stationList){
			countS++;
			if(s.getString().equals(start)){
				break;
			}
		}
		
		for(Station s: stationList){
			countD++;
			if(s.getString().equals(dest)){
				break;
			}
		}
		
		System.out.println("Distances:" + (countD - countS) + " stations");

		return 1;
	}
	
	
	
	@SuppressWarnings("resource")
	public void readData(){ //read csv data
		try {
			BufferedReader reader = new BufferedReader(new FileReader("data/StationList.csv")); //import csv file
			
			String row = null; //no line is read at first
			
			while((row = reader.readLine()) != null){
				
				String data[] = row.split(","); //split columns in each rows
				
				Station newStation = new Station(data[0]);
				stationList.add(newStation);
				
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
