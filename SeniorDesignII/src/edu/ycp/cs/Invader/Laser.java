package edu.ycp.cs.Invader;

public class Laser 
{
	boolean friendly;
	int direction_offset;
	
	public Laser(boolean friendly)
	{
		this.friendly = friendly;
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
}
