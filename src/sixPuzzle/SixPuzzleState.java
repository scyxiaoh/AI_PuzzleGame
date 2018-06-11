package sixPuzzle;
import java.util.ArrayList;
import java.util.Arrays;

import abstracts.Operator;
import abstracts.State;

public class SixPuzzleState extends State {

	/**
	 *	0 indicates the blank tile
	 *	[1,5] indicates the number tiles
	 */
	private int tilePosition[];
	
	public SixPuzzleState(int[] positions) {
		this.tilePosition = positions;
	}
	@Override
	public void print() {
		assert tilePosition.length == 6;
		System.out.println(tilePosition[0] + "  " + tilePosition[1] + "  " + tilePosition[2]);
		System.out.println(tilePosition[3] + "  " + tilePosition[4] + "  " + tilePosition[5]);
		System.out.println();
	}
	
	public boolean isGoal() {
		return (tilePosition[0] == 0 && tilePosition[1] == 1 && tilePosition[2] == 2 && tilePosition[3] == 5 && tilePosition[4] == 4 && tilePosition[5] == 3);
	}
	
	public boolean isLegal(SixPuzzleOperator op) {
		int blankPosition = this.getBlankPosition();
		if (op == SixPuzzleOperator.UP) {
			return (blankPosition < 3);
		}
		else if (op == SixPuzzleOperator.LEFT) {
			return ((blankPosition!=2) && (blankPosition!=5));
		}
		else if (op == SixPuzzleOperator.RIGHT) {
			return ((blankPosition!=0) && (blankPosition!=3));
		}
		else if (op == SixPuzzleOperator.DOWN) {
			return (blankPosition > 2);
		}
		else return false;
	}
	
	public ArrayList<Operator> getLegalOps(){
		ArrayList<Operator> validOps = new ArrayList<Operator>();
		ArrayList<Integer> validNumbers = new ArrayList<Integer>();
		int blankPosition = this.getBlankPosition();
		if (this.isLegal(SixPuzzleOperator.UP)) {
			validNumbers.add(tilePosition[blankPosition + 3]);
			validOps.add(SixPuzzleOperator.UP);
		}
		if (this.isLegal(SixPuzzleOperator.LEFT)) {
			validNumbers.add(tilePosition[blankPosition + 1]);
			validOps.add(SixPuzzleOperator.LEFT);
		}
		if (this.isLegal(SixPuzzleOperator.RIGHT)) {
			validNumbers.add(tilePosition[blankPosition - 1]);
			validOps.add(SixPuzzleOperator.RIGHT);
		}
		if (this.isLegal(SixPuzzleOperator.DOWN)) {
			validNumbers.add(tilePosition[blankPosition - 3]);
			validOps.add(SixPuzzleOperator.DOWN);
		}
		for (int i = 0; i < validNumbers.size()-1; i++) {
			for (int j = i + 1; j > 0; j--) {
				if (validNumbers.get(j-1) <= validNumbers.get(j)) {
					break;
				}
				int tempInt = validNumbers.get(j);
				Operator tempOp = validOps.get(j);
				validNumbers.set(j, validNumbers.get(j-1));
				validOps.set(j, validOps.get(j-1));
				validNumbers.set(j-1, tempInt);
				validOps.set(j-1, tempOp);
			}
		}
		return validOps;
	}
	
	int getBlankPosition() {
		int blankPosition = tilePosition.length;
		for (int i = 0; i < tilePosition.length; i++) {
			if(tilePosition[i] == 0) {
				blankPosition = i;
				break;
			}
		}
		assert blankPosition == tilePosition.length; 
		return blankPosition;
	}
	
	public State nextState(SixPuzzleOperator op) {
		if (!this.isLegal(op)) return null;
		else {
			int newTiles[] = Arrays.copyOf(tilePosition, 6);
			int blankPosition = this.getBlankPosition();
			if (op == SixPuzzleOperator.UP) {
				newTiles[blankPosition] = tilePosition[blankPosition+3];
				newTiles[blankPosition+3] = 0;
			}
			else if(op == SixPuzzleOperator.LEFT) {
				newTiles[blankPosition] = tilePosition[blankPosition+1];
				newTiles[blankPosition+1] = 0;
			}
			else if(op == SixPuzzleOperator.RIGHT) {
				newTiles[blankPosition] = tilePosition[blankPosition-1];
				newTiles[blankPosition-1] = 0;
			}
			else if (op == SixPuzzleOperator.DOWN) {
				newTiles[blankPosition] = tilePosition[blankPosition-3];
				newTiles[blankPosition-3] = 0;
			}
			return new SixPuzzleState(newTiles);
		}
	}

	public boolean equals(Object o) {
		if (o.getClass() != this.getClass()) return false;
		else {
			boolean equal = true;
			for (int i = 0; i < tilePosition.length; i++) {
				if (((SixPuzzleState)o).tilePosition[i] != tilePosition[i]) {
					return false;
				}
			}
			return equal;
		}
	}
}
