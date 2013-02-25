package edu.ycp.cs.Tetris;

import java.util.Random;

public enum Style 
{
	ROD (0),
	BOX (1),
	Z (2),
	RZ (3),
	L (4),
	RL (5),
	T (6);
	
	private int key;
	
	private Style(int k)
	{
		key = k;
	}
	
	public int getKey()
	{
		return key;
	}
	
	public static Style createStyle()
	{
		Random random = new Random();
		int val = random.nextInt(7);
		return Style.values()[val];
	}
}
