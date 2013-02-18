package edu.ycp.cs.Main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Score 
{
	String current_name;
	int current_score;
	int[] scores;
	String[] names;
	int num_scores;
	String game;
	
	public Score()
	{
		scores = new int[10];
		names = new String[10];
	}
	
	public String getCurrentName()
	{
		return current_name;
	}
	
	public int getCurrent_Score()
	{
		return current_score;
	}
	
	public int getNumScores()
	{
		return num_scores;
	}
	
	public int getScore(int index)
	{
		return scores[index];
	}
	
	public String getName(int index)
	{
		return names[index];
	}
	
	public String getGame()
	{
		return game;
	}
	
	public void setCurrentName(String name)
	{
		current_name = name;
	}
	
	public void setCurrent_Score(int score)
	{
		current_score = score;
	}
	
	public void setScore(int index, int score)
	{
		scores[index] = score;
	}
	
	public void setName(int index, String name)
	{
		names[index] = name;
	}
	
	public void setNumScores(int num)
	{
		num_scores = num;
	}
	
	public void setGame(String game)
	{
		this.game = game;
	}
	
	public void getScores(String gameName)
	{
		BufferedReader br = null;
		
		try
		{
			String CurrentLine;
			br = new BufferedReader(new FileReader(gameName + ".txt"));
			
			game = gameName;
			
			int counter = 0;
			
			while((CurrentLine = br.readLine()) != null)
			{
				names[counter] = CurrentLine.substring(0, 3);
				scores[counter] = Integer.parseInt(CurrentLine.substring(4));
				//System.out.println(CurrentLine);
				
				counter ++;
			}
			
			num_scores = counter;
			
			for(int i = num_scores; i < 10; i ++)
			{
				names[i] = "NIL";
				scores[i] = 0;
			}
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				if(br != null)
				{
					br.close();
				}
			}
			catch(IOException ex)
			{
				ex.printStackTrace();
			}
		}
	}
	
	public boolean checkScore()
	{
		if(num_scores < 10)
		{
			num_scores ++;
		}
		
		for(int i = 0; i < num_scores; i ++)
		{
			if(scores[i] < current_score)
			{
				insertScore();
				return true;
			}
		}
		
		return false;
	}
	
	public void insertScore()
	{
		int[] temp_score = new int[10];
		String[] temp_name = new String[10];
		
		int temp_counter = 0;
		
		for(int i = 0; i < num_scores; i ++)
		{
			if(scores[i] > current_score)
			{
				temp_score[temp_counter] = scores[i];
				temp_name[temp_counter] = names[i];
			}
			else
			{
				if(current_score != 0)
				{
					temp_score[temp_counter] = current_score;
					temp_name[temp_counter] = current_name;
					current_score = 0;
					i --;
				}
			}
			
			temp_counter ++;
		}
		
		scores = temp_score;
		names = temp_name;
		
		WriteScore();
	}
	
	public void WriteScore()
	{
		try 
		{
			FileWriter fw = new FileWriter(new File(game + ".txt").getAbsoluteFile());
			
			BufferedWriter bw = new BufferedWriter(fw);
			
			for(int i = 0; i < num_scores; i ++)
			{
				String content = names[i] + " " + scores[i] + "\n";
				bw.write(content);
			}
			
			bw.close();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
}
