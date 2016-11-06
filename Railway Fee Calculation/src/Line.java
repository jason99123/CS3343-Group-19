import java.util.ArrayList;

public class Line {
	
	private ArrayList<Station> stations = new ArrayList<Station>();
	private String name;
	
	public Line(String name){this.name = name;}
	
	/**
	 * Add a station into a line
	 * @param s Station to be added
	 */
	public void addStation(Station s)
	{
		stations.add(s);
	}
	
	/**
	 * Get the position of a station in a line
	 * @param s Station to be searched
	 * @return index of station
	 */
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
	
	/**
	 * Return the station object by giving a station name
	 * @param name
	 * @return station
	 */
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
	
	/**
	 * I forget what is this...
	 * @param s station
	 * @return
	 */
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
	
	/**
	 * Give a code and return a station, return null if not found
	 * @param code
	 * @return station or null
	 */
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
	
	/**
	 * Return a station by giving a position
	 * @param pos
	 * @return
	 */
	public Station returnStation(int pos)
	{
		return stations.get(pos);
	}
	
	/**
	 * Check whether both stations exist in the same line
	 * @param s Station 1
	 * @param e Station 2
	 * @return true if both station in same line, else return false
	 */
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
	
	/**
	 * Return true if the station exist, else return false
	 * @param s
	 * @return
	 */
	public boolean stationExists(Station s)
	{
		for(Station s1 : stations)
		{
			if(s.getStation().equalsIgnoreCase(s1.getStation()))
				return true;
		}
		
		return false;
	}
	
	/**
	 * Get the distance between two stations in the same line
	 * @param s Start station
	 * @param e end station
	 * @return distance
	 */
	public double getDistance(Station s, Station e)
	{
		int startIndex = getStationPos(s);
		int loopSIndex = startIndex;
		int destIndex = getStationPos(e);
		int loopDIndex = destIndex;
		double returnDistance = 0;
		
		// Go left and found the destination station
		if(startIndex > destIndex)
		{
			
			while(loopSIndex > 0)
			{			
				Station prevStation = stations.get(loopSIndex - 1);
				returnDistance = returnDistance + stations.get(loopSIndex).getDistance();
				
				// Found
				if(prevStation.getStation().equalsIgnoreCase(e.getStation()))
				{
					break;
				}
				
				loopSIndex--;
			}
		}
		
		// Go right and found the destination station
		else
		{
			
			while(loopSIndex < stations.size()-1)
			{
				Station nextStation = stations.get(loopSIndex + 1);
				returnDistance = returnDistance + stations.get(loopSIndex+1).getDistance();
				
				// Found
				if(nextStation.getStation().equalsIgnoreCase(e.getStation()))
				{
					break;
				}
				
				loopSIndex++;
			}
		}
		
		return returnDistance;
	}
	
	/**
	 * Return the number of station in this line
	 * @return
	 */
	public int getNumberOfStation()
	{
		return stations.size();
	}
	
	/**
	 * Return the name of this station
	 * @return
	 */
	public String getName()
	{
		return name;
	}
	
	/**
	 * Print all station in this line
	 */
	public void printAll()
	{
		for(Station s : stations)
		{
			System.out.println("All station:");
			System.out.println(s.getStation());
		}
	}
	
	/**
	 * Get the next interchange station
	 * @param curPos
	 * @param direction
	 * @return
	 */
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
