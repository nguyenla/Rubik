// This class implements a common method to solve the Rubik cube.
// This method includes 7 simple algorithms.

public class Simple_solver {
	private RubikCube cube;
	
	public Simple_solver(String initialState) {
		cube = new RubikCube(initialState);
	}
	
	public void bottom_layer_edge() {
		while (!(cube.check_square("front", 8) && cube.check_square("back", 8) 
				&& cube.check_square("left", 8) && cube.check_square("right", 8))) {
			
			// Set up the desired orientation
			while (!(cube.getColor("front", 5).equals(cube.getColor("right", 8)) 
					&& cube.getColor("right", 5).equals(cube.getColor("front", 8)))) {
				cube.rotateCubeRight();
			}
			
			// Apply moves
			cube.rotateRightAntiClockwise();
			cube.rotateDownClockwise();
			cube.rotateDownClockwise();
			cube.rotateRightClockwise();
			cube.rotateDownClockwise();
			cube.rotateRightAntiClockwise();
			cube.rotateDownClockwise();
			cube.rotateRightClockwise();
			cube.rotateDownClockwise();
		}
	}
	
	// Helper method
	// Need rigorous testing
	public boolean check_bottom_corner(int num) {
		String c1, c2, c3, s1, s2, s3;
		for (int i = 0; i < num - 1; i++) {
			cube.rotateCubeLeft();
		}
		c1 = cube.getColor("down", 5);
		c2 = cube.getColor("left", 5);
		c3 = cube.getColor("front", 5);
		s1 = cube.getColor("down", 1);
		s2 = cube.getColor("left", 9);
		s3 = cube.getColor("front", 7);
		
		for (int i = 0; i < num - 1; i++) {
			cube.rotateCubeRight();
		}
		return (c1.equals(s1) || c1.equals(s2) || c1.equals(s3)) &&
				(c2.equals(s1) || c2.equals(s2) || c2.equals(s3)) &&
				(c3.equals(s1) || c3.equals(s2) || c3.equals(s3));	
	}
	
	public void bottom_layer_corner_location() {
		// Set up the desired orientation
		for (int i = 0; i < 4; i++) {
			if (check_bottom_corner(2)) {
				break;
			}
			else {
				cube.rotateCubeRight();
			}
		}
		
		if (!check_bottom_corner(2)) {
			cube.rotateRightAntiClockwise();
			cube.rotateDownAntiClockwise();
			cube.rotateRightClockwise();
			cube.rotateDownClockwise();
			cube.rotateLeftClockwise();
			cube.rotateDownAntiClockwise();
			cube.rotateRightAntiClockwise();
			cube.rotateDownClockwise();
			cube.rotateLeftAntiClockwise();
			cube.rotateRightClockwise();
			while (!check_bottom_corner(2)) {
				cube.rotateCubeRight();
			}
		}
		
		// Apply steps until the bottom corners are in the correct locations
		while (!check_bottom_corner(1) || !check_bottom_corner(3) || !check_bottom_corner(4)) {
			cube.rotateRightAntiClockwise();
			cube.rotateDownAntiClockwise();
			cube.rotateRightClockwise();
			cube.rotateDownClockwise();
			cube.rotateLeftClockwise();
			cube.rotateDownAntiClockwise();
			cube.rotateRightAntiClockwise();
			cube.rotateDownClockwise();
			cube.rotateLeftAntiClockwise();
			cube.rotateRightClockwise();
		}
	}
	
	// Print the current state of the rubik cube
	public void printState() {
		cube.print();
	}
	
	public static void main(String[] args) {
//		Simple_solver solver = new Simple_solver("uuuuuuuuulllllllllfffffffffrrrrrrrrrdddddddddbbbbbbbbb");
//		String state = "bbbbbbbbboooooogwgwwwwwwrowrrrrrrrroygggggyggyyyyyywyo";
		String state = "bbbbbbbbboooooowoywwwwwwgwyrrrrrrororgggggrggyyyyyywyg";
		Simple_solver solver2 = new Simple_solver(state);
		solver2.printState();
		solver2.bottom_layer_corner_location();
		solver2.printState();
	}
	
}
