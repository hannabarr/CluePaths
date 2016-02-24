import java.util.*;

public class IntBoard {
	private Map<BoardCell, LinkedList<BoardCell>> adjMtx;
	private static Set<BoardCell> targets;
	private BoardCell[][] grid;
	private Set<BoardCell> visited;
	int rows, cols;
	//Set<BoardCell> cells = new Set<BoardCell>();
	public IntBoard(int h, int w) {
		adjMtx = new HashMap<BoardCell, LinkedList<BoardCell>>();
		targets = new HashSet<BoardCell>();
		//visited = new HashSet<BoardCell>();
		grid = new BoardCell[h][w];
		this.rows = h;
		this.cols = w;
		for (int i = 0; i < h; i++) 
			for(int j = 0; j < w; j++)
				grid[i][j] = new BoardCell(i,j);
	}
	
	public void calcAdjacencies() {
		for(int r = 0; r < rows; r ++) {
			for(int w = 0; w < cols; w --) {
				BoardCell cell = new BoardCell(r,w);
				LinkedList<BoardCell> list = getAdjList(cell);
				
				adjMtx.put(cell, list);
			}
		}
	}
	
	public void calcTargets(BoardCell startCell, int pathLength) {
		visited = new HashSet<BoardCell>();
		visited.add(startCell);
		findAllTargets(startCell, pathLength);
		
	}
	
	public void findAllTargets(BoardCell startCell, int numSteps) {
		LinkedList<BoardCell> adj = getAdjList(startCell);
		LinkedList<BoardCell> unv = unvisitedAdj(adj);
		for(BoardCell b : unv) {
			visited.add(b);
			if(numSteps == 1) targets.add(b);
			else findAllTargets(b,numSteps-1);
			visited.remove(b);
		}
	}
	
	public static Set<BoardCell> getTargets() {
		return targets;
	}
	
	private LinkedList<BoardCell> unvisitedAdj(LinkedList<BoardCell> adj) {
		LinkedList<BoardCell> unv = new LinkedList<BoardCell>();
		for(BoardCell b: adj) {
			if(!visited.contains(b)) unv.add(b);
		}
		return unv;
	}
	
	public LinkedList<BoardCell> getAdjList(BoardCell cell) {
		LinkedList<BoardCell> list = new LinkedList<BoardCell>();
		// Up
		if(cell.row != 0) list.add(getCell(cell.row-1,cell.col));
		// Right
		if (cell.col < cols-1) list.add(getCell(cell.row,cell.col+1));
		// Down
		if (cell.row < rows-1) list.add(getCell(cell.row+1,cell.col));
		// Left
		if (cell.col != 0) list.add(getCell(cell.row,cell.col-1));
		
		return list;
	}

	public BoardCell getCell(int i, int j) {
		// TODO Auto-generated method stub
		return grid[i][j];
		//return null;
	}
	
	public static void main(String[] argv) {
		// STUFF FOR TESTING.. REMOVE BEFORE SUBMITTING
		IntBoard a = new IntBoard(4, 4);
		BoardCell c = a.getCell(0,0);
		a.calcTargets(c,3);
		//a.findAllTargets(c, 3);
		
		Set<BoardCell> l = getTargets();
		System.out.println(l);
		

	}

}
