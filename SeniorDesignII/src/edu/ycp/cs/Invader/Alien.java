package edu.ycp.cs.Invader;

public class Alien 
{
	Coordinates location;
	int Life;
	
	public Alien(int x, int y, int life)
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
	
	public boolean moveLeft()
	{
		location.setX(location.getX() - 1);
		if(location.getX() == 0)
		{
			return true;
		}
		
		return false;
	}
	
	public boolean moveRight()
	{
		location.setX(location.getX() + 1);
		if(location.getX() == 19)
		{
			return true;
		}
		
		return false;
	}
	
	public void moveDown()
	{
		location.setY(location.getY() - 1);
	}
	
	public void damageAlien()
	{
		Life --;
	}
	
	public boolean charTest(Coordinates c)
	{
		if(location.getY() == 0 && location.getX() == c.getX())
		{
			return true;
		}
		return false;
	}
}
