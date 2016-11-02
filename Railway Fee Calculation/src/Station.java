import java.util.Map;

public class Station{
	
	private String station;
	private int code;
	private double distance;
	private boolean isInterchange;
	
	public Station(String _code, String name,String distance,String isInterchange){
		this.station=name;
		this.code = Integer.parseInt(_code);
		this.distance=Double.parseDouble(distance);
		this.isInterchange = Boolean.parseBoolean(isInterchange);
	}
	
	public String getStation(){
		return station;
	}
	
	public int getCode(){
		return code;
	}
	
	public double getDistance(){
		return distance;
	}
	
	public boolean isInterchange()
	{
		return isInterchange;
	}
}
