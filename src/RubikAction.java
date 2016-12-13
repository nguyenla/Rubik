/**
 * A RubikAction object represents an action in a search tree for
 * RubikCube problems.
 * 
 * @author Lam Nguyen, Sasha Jouravlev
 * @author some code copied from Professor John MacCormick, used with permission.
 * @version December 2016
 */
public class RubikAction extends Action {

	/**
	 * The 12 directions in which the cube can be rotated
	 */
	public enum Direction {
		UpClockwise, UpAntiClockwise, DownClockwise, DownAntiClockwise,
		LeftClockwise, LeftAntiClockwise, RightClockwise, RightAntiClockwise,
		BackClockwise, BackAntiClockwise, FrontClockwise, FrontAntiClockwise
	};

	// The direction in which a tile is moved to perform this action.
	private Direction direction;

	/**
	 * Create a new RubikAction representing a move in a given direction.
	 */
	public RubikAction(Direction direction) {
		super();
		this.direction = direction;
	}

	/** Returns the direction in which the cube is rotated to perform this action
	 * @return The direction in which the cube is rotated to perform this action
	 */
	public Direction getDirection() {
		return direction;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return direction.toString();
	}

}
