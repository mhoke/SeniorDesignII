package edu.ycp.cs.Invader;

public class Barrier 
{
	Coordinates location;
	int Life;
	
	public Barrier(int x, int y, int life)
	{
		location = new Coordinates(x, y);
		Life = life;
	}
	
	public Coordinates getLocation()
	{
		return location;
	}
	
	public int getX()
	{
		return location.getX();
	}
	
	public int getY()
	{
		return location.getY();
	}
	
	public int getLife()
	{
		return Life;
	}
	
	public void killBarrier()
	{
		Life = 0;
	}
}
