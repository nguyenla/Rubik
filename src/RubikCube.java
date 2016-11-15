import java.util.*;

public class RubikCube {
	private String up;
	private String down;
	private String left;
	private String right;
	private String front;
	private String back;

	// Constructor to initialize the orientation of a rubik cube to an arbitrary state
	public RubikCube(String initial) {
		if (initial.length() == 54) {
			up = initial.substring(0, 9);
			left = initial.substring(9, 18);
			front = initial.substring(18, 27);
			right = initial.substring(27, 36);
			down = initial.substring(36, 45);
			back = initial.substring(45, 54);
		}
		else {
			System.out.println("Invalid orientation");
		}
	}

	// Helper method: Rotate clockwise
	public static String rotateClockwise(String state) {
		StringBuilder res = new StringBuilder("");
		res.append(state.charAt(6));
		res.append(state.charAt(3));
		res.append(state.charAt(0));
		res.append(state.charAt(7));
		res.append(state.charAt(4));
		res.append(state.charAt(1));
		res.append(state.charAt(8));
		res.append(state.charAt(5));
		res.append(state.charAt(2));
		return res.toString();
	}

	// Helper method: Rotate anticlockwise
	public static String rotateAntiClockwise(String state) {
		StringBuilder res = new StringBuilder("");
		res.append(state.charAt(2));
		res.append(state.charAt(5));
		res.append(state.charAt(8));
		res.append(state.charAt(1));
		res.append(state.charAt(4));
		res.append(state.charAt(7));
		res.append(state.charAt(0));
		res.append(state.charAt(3));
		res.append(state.charAt(6));
		return res.toString();
	}

	public static String[] move(String s1, String s2, String s3, String s4, int pos1, int pos2, int pos3) {
		String[] res = new String[4];

		return res;
	}

	// Rotate up
	public void rotateUpClockwise() {
		up = rotateClockwise(up);

		// Change in first layer L -> B -> R -> F -> L
		String temp = left.substring(0, 3);
		left = front.substring(0, 3) + left.substring(3);
		front = right.substring(0, 3) + front.substring(3);
		right = back.substring(0, 3) + right.substring(3);
		back = temp + back.substring(3);
	}

	public void rotateUpAntiClockwise() {
		up = rotateAntiClockwise(up);

		// Change in first layer L -> F -> R -> B -> L
		String temp = left.substring(0, 3);
		left = back.substring(0, 3) + left.substring(3);
		back = right.substring(0, 3) + back.substring(3);
		right = front.substring(0, 3) + right.substring(3);
		front = temp + front.substring(3);
	}

	// Rotate down
	public void rotateDownClockwise() {
		down = rotateClockwise(down);

		// Change in first layer L -> F -> R -> B -> L
		// This is the same order as rotateUpAnticlockwise
		String temp = left.substring(6);
		left = left.substring(0, 6) + back.substring(6);
		back = back.substring(0, 6) + right.substring(6);
		right = right.substring(0, 6) + front.substring(6);
		front = front.substring(0, 6) + temp;
	}

	public void rotateDownAntiClockwise() {
		down = rotateAntiClockwise(down);

		// Change in first layer L -> B -> R -> F -> L
		// This is the same order as rotateUpClockwise
		String temp = left.substring(6);
		left = left.substring(0, 6) + front.substring(6);
		front = front.substring(0, 6) + right.substring(6);
		right = right.substring(0, 6) + back.substring(6);
		back = back.substring(0, 6) + temp;
	}

	// Rotate left
	public void rotateLeftClockwise() {

	}

	public void rotateLeftAntiClockwise() {

	}

	// Rotate right
	public void rotateRightClockwise() {

	}

	public void rotateRightAntiClockwise() {

	}

	// Rotate front
	public void rotateFrontClockwise() {

	}

	public void rotateFrontAntiClockwise() {

	}

	// Rotate back
	public void rotateBackClockwise() {

	}

	public void rotateBackAntiClockwise() {

	}

	// Helper method: Print the rubik cube
	public void print() {
		System.out.println("Current state:");
		System.out.println("    " + up.substring(0, 3));
		System.out.println("    " + up.substring(3, 6));
		System.out.println("    " + up.substring(6, 9));
		System.out.println();

		System.out.println(left.substring(0, 3) + " " + front.substring(0, 3) + " " + right.substring(0, 3));
		System.out.println(left.substring(3, 6) + " " + front.substring(3, 6) + " " + right.substring(3, 6));
		System.out.println(left.substring(6, 9) + " " + front.substring(6, 9) + " " + right.substring(6, 9));
		System.out.println();

		System.out.println("    " + down.substring(0, 3));
		System.out.println("    " + down.substring(3, 6));
		System.out.println("    " + down.substring(6, 9));
		System.out.println();

		System.out.println("    " + back.substring(0, 3));
		System.out.println("    " + back.substring(3, 6));
		System.out.println("    " + back.substring(6, 9));
		System.out.println();
	}

	// Main method to run driver tests
	public static void main(String[] args) {	
		// Test case for rotateUpClockwise
		String initial = "uuuuuuuuulllllllllfffffffffrrrrrrrrrdddddddddbbbbbbbbb";
		RubikCube cube = new RubikCube(initial);
		cube.rotateDownAntiClockwise();
		//		cube.rotateUpAntiClockwise();
		//		cube.rotateUpAntiClockwise();
		//		cube.rotateUpAntiClockwise();
		//		cube.rotateUpAntiClockwise();
		cube.print();
	}
}
