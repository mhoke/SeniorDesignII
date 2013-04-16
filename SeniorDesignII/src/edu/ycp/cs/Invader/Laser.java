package edu.ycp.cs.Invader;

public class Laser 
{
	boolean friendly;
	int direction_offset;
	Coordinates location;
	
	public Laser(boolean friendly, Coordinates location)
	{
		this.friendly = friendly;
		this.location = location;
		if(friendly)
		{
			direction_offset = 1;
		}
		else
		{
			direction_offset = -1;
		}
	}
	
	public boolean isFriendly()
	{
		return friendly;
	}
	
	public int getDirection()
	{
		return direction_offset;
	}
	
	public Coordinates getLocation()
	{
		return location;
	}
	
	public boolean getFriendly()
	{
		return friendly;
	}
	
	public void moveLaser()
	{
		location.setY(location.getY() + direction_offset);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Laser other = (Laser) obj;
		if (direction_offset != other.direction_offset)
			return false;
		if (friendly != other.friendly)
			return false;
		if (location == null) {
			if (other.location != null)
				return false;
		} else if (!location.equals(other.location))
			return false;
		return true;
	}
}
