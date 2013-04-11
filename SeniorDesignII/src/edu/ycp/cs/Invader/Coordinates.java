package edu.ycp.cs.Invader;

public class Coordinates 
{
	int x;
	int y;
	
	public Coordinates(int x, int y)
	{
		this.x = x;
		this.y = y;
	}
	
	public int getX() 
	{
		return x;
	}
	public void setX(int x) 
	{
		this.x = x;
	}
	public int getY() 
	{
		return y;
	}
	public void setY(int y) 
	{
		this.y = y;
	}
	
	public boolean isEqual(Coordinates a)
	{
		if(x == a.getX() && y == a.getY())
		{
			return true;
		}
		return false;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Coordinates other = (Coordinates) obj;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		return true;
	}
}
