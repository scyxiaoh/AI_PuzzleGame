package abstracts;
import java.util.ArrayList;

public abstract class Problem {
	
	protected State startState;
	
	public abstract boolean isGoal (State crtState);
	public abstract boolean isLegal (State s, Operator op);
	public abstract ArrayList<Operator> getLegalOps(State s);
	public abstract State nextState(State crtStatem, Operator op);
	public abstract float cost(State s, Operator op);
	
	public State getStartState() {return startState;}
}
