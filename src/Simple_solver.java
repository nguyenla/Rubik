// This class implements a common method to solve the Rubik cube.
// This method includes 7 simple algorithms.

public class Simple_solver {
	private RubikCube cube;
	
	public Simple_solver(String initialState) {
		cube = new RubikCube(initialState);
	}
	
	public static void main(String[] args) {
		Simple_solver solver = new Simple_solver("uuuuuuuuulllllllllfffffffffrrrrrrrrrdddddddddbbbbbbbbb");
	}
	
}
