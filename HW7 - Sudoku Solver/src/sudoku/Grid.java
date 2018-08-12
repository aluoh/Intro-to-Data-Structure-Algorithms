package sudoku;

import java.util.*;


public class Grid 
{
	private int[][]						values;
	

	//
	// DON'T CHANGE THIS.
	//
	// Constructs a Grid instance from a string[] as provided by TestGridSupplier.
	// See TestGridSupplier for examples of input.
	// Dots in input strings represent 0s in values[][].
	//
	public Grid(String[] rows)
	{
		values = new int[9][9];
		for (int j=0; j<9; j++)
		{
			String row = rows[j];
			char[] charray = row.toCharArray();
			for (int i=0; i<9; i++)
			{
				char ch = charray[i];
				if (ch != '.')
					values[j][i] = ch - '0';
			}
		}
	}
	
	
	//
	// DON'T CHANGE THIS.
	//
	public String toString()
	{
		String s = "";
		for (int j=0; j<9; j++)
		{
			for (int i=0; i<9; i++)
			{
				int n = values[j][i];
				if (n == 0)
					s += '.';
				else
					s += (char)('0' + n);
			}
			s += "\n";
		}
		return s;
	}


	//
	// DON'T CHANGE THIS.
	// Copy ctor. Duplicates its source. You’ll call this 9 times in next9Grids.
	//
	Grid(Grid src)
	{
		values = new int[9][9];
		for (int j=0; j<9; j++)
			for (int i=0; i<9; i++)
				values[j][i] = src.values[j][i];
	}
	
	
	//
	// Finds an empty member of values[][]. Returns an array list of 9 grids that look like the current grid,
	// except the empty member contains 1, 2, 3 .... 9. Returns null if the current grid is full. Don’t change
	// “this” grid. Build 9 new grids.
	// 
	//
	// Example: if this grid = 1........
	//                         .........
	//                         .........
	//                         .........
	//                         .........
	//                         .........
	//                         .........
	//                         .........
	//                         .........
	//
	// Then the returned array list would contain:
	//
	// 11.......          12.......          13.......          14.......    and so on     19.......
	// .........          .........          .........          .........                  .........
	// .........          .........          .........          .........                  .........
	// .........          .........          .........          .........                  .........
	// .........          .........          .........          .........                  .........
	// .........          .........          .........          .........                  .........
	// .........          .........          .........          .........                  .........
	// .........          .........          .........          .........                  .........
	// .........          .........          .........          .........                  .........
	//
	public ArrayList<Grid> next9Grids()
	{		
		// Construct array list to contain 9 new grids.
		
		ArrayList<Grid> grids = new ArrayList<Grid>();
		ArrayList<Grid> firstNine = new ArrayList<Grid>(9); // holds the first 9 grid only
		if(isFull())
		{
			return null;
		}
		else {
		for(int i = 0; i < 9; i++) 
		{
			for(int j = 0; j < 9; j++)
			{
				Grid g = new Grid(this); // creates a new grid
				if(g.values[i][j] == 0)
				{
					for(int k = 1; k < 10; k++)
					{
						g.values[i][j] = k; // sets the grid values to a number if it's = to 0
						grids.add(new Grid (g)); // adds a new grid to the arraylist using "this" value
					}
				}
			}
		}

		for (int m = 0; m < 9; m++) // get's only the first 9 grid out of all the possibilities in grids
		{
			firstNine.add(grids.get(m));
		}

		return firstNine;
		}
	}
	
	//
	// Returns true if this grid is legal. A grid is legal if no row, column, or
	// 3x3 block contains a repeated 1, 2, 3, 4, 5, 6, 7, 8, or 9.
	//
	public boolean isLegal()
	{
		// Check every row. If you find an illegal row, return false.
		
		for(int i = 0; i < 9; i++)
		{
			if(!validRow(i))
			{
				return false;
			}
		}
		
		// Check every column. If you find an illegal column, return false.
		
			for (int i = 0; i < 9; i++)
			{
				for(int j = 0; j < 9; j++)
				{
					if(!validColumn(j))
					{
						return false;
					}
				}
			}
		
		// Check every block. If you find an illegal block, return false.
		
		for(int i = 0; i < 9; i = i + 3)
		{
			for(int j = 0; j < 9; j = j + 3)
			{
				ArrayList<Integer> block = new ArrayList<Integer>();
				for(int k = i; k < i + 3; k++)
				{
					for(int l = j; l < j + 3; l++)
					{
						block.add(values[k][l]);
					}
				}
				if(!validBlock(block))
				{
					return false;
				}
			}
		}
		
		// All rows/cols/blocks are legal.
		return true;
	}
	
	
	// Checks a row for duplicate numbers
	public boolean validRow(int row)
	{
		for(int i = 0; i < 9; i++)
		{
			for(int j = 8; j > -1; j--)
			{
				if(i != j && values[row][i] != 0 && values[row][j] != 0 && values[row][i] == values[row][j])
				{
					return false;
				}
			}
		}
		return true;
	}
	
	// Checks a column for duplicate numbers
	public boolean validColumn(int col)
	{
		for(int i = 0; i < 9; i++)
		{
			for(int j = 8; j > -1; j--)
			{
				if(i != j && values[i][col] != 0 && values[j][col] != 0 && values[i][col] == values[j][col]) // comparing the values from index 0-8, if same value return false
				{
					return false;
				}
			}
		}
		return true;
	}
	
	// Checks a block for duplicate numbers
	public boolean validBlock(ArrayList<Integer> numbers)
	{
		for(int i = 0; i < numbers.size(); i++)
		{
			for(int j = numbers.size() - 1; j > -1; j--)
			{
				if(numbers.get(i) == numbers.get(j) && i != j && numbers.get(i) != 0 && numbers.get(j) != 0) // compares the value from index 0-8, return false if there are repeated numbers
				{
					return false;
				}
			}
		}
		return true;
	}
	
	
	
	//
	// Returns true if every cell member of values[][] is a digit from 1-9.
	//
	public boolean isFull()
	{
		for(int i = 0; i < 9; i++)
		{
			for (int j = 0; j < 9; j++)
			{
				if(values[i][j] == 0)
				{
					return false;
				}
			}
		}
		return true;
	}
	

	//
	// Returns true if x is a Grid and, for every (i,j), 
	// x.values[i][j] == this.values[i][j].
	//
	public boolean equals(Object x)
	{
		Grid grid = (Grid) x;
		for(int i = 0; i < 9; i++)
		{
			for (int j = 0; j < 9; j++)
			{
				if(this.values[i][j] != grid.values[i][j])
				{
					return false;
				}
			}
		}
		return true;
	}

}
