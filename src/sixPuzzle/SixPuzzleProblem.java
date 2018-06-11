package sixPuzzle;
import java.util.ArrayList;

import abstracts.*;

public class SixPuzzleProblem extends Problem{
	
	public SixPuzzleProblem(SixPuzzleState startState) {
		this.startState = startState;
	}

	@Override
	public boolean isGoal(State crtState) {
		if (crtState.getClass() != SixPuzzleState.class) {
			return false;
		}
		else {
			return ((SixPuzzleState)crtState).isGoal();	
		}
	}

	@Override
	public boolean isLegal(State s, Operator op) {
		if ((s.getClass() != SixPuzzleState.class) || (op.getClass() != SixPuzzleOperator.class)) {
			return false;
		}
		else {
			return ((SixPuzzleState)s).isLegal((SixPuzzleOperator)op);
		}
	}

	@Override
	public ArrayList<Operator> getLegalOps(State s) {
		if ((s.getClass() != SixPuzzleState.class)) {
			return null;
		}
		else {
			return ((SixPuzzleState)s).getLegalOps();
		}
	}

	@Override
	public State nextState(State crtState, Operator op) {
		if ((crtState.getClass() != SixPuzzleState.class  || (op.getClass() != SixPuzzleOperator.class))) {
			return null;
		}
		else {
			return ((SixPuzzleState)crtState).nextState((SixPuzzleOperator)op);
		}
	}

	@Override
	public float cost(State s, Operator op) {
		return 1;
	}
	
	public ArrayList<SixPuzzleNode> getBFSResult(){
		ArrayList<SixPuzzleNode> queue = new ArrayList<SixPuzzleNode>();
		ArrayList<SixPuzzleState> visited = new ArrayList<SixPuzzleState>();
		assert this.startState.getClass() == SixPuzzleState.class;
		queue.add(new SixPuzzleNode((SixPuzzleState)this.startState, null));
		while (!queue.isEmpty()) {
			//System.out.println(queue.size());//test
			SixPuzzleNode currentNode = queue.remove(0);
			//currentNode.getState().print();//test
			if (currentNode.getState().isGoal()) {
				return currentNode.getPath();
			}
			ArrayList<Operator> legalOps = currentNode.getState().getLegalOps();
			for (int i = 0; i < legalOps.size(); i++) {
				assert legalOps.get(i).getClass() == SixPuzzleOperator.class;
				//System.out.println("direction:" + ((SixPuzzleOperator)(legalOps.get(i))).getDirection());//test
				SixPuzzleState nextState = (SixPuzzleState)(currentNode.getState().nextState((SixPuzzleOperator)(legalOps.get(i))));
				//System.out.println("next state");//test
				//nextState.print();//test
				if (!visited.contains(nextState)) {
					queue.add(new SixPuzzleNode(nextState,currentNode));
					visited.add(nextState);
				}
			}
		}
		return null;
	}
	
	public ArrayList<SixPuzzleNode> getDFSResult(){
		ArrayList<SixPuzzleNode> stack = new ArrayList<SixPuzzleNode>();
		ArrayList<SixPuzzleState> visited = new ArrayList<SixPuzzleState>();
		assert this.startState.getClass() == SixPuzzleState.class;
		stack.add(new SixPuzzleNode((SixPuzzleState)this.startState, null));
		while (!stack.isEmpty()) {
			//System.out.println(queue.size());//test
			SixPuzzleNode currentNode = stack.remove(0);
			//currentNode.getState().print();//test
			if (currentNode.getState().isGoal()) {
				return currentNode.getPath();
			}
			ArrayList<Operator> legalOps = currentNode.getState().getLegalOps();
			for (int i = legalOps.size()-1; i >= 0; i--) {
				assert legalOps.get(i).getClass() == SixPuzzleOperator.class;
				//System.out.println("direction:" + ((SixPuzzleOperator)(legalOps.get(i))).getDirection());//test
				SixPuzzleState nextState = (SixPuzzleState)(currentNode.getState().nextState((SixPuzzleOperator)(legalOps.get(i))));
				//System.out.println("next state");//test
				//nextState.print();//test
				if (!visited.contains(nextState)) {
					stack.add(0, new SixPuzzleNode(nextState,currentNode));
					visited.add(nextState);
				}
			}
		}
		return null;
	}
	
	public ArrayList<SixPuzzleNode> getDepthLimitedResult(int depthLimit){
		ArrayList<SixPuzzleNode> stack = new ArrayList<SixPuzzleNode>();
		ArrayList<SixPuzzleState> visited = new ArrayList<SixPuzzleState>();
		assert this.startState.getClass() == SixPuzzleState.class;
		stack.add(new SixPuzzleNode((SixPuzzleState)this.startState, null));
		while (!stack.isEmpty()) {
			//System.out.println(queue.size());//test
			SixPuzzleNode currentNode = stack.remove(0);
			//currentNode.getState().print();//test
			if (currentNode.getState().isGoal()) {
				return currentNode.getPath();
			}
			ArrayList<Operator> legalOps = currentNode.getState().getLegalOps();
			for (int i = legalOps.size()-1; i >= 0; i--) {
				assert legalOps.get(i).getClass() == SixPuzzleOperator.class;
				//System.out.println("direction:" + ((SixPuzzleOperator)(legalOps.get(i))).getDirection());//test
				SixPuzzleState nextState = (SixPuzzleState)(currentNode.getState().nextState((SixPuzzleOperator)(legalOps.get(i))));
				//System.out.println("next state");//test
				//nextState.print();//test
				SixPuzzleNode newNode = new SixPuzzleNode(nextState,currentNode);
				if (!visited.contains(nextState) && (newNode.getDepth() < depthLimit)) {
					stack.add(0, newNode);
					visited.add(nextState);
				}
			}
		}
		return null;
	}
	
	public ArrayList<SixPuzzleNode> getIDSResult(){
		for (int i = 0; ;i++) {
			ArrayList<SixPuzzleNode> path = getDepthLimitedResult(i);
			if (path != null) return path;
		}
	}

}
