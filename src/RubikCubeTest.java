import static org.junit.Assert.*;
import org.junit.Test;

public class RubikCubeTest {
	public static final String initial = "uuuuuuuuu,lllllllll,fffffffff,rrrrrrrrr,ddddddddd,bbbbbbbbb";

	@Test
	public void testConstructor() {
		RubikCube cube = new RubikCube(initial);
		assertEquals(initial, cube.getState());
	}

	// Test rotate up
	@Test
	public void testRotateUpClockwise() {
		String end = "uuuuuuuuu,fffllllll,rrrffffff,bbbrrrrrr,ddddddddd,lllbbbbbb";
		RubikCube cube = new RubikCube(initial);
		cube.rotateUpClockwise();
		assertEquals("Rotate Up clockwise fails!", end, cube.getState());
	}

	@Test
	public void testRotateUpAntiClockwise() {
		String end = "uuuuuuuuu,bbbllllll,lllffffff,fffrrrrrr,ddddddddd,rrrbbbbbb";
		RubikCube cube = new RubikCube(initial);
		cube.rotateUpAntiClockwise();
		assertEquals("Rotate Up anti-clockwise fails!", end, cube.getState());
	}

	// Test rotate down
	@Test
	public void testRotateDownClockwise() {
		String end = "uuuuuuuuu,llllllbbb,fffffflll,rrrrrrfff,ddddddddd,bbbbbbrrr";
		RubikCube cube = new RubikCube(initial);
		cube.rotateDownClockwise();
		assertEquals("Rotate Down clockwise fails!", end, cube.getState());
	}

	@Test
	public void testRotateDownAntiClockwise() {
		String end = "uuuuuuuuu,llllllfff,ffffffrrr,rrrrrrbbb,ddddddddd,bbbbbblll";
		RubikCube cube = new RubikCube(initial);
		cube.rotateDownAntiClockwise();
		assertEquals("Rotate Down anti-clockwise fails!", end, cube.getState());
	}

	// Test rotate left
	@Test
	public void testRotateLeftClockwise() {
		String end = "buubuubuu,lllllllll,uffuffuff,rrrrrrrrr,fddfddfdd,bbdbbdbbd";
		RubikCube cube = new RubikCube(initial);
		cube.rotateLeftClockwise();
		assertEquals("Rotate Left clockwise fails!", end, cube.getState());
	}

	@Test
	public void testRotateLeftAntiClockwise() {
		String end = "fuufuufuu,lllllllll,dffdffdff,rrrrrrrrr,bddbddbdd,bbubbubbu";
		RubikCube cube = new RubikCube(initial);
		cube.rotateLeftAntiClockwise();
		assertEquals("Rotate Left anti-clockwise fails!", end, cube.getState());
	}
	
	// Test rotate right
	@Test
	public void testRotateRightClockwise() {
		String end = "uufuufuuf,lllllllll,ffdffdffd,rrrrrrrrr,ddbddbddb,ubbubbubb";
		RubikCube cube = new RubikCube(initial);
		cube.rotateRightClockwise();
		assertEquals("Rotate Right clockwise fails!", end, cube.getState());
	}

	@Test
	public void testRotateRightAntiClockwise() {
		String end = "uubuubuub,lllllllll,ffuffuffu,rrrrrrrrr,ddfddfddf,dbbdbbdbb";
		RubikCube cube = new RubikCube(initial);
		cube.rotateRightAntiClockwise();
		assertEquals("Rotate Right anti-clockwise fails!", end, cube.getState());
	}
	
	// Test rotate front
	@Test
	public void testRotateFrontClockwise() {
		String end = "uuuuuulll,lldlldlld,fffffffff,urrurrurr,rrrdddddd,bbbbbbbbb";
		RubikCube cube = new RubikCube(initial);
		cube.rotateFrontClockwise();
		assertEquals("Rotate Front clockwise fails!", end, cube.getState());
	}

	@Test
	public void testRotateFrontAntiClockwise() {
		String end = "uuuuuurrr,llullullu,fffffffff,drrdrrdrr,llldddddd,bbbbbbbbb";
		RubikCube cube = new RubikCube(initial);
		cube.rotateFrontAntiClockwise();
		assertEquals("Rotate Front anti-clockwise fails!", end, cube.getState());
	}
	
	// Test rotate back
	@Test
	public void testRotateBackClockwise() {
		String end = "rrruuuuuu,ullullull,fffffffff,rrdrrdrrd,ddddddlll,bbbbbbbbb";
		RubikCube cube = new RubikCube(initial);
		cube.rotateBackClockwise();
		assertEquals("Rotate Back clockwise fails!", end, cube.getState());
	}

	@Test
	public void testRotateBackAntiClockwise() {
		String end = "llluuuuuu,dlldlldll,fffffffff,rrurrurru,ddddddrrr,bbbbbbbbb";
		RubikCube cube = new RubikCube(initial);
		cube.rotateBackAntiClockwise();
		assertEquals("Rotate Back anti-clockwise fails!", end, cube.getState());
	}
	
	// Test rotate cube right
	@Test
	public void testRotateCubeRight() {
		String end = "uuuuuuuuu,bbbbbbbbb,lllllllll,fffffffff,ddddddddd,rrrrrrrrr";
		RubikCube cube = new RubikCube(initial);
		cube.rotateCubeRight();
		assertEquals("Rotate Cube Right fails!", end, cube.getState());
	}
	
	// Test rotate cube left
	@Test
	public void testRotateCubeLeft() {
		String end = "uuuuuuuuu,fffffffff,rrrrrrrrr,bbbbbbbbb,ddddddddd,lllllllll";
		RubikCube cube = new RubikCube(initial);
		cube.rotateCubeLeft();
		assertEquals("Rotate Cube Left fails!", end, cube.getState());
	}

}
