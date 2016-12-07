import java.util.ArrayList;

// This class implements a common method to solve the Rubik cube.
// This method includes 7 simple algorithms.

public class Simple_solver {
	private RubikCube cube;
	private ArrayList<String> steps;

	// Constructor
	public Simple_solver(String initialState) {
		cube = new RubikCube(initialState);
		steps = new ArrayList<String>();
	}

	// Helper method
	// This method counts the number of middle edge squares on the top face with the correct color
	public int helper_num_correct_center_top() {
		int numCorrect = 0;
		if (cube.check_square("up", 2) && cube.check_square("back", 2)) {
			numCorrect++;
		}
		if (cube.check_square("up", 4) && cube.check_square("left", 2)) {
			numCorrect++;
		}
		if (cube.check_square("up", 6) && cube.check_square("right", 2)) {
			numCorrect++;
		}
		if (cube.check_square("up", 8) && cube.check_square("front", 2)) {
			numCorrect++;
		}
		return numCorrect;
	}
	
	// Helper method
	// This method counts the number of corners on the top face with the correct color
	public int helper_num_correct_corners_top() {
		int numCorrect = 0;
		if (cube.check_square("up", 1)) {
			numCorrect++;
		}
		if (cube.check_square("up", 3)) {
			numCorrect++;
		}
		if (cube.check_square("up", 7)) {
			numCorrect++;
		}
		if (cube.check_square("up", 9)) {
			numCorrect++;
		}
		return numCorrect;
	}

	// Accessor method for the cube field
	public RubikCube getCube() {
		return cube;
	}

	public void first_layer_center() {
		String top = cube.getColor("up", 5);
		int numCorrect = helper_num_correct_center_top();
		
		// if there is a middle square with the correct color at the top, but at the wrong location
		if (numCorrect > 0) {
			int i = 0;
			while (i < 4) {
				if (cube.check_square("up", 8) && !cube.check_square("front", 2)) {
					i = 0;
					cube.rotateFrontClockwise();
					cube.rotateFrontClockwise();
					while (!cube.check_square("front", 8)) {
						cube.rotateCubeLeft();
						cube.rotateDownClockwise();
					}
					cube.rotateFrontClockwise();
					cube.rotateFrontClockwise();
				}
				else {
					cube.rotateCubeLeft();
					i++;
				}
			}
		}
		numCorrect = helper_num_correct_center_top();
		
		while (numCorrect < 4) {
			// if a middle edge square is on the bottom
			while (cube.getFace("down").contains(top)) {
				while (!cube.getColor("down", 2).equals(top)) {
					cube.rotateCubeLeft();;
				}
				while (!cube.check_square("front", 8)) {
					cube.rotateCubeLeft();
					cube.rotateDownClockwise();
				}
				cube.rotateFrontClockwise();
				cube.rotateFrontClockwise();
				numCorrect = helper_num_correct_center_top();
			}
			
			// if a middle edge square is on one of (back, front, left, right)
			int i = 0;
			while (i < 4) {
				if (cube.getColor("front", 2).equals(top)) {
					i = 0;
					cube.rotateFrontAntiClockwise();
					cube.rotateLeftClockwise();
					cube.rotateCubeRight();
					helper_bring_middle_up();
				}
				else if (cube.getColor("front", 4).equals(top)) {
					i = 0;
					cube.rotateLeftClockwise();
					cube.rotateCubeRight();
					helper_bring_middle_up();
				}
				else if (cube.getColor("front", 6).equals(top)) {
					i = 0;
					cube.rotateRightAntiClockwise();
					cube.rotateCubeLeft();
					helper_bring_middle_up();
				}
				else if (cube.getColor("front", 8).equals(top)) {
					i = 0;
					cube.rotateFrontAntiClockwise();
					cube.rotateRightAntiClockwise();
					cube.rotateCubeLeft();
					helper_bring_middle_up();
				}
				else {
					cube.rotateCubeLeft();
					i++;
				}
			}
			numCorrect = helper_num_correct_center_top();
		}
	}


	// Helper method
	// Different from bring_left_up() and bring_right_up()
	public void helper_bring_middle_up() {
		while (!cube.check_square("front", 8)) {
			cube.rotateCubeLeft();
			cube.rotateDownClockwise();
		}
		cube.rotateFrontClockwise();
		cube.rotateFrontClockwise();
	}
	
