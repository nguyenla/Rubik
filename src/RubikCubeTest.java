import static org.junit.Assert.*;
import org.junit.Test;

public class RubikCubeTest {
	public static final String initial = "uuuuuuuuulllllllllfffffffffrrrrrrrrrdddddddddbbbbbbbbb";
	
	@Test
	public void testConstructor() {
		RubikCube cube = new RubikCube(initial);
		assertEquals(initial, cube.getState());
	}
	
	// Test rotate up
	@Test
	public void testRotateUpClockwise() {
		String end = "uuuuuuuuufffllllllrrrffffffbbbrrrrrrdddddddddlllbbbbbb";
		RubikCube cube = new RubikCube(initial);
		cube.rotateUpClockwise();
		assertEquals("Rotate Up clockwise fails!", end, cube.getState());
	}
	
	@Test
	public void testRotateUpAntiClockwise() {
		String end = "uuuuuuuuubbblllllllllfffffffffrrrrrrdddddddddrrrbbbbbb";
		RubikCube cube = new RubikCube(initial);
		cube.rotateUpAntiClockwise();
		assertEquals("Rotate Up anti-clockwise fails!", end, cube.getState());
	}
	
	// Test rotate down
	@Test
	public void testRotateDownClockwise() {
		String end = "uuuuuuuuullllllbbbfffffflllrrrrrrfffdddddddddbbbbbbrrr";
		RubikCube cube = new RubikCube(initial);
		cube.rotateDownClockwise();
		assertEquals("Rotate Down clockwise fails!", end, cube.getState());
	}
	
	@Test
	public void testRotateDownAntiClockwise() {
		String end = "uuuuuuuuullllllfffffffffrrrrrrrrrbbbdddddddddbbbbbblll";
		RubikCube cube = new RubikCube(initial);
		cube.rotateDownAntiClockwise();
		assertEquals("Rotate Down anti-clockwise fails!", end, cube.getState());
	}
}
