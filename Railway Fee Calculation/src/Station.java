import java.util.Map;

public class Station{
	
	private String station;
	private int code;
	private double distance;
	private boolean isInterchange;
	private int posX;
	private int posY;
	
	public Station(String _code, String name,String distance,String isInterchange){
		this.station=name;
		this.code = Integer.parseInt(_code);
		this.distance=Double.parseDouble(distance);
		this.isInterchange = (isInterchange.equalsIgnoreCase("Y"))?true:false;
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
	
	public void setPosX(int x)
	{
		posX = x;
	}
	
	public void setPosY(int y)
	{
		posY = y;
	}
	
	public int getPosX()
	{
		return posX;
	}
	
	public int getPosY()
	{
		return posY;
	}
}
