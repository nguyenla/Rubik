import java.util.ArrayList;

/**
 * An RubikWorldState object represents a possible state for a Rubik cube.
 * 
 * @author Lam Nguyen, Sasha Jouravlev, Dickinson College
 * @author Some code copied from Prof. Grant Braught; used with permission.
 * @version September 2014
 */
public class RubikWorldState extends WorldState {

	private RubikCube cube;

	/**
	 * Create a new RubikCube state representing the RubikCube.
	 * 
	 * @param initialCube
	 */
	public RubikWorldState(RubikCube initialCube) {
		cube = initialCube;
	}

	/*
	 * This method checks the position of the hole on the board 
	 * to find all possible actions that can be applied to the current board
	 * 
	 * @see WorldState#getValidActions()
	 */
	@Override
	public ArrayList<Action> getValidActions() {
		ArrayList<Action> actions = new ArrayList<Action>();
		actions.add(new RubikAction(RubikAction.Direction.UpClockwise));
		actions.add(new RubikAction(RubikAction.Direction.UpAntiClockwise));
		actions.add(new RubikAction(RubikAction.Direction.DownClockwise));
		actions.add(new RubikAction(RubikAction.Direction.DownAntiClockwise));
		actions.add(new RubikAction(RubikAction.Direction.LeftClockwise));
		actions.add(new RubikAction(RubikAction.Direction.LeftAntiClockwise));
		actions.add(new RubikAction(RubikAction.Direction.RightClockwise));
		actions.add(new RubikAction(RubikAction.Direction.RightAntiClockwise));
		actions.add(new RubikAction(RubikAction.Direction.BackClockwise));
		actions.add(new RubikAction(RubikAction.Direction.BackAntiClockwise));
		actions.add(new RubikAction(RubikAction.Direction.FrontClockwise));
		actions.add(new RubikAction(RubikAction.Direction.FrontAntiClockwise));
		return actions;
	}

	/* 
	 * This method applies 
	 * @see WorldState#apply(Action)
	 */
	@Override
	public WorldState apply(Action action) {
		RubikAction.Direction direction = ((RubikAction) action).getDirection();
		RubikCube newCube = new RubikCube(cube.getState());
		switch (direction) {
		case UpClockwise:
			newCube.rotateUpClockwise();
			break;
		case UpAntiClockwise:
			newCube.rotateUpAntiClockwise();
			break;
		case DownClockwise:
			newCube.rotateDownClockwise();
			break;
		case DownAntiClockwise:
			newCube.rotateDownAntiClockwise();
			break;
		case LeftClockwise:
			newCube.rotateLeftClockwise();
			break;
		case LeftAntiClockwise:
			newCube.rotateLeftAntiClockwise();
			break;
		case RightClockwise:
			newCube.rotateRightClockwise();
			break;
		case RightAntiClockwise:
			newCube.rotateRightAntiClockwise();
			break;
		case BackClockwise:
			newCube.rotateBackClockwise();
			break;
		case BackAntiClockwise:
			newCube.rotateBackAntiClockwise();
			break;
		case FrontClockwise:
			newCube.rotateFrontClockwise();
			break;
		case FrontAntiClockwise:
			newCube.rotateFrontAntiClockwise();
			break;
		}
		return new RubikWorldState(newCube);
	}

	/* (non-Javadoc)
	 * @see WorldState#toString()
	 */
	@Override
	public String toString() {
		return cube.getState();
	}

	/**
	 * Return a reference to the calling object's puzzle board.
	 * @return A reference to the calling object's puzzle board.
	 */
	public RubikCube getCube() {
		return cube;
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean equals(Object other) {
		RubikWorldState otherState = (RubikWorldState) other;
		return this.cube.getState().equals(otherState.getCube().getState());
	}
}
