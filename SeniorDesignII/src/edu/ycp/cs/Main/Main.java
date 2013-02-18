package edu.ycp.cs.Main;

public class Main 
{
	public static void main(String[] args) 
	{
		Score score = new Score();
		
		score.getScores("Tetris");
		
		score.setCurrent_Score(115);
		score.setCurrentName("TST");
		
		score.checkScore();
	}
}
