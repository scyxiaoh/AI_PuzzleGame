package sixPuzzle;

import java.util.ArrayList;

public class ProblemTester {

	public static void main(String[] args) {
		SixPuzzleState initialState = new SixPuzzleState(new int[] {1,4,2,5,3,0});
		SixPuzzleProblem q1 = new SixPuzzleProblem(initialState);
		ArrayList<SixPuzzleNode> bfsPath = q1.getBFSResult();
		ArrayList<SixPuzzleNode> dfsPath = q1.getDFSResult();
		ArrayList<SixPuzzleNode> idsPath = q1.getIDSResult();
		
		System.out.println("--------------------BFS---------------------");
		for (int i = 0; i < bfsPath.size(); i++) {
			bfsPath.get(i).getState().print();
		}
		
		System.out.println("--------------------DFS---------------------");
		for (int i = 0; i < dfsPath.size(); i++) {
			dfsPath.get(i).getState().print();
		}
		
		System.out.println("--------------------IDS---------------------");
		for (int i = 0; i < idsPath.size(); i++) {
			idsPath.get(i).getState().print();
		}
	}

}
