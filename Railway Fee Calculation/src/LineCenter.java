import java.util.ArrayList;

public class LineCenter {
	
	ArrayList<Line> line = new ArrayList<>();
	private static LineCenter instance = new LineCenter();
	
	private LineCenter(){}
	
	public static LineCenter getInstance()
	{
		return instance;
	}
		
	public ArrayList<Line> inLine(Station s)
	{
		ArrayList<Line> returnList = new ArrayList<>();
		
		for(Line l: line)
		{
			
			if(l.stationExists(s))
			{
				returnList.add(l);
			}
		}
		
		return returnList;
	}
	
	public Station getStationByCode(int code)
	{
		for(Line l: line)
		{
			
			if(l.getStationByCode(code) != null)
			{
				return l.getStationByCode(code);
			}
		}
		
		return null;
	}
	
	public Station getStationByPos(int x, int y)
	{
		
		for(Line l : line)
		{
			int i = 0;
			
			for(int j = 0 ; j < l.getNumberOfStation() ; j++)
			{
				Station tmp = l.returnStation(i);
				
				if(Math.abs(tmp.getPosX() - x) < 5 && Math.abs(tmp.getPosY() - y) < 5)
				{
					return tmp;
				}
				
				i++;
			}
		}
		
		return null;
	}
	
	public void addLine(Line l)
	{
		line.add(l);
	}

}
