package edu.ycp.cs.Invader;

public class Character 
{
	Coordinates location;
	
	public Character(int x, int y)
	{
		location = new Coordinates(x, y);
	}
	
	public void moveLeft()
	{
		if(location.getX() != 0)
		{
			location.setX(location.getX() - 1);
		}
	}
	
	public void moveRight()
	{
		if(location.getX() != 19)
		{
			location.setX(location.getX() + 1);
		}
	}
	
	public Coordinates getLocation()
	{
		return location;
	}
}
