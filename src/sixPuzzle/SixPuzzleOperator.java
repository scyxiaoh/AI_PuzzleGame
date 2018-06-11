package sixPuzzle;

import abstracts.Operator;

public class SixPuzzleOperator extends Operator {
	/**
	 * direction = 0	--> up
	 * 			 = 1	--> left
	 * 			 = 2	--> right
	 * 			 = 3	--> down
	 */
	private int direction;
	public static final SixPuzzleOperator UP = new SixPuzzleOperator(0);
	public static final SixPuzzleOperator LEFT = new SixPuzzleOperator(1);
	public static final SixPuzzleOperator RIGHT = new SixPuzzleOperator(2);
	public static final SixPuzzleOperator DOWN = new SixPuzzleOperator(3);
	
	private SixPuzzleOperator(int direction) {
		if (direction < 0) {
			this.direction = 0;
		}
		else if (direction > 3) {
			this.direction = 3;
		}
		else {
			this.direction = direction;
		}
	}
	
	public int getDirection() {
		return direction;
	}

}
