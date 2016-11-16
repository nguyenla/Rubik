import java.util.ArrayList;

// This class implements a common method to solve the Rubik cube.
// This method includes 7 simple algorithms.

public class Simple_solver {
	private RubikCube cube;
	private ArrayList<String> steps;
	
	public Simple_solver(String initialState) {
		cube = new RubikCube(initialState);
		steps = new ArrayList<String>();
	}
	
	public void bottom_cross_helper() {
		cube.rotateRightAntiClockwise();
		cube.rotateBackAntiClockwise();
		cube.rotateDownAntiClockwise();
		cube.rotateBackClockwise();
		cube.rotateDownClockwise();
		cube.rotateRightClockwise();
	}
	
	// Create a cross of the same color in the bottom face of the cube
	public void bottom_cross() {
		// make sure there is one square with the right color
		if (!cube.check_square("down", 2) && !cube.check_square("down", 4) 
			&& !cube.check_square("down", 6) && !cube.check_square("down", 8)) {
			bottom_cross_helper();
		}
		
		while (!cube.check_square("down", 2)) {
			cube.rotateCubeLeft();
		}
		
		// make sure there are at least 2 squares with the right color
		if (!cube.check_square("down", 4)  && !cube.check_square("down", 6) && !cube.check_square("down", 8)) {
			bottom_cross_helper();
		}
		
		// make sure that the vertical strip is of the same color
		if (!cube.check_square("down", 8)) {
			if (cube.check_square("down", 4)) {
				bottom_cross_helper();
			}
			else if (cube.check_square("down", 6)) {
				cube.rotateCubeLeft();
				bottom_cross_helper();
			}
		}
		
		// Create the cross if it is not formed yet
		if (cube.check_square("down", 8) && !cube.check_square("down", 4)) {
			bottom_cross_helper();
		}
	}
	
	// This method makes sure that the center square of the bottom layer of each face has the correct color
	public void bottom_layer_edge() {
		while (!cube.check_square("front", 8)) {
			cube.rotateDownClockwise();
		}
		cube.rotateCubeLeft();
		
		// while not all the bottom middle squares are correct
		while (!cube.check_square("front", 8)) {
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
			
			cube.rotateCubeLeft();
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
	
	// Final step
	public void bottom_layer() {
		// While the cube is not solved
		while (!cube.check_square("front", 9) || !cube.check_square("left", 9) || !cube.check_square("right", 9) || !cube.check_square("back", 9)) {
			// set up the desired location
			while (cube.check_square("front", 9)) {
				cube.rotateCubeLeft();
			}
			System.out.println("Before move:");
			printState();
			while (!cube.check_square("front", 9)) {
				cube.rotateRightAntiClockwise();
				cube.rotateDownAntiClockwise();
				cube.rotateDownAntiClockwise();
				cube.rotateRightClockwise();
				cube.rotateDownClockwise();
				cube.rotateRightAntiClockwise();
				cube.rotateDownClockwise();
				cube.rotateRightClockwise();
				
				cube.rotateLeftClockwise();
				cube.rotateDownAntiClockwise();
				cube.rotateDownAntiClockwise();
				cube.rotateLeftAntiClockwise();
				cube.rotateDownAntiClockwise();
				cube.rotateLeftClockwise();
				cube.rotateDownAntiClockwise();
				cube.rotateLeftAntiClockwise();
			}
		}
	}
	
	// Solve method
	public void solve() {
		bottom_layer_edge();
		bottom_layer_corner_location();
		bottom_layer();
		printState();
	}
	
	// Print the current state of the rubik cube
	public void printState() {
		cube.print();
	}
	
	public static void main(String[] args) {
//		Simple_solver solver = new Simple_solver("uuuuuuuuulllllllllfffffffffrrrrrrrrrdddddddddbbbbbbbbb");
//		String state = "bbbbbbbbboooooogwgwwwwwwrowrrrrrrrroygggggyggyyyyyywyo";
//		String state = "bbbbbbbbboooooowoywwwwwwgwyrrrrrrororgggggrggyyyyyywyg";
		String state = "bbbbbbbbboooooogggwwwwwwrggrrrrrrogryowrgwyywyyyyyyggo";
		Simple_solver solver2 = new Simple_solver(state);
		solver2.printState();
		solver2.bottom_cross();
		solver2.printState();
		solver2.solve();
	}
}