	// This method arranges the corner square of the first layer
	// After executing this method, all the corner square on the top face should be correct
	public void first_layer_corner() {
		int numCorrect = helper_num_correct_corners_top(); // number of correct corners on the top face
		String top = cube.getColor("up", 5);

		while (numCorrect < 4) {
			// if the bottom face contains a square with the color of the top face
			while (cube.getFace("down").contains(top)) {
				while (!cube.getColor("down", 1).equals(top)) {
					cube.rotateDownClockwise();
				}
				cube.rotateLeftClockwise();
				cube.rotateDownAntiClockwise();
				cube.rotateLeftAntiClockwise();
				cube.rotateCubeLeft();
				helper_first_layer_corner("right");
				numCorrect = helper_num_correct_corners_top();
			}

			int i = 0;
			// rotate the cube until the front face contains a square with the color of the top face 
			while (!cube.getFace("front").contains(top) && i < 4) {
				cube.rotateCubeLeft();
				i++;
			}
			// bring that square up to its corresponding location on the top face
			if (cube.getColor("front", 1).equals(top)) {
				cube.rotateLeftClockwise();
				cube.rotateDownAntiClockwise();
				cube.rotateLeftAntiClockwise();
				helper_first_layer_corner("left");
				numCorrect = helper_num_correct_corners_top();
			}

			if (cube.getColor("front", 3).equals(top)) {
				cube.rotateRightAntiClockwise();
				cube.rotateDownClockwise();
				cube.rotateRightClockwise();
				helper_first_layer_corner("right");
				numCorrect = helper_num_correct_corners_top();			}

			if (cube.getColor("front", 7).equals(top)) {
				helper_first_layer_corner("left");
				numCorrect = helper_num_correct_corners_top();
			}

			if (cube.getColor("front", 9).equals(top)) {
				helper_first_layer_corner("right");
				numCorrect = helper_num_correct_corners_top();
			}
		}
		
		// TODO: implement swapping if necessary
	}

	// Helper method
	// TODO: COMMENT HERE
	public void helper_first_layer_corner(String pos) {
		if (pos.equals("left")) {
			while (!cube.check_square("left", 9)) {
				cube.rotateCubeLeft();
				cube.rotateDownClockwise();
			}
			bring_left_up();
		}
		else if (pos.equals("right")) {
			while (!cube.check_square("right", 7)) {
				cube.rotateCubeLeft();
				cube.rotateDownClockwise();
			}
			bring_right_up();
		}
		else {
			System.out.println("Error: Wrong argument used for function helper_first_layer_corner.");
		}
	}

	// Helper method: This method brings all the top squares from the bottom layer to their correct places
	public void bring_all_top_squares_up() {
		String top = cube.getColor("up", 5);
		int i = 0;
		while ( i < 4) {
			if (cube.getColor("front", 9).equals(top)) {
				while (!cube.getColor("right", 7).equals(cube.getColor("right",5))) {
					cube.rotateCubeLeft();
					cube.rotateDownClockwise();
				}
				bring_right_up();
				System.out.println("Bring right up");
				i = 0;
			}
			else if (cube.getColor("front", 7).equals(top)) {
				while (!cube.getColor("left", 9).equals(cube.getColor("left",5))) {
					cube.rotateCubeRight();
					cube.rotateDownAntiClockwise();
				}
				bring_left_up();
				System.out.println("Bring left up");
				i = 0;
			}
			else {
				cube.rotateCubeLeft();
				i++;
			}
		}
	}

	// Helper method: Bring bottom-left square of the front face up
	public void bring_left_up() {
		cube.rotateDownClockwise();
		cube.rotateLeftClockwise();
		cube.rotateDownAntiClockwise();
		cube.rotateLeftAntiClockwise();
	}

	// Helper method: Bring left square down to bottom layer
	public void bring_left_down() {
		cube.rotateLeftClockwise();
		cube.rotateDownClockwise();
		cube.rotateLeftAntiClockwise();
	}

	// Helper method: Bring bottom-right square of the front face up
	public void bring_right_up() {
		cube.rotateDownAntiClockwise();
		cube.rotateRightAntiClockwise();
		cube.rotateDownClockwise();
		cube.rotateRightClockwise();
	}

	// Helper method: Bring bottom-right square of the front face down
	// This method can be used to move a desired color from the right side to the bottom layer
	public void bring_right_down() {
		cube.rotateRightAntiClockwise();
		cube.rotateDownAntiClockwise();
		cube.rotateRightClockwise();
	}

	// Helper method: Bring bottom-right square of the front face down
	// while moving the desired color from top right of the front side to the bottom layer
	public void bring_right_down_front_color() {
		cube.rotateRightAntiClockwise();
		cube.rotateDownClockwise();
		cube.rotateRightClockwise();
		cube.rotateDownAntiClockwise();
	}

	// This method switch the position of the bottom center square and the right center square
	// on the front face
	public void swap_second_layer(String direction) {
		if (direction.equals("right")) {
			cube.rotateDownAntiClockwise();
			cube.rotateRightAntiClockwise();
			cube.rotateDownClockwise();
			cube.rotateRightClockwise();
			cube.rotateDownClockwise();
			cube.rotateFrontClockwise();
			cube.rotateDownAntiClockwise();
			cube.rotateFrontAntiClockwise();
		}
		else if (direction.equals("left")) {
			cube.rotateDownClockwise();
			cube.rotateLeftClockwise();
			cube.rotateDownAntiClockwise();
			cube.rotateLeftAntiClockwise();
			cube.rotateDownAntiClockwise();
			cube.rotateFrontAntiClockwise();
			cube.rotateDownClockwise();
			cube.rotateFrontClockwise();
		}
	}

