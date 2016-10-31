import java.util.Map;

public class Station{
	
	private String station;
	private int code;
	
	public Station(String _code, String name){
		this.station=name;
		this.code = Integer.parseInt(_code);
	}
	
	public String getStation(){
		return station;
	}
	
	public int getCode(){
		return code;
	}
}
