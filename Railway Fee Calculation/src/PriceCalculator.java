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
	
	public double getDiscountFromAge(int ageGroup){
		if(ageGroup == 1 || ageGroup ==2)
			return 0.5;
		else if (ageGroup == 4)
			return 0.2;
		else
			return 1;
	}
	
	public boolean octopusCechker(int method){
		if (method == 1)
			return true;
		else 
			return false;
	}
	
	
	@SuppressWarnings("resource")
	public void readallStation(){ //read csv data
		try {
			BufferedReader reader= new BufferedReader(new FileReader("data/StationList.csv")); //import csv file
			
			String row=null; //no line is read at first
			
			while((row=reader.readLine())!=null){
				
				String data[]=row.split(","); //split columns in each rows
				
				Station newStation= new Station(data[0],data[1],data[2]);
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


	public double stationDistance(int start,int dest){
		Station startStation=null;
		Station destStation = null;
		double totalDistance = 0;
		for(Station e:StationList){
			if(e.getCode()==start)
				startStation = e;
			if(e.getCode()==dest)
				destStation=e;
		}
		
		/*for(int i = startStation.getCode();i<destStation.getCode();i++){
			Station temp=StationList.get(i);
			if(i==start)
				continue;
			else
			totalDistance += temp.getDistance();
			}*/
		
		int startIndex = StationList.indexOf(startStation);
		int loopSIndex = startIndex;
		int destIndex = StationList.indexOf(destStation);
		int loopDIndex = destIndex;
		double leftDistance = 0;
		double rightDistance = 0;
		
		// Go left
		if(startIndex > destIndex){
			while(loopSIndex > 0)
			{
				Station prevStation = StationList.get(loopSIndex - 1);
				leftDistance = leftDistance + StationList.get(loopSIndex).getDistance();
				
				if(prevStation == destStation)
				{
					break;
				}
				
				loopSIndex--;
			}
		}
		
		else
		{
			// Go right
			while(loopSIndex < StationList.size()-1)
			{
				Station nextStation = StationList.get(loopSIndex + 1);
				rightDistance = rightDistance + StationList.get(loopSIndex+1).getDistance();
				
				if(nextStation == destStation)
				{
					break;
				}
				
				loopSIndex++;
			}
		}
		
		if(leftDistance == 0)
		{
			totalDistance = rightDistance;
		}
		
		else if(rightDistance == 0)
		{
			totalDistance = leftDistance;
		}
		
		else
			totalDistance=(leftDistance < rightDistance)?leftDistance:rightDistance;
		
		return totalDistance;
	}
	
	public void outputAllStation(){
		System.out.printf("%10s%15s\n", "Code", "Station");
		for (Station station: StationList){
			System.out.printf("%10s%15s\n", station.getCode(), station.getStation());
			
		}
		
	}

	public double finalCalculation(int ageGroup,int quantity,int octopusMethod,int startStation,int destStation){
		double finalPrice=0;
		double ageGroupDiscount;
		double totalDistance;
		ageGroupDiscount=getDiscountFromAge(ageGroup);
		totalDistance=stationDistance(startStation,destStation);
		double pricePerKM = 0;
		
		if(totalDistance<1.5 && totalDistance>0)
			pricePerKM = 2;
		else if(totalDistance > 1.5 && totalDistance < 8)
			pricePerKM = 1.5;
		else
			pricePerKM = 0.6;
		
		if(octopusCechker(octopusMethod)){
			finalPrice = ageGroupDiscount * (totalDistance * pricePerKM) * 1;  
		}
		else
			finalPrice = ageGroupDiscount * (totalDistance * pricePerKM) * quantity;  

		return finalPrice;
	}

}
