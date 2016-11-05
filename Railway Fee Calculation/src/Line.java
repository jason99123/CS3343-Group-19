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
	
	public Station getIndexByName(Station s)
	{
		for(Station s1 : stations)
		{
			if(s1.getStation().equalsIgnoreCase(s.getStation()))
			{
				return s1;
			}
		}
		
		return null;
	}
	
	public Station getStationByCode(int code)
	{
		for(Station s : stations)
		{
			if(s.getCode() == code)
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
	
	public double getDistance(Station s, Station e)
	{
		int startIndex = getStationPos(s);
		int loopSIndex = startIndex;
		int destIndex = getStationPos(e);
		int loopDIndex = destIndex;
		double returnDistance = 0;
		
		if(startIndex > destIndex)
		{
			
			
			while(loopSIndex > 0)
			{			
				Station prevStation = stations.get(loopSIndex - 1);
				returnDistance = returnDistance + stations.get(loopSIndex).getDistance();
				
				if(prevStation.getStation().equalsIgnoreCase(e.getStation()))
				{
					break;
				}
				
				loopSIndex--;
			}
		}
		
		else
		{
			
			while(loopSIndex < stations.size()-1)
			{
				Station nextStation = stations.get(loopSIndex + 1);
				returnDistance = returnDistance + stations.get(loopSIndex+1).getDistance();
				
				if(nextStation.getStation().equalsIgnoreCase(e.getStation()))
				{
					break;
				}
				
				loopSIndex++;
			}
		}
		
		return returnDistance;
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
