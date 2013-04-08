package edu.ycp.cs.Invader;

public enum Values 
{
	BLANK (0),
	GUN (1),
	ALIEN (2),
	BARRIER (3),
	ALIEN_MISSLE (4),
	USER_MISSLE (5);
	
	private int key;
	
	private Values(int k)
	{
		key = k;
	}
	
	public int getKey()
	{
		return key;
	}
}
