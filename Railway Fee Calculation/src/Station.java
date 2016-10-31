import java.util.Map;

public class Station{
	
	private String station;
	private int code;
	private double distance;
	
	public Station(String _code, String name,String distance){
		this.station=name;
		this.code = Integer.parseInt(_code);
		this.distance=Double.parseDouble(distance);

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
}
