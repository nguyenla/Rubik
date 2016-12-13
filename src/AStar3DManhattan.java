/**
 * An AStarManhattan object represents a node in a search tree, in which
 * A* search is used using the Manhattan (or city block) heuristic.
 * 
 * @author Sasha Jouravlev, Lam Nguyen
 * @version December 2016
 */
public class AStar3DManhattan extends AStarAbstract {

	/**
	 * @param parent
	 * @param state
	 * @param action
	 */
	public AStar3DManhattan(SearchNode parent, WorldState state, Action action) {
		super(parent, state, action);

		// if this isn't the root of the tree, set the number of moves to the parent's number plus 1
		if (parent!=null) {
			this.setNumMoves(((AStar3DManhattan)parent).getNumMoves() + 1);
		}

		// set the cost for this node: the number of moves it took to get to this state + the heuristic
		cost = this.getNumMoves() + this.findHeuristics(state);
		//System.out.println(cost);
	}

	/**
	 * Returns the 3D Manhattan heuristic for the current node
	 */
	@Override
	public double findHeuristics(WorldState current) { 
		double totalEdge = 0;
		double totalCorner = 0;
		String state = ((RubikWorldState) current).getCube().getState();
		RubikCube cube = new RubikCube(state);
		for (int i = 0; i < 4; i++) {
			cube.rotateCubeRight();
			totalEdge += manhattan_edge(cube);
			totalCorner += manhattan_corner(cube);
		}
		cube.rotateCubeUp();
		cube.rotateCubeUp();
		for (int i = 0; i < 4; i++) {
			cube.rotateCubeRight();
			totalEdge += manhattan_edge(cube);
			totalCorner += manhattan_corner(cube);
		}
		cube.rotateCubeUp();
		cube.rotateCubeRight();
		for (int i = 0; i < 4; i++) {
			cube.rotateCubeUp();
			totalEdge += manhattan_edge(cube);
		}
		cube.rotateCubeLeft();
		cube.rotateCubeDown();
		
		return (totalEdge + totalCorner) / 4 + this.getNumMoves();
	}

	/**
	 * Create a new node, which is a child of the calling node, and which has
	 * the given state and action.
	 * 
	 * Returns: The newly-constructed child node.
	 */
	@Override
	protected SearchNode createChild(WorldState childState, Action action) {
		return new AStar3DManhattan(this, childState, action);
	}

	// Helper function
	// Calculate the 3D Manhattan distance from the square at the up-front edge
	// to its correct location and orientation
	// These numbers were found manually and might be sub-optimal
	public static int manhattan_edge(RubikCube cube) {
		String color1 = cube.getColor("up", 8);
		String color2 = cube.getColor("front", 2);
		String colorUp = cube.getColor("up", 5);
		String colorDown = cube.getColor("down", 5);
		String colorBack = cube.getColor("back", 5);
		String colorFront = cube.getColor("front", 5);
		String colorLeft = cube.getColor("left", 5);
		String colorRight = cube.getColor("right", 5);

		// return the distance that corresponds to each of the 24 cases
		// Case 1:
		if (color1.equals(colorUp) && color2.equals(colorFront)) {
			return 0;
		}
		// Case 2:
		else if (color1.equals(colorFront) && color2.equals(colorUp)) {
			return 3;
		}
		// Case 3: right - front
		else if (color1.equals(colorRight) && color2.equals(colorFront)) {
			return 1;
		}
		// Case 4: front - right
		else if (color1.equals(colorFront) && color2.equals(colorRight)) {
			return 2;
		}
		// Case 5: left - front
		else if (color1.equals(colorLeft) && color2.equals(colorFront)) {
			return 1;
		}
		// Case 6: front - left
		else if (color1.equals(colorFront) && color2.equals(colorLeft)) {
			return 1;
		}
		// Case 7: down - front
		else if (color1.equals(colorDown) && color2.equals(colorFront)) {
			return 2;
		}
		// Case 8: front - down
		else if (color1.equals(colorFront) && color2.equals(colorDown)) {
			return 3;
		}
		// Case 9: up - left
		else if (color1.equals(colorUp) && color2.equals(colorLeft)) {
			return 1;
		}
		// Case 10: left - up
		else if (color1.equals(colorLeft) && color2.equals(colorUp)) {
			return 3;
		}
		// Case 11: up - right
		else if (color1.equals(colorUp) && color2.equals(colorRight)) {
			return 1;
		}
		// Case 12: right - up
		else if (color1.equals(colorRight) && color2.equals(colorUp)) {
			return 3;
		}
		// Case 13: up - back
		else if (color1.equals(colorUp) && color2.equals(colorBack)) {
			return 2;
		}
		// Case 14: back - up
		else if (color1.equals(colorBack) && color2.equals(colorUp)) {
			return 3;
		}
		// Case 15: left - back
		else if (color1.equals(colorLeft) && color2.equals(colorBack)) {
			return 3;
		}
		// Case 16: back - left
		else if (color1.equals(colorBack) && color2.equals(colorLeft)) {
			return 2;
		}
		// Case 17: right - back
		else if (color1.equals(colorRight) && color2.equals(colorBack)) {
			return 3;
		}
		// Case 18: back - right
		else if (color1.equals(colorBack) && color2.equals(colorRight)) {
			return 2;
		}
		// Case 19: left - down
		else if (color1.equals(colorLeft) && color2.equals(colorDown)) {
			return 2;
		}
		// Case 20: down - left
		else if (color1.equals(colorDown) && color2.equals(colorLeft)) {
			return 3;
		}
		// Case 21: back - down
		else if (color1.equals(colorBack) && color2.equals(colorDown)) {
			return 3;
		}
		// Case 22: down - back
		else if (color1.equals(colorDown) && color2.equals(colorBack)) {
			return 4;
		}
		// Case 23: right - down
		else if (color1.equals(colorRight) && color2.equals(colorDown)) {
			return 2;
		}
		// Case 24: down - right
		else if (color1.equals(colorDown) && color2.equals(colorRight)) {
			return 3;
		}

		return Integer.MAX_VALUE;
	}

