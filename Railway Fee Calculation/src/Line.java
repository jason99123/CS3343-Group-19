import java.util.ArrayList;

public class Line {
	
	private ArrayList<Station> stations = new ArrayList<Station>();
	private String name;
	
	public Line(String name){this.name = name;}
	
	public void addStation(Station s)
	{
		stations.add(s);
	}
	
	public int getStationPos(Station s)
	{
		for(Station s1 : stations)
		{
			if(s1.getStation().equalsIgnoreCase(s.getStation()))
			{
				return stations.indexOf(s1);
			}
		}
		
		return -1;
	}
	
	public Station getStation(String name)
	{
		for(Station s : stations)
		{
			if(s.getStation().equalsIgnoreCase(name))
			{
				return s;
			}
		}
		
		return null;
	}
	
	public Station returnStation(int pos)
	{
		return stations.get(pos);
	}
	
	public boolean stationExists(Station s, Station e)
	{
		boolean startExist = false;
		boolean endExist = false;
				
		for(Station s1 : stations)
		{
			if(s.getStation().equalsIgnoreCase(s1.getStation()))
			{
				startExist = true;
			}
			
			if(e.getStation().equalsIgnoreCase(s1.getStation()))
			{
				endExist = true;
			}
		}

		return (startExist == true && endExist == true)?true:false;
	}
	
	public boolean stationExists(Station s)
	{
		for(Station s1 : stations)
		{
			if(s.getStation().equalsIgnoreCase(s1.getStation()))
				return true;
		}
		
		return false;
	}
	
	public int getDistance(Station s, Station e)
	{
		int countS=0;
		int countD=0;
		
		for(Station s1:stations){
			countS++;
			if(s1.getStation().equals(s.getStation())){
				break;
			}
		}
		
		for(Station s1:stations){
			countD++;
			if(s1.getStation().equals(e.getStation())){
				break;
			}
		}
		
//		System.out.println("countD" + countD);
//		System.out.println("countS" + countS);
		return Math.abs(countD - countS);
	}
	
	public int getNumberOfStation()
	{
		return stations.size();
	}
	
	public String getName()
	{
		return name;
	}
	
	public void printAll()
	{
		for(Station s : stations)
		{
			System.out.println("All station:");
			System.out.println(s.getStation());
		}
	}
	
	public Station getNextInterchange(int curPos , String direction)
	{
		if(direction.equalsIgnoreCase("L"))
		{
			for(int i = curPos-1; i >= 0; i--)
			{
				if(stations.get(i).isInterchange() == true)
					return stations.get(i);
			}
		}
		
		else if(direction.equalsIgnoreCase("R"))
		{
			for(int i = curPos+1; i < stations.size(); i++)
			{
				if(stations.get(i).isInterchange() == true)
					return stations.get(i);
			}
		}
		
		return null;
	}

	
}
