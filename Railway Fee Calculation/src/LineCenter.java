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
	
	public void addLine(Line l)
	{
		line.add(l);
	}

}