	// Helper function
	// Calculate the 3D Manhattan distance from the square at the up-left-front corner
	// to its correct location and orientation
	// These numbers were found manually and might be sub-optimal
	public static int manhattan_corner(RubikCube cube) {
		String color1 = cube.getColor("up", 7);
		String color2 = cube.getColor("left", 3);
		String color3 = cube.getColor("front", 1);
		String colorUp = cube.getColor("up", 5);
		String colorDown = cube.getColor("down", 5);
		String colorBack = cube.getColor("back", 5);
		String colorFront = cube.getColor("front", 5);
		String colorLeft = cube.getColor("left", 5);
		String colorRight = cube.getColor("right", 5);
		
		// Case 1: up - left - front
		if (color1.equals(colorUp) && color2.equals(colorLeft) && color3.equals(colorFront)) {
			return 0;
		}
		// Case 2: left - front - up
		else if (color1.equals(colorLeft) && color2.equals(colorFront) && color3.equals(colorUp)) {
			return 2;
		}
		// Case 3: front - up - left
		else if (color1.equals(colorFront) && color2.equals(colorUp) && color3.equals(colorLeft)) {
			return 2;
		}

		// Case 4: up - front - right
		else if (color1.equals(colorUp) && color2.equals(colorFront) && color3.equals(colorRight)) {
			return 1;
		}
		// Case 5: front - right - up
		else if (color1.equals(colorFront) && color2.equals(colorRight) && color3.equals(colorUp)) {
			return 3;
		}
		// Case 6: right - up - front
		else if (color1.equals(colorRight) && color2.equals(colorUp) && color3.equals(colorFront)) {
			return 1;
		}

		// Case 7: up - right - back
		else if (color1.equals(colorUp) && color2.equals(colorRight) && color3.equals(colorBack)) {
			return 2;
		}
		// Case 8: right - back - up
		else if (color1.equals(colorRight) && color2.equals(colorBack) && color3.equals(colorUp)) {
			return 2;
		}
		// Case 9: back - up - right
		else if (color1.equals(colorBack) && color2.equals(colorUp) && color3.equals(colorRight)) {
			return 2;
		}
		
		// Case 10: up - back - left
		else if (color1.equals(colorUp) && color2.equals(colorBack) && color3.equals(colorLeft)) {
			return 1;
		}
		// Case 11: back - left - up
		else if (color1.equals(colorBack) && color2.equals(colorLeft) && color3.equals(colorUp)) {
			return 1;
		}
		// Case 12: left - up - back
		else if (color1.equals(colorLeft) && color2.equals(colorUp) && color3.equals(colorBack)) {
			return 3;
		}

		// Case 13: front - left - down
		else if (color1.equals(colorFront) && color2.equals(colorLeft) && color3.equals(colorDown)) {
			return 1;
		}
		// Case 14: left - down - front
		else if (color1.equals(colorLeft) && color2.equals(colorDown) && color3.equals(colorFront)) {
			return 1;
		}
		// Case 15: down - front - left
		else if (color1.equals(colorDown) && color2.equals(colorFront) && color3.equals(colorLeft)) {
			return 3;
		}

		// Case 16: front - down - right
		else if (color1.equals(colorFront) && color2.equals(colorDown) && color3.equals(colorRight)) {
			return 2;
		}
		// Case 17: down - right - front
		else if (color1.equals(colorDown) && color2.equals(colorRight) && color3.equals(colorFront)) {
			return 2;
		}
		// Case 18: right - front - down
		else if (color1.equals(colorRight) && color2.equals(colorFront) && color3.equals(colorDown)) {
			return 2;
		}

		// Case 19: left - back - down
		else if (color1.equals(colorLeft) && color2.equals(colorBack) && color3.equals(colorDown)) {
			return 2;
		}
		// Case 20: back - down - left
		else if (color1.equals(colorBack) && color2.equals(colorDown) && color3.equals(colorLeft)) {
			return 2;
		}
		// Case 21: down - left - back
		else if (color1.equals(colorDown) && color2.equals(colorLeft) && color3.equals(colorBack)) {
			return 2;
		}

		// Case 22: right - down - back
		else if (color1.equals(colorRight) && color2.equals(colorDown) && color3.equals(colorBack)) {
			return 3;
		}
		// Case 23: down - back - right
		else if (color1.equals(colorDown) && color2.equals(colorBack) && color3.equals(colorRight)) {
			return 3;
		}
		// Case 24: back - right - down
		else if (color1.equals(colorBack) && color2.equals(colorRight) && color3.equals(colorDown)) {
			return 3;
		}
		

		return Integer.MAX_VALUE;
	}
}
