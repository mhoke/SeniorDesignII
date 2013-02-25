package edu.ycp.cs.Tetris;

import java.util.Random;

public enum Pieces 
{
	BLANK (0),
	RED (1),
	GREEN (2),
	BLUE (3),
	YELLOW (4),
	CURRENT (5),
	TWO (6),
	ROTATE (7);
	
	private int key;
	
	private Pieces(int k)
	{
		key = k;
	}
	
	public int getKey()
	{
		return key;
	}
	
	public static Pieces createColor()
	{
		Random random = new Random();
		int val = random.nextInt(4) + 1;
		return Pieces.values()[val];
	}
}
