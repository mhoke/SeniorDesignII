package edu.ycp.cs.Tetris;

public class Tetris 
{
	int grid[][];
	int cur_row;
	int cur_col;
	Pieces cur_color;
	Style cur_style;
	Pieces next_color;
	Style next_style;
	
	public Tetris()
	{
		grid = new int[10][20];
		
		for(int i = 0; i < 10; i ++)
		{
			for(int j = 0; j < 20; j ++)
			{
				grid[i][j] = Pieces.BLANK.getKey();
			}
		}
		
		next_color = Pieces.createColor();
		next_style = Style.createStyle();
		
		create_Piece();
	}
	
	public void create_Piece()
	{
		cur_color = next_color;
		cur_style = next_style;
		next_color = Pieces.createColor();
		next_style = Style.createStyle();
		
		cur_row = 19;
		cur_col = 4;
		
		grid[4][19] = Pieces.CURRENT.getKey();
		
		if(cur_style == Style.BOX)
		{
			grid[5][19] = Pieces.CURRENT.getKey();
			grid[4][18] = Pieces.CURRENT.getKey();
			grid[5][18] = Pieces.CURRENT.getKey();
		}
		else if(cur_style == Style.L)
		{
			grid[5][19] = Pieces.CURRENT.getKey();
			grid[6][19] = Pieces.CURRENT.getKey();
			grid[4][18] = Pieces.CURRENT.getKey();
		}
		else if(cur_style == Style.RL)
		{
			grid[4][18] = Pieces.CURRENT.getKey();
			grid[5][18] = Pieces.CURRENT.getKey();
			grid[6][18] = Pieces.CURRENT.getKey();
		}
		else if(cur_style == Style.ROD)
		{
			grid[5][19] = Pieces.CURRENT.getKey();
			grid[6][19] = Pieces.CURRENT.getKey();
			grid[7][19] = Pieces.CURRENT.getKey();
		}
		else if(cur_style == Style.RZ)
		{
			grid[3][19] = Pieces.CURRENT.getKey();
			grid[4][18] = Pieces.CURRENT.getKey();
			grid[5][18] = Pieces.CURRENT.getKey();
		}
		else if(cur_style == Style.T)
		{
			grid[5][19] = Pieces.CURRENT.getKey();
			grid[4][18] = Pieces.CURRENT.getKey();
			grid[3][19] = Pieces.CURRENT.getKey();
		}
		else if(cur_style == Style.Z)
		{
			grid[5][19] = Pieces.CURRENT.getKey();
			grid[4][18] = Pieces.CURRENT.getKey();
			grid[3][18] = Pieces.CURRENT.getKey();
		}
	}
	
	public void set_Piece()
	{
		boolean flag = true;
		
		for(int i = 0; i < 10; i ++)
		{
			flag = true;
			for(int j = 0; j < 20; j ++)
			{
				if(grid[i][j] == Pieces.CURRENT.getKey() || grid[i][j] == Pieces.TWO.getKey())
				{
					grid[i][j] = cur_color.getKey();
				}
				if(grid[i][j] == Pieces.BLANK.getKey())
				{
					flag = false;
				}
			}
			if(flag == true)
			{
				remove_Row(i);
			}
		}
		
		create_Piece();
	}
	
	public void remove_Row(int row)
	{
		for(int i = row; i < 19; i ++)
		{
			for(int j = 0; j < 10; j ++)
			{
				grid[j][i] = grid[j][i + 1];
			}
		}
		
		for(int i = 0; i < 10; i ++)
		{
			grid[i][19] = Pieces.BLANK.getKey();
		}
	}
	
	public void move_Right()
	{
		boolean flag = true;
		
		for(int i = 0; i < 20; i ++)
		{
			if(grid[9][i] == Pieces.CURRENT.getKey())
			{
				flag = false;
				i = 21;
			}
		}
		
		if(flag)
		{	
			for(int i = 0; i < 10; i ++)
			{
				for(int j = 0; j < 20; j ++)
				{
					if(grid[i][j] == Pieces.CURRENT.getKey())
					{
						if(grid[i + 1][j] != Pieces.BLANK.getKey() && grid[i + 1][j] != Pieces.CURRENT.getKey() && grid[i + 1][j] != Pieces.TWO.getKey())
						{
							flag = false;
							j = 21;
							i = 11;
						}
					}
				}
			}
		}
		
		if(flag)
		{
			for(int i = 9; i >= 0; i --)
			{
				for(int j = 0; j < 20; j ++)
				{
					if(grid[i][j] == Pieces.CURRENT.getKey() || grid[i][j] == Pieces.TWO.getKey())
					{
						if(grid[i + 1][j] == Pieces.CURRENT.getKey())
						{
							grid[i + 1][j] = Pieces.TWO.getKey();
						}
						else
						{
							grid[i + 1][j] = Pieces.CURRENT.getKey();
						}
						
						if(grid[i][j] == Pieces.CURRENT.getKey())
						{
							grid[i][j] = Pieces.BLANK.getKey();
						}
						else if(grid[i][j] == Pieces.TWO.getKey())
						{
							grid[i][j] = Pieces.CURRENT.getKey();
						}
					}
				}
			}
			
			cur_col ++;
		}
	}
	
