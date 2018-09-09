package sudoku;

import java.util.*;


public class Solver 
{
	private Grid						problem;
	private ArrayList<Grid>				solutions;
	
	
	public Solver(Grid problem)
	{
		this.problem = problem;
	}
	
	
	public void solve()
	{
		solutions = new ArrayList<>();
		solveRecurse(problem);
	}
	

	//
	// Standard backtracking recursive solver.
	//
	private void solveRecurse(Grid grid)
	{		
		Evaluation eval = evaluate(grid);
		
		if (eval == Evaluation.ABANDON)
		{
			return; // Abandon evaluation of this illegal board.
		}
		else if (eval == Evaluation.ACCEPT)
		{
			solutions.add(grid); // A complete and legal solution. Add it to solutions.
		}
		else
		{
			// Here if eval == Evaluation.CONTINUE. Generate all 9 possible next grids. Recursively 
			assert eval == Evaluation.CONTINUE;
			ArrayList<Grid> nextNine = grid.next9Grids(); // Gets the 9 possibilities of grid
			for(Grid g : nextNine)
			{
				solveRecurse(g); //Recursively calls on the grid
			}
			
		}
	}
	

	//
	// Returns Evaluation.ABANDON if the grid is illegal. 
	// Returns ACCEPT if the grid is legal and complete.
	// Returns CONTINUE if the grid is legal and incomplete.
	//
	public Evaluation evaluate(Grid grid)
	{
		
		if(!grid.isLegal()) // Illegal grid abandon it
		{
			return Evaluation.ABANDON;
		}
		else if (grid.isLegal() && grid.isFull()) // Grid is legal & full, then accepts it
		{
			return Evaluation.ACCEPT;
		}
		else
		{
			return Evaluation.CONTINUE; // Else just continue on
		}

	}
	
	// Gets the solutions
	public ArrayList<Grid> getSolutions()
	{
		return solutions;
	}
	
	
	public static void main(String[] args)
	{
		Grid g = TestGridSupplier.getPuzzle1();		// or any other puzzle
		Solver solver = new Solver(g);
		System.out.println("Will solve\n" + g);
		solver.solve();
		System.out.println(solver.getSolutions());
		
		// Print out solution, or test if it equals() the solution in TestGridSupplier.
	}
}
