import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class PriceCalculator {
	
	private static PriceCalculator instance=new PriceCalculator();
	private ArrayList<Station>StationList = new ArrayList<>();
	private LineCenter lc = LineCenter.getInstance();
	
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
		
		// The information
		String[] fileName = {"blue","green","purple","red"};
		int[] posX = {951,916,867,817,772,716,684,635,591,542,493,454,407,363,-1,-1,527,528,529,550,593,629,681,746,801,841,841,841,840,867,918,685,717,866,918,945,946,947,178,225,270,315,360,393,432,479,506,530,527,529,524,524,454,409};
		int[] posY = {698,665,646,646,647,638,639,644,643,644,643,637,640,641,-1,-1,494,460,425,383,363,364,366,362,364,396,428,465,502,529,526,640,640,528,531,499,465,435,365,366,365,365,368,366,364,366,383,425,462,493,522,551,641,640};
		
		// For reading the position in GUI
		int readStationGUIPos = 0;
		
		try {
			
			for(String s : fileName)
			{
				BufferedReader reader= new BufferedReader(new FileReader("data/" + s + "_line.csv")); //import csv file
				
				String row=reader.readLine(); //no line is read at first
				Line line = new Line(s);
				
				while((row=reader.readLine())!=null){
					
					String data[]=row.split(","); //split columns in each rows
					
					// These stations are not added because the map is not up-to-date
					if(data[1].equalsIgnoreCase("LOHAS Park") || data[1].equalsIgnoreCase("Sai Ying Pun") || data[1].equalsIgnoreCase("HKU") || data[1].equalsIgnoreCase("Kennedy Town"))
					{
						continue;
					}
					
					Station newStation= new Station(data[0],data[1],data[2],data[3]);
					newStation.setPosX(posX[readStationGUIPos]);
					newStation.setPosY(posY[readStationGUIPos]);
					readStationGUIPos++;
					
					StationList.add(newStation);
					line.addStation(newStation);
				}
				
				lc.addLine(line);
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block (need to edit it later)
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block (need to edit it later)
			e.printStackTrace();
		}
		
		
	}
	
	/**
	 * Get the distance between two stations by giving two code
	 * @param start
	 * @param dest
	 * @return
	 */
	public double stationDistance(int start,int dest)
	{
		Station startStation = lc.getStationByCode(start);
		Station endStation = lc.getStationByCode(dest);
		
		
		return stationDistance(startStation,endStation,null);
	}

	/**
	 * Calculate the distance by recursion
	 * @param start
	 * @param dest
	 * @param exceptionList The list that will not be checked again
	 * @return
	 */
	public double stationDistance(Station start,Station dest,ArrayList<Line> exceptionList){
		
		double leftDistance = 1000;
		double rightDistance = 1000;
		double tmpLeft = 0;
		double tmpRight = 0;
		double returnDistance = 0;
		
		// The first call the exception list is null
		if(exceptionList == null)
		{
			exceptionList = new ArrayList<Line>();
		}
		
		ArrayList<Line> line = lc.inLine(start);
		
		
		for(Line l : line)
		{
			// If the line is checked, don't check it again
			if(exceptionList.contains(l))
			{
				continue;
			}
			
			// If both stations in same line, return the distance
			for(Line l2 : line)
			{
				if(l2.stationExists(start, dest))
				{
					return l2.getDistance(start, dest);
				}
			}
			
			
			int startIndex = l.getStationPos(start);
			int loopStartIndex = startIndex;
			
			// Go left
			while(loopStartIndex > 0)
			{
				Station prevStation = l.returnStation(loopStartIndex-1);
				
				// If the station is interchange station, use that station as a start station
				if(prevStation.isInterchange())
				{
					ArrayList<Line> passList = deepCoping(exceptionList);
					passList.add(l);
					double curDistance = l.getDistance(start, prevStation);
					tmpLeft = stationDistance(prevStation,dest,passList) + curDistance;
					// May have a chance to check more than 1 line
					leftDistance = (tmpLeft < leftDistance)?tmpLeft:leftDistance;
					
				}
				
				loopStartIndex--;
			}
			
			// Reset the looping index
			loopStartIndex = startIndex;
			
			// Go right
			while(loopStartIndex < l.getNumberOfStation()-1)
			{
				Station nextStation = l.returnStation(loopStartIndex+1);
				
				// If the station is interchange station, use that station as a start station
				if(nextStation.isInterchange())
				{
					ArrayList<Line> passList = deepCoping(exceptionList);
					passList.add(l);
					double curDistance = l.getDistance(start, nextStation);
					tmpRight = stationDistance(nextStation,dest,passList) + curDistance;
					// May have a chance to check more than 1 line
					rightDistance = (tmpRight < rightDistance)?tmpRight:rightDistance;
				}
				
				loopStartIndex++;
			}
					
		}
		
		// Nothing is found in the left
		if(leftDistance == 1000)
		{
			returnDistance = rightDistance;
		}
		
		// Nothing is found in the left
		else if(rightDistance == 1000)
		{
			returnDistance = leftDistance;
		}
		
		// Compare the distance
		else
			returnDistance =(leftDistance < rightDistance)?leftDistance:rightDistance;
		
		return returnDistance;	
	}
	
	/**
	 * Output all stations
	 */
	public void outputAllStation(){
		System.out.printf("%10s%15s\n", "Code", "Station");
		for (Station station: StationList){
			System.out.printf("%10s%15s\n", station.getCode(), station.getStation());
			
		}
		
	}
	
	/**
	 * Copy a new arraylist
	 * @param another
	 * @return
	 */
	private ArrayList<Line> deepCoping(ArrayList<Line> another)
	{
		ArrayList<Line> returnList = new ArrayList<Line>();
		
		for(Line l : another)
		{
			returnList.add(l);
		}
		
		return returnList;
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
	
	/**
	 * Use station is better, return the price
	 * @param ageGroup
	 * @param quantity
	 * @param octopusMethod
	 * @param startStation
	 * @param destStation
	 * @return
	 */
	public double finalCalculation(int ageGroup,int quantity,int octopusMethod,Station startStation,Station destStation){
		double finalPrice=0;
		double ageGroupDiscount;
		double totalDistance;
		ageGroupDiscount=getDiscountFromAge(ageGroup);
		totalDistance=stationDistance(startStation,destStation,null);
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