	public void move_Left()
	{
		boolean flag = true;
		
		for(int i = 0; i < 20; i ++)
		{
			if(grid[0][i] == Pieces.CURRENT.getKey())
			{
				flag = false;
				i = 21;
			}
		}
		
		if(flag)
		{	
			for(int i = 0; i < 10; i ++)
			{
				for(int j = 0; j < 20; j ++)
				{
					if(grid[i][j] == Pieces.CURRENT.getKey())
					{
						if(grid[i - 1][j] != Pieces.BLANK.getKey() && grid[i - 1][j] != Pieces.CURRENT.getKey() && grid[i - 1][j] != Pieces.TWO.getKey())
						{
							flag = false;
							j = 21;
							i = 11;
						}
					}
				}
			}
		}
		
		if(flag)
		{
			for(int i = 0; i < 10; i ++)
			{
				for(int j = 0; j < 20; j ++)
				{
					if(grid[i][j] == Pieces.CURRENT.getKey() || grid[i][j] == Pieces.TWO.getKey())
					{
						if(grid[i - 1][j] == Pieces.CURRENT.getKey())
						{
							grid[i - 1][j] = Pieces.TWO.getKey();
						}
						else
						{
							grid[i - 1][j] = Pieces.CURRENT.getKey();
						}
						
						if(grid[i][j] == Pieces.CURRENT.getKey())
						{
							grid[i][j] = Pieces.BLANK.getKey();
						}
						else if(grid[i][j] == Pieces.TWO.getKey())
						{
							grid[i][j] = Pieces.CURRENT.getKey();
						}
					}
				}
			}
			
			cur_col --;
		}
	}
	
	public void move_Down()
	{
		boolean flag = true;
		
		for(int i = 0; i < 10; i ++)
		{
			for(int j = 0; j < 20; j ++)
			{
				if(grid[i][j] == Pieces.CURRENT.getKey())
				{
					if(j == 0 || (grid[i][j - 1] != Pieces.CURRENT.getKey() && grid[i][j - 1] != Pieces.BLANK.getKey()))
					{
						flag = false;
						set_Piece();
						i = 11;
						j = 21;
					}
				}
			}
		}
		
		if(flag)
		{
			for(int i = 0; i < 10; i ++)
			{
				for(int j = 0; j < 20; j ++)
				{
					if(grid[i][j] == Pieces.CURRENT.getKey() || grid[i][j] == Pieces.TWO.getKey())
					{
						if(grid[i][j - 1] == Pieces.CURRENT.getKey())
						{
							grid[i][j - 1] = Pieces.TWO.getKey();
						}
						else
						{
							grid[i][j - 1] = Pieces.CURRENT.getKey();
						}
						
						if(grid[i][j] == Pieces.CURRENT.getKey())
						{
							grid[i][j] = Pieces.BLANK.getKey();
						}
						else if(grid[i][j] == Pieces.TWO.getKey())
						{
							grid[i][j] = Pieces.CURRENT.getKey();
						}
					}
				}
			}
			
			cur_row --;
		}
	}
	
	public void rotate_right()
	{
		int col_diff;
		int row_diff;
		
		boolean flag = true;
		
		for(int i = 0; i < 10; i ++)
		{
			for(int j = 0; j < 20; j ++)
			{
				if(grid[i][j] == Pieces.CURRENT.getKey() || grid[i][j] == Pieces.TWO.getKey())
				{
					row_diff = cur_col - i;
					col_diff = cur_row - j;
					
					if(i + col_diff >= 10 || j + row_diff >= 20)
					{
						i = 11;
						j = 21;
						flag = false;
					}
					
					else if(grid[i + col_diff][j + row_diff] != Pieces.BLANK.getKey() && grid[i + col_diff][j + row_diff] != Pieces.CURRENT.getKey() && grid[i + col_diff][j + row_diff] != Pieces.TWO.getKey() && grid[i + col_diff][j + row_diff] != Pieces.ROTATE.getKey())
					{
						i = 11;
						j = 21;
						flag = false;
					}
				}
			}
		}
		
		if(flag)
		{
			for(int i = 0; i < 10; i ++)
			{
				for(int j = 0; j < 20; j ++)
				{
					if(grid[i][j] == Pieces.CURRENT.getKey() || grid[i][j] == Pieces.TWO.getKey() || grid[i][j] == Pieces.ROTATE.getKey())
					{
						col_diff = cur_col - i;
						row_diff = cur_row - j;
						
						if(col_diff != 0 && row_diff != 0)
						{
							if(grid[i + col_diff][j + row_diff] == Pieces.CURRENT.getKey() || grid[i + col_diff][j + row_diff] == Pieces.TWO.getKey())
							{
								grid[i + col_diff][j + row_diff] = Pieces.ROTATE.getKey();
							}
							else if(grid[i + col_diff][j + row_diff] == Pieces.BLANK.getKey())
							{
								grid[i + col_diff][j + row_diff] = Pieces.CURRENT.getKey();
							}
							
							if(grid[i][j] == Pieces.CURRENT.getKey())
							{
								grid[i][j] = Pieces.BLANK.getKey();
							}
							else if(grid[i][j] == Pieces.ROTATE.getKey())
							{
								grid[i][j] = Pieces.CURRENT.getKey();
							}
						}
					}
				}
			}
		}
	}
	
	public void print_Game()
	{
		System.out.println("\n");
		for(int i = 19; i >= 0; i --)
		{
			for(int j = 0; j < 10; j ++)
			{
				System.out.print(grid[j][i] + " ");
			}
			System.out.print("\n");
		}
	}
}
