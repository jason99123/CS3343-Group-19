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
		readData();
	}
	
	public static PriceCalculator getInstance(){
		return instance;
	}
	
	public float getBasePrice(String start,String dest) {
		
		int countS=0;
		int countD=0;
		
		for(Station s:StationList){
			countS++;
			if(s.getString().equals(start)){
				break;
			}
		}
		
		for(Station s:StationList){
			countD++;
			if(s.getString().equals(dest)){
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
	public void readData(){ //read csv data
		try {
			BufferedReader reader= new BufferedReader(new FileReader("data/StationList.csv")); //import csv file
			
			String row=null; //no line is read at first
			
			while((row=reader.readLine())!=null){
				
				String data[]=row.split(","); //split columns in each rows
				
				Station newStation= new Station(data[0]);
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

}
