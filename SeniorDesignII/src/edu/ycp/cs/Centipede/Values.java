package edu.ycp.cs.Centipede;

public enum Values 
{
	BLANK (0),
	CENTIPEDE (1),
	FOOD (2);
	
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
