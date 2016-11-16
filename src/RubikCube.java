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

	public String getState() {
		return up + left + front + right + down + back;
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

	public static String replaceAtIndex(String original, int index, char replacement) {
		return original.substring(0, index) + replacement + original.substring(index + 1);
	}

	// A very sophisticated method to execute a simple task
	// *** Need better comment ***
	public static String[] permute(String s1, String s2, String s3, String s4,
			int loc1, int loc2, int loc3, int loc4) {
		String[] res = new String[4];
		int a1 = loc1 / 100 - 1;	int a2 = (loc1 / 10) % 10 - 1;	int a3 = loc1 % 10 - 1;
		int b1 = loc2 / 100 - 1;	int b2 = (loc2 / 10) % 10 - 1;	int b3 = loc2 % 10 - 1; 
		int c1 = loc3 / 100 - 1;	int c2 = (loc3 / 10) % 10 - 1;	int c3 = loc3 % 10 - 1; 
		int d1 = loc4 / 100 - 1;	int d2 = (loc4 / 10) % 10 - 1;	int d3 = loc4 % 10 - 1; 

		char temp1 = s1.charAt(a1);
		char temp2 = s1.charAt(a2);
		char temp3 = s1.charAt(a3);
		s1 = replaceAtIndex(s1, a1, s4.charAt(d1));
		s1 = replaceAtIndex(s1, a2, s4.charAt(d2));
		s1 = replaceAtIndex(s1, a3, s4.charAt(d3));

		s4 = replaceAtIndex(s4, d1, s3.charAt(c1));
		s4 = replaceAtIndex(s4, d2, s3.charAt(c2));
		s4 = replaceAtIndex(s4, d3, s3.charAt(c3));

		s3 = replaceAtIndex(s3, c1, s2.charAt(b1));
		s3 = replaceAtIndex(s3, c2, s2.charAt(b2));
		s3 = replaceAtIndex(s3, c3, s2.charAt(b3));

		s2 = replaceAtIndex(s2, b1, temp1);
		s2 = replaceAtIndex(s2, b2, temp2);
		s2 = replaceAtIndex(s2, b3, temp3);

		res[0] = s1;
		res[1] = s2;
		res[2] = s3;
		res[3] = s4;
		return res;
	}

	// Rotate up clockwise
	public void rotateUpClockwise() {
		up = rotateClockwise(up);

		// Change in first layer L -> B -> R -> F -> L
		String temp = left.substring(0, 3);
		left = front.substring(0, 3) + left.substring(3);
		front = right.substring(0, 3) + front.substring(3);
		right = back.substring(0, 3) + right.substring(3);
		back = temp + back.substring(3);
	}

	// Rotate up anti-clockwise
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

	// Rotate left clockwise
	// U (147) -> F (147) -> D (147) -> B (963) -> U (147)
	public void rotateLeftClockwise() {
		left = rotateClockwise(left);
		String[] temp = permute(up, front, down, back, 147, 147, 147, 963);
		up = temp[0];
		front = temp[1];
		down = temp[2];
		back = temp[3];
	}

	// Rotate left anti-clockwise
	// U (147) -> B (963) -> D (147) -> F (147) -> U (147)
	public void rotateLeftAntiClockwise() {
		left = rotateAntiClockwise(left);
		String[] temp = permute(up, back, down, front, 147, 963, 147, 147);
		up = temp[0];
		back = temp[1];
		down = temp[2];
		front = temp[3];
	}

	// Rotate right clockwise
	// U (963) -> B (147) -> D (963) -> F (963) -> U (963)
	public void rotateRightClockwise() {
		right = rotateClockwise(right);
		String[] temp = permute(up, back, down, front, 963, 147, 963, 963);
		up = temp[0];
		back = temp[1];
		down = temp[2];
		front = temp[3];
	}

	// Rotate right anti-clockwise
	// U (963) -> F (963) -> D (963) -> B (147) -> U (963)
	public void rotateRightAntiClockwise() {
		right = rotateAntiClockwise(right);
		String[] temp = permute(up, front, down, back, 963, 963, 963, 147);
		up = temp[0];
		front = temp[1];
		down = temp[2];
		back = temp[3];
	}

	// Rotate front clockwise
	// L (963) -> U (789) -> R (147) -> D (321) -> L (963)
	public void rotateFrontClockwise() {
		front = rotateClockwise(front);
		String[] temp = permute(left, up, right, down, 963, 789, 147, 321);
		left = temp[0];
		up = temp[1];
		right = temp[2];
		down = temp[3];
	}

	// Rotate front anti-clockwise
	// L (963) -> D (321) -> R (147) -> U (789) -> L (963)
	public void rotateFrontAntiClockwise() {
		front = rotateAntiClockwise(front);
		String[] temp = permute(left, down, right, up, 963, 321, 147, 789);
		left = temp[0];
		down = temp[1];
		right = temp[2];
		up = temp[3];
	}

	// Rotate back clockwise
	public void rotateBackClockwise() {
		back = rotateClockwise(back);
		String[] temp = permute(up, left, down, right, 321, 147, 789, 963);
		up = temp[0];
		left = temp[1];
		down = temp[2];
		right = temp[3];
	}

	// Rotate back anti-clockwise
	public void rotateBackAntiClockwise() {
		back = rotateAntiClockwise(back);
		String[] temp = permute(up, right, down, left, 321, 963, 789, 147);
		up = temp[0];
		right = temp[1];
		down = temp[2];
		left = temp[3];
	}

	// Helper method: Print the rubik cube
	public void print() {
		System.out.println("Current state:");
		System.out.println("    " + up.substring(0, 3));
		System.out.println("    " + up.substring(3, 6));
		System.out.println("    " + up.substring(6, 9));
		System.out.println();

		System.out.println(left.substring(0, 3) + " " + front.substring(0, 3) + " " + right.substring(0, 3) + " " + back.substring(0, 3));
		System.out.println(left.substring(3, 6) + " " + front.substring(3, 6) + " " + right.substring(3, 6) + " " + back.substring(3, 6));
		System.out.println(left.substring(6, 9) + " " + front.substring(6, 9) + " " + right.substring(6, 9) + " " + back.substring(6, 9));
		System.out.println();

		System.out.println("    " + down.substring(0, 3));
		System.out.println("    " + down.substring(3, 6));
		System.out.println("    " + down.substring(6, 9));
		System.out.println();

		//		System.out.println("    " + back.substring(0, 3));
		//		System.out.println("    " + back.substring(3, 6));
		//		System.out.println("    " + back.substring(6, 9));
		//		System.out.println();
	}

	// Main method to run driver tests
	public static void main(String[] args) {	
		// Test case for rotateUpClockwise
		String initial = "uuuuuuuuulllllllllfffffffffrrrrrrrrrdddddddddbbbbbbbbb";
		RubikCube cube = new RubikCube(initial);
		cube.rotateDownClockwise();
		cube.print();
	}
}