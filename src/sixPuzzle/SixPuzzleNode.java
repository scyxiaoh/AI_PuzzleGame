package sixPuzzle;

import java.util.ArrayList;

public class SixPuzzleNode {
	private SixPuzzleState state;
	private SixPuzzleNode parent;
	private int depth;
	
	public SixPuzzleNode(SixPuzzleState state, SixPuzzleNode parent) {
		this.state = state;
		this.parent = parent;
		if (parent == null) {
			depth = 0;
		} else {
			depth = parent.depth + 1;
		}
	}
	public SixPuzzleState getState() {
		return state;
	}
	public SixPuzzleNode getParent() {
		return parent;
	}
	
	public int getDepth() {
		return this.depth;
	}
	
	public ArrayList<SixPuzzleNode> getPath() {
		ArrayList<SixPuzzleNode> path;
		if (this.parent == null) {
			path = new ArrayList<SixPuzzleNode>();
		}
		else {
			path = parent.getPath();
		}
		path.add(this);
		return path;
	}
}