	public void helper_second_layer() {
		String bottom = cube.getColor("down", 5);
		while (!( (cube.getColor("front", 8).equals(bottom) || cube.getColor("down", 2).equals(bottom)) &&
				(cube.getColor("left", 8).equals(bottom) || cube.getColor("down", 4).equals(bottom)) && 
				(cube.getColor("right", 8).equals(bottom) || cube.getColor("down", 6).equals(bottom)) && 
				(cube.getColor("back", 8).equals(bottom) || cube.getColor("down", 8).equals(bottom)))) {
			while (cube.getColor("front", 8).equals(bottom) || cube.getColor("down", 2).equals(bottom)) {
				cube.rotateCubeLeft();
			}
			while (!cube.check_square("front", 8)) {
				cube.rotateCubeLeft();
				cube.rotateDownClockwise();
			}
			if (cube.getColor("down", 2).equals(cube.getColor("right", 5))) {
				swap_second_layer("right");
			}
			else if (cube.getColor("down", 2).equals(cube.getColor("left", 5))) {
				swap_second_layer("left");
			}
		}
	}
	// This method put all the squares on the second layer in the correct position
	public void second_layer() {
		helper_second_layer();
		while (!(cube.check_square("front", 4) && cube.check_square("front", 6) &&
				cube.check_square("left", 4) && cube.check_square("left", 6) &&
				cube.check_square("right", 4) && cube.check_square("right", 6) &&
				cube.check_square("back", 4) && cube.check_square("back", 6))) {
			while (cube.check_square("front", 6) && cube.check_square("right", 4)) {
				cube.rotateCubeLeft();	
			}
			if (!(cube.check_square("front", 6) && cube.check_square("right", 4))) {
				swap_second_layer("right");
				helper_second_layer();
			}
		}
	}

	// This method includes a series of moves that might be executed multiple times
	// to get the cross in the bottom
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

	// This method swaps the bottom middle squares of two adjacent faces
	public void helper_bottom_layer_edge() {
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

	// This method makes sure that the center square of the bottom layer of each face has the correct color
	public void bottom_layer_edge() {
		while (!cube.check_square("front", 8)) {
			cube.rotateDownClockwise();
		}
		cube.rotateCubeLeft();

		// if front and left are done
		if (cube.check_square("front", 8)) {
			cube.rotateCubeLeft();
			if (!cube.check_square("front", 8)) {
				helper_bottom_layer_edge();
			}
		}

		// if back and left are done, we only need to do one more swap
		else if (cube.getColor("front", 8).equals(cube.getColor("right", 5)) &&
				cube.getColor("right", 8).equals(cube.getColor("front", 5))) {
			helper_bottom_layer_edge();
		}

		// if the front face is not done, but the right face is done
		else if (cube.check_square("right", 8) && !cube.check_square("front", 8)) {
			cube.rotateDownAntiClockwise();
			helper_bottom_layer_edge();
			cube.rotateCubeLeft();
			cube.rotateCubeLeft();
			helper_bottom_layer_edge();
		} 

		// if only the right face is done
		else if (cube.check_square("right", 8) && !cube.check_square("front", 8) && !cube.check_square("back", 8)) {
			cube.rotateDownAntiClockwise();
			helper_bottom_layer_edge();
			cube.rotateCubeRight();
			cube.rotateCubeRight();
			helper_bottom_layer_edge();
		}
		// if all the remaining faces are not done
		else {
			helper_bottom_layer_edge();
			cube.rotateCubeLeft();
			helper_bottom_layer_edge();
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

	public void helper_bottom_layer_corner_location() {
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
			helper_bottom_layer_corner_location();
			while (!check_bottom_corner(2)) {
				cube.rotateCubeRight();
			}
		}

		// Apply steps until the bottom corners are in the correct locations
		while (!check_bottom_corner(1) || !check_bottom_corner(3) || !check_bottom_corner(4)) {
			//			System.out.println("Stuck");
			//			printState();
			helper_bottom_layer_corner_location();
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
			//			printState();
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
		first_layer_center();
		System.out.println("After completing all first layer middle squares:");
		printState();
		
		first_layer_corner();
		System.out.println("After completing all first layer corners:");
		printState();
		
		second_layer();
		System.out.println("After second layer:");
		printState();

		bottom_cross();
		System.out.println("After bottom cross:");
		printState();

		bottom_layer_edge();
		System.out.println("After bottom center square:");
		printState();

		bottom_layer_corner_location();
		System.out.println("After bottom_layer_corner_location(), the locations of the corners should be correct.");
		printState();

		bottom_layer();
		System.out.println("After bottom_layer(), the cube should be solved.");
		printState();
	}

	// Print the current state of the rubik cube
	public void printState() {
		cube.print();
	}

	public static void main(String[] args) {
		// Simple_solver solver = new Simple_solver("uuuuuuuuulllllllllfffffffffrrrrrrrrrdddddddddbbbbbbbbb");
		String state = "bwgybygyw,gorrowrbo,wwyoybbrr,orooryygo,ybbgwowwg,brrbggwgy";
		Simple_solver solver2 = new Simple_solver(state);
		System.out.println("Initial state:");
		solver2.printState();
		solver2.solve();
		solver2.printState();
		System.out.println(solver2.getCube().getMove());
	}
}