package edu.ycp.cs.Tetris;

import java.util.Random;

public enum Pieces 
{
	BLANK (0),
	RED (1),
	GREEN (2),
	BLUE (3),
	YELLOW (4),
	CYAN (5),
	BLACK (6),
	MAGENTA (7),
	CURRENT (8),
	TWO (9),
	ROTATE (10);
	
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
		int val = random.nextInt(7) + 1;
		return Pieces.values()[val];
	}
}
