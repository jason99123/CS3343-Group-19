import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class PriceCalculator {
	
	private static PriceCalculator instance=new PriceCalculator();
	private ArrayList<Station>StationList = new ArrayList<>();
	
	private PriceCalculator(){
		readallStation();
	}
	
	public static PriceCalculator getInstance(){
		return instance;
	}
	
	public float getBasePrice(int start2,int dest2) {
		
		int countS=0;
		int countD=0;
		
		for(Station s:StationList){
			countS++;
			if(s.getStation().equals(start2)){
				break;
			}
		}
		
		for(Station s:StationList){
			countD++;
			if(s.getStation().equals(dest2)){
				break;
			}
		}
		
		if((countD-countS)<=3){
			return 3;
		}else if((countD-countS)<=6 && (countD-countS)>3 ){
			return 6;
		}else if((countD-countS)<=9 && (countD-countS)>6 ){
			return 9;
		}else if((countD-countS)<=12 && (countD-countS)>9){
			return 12;
		}
			return -1;
	}
	
	
	
	@SuppressWarnings("resource")
	public void readallStation(){ //read csv data
		try {
			BufferedReader reader= new BufferedReader(new FileReader("data/StationList.csv")); //import csv file
			
			String row=null; //no line is read at first
			
			while((row=reader.readLine())!=null){
				
				String data[]=row.split(","); //split columns in each rows
				
				Station newStation= new Station(data[0],data[1]);
				StationList.add(newStation);
				
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block (need to edit it later)
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block (need to edit it later)
			e.printStackTrace();
		}
		
		
	}

	public void outputAllStation() {
		System.out.printf("%10s%15s\n", "Code", "Station");
		for (Station station: StationList){
			System.out.printf("%10s%15s\n", station.getCode(), station.getStation());
			
		}
		
	}

}